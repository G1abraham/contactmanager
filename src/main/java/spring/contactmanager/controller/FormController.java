package spring.contactmanager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import spring.contactmanager.Repository.UserRepository;
import spring.contactmanager.model.User;

@Controller
public class FormController {
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private UserRepository repository;
	
	   @RequestMapping("/login")
	    public String login(User user, Model model) {
			model.addAttribute("user", user);
	        return "Login";
	    }
	
	@RequestMapping("/SignUP")
	public String signUp(User user,Model model) {
		user.setEnabled(true);
		model.addAttribute("user", user);
		return "SignUp";
	}
	
	@RequestMapping(path = "/addUser", method = RequestMethod.POST)
    public String addUser(@ModelAttribute @Valid User user, BindingResult bindingResult , HttpSession session, Model model, @RequestParam(value = "agreement", defaultValue = "false") boolean agreement) {
        
		
		
			if ( bindingResult.hasErrors()) {
				System.out.println("This is to show that it's working");
        	System.out.println(bindingResult);
            model.addAttribute("user", user);
            return "SignUP";

        } 
		else if(!agreement){
			model.addAttribute("error","Please agree to the terms and conditions!");
			model.addAttribute("user", user);
			return "SignUP";

		}

		else {
            session.setAttribute("user", user);
            System.out.println(user);  
            user.setUserPassword(passwordEncoder.encode(user.getUserPassword()));
            repository.save(user);
            return "Status";
        }
    }
	
}
