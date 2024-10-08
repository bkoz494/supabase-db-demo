package com.example.supabase_db_demo.tools;

import com.example.supabase_db_demo.dto.CreateNoteForm;
import com.example.supabase_db_demo.model.Notes;

public class NoteDtoToNoteMapper {
    public static Notes toNote(CreateNoteForm createNoteForm){
        Notes notes = new Notes();
        notes.setTitle(createNoteForm.getTitle());
        notes.setText(createNoteForm.getText());
        return notes;
    }

    public static CreateNoteForm toDto(Notes notes){
        CreateNoteForm dto = new CreateNoteForm();
        dto.setTitle(notes.getTitle());
        dto.setText(notes.getText());
        return dto;
    }
}
