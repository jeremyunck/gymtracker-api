package org.example.repository;

import org.example.jpa.Exercise;
import org.example.jpa.ExerciseSetLogEntry;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExerciseSetLogEntryRepository extends JpaRepository<ExerciseSetLogEntry, Long> {
}
