package spring.contactmanager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpSession;
import spring.contactmanager.Repository.UserRepository;
import spring.contactmanager.model.User;

@Controller
public class FormController {
	
	@Autowired
	private UserRepository repository;
	
	   @RequestMapping("/login")
	    public String login(User user, Model model) {
			model.addAttribute("user", user);
	        return "Login";
	    }
	
	@RequestMapping("/SignUP")
	public String signUp(User user,Model model) {
		user.setEnabled(false);
		model.addAttribute("user", user);
		return "SignUp";
	}
	
	@RequestMapping(path="/addUser", method=RequestMethod.POST)	
	public String addUser(@ModelAttribute User user, HttpSession session, Model model,@RequestParam(value="agreement", defaultValue = "false") boolean agreemnet) {
		if(!agreemnet) {
			System.out.println("Please accept terms and conditions");
			return "redirect:/SignUP";
		}else {

			session.setAttribute("user", user);
			System.out.println(user);
			repository.save(user);
			return "Status";
		}
		
	}
	
	
	
	

}
