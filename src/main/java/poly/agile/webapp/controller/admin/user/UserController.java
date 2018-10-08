package poly.agile.webapp.controller.admin.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import poly.agile.webapp.model.User;
import poly.agile.webapp.service.user.UserService;

@Controller
@RequestMapping("/admin")
public class UserController {

	@Autowired
	private UserService userService;

	@GetMapping("/users")
	public String all(Model model) {
		model.addAttribute("users", userService.findAll());
		return "admin/user/list";
	}

	@GetMapping("/user/{id}")
	public User get(@PathVariable("id") Integer id) {
		return userService.findUserById(id);
	}

	@PutMapping(value = "/user/{id}", params = "enabled")
	public String setEnabled(@PathVariable("id") Integer userId, @RequestParam("enabled") Boolean enabled) {
		try {
			userService.setEnabledUser(userId, enabled);
			return "redirect:/admin/users";
		} catch (Exception e) {
			e.printStackTrace();
			return "admin/user/list";
		}
	}

	@DeleteMapping("/user/{id}")
	public @ResponseBody boolean delete(@PathVariable("id") Integer id) {
		User user = userService.findUserById(id);
		return userService.remove(user);
	}

	@ModelAttribute("adminUserPage")
	public boolean active() {
		return true;
	}
}
