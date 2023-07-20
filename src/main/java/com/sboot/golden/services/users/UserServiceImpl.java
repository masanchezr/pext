package com.sboot.golden.services.users;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.sboot.golden.dbaccess.entities.AuthorityEntity;
import com.sboot.golden.dbaccess.entities.UserEntity;
import com.sboot.golden.dbaccess.repositories.AuthorityRepository;
import com.sboot.golden.dbaccess.repositories.UsersRepository;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private AuthorityRepository authorityRepository;

	@Autowired
	private UsersRepository usersRepository;

	@Autowired
	private PasswordEncoder pbkdf2Encoder;

	@Autowired
	private ModelMapper mapper;

	@Override
	public User findUser(String username) {
		UserEntity entity = usersRepository.findByUsername(username);
		User user = null;
		if (entity != null) {
			user = mapper.map(entity, User.class);
		}
		return user;
	}

	@Override
	public List<User> allUsers() {
		return mapper(usersRepository.findAll());
	}

	private List<User> mapper(Iterable<UserEntity> findAll) {
		List<User> users = null;
		if (findAll != null) {
			users = new ArrayList<>();
			Iterator<UserEntity> iusers = findAll.iterator();
			while (iusers.hasNext()) {
				users.add(mapper.map(iusers.next(), User.class));
			}
		}
		return users;
	}

	public void newUser(User user) {
		UserEntity entity;
		AuthorityEntity authority = authorityRepository.findByAuthority("ROLE_".concat(user.getRole()));
		Set<AuthorityEntity> authorities = new HashSet<AuthorityEntity>();
		user.setPassword(pbkdf2Encoder.encode(user.getPassword()));
		entity = usersRepository.save(mapper.map(user, UserEntity.class));
		// volvemos a guardar, una vez que tenemos ya el user
		authorities.add(authority);
		entity.setAuthority(authorities);
		usersRepository.save(entity);
	}

	@Override
	public List<User> allUsersEnabled() {
		return mapper(usersRepository.findByEnabledTrue());
	}
}