package com.ccentre.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import com.ccentre.entity.*;
import com.ccentre.entity.enums.*;
import com.ccentre.service.*;

@Controller
public class MyController {
    static final int DEFAULT_GROUP_ID = -1;

    @Autowired
    private UserService userService;

    @Autowired
    private PartnerService partnerService;

    @Autowired
    private WikiService wikiService;

    @RequestMapping("/")
    public String index(Model model) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String login = user.getUsername();

        CustomUser dbUser = userService.getUserByLogin(login);

        model.addAttribute("login", login);
        model.addAttribute("roles", user.getAuthorities());
        model.addAttribute("email", dbUser.getEmail());
        model.addAttribute("phone", dbUser.getPhone());
        model.addAttribute("skype", dbUser.getSkype());

        return "redirect:/partners";
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String update(@RequestParam(required = false) String email, @RequestParam(required = false) String phone,
                         @RequestParam(required = false) String skype) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String login = user.getUsername();

        CustomUser dbUser = userService.getUserByLogin(login);
        dbUser.setEmail(email);
        dbUser.setPhone(phone);
        dbUser.setSkype(skype);

        userService.updateUser(dbUser);

        return "redirect:/";
    }

    @RequestMapping(value = "/newuser", method = RequestMethod.POST)
    public String update(@RequestParam String login,
                         @RequestParam String password,
                         @RequestParam(required = false) String email,
                         @RequestParam(required = false) String phone,
                         @RequestParam(required = false) String skype,
                         Model model) {
        if (userService.existsByLogin(login)) {
            model.addAttribute("exists", true);
            return "register";
        }

        ShaPasswordEncoder encoder = new ShaPasswordEncoder();
        String passHash = encoder.encodePassword(password, null);

        CustomUser dbUser = new CustomUser(login, passHash, UserRole.USER, email, phone, skype);
        userService.addUser(dbUser);

        return "redirect:/";
    }

    @RequestMapping("/register")
    public String register() {
        return "register";
    }

    @RequestMapping("/admin")
    public String admin() {
        return "admin";
    }

    @RequestMapping("/index")
    public String index() {
        return "index";
    }

    @RequestMapping("/partners")
    public String partners(Model model) {
        List<Partner> list = partnerService.listPartner();
        Collections.sort(list);
        model.addAttribute("partners", list);
        return "partners";
    }

    @RequestMapping("/unauthorized")
    public String unauthorized(Model model) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("login", user.getUsername());
        return "unauthorized";
    }

    @RequestMapping("/partner_add_page")
    public String partnerAddPage(@RequestParam long id,
                                 Model model) {
        Partner partner = partnerService.getPartnerById(id);

        if (partner != null) {
            model.addAttribute("id", id);
            model.addAttribute("ord", partner.getOrd());
            model.addAttribute("name", partner.getName());
            model.addAttribute("topic", partner.getTopic());
            model.addAttribute("url", partner.getUrl());
        }

        return "partner_add_page";
    }

    @RequestMapping(value = "/partner/add", method = RequestMethod.POST)
    public String partnerAdd(@RequestParam String id,
                             @RequestParam int ord,
                             @RequestParam String name,
                             @RequestParam String topic,
                             @RequestParam String url,
                             @RequestParam MultipartFile logo,
                             Model model) {

        if (id.equals("")) {
            Partner partner = null;
            if (logo.isEmpty())
                partner = new Partner(name, topic, url);
            else try {
                partner = new Partner(name, topic, url, new javax.sql.rowset.serial.SerialBlob(logo.getBytes()));
            } catch (Exception e) {
                e.printStackTrace();
            }
            partnerService.addPartner(partner);
        } else {
            Partner partner = partnerService.getPartnerById(Long.parseLong(id));
            partner.setName(name);
            partner.setTopic(topic);
            partner.setUrl(url);
            partner.setOrd(ord);
            if (!logo.isEmpty())
                try {
                    partner.setLogo(new javax.sql.rowset.serial.SerialBlob(logo.getBytes()));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            partnerService.addPartner(partner);
        }
        return "redirect:/partners";
    }

    @RequestMapping(value = "/partner/delete", method = RequestMethod.POST)
    public ResponseEntity<Void> delete(@RequestParam(value = "toDelete[]", required = false) long[] toDelete, Model model) {
        if (toDelete != null)
            partnerService.deletePartnerById(toDelete);

        model.addAttribute("partners", partnerService.listPartner());
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @RequestMapping("/photo/{partner_id}")
    public ResponseEntity<byte[]> onPhoto(@PathVariable("partner_id") long id) {
        return photoById(id);
    }

    private ResponseEntity<byte[]> photoById(long id) {
        Partner partner = partnerService.getPartnerById(id);
        int blobLength = 0;
        try {
            blobLength = (int) partner.getLogo().length();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        byte[] bytes = new byte[0];
        try {
            bytes = partner.getLogo().getBytes(1, blobLength);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (bytes == null)
            throw new PhotoNotFoundException();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_PNG);

        return new ResponseEntity<byte[]>(bytes, headers, HttpStatus.OK);
    }

    @RequestMapping("/wiki")
    public String wiki(Model model) {
        model.addAttribute("groups", wikiService.listGroups());
        model.addAttribute("wikis", wikiService.list());
        return "wiki";
    }

    @RequestMapping("/wiki_add_page")
    public String wikiAddPage(Model model) {
        model.addAttribute("groups", wikiService.listGroups());
        return "wiki_add_page";
    }

    @RequestMapping("/wiki_edit_page")
    public String wikiAddPage(@RequestParam long id,
                                 Model model) {
        Wiki wiki = wikiService.getWikiById(id);

        if (wiki != null) {
            model.addAttribute("id", id);
            model.addAttribute("name", wiki.getName());
            model.addAttribute("description", wiki.getDescription());
            //model.addAttribute("customUser", wiki.getCustomUser());
            model.addAttribute("url", wiki.getUrl());
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            model.addAttribute("date", sdf.format(wiki.getDate()));
        }
        ;
        model.addAttribute("groups", wikiService.listGroups());
        return "wiki_add_page";
    }

    @RequestMapping("/group_add_page")
    public String groupAddPage() {
        return "group_add_page";
    }

    /*@RequestMapping("/group")
    public String listGroup(@RequestParam(value = "group") long groupId, Model model) {
        Group group = (groupId != DEFAULT_GROUP_ID) ? contactService.findGroup(groupId) : null;

        model.addAttribute("groups", contactService.listGroups());
        model.addAttribute("currentGroup", group);
        model.addAttribute("contacts", contactService.list(group));
        return "wiki";
    }*/

    @RequestMapping("/group/{id}")
    public String listGroup(@PathVariable(value = "id") long groupId, Model model) {
        Group group = (groupId != DEFAULT_GROUP_ID) ? wikiService.findGroup(groupId) : null;

        model.addAttribute("groups", wikiService.listGroups());
        model.addAttribute("currentGroup", group);
        model.addAttribute("wikis", wikiService.list(group));
        return "wiki";
    }

    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public String search(@RequestParam String pattern, Model model) {
        model.addAttribute("groups", wikiService.listGroups());
        model.addAttribute("wikis", wikiService.list(pattern));
        return "wiki";
    }

    @RequestMapping(value = "/wiki/delete", method = RequestMethod.POST)
    public ResponseEntity<Void> deleteWiki(@RequestParam(value = "toDelete[]", required = false) long[] toDelete, Model model) {
        if (toDelete != null)
            wikiService.delete(toDelete);

        model.addAttribute("groups", wikiService.listGroups());
        model.addAttribute("wikis", wikiService.list());

        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @RequestMapping(value = "/wiki/add", method = RequestMethod.POST)
    public String wikiAdd(@RequestParam(value = "group") long groupId,
                          @RequestParam String name,
                          @RequestParam String description,
                          //@RequestParam CustomUser customUser,
                          @RequestParam String url,
                          @RequestParam @DateTimeFormat(pattern="yyyy-MM-dd") Date date,
                          Model model) {
        Group group = (groupId != DEFAULT_GROUP_ID) ? wikiService.findGroup(groupId) : null;

        Wiki wiki = new Wiki(group, name, description, url, date);
        wikiService.add(wiki);
        model.addAttribute("groups", wikiService.listGroups());
        model.addAttribute("wikis", wikiService.list());
        return "redirect:/wiki";
    }

    @RequestMapping(value = "/wiki/edit", method = RequestMethod.POST)
    public String contactEdit(@RequestParam(value = "group") long groupId,
                              @RequestParam long id,
                              @RequestParam String name,
                              @RequestParam String description,
                              //@RequestParam CustomUser customUser,
                              @RequestParam String url,
                              @RequestParam @DateTimeFormat(pattern="yyyy-MM-dd") Date date,
                              Model model) {
        Group group = (groupId != DEFAULT_GROUP_ID) ? wikiService.findGroup(groupId) : null;

        Wiki wiki = wikiService.getWikiById(id);
        if (wiki != null) {
            wiki.setGroup(group);
            wiki.setName(name);
            wiki.setDescription(description);
            //wiki.setCustomUser(customUser);
            wiki.setUrl(url);
            wiki.setDate(date);
        } else {
            wiki = new Wiki(group, name, description, url, date);
        };
        wikiService.add(wiki);

        model.addAttribute("groups", wikiService.listGroups());
        model.addAttribute("wikis", wikiService.list());
        return "redirect:/wiki";
    }

    @RequestMapping(value = "/group/add", method = RequestMethod.POST)
    public String groupAdd(@RequestParam String name, Model model) {
        wikiService.addGroup(new Group(name));

        model.addAttribute("groups", wikiService.listGroups());
        model.addAttribute("wikis", wikiService.list());
        return "redirect:/wiki";
    }
}
