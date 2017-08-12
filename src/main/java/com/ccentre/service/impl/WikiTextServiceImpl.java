package com.ccentre.service.impl;


import com.ccentre.entity.WikiText;
import com.ccentre.repository.WikiTextRepository;
import com.ccentre.service.WikiTextService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class WikiTextServiceImpl implements WikiTextService {
    @Autowired
    private WikiTextRepository wikiTextRepository;

    @Override
    @Transactional
    public void add(WikiText wikiText) {
        wikiTextRepository.save(wikiText);
    }

    @Override
    @Transactional
    public void delete(WikiText wikiText) {
        wikiTextRepository.delete(wikiText);
    }

    @Override
    @Transactional(readOnly = true)
    public WikiText findOne(long id) {
        return wikiTextRepository.getOne(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<WikiText> list() {
        return wikiTextRepository.findAll();
    }
}
