package poly.agile.webapp.service.brand;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import poly.agile.webapp.exception.DuplicateBrandNameException;
import poly.agile.webapp.model.Brand;
import poly.agile.webapp.repository.BrandRespository;

@Service
public class BrandServiceImpl implements BrandService {

	@Autowired
	private BrandRespository repository;

	@Override
	public Brand create(Brand b) {
		Brand brand = repository.findByName(b.getName());
		if (brand != null)
			throw new DuplicateBrandNameException();
		return repository.save(b);
	}

	@Override
	public Brand update(Brand b) {
		Brand brand = repository.findByName(b.getName());
		if (brand != null)
			if (!brand.getId().equals(b.getId()))
				throw new DuplicateBrandNameException();
		return repository.save(b);
	}

	@Override
	public boolean remove(Brand b) {
		if(b==null) throw new NullPointerException();
		try {
			repository.delete(b);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public Brand findById(Integer id) {
		return repository.getOne(id);
	}

	@Override
	public Brand findBrandByName(String name) {
		return repository.findByName(name);
	}

	@Override
	public List<Brand> findAll() {
		return repository.findAll();
	}

}
