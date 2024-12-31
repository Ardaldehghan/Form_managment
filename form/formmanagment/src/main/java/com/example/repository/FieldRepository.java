package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.Entity.Field;

@Repository
public interface FieldRepository extends JpaRepository<Field, Long> {}
