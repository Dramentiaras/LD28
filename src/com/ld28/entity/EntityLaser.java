package com.ld28.entity;

import com.ld28.audio.SoundSystem;
import com.ld28.handler.GameHandler;
import com.ld28.level.Level;
import com.ld28.tile.Tile;

public class EntityLaser extends Entity {

	private EntityPlayer shooter;
	
	public EntityLaser(float x, float y, float xMotion, float yMotion, EntityPlayer shooter, GameHandler game) {
		
		super(x, y, game);
		
		if (xMotion != 0) {
			
			width = 8;
			height = 3;
			motionX = xMotion;
			motionY = 0;
		}
		else {
			
			width = 3;
			height = 8;
			motionX = 0;
			motionY = yMotion;
		}
		
		colWidth = width;
		colHeight = height;
		
		this.shooter = shooter;
		
		getEntityRenderer().setTextured(false);
		getEntityRenderer().setColor(1f, 0f, 0f);
	}
	
	@Override
	public void onTileCollision(int x, int y, int id, Level level) {
		
		if (Tile.tiles[id].isObstacle()) {
			
			game.flagForRemoval(this);
			SoundSystem.play("laser_hit");
		}
	}
	
	@Override
	public void onEntityCollision(Entity entity) {
		
		if (entity instanceof EntityPowerup && !((EntityPowerup) entity).isActivated()) {
			
			((EntityPowerup) entity).activate();
			game.flagForRemoval(this);
			SoundSystem.play("laser_hit");
		}
		
		if (entity instanceof EntityGuard && !((EntityGuard) entity).isDead()) {
			
			((EntityGuard) entity).die();
		}
	}
}
