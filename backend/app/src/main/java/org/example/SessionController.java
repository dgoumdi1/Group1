package org.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/sessions")
public class SessionController {

    @Autowired
    private SessionService sessionService;

    @Autowired
    private UserRepository userRepository;

    private User getCurrentUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return userRepository.findByEmail(auth.getName())
                .orElseThrow(() -> new RuntimeException("User not authenticated"));
    }

    @PostMapping("/start")
    public Session start() {
        return sessionService.startSession(getCurrentUser());
    }

    @PostMapping("/stop")
    public Session stop() {
        return sessionService.stopSession(getCurrentUser());
    }

    @GetMapping("/today")
    public Long getToday() {
        return sessionService.getTotalMinutesToday(getCurrentUser());
    }

    @GetMapping("/week")
    public Long getWeek() {
        return sessionService.getTotalMinutesThisWeek(getCurrentUser());
    }
}
