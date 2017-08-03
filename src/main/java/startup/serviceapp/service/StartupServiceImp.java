package startup.serviceapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import startup.serviceapp.model.Startup;
import startup.serviceapp.model.StartupEvaluation;
import startup.serviceapp.model.UserDB;
import startup.serviceapp.repository.StartupRepository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Service for {@link startup.serviceapp.model.Startup}
 *
 * @author Alexander Kruglov
 * @version 1.0
 */

public class StartupServiceImp implements StartupService {

	private StartupRepository startupRepository;

	@Autowired
	public StartupServiceImp(StartupRepository startupRepository) {
		this.startupRepository = startupRepository;
	}

	@Override
	@Transactional
	public void save(Startup startup) {
		startup.setStatus("Draft");
		startupRepository.save(startup);
	}

	@Override
	@Transactional
	public void edit(Startup startup) {
		startup.setStatus("Draft");
		startupRepository.save(startup);
	}

	@Override
	@Transactional
	public void delete(Startup startup) {
		startupRepository.delete(startup);
	}

	@Override
	@Transactional
	public void deleteById(long id) {
		startupRepository.delete(id);
	}

	@Override
	@Transactional
	public void ready(long id) {
		Startup startup = startupRepository.findById(id);
		startup.setStatus("Ready for approve");
		startupRepository.save(startup);
	}

	@Override
	@Transactional
	public void approve(long id) {
		Startup startup = startupRepository.findById(id);
		startup.setStatus("Approved");
		startupRepository.save(startup);
	}

	@Override
	@Transactional
	public void reject(long id) {
		Startup startup = startupRepository.findById(id);
		startup.setStatus("Rejected");
		startupRepository.save(startup);
	}

	@Override
	@Transactional
	public void close(long id) {
		Startup startup = startupRepository.findById(id);
		startup.setStatus("Closed");
		startup.setCurrnet_investment(0);
		startupRepository.save(startup);
	}

	@Override
	@Transactional
	public Startup findByName(String name) {
		return startupRepository.findByName(name);
	}

	@Override
	@Transactional
	public List<Startup> getAllSrtartups() {
		return startupRepository.findAll();
	}

	@Override
	public List<Startup> getAllApproveStartups() {
		List<Startup> startups;
		startups = this.getAllSrtartups().stream()
				.filter(startup -> startup.getStatus().equalsIgnoreCase("Approve"))
				.collect(Collectors.toList());
		return startups;
	}

	@Override
	public List<Startup> getAllBusinessStartups() {
		List<Startup> startups;
		startups = this.getAllSrtartups().stream()
				.filter(startup -> startup.getCategory().getName().equalsIgnoreCase("Business (shares) for sale"))
				.collect(Collectors.toList());
		return startups;
	}

	@Override
	public List<Startup> getAllInvestmentStartups() {
		List<Startup> startups;
		startups = this.getAllSrtartups().stream()
				.filter(startup -> startup.getCategory().getName().equalsIgnoreCase("Investment projects"))
				.collect(Collectors.toList());
 		return startups;
	}

	@Override
	public List<Startup> getAllMobileStartups() {
		List<Startup> startups;
		startups = this.getAllSrtartups().stream()
				.filter(startup -> startup.getCategory().getName().equalsIgnoreCase("Mobile applications"))
				.collect(Collectors.toList());
		return startups;
	}


	/**
	 * This method gets Set of {@link Startup} by {@link UserDB}
	 * and puts it into the List of {@link Startup}
	 */
	@Override
	@Transactional
	public List<Startup> getStartupsByUser(UserDB userDB) {
		List<Startup> startups = new ArrayList<>();
		startups.addAll(userDB.getStartups());
		return startups;
	}

	@Override
	public List<Startup> getStartupsByName(String name) {
		List<Startup> startups;
		startups = this.getAllSrtartups().stream()
				.filter(startup -> startup.getName().equalsIgnoreCase(name))
				.collect(Collectors.toList());
		return startups;
	}

	@Override
	public Collection<Startup> findAllByKeyWord(String key) {
		Collection<Startup> startups;
		if(key.isEmpty()){
			startups = getAllApproveStartups();
		}else {
			startups = startupRepository.findByKeyWorld(key);
		}
		return startups;
	}

	@Override
	public Startup getStartupById(long id) {
		return startupRepository.findById(id);
	}

	@Override
	public Double averageRating(long id) {
		double avaregeRating;
		int totalRating = 0;
		Startup startup = startupRepository.findById(id);
		Set<StartupEvaluation> marks = startup.getMarks();
		if(marks.size() == 0){
			return 0.0;
		}
		for (StartupEvaluation mark : marks){
			totalRating += mark.getMark();
		}
		avaregeRating = (double) totalRating/marks.size();
	/**
	* rounding averageRating to 1 decimal place
	*/
		avaregeRating = roundToOneDecimal(avaregeRating);
		return avaregeRating;
	}

	@Override
	public int votesCount(long id) {
		Startup startup = startupRepository.findById(id);
		return startup.getMarks().size();
	}

	@Override
	@Transactional
	public void invest(long startupId, int investment) {
		Startup startup = startupRepository.findById(startupId);
		startup.setCurrnet_investment(startup.getCurrnet_investment() + investment);
		startupRepository.save(startup);
	}

	/**
	 * This method rounds any double variable to one decimal
	 *
	 * @param d the variable to round
	 * @return the result of rounding
	 */
	private double roundToOneDecimal(double d){
		d = d*10;
		d= Math.round(d);
		d = d/10;
		return 10;
	}
}
