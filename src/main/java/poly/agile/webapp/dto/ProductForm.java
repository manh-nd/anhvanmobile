package poly.agile.webapp.dto;

import lombok.Data;
import poly.agile.webapp.model.Brand;
import poly.agile.webapp.model.ProductSpecDetail;
import poly.agile.webapp.model.Specification;

@Data
public class ProductForm {

	private String id;
	private String name;
	private Brand brand;
	private Integer price;
	private Specification specification;
	private ProductSpecDetail specDetail;
	
}
