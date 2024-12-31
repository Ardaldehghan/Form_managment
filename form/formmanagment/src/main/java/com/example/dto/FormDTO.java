package com.example.dto;

import java.time.LocalDate;
import java.util.List;

public class FormDTO {
    private Long id;
    private String name;
    private boolean published;
    private List<FieldDTO> fields;
    private LocalDate date;
    public FormDTO() {}

    public FormDTO(Long id, String name, boolean published, List<FieldDTO> fields) {
        this.id = id;
        this.name = name;
        this.published = published;
        this.fields = fields;
    }
    public List<FieldDTO> getFields() {
        return fields;
    }
    public void setFields(List<FieldDTO> fields) {
        this.fields = fields;
    }
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
    public void setPublished(boolean publish){
        this.published=publish;
    }
    public boolean isPublished(){
        return this.published;
    }
    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

}

