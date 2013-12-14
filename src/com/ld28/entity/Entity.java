package com.ld28.entity;

import com.ld28.handler.GameHandler;
import com.ld28.level.Level;
import com.ld28.render.EntityRenderer;

public class Entity {

	public float x, y, width, height, motionX, motionY;
	public float colWidth, colHeight;
	public static final float DEFAULT_SIZE = 16f;
	public int frame;
	
	protected GameHandler game;
	
	private EntityRenderer renderer;
	
	public Entity(float x, float y, float width, float height, GameHandler game) {
		
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.game = game;
		colWidth = width;
		colHeight = height;
		
		renderer = new EntityRenderer(this);
	}
	
	public Entity(float x, float y, GameHandler game) {
		
		this(x, y, DEFAULT_SIZE, DEFAULT_SIZE, game);
	}
	
	public void move() {
		
		x += motionX;
		y += motionY;
	}
	
	public void update() {
	
		move();
	}
	
	public void onEntityCollision(Entity entity) {
	
	}
	
	public void onTileCollision(int x, int y, int id, Level level) {
		
		
	}
	
	public EntityRenderer getEntityRenderer() {
		
		return renderer;
	}
	
	public void setEntityRenderer(EntityRenderer renderer) {
		
		this.renderer = renderer;
	}
}
