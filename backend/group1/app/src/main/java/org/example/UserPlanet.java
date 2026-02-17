package org.example;

import javax.persistence.*;

@Entity
public class UserPlanet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Many UserPlanet rows point to one User
    @ManyToOne(optional = false)
    private User user;

    // Many UserPlanet rows point to one Planet
    @ManyToOne(optional = false)
    private Planet planet;

    public UserPlanet() {}

    public UserPlanet(User user, Planet planet) {
        this.user = user;
        this.planet = planet;
    }

    public Long getId() { return id; }

    public User getUser() { return user; }

    public void setUser(User user) { this.user = user; }

    public Planet getPlanet() { return planet; }

    public void setPlanet(Planet planet) { this.planet = planet; }
}

