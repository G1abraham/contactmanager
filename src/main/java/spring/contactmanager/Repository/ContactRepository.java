package spring.contactmanager.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import spring.contactmanager.model.Contact;

public interface ContactRepository extends JpaRepository<Contact, Integer> {

}
