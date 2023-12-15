package org.example.repository;

import org.example.jpa.WorkoutLogEntry;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WorkoutLogEntryRepository extends JpaRepository<WorkoutLogEntry, Long> {
}
