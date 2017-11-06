package com.ccentre.service.impl;

import com.ccentre.repository.*;
import com.ccentre.service.UserDocumentService;
import com.ccentre.service.WikiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

import com.ccentre.entity.*;

@Service
public class WikiServiceImpl implements WikiService {

    @Autowired
    private WikiRepository wikiRepository;

    @Autowired
    private GroupRepository groupRepository;

    @Autowired
    private WikiTextRepository wikiTextRepository;

    @Autowired
    private WikiPDFRepository wikiPDFRepository;

    @Autowired
    private UserDocumentRepository userDocumentRepository;

    @Autowired
    private UserDocumentService userDocumentService;

    @Override
    @Transactional
    public void add(Wiki wiki){
        wikiRepository.save(wiki);
    }

    @Override
    @Transactional
    public void delete(Wiki wiki) {
        wikiRepository.delete(wiki);
    }

    @Override
    @Transactional
    public void delete(long[] ids) {
        Wiki c;
        for (long id : ids) {
            c = wikiRepository.getOne(id);
            //del descr
            wikiTextRepository.delete(c.getWikiText());
            //del pdf
            wikiPDFRepository.delete(c.getWikiPDF());
            //del all files
            List<UserDocument> documents = userDocumentService.findAllByWiki(c);
            for (UserDocument u:
                 documents) {userDocumentRepository.delete(u);}

            wikiRepository.delete(c);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<Wiki> list() {
        return wikiRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Wiki> list(Group group) {
        return wikiRepository.list(group);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Wiki> list(String pattern) {
        return wikiRepository.list(pattern);
    }

    @Override
    @Transactional
    public void addGroup(Group group) {
        groupRepository.save(group);
    }

    @Override
    @Transactional
    public void deleteGroup(Group group) {
        groupRepository.delete(group);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Group> listGroups() {
        return groupRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Group findGroup(long id) {
        return groupRepository.getOne(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Wiki getWikiById(long id) {
        return wikiRepository.findOne(id);
    }

}