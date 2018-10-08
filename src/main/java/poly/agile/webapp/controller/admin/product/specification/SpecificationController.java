package poly.agile.webapp.controller.admin.product.specification;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import poly.agile.webapp.model.Specification;
import poly.agile.webapp.service.specification.SpecificationSerivce;

@Controller
@RequestMapping("/admin/product")
public class SpecificationController {

	@Autowired
	private SpecificationSerivce specificationSerivce;

	@GetMapping("/specifications")
	public String all(Model model) {
		model.addAttribute("specifications", specificationSerivce.findAll());
		return "admin/product/specification/list";
	}

	@GetMapping("/specification")
	public String add() {
		return "admin/product/specification/add";
	}
	
	@GetMapping("/specification/{id}")
	public String edit(@PathVariable("id") Integer id, Model model) {
		Specification specification = specificationSerivce.findById(id);
		model.addAttribute("specification", specification);
		return "admin/product/specification/edit";
	}
	
	@PutMapping(value="/specification/{id}", params="update")
	public String update(@Valid @ModelAttribute("specification") Specification specification, BindingResult result , Model model) {
		
		if(result.hasErrors())
			return "admin/product/specification/edit";
		
		specificationSerivce.update(specification);
		
		return "admin/product/specification/edit";
	}

	@ModelAttribute("specification")
	public Specification getSpecification() {
		return new Specification();
	}

	@ModelAttribute("adminProductPage")
	public boolean active() {
		return true;
	}

}
