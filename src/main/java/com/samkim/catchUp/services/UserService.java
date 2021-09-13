package com.samkim.catchUp.services;

import java.util.List;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.samkim.catchUp.models.User;
import com.samkim.catchUp.repositories.UserRepository;





@Service
public class UserService {

	@Autowired
	private UserRepository uRepo;
	
	public User getOneUser(Long id) {
		return this.uRepo.findById(id).orElse(null);
	}
	
	public List<User> getAllUsers(){
		return this.uRepo.findAll();
	}
	
//	public boolean emailExist(String email) {
//		return this.uRepo.existsByEmail(email);
//	}
//	
//	public void saveUser(User user) {
//		this.uRepo.save(user);
//	}
	
	public User registerUser(User user) {
		String hash = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());
		user.setPassword(hash);
		return this.uRepo.save(user);
	}
	
	public User getByEmail(String email) {
		return this.uRepo.findByEmail(email);
	}
	
	public boolean authenticateUser(String email, String password) {
		User user = this.uRepo.findByEmail(email);
		if(user == null) {
			return false;
		}
		else {
			if(BCrypt.checkpw(password, user.getPassword())){
				return true;
			}
			else {
				return false;
			}
		}
	}
}
