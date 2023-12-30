package org.example.jpa;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.util.Constants;
import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Timestamp;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "routine_exercise_map")
@Schema(description = "Maps exercises to routines. Order number is the order in which the exercises should be performed.")
public class RoutineExerciseMap {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.AUTO)
    private long id;

    @JoinColumn(name = "routine_id")
    @OneToOne
    private Routine routine;

    @JoinColumn(name = "exercise_id")
    @OneToOne
    private Exercise exercise;

    @Column(name = "order_number")
    private int orderNumber;

    @Column(name = "created_at")
    @DateTimeFormat(pattern = Constants.TIMESTAMP_FORMAT)
    private Timestamp createdAt;
}
