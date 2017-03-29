package com.ccentre.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

import com.ccentre.entity.*;

public interface ContactRepository extends JpaRepository<Contact, Long> {
    @Query("SELECT c FROM Contact c WHERE c.group = :group")
    List<Contact> list(@Param("group") Group group);

    @Query("SELECT c FROM Contact c WHERE c.name LIKE %:pattern%")
    List<Contact> list(@Param("pattern") String pattern);
}
