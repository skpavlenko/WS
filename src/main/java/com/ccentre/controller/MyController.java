package com.ccentre.controller;

import com.ccentre.util.FileValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
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
import org.springframework.ui.ModelMap;
import org.springframework.util.FileCopyUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import com.ccentre.entity.*;
import com.ccentre.entity.enums.*;
import com.ccentre.service.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@Controller
public class MyController {
    static final int DEFAULT_GROUP_ID = -1;

    @Autowired
    private UserService userService;

    @Autowired
    private PartnerService partnerService;

    @Autowired
    private WikiService wikiService;

    @Autowired
    private WikiTextService wikiTextService;

    @Autowired
    UserDocumentService userDocumentService;

    @Autowired
    MessageSource messageSource;

    @Autowired
    FileValidator fileValidator;

    @InitBinder("fileBucket")
    protected void initBinder(WebDataBinder binder) {
        binder.setValidator(fileValidator);
    }

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

        return "redirect:/wiki";
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

    @RequestMapping("/pdf/{wiki_id}")
    public ResponseEntity<byte[]> onWiki(@PathVariable("wiki_id") long id) {
        return wikiById(id);
    }

    private ResponseEntity<byte[]> wikiById(long id) {
        Wiki wiki = wikiService.getWikiById(id);
        int blobLength = 0;
        try {
            blobLength = (int) wiki.getPdf().length();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        byte[] bytes = new byte[0];
        try {
            bytes = wiki.getPdf().getBytes(1, blobLength);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (bytes == null)
            throw new PhotoNotFoundException();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType("application/pdf"));

        return new ResponseEntity<byte[]>(bytes, headers, HttpStatus.OK);
    }

    @RequestMapping("/wiki")
    public String wiki(Model model) {
        model.addAttribute("groups", wikiService.listGroups());
        model.addAttribute("wikis", wikiService.list());
        return "wiki";
    }

    @RequestMapping("/userslist")
    public String userslist(Model model) {
        model.addAttribute("users", userService.listUser());
        return "userslist";
    }

    @RequestMapping("/wiki_add_page")
    public String wikiAddPage(ModelMap model) {
        model.addAttribute("groups", wikiService.listGroups());

        return "wiki_add_page";
    }

    @RequestMapping("/wiki_edit_page")
    public String wikiAddPage(@RequestParam long id,
                                 ModelMap model) {
        Wiki wiki = wikiService.getWikiById(id);

        if (wiki != null) {
            model.addAttribute("id", id);
            model.addAttribute("grp", wiki.getGroup());
            model.addAttribute("name", wiki.getName());
            model.addAttribute("description", wiki.getWikiText().getDescription());
            model.addAttribute("customUser", wiki.getCustomUser());
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
        if (toDelete != null) {
            wikiService.delete(toDelete);}

        model.addAttribute("groups", wikiService.listGroups());
        model.addAttribute("wikis", wikiService.list());

        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @RequestMapping(value = "/wiki/add", method = RequestMethod.POST)
    public String wikiAdd(@RequestParam(value = "group") long groupId,
                          @RequestParam String name,
                          @RequestParam String description,
                          @RequestParam String url,
                          @RequestParam @DateTimeFormat(pattern="yyyy-MM-dd") Date date,
                          @RequestParam MultipartFile pdf,
                          ModelMap model) {
        Group group = (groupId != DEFAULT_GROUP_ID) ? wikiService.findGroup(groupId) : null;

        //get current user
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String login = user.getUsername();
        CustomUser customUser = userService.getUserByLogin(login);

        //date
        if (date==null) date = new Date(System.currentTimeMillis());

        Wiki wiki = setPDF(group, name, customUser, url, date, pdf);
        wikiService.add(wiki);

        setDescr(wiki, description);

        model.addAttribute("groups", wikiService.listGroups());
        model.addAttribute("wikis", wikiService.list());

        //Documents
        model.addAttribute("user", wiki);

        FileBucket fileModel = new FileBucket();
        model.addAttribute("fileBucket", fileModel);

        List<UserDocument> documents = userDocumentService.findAllByWiki(wiki);
        model.addAttribute("documents", documents);

        return "redirect:/wiki";
    }

    private Wiki setPDF(Group group, String name, CustomUser customUser, String url, Date date, MultipartFile pdf) {
        Wiki wiki = null;
        if (pdf.isEmpty())
            wiki = new Wiki(group, name, customUser, url, date);
        else try {
            wiki = new Wiki(group, name, customUser, url, date, new javax.sql.rowset.serial.SerialBlob(pdf.getBytes()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return wiki;
    }

    private void setDescr(Wiki wiki, String description) {
        //description
        WikiText wikiText;
        if (wiki.getWikiText() == null) {
            wikiText = new WikiText(description);
            wikiTextService.add(wikiText);
            wiki.setWikiText(wikiText);
        } else
            wiki.getWikiText().setDescription(description);
    }

    @RequestMapping(value = "/wiki/edit", method = RequestMethod.POST)
    public String contactEdit(@RequestParam(value = "group") long groupId,
                              @RequestParam long id,
                              @RequestParam String name,
                              @RequestParam String description,
                              @RequestParam String url,
                              @RequestParam @DateTimeFormat(pattern="yyyy-MM-dd") Date date,
                              @RequestParam MultipartFile pdf,
                              ModelMap model) {
        Group group = (groupId != DEFAULT_GROUP_ID) ? wikiService.findGroup(groupId) : null;

        //get current user
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String login = user.getUsername();
        CustomUser customUser = userService.getUserByLogin(login);

        Wiki wiki = wikiService.getWikiById(id);
        if (wiki != null) {
            wiki.setGroup(group);
            wiki.setName(name);
            wiki.setCustomUser(customUser);
            wiki.setUrl(url);
            wiki.setDate(date);
            if (!pdf.isEmpty())
                try {
                    wiki.setPdf(new javax.sql.rowset.serial.SerialBlob(pdf.getBytes()));
                } catch (Exception e) {
                    e.printStackTrace();
                }

        } else {
            wiki = setPDF(group, name, customUser, url, date, pdf);
        };
        wikiService.add(wiki);

        setDescr(wiki, description);

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

    @RequestMapping(value = { "/add-document-{userId}" }, method = RequestMethod.GET)
    public String addDocuments(@PathVariable Long userId, ModelMap model) {
        Wiki wiki = wikiService.getWikiById(userId);
        model.addAttribute("user", wiki);

        FileBucket fileModel = new FileBucket();
        model.addAttribute("fileBucket", fileModel);

        List<UserDocument> documents = userDocumentService.findAllByWiki(wiki);
        model.addAttribute("documents", documents);

        return "managedocuments";
    }


    @RequestMapping(value = { "/download-document-{userId}-{docId}" }, method = RequestMethod.GET)
    public String downloadDocument(@PathVariable Long userId, @PathVariable Integer docId, HttpServletResponse response) throws IOException {
        UserDocument document = userDocumentService.findById(docId);
        response.setContentType(document.getType());
        response.setContentLength(document.getContent().length);
        response.setHeader("Content-Disposition","attachment; filename=\"" + document.getName() +"\"");

        FileCopyUtils.copy(document.getContent(), response.getOutputStream());

        return "redirect:/add-document-"+userId;
    }

    @RequestMapping(value = { "/delete-document-{userId}-{docId}" }, method = RequestMethod.GET)
    public String deleteDocument(@PathVariable Long userId, @PathVariable Integer docId) {
        userDocumentService.deleteById(docId);
        return "redirect:/add-document-"+userId;
    }

    @RequestMapping(value = { "/add-document-{userId}" }, method = RequestMethod.POST)
    public String uploadDocument(@Valid FileBucket fileBucket, BindingResult result, ModelMap model, @PathVariable Long userId) throws IOException{

        if (result.hasErrors()) {
            System.out.println("validation errors");
            Wiki wiki = wikiService.getWikiById(userId);
            model.addAttribute("user", wiki);

            List<UserDocument> documents = userDocumentService.findAllByWiki(wiki);
            model.addAttribute("documents", documents);

            return "managedocuments";
        } else {

            System.out.println("Fetching file");

            Wiki wiki = wikiService.getWikiById(userId);
            model.addAttribute("user", wiki);

            saveDocument(fileBucket, wiki);

            return "redirect:/add-document-"+userId;
        }
    }

    private void saveDocument(FileBucket fileBucket, Wiki wiki) throws IOException{

        UserDocument document = new UserDocument();

        MultipartFile multipartFile = fileBucket.getFile();

        document.setName(multipartFile.getOriginalFilename());
        document.setDescription(fileBucket.getDescription());
        document.setType(multipartFile.getContentType());
        document.setContent(multipartFile.getBytes());
        document.setWiki(wiki);
        userDocumentService.saveDocument(document);
    }

    @RequestMapping("/useredit")
    public String userEditPage(@RequestParam long id,
                               ModelMap model) {
        CustomUser customUser = userService.getUserById(id);

        if (customUser != null) {
            model.addAttribute("id", id);
            model.addAttribute("rle", customUser.getRole().name());
            model.addAttribute("login", customUser.getLogin());
            model.addAttribute("email", customUser.getEmail());
            model.addAttribute("phone", customUser.getPhone());
            model.addAttribute("skype", customUser.getSkype());
        }
        List<String> userRoles = new ArrayList<String>();
        for(UserRole ur: UserRole.values()) {userRoles.add(ur.name());}
        model.addAttribute("roles", userRoles);

        return "useredit";
    }

    @RequestMapping(value = "/user/edit", method = RequestMethod.POST)
    public String userUpdate(@RequestParam Long id,
                             @RequestParam String login,
                             @RequestParam String role,
                             @RequestParam String password,
                             @RequestParam(required = false) String email,
                             @RequestParam(required = false) String phone,
                             @RequestParam(required = false) String skype,
                             Model model) {
        CustomUser dbUser = userService.getUserById(id);

        ShaPasswordEncoder encoder = new ShaPasswordEncoder();
        String passHash = encoder.encodePassword(password, null);

        dbUser.setRole(UserRole.valueOf(role));
        dbUser.setPassword(passHash);
        dbUser.setEmail(email);
        dbUser.setPhone(phone);
        dbUser.setSkype(skype);
        userService.addUser(dbUser);

        return "redirect:/userslist";
    }
}
