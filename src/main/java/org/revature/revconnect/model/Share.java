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
        name = "shares",
        indexes = {
                @Index(name = "idx_user_id", columnList = "user_id"),
                @Index(name = "idx_post_id", columnList = "post_id"),
                @Index(name = "idx_original_author_id", columnList = "original_author_id"),
                @Index(name = "idx_created_at", columnList = "created_at")
        }
)
public class Share {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // User who shared
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    // Post being shared
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "post_id", nullable = false)
    private Post post;

    // Original author of the post
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "original_author_id", nullable = false)
    private User originalAuthor;

    @Lob
    private String caption;

    @Enumerated(EnumType.STRING)
    @Column(name = "share_type", nullable = false)
    private ShareType shareType = ShareType.REPOST;

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @PrePersist
    public void onCreate() {
        this.createdAt = LocalDateTime.now();
        if (this.shareType == null) {
            this.shareType = ShareType.REPOST;
        }
    }

    // ===== ENUM INSIDE ENTITY =====
    public enum ShareType {
        REPOST,
        QUOTE
    }
}