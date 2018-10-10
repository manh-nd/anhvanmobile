package poly.agile.webapp.service.user;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import poly.agile.webapp.exception.DuplicateEmailException;
import poly.agile.webapp.exception.DuplicatePhoneNumberException;
import poly.agile.webapp.exception.DuplicateUsernameException;
import poly.agile.webapp.model.Role;
import poly.agile.webapp.model.User;
import poly.agile.webapp.repository.RoleRepository;
import poly.agile.webapp.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RoleRepository roleRepository;

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
	public User create(User u) {
		if (u == null)
			throw new NullPointerException();
		if (findByUsername(u.getUsername()) != null)
			throw new DuplicateUsernameException();
		if (findByEmail(u.getEmail()) != null)
			throw new DuplicateEmailException();
		if (findByPhoneNumber(u.getPhoneNumber()) != null)
			throw new DuplicatePhoneNumberException();

		u.setPassword(new BCryptPasswordEncoder().encode(u.getPassword()));
		Set<Role> roles = new HashSet<>();
		roles.add(roleRepository.findByName("USER"));
		u.setRoles(roles);
		u.setEnabled(true);

		return userRepository.save(u);
	}

	@Deprecated
	@Override
	public User update(User u) {
		return null;
	}

	@Override
	public boolean remove(User u) {
		try {
			userRepository.delete(u);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public User findById(Integer id) {
		return userRepository.getOne(id);
	}

	@Override
	public List<User> findAll() {
		return userRepository.findAll();
	}

	@Override
	public User findByUsername(String username) {
		return userRepository.findByUsername(username);
	}

	@Override
	public User findByEmail(String email) {
		return userRepository.findByEmail(email);
	}

	@Override
	public User findByPhoneNumber(String phoneNumber) {
		return userRepository.findByPhoneNumber(phoneNumber);
	}

	@Override
	public boolean disableUser(String username, boolean disable) {
		userRepository.disableUser(username, disable);
		return true;
	}

	@Override
	public boolean changePassword(String username, String password) {
		userRepository.updatePassword(username, password);
		return true;
	}

	@Override
	public boolean changeFullName(String username, String fullname) {
		userRepository.updateFullname(username, fullname);
		return true;
	}

	@Override
	public boolean changeAddress(String username, String address) {
		 userRepository.updateAddress(username, address);
		 return true;
	}

	@Override
	public boolean changeGender(String username, boolean gender) {
		userRepository.updateGender(username, gender);
		return true;
	}


}
