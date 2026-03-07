package org.example;

import jakarta.persistence.*;
import java.time.Instant;

@Entity
@Table(name = "user_group",
    uniqueConstraints = {
        @UniqueConstraint(columnNames = {"user_id","group_id"})
    }
)
public class UserGroup {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "group_id")
    private Group group;

    @Column(nullable=false)
    private Instant joinedTime;

    public UserGroup () {
        this.joinedTime = Instant.now();
    }

    public Long getId () { return id; }
    public User getUser () { return user; }
    public Group getGroup () { return group; }
    public Instant getJoinedTime () { return joinedTime; }
}
