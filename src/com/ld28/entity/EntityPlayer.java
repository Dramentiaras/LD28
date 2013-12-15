package com.ld28.entity;

import org.lwjgl.input.Keyboard;

import com.ld28.audio.SoundSystem;
import com.ld28.handler.GameHandler;
import com.ld28.level.Level;
import com.ld28.tile.Tile;

public class EntityPlayer extends EntityHuman {
	
	public static final float MAX_SPEED = 2f;
	
	private boolean firing = false;
	private int ticksDead;
	private int ticksFiring;
	private int ticksFlicker;
	
	public EntityPlayer(float x, float y, GameHandler game) {
		
		super(x, y, game);
		getEntityRenderer().frame = baseFrame;
	}
	
	@Override
	public void update() {
		
		super.update();
		
		if (isDead()) {
			
			if (ticksDead > 30) {
				
				if (ticksFlicker >= 30) {
					
					getEntityRenderer().setShouldRender(!getEntityRenderer().shouldRender());
					ticksFlicker = 0;
				}
				
				ticksFlicker++;
			}
			
			if (ticksDead > 120) {
				
				game.resetLevel();
			}
			
			ticksDead++;
		}
		
		if (firing) {
			
			motionX = motionY = 0;
			
			if (ticksFiring > 30) {
				
				firing = false;
			}
			
			ticksFiring++;
		}
	}
	
	@Override
	public void animate() {
		
		super.animate();
		
		if (firing) {
			
			getEntityRenderer().frame = baseFrame + 2;
		}
	}
	
	public void fire() {
		
		firing = true;
		SoundSystem.play("laser");
		
		ticksFiring = 0;
		
		float rot = getEntityRenderer().getRotation();
		
		EntityLaser laser;
		
		if (rot == 0 || rot == 180) {
			
			laser = new EntityLaser(0, 0, (rot == 0 ? 8f:-8f), 0f, this, game);
		}
		else {
			
			laser = new EntityLaser(0, 0, 0f, (rot == 90 ? 8f:-8f), this, game);
		}
		
		if (rot == 0) {
			
			laser.x = x + colWidth / 2 + laser.width / 2 + 1;
			laser.y = y;
		}
		else if (rot == 180) {
			
			laser.x = x - colWidth / 2 - laser.width / 2 - 1;
			laser.y = y;
		}
		else if (rot == 90) {
			
			laser.x = x;
			laser.y = y + colHeight / 2 + laser.height / 2 + 1;
		}
		else if (rot == 270) {
			
			laser.x = x;
			laser.y = y - colHeight / 2 - laser.height / 2 - 1;
		}
		
		game.addEntity(laser);
	}
	
	@Override
	public void onEntityCollision(Entity entity) {
		
		if (entity instanceof EntityGuard) {
			
			die();
		}
 	}
	
	@Override
	public void onTileCollision(int x, int y, int id, Level level) {
		
		if (!isDead()) {
			if (id == Tile.wall.id) {
				
				if (motionX > 0) {
					
					game.level.setKeyPressed(Keyboard.KEY_D);
					motionX = 0;
				}
				else if (motionX < 0) {
					
					game.level.setKeyPressed(Keyboard.KEY_A);
					motionX = 0;
				}
				else if (motionY > 0) {
					
					game.level.setKeyPressed(Keyboard.KEY_S);
					motionY = 0;
				}
				else if (motionY < 0) {
					
					game.level.setKeyPressed(Keyboard.KEY_W);
					motionY = 0;
				}
			}
			if (id == Tile.doorOpen.id) {
				
				game.nextLevel();
			}
		}
	}
	
	public void die() {
		
		setDead(true);
		SoundSystem.play("death" + random.nextInt(2));
	}
}
