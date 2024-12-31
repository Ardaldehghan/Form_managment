package com.example.service;

import java.util.List;
import java.util.stream.Collectors;

import com.example.exception.FormNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//import com.example.repository.FieldRepository;
import com.example.repository.FormRepository;
import com.example.Entity.Field;
import com.example.Entity.Form;
import com.example.dto.FieldDTO;
import com.example.dto.FormDTO;
@Service
public class FormServiceImpl implements FormService {

    @Autowired
    private FormRepository formRepository;

   // @Autowired
   // private FieldRepository fieldRepository;

    // ---- Old APIs ----

    @Override
    public List<FormDTO> getAllForms() {
        return formRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public FormDTO createForm(FormDTO formDTO) {
        Form form = convertToEntity(formDTO);
        if (form.getFields() != null) {
            form.getFields().forEach(ff -> ff.setForm(form));
        }
        Form savedForm = formRepository.save(form);
        return convertToDTO(savedForm);
    }

    @Override
    public FormDTO getFormById(Long id) {
        Form form = formRepository.findById(id)
                .orElseThrow(() -> new FormNotFoundException("Form with ID " + id + " not found"));
        return convertToDTO(form);
    }


    @Override
    public FormDTO updateForm(Long id, FormDTO formDTO) {
        Form form = formRepository.findById(id)
                .orElseThrow(() -> new FormNotFoundException("Form with ID " + id + " not found"));

        form.setName(formDTO.getName());
        form.setPublished(formDTO.isPublished());

        Form updatedForm = formRepository.save(form);
        return convertToDTO(updatedForm);
    }

    @Override
    public void deleteForm(Long id) {
        Form form = formRepository.findById(id)
                .orElseThrow(() -> new FormNotFoundException("Form with ID " + id + " not found"));

        formRepository.delete(form);
    }

    @Override
    public List<FieldDTO> getFormFields(Long formId) {
        Form form = formRepository.findById(formId)
                .orElseThrow(() -> new FormNotFoundException("Form with ID " + formId + " not found"));
        return form.getFields().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public FormDTO updateFormFields(Long formId, List<FieldDTO> fields) {
        Form form = formRepository.findById(formId)
                .orElseThrow(() -> new FormNotFoundException("Form with ID " + formId + " not found"));

        form.getFields().clear();
        for (FieldDTO fieldDTO : fields) {
            Field field = convertToEntity(fieldDTO);
            field.setForm(form);
            form.getFields().add(field);
        }

        Form updatedForm = formRepository.save(form);
        return convertToDTO(updatedForm);
    }

    @Override
    public FormDTO toggleFormPublish(Long formId) {
        Form form = formRepository.findById(formId)
                .orElseThrow(() -> new FormNotFoundException("Form with ID " + formId + " not found"));

        // Toggle publish status
        form.setPublished(!form.isPublished());
        Form updatedForm = formRepository.save(form);

        return convertToDTO(updatedForm);
    }

    @Override
    public List<FormDTO> getPublishedForms() {
        List<Form> publishedForms = formRepository.findByPublished(true);
        return publishedForms.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    // ---- Mapper Methods ----

    private FormDTO convertToDTO(Form form) {
        FormDTO formDTO = new FormDTO();
        formDTO.setId(form.getId());
        formDTO.setName(form.getName());
        formDTO.setPublished(form.isPublished());
        formDTO.setDate(form.getDate());
        formDTO.setFields(form.getFields().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList()));
        return formDTO;
    }

    private FieldDTO convertToDTO(Field field) {
        FieldDTO fieldDTO = new FieldDTO();
        fieldDTO.setId(field.getId());
        fieldDTO.setName(field.getName());
        fieldDTO.setLabel(field.getLabel());
        fieldDTO.setType(field.getType());
        fieldDTO.setDefaultValue(field.getDefaultValue());
        return fieldDTO;
    }

    private Form convertToEntity(FormDTO formDTO) {
        Form form = new Form();
        form.setName(formDTO.getName());
        form.setPublished(formDTO.isPublished());
        form.setDate(formDTO.getDate());
        // Convert fields and link them to the form
        List<Field> fields = formDTO.getFields().stream()
                .map(fieldDTO -> {
                    Field field = convertToEntity(fieldDTO);
                    field.setForm(form);  // Ensure bidirectional linking
                    return field;
                })
                .collect(Collectors.toList());

        form.setFields(fields);
        return form;
    }



    private Field convertToEntity(FieldDTO fieldDTO) {
        Field field = new Field();
        field.setId(fieldDTO.getId());  // Include ID if it exists (for updates)
        field.setName(fieldDTO.getName());
        field.setLabel(fieldDTO.getLabel());
        field.setType(fieldDTO.getType());
        field.setDefaultValue(fieldDTO.getDefaultValue());
        return field;
    }


}


