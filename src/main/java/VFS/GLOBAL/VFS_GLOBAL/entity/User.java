package VFS.GLOBAL.VFS_GLOBAL.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;

@ToString
@Data
@Table(name = "User")
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userId;

    @Column(unique = true)
    private String trackingId;

    @NotEmpty(message = "Name is required")
    private String name;
    @NotEmpty(message = "passportNo is required")
    private String passportNo;

//    @NotEmpty(message = "DOB is required")
    private LocalDate dob;
    private LocalDate expectedDate;

    private String status;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;
}
