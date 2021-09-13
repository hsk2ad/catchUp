package com.samkim.catchUp.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.samkim.catchUp.models.Idea;
import com.samkim.catchUp.models.User;
import com.samkim.catchUp.repositories.IdeaRepository;


@Service
public class IdeaService {
	@Autowired
	private IdeaRepository iRepo;
	
	public List<Idea> getAllIdeas(){
		return this.iRepo.findAll();
	}
	
	public Idea getOneIdea(Long id) {
		return this.iRepo.findById(id).orElse(null);
	}
	
	public Idea create(Idea task) {
		return this.iRepo.save(task);
	}
	
	public void likeIdea(Idea idea, User user) {
		List<User> likers = idea.getLikers();
		likers.add(user);
		this.iRepo.save(idea);
	}
	
	public void deleteTask(Long id) {
		this.iRepo.deleteById(id);
	}
}
