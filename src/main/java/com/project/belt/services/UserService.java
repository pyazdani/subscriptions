package com.project.belt.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.project.belt.models.Role;
import com.project.belt.models.User;
import com.project.belt.repositories.RoleRepository;
import com.project.belt.repositories.SubscriptionRepository;
import com.project.belt.repositories.UserRepository;

@Service
public class UserService {
	private UserRepository userRepository;
	private RoleRepository roleRepository;
	private SubscriptionRepository subscriptionRepository;
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	public UserService(UserRepository userRepository, RoleRepository roleRepository, SubscriptionRepository subscriptionRepository, BCryptPasswordEncoder bCryptPasswordEncoder){
		this.userRepository= userRepository;
		this.roleRepository=roleRepository;
		this.subscriptionRepository= subscriptionRepository;
		this.bCryptPasswordEncoder= bCryptPasswordEncoder;
	}
	
	public void saveWithUserRole(User user){
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		ArrayList<Role> roleList = new ArrayList<>();
		roleList.add(roleRepository.findByName("ROLE_USER"));	
		user.setRoles(roleList);
		userRepository.save(user);
	}
	
	public void saveWithAdminRole(User user){
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		ArrayList<Role> roleList = new ArrayList<>();
		roleList.add(roleRepository.findByName("ROLE_ADMIN"));
		user.setRoles(roleList);
		userRepository.save(user);
	}
	
	public void update(User user){
		user.setUpdatedAt(new Date());
		userRepository.save(user);
	}
	
	public User findByEmail(String email){
		return userRepository.findByEmail(email);
	}
	
	public List<User> all(){
		return userRepository.findAll();
	}
	
	public void delete(Long id){
		userRepository.delete(id);
	}

	public User findById(Long id) {
		return userRepository.findById(id);
	}
}