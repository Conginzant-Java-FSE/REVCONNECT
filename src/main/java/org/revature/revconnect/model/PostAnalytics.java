package org.revature.revconnect.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(
        name = "post_analytics",
        indexes = {
                @Index(name = "idx_post_id", columnList = "post_id"),
                @Index(name = "idx_total_views", columnList = "total_views"),
                @Index(name = "idx_engagement_rate", columnList = "engagement_rate")
        }
)
public class PostAnalytics {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "post_id", nullable = false, unique = true)
    private Post post;

    @Column(name = "total_views")
    private Integer totalViews = 0;

    @Column(name = "unique_viewers")
    private Integer uniqueViewers = 0;

    @Column(name = "total_likes")
    private Integer totalLikes = 0;

    @Column(name = "total_comments")
    private Integer totalComments = 0;

    @Column(name = "total_shares")
    private Integer totalShares = 0;


    @Column(name = "engagement_rate", precision = 5, scale = 2)
    private BigDecimal engagementRate = BigDecimal.ZERO;

    @Column(name = "reach")
    private Integer reach = 0;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @PrePersist
    @PreUpdate
    public void onUpdate() {
        this.updatedAt = LocalDateTime.now();
        if (this.engagementRate == null) this.engagementRate = BigDecimal.ZERO;
    }
}