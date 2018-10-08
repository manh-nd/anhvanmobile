package poly.agile.webapp.service.specification;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import poly.agile.webapp.exception.DuplicateSpecificationNameException;
import poly.agile.webapp.model.Specification;
import poly.agile.webapp.repository.SpecificationRepository;

@Service
public class SpecificationServiceImpl implements SpecificationSerivce {

	@Autowired
	private SpecificationRepository specificationRepository;

	@Override
	public Specification create(Specification s) {
		Specification specification = findByName(s.getName());
		if (specification != null)
			throw new DuplicateSpecificationNameException();

		return specificationRepository.save(s);
	}

	@Override
	public Specification update(Specification s) {
		Specification specification = findByName(s.getName());
		if (specification != null)
			if (!specification.getId().equals(s.getId()))
				throw new DuplicateSpecificationNameException();
		return specificationRepository.save(s);
	}

	@Override
	public boolean remove(Specification s) {
		try {
			specificationRepository.delete(s);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public Specification findById(Integer id) {
		return specificationRepository.getOne(id);
	}

	@Override
	public List<Specification> findAll() {
		return specificationRepository.findAll();
	}

	@Override
	public Specification findByName(String name) {
		return specificationRepository.findByName(name);
	}

}
