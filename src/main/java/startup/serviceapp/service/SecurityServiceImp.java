package startup.serviceapp.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;


/**
 * Service for Security
 *
 * @author Alexander Kruglov
 * @version 1.0
 */

@Service
public class SecurityServiceImp implements SecurityService {
	private static final Logger logger = LoggerFactory.getLogger(SecurityServiceImp.class);

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private UserDetailsService userDetailsService;
	@Override
	public String getLoggedUserLogin() {
		Object userDetails = SecurityContextHolder.getContext().getAuthentication().getDetails();
		if (userDetails instanceof UserDetails) {
			return ((UserDetails) userDetails).getUsername();
		}
		return null;
	}

	@Override
	public void autoLogin(String login, String password) {
		UserDetails userDetails = userDetailsService.loadUserByUsername(login);
		UsernamePasswordAuthenticationToken authenticationToken =
				new UsernamePasswordAuthenticationToken(userDetails, password, userDetails.getAuthorities());

		authenticationManager.authenticate(authenticationToken);

		if (authenticationToken.isAuthenticated()) {
			SecurityContextHolder.getContext().setAuthentication(authenticationToken);

			logger.debug(String.format("Successfully %s auto logged in", login));
		}
	}
}
