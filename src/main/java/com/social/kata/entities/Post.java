package com.social.kata.entities;

import java.util.Calendar;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * 
 * @author giovanni
 * Entità per il salvataggio dei post dell'utente
 */
@Entity
@Table(name = "post", schema = "kata")
public class Post extends BaseEntity{
	
	//messaggio da salvare
	private String message;
	
	//quando il messaggio viene postato (in millis)
	private Long posted;
	
	@ManyToOne
	private User user;
	
	public Post() {
		
	}
	
	public Post(String message) {
		this.message = message;
		this.posted = Calendar.getInstance().getTimeInMillis();
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Long getPosted() {
		return posted;
	}

	public void setPosted(Long posted) {
		this.posted = posted;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
}
