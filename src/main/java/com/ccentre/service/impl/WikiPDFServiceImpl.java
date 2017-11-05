package com.ccentre.service.impl;


import com.ccentre.entity.WikiPDF;
import com.ccentre.repository.WikiPDFRepository;
import com.ccentre.service.WikiPDFService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class WikiPDFServiceImpl implements WikiPDFService {
    @Autowired
    private WikiPDFRepository wikiPDFRepository;

    @Override
    @Transactional
    public void add(WikiPDF wikiPDF) {
        wikiPDFRepository.save(wikiPDF);
    }

    @Override
    @Transactional
    public void delete(WikiPDF wikiPDF) {
        wikiPDFRepository.delete(wikiPDF);
    }

    @Override
    @Transactional(readOnly = true)
    public WikiPDF findOne(long id) {
        return wikiPDFRepository.getOne(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<WikiPDF> list() {
        return wikiPDFRepository.findAll();
    }
}
