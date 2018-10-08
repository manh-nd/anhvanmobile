package poly.agile.webapp.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import poly.agile.webapp.dto.BranDTO;
import poly.agile.webapp.model.Brand;

@Transactional
public interface BrandRespository extends JpaRepository<Brand, Integer> {
	
	Brand findByName(String name);

	@Query("SELECT new poly.agile.webapp.dto.BranDTO(b.id, b.name) FROM Brand b ORDER BY b.id")
	Page<BranDTO> getPages(Pageable pageable);
}
