package example.server.repository.mainReps;

import example.server.entity.Role;
import example.server.entity.enums.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.access.prepost.PreAuthorize;

import java.util.Optional;


public interface RoleRepository extends JpaRepository<Role, Integer> {
    Optional<Role> findByRoleName(RoleName roleName);

    @PreAuthorize("hasRole('DEVELOPER')")
    @Override
    void deleteById(Integer integer);
}
