package spring.contactmanager.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
@Entity
public class Contact {
     
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int contactId;
    private String contactName;
    private String phone;
    @ManyToOne
    private User user;
}
