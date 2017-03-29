package com.ccentre.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ccentre.entity.*;

public interface GroupRepository extends JpaRepository<Group, Long> {
}
