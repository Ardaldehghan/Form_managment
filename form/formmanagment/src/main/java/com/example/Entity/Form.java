package com.example.Entity;

import jakarta.persistence.*;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Form {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private boolean published;

    private LocalDate date;

    @OneToMany(mappedBy = "form", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Field> fields=new ArrayList<>();
    public Form() {}

    public Form(String name, boolean published,LocalDate date) {
        this.name = name;
        this.published = published;
        this.date=date;
    }
    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isPublished() {
        return published;
    }

    public void setPublished(boolean published) {
        this.published = published;
    }

    public List<Field> getFields() {
        return fields;
    }

    public void setFields(List<Field> fieldstoChange) {
        // Clear the existing fields to ensure proper cascading
        if (this.fields != null) {
            this.fields.clear();
        }

        // Handle null or empty fieldstoChange gracefully
        if (fieldstoChange == null) {
            return;
        }

        // Add new fields and ensure they are linked to this form
        for (Field field : fieldstoChange) {
            field.setForm(this);  // Link each field to this form
            this.fields.add(field);
        }
    }
    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

}
