package spring.contactmanager.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import spring.contactmanager.model.User;

public interface UserRepository extends JpaRepository <User, Integer>{

    
} 