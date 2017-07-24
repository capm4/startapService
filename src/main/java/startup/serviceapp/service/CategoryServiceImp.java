package startup.serviceapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import startup.serviceapp.model.Category;
import startup.serviceapp.repository.CategoryRepository;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Service for {@link startup.serviceapp.model.Category}
 *
 * @author  Alexander Kruglov
 * @version 1.0
 */

@Service
public class CategoryServiceImp implements CategoryService {
	private CategoryRepository categoryRepository;

	@Autowired
	public CategoryServiceImp(CategoryRepository categoryRepository) {
		this.categoryRepository = categoryRepository;
	}


	@Override
	public List<Category> getAllCategories() {
		return categoryRepository.findAll();
	}

	@Override
	public Category getCategoryById(long id) {
		return categoryRepository.findOne(id);
	}

	@Override
	public Category getCategoryByName(String name) {
		return this.categoryRepository.findByName(name);
	}

	@Override
	public List<String> getAllCategoryNames() {
		List<String> categoryNames;
		categoryNames = this.getAllCategories().stream()
				.map(Category::getName)
				.collect(Collectors.toList());
		return categoryNames;
	}
}
