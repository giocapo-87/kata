package com.social.kata.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.persistence.JoinColumn;

import org.springframework.lang.NonNull;

@Entity
@Table(name = "user", schema = "kata", 
	uniqueConstraints = @UniqueConstraint(columnNames={"userName"}))
public class User extends BaseEntity{
	
	//username utente
	@NonNull
	private String userName;
		
	//lista dei post dell'utente
	@OneToMany(cascade =  {CascadeType.PERSIST,CascadeType.MERGE})
	@JoinColumn(name = "user_id")
	private List<Post> posts = new ArrayList<>();
	
	public User() {
		
	}
	
	public User(String userName) {
		super();
		this.userName = userName;
	}

	//lista dei followers dell'utente
	@ManyToMany(cascade = {CascadeType.MERGE,CascadeType.PERSIST})
    @JoinTable(
        name = "User_follower", 
        joinColumns = { @JoinColumn(name = "user_id") }, 
        inverseJoinColumns = { @JoinColumn(name = "follower_id")})
	private List<User> followers = new ArrayList<>();
	
	public String getUserName() {
		return userName;
	}

	public List<Post> getPosts() {
		return posts;
	}

	public void setPosts(List<Post> posts) {
		this.posts = posts;
	}

	public List<User> getFollowers() {
		return followers;
	}
	
}
