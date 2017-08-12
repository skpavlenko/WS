package com.ccentre.service;

import com.ccentre.entity.Group;
import com.ccentre.entity.WikiText;

import java.util.List;

public interface WikiTextService {
    void add(WikiText wikiText);

    void delete(WikiText wikiText);

    WikiText findOne(long id);

    List<WikiText> list();
}
