package poly.agile.webapp.service.user;

import java.util.Date;

import org.springframework.security.core.userdetails.UserDetailsService;

import poly.agile.webapp.model.User;
import poly.agile.webapp.service.BaseService;

public interface UserService extends UserDetailsService, BaseService<User, Integer> {

	User findUserById(Integer id);

	User findByUsername(String username);

	User findByEmail(String email);

	User findByPhoneNumber(String phoneNumber);
	
	void updateProfile(String username, String fullname, String address, Date birthdate, Boolean gender);
	
	void setEnabledUser(Integer userId, boolean enabled);
	

}
