package com.ld28.entity;

import com.ld28.audio.SoundSystem;
import com.ld28.handler.GameHandler;
import com.ld28.tile.Tile;

public class EntityGuard extends EntityHuman {

	public static final float MAX_SPEED = 2f;
	
	private boolean stabbing = false;
	private boolean walkingBack = false;
	private int ticksDead;
	private int ticksStabbing;
	private int ticksFlicker;
	private int p1X = 0, p1Y = 0, p2X = 0, p2Y = 0;
	
	public EntityGuard(int p1X, int p1Y, int p2X, GameHandler game) {
		
		super(p1X * Tile.SIZE + Tile.SIZE / 2, p1Y * Tile.SIZE + Tile.SIZE / 2, game);
		baseFrame = 36;
		
		getEntityRenderer().frame = baseFrame;
		
		this.p1X = p1X * Tile.SIZE + Tile.SIZE / 2;
		this.p1Y = p2Y = p1Y * Tile.SIZE + Tile.SIZE / 2;
		this.p2X = p2X * Tile.SIZE + Tile.SIZE / 2;
	}
	
	public EntityGuard(int p1X, int p1Y, int p2X, int p2Y, GameHandler game) {
		
		super(p1X * Tile.SIZE + Tile.SIZE / 2, p1Y * Tile.SIZE + Tile.SIZE / 2, game);
		baseFrame = 36;
		
		getEntityRenderer().frame = baseFrame;
		
		this.p1X = this.p2X = p1X * Tile.SIZE + Tile.SIZE / 2;
		this.p1Y = p1Y * Tile.SIZE + Tile.SIZE / 2;
		this.p2Y = p2Y * Tile.SIZE + Tile.SIZE / 2;
	}
	
	@Override
	public void update() {
		
		if (!walkingBack) {
			
			if (p1X != p2X) {
				
				if (x > p2X) {
					
					if (x - MAX_SPEED < p2X) {
						
						motionX = x - p2X;
					}
					else {
						motionX = -MAX_SPEED;
					}
				}
				else if (x < p2X) {
					
					if (x + MAX_SPEED > p2X) {
						
						motionX = x - p2X;
					}
					else {
						
						motionX = MAX_SPEED;
					}
				}
				else {
					
					motionX = 0;
					walkingBack = true;
				}
			}
			else if (p1Y != p2Y) {
				
				if (y > p2Y) {
					
					if (y - MAX_SPEED < p2Y) {
						
						motionY = y - p2Y;
					}
					else {
						
						motionY = -MAX_SPEED;
					}
				}
				else if (y < p2Y) {
					
					if (y + MAX_SPEED > p2Y) {
						
						motionY = y - p2Y;
					}
					else {
						
						motionY = MAX_SPEED;
					}
				}
				else {
					
					motionY = 0;
					walkingBack = true;
				}
			}
		}
		else {
			
			if (p1X != p2X) {
				
				if (x > p1X) {
					
					if (x - MAX_SPEED < p1X) {
						
						motionX = x - p1X;
					}
					else {
						motionX = -MAX_SPEED;
					}
				}
				else if (x < p1X) {
					
					if (x + MAX_SPEED > p1X) {
						
						motionX = x - p1X;
					}
					else {
						
						motionX = MAX_SPEED;
					}
				}
				else {
					
					motionX = 0;
					walkingBack = false;
				}
			}
			else if (p1Y != p2Y) {
				
				if (y > p1Y) {
					
					if (y - MAX_SPEED < p1Y) {
						
						motionY = y - p1Y;
					}
					else {
						
						motionY = -MAX_SPEED;
					}
				}
				else if (y < p1Y) {
					
					if (y + MAX_SPEED > p1Y) {
						
						motionY = y - p1Y;
					}
					else {
						
						motionY = MAX_SPEED;
					}
				}
				else {
					
					motionY = 0;
					walkingBack = false;
				}
			}
		}
		
		if (isDead()) {
			
			motionY = motionX = 0;
			
			if (ticksDead > 60 && ticksDead < 120) {
				
				if (ticksFlicker >= 15) {
					
					getEntityRenderer().setShouldRender(!getEntityRenderer().shouldRender());
					ticksFlicker = 0;
				}
				
				ticksFlicker++;
			}
			else if (ticksDead > 120) {
				
				if (ticksFlicker >= 7) {
					
					getEntityRenderer().setShouldRender(!getEntityRenderer().shouldRender());
					ticksFlicker = 0;
				}
				
				ticksFlicker++;
			}
			
			if (ticksDead > 180) {
				
				game.flagForRemoval(this);
			}
			
			ticksDead++;
		}
		
		if (stabbing) {
			
			motionX = motionY = 0;
			
			if (ticksStabbing > 30) {
				
				stabbing = false;
			}
			
			ticksStabbing++;
		}
		
		move();
		
		animate();
	}
	
	@Override
	public void animate() {
		
		super.animate();
		
		if (isDead()) {
			
			getEntityRenderer().frame = baseFrame + 3;
		}
		
		if (stabbing) {
			
			getEntityRenderer().frame = baseFrame + 2;
		}
	}
	
	public void stab() {
		
		stabbing = true;
		
		ticksStabbing = 0;
	}
	
	public void die() {
		
		setDead(true);
		SoundSystem.play("guard_death");
	}
	
	@Override
	public void onEntityCollision(Entity entity) {
			
		System.out.println(entity);
		
		if (entity instanceof EntityPlayer) {
			
			stab();
		}
	}
}
