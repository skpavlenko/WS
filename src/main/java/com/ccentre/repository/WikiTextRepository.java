package com.ccentre.repository;

import com.ccentre.entity.WikiText;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WikiTextRepository extends JpaRepository<WikiText, Long> {
}
