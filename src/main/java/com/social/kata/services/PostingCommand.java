package com.social.kata.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.social.kata.constants.Constants;
import com.social.kata.entities.Post;
import com.social.kata.entities.User;
import com.social.kata.repositories.UserRepository;

/**
 * 
 * @author giovanni
 * classe per la gestione del comando "->"
 */
@Service
@Qualifier("posting")
public class PostingCommand implements CommandStrategy{

	@Autowired
	private UserRepository userRepository;
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public boolean execute(String command) {
		try {
			String[] split = command.split(Constants.Command.POSTING_COMMAND_KEY);
			String username = split[0].trim();
			String message = split[1].trim();
			
			Post post = new Post(message);
			User user = userRepository.findByUserName(username);
			
			if(user == null) {
				user = new User(username);
			}
			user.getPosts().add(post);
			user = userRepository.save(user);
			return true;
		}catch (Exception e) {
			System.err.println(e.getMessage());
			return false;
		}
		
	}
	

}
