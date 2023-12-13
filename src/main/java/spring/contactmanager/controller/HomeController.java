package spring.contactmanager.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpSession;

@Controller
public class HomeController {


    @RequestMapping({"/home", "/"})
    public String home(HttpSession session, Model model) {
        return "Home"; 
    }

  
 
	
    
}