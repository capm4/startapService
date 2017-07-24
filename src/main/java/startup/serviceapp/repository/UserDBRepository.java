package startup.serviceapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import startup.serviceapp.model.UserDB;

/**
 * repository for {@link UserDB}
 *
 * @author Alexander Kruglov
 * @version 1.0
 */

@Repository
public interface UserDBRepository extends JpaRepository<UserDB, Long >{
	UserDB findByLogin(String login);
}
