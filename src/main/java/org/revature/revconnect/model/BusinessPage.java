package org.revature.revconnect.model;
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
        name = "business_pages",
        indexes = {
                @Index(name = "idx_user_id", columnList = "user_id"),
                @Index(name = "idx_page_url", columnList = "page_url"),
                @Index(name = "idx_is_published", columnList = "is_published")
        }
)
public class BusinessPage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // user_id is UNIQUE in DB (one business page per user)
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false, unique = true)
    private User user;

    @Column(name = "page_name", length = 100)
    private String pageName;

    @Lob
    @Column(name = "page_description", columnDefinition = "TEXT")
    private String pageDescription;

    @Column(name = "cover_image", length = 255)
    private String coverImage;

    @Column(name = "page_url", unique = true, length = 100)
    private String pageUrl;

    @Column(name = "is_published")
    private Boolean isPublished = false;

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @PrePersist
    public void onCreate() {
        LocalDateTime now = LocalDateTime.now();
        this.createdAt = now;
        this.updatedAt = now;
        if (this.isPublished == null) this.isPublished = false;
    }

    @PreUpdate
    public void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }
}
