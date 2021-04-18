package com.social.kata;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

import com.social.kata.services.Engine;

@SpringBootApplication
public class KataApplication {

	@Autowired
	private Engine engine;
	
	public static void main(String[] args) {
		SpringApplication.run(KataApplication.class, args);
	}
	
	/**
	 * Metodo che parte subito dopo lo startup del progetto
	 */
	@EventListener(ApplicationReadyEvent.class)
	private void runKata() {
		engine.startSocial();
	}
}
