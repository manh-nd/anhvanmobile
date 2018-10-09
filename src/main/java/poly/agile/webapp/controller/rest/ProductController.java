package poly.agile.webapp.controller.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import poly.agile.webapp.model.Product;
import poly.agile.webapp.repository.ProductRepository;

@RestController("restProductController")
@RequestMapping("/rest")
public class ProductController {
	
	@Autowired
	private ProductRepository productRepository;
	
	@GetMapping("/products")
	public List<Product> all(){
		return productRepository.findAll();
	}
	
	
}
