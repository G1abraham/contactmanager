package spring.contactmanager.model;


import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Entity
@Data

public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int userId;
	@Size(min = 4, message = "Name should be more than 2 letters!")
	@NotBlank(message="UserName cannot be blank!")
	private String userName;
	@Column (unique = true)
	@NotBlank(message = "Email cannot be blank!")
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
