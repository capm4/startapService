package startup.serviceapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.transaction.annotation.Transactional;
import startup.serviceapp.model.Role;
import startup.serviceapp.model.UserDB;
import startup.serviceapp.repository.UserDBRepository;

import java.util.ArrayList;
import java.util.List;

/**
 * Implements of {@link UserDetailsService}
 *
 * @author Aleksandr Kruglov
 * @version 1.0
 */
public class UserDetailsServiceImpl implements UserDetailsService {
	@Autowired
	private UserDBRepository userDBRepository;

	@Override
	@Transactional(readOnly = true)
	public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
		UserDB user = userDBRepository.findByLogin(login);
		List<GrantedAuthority> grantedAuthorityList = new ArrayList<>();
		for(Role role: user.getRoles()){
			grantedAuthorityList.add(new SimpleGrantedAuthority(role.getName()));
		}
		return new User(user.getLogin(), user.getPassword(),grantedAuthorityList);
	}
}
