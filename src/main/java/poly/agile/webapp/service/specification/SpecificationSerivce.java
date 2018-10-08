package poly.agile.webapp.service.specification;

import poly.agile.webapp.model.Specification;
import poly.agile.webapp.service.BaseService;

public interface SpecificationSerivce extends BaseService<Specification, Integer> {
	
	Specification findByName(String name);
	
}	
