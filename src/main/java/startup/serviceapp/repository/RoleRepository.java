package startup.serviceapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import startup.serviceapp.model.Role;

/**
 * Repository for {@link Role}
 *
 * @author Alexander Kruglov
 * @version 1.0
 */

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
}
