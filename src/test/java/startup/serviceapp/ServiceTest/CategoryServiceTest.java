package startup.serviceapp.ServiceTest;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import startup.serviceapp.model.Category;
import startup.serviceapp.repository.CategoryRepository;
import startup.serviceapp.service.CategoryService;
import startup.serviceapp.service.CategoryServiceImp;

import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.*;


/**
 * Test for {@link startup.serviceapp.service.CategoryServiceImp}
 *
 * @author Alexandr Kruglov
 * @version 1.0
 */


public class CategoryServiceTest {
	CategoryService categoryServiceImp;
	CategoryRepository categoryRepository;

	@Before
	public void init(){
		categoryRepository = mock(CategoryRepository.class);
		categoryServiceImp = new CategoryServiceImp(categoryRepository);
	}


	@Test
	public void FindByNameTest(){
		Category category = mock(Category.class);
		when(categoryRepository.findByName(anyString())).thenReturn(category);

		Category foundCategory = categoryServiceImp.getCategoryByName("Mobile applications");

		verify(categoryRepository, times(1)).findByName(anyString());

		Assert.assertEquals(category,foundCategory);
	}
}
