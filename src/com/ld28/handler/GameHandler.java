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
import com.ld28.entity.EntityPlayer;
import com.ld28.input.Input;
import com.ld28.level.Level;
import com.ld28.menu.Menu;
import com.ld28.texture.TextureLibrary;

public class GameHandler {

	public ArrayList<Entity> entities;
	private ArrayList<Entity> removals;
	private Controls controls;
	public Level level;
	
	private boolean paused;
	
	private int curLevel = 0;
	
	public static final int MENU = 0, INGAME = 1;
	
	private int state = MENU;
	
	public EntityPlayer player;
	
	public GameHandler() {
		
		GLSettings.initGL();
		
		loadTextures();
		LevelLoader.loadLevels(this);
		
		loop();
		
		clean();
	}
	
	public void startGame() {
		
		state = INGAME;
		
		entities = new ArrayList<Entity>();
		removals = new ArrayList<Entity>();
		
		setLevel(LevelLoader.getLevel(curLevel, this));
		level.spawnPlayer();

		setControls(new GameControls(this));
	}
	
	public void enterMenu(Menu menu) {
		
		state = MENU;
	}
	
	private void loadTextures() {
		
		TextureLibrary.loadAndSubdivide("textures/level/tileset.png", 16, 16, 16, 16);
		TextureLibrary.loadAndSubdivide("textures/player/player.png", 8, 8, 16, 16);
	}
	
	public void addEntity(Entity entity) {
		
		entities.add(entity);
	}
	
	public void pause() {
		
		paused = true;
	}
	
	public void unpause() {
		
		paused = false;
	}
	
	public void flagForRemoval(Entity entity) {
		
		removals.add(entity);
	}
	
	public void setLevel(Level level) {
		
		this.level = level;
	}
	
	public void resetLevel() {
		
		entities = new ArrayList<Entity>();
		removals = new ArrayList<Entity>();
		
		setLevel(LevelLoader.getLevel(curLevel, this));
		level.spawnPlayer();

		setControls(new GameControls(this));
	}
	
	public void render() {
		
		GL11.glClear(GL11.GL_COLOR_BUFFER_BIT);
	
		if (state == INGAME) {
			level.render();
			
			for (Entity e : entities) {
				
				e.getEntityRenderer().render();
			}
		}
		else if (state == MENU) {
			
			
		}
	}
	
	public void update() {
		
		for (Entity e : entities) {
			
			e.update();
		}
		
		for (Entity e : removals) {
			
			entities.remove(e);
		}
		
		level.update();
		
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
