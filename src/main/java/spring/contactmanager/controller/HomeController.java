package spring.contactmanager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpSession;
import spring.contactmanager.Repository.UserRepository;

@Controller
public class HomeController {

    @Autowired
    private UserRepository userRepository;
    
  
    @RequestMapping("/home")
    public String home(HttpSession session, Model model){
        session.setAttribute("name", "Jeevan");
        return "Home";
    }
    @RequestMapping("/SignUp")
    public String signUp(){
        return "SignUP";
    }
}
