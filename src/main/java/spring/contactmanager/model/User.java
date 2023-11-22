package spring.contactmanager.model;


import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Entity
@Data

public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int userId;
	private String userName;
	@Column (unique = true)
	private String userEmail;
	private String userPassword;
	private String role;
	private boolean enabled;
	private String imageUrl;
	@Column (length = 500)
	private String about;
	@OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
	private List<Contact> contacts;
	
    

   
    
}
