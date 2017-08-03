package startup.serviceapp.serviceTest;

import org.junit.Before;
import org.junit.Test;
import startup.serviceapp.model.Startup;
import startup.serviceapp.repository.StartupRepository;
import startup.serviceapp.service.StartupService;
import startup.serviceapp.service.StartupServiceImp;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
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


}
