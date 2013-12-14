package com.ld28.controls;

import com.ld28.base.GLSettings;
import com.ld28.handler.GameHandler;
import com.ld28.input.Input;

public class GameControls implements Controls {

	private GameHandler game;
	
	public GameControls(GameHandler game) {
		
		this.game = game;
	}
	
	@Override
	public void check() {
		
	}
}
