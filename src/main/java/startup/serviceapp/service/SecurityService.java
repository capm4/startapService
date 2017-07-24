package startup.serviceapp.service;

/**
 * interface for {@link SecurityServiceImp}
 *
 * @author Alexandr Kruglov
 * @version 1.0
 */

public interface SecurityService {
	String getLoggedUserLogin();
	void autoLogin(String login, String password);
}
