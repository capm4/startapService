package startup.serviceapp.service;

import startup.serviceapp.model.Startup;
import startup.serviceapp.model.UserDB;

import java.util.Collection;
import java.util.List;

/**
 * Interface for {@link startup.serviceapp.model.Startup}' service
 *
 * @author Alexander Kruglov
 * @version 1.0
 */

public interface StartupService {
	void save (Startup startup);

	void edit (Startup startup);

	void delete (Startup startup);

	void deleteById (long id);

	void ready (long id);

	void approve (long id);

	void reject (long id);

	void close (long id);

	Startup findByName (String name);

	List<Startup> getAllSrtartups();

	List<Startup> getAllApproveStartups();

	List<Startup> getAllBusinessStartups();

	List<Startup> getAllInvestmentStartups();

	List<Startup> getAllMobileStartups();

	List<Startup> getStartupsByUser(UserDB userDB);

	List<Startup> getStartupsByName (String name);

	Collection<Startup> findAllByKeyWord(String key);

	Startup getStartupById(long id);

	Double averageRating(long id);

	int votesCount(long id);

	void invest (long startupId, int investment);
}
