package org.example.repository;

import org.example.model.Exercise;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExerciseManagerRepository extends JpaRepository<Exercise, Long> {
}
