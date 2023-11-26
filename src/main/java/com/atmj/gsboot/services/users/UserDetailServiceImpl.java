package com.atmj.gsboot.services.users;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
import org.springframework.stereotype.Service;

import com.atmj.dbaccess.entities.AuthorityEntity;
import com.atmj.gsboot.dbaccess.entities.UserEntity;
import com.atmj.gsboot.dbaccess.repositories.UsersRepository;

@Service
public class UserDetailServiceImpl implements UserDetailsService {

	@Autowired
	private UsersRepository usersRepository;

	@Override
	public UserDetails loadUserByUsername(String username) {
		UserEntity appUser = usersRepository.findByUsernameAndEnabledTrue(username);
		// Mapear nuestra lista de Authority con la de spring security
		List<GrantedAuthority> grantList = new ArrayList<>();
		for (AuthorityEntity authority : appUser.getAuthority()) {
			// ROLE_USER, ROLE_ADMIN,..
			GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(authority.getAuthority());
			grantList.add(grantedAuthority);
		}

		// Crear El objeto UserDetails que va a ir en sesión y retornarlo.
		return new org.springframework.security.core.userdetails.User(appUser.getUsername(), appUser.getPassword(),
				grantList);
	}

	@Bean
	public PasswordEncoder encoder() {
		return Pbkdf2PasswordEncoder.defaultsForSpringSecurity_v5_8();
	}

	public Iterable<UserEntity> allEmployeesActives() {
		return usersRepository.findByEnabledTrue();
	}
}