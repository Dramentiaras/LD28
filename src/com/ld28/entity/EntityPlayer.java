package com.ld28.entity;

import com.ld28.handler.GameHandler;
import com.ld28.level.Level;
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
		
		animate();
		
		super.update();
	}
	
	public void animate() {
		
		int frame = getEntityRenderer().frame;
		
		if (motionX != 0 || motionY != 0) {
			
			if (animateTicks > 15 || frame == 0 && animateTicks > 5) {
			
				getEntityRenderer().flipX(motionX < 0);
					
				if (frame == 0) getEntityRenderer().frame = 1;
				else if (frame == 1 && !walkFlip){
						
					walkFlip = true;
					getEntityRenderer().flipY(true);
				}
				else {
					
					walkFlip = false;
					getEntityRenderer().flipY(false);
					getEntityRenderer().frame = 0;
				}
					
				animateTicks = 0;
			}
		}
		else {
			
			walkFlip = false;
			getEntityRenderer().flipY(false);
			
			getEntityRenderer().frame = 0;
		}
		
		if (motionX > 0) {
			
			getEntityRenderer().setRotation(0);
		}
		if (motionX < 0) {
			
			getEntityRenderer().setRotation(180);
		}
		else if (motionY > 0) {
			
			getEntityRenderer().setRotation(90);
		}
		else if (motionY < 0) {
			
			getEntityRenderer().setRotation(270);
		}
		
		animateTicks++;
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
		if (id == Tile.doorOpen.id) {
			
			game.nextLevel();
		}
	}
}
