package org.revature.revconnect.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "user_settings")
public class UserSettings {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false, unique = true)
    private User user;

    private Boolean notifyConnectionRequest = true;
    private Boolean notifyConnectionAccepted = true;
    private Boolean notifyNewFollower = true;
    private Boolean notifyLike = true;
    private Boolean notifyComment = true;
    private Boolean notifyShare = true;
    private Boolean emailNotifications = true;

    private LocalDateTime updatedAt = LocalDateTime.now();
}
