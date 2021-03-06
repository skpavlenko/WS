package com.ccentre.service;

import com.ccentre.entity.UserDocument;
import com.ccentre.entity.Wiki;

import java.util.List;

public interface UserDocumentService {

	UserDocument findById(Integer id);

	List<UserDocument> findAll();
	
	List<UserDocument> findAllByWiki(Wiki wiki);
	
	void saveDocument(UserDocument document);
	
	void deleteById(Integer id);
}
