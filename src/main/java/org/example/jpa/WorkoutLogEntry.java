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
@Table(name = "workout_log_entry")
@Schema(description = "Workout entry. Belongs to a user and a routine. Contains a list of exercise set log entries.")
public class WorkoutLogEntry {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.AUTO)
    private long id;

    @JoinColumn(name = "user_id")
    @ManyToOne
    private User user;

    @JoinColumn(name = "routine_id")
    @ManyToOne
    private Routine routine;

    @Column(name = "created_at")
    @DateTimeFormat(pattern = Constants.TIMESTAMP_FORMAT)
    private Timestamp createdAt;
}
