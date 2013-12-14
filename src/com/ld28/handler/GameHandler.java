package com.ld28.handler;

import java.util.ArrayList;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;

import com.ld28.base.GLSettings;
import com.ld28.controls.Controls;
import com.ld28.controls.GameControls;
import com.ld28.entity.Entity;
import com.ld28.input.Input;
import com.ld28.level.Level;
import com.ld28.texture.TextureLibrary;

public class GameHandler {

	private ArrayList<Entity> entities;
	private ArrayList<Entity> removals;
	private Controls controls;
	public Level level;
	
	public GameHandler() {
		
		GLSettings.initGL();
		
		TextureLibrary.loadAndSubdivide("textures/level/tileset.png", 16, 16, 16, 16);
		
		setLevel(new Level(30, 30, this));
		
		entities = new ArrayList<Entity>();
		removals = new ArrayList<Entity>();

		setControls(new GameControls(this));
		
		loop();
		
		clean();
	}
	
	public void addEntity(Entity entity) {
		
		entities.add(entity);
	}
	
	public void flagForRemoval(Entity entity) {
		
		removals.add(entity);
	}
	
	public void setLevel(Level level) {
		
		this.level = level;
	}
	
	public void render() {
		
		GL11.glClear(GL11.GL_COLOR_BUFFER_BIT);
	
		level.render();
		
		for (Entity e : entities) {
			
			e.getEntityRenderer().render();
		}
	}
	
	public void update() {
		
		for (Entity e : entities) {
			
			e.update();
		}
		
		for (Entity e : removals) {
			
			entities.remove(e);
		}
		
		removals = new ArrayList<Entity>();
		
		controls.check();
		
		Input.update();
	}
	
	public void setControls(Controls ctrls) {
		
		controls = ctrls;
	}
	
	private void loop() {
		
		while (!Display.isCloseRequested()) {
			
			update();
			render();
			
			Display.update();
			Display.sync(60);
		}
	}
	
	private void clean() {
		
		Display.destroy();
		Mouse.destroy();
		Keyboard.destroy();
	}
}
