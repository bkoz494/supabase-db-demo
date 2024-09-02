package com.example.supabase_db_demo.dao;

import com.example.supabase_db_demo.model.Notes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface NotesRepository extends JpaRepository<Notes, Long> {
    List<Notes> findByOwnerId(UUID ownerId);
}
