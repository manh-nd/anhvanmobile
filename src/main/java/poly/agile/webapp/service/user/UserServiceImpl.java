package poly.agile.webapp.service.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import poly.agile.webapp.model.User;
import poly.agile.webapp.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByUsername(username);
		if (user == null)
			throw new UsernameNotFoundException("User not found!");
		return user;
	}

	@Override
	public User findUserById(Integer id) {
		return userRepository.getOne(id);
	}

	@Override
	public User create(User object) {
		return null;
	}

	@Override
	public User update(User object) {
		return null;
	}

	@Override
	public boolean remove(User object) {
		return false;
	}

	@Override
	public User findById(Integer id) {
		return null;
	}

	@Override
	public List<User> findAll() {
		return null;
	}

}
