package org.example.repository;

import org.example.jpa.ExerciseByRoutineIdView;
import org.example.jpa.RoutineExerciseMap;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoutineExerciseMapRepository extends JpaRepository<RoutineExerciseMap, Long> {
    List<ExerciseByRoutineIdView> findExerciseByRoutineId(Long routineId);
    List<RoutineExerciseMap> findByRoutineId(Long routineId);
}
