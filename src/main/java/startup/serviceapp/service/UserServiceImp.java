package startup.serviceapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import startup.serviceapp.model.Role;
import startup.serviceapp.model.UserDB;
import startup.serviceapp.repository.UserDBRepository;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Implements of {@link UserService}
 *
 * @author Alexander Kruglov
 * @version 1.0
 */

@Service
public class UserServiceImp implements UserService {
	@Autowired
	private UserDBRepository userDBRepository;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Override
	public void save(UserDB userDB) {
		userDB.setPassword(bCryptPasswordEncoder.encode(userDB.getPassword()));
		userDBRepository.save(userDB);
	}

	@Override
	public void saveWithoutEncode(UserDB userDB) {
		userDBRepository.save(userDB);
	}

	@Override
	@Transactional
	public UserDB findByLogin(String login) {
		return userDBRepository.findByLogin(login);
	}

	@Override
	@Transactional(readOnly = true)
	public UserDB getAuthenticatedUser(HttpServletRequest request) {
		UserDB userDB;
		HttpSession session = request.getSession(true);
		SecurityContextImpl sci = (SecurityContextImpl) session.getAttribute("SPRING_SECURITY_CONTEXT");
		try{
			UserDetails userDetails = (UserDetails) sci.getAuthentication().getPrincipal();
			userDB = userDBRepository.findByLogin(userDetails.getUsername());
			return userDB;
		}catch (NullPointerException e){
			return new UserDB();
		}
	}

	@Override
	@Transactional(readOnly = true)
	public boolean isAuthenticated(HttpServletRequest request) {
		HttpSession session = request.getSession(true);
		SecurityContextImpl sci = (SecurityContextImpl) session.getAttribute("SPRING_SECURITY_CONTEXT");
		try {
			return sci.getAuthentication().isAuthenticated();
		}catch (NullPointerException e){
			return false;
		}
	}

	@Override
	@Transactional(readOnly = true)
	public boolean isAdmin(HttpServletRequest request) {
		UserDB user = getAuthenticatedUser(request);
		return user.getRoles().contains(new Role("ROLE_ADMIN"));
	}

	@Override
	public boolean isStartupOwner(long id, HttpServletRequest request) {
		UserDB user = getAuthenticatedUser(request);
		return user.getStartups().stream()
				.allMatch(p -> p.getId() == id);
	}

	public void setUserDBRepository(UserDBRepository userDBRepository) {
		this.userDBRepository = userDBRepository;
	}

	public void setbCryptPasswordEncoder(BCryptPasswordEncoder bCryptPasswordEncoder) {
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
	}
}
