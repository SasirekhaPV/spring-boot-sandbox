package co.vinod.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import co.vinod.entity.CustomUser;
import co.vinod.entity.UserEntity;

// Decorator for UserService, required by AuthenticationManager
@Service
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	UserService userService;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		try {
			UserEntity user = userService.getUserDetails(username);

			if (user != null && user.getId() != null) {
				CustomUser customUser = new CustomUser(user);
				return customUser;
			} else {
				throw new UsernameNotFoundException("User with email " + username + " not found!");
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new UsernameNotFoundException(e.getLocalizedMessage());
		}

	}

}
