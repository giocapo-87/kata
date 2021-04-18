package com.social.kata.services;

import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.social.kata.constants.Constants;
import com.social.kata.entities.Post;
import com.social.kata.entities.User;
import com.social.kata.repositories.PostRepository;
import com.social.kata.repositories.UserRepository;
import com.social.kata.utils.PostTime;
import com.social.kata.utils.TimeUtil;

/**
 * 
 * @author giovanni
 * classe per la gestione del comando "wall"
 */
@Service
@Qualifier("wall")
public class WallCommand implements CommandStrategy{

	@Autowired
	private PostRepository postRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Override
	public boolean execute(String command) {
		try {
			String[] split = command.split(Constants.Command.WALL_COMMAND_KEY);
			String username = split[0].trim();
			User user = userRepository.findByUserName(username);
			if(user != null) {
				List<Post> posts = postRepository.findWallPosts(user.getId());
				for (Post post : posts) {
					PostTime postDate = TimeUtil.getPostDate(Calendar.getInstance().getTimeInMillis() - post.getPosted());

					System.out.println("> " + post.getUser().getUserName() + " - " + post.getMessage() + " (" + postDate.getTime() + " " + postDate.getMisurationType().getValue() + " ago)");
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
