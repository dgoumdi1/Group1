package org.example;

import org.springframework.data.jpa.repository.JpaRepository;
import java.time.Instant;
import java.util.Optional;

public interface SessionRepository extends JpaRepository<Session, Long> {
    Optional<Session> findByStartTime(Instant startTime);
    Optional<Session> findByEndTime(Instant endTime);
    Optional<Session> findByUser(User user);
    Optional<Session> findByDurationMinutes(Long durationMinutes);
}
