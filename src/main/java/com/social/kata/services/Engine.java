package com.social.kata.services;

import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.social.kata.constants.Constants;

/**
 * 
 * @author giovanni
 * 
 */
@Service
public class Engine {

	@Autowired
	@Qualifier("posting")
	private CommandStrategy postingCommand;
	
	@Autowired
	@Qualifier("reading")
	private CommandStrategy readingCommand;
	
	@Autowired
	@Qualifier("following")
	private CommandStrategy followingCommand;
	
	@Autowired
	@Qualifier("wall")
	private CommandStrategy wallCommand;
	
	/**
	 * Metodo per la gestione dei comandi in input
	 */
	public void startSocial() {
		Scanner scanner = new Scanner(System.in);

		System.out.println(Constants.Message.WELCOME_MESSAGE);
		System.out.println(Constants.Message.DO_ACTION_MESSAGE);

		while(scanner.hasNext()) {
			String command = scanner.nextLine();
			CommandStrategy commandStrategy = chooseStrategy(command);
			if(commandStrategy != null) {
				commandStrategy.execute(command);
			}
			
			System.out.println(Constants.Message.DO_ACTION_MESSAGE);
		}
	}

	/**
	 * 
	 * @param command: stringa di input immessa dall'utente
	 * Valori ammessi: 
	 * 	POSTING:	<username> -> <message> 
	 *	FOLLOWING: <username> follows <otherUser>
	 *	READING: <username>
	 *	WALL: <username> wall
	 *
	 * Il metodo restituisce lo strategy da utilizzare per la gestione del comando immesso
	 * @return
	 */
	private CommandStrategy chooseStrategy(String command) {
	
		if(command.contains(Constants.Command.POSTING_COMMAND_KEY)) {
			return postingCommand;
		}else if(command.contains(Constants.Command.FOLLOWING_COMMAND_KEY)) {
			return followingCommand;
		}else if(command.contains(Constants.Command.WALL_COMMAND_KEY)){
			return wallCommand;
		}else {
			return readingCommand;
		}
	}
}
