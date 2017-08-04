package startup.serviceapp.serviceTest;

import org.junit.Before;
import org.junit.Test;
import startup.serviceapp.model.Category;
import startup.serviceapp.model.Startup;
import startup.serviceapp.model.StartupEvaluation;
import startup.serviceapp.model.UserDB;
import startup.serviceapp.repository.StartupRepository;
import startup.serviceapp.service.StartupService;
import startup.serviceapp.service.StartupServiceImp;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

/**
 * Test for {@link startup.serviceapp.service.StartupServiceImp}
 *
 * @author Alexande Kruglov
 * @version 1.0
 */

public class StartupServiceTest {
	StartupService startupService;
	StartupRepository startupRepository;

	@Before
	public void init(){
		startupRepository = mock(StartupRepository.class);
		startupService = new StartupServiceImp(startupRepository);
	}

	@Test
	public void save(){
		// init
		Startup startup = new Startup();
		Startup startupName = new Startup("TestName");
		when(startupRepository.save(startup)).thenReturn(startup);
		when(startupRepository.save(startupName)).thenReturn(startupName);
		//use
		startupService.save(startup);
		startupService.save(startupName);
		//check
		verify(startupRepository, times(1)).save(startup);
		assertEquals("TestName", startupRepository.save(startupName).getName());
	}

	@Test
	public void edit(){
		//init
		Startup startup = new Startup();
		when(startupRepository.save(startup)).thenReturn(startup);
		//use
		startupService.edit(startup);
		//check
		verify(startupRepository, times(1)).save(startup);
		assertEquals("Draft", startupRepository.save(startup).getStatus());
	}

	@Test
	public void delete(){
		//init
		Startup startup = new Startup();
		doAnswer(i->{
			assertEquals(startup, i.getArgumentAt(0, Startup.class));
			return null;
		}).when(startupRepository).delete(startup.getId());
		//use
		startupService.delete(startup);
		//check
		verify(startupRepository, times(1)).delete(startup);
	}

	@Test
	public void deleteById(){
		//init
		Startup startup = new Startup();
		doAnswer(i->{
			assertEquals(startup.getId(), (long) i.getArgumentAt(0, Long.class));
			return null;
		}).when(startupRepository).delete(startup.getId());
		//use
		startupService.deleteById(startup.getId());
		//check
		verify(startupRepository,times(1)).delete(startup.getId());
	}

	@Test
	public void ready(){
		//init
		Startup startup = mock(Startup.class);
		when(startupRepository.findById(anyLong())).thenReturn(startup);
		when(startupRepository.save(startup)).thenReturn(startup);
		//use
		startupService.ready(startup.getId());
		//check
		verify(startupRepository,times(1)).findById(anyLong());
		verify(startup, times(1)).setStatus("Ready for approve");
		verify(startupRepository,times(1)).save(startup);
	}

	@Test
	public void approve(){
		//init
		Startup startup = mock(Startup.class);
		when(startupRepository.findById(anyLong())).thenReturn(startup);
		when(startupRepository.save(startup)).thenReturn(startup);
		//use
		startupService.approve(startup.getId());
		//check
		verify(startupRepository, times(1)).findById(anyLong());
		verify(startup, times(1)).setStatus("Approved");
		verify(startupRepository, times(1)).save(startup);
	}

	@Test
	public void reject(){
		//init
		Startup startup = mock(Startup.class);
		when(startupRepository.findById(anyLong())).thenReturn(startup);
		when(startupRepository.save(startup)).thenReturn(startup);
		//use
		startupService.reject(startup.getId());
		//check
		verify(startupRepository, times(1)).findById(anyLong());
		verify(startup, times(1)).setStatus("Rejected");
		verify(startupRepository,times(1)).save(startup);
	}

	@Test
	public void closed(){
		//init
		Startup startup = new Startup();
		when(startupRepository.findById(anyLong())).thenReturn(startup);
		when(startupRepository.save(startup)).thenReturn(startup);
		//use
		startupService.close(startup.getId());
		//check
		verify(startupRepository,times(1)).findById(anyLong());
		assertEquals(0, startupRepository.save(startup).getCurrnet_investment());
		assertEquals("Closed", startupRepository.save(startup).getStatus());
		verify(startupRepository, times(3)).save(startup);
	}

	@Test
	public void findByName(){
		//init
		Startup startup = new Startup("name");
		when(startupRepository.findByName(anyString())).thenReturn(startup);
		//use
		Startup startapFound = startupService.findByName("name");
		//check
		verify(startupRepository, times(1)).findByName(anyString());
		assertEquals(startup, startapFound);
	}

	@Test
	public void getAllStartaps(){
		//init
		Startup startup = new Startup();
		Startup startup1 = new Startup();
		Startup startup2 = new Startup();
		List<Startup> testList = new ArrayList<>();
		testList.add(startup);
		testList.add(startup1);
		testList.add(startup2);
		when(startupRepository.findAll()).thenReturn(testList);
		//use
		List<Startup> listToCheck = startupService.getAllSrtartups();
		//check
		verify(startupRepository, times(1)).findAll();
		assertEquals(testList, listToCheck);
	}

	@Test
	public void getAllApproveStartaps(){
		//init
		Startup startup = mock(Startup.class);
		Startup startup1 = mock(Startup.class);
		Startup startup2 = mock(Startup.class);
		when(startup.getStatus()).thenReturn("Draft");
		when(startup1.getStatus()).thenReturn("Approve");
		when(startup2.getStatus()).thenReturn("Approve");
		//use
		List<Startup> listToCheck = startupService.getAllApproveStartups();
		//check
		assertTrue(listToCheck.stream().allMatch(p -> p.getStatus().equalsIgnoreCase("Approve")));
		verify(startupRepository, times(1)).findAll();
	}

	@Test
	public void getAllBusinessStartups(){
		//init
		Startup startup1 = mock(Startup.class);
		when(startup1.getStatus()).thenReturn("Approved");
		when(startup1.getCategory()).thenReturn(new Category("Business (shares) for sale"));
		Startup startup2 = mock(Startup.class);
		when(startup2.getStatus()).thenReturn("Approved");
		when(startup2.getCategory()).thenReturn(new Category("Business (shares) for sale"));
		Startup startup3 = mock(Startup.class);
		when(startup3.getStatus()).thenReturn("Approved");
		when(startup3.getCategory()).thenReturn(new Category("Mobile Appliations"));

		List<Startup> testList = new ArrayList<>();
		testList.add(startup1);
		testList.add(startup2);
		testList.add(startup3);

		when(startupRepository.findAll()).thenReturn(testList);
		//use
		List<Startup> listToCheck = startupService.getAllBusinessStartups();
		//check
		assertTrue(listToCheck.stream().allMatch(p->p.getCategory().getName().equalsIgnoreCase("Business (shares) for sale")));
		verify(startupRepository, times(1)).findAll();
	}

	@Test
	public void getAllInvestmentStartups(){
		//init
		Startup startup = mock(Startup.class);
		Startup startup1 = mock(Startup.class);
		Startup startup2 = mock(Startup.class);
		when(startup.getStatus()).thenReturn("Approved");
		when(startup.getCategory()).thenReturn(new Category("Investment projects"));
		when(startup1.getStatus()).thenReturn("Approved");
		when(startup1.getCategory()).thenReturn(new Category("Investment projects"));
		when(startup2.getStatus()).thenReturn("Approved");
		when(startup2.getCategory()).thenReturn(new Category("Mobile Appliations"));
		List<Startup> testList = new ArrayList<>();
		testList.add(startup);
		testList.add(startup1);
		testList.add(startup2);

		when(startupRepository.findAll()).thenReturn(testList);
		//use
		List<Startup> listToCheck = startupService.getAllInvestmentStartups();
		//check
		assertTrue(listToCheck.stream().allMatch(p->p.getCategory().getName().equalsIgnoreCase("Investment projects")));
		verify(startupRepository, times(1)).findAll();
	}

	@Test
	public void getAllMobileStartups(){
		//init
		Startup startup = mock(Startup.class);
		Startup startup1 = mock(Startup.class);
		Startup startup2 = mock(Startup.class);
		when(startup.getStatus()).thenReturn("Approved");
		when(startup.getCategory()).thenReturn(new Category("Mobile applications"));
		when(startup1.getStatus()).thenReturn("Approved");
		when(startup1.getCategory()).thenReturn(new Category("Mobile applications"));
		when(startup2.getStatus()).thenReturn("Approved");
		when(startup2.getCategory()).thenReturn(new Category("Mobile Appliations"));
		List<Startup> testList = new ArrayList<>();
		testList.add(startup);
		testList.add(startup1);
		testList.add(startup2);
		//use
		List<Startup> listToCheck = startupService.getAllMobileStartups();
		//check
		assertTrue(listToCheck.stream().allMatch(p->p.getStatus().equalsIgnoreCase("Mobile applications")));
		verify(startupRepository, times(1)).findAll();
	}

	@Test
	public void getStartupsByUser(){
		//init
		UserDB user = mock(UserDB.class);
		Set<Startup> set = new HashSet<>();
		when(user.getStartups()).thenReturn(set);
		//use
		List<Startup> listToCheck = startupService.getStartupsByUser(user);
		//check
		verify(user, times(1)).getStartups();
		assertThat(listToCheck,  instanceOf(ArrayList.class));
	}

	@Test
	public void getStartupsByName(){
		//init
		Category category = new Category("Test");
		Startup startup1 = new Startup("First",1000, "Test Description", category);
		Startup startup2 = new Startup("Second", 2000,"Test Description",  category);
		Startup startup22 = new Startup("Second", 2222,"Test Description",  category);
		Startup startup3 = new Startup("Third", 3000,"Test Description",  category);

		List<Startup> allStartups = new ArrayList<>();
		allStartups.add(startup1);
		allStartups.add(startup2);
		allStartups.add(startup22);
		allStartups.add(startup3);

		List<Startup> testList = new ArrayList<>();
		testList.add(startup2);
		testList.add(startup22);

		when(startupRepository.findAll()).thenReturn(allStartups);
		//use
		List<Startup> listToCheck = startupService.getStartupsByName("Second");
		//check
		assertTrue(listToCheck.stream().allMatch(p -> p.getName().equalsIgnoreCase("Second")));
		verify(startupRepository, times(1)).findAll();
		assertEquals(testList, listToCheck);
	}

	@Test
	public void getStartupById(){
		//init
		Startup startup = new Startup();
		startup.setId(0);
		when(startupRepository.findById(anyLong())).thenReturn(startup);
		//use
		Startup startupToCheck = startupService.getStartupById(anyLong());
		//check
		verify(startupRepository,times(1)).findById(anyLong());
		assertEquals(0, startupToCheck.getId());
	}

	@Test
	public void averageRating_corretck(){
		//init
		Startup startup = mock(Startup.class);
		when(startupRepository.findById(anyLong())).thenReturn(startup);
		StartupEvaluation startupEvaluation = new StartupEvaluation(mock(UserDB.class), startup, 1);
		StartupEvaluation startupEvaluation1 = new StartupEvaluation(mock(UserDB.class), startup, 2 );

		Set<StartupEvaluation> set = new HashSet<>();
		set.add(startupEvaluation);
		set.add(startupEvaluation1);
		when(startup.getMarks()).thenReturn(set);

		//use
		Double check = startupService.averageRating(anyLong());
		//check

		assertEquals((Double) 1.5 , check);
		verify(startup, times(1)).getMarks();
		verify(startupRepository, times(1)).findById(anyLong());
	}

	@Test
	public void averageRating_empty(){
		//init
		Startup startup = mock(Startup.class);
		Set<StartupEvaluation> set = new HashSet<>();
		when(startupRepository.findById(anyLong())).thenReturn(startup);
		when(startup.getMarks()).thenReturn(set);
		//use
		Double check = startupService.averageRating(anyLong());
		//check
		assertEquals((Double)0.0, check);
		verify(startupRepository,times(1)).findById(anyLong());
		verify(startup, times(1)).getMarks();
	}

	@Test
	public void voiceCounter(){
		//init
		Startup startup = mock(Startup.class);
		Set<StartupEvaluation> set = mock(HashSet.class);
		when(startupRepository.findById(anyLong())).thenReturn(startup);
		when(startup.getMarks()).thenReturn(set);
		when(set.size()).thenReturn(5);
		//use
		int check = startupService.votesCount(anyLong());
		//check
		assertEquals(5, check);
		verify(startupRepository, times(1)).findById(anyLong());
		verify(startup, times(1)).getMarks();
		verify(set, times(1)).size();
	}
}
