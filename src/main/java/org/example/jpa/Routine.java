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
@Table(name = "routine")
@Schema(description = "All details about the Routine. RoutineMap maps exercises to routines.")
public class Routine {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.UUID)
    private long id;

    @Column(name="name")
    private String name;

    @Column(name="description")
    private String description;

    @JoinColumn(name="creator_id")
    @ManyToOne
    private User creatorId;

    @Column(name="created_at")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Timestamp createdAt;

    @Column(name="used_count")
    private int usedCount;
}
