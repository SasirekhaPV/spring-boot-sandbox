package co.vinod.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import co.vinod.entity.UserEntity;

@Repository
public interface UserRepository extends CrudRepository<UserEntity, Integer> {
	
	UserEntity findByEmail(String email);
	
	
	@Query(nativeQuery = true, value = "SELECT DISTINCT P.PERMISSION_NAME FROM PERMISSIONS P \n" + 
			"	INNER JOIN ASSIGN_PERMISSION_TO_ROLE P_R ON P.ID=P_R.PERMISSION_ID\n" + 
			"	INNER JOIN ROLES R ON R.ID=P_R.ROLE_ID \n" + 
			"	INNER JOIN ASSIGN_USER_TO_ROLE U_R ON U_R.ROLE_ID=R.ID\n" + 
			"	INNER JOIN USERS U ON U.ID=U_R.USER_ID\n" + 
			"	WHERE U.EMAIL_ID=?1")
	List<String> getPermissions(String email);
}
