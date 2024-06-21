package EksamenProgrammeringBackend.security.repository;

import EksamenProgrammeringBackend.security.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, String> {
}
