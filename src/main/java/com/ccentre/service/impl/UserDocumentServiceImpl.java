package com.ccentre.service.impl;


import com.ccentre.entity.UserDocument;
import com.ccentre.entity.Wiki;
import com.ccentre.repository.UserDocumentRepository;
import com.ccentre.service.UserDocumentService;
import com.ccentre.service.impl.UserDocumentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("userDocumentService")
@Transactional
public class UserDocumentServiceImpl implements UserDocumentService{

	@Autowired
	UserDocumentRepository userDocumentRepository;

	public UserDocument findById(Integer id) {
		return userDocumentRepository.findOne(id);
	}

	public List<UserDocument> findAll() {
		return userDocumentRepository.findAll();
	}

	public List<UserDocument> findAllByWiki(Wiki wiki) {
		return userDocumentRepository.findAllByWiki(wiki);
	}
	
	public void saveDocument(UserDocument document){
		userDocumentRepository.save(document);
	}

	public void deleteById(Integer id){
		userDocumentRepository.delete(id);
	}
	
}
