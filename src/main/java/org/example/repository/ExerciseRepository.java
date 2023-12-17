package org.example.repository;

import org.example.jpa.Exercise;
import org.example.jpa.ExerciseView;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ExerciseRepository extends JpaRepository<Exercise, Long> {
    Optional<Exercise> findByName(String name);

    List<ExerciseView> findExerciseByNameLike(String pattern);
}
