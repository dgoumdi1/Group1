package org.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.Instant;
import java.time.temporal.ChronoUnit;

@Service
public class SessionService {

    @Autowired
    private SessionRepository sessionRepository;

    public Session startSession(User user) {
        sessionRepository.findFirstByUserAndEndTimeIsNullOrderByStartTimeDesc(user)
                .ifPresent(s -> { throw new RuntimeException("A session is already active!"); });

        Session session = new Session(Instant.now());
        session.setUser(user);
        return sessionRepository.save(session);
    }

    public Session stopSession(User user) {
        Session session = sessionRepository.findFirstByUserAndEndTimeIsNullOrderByStartTimeDesc(user)
                .orElseThrow(() -> new RuntimeException("No active session found."));

        session.setEndTime(Instant.now());
        return sessionRepository.save(session);
    }

    public Long getTotalMinutesToday(User user) {
        Instant startOfDay = Instant.now().truncatedTo(ChronoUnit.DAYS);
        Long total = sessionRepository.sumDurationSince(user, startOfDay);
        return (total != null) ? total : 0L;
    }

    public Long getTotalMinutesThisWeek(User user) {
        Instant sevenDaysAgo = Instant.now().minus(7, ChronoUnit.DAYS);
        Long total = sessionRepository.sumDurationSince(user, sevenDaysAgo);
        return (total != null) ? total : 0L;
    }
}
