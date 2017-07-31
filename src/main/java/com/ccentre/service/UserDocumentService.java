package com.ccentre.service;

import com.ccentre.entity.UserDocument;

import java.util.List;

public interface UserDocumentService {

	UserDocument findById(Long id);

	List<UserDocument> findAll();
	
	List<UserDocument> findAllByUserId(Long id);
	
	void saveDocument(UserDocument document);
	
	void deleteById(Long id);
}
