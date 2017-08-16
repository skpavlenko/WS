package com.ccentre.repository;

import com.ccentre.entity.Group;
import com.ccentre.entity.Note;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface NoteRepository extends JpaRepository<Note, Long> {
    @Query("SELECT n FROM Note n WHERE n.group = :group")
    List<Note> list(@Param("group") Group group);

    @Query("SELECT n FROM Note n WHERE n.name LIKE %:pattern%")
    List<Note> list(@Param("pattern") String pattern);
}
