package startup.serviceapp.service;

import org.springframework.stereotype.Service;
import startup.serviceapp.model.UserDB;

import javax.servlet.http.HttpServletRequest;

/**
 * Service class for {@link startup.serviceapp.model.UserDB}
 *
 * @author Aleksander Kruglov
 * @version 1.0
 */

@Service
public interface UserService {
	void save(UserDB userDB);
	void saveWithoutEncode(UserDB userDB);
	UserDB findByLoggin(String loggin);
	public UserDB getAuthenticatedUser(HttpServletRequest request);
	public boolean isAuthenticated(HttpServletRequest request);
	public boolean isAdmin(HttpServletRequest request);
	public boolean isStartupOwner(long id, HttpServletRequest request);
}
