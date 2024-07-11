package com.example.supabase_db_demo.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateNoteForm {
    private String title;
    private String text;
}
