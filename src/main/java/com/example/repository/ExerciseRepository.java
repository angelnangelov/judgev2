package com.example.repository;

import com.example.models.entities.Exercise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExerciseRepository  extends JpaRepository<Exercise,Long> {
}
