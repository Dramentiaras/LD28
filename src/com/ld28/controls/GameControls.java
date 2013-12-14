package com.ld28.controls;

import org.lwjgl.input.Keyboard;

import com.ld28.base.GLSettings;
import com.ld28.entity.EntityPlayer;
import com.ld28.handler.GameHandler;
import com.ld28.input.Input;

public class GameControls implements Controls {

	private GameHandler game;
	
	public GameControls(GameHandler game) {
		
		this.game = game;
	}
	
	@Override
	public void check() {
		
		if (Input.isKeyDown(Keyboard.KEY_D)) {
			
			game.player.accelerateXTo(EntityPlayer.MAX_SPEED);
		}
		
		else if (Input.isKeyDown(Keyboard.KEY_A)) {
			
			game.player.accelerateXTo(-EntityPlayer.MAX_SPEED);
		}
		else {
			
			game.player.accelerateXTo(0);
		}
		
		if (Input.isKeyDown(Keyboard.KEY_W)) {
			
			game.player.accelerateYTo(-EntityPlayer.MAX_SPEED);
		}
		
		else if (Input.isKeyDown(Keyboard.KEY_S)) {
			
			game.player.accelerateYTo(EntityPlayer.MAX_SPEED);
		}
		else {
			
			game.player.accelerateYTo(0);
		}
	}
}
