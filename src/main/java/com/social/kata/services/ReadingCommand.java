package com.social.kata.services;

import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.social.kata.entities.Post;
import com.social.kata.repositories.PostRepository;
import com.social.kata.utils.PostTime;
import com.social.kata.utils.TimeUtil;

/**
 * 
 * @author giovanni
 * classe per la gestione del comando per la lettura dei post dell'utente
 *
 */
@Service
@Qualifier("reading")
public class ReadingCommand implements CommandStrategy{

	@Autowired
	private PostRepository postRepository;
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public boolean execute(String command) {
		try {
			List<Post> posts = postRepository.findByUser_userNameOrderByPostedDesc(command.trim());
			for (Post post : posts) {
				PostTime postDate = TimeUtil.getPostDate(Calendar.getInstance().getTimeInMillis() - post.getPosted());

				System.out.println("> " + post.getMessage() + " (" + postDate.getTime() + " " + postDate.getMisurationType().getValue() + " ago)");
			}
			return true;
		}catch (Exception e) {
			System.err.println(e.getMessage());
			return false;
		}
		
	}
	

}
