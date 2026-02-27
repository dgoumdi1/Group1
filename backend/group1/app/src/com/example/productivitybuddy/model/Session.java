package com.example.productivitybuddy.model;

import java.time.Duration;
import java.time.Instant;

import javax.persistence.*;

@Entity
public class Session {

    //variables for session
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "start_time", nullable = false)
    private Instant startTime;

    @Column(name = "end_time", nullable = false)
    private Instant endTime;

    @Column(name = "duration_minutes")
    private Long durationMinutes;

    public Session(Instant startTime) {
        this.startTime = startTime;
    }

    public Long getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public Instant getStartTime() {
        return startTime;
    }

    public Instant getEndTime() {
        return endTime;
    }

    public Long getDurationMinutes() {
        return durationMinutes;
    }

    public void setEndTime(Instant endTime) {
        this.endTime = endTime;
        this.durationMinutes = Duration.between(startTime,endTime).toMinutes();
    }
}
