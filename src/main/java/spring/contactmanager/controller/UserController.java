package spring.contactmanager.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class UserController {
    
    @RequestMapping("/Menu")
    public String user(){
      return "User/Dashboard";
    }
}
