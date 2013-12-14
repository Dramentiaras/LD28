package com.ld28.entity;

import com.ld28.handler.GameHandler;
import com.ld28.level.Level;
import com.ld28.texture.TextureLibrary;
import com.ld28.tile.Tile;

public class EntityPlayer extends Entity {
	
	public float acceleration = .05f;
	public float xAccelerate, yAccelerate;
	
	private int animateTicks;
	private boolean walkFlip = false;
	
	public static final float MAX_SPEED = 3.0f;
	
	public EntityPlayer(float x, float y, GameHandler game) {
		
		super(x, y, 16, 16, game);
		colWidth = colHeight = 12;
		
		getEntityRenderer().setTextured(true);
		getEntityRenderer().setSubdivided(true);
		getEntityRenderer().setTextureName("player");
	}

	@Override
	public void update() {
		
		if (xAccelerate != motionX) {
			
			if (xAccelerate > motionX) {
				
				if (motionX + acceleration > xAccelerate) {
					
					motionX = xAccelerate;
				}
				else {
					
					motionX += acceleration;
				}
			}
			else {
				
				if (motionX - acceleration < xAccelerate) {
					
					motionX = xAccelerate;
				}
				else {
					
					motionX -= acceleration;
				}
			}
		}
		
		if (yAccelerate != motionY) {
			
			if (yAccelerate > motionY) {
				
				if (motionY + acceleration > yAccelerate) {
					
					motionY = yAccelerate;
				}
				else {
					
					motionY += acceleration;
				}
			}
			else {
				
				if (motionY - acceleration < yAccelerate) {
					
					motionY = yAccelerate;
				}
				else {
					
					motionY -= acceleration;
				}
			}
		}
		
		int frame = getEntityRenderer().frame;
		
		if (motionX != 0) {
			
			if (animateTicks > 25) {
			
				getEntityRenderer().flipX(motionX < 0);
					
				if (frame == 0) getEntityRenderer().frame = 1;
				else {
						
					getEntityRenderer().frame = 0;
				}
					
				animateTicks = 0;
			}
		}
		else if (motionY != 0) {
			
			if (animateTicks > 25) {
				
				getEntityRenderer().flipX(false);
				
				if (motionY > 0) {
					
					if (frame == 8) {
						
						getEntityRenderer().frame = 9;
					}
					else if (frame == 9 && !walkFlip) {
						
						getEntityRenderer().flipX(true);
						walkFlip = true;
					}
					else {
						
						walkFlip = false;
						getEntityRenderer().flipX(false);
						
						getEntityRenderer().frame = 8;
					}
				}
				else {
					
					if (frame == 16) {
						
						getEntityRenderer().frame = 17;
					}
					else if (frame == 17 && !walkFlip) {
						
						getEntityRenderer().flipX(true);
						walkFlip = true;
					}
					else {
						
						walkFlip = false;
						getEntityRenderer().flipX(false);
						
						getEntityRenderer().frame = 16;
					}
				}
				
				animateTicks = 0;
			}
		}
		else {
			
			walkFlip = false;
			getEntityRenderer().flipX(false);
			
			getEntityRenderer().frame = 0;
		}
		
		animateTicks++;
		
		super.update();
	}
	
	public void accelerateXTo(float speed) {
		
		xAccelerate = speed;
	}
	
	public void accelerateYTo(float speed) {
		
		yAccelerate = speed;
	}
	
	@Override
	public void onTileCollision(int x, int y, int id, Level level) {
		
		if (id == Tile.wall.id) {
			
			game.resetLevel();
		}
	}
}
