package com.ld28.entity;

import org.lwjgl.input.Keyboard;

import com.ld28.handler.GameHandler;

public class EntityPowerup extends Entity {

	public static final int RIGHT = 0, DOWN = 1, LEFT = 2, UP = 3, SPACE = 4;
	
	private int type;
	private boolean activated = false;
	
	public EntityPowerup(int type, float x, float y, GameHandler game) {
		
		super(x, y, game);
		this.type = type;
		
		getEntityRenderer().setTextured(true);
		getEntityRenderer().setSubdivided(true);
		getEntityRenderer().setTextureName("assets");
		
		getEntityRenderer().frame = type == SPACE ? 46:48;
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
				
				game.level.resetPressedKey(Keyboard.KEY_D);
				break;
			}
			case DOWN: {
				
				game.level.resetPressedKey(Keyboard.KEY_S);
				break;
			}
			case LEFT: {
				
				game.level.resetPressedKey(Keyboard.KEY_A);
				break;
			}
			case UP: {
				
				game.level.resetPressedKey(Keyboard.KEY_W);
				break;
			}
			case SPACE: {
				
				game.level.resetPressedKey(Keyboard.KEY_SPACE);
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
