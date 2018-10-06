package poly.agile.webapp.service.user;

import org.springframework.security.core.userdetails.UserDetailsService;

import poly.agile.webapp.model.User;
import poly.agile.webapp.service.BaseService;

public interface UserService extends UserDetailsService, BaseService<User, Integer> {

	User findUserById(Integer id);
	
}
