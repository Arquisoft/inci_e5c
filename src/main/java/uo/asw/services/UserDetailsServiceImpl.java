package uo.asw.services;


import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import uo.asw.entities.Operario;
import uo.asw.repositories.OperariosRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService{
	
    @Autowired
    private OperariosRepository operariosRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
	    Operario o = operariosRepository.findByUsername(username);
	    Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
	    
	    if (o == null) throw new UsernameNotFoundException("usuario no encontrado");
	  
	    return  new org.springframework.security.core.userdetails.User(
	            o.getUsername(), o.getPassword(), grantedAuthorities);
	}
	
}
