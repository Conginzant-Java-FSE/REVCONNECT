package org.revature.revconnect.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(
        name = "users",
        indexes = {
                @Index(name = "idx_username", columnList = "username"),
                @Index(name = "idx_email", columnList = "email"),
                @Index(name = "idx_user_type", columnList = "user_type"),
                @Index(name = "idx_created_at", columnList = "created_at")
        }
)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // ===== Core fields =====
    @Column(nullable = false, unique = true, length = 50)
    private String username;

    @Column(nullable = false, unique = true, length = 100)
    private String email;

    @Column(nullable = false, length = 255)
    private String password;

    @Column(nullable = false, length = 100)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(name = "user_type", nullable = false, length = 20)
    private UserType userType = UserType.PERSONAL;

    @Lob
    @Column(columnDefinition = "TEXT")
    private String bio;

    @Column(name = "profile_picture", length = 255)
    private String profilePicture;

    @Column(length = 100)
    private String location;

    @Column(length = 255)
    private String website;

    @Enumerated(EnumType.STRING)
    @Column(length = 10)
    private ProfilePrivacy privacy = ProfilePrivacy.PUBLIC;

    @Column(name = "is_verified")
    private Boolean isVerified = false;

    // ===== Creator/Business specific fields (same users table) =====
    @Column(name = "business_name", length = 100)
    private String businessName;

    @Column(length = 50)
    private String category;

    @Column(length = 50)
    private String industry;

    @Column(name = "contact_info", length = 255)
    private String contactInfo;

    @Column(name = "business_address", length = 255)
    private String businessAddress;

    @Column(name = "business_hours", length = 100)
    private String businessHours;

    @Lob
    @Column(name = "external_links", columnDefinition = "TEXT")
    private String externalLinks;

    @Lob
    @Column(name = "social_media_links", columnDefinition = "TEXT")
    private String socialMediaLinks;

    // ===== Timestamps =====
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    // ===== Relationships (Member-1 tables) =====
    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private UserSettings userSettings;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private BusinessPage businessPage;

    // ===== lifecycle hooks =====
    @PrePersist
    public void onCreate() {
        LocalDateTime now = LocalDateTime.now();
        this.createdAt = now;
        this.updatedAt = now;
        if (this.userType == null) this.userType = UserType.PERSONAL;
        if (this.privacy == null) this.privacy = ProfilePrivacy.PUBLIC;
        if (this.isVerified == null) this.isVerified = false;
    }

    @PreUpdate
    public void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }

    // ===== Enums INSIDE entity (as requested) =====
    public enum UserType {
        PERSONAL, CREATOR, BUSINESS
    }

    public enum ProfilePrivacy {
        PUBLIC, PRIVATE
    }
}
