package com.social.kata.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.social.kata.constants.Constants;
import com.social.kata.entities.User;
import com.social.kata.repositories.UserRepository;

/**
 * classe per la gestione del comando "follows"
 * @author giovanni
 *
 */
@Service
@Qualifier("following")
public class FollowingCommand implements CommandStrategy{

	@Autowired
	private UserRepository userRepository;
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public boolean execute(String command) {
		try {
			String[] split = command.split(Constants.Command.FOLLOWING_COMMAND_KEY);
			
			User user = userRepository.findByUserName(split[1].trim());
			User follower = userRepository.findByUserName(split[0].trim());
			if(user != null && follower != null && user.getId().longValue() != follower.getId().longValue()) {
				if(!user.getFollowers().contains(follower)) {
					user.getFollowers().add(follower);
					userRepository.save(user);
				}
			}else {
				System.out.println(Constants.Message.USER_NOT_FOUND);
			}
			return true;
		}catch (Exception e) {
			System.err.println(e.getMessage());
			return false;
		}
		
	}
}
