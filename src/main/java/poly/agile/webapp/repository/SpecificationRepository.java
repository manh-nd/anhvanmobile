package poly.agile.webapp.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import poly.agile.webapp.dto.SpecificationDTO;
import poly.agile.webapp.model.Specification;

@Transactional
public interface SpecificationRepository extends JpaRepository<Specification, Integer> {

	Specification findByName(String name);

	@Query("SELECT new poly.agile.webapp.dto.SpecificationDTO(s.id, s.name) FROM Specification s ORDER BY s.id")
	Page<SpecificationDTO> getPages(Pageable pageable);

}
