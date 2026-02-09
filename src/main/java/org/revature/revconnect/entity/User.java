package org.revature.revconnect.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 50)
    private String username;

    @Column(nullable = false, unique = true, length = 100)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false, length = 100)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private UserType userType = UserType.PERSONAL;

    @Column(columnDefinition = "TEXT")
    private String bio;

    private String profilePicture;
    private String location;
    private String website;

    @Enumerated(EnumType.STRING)
    private ProfilePrivacy privacy = ProfilePrivacy.PUBLIC;

    private Boolean isVerified = false;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private UserSettings userSettings;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private BusinessPage businessPage;

    private LocalDateTime createdAt = LocalDateTime.now();
    private LocalDateTime updatedAt = LocalDateTime.now();

    /* ===== ENUMS INSIDE ENTITY ===== */

    public enum UserType {
        PERSONAL,
        CREATOR,
        BUSINESS
    }

    public enum ProfilePrivacy {
        PUBLIC,
        PRIVATE
    }
}
