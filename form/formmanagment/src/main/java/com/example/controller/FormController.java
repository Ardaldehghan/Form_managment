package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.service.*;
import com.example.dto.*;

@RestController
@RequestMapping("/forms")
public class FormController {

    @Autowired
    private FormService formService;

    // ---- Old APIs ----

    // GET: Get all forms
    @GetMapping("/")
    public List<FormDTO> getAllForms() {


        return formService.getAllForms();
    }

    // POST: Create a new form
    @PostMapping("/")
    public FormDTO createForm(@RequestBody FormDTO formDTO) {
        return formService.createForm(formDTO);
    }

    // GET: Get a form by ID
    @GetMapping("/{id}")
    public FormDTO getFormById(@PathVariable Long id) {
        return formService.getFormById(id);
    }

    // PUT: Update a form
    @PutMapping("/{id}")
    public FormDTO updateForm(@PathVariable Long id, @RequestBody FormDTO formDTO) {
        return formService.updateForm(id, formDTO);
    }

    // DELETE: Delete a form
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteForm(@PathVariable Long id) {
        formService.deleteForm(id);
        return ResponseEntity.ok().build();
    }

    // ---- New APIs ----

    // GET: View fields of a specific form
    @GetMapping("/{id}/fields")
    public List<FieldDTO> getFormFields(@PathVariable Long id) {
        return formService.getFormFields(id);
    }

    // PUT: Update fields of a specific form
    @PutMapping("/{id}/fields")
    public FormDTO updateFormFields(@PathVariable Long id, @RequestBody List<FieldDTO> fields) {
        return formService.updateFormFields(id, fields);
    }

    // POST: Toggle publish status of a form
    @PostMapping("/{id}/publish")
    public FormDTO toggleFormPublish(@PathVariable Long id) {
        return formService.toggleFormPublish(id);
    }

    // GET: Get a list of published forms
    @GetMapping("/published")
    public List<FormDTO> getPublishedForms() {
        return formService.getPublishedForms();
    }
}

