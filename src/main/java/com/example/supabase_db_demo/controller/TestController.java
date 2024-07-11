package com.example.supabase_db_demo.controller;

import com.example.supabase_db_demo.dao.NotesRepository;
import com.example.supabase_db_demo.model.Notes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class TestController {

    @Autowired
    NotesRepository notesRepository;

    @GetMapping("/")
    public String showIndex(Model model){
        List<Notes> notes = notesRepository.findAll();
        model.addAttribute("notes", notes);
        return "index";
    }
}
