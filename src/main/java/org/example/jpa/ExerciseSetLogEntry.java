package org.example.jpa;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Timestamp;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "exercise_set_log_entry")
@Schema(description = "Exercise set log entry. Belongs to a workout log entry.")
public class ExerciseSetLogEntry {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private long id;

    @JoinColumn(name = "user_id")
    @ManyToOne
    private User user;

    @JoinColumn(name = "workout_log_entry_id")
    @ManyToOne
    private WorkoutLogEntry workoutLogEntry;

    @JoinColumn(name = "exercise_id")
    @ManyToOne
    private Exercise exercise;

    @Column(name = "set_number")
    private int setNumber;

    @Column(name = "weight")
    private int weight;

    @Column(name = "repetitions")
    private int repetitions;

    @Column(name = "failed")
    private boolean failed;

    @Column(name = "created_at")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Timestamp createdAt;

}
