package com.example;

import java.time.Duration;
import java.time.Instant;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import org.junit.jupiter.api.Test;

import com.example.model.Session;


public class SessionTest {
    @Test
    void testSessionConstructor() {
        Instant startTime = Instant.now();
        Session session = new Session(startTime);

        assertEquals(startTime,session.getStartTime());
    } 

    @Test
    void testSettersAndGetters() {
        Instant startTime = Instant.now();
        Session session = new Session(startTime);

        Instant endTime = Instant.now();
        session.setEndTime(endTime);

        assertEquals(session.getEndTime(),endTime);
        assertEquals(session.getStartTime(),startTime);
        assertEquals(session.getDurationMinutes(),Duration.between(startTime,endTime).toMinutes());
    }

    @Test
    void testNullFields() {
        Instant startTime = Instant.now();
        Session session = new Session(startTime);

        assertNull(session.getId());
        //assertNull(session.getUser());
    }
}
