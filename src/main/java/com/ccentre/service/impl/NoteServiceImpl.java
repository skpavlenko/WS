package com.ccentre.service.impl;

import com.ccentre.entity.Group;
import com.ccentre.entity.Note;
import com.ccentre.repository.GroupRepository;
import com.ccentre.repository.NoteRepository;
import com.ccentre.service.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class NoteServiceImpl implements NoteService {

    @Autowired
    private NoteRepository noteRepository;

    @Autowired
    private GroupRepository groupRepository;

    @Override
    @Transactional
    public void add(Note note){
        noteRepository.save(note);
    }

    @Override
    @Transactional
    public void delete(Note note) {
        noteRepository.delete(note);
    }

    @Override
    @Transactional
    public void delete(long[] ids) {
        Note n;
        for (long id : ids) {
            n = noteRepository.getOne(id);
            noteRepository.delete(n);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<Note> list() {
        return noteRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Note> list(Group group) {
        return noteRepository.list(group);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Note> list(String pattern) {
        return noteRepository.list(pattern);
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
    public Note getNoteById(long id) {
        return noteRepository.findOne(id);
    }

}