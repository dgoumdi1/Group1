package org.example;

import org.springframework.data.jpa.repository.JpaRepository;
import java.time.Instant;
import java.util.Optional;

public interface GroupRepository extends JpaRepository<Group,Long>{
    Optional<Group> findById(Long id);
    Optional<Group> findByName(String name);
    Optional<Group> findByOwner(User owner);
    Optional<Group> findByCreationTime(Instant creationTime);
}
