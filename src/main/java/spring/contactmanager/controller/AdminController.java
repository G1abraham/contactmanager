package spring.contactmanager.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpSession;
import spring.contactmanager.Repository.UserRepository;
import spring.contactmanager.model.User;

@Controller
@RequestMapping("/admin")
public class AdminController {
	
	@Autowired
	private UserRepository repository;

	@RequestMapping("/dashboard")
	public String dashboard(Model model, Principal principal,HttpSession httpSession) {
		
		String username= principal.getName();
		
		User user=repository.getUserByUsername(username);
		
		System.out.println(user.getUserName());
		httpSession.setAttribute("user", user);
		model.addAttribute("user", user);
		return "/admin/AdminDashboard";
	}
	
	@RequestMapping("/userlist")
	public String contact(Model model) {
		List<User> normalUser= repository.getUserByRole("User");
		List<User> adminUser=  repository.getUserByRole("Admin");
		model.addAttribute("adminUser", adminUser);
		System.out.println(normalUser);
		System.out.println(adminUser);
		model.addAttribute("normalUser", normalUser);
		return "/admin/Users";
	}
	
	
}
