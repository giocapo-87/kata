package com.social.kata;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.Before;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Primary;
import org.springframework.core.metrics.ApplicationStartup;
import org.springframework.test.context.junit4.SpringRunner;

import com.social.kata.services.FollowingCommand;
import com.social.kata.services.PostingCommand;
import com.social.kata.services.ReadingCommand;
import com.social.kata.services.WallCommand;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = TestKataApplication.class)
@TestMethodOrder(OrderAnnotation.class)
class KataApplicationTests {
	
	@MockBean
	private ApplicationStartup applicationStartup;
	
	@Autowired
	private PostingCommand posting;
	
	@Autowired
	private FollowingCommand following;
	
	@Autowired
	private ReadingCommand reading;
	
	@Autowired
	private WallCommand wall;
	
	@Before
	@Primary
	public void init() {
		applicationStartup.start("test");
	}
	@Test
	@Order(1)
	void postingTest() {
		assertTrue(posting.execute("TestUser->test message"));
	}
	
	@Test
	@Order(2)
	void readingTest() {
		assertTrue(reading.execute("TestUser"));
	}
	
	@Test
	@Order(3)
	void followingTest() {
		assertTrue(following.execute("TestUser follows TestUser2"));
	}
	
	@Test
	@Order(4)
	void wallTest() {
		assertTrue(wall.execute("TestUser wall"));
	}

}
