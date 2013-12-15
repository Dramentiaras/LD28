package com.ld28.entity;

import org.lwjgl.input.Keyboard;

import com.ld28.handler.GameHandler;

public class EntityPowerup extends Entity {

	public static final int RIGHT = 0, DOWN = 1, LEFT = 2, UP = 3, SPACE = 4;
	
	private int type;
	private boolean activated = false;
	private boolean disable;
	
	public EntityPowerup(int type, float x, float y, GameHandler game) {
	
		this(type, x, y, false, game);
	}
	
	public EntityPowerup(int type, float x, float y, boolean disable, GameHandler game) {
		
		super(x, y, game);
		this.type = type;
		
		colWidth = colHeight = 12f;
		
		getEntityRenderer().setTextured(true);
		getEntityRenderer().setSubdivided(true);
		getEntityRenderer().setTextureName("assets");
		
		getEntityRenderer().frame = type == SPACE ? (disable ? 47:46):(disable ? 50:48);
	}
	
	@Override
	public void update() {

		switch (type) {
		
			case 1: {
				
				getEntityRenderer().setRotation(90f);
				break;
			}
			case 2: {
				
				getEntityRenderer().setRotation(180f);
				break;
			}
			case 3: {
				
				getEntityRenderer().setRotation(270f);
				break;
			}
			default: {
				
				getEntityRenderer().setRotation(0f);
				break;
			}
		}
		
	}
	
	public void activate() {
		
		activated = true;
		switch (type) {
		
			case RIGHT: {
				
				if (!disable) {
					
					game.level.resetPressedKey(Keyboard.KEY_D);
				}
				else {
					
					game.level.setKeyPressed(Keyboard.KEY_D);
				}
				break;
			}
			case DOWN: {
				
				if (!disable) {
					
					game.level.resetPressedKey(Keyboard.KEY_S);
				}
				else {
					
					game.level.setKeyPressed(Keyboard.KEY_S);
				}
				break;
			}
			case LEFT: {
				
				if (!disable) {
					
					game.level.resetPressedKey(Keyboard.KEY_A);
				}
				else {
					
					game.level.setKeyPressed(Keyboard.KEY_A);
				}
				break;
			}
			case UP: {
				
				if (!disable) {
					
					game.level.resetPressedKey(Keyboard.KEY_W);
				}
				else {
					
					game.level.setKeyPressed(Keyboard.KEY_W);
				}
				break;
			}
			case SPACE: {
				
				if (!disable) {
					
					game.level.resetPressedKey(Keyboard.KEY_SPACE);
				}
				else {
					
					game.level.setKeyPressed(Keyboard.KEY_SPACE);
				}
				break;
			}
		}
		getEntityRenderer().frame += 1;
	}
	
	@Override
	public void onEntityCollision(Entity entity) {
		
		if (entity instanceof EntityPlayer && !activated) {
			
			activate();
		}
	}
	
	public boolean isActivated() {
		
		return activated;
	}
}
