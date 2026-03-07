package org.example;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.Instant;
import java.util.Optional;

public interface SessionRepository extends JpaRepository<Session, Long> {
    Optional<Session> findByStartTime(Instant startTime);
    Optional<Session> findByEndTime(Instant endTime);
    Optional<Session> findByUser(User user);
    Optional<Session> findByDurationMinutes(Long durationMinutes);


    // 2. NEW: Find the most recent active session (where end_time is null)
    // This is used for the /stop endpoint
    Optional<Session> findFirstByUserAndEndTimeIsNullOrderByStartTimeDesc(User user);

    // 3. NEW: Sum durationMinutes for a user since a specific time
    // This is used for /today and /week endpoints
    @Query("SELECT SUM(s.durationMinutes) FROM Session s WHERE s.user = :user AND s.startTime >= :since")
    Long sumDurationSince(@Param("user") User user, @Param("since") Instant since);
}
