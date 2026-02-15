package org.example;
import java.time.LocalDateTime;

public class User {
    private Integer id;
    private String username;
    private String email;
    private String passwordHash;
    private Integer currentPlanetId;

    public User(Integer id, String username, String email, String passwordHash, Integer currentPlanetId) {
        //todo: initialize user fields
    }

    public Integer getId() {
        //todo: return user id
        return null;
    }

    public void setId(Integer id) {
        //todo: set user id
    }

    public String getUsername() {
        //todo: return username
        return null;
    }

    public String getEmail() {
        //todo: return email
        return null;
    }

    public String getPasswordHash() {
        //todo: return password hash
        return null;
    }

    public Integer getCurrentPlanetId() {
        //todo: return current planet id
        return null;
    }

    public void updateEmail(String newEmail) {
        //todo: update user email
    }

    public void updatePasswordHash(String newPasswordHash) {
        //todo: update password hash
    }

    public boolean isPasswordHashMatch(String candidateHash) {
        //todo: compare candidate hash with stored hash
        return false;
    }

    public void assignCurrentPlanet(Integer planetId) {
        //todo: assign current planet id
    }

    public String getUserSummary() {
        //todo: return formatted user summary
        return null;
    }

}