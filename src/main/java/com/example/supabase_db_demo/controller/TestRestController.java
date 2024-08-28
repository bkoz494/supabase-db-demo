package com.example.supabase_db_demo.controller;

import com.example.supabase_db_demo.dao.NotesRepository;
import com.example.supabase_db_demo.dto.NoteRequest;
import com.example.supabase_db_demo.model.Notes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class TestRestController {

    @Autowired
    NotesRepository notesRepository;

    @GetMapping("/notes")
    public List<Notes> getNotes(){
        return notesRepository.findAll();
    }

    @GetMapping("/notes/{id}")
    public Notes getNotesById(@PathVariable Long id){
        return notesRepository.findById(id).orElseThrow(() -> new NoSuchElementException(new NoSuchElementException("Could not find a note with id: " + id)));
    }

    @PostMapping("/notes")
    public ResponseEntity<Notes> createNote(@RequestBody NoteRequest request){
        Notes note = new Notes();
        note.setTitle(request.getTitle());
        note.setText(request.getText());
        Notes saved = notesRepository.save(note);
        return new ResponseEntity<>(note, HttpStatus.CREATED);
    }

    @PutMapping("/notes/{id}")
    public ResponseEntity<Notes> updateNote(@RequestBody NoteRequest request, @PathVariable Long id){
        Notes note = notesRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Could not find a note with id: " + id));
        note.setTitle(request.getTitle());
        note.setText(request.getText());
        Notes saved = notesRepository.save(note);
        return new ResponseEntity<>(note, HttpStatus.OK);
    }

    @DeleteMapping("/notes/{id}")
    public ResponseEntity<Void> deleteNote(@PathVariable Long id) {
        if (!notesRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        notesRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
