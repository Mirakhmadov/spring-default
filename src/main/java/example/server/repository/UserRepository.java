package example.server.repository;

import example.server.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {

    Optional<User> findByEmailEqualsIgnoreCase(String email);

    boolean existsByEmailEqualsIgnoreCase(String email);
}