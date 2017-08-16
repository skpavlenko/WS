package com.ccentre.service;

import com.ccentre.entity.Group;
import com.ccentre.entity.Note;

import java.util.List;

public interface NoteService {
    void add(Note note);
    void delete(Note note);
    void delete(long[] ids);
    List<Note> list();
    List<Note> list(Group group);
    List<Note> list(String pattern);
    void addGroup(Group group);
    void deleteGroup(Group group);
    List<Group> listGroups();
    Group findGroup(long id);
    Note getNoteById(long id);
}
