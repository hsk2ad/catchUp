package com.samkim.catchUp.models;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;


@Entity
@Table(name="users")
public class User {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	private String firstName;
	private String lastName;
	private String alias;
	@NotBlank
	@Email
	private String email;
	@Size(min=8, message="Password must be 8 characters")
	private String password;
	@Transient
	private String confirmPassword;
	
	@OneToMany(mappedBy="user", cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	private List<Idea> createdIdeas;
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name="likes",
		joinColumns = @JoinColumn(name = "user_id"),
		inverseJoinColumns = @JoinColumn(name = "idea_id")
)
	private List<Idea> likedIdeas;
	
	public User() {
		
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getAlias() {
		return alias;
	}
	public void setAlias(String alias) {
		this.alias = alias;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getConfirmPassword() {
		return confirmPassword;
	}
	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}
//	public List<Idea> getCreatedTasks() {
//		return createdIdeas;
//	}
//	public void setCreatedTasks(List<Task> createdTasks) {
//		this.createdTasks = createdTasks;
//	}
	public List<Idea> getCreatedIdeas() {
		return createdIdeas;
	}
	public void setCreatedIdeas(List<Idea> createdIdeas) {
		this.createdIdeas = createdIdeas;
	}
	public List<Idea> getLikedIdeas() {
		return likedIdeas;
	}
	public void setLikedIdeas(List<Idea> likedIdeas) {
		this.likedIdeas = likedIdeas;
	}
}