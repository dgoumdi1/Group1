package org.example;

import java.time.Instant;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserGroupRepository extends JpaRepository<UserGroup,Long> {
    Optional<UserGroup> findById (Long id);
    Optional<UserGroup> findByUser (User user);
    Optional<UserGroup> findByGroup (Group group);
    Optional<UserGroup> findByJoinedTime (Instant joinedTime);
    boolean existsByUserAndGroup(User user, Group group);
}
