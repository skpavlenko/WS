package com.ccentre.service;

import com.ccentre.entity.WikiPDF;

import java.util.List;

public interface WikiPDFService {
    void add(WikiPDF wikiPDF);

    void delete(WikiPDF wikiPDF);

    WikiPDF findOne(long id);

    List<WikiPDF> list();
}
