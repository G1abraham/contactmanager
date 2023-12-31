package spring.contactmanager.Config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import spring.contactmanager.Repository.UserRepository;
import spring.contactmanager.model.User;



public class UserServiceDetailsImpl implements UserDetailsService
{
	
	@Autowired
	private UserRepository repository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user= repository.getUserByUsername(username);
		if(user==null) {
			throw new UsernameNotFoundException("Couldn't find User");
		}
		CustomUserDetails customUserDetails= new CustomUserDetails(user);
		
		return customUserDetails;
	}


}
