package com.weerayosong.clinical_triage_backend.model;

import jakarta.persistence.*;
import lombok.Data; // Lombok help with Getter/Setter
import java.time.LocalDateTime;

@Entity
@Table(name = "patients")
@Data // Lombok annotation
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String name;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String symptoms;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private UrgencyLevel urgency;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private Status status;

    @Column(name = "registered_at", updatable = false)
    private LocalDateTime registeredAt;

    // auto create date
    @PrePersist
    protected void onCreate() {
        this.registeredAt = LocalDateTime.now();
        if (this.status == null) {
            this.status = Status.WAITING; // default status
        }
    }

    // enum
    public enum UrgencyLevel {
        NORMAL, URGENT, EMERGENCY
    }

    public enum Status {
        WAITING, IN_TREATMENT, DISCHARGED
    }
}