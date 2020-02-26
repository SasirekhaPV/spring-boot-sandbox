package co.vinod.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import co.vinod.dao.UserRepository;
import co.vinod.entity.UserEntity;

@Service
public class UserService {

	@Autowired
	UserRepository userRepository;

	public UserEntity getUserDetails(String email) {
		UserEntity userEntity = userRepository.findByEmail(email);
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();

		if (userEntity != null && userEntity.getUserType().equalsIgnoreCase("super_admin")) {
			authorities.add(new SimpleGrantedAuthority("ROLE_SYSTEMADMIN"));
		} else {
			List<String> permissions = userRepository.getPermissions(email);
			if (permissions != null && permissions.isEmpty() == false) {
				for (String permission : permissions) {
					authorities.add(new SimpleGrantedAuthority(permission));
				}
			}
		}

		userEntity.setGrantedAuthorities(authorities);
		return userEntity;
	}

}
