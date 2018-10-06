package poly.agile.webapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import poly.agile.webapp.model.User;

@Transactional
public interface UserRepository extends JpaRepository<User, Integer> {

	User findByUsername(String username);
	
}
