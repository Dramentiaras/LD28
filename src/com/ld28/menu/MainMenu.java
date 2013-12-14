package com.ld28.menu;

import com.ld28.handler.GameHandler;

public class MainMenu extends Menu {

	public MainMenu(GameHandler game) {
		
		super(game);
		addMenuObject("start");
		addMenuObject("instructions");
		addMenuObject("options");
		addMenuObject("exit");
	}
	
	@Override
	public void triggerIndex(int index) {
		
		if (getObject(index) == "start") {
			
			game.startGame();
		}
	}
}
