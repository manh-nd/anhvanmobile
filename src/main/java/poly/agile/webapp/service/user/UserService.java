package poly.agile.webapp.service.user;

import org.springframework.security.core.userdetails.UserDetailsService;

import poly.agile.webapp.dto.UserDTO;
import poly.agile.webapp.model.User;
import poly.agile.webapp.service.BaseService;
import poly.agile.webapp.service.DTOService;

public interface UserService extends UserDetailsService, BaseService<User, Integer>, DTOService<UserDTO> {

	User findUserById(Integer id);

	User findByUsername(String username);

	User findByEmail(String email);

	User findByPhoneNumber(String phoneNumber);

	boolean disableUser(String username, boolean enabled);

	boolean changePassword(String username, String password);

	boolean changeFullName(String username, String fullname);

	boolean changeAddress(String username, String address);

	boolean changeGender(String username, boolean gender);
	
	long getTotalUser();

}
