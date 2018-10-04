package poly.agile.webapp.controller.auth;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import poly.agile.webapp.dto.Register;
import poly.agile.webapp.exception.DuplicateEmailException;
import poly.agile.webapp.exception.DuplicatePhoneNumberException;
import poly.agile.webapp.exception.DuplicateUsernameException;
import poly.agile.webapp.model.User;
import poly.agile.webapp.service.user.UserService;

@Controller
public class RegisterController {

	@Autowired
	private UserService userSerivce;

	@GetMapping("/register")
	public String register() {
		return "auth/register";
	}

	@PostMapping("/register")
	public String save(@Valid @ModelAttribute("register") Register register, BindingResult result) {

		if (result.hasErrors())
			return "auth/register";

		String fullname = register.getFullname();
		String username = register.getUsername();
		String password = register.getPassword();
		String verifyPassword = register.getVerifyPassword();
		String email = register.getEmail();
		String phoneNumber = register.getPhoneNumber();

		if (!password.equals(verifyPassword)) {
			result.rejectValue("password", "register.password", "Mật khẩu không trùng khớp!");
			result.rejectValue("verifyPassword", "register.verifyPassword", "Mật khẩu không trùng khớp!");
			return "auth/register";
		}

		User user = new User();
		user.setFullname(fullname);
		user.setUsername(username);
		user.setEmail(email);
		user.setPassword(verifyPassword);
		user.setPhoneNumber(phoneNumber);

		try {
			User u = userSerivce.create(user);
			Authentication auth = new UsernamePasswordAuthenticationToken(u, u.getPassword(), user.getAuthorities());
			SecurityContextHolder.getContext().setAuthentication(auth);
			return "redirect:/";
		} catch (DuplicateUsernameException e) {
			e.printStackTrace();
			result.rejectValue("username", "register.username", "Trùng tài khoản!");
			return "auth/register";
		} catch (DuplicateEmailException e) {
			e.printStackTrace();
			result.rejectValue("email", "register.email", "Trùng thư điện tử!");
			return "auth/register";
		} catch (DuplicatePhoneNumberException e) {
			e.printStackTrace();
			result.rejectValue("phoneNumber", "register.phoneNumber", "Trùng số điện thoại!");
			return "auth/register";
		} catch (Exception e) {
			e.printStackTrace();
			result.reject("error", "Có lỗi xảy ra!");
			return "redirect:/";
		}
		
	}

	@ModelAttribute("register")
	public Register getRegister() {
		return new Register();
	}

}
