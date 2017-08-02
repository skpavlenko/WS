package com.ccentre.repository;

import com.ccentre.entity.Group;
import com.ccentre.entity.UserDocument;
import com.ccentre.entity.Wiki;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserDocumentRepository extends JpaRepository<UserDocument, Integer> {
    @Query("SELECT c FROM UserDocument c WHERE c.wiki = :wiki")
    List<UserDocument> findAllByWiki(@Param("wiki") Wiki wiki);
}
