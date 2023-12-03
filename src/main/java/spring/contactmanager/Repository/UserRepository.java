package spring.contactmanager.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import spring.contactmanager.model.User;

public interface UserRepository extends JpaRepository<User, Integer> {

    @Query("select u from User u where u.userEmail = :email")
    public User getUserByUsername(@Param("email") String email);

}
