package spring.contactmanager.controller;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.security.Principal;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import jakarta.servlet.http.HttpSession;
import spring.contactmanager.Repository.ContactRepository;
import spring.contactmanager.Repository.UserRepository;
import spring.contactmanager.model.Contact;
import spring.contactmanager.model.User;

@Controller
@RequestMapping("/user")
public class UserController {
	@Autowired
	private UserRepository repository;
	@Autowired
	private ContactRepository contactrepository;
	@Autowired
	private ResourceLoader loader;
    
    @RequestMapping("/dashboard")
        public String dashboard(Model model, Principal principal,HttpSession httpSession) {
		
		String username= principal.getName();
		
		User user=repository.getUserByUsername(username);
		System.out.println(user.getUserName());
		httpSession.setAttribute("user", user);
		model.addAttribute("user", user);
      return "/User/UserDashboard";
    }
    
    @RequestMapping("/contactList")
    public String contacts(User user,Model model,HttpSession session,
    		@RequestParam(defaultValue="0")int page) {
    	Integer userId= ((User)session.getAttribute("user")).getUserId();
    	
    	//number of contacts to retrieved
    	
    	int pageSize=10;
    	
    	Pageable pageable= PageRequest.of(page, pageSize);
    	
    	//Fetching contacts using pagination 
    	
    	Page<Contact> contactList= repository.getContactsByUserId(userId, pageable);
    	
    	if(contactList.isEmpty()) {
        	model.addAttribute("contactList", Collections.EMPTY_LIST);
    	}
    	else {
        	model.addAttribute("contactList", contactList.getContent());
    	}
    	
    	 // Add pagination-related information to the model
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", contactList.getTotalPages());

    	
    	return "/User/Contact";
    }
    
    @RequestMapping("/contactForm")
    public String contactForm(Contact contact, Model model) {
    	model.addAttribute("contact",contact);
    	return "User/AddContacts";
    }
    
    @RequestMapping(path="/addContact",method=RequestMethod.POST)
    public String addContact(@ModelAttribute Contact contact,
    		@RequestParam("file")MultipartFile file, 
    		Model model, HttpSession session) {
    	try {
    		
			User user= (User)session.getAttribute("user");
			contact.setUser(user);
			if(!file.isEmpty()) {
				contact.setImage(file.getOriginalFilename());
				Resource resource =loader.getResource("classpath:/static/image"); 
				File saveFile= resource.getFile();
				Path path= Paths.get(saveFile.getAbsolutePath()+ File.separator+ file.getOriginalFilename()); 
				Files.copy(file.getInputStream(),path, StandardCopyOption.REPLACE_EXISTING);
				System.out.println("Image successfully added!");
			}
			contactrepository.save(contact);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return "redirect:/user/contactList";
    }
    
    @RequestMapping(path="/deleteContact", method=RequestMethod.POST)
    public String deleteContact(@RequestParam("id")int id) {
    	contactrepository.deleteById(id);
    	return "redirect:/user/contactList";
    }
    
    
    
}
