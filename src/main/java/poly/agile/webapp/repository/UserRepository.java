package poly.agile.webapp.repository;

import java.util.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import poly.agile.webapp.model.User;

@Transactional
public interface UserRepository extends JpaRepository<User, Integer> {

	User findByUsername(String username);

	User findByEmail(String email);

	User findByPhoneNumber(String phoneNumber);

	@Modifying
	@Query("UPDATE User u SET u.fullname = :fullname, u.address = :address, u.birthdate = :birthdate, u.gender = :gender "
			+ "WHERE u.username = :username")
	void updateUser(@Param("username") String username, @Param("fullname") String fullname,
			@Param("address") String address, @Param("birthdate") Date birthdate, @Param("gender") Boolean gender);

}
