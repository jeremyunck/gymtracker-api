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
@Table(name = "exercise")
@Schema(description = "All details about the Exercise.")
public class Exercise {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name="name")
    private String name;

    @Column(name="description")
    private String description;

    @JoinColumn(name="creator_id")
    @ManyToOne
    private User creator;

    @Column(name="created_at")
    @DateTimeFormat(pattern = Constants.TIMESTAMP_FORMAT)
    private Timestamp createdAt;

    @Column(name="used_count")
    private int usedCount;
}
