package com.example.supabase_db_demo.controller;

import com.example.supabase_db_demo.dao.NotesRepository;
import com.example.supabase_db_demo.dto.NoteRequest;
import com.example.supabase_db_demo.model.Notes;
import com.example.supabase_db_demo.tools.PrincipalTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api")
public class TestRestController {

    @Autowired
    NotesRepository notesRepository;

    @GetMapping("/notes")
    public List<Notes> getNotes(Principal principal){
        UUID id = PrincipalTools.getPrincipalId(principal);
        return notesRepository.findByOwnerId(id);
    }

    @GetMapping("/notes/{id}")
    public Object getNotesById(@PathVariable Long id, Principal principal){
        Notes note = notesRepository.findById(id).orElseThrow(() -> new NoSuchElementException(new NoSuchElementException("Could not find a note with id: " + id)));
        UUID principalId = PrincipalTools.getPrincipalId(principal);
        if(note.getOwnerId().equals(principalId)){
            return note;
        } else {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }

    @PostMapping("/notes")
    public ResponseEntity<Notes> createNote(@RequestBody NoteRequest request,
                                            Principal principal){
        Notes note = new Notes();
        note.setTitle(request.getTitle());
        note.setText(request.getText());
        UUID principalId = PrincipalTools.getPrincipalId(principal);
        note.setOwnerId(principalId);
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
