package org.example;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    // One user can have many UserPlanet rows
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<UserPlanet> userPlanets = new HashSet<>();

    public User() {}

    public User(String name) {
        this.name = name;
    }

    public Long getId() { return id; }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public Set<UserPlanet> getUserPlanets() { return userPlanets; }

    public void addPlanet(Planet planet) {
        UserPlanet up = new UserPlanet(this, planet);
        userPlanets.add(up);
        planet.getUserPlanets().add(up);
    }

    public void removePlanet(Planet planet) {
        userPlanets.removeIf(up -> {
            boolean match = up.getPlanet().equals(planet);
            if (match) {
                planet.getUserPlanets().remove(up);
                up.setUser(null);
                up.setPlanet(null);
            }
            return match;
        });
    }
}
