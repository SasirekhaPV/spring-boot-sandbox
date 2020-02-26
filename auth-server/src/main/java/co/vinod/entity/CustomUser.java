package co.vinod.entity;

import org.springframework.security.core.userdetails.User;

import lombok.Getter;
import lombok.Setter;

// Decorator for UserEntity, required by AuthenticationManager
@Getter
@Setter
public class CustomUser extends User {
	private static final long serialVersionUID = 1L;

	private Integer id;
	private String firstName;
	private String lastName;
	private String mobile;
	private String country;
	private String userType;

	public CustomUser(UserEntity userEntity) {
		super(userEntity.getEmail(), userEntity.getPassword(), userEntity.getGrantedAuthorities());
		this.id = userEntity.getId();
		this.firstName = userEntity.getFirstName();
		this.lastName = userEntity.getLastName();
		this.mobile = userEntity.getMobile();
		this.country = userEntity.getCountry();
		this.userType = userEntity.getUserType();
	}
}
