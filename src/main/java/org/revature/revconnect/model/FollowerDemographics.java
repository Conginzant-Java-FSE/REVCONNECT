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
        name = "follower_demographics",
        indexes = {
                @Index(name = "idx_user_id", columnList = "user_id"),
                @Index(name = "idx_total_followers", columnList = "total_followers")
        }
)
public class FollowerDemographics {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false, unique = true)
    private User user;

    @Column(name = "total_followers")
    private Integer totalFollowers = 0;


    @Lob
    @Column(name = "location_data", columnDefinition = "JSON")
    private String locationData;

    @Lob
    @Column(name = "age_distribution", columnDefinition = "JSON")
    private String ageDistribution;

    @Lob
    @Column(name = "gender_distribution", columnDefinition = "JSON")
    private String genderDistribution;

    @Lob
    @Column(name = "top_locations", columnDefinition = "JSON")
    private String topLocations;


    @Column(name = "growth_rate", precision = 5, scale = 2)
    private BigDecimal growthRate = BigDecimal.ZERO;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @PrePersist
    @PreUpdate
    public void onUpdate() {
        this.updatedAt = LocalDateTime.now();
        if (this.totalFollowers == null) this.totalFollowers = 0;
        if (this.growthRate == null) this.growthRate = BigDecimal.ZERO;
    }
}