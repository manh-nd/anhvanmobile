package poly.agile.webapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import poly.agile.webapp.model.Specification;

@Transactional
public interface SpecificationRepository extends JpaRepository<Specification, Integer> {

	Specification findByName(String name);

}
