package poly.agile.webapp.controller.admin.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import poly.agile.webapp.dto.ProductDTO;
import poly.agile.webapp.service.product.ProductService;
import poly.agile.webapp.util.pagination.Pagination;

@Controller
@RequestMapping("/admin")
public class ProductListController {

	@Autowired
	private ProductService productService;

	@GetMapping("/products")
	public String all(Model model, @RequestParam(value = "page", defaultValue = "1") Integer page) {
		Page<ProductDTO> pages = productService.getPages(page);
		Pagination pagination = new Pagination(pages.getTotalPages(), 10, page);
		model.addAttribute("products", pages.getContent());
		model.addAttribute("pagination", pagination);
		model.addAttribute("adminProductPage", true);
		return "admin/product/list";
	}

}
