package com.example.supabase_db_demo.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.bind.annotation.RequestMapping;

@Getter
@Setter
@NoArgsConstructor
@RequestMapping
public class NoteRequest {
    private String title;
    private String text;
}
