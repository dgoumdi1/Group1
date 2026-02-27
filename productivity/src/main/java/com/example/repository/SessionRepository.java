package com.example.productivitybuddy.repository;

import com.example.productivitybuddy.model.Session;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface SessionRepository extends JpaRepository<Session,Long> {
    Optional<Session> findByStartTime(Instant startTime);
    Optional<Session> findByEndTime(Instant endTime);
    Optional<Session> findByUser(User user);
    Optional<Session> findByDurationMinutes(Long durationMinutes);
}
