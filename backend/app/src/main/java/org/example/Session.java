package org.example;

import java.time.Duration;
import java.time.Instant;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Session {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "start_time", nullable = false)
    private Instant startTime;

    @Column(name = "end_time")
    private Instant endTime;

    @Column(name = "duration_minutes")
    private Long durationMinutes;

    public Session() {
        this.startTime = Instant.now();
    }

    public Session(Instant startTime) {
        this.startTime = startTime;
    }

    public Long getId() { return id; }
    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }
    public Instant getStartTime() { return startTime; }
    public Instant getEndTime() { return endTime; }
    public Long getDurationMinutes() { return durationMinutes; }

    public void setEndTime(Instant endTime) {
        this.endTime = endTime;
        this.durationMinutes = Duration.between(startTime, endTime).toMinutes();
    }
}
