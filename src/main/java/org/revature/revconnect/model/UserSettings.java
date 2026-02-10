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
        name = "user_settings",
        indexes = {
                @Index(name = "idx_user_id", columnList = "user_id")
        }
)
public class UserSettings {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // user_id is UNIQUE in DB (one settings row per user)
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false, unique = true)
    private User user;

    @Column(name = "notify_connection_request")
    private Boolean notifyConnectionRequest = true;

    @Column(name = "notify_connection_accepted")
    private Boolean notifyConnectionAccepted = true;

    @Column(name = "notify_new_follower")
    private Boolean notifyNewFollower = true;

    @Column(name = "notify_like")
    private Boolean notifyLike = true;

    @Column(name = "notify_comment")
    private Boolean notifyComment = true;

    @Column(name = "notify_share")
    private Boolean notifyShare = true;

    @Column(name = "email_notifications")
    private Boolean emailNotifications = true;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @PrePersist
    public void onCreate() {
        this.updatedAt = LocalDateTime.now();
        if (notifyConnectionRequest == null) notifyConnectionRequest = true;
        if (notifyConnectionAccepted == null) notifyConnectionAccepted = true;
        if (notifyNewFollower == null) notifyNewFollower = true;
        if (notifyLike == null) notifyLike = true;
        if (notifyComment == null) notifyComment = true;
        if (notifyShare == null) notifyShare = true;
        if (emailNotifications == null) emailNotifications = true;
    }

    @PreUpdate
    public void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }
}

