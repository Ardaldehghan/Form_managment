package com.example.service;

import java.util.List;

import com.example.dto.FieldDTO;
import com.example.dto.FormDTO;

public interface FormService {
    List<FormDTO> getAllForms();
    FormDTO createForm(FormDTO formDTO);
    FormDTO getFormById(Long id);
    FormDTO updateForm(Long id, FormDTO formDTO);
    void deleteForm(Long id);
    List<FieldDTO> getFormFields(Long formId);
    FormDTO updateFormFields(Long formId, List<FieldDTO> fields);
    FormDTO toggleFormPublish(Long formId);
    List<FormDTO> getPublishedForms();
}

