package poly.agile.webapp.controller.admin.order;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class OrderLineController {

	@GetMapping("/order/line/{id}")
	public String getOrderLine(@PathVariable("id") Integer id) {
		return "order-line";
	}
}
