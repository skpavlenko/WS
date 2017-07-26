package com.ccentre.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

import com.ccentre.entity.*;

public interface WikiRepository extends JpaRepository<Wiki, Long> {
    @Query("SELECT c FROM Wiki c WHERE c.group = :group")
    List<Wiki> list(@Param("group") Group group);

    @Query("SELECT c FROM Wiki c WHERE c.name LIKE %:pattern%")
    List<Wiki> list(@Param("pattern") String pattern);
}
