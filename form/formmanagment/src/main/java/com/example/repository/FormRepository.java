package com.example.repository;

import com.example.Entity.Form;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FormRepository extends JpaRepository<Form, Long> {
    // You can add custom query methods here if needed.
    List<Form> findByPublished(Boolean published);
}


