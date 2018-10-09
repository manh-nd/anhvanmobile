package poly.agile.webapp.controller.auth;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import poly.agile.webapp.model.Order;
import poly.agile.webapp.model.User;
import poly.agile.webapp.service.order.OrderService;
import poly.agile.webapp.service.user.UserService;

@Controller
public class ProfileController {

	@Autowired
	private UserService userService;

	@Autowired
	private OrderService orderService;

	@GetMapping("/profile")
	public String profile() {
		return "auth/profile";
	}

	@GetMapping("/profile/user-info")
	public @ResponseBody User getUser(Principal principal) {
		return userService.findByUsername(principal.getName());
	}

	@GetMapping("/profile/order/total-order")
	public @ResponseBody Long getCount(Principal principal) {
		return orderService.countNumberOfOrder(userService.findByUsername(principal.getName()));
	}

	@GetMapping("/profile/order/history")
	public @ResponseBody List<Order> getOrder(Principal principal) {
		return orderService.findOrderByUser(userService.findByUsername(principal.getName()));
	}

}
