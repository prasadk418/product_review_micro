package com.zuul.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.zuul.domain.User;
import com.zuul.respository.UserRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService{
	
	@Autowired
	private UserRepository userRepo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		User user=userRepo.findByUsername(username);
		
		/*Set<GrantedAuthority> grantedAuthorities=new HashSet<GrantedAuthority>();
		
		for(Role role : user.getRoles()){
			grantedAuthorities.add(new SimpleGrantedAuthority(role.getRole()));
		}*/
		
		//return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), grantedAuthorities);
		return user;
	}

}
