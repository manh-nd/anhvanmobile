package poly.agile.webapp.controller.auth;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RegisterController {

	@GetMapping("/register")
	public String register() {
		return "auth/register";
	}
	
}
