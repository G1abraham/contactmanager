package spring.contactmanager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import spring.contactmanager.Repository.UserRepository;

@Controller
public class HomeController {

    @Autowired
    private UserRepository userRepository;
    
  
    @RequestMapping("/home")
    public String home(){
        return "Home";
    }
}
