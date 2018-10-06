package poly.agile.webapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import poly.agile.webapp.model.Role;

@Transactional
public interface RoleRepository extends JpaRepository<Role, Integer> {

}
