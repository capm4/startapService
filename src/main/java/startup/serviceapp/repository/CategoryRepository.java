package startup.serviceapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import startup.serviceapp.model.Category;


/**
 * Repository for {@link startup.serviceapp.model.Category}
 *
 * @author Alexandr Kruglov
 * @version 1.0
 */

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
	Category findByName(String name);
}
