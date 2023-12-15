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
@Table(name = "user")
@Schema(description = "All details about the User.")
public class User {
    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.UUID)
    private long id;

    @Column(name="name")
    private String username;

    @Column(name="email")
    private String email;

    @Column(name="created_at")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Timestamp createdAt;
}
