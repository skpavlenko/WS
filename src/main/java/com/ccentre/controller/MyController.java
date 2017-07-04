package com.ccentre.controller;

import org.springframework.beans.factory.annotation.Autowired;
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
import java.util.Collections;
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
    private ContactService contactService;

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
        model.addAttribute("groups", contactService.listGroups());
        model.addAttribute("contacts", contactService.list());
        return "wiki";
    }

    @RequestMapping("/contact_add_page")
    public String contactAddPage(Model model) {
        model.addAttribute("groups", contactService.listGroups());
        return "contact_add_page";
    }

    @RequestMapping("/contact_edit_page")
    public String contactAddPage(@RequestParam long id,
                                 Model model) {
        Contact contact = contactService.getContactById(id);

        if (contact != null) {
            model.addAttribute("id", id);
            model.addAttribute("grp", contact.getGroup());
            model.addAttribute("name", contact.getName());
            model.addAttribute("surname", contact.getSurname());
            model.addAttribute("phone", contact.getPhone());
            model.addAttribute("email", contact.getEmail());
        };
        model.addAttribute("groups", contactService.listGroups());
        return "contact_add_page";
    }

    @RequestMapping("/group_add_page")
    public String groupAddPage() {
        return "group_add_page";
    }

    @RequestMapping("/group")
    public String listGroup(@RequestParam(value = "group") long groupId, Model model) {
        Group group = (groupId != DEFAULT_GROUP_ID) ? contactService.findGroup(groupId) : null;

        model.addAttribute("groups", contactService.listGroups());
        model.addAttribute("currentGroup", group);
        model.addAttribute("contacts", contactService.list(group));
        return "wiki";
    }

    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public String search(@RequestParam String pattern, Model model) {
        model.addAttribute("groups", contactService.listGroups());
        model.addAttribute("contacts", contactService.list(pattern));
        return "/wiki";
    }

    @RequestMapping(value = "/contact/delete", method = RequestMethod.POST)
    public ResponseEntity<Void> deleteContact(@RequestParam(value = "toDelete[]", required = false) long[] toDelete, Model model) {
        if (toDelete != null)
            contactService.delete(toDelete);

        model.addAttribute("groups", contactService.listGroups());
        model.addAttribute("contacts", contactService.list());

        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @RequestMapping(value = "/contact/add", method = RequestMethod.POST)
    public String contactAdd(@RequestParam(value = "group") long groupId,
                             @RequestParam String name,
                             @RequestParam String surname,
                             @RequestParam String phone,
                             @RequestParam String email,
                             Model model) {
        Group group = (groupId != DEFAULT_GROUP_ID) ? contactService.findGroup(groupId) : null;

        Contact contact = new Contact(group, name, surname, phone, email);
        contactService.add(contact);

        model.addAttribute("groups", contactService.listGroups());
        model.addAttribute("contacts", contactService.list());
        return "redirect:/wiki";
    }

    @RequestMapping(value = "/group/add", method = RequestMethod.POST)
    public String groupAdd(@RequestParam String name, Model model) {
        contactService.addGroup(new Group(name));

        model.addAttribute("groups", contactService.listGroups());
        model.addAttribute("contacts", contactService.list());
        return "redirect:/wiki";
    }
}
