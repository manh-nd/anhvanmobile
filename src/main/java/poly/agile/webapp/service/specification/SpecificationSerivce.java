package poly.agile.webapp.service.specification;

import poly.agile.webapp.dto.SpecificationDTO;
import poly.agile.webapp.model.Specification;
import poly.agile.webapp.service.BaseService;
import poly.agile.webapp.service.DTOService;

public interface SpecificationSerivce extends BaseService<Specification, Integer>, DTOService<SpecificationDTO> {
	
	Specification findByName(String name);
	
}	
