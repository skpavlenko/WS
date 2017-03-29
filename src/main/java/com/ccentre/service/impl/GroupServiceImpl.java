package com.ccentre.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

import com.ccentre.entity.*;
import com.ccentre.repository.*;
import com.ccentre.service.*;

@Service
public class GroupServiceImpl implements GroupService {
    @Autowired
    private GroupRepository groupRepository;

    @Override
    @Transactional
    public void add(Group group) {
        groupRepository.save(group);
    }

    @Override
    @Transactional
    public void delete(Group group) {
        groupRepository.delete(group);
    }

    @Override
    @Transactional(readOnly = true)
    public Group findOne(long id) {
        return groupRepository.getOne(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Group> list() {
        return groupRepository.findAll();
    }
}
