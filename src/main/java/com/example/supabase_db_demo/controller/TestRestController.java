package com.example.supabase_db_demo.controller;

import com.example.supabase_db_demo.dao.NotesRepository;
import com.example.supabase_db_demo.dto.NoteRequest;
import com.example.supabase_db_demo.model.Notes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class TestRestController {

    @Autowired
    NotesRepository notesRepository;

    @GetMapping("/notes")
    public List<Notes> getNotes(){
        return notesRepository.findAll();
    }

    @PostMapping("/notes")
    public ResponseEntity<Notes> createNote(@RequestBody NoteRequest request){
        Notes note = new Notes();
        note.setTitle(request.getTitle());
        note.setText(request.getText());
        Notes saved = notesRepository.save(note);
        return new ResponseEntity<>(note, HttpStatus.CREATED);
    }
}
