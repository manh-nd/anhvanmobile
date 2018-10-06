package poly.agile.webapp.controller.auth;

import java.sql.Date;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PutMapping;

import poly.agile.webapp.dto.Profile;
import poly.agile.webapp.model.User;
import poly.agile.webapp.service.user.UserService;

@Controller
public class ProfileController {

	@Autowired
	private UserService userService;

	@GetMapping("/profile")
	public String profile(Model model) {
		User user = userService.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
		model.addAttribute("profileNav", true);
		model.addAttribute("user", user);
		return "auth/profile";
	}

	@PutMapping("/profile/change-information")
	public String changeInformation(@Valid @ModelAttribute("profile") Profile profile, BindingResult result, Model model) {

		if(result.hasErrors())
			return "auth/profile";
			
		String fullname = profile.getFullname();
		String address = profile.getAddress();
		Date birthdate = profile.getBirthdate();
		Boolean gender = profile.getGender();

		try {
			userService.updateProfile(SecurityContextHolder.getContext().getAuthentication().getName(), fullname,
					address, birthdate, gender);
			model.addAttribute("profileNav", true);
			return "redirect:/profile";
		} catch (Exception e) {
			e.printStackTrace();
			return "auth/profile";
		}
	}

	@ModelAttribute("profile")
	public Profile getProfile() {
		return new Profile();
	}

}
