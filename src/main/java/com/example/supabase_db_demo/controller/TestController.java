package com.example.supabase_db_demo.controller;

import com.example.supabase_db_demo.dao.NotesRepository;
import com.example.supabase_db_demo.dto.CreateNoteForm;
import com.example.supabase_db_demo.model.Notes;
import com.example.supabase_db_demo.tools.NoteDtoToNoteMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.Optional;

@Controller
public class TestController {

    @Autowired
    NotesRepository notesRepository;

    @GetMapping("/")
    public String showIndex(Model model){
        List<Notes> notes = notesRepository.findAll();
        model.addAttribute("notes", notes);
        return "/index";
    }

    @GetMapping("/create-note-form")
    public String createNoteForm(Model model){
        model.addAttribute("noteDto", new CreateNoteForm());
        return "createNoteForm";
    }

    @PostMapping("/create-note")
    public String createNote(@ModelAttribute CreateNoteForm noteDto){
        Notes note = NoteDtoToNoteMapper.toNote(noteDto);
        notesRepository.save(note);
        return "redirect:/";
    }

    @GetMapping("/deleteNote/{id}")
    public String deleteNote(@PathVariable("id") Long noteId){
        notesRepository.deleteById(noteId);
        return "redirect:/";
    }
    @GetMapping("/updateNote/{id}")
    public String showUpdateNote(@PathVariable("id") Long noteId, Model model){
        Optional<Notes> notesOptional = notesRepository.findById(noteId);
        if(notesOptional.isPresent()){
            model.addAttribute("note", notesOptional.get());
            return "update-note-form :: form";
        }
        else {
            return "index";
        }
    }

    @PostMapping("/update-note")
    public String updateNote(@ModelAttribute Notes notes){
        notesRepository.save(notes);
        return "redirect:/";
    }
}
