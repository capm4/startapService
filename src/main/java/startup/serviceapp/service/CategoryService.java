package startup.serviceapp.service;

import startup.serviceapp.model.Category;

import java.util.List;

/**
 * Interface for {@link startup.serviceapp.model.Category}' service
 *
 * @author  Alexander Kruglov
 * @version 1.0
 */

public interface CategoryService {
	List<Category> getAllCategories();

	Category getCategoryById(long id);

	Category getCategoryByName(String name);

	List<String> getAllCategoryNames();
}
