package com.ld28.entity;

import com.ld28.handler.GameHandler;
import com.ld28.render.EntityRenderer;

public class Entity {

	public float x, y, width, height, motionX, motionY;
	public float renderX, renderY;
	public static final float DEFAULT_SIZE = 32f;
	public int frame;
	
	private GameHandler game;
	
	private EntityRenderer renderer;
	
	public Entity(float x, float y, float width, float height, GameHandler game) {
		
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.game = game;
		
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
	
	public EntityRenderer getEntityRenderer() {
		
		return renderer;
	}
	
	public void setEntityRenderer(EntityRenderer renderer) {
		
		this.renderer = renderer;
	}
}
