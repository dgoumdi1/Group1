package org.example;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.time.Instant;
import java.util.Optional;

public interface SessionRepository extends JpaRepository<Session, Long> {
    Optional<Session> findByStartTime(Instant startTime);
    Optional<Session> findByEndTime(Instant endTime);
    Optional<Session> findByUser(User user);
    Optional<Session> findByDurationMinutes(Long durationMinutes);
    Optional<Session> findFirstByUserAndEndTimeIsNullOrderByStartTimeDesc(User user);

    @Query("SELECT SUM(s.durationMinutes) FROM Session s WHERE s.user = :user AND s.startTime >= :since")
    Long sumDurationSince(@Param("user") User user, @Param("since") Instant since);
}
