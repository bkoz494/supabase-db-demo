package com.example.supabase_db_demo.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "notes")
@Getter
@Setter
public class Notes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "created_at")
    private LocalDateTime createdAt;
    private String text;
    private String title;

    @PrePersist
    private void setCreatedAt(){
        this.createdAt = LocalDateTime.now();
    }
}
