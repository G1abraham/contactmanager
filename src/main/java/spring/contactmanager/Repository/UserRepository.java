package spring.contactmanager.Repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import spring.contactmanager.model.Contact;
import spring.contactmanager.model.User;

public interface UserRepository extends JpaRepository<User, Integer> {

    @Query("select u from User u where u.userEmail = :email")
    public User getUserByUsername(@Param("email") String email);
    
    @Query("select u from User u where u.roles = :role")
    public List<User> getUserByRole(@Param("role") String roles);
    
    @Query("SELECT c FROM User u LEFT JOIN u.contacts c WHERE u.userId = :userId")
    public Page<Contact> getContactsByUserId(@Param("userId") int userId,Pageable pageable);


}
