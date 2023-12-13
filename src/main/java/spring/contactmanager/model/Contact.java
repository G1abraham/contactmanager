package spring.contactmanager.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
@Entity
public class Contact {
     
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int contactId;
	private String name;
	private String secondName;
	private String work;
	private String email;
	private String phone;
	
	private String image;
	@Column(length = 10000)
	private String description;
    @ManyToOne
    private User user;
}
