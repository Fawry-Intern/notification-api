package com.fawry.notificationapi.model;

import com.fawry.kafka.events.enums.EventStatus;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;
import java.time.LocalDateTime;

import static jakarta.persistence.EnumType.STRING;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Entity
@Table(name = "failed_register_events")
public class FailedRegisterEvent {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "username", nullable = false, unique = true)
    private String username;

    @Column(name = "topic_name", nullable = false)
    private String topic;

    @Column(name = "order_message")
    private long offset;

    @Enumerated(STRING)
    private EventStatus eventStatus = EventStatus.FAILED;

    private int retryCount = 0;

    @CreationTimestamp
    @Column(updatable = false)
    private Instant createdAt = Instant.now();

    @UpdateTimestamp
    private LocalDateTime lastRetry;

    @Override
    public String toString() {
        return "FailedRegisterEvent{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", username='" + username + '\'' +
                ", topic='" + topic + '\'' +
                ", offset=" + offset +
                ", eventStatus=" + eventStatus +
                ", retryCount=" + retryCount +
                ", createdAt=" + createdAt +
                ", lastRetry=" + lastRetry +
                '}';
    }
}
