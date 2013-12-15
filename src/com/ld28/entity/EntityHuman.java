package com.ld28.entity;

import java.util.Random;

import com.ld28.handler.GameHandler;

public class EntityHuman extends Entity {

	public float acceleration = .25f;
	public float xAccelerate, yAccelerate;
	protected Random random = new Random();
	
	private int animateTicks;
	private boolean walkFlip = false;
	
	public int baseFrame = 32;
	
	public EntityHuman(float x, float y, GameHandler game) {
		super(x, y, 16, 16, game);
		colWidth = colHeight = 12f;
		
		getEntityRenderer().setTextured(true);
		getEntityRenderer().setSubdivided(true);
		getEntityRenderer().setTextureName("assets");
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
		
		if (isDead()) {
			
			motionX = motionY = 0;
		}
		
		super.update();
	}
	
	public void animate() {
		
		int frame = getEntityRenderer().frame;
		
		if (motionX != 0 || motionY != 0) {
			
			if (animateTicks > 15 || frame == 0 && animateTicks > 5) {
					
				if (frame == baseFrame) getEntityRenderer().frame = baseFrame + 1;
				else if (frame == baseFrame + 1 && !walkFlip){
					
					walkFlip = true;
					
					if (getEntityRenderer().getRotation() == 90 || getEntityRenderer().getRotation() == 270) {
						
						getEntityRenderer().flipX(true);
					}
					else {
						
						getEntityRenderer().flipY(true);
					}
				}
				else {
					
					walkFlip = false;
					if (getEntityRenderer().getRotation() == 90 || getEntityRenderer().getRotation() == 270) {
						
						getEntityRenderer().flipX(false);
					}
					else {
						
						getEntityRenderer().flipY(false);
					}
					getEntityRenderer().frame = baseFrame;
				}
					
				animateTicks = 0;
			}
		}
		else if (isDead()) {
			
			getEntityRenderer().frame = baseFrame + 3;
		}
		else {
			
			walkFlip = false;
			getEntityRenderer().flipY(false);
			getEntityRenderer().flipX(false);
			
			getEntityRenderer().frame = baseFrame;
		}
		
		if (motionX > 0) {
			
			getEntityRenderer().setRotation(0);
			getEntityRenderer().flipX(false);
		}
		if (motionX < 0) {
			
			getEntityRenderer().setRotation(180);
			getEntityRenderer().flipX(false);
		}
		else if (motionY > 0) {
			
			getEntityRenderer().setRotation(90);
			getEntityRenderer().flipY(false);
		}
		else if (motionY < 0) {
			
			getEntityRenderer().setRotation(270);
			getEntityRenderer().flipY(false);
		}
		
		animateTicks++;
	}
	
	public void accelerateXTo(float speed) {
		
		xAccelerate = speed;
	}
	
	public void accelerateYTo(float speed) {
		
		yAccelerate = speed;
	}
}
