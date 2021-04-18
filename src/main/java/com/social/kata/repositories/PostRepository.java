package com.social.kata.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.social.kata.entities.Post;

public interface PostRepository extends CrudRepository<Post, Long>{

	List<Post> findByUser_userNameOrderByPostedDesc(String userName);
	
	//metodo per il recupero di tutti i post del wall di un utente
	@Query(value = "select * from kata.post as p " + 
			"join kata.user as u on u.id = p.user_id " + 
			"where u.id = ?1 or u.id in " + 
			"(select uf.user_id from kata.user_follower as uf " + 
			"where uf.follower_id = ?1) order by p.posted desc",nativeQuery = true)
	List<Post> findWallPosts(Long id);
}
