package poly.agile.webapp.service.brand;

import poly.agile.webapp.dto.BrandDTO;
import poly.agile.webapp.model.Brand;
import poly.agile.webapp.service.BaseService;
import poly.agile.webapp.service.DTOService;

public interface BrandService extends BaseService<Brand, Integer>, DTOService<BrandDTO> {
	
	public Brand findBrandByName(String name);

	public long getTotalBrand();
	
}
