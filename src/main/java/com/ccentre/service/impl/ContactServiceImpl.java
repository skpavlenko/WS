package com.ccentre.service.impl;

import com.ccentre.repository.ContactRepository;
import com.ccentre.repository.GroupRepository;
import com.ccentre.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

import com.ccentre.entity.*;

@Service
public class ContactServiceImpl implements ContactService {

    @Autowired
    private ContactRepository contactRepository;

    @Autowired
    private GroupRepository groupRepository;

    @Override
    @Transactional
    public void add(Contact contact) {
        contactRepository.save(contact);
    }

    @Override
    @Transactional
    public void delete(Contact contact) {
        contactRepository.delete(contact);
    }

    @Override
    @Transactional
    public void delete(long[] ids) {
        Contact c;
        for (long id : ids) {
            c = contactRepository.getOne(id);
            contactRepository.delete(c);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<Contact> list() {
        return contactRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Contact> list(Group group) {
        return contactRepository.list(group);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Contact> list(String pattern) {
        return contactRepository.list(pattern);
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
    public Contact getContactById(long id) {
        return contactRepository.findOne(id);
    }

}