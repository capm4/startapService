package startup.serviceapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import startup.serviceapp.model.Startup;

import java.util.Collection;

/**
 * Repository for {@link Startup}
 *
 * @author Alexandr Kruglov
 * @version 1.0
 */

@Repository
public interface StartupRepository extends JpaRepository<Startup, Long> {
	Startup findByName (String name);
	Startup findById (long id);

	@Query("SELECT s from startup s where s.name like %:key% or s.discription like %:key%")
	Collection<Startup> findByKeyWorld(@Param("key") String key);
}
