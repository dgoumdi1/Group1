package org.example;

import jakarta.persistence.*;
import java.time.Instant;

@Entity
public class Group {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable=false, unique=true)
    private String name;

    @ManyToOne
    @JoinColumn(name="owner_id")
    private User owner;

    @Column(nullable=false, name="created_at")
    private Instant creationTime;

    public Group() {}

    public Group(String name) {
        this.name = name;
        this.creationTime = Instant.now();
    }

    public Long getId() { return id; }
    public String getName() { return name; }
    public User getOwner() { return owner; }
    public Instant getCreationTime () { return creationTime; }

    public void setName(String name) {
        this.name = name;
    }
}
