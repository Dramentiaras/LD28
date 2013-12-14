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
import com.ld28.menu.MainMenu;
import com.ld28.menu.Menu;
import com.ld28.render.FontRenderer;
import com.ld28.texture.TextureLibrary;

public class GameHandler {

	public ArrayList<Entity> entities;
	private ArrayList<Entity> removals;
	private Controls controls;
	public Level level;
	
	public Menu menu;
	
	private boolean paused;
	private boolean renderGame = false;
	
	private int curLevel = 0;
	
	public static final int MENU = 0, INGAME = 1;
	
	private int state = MENU;
	
	public EntityPlayer player;
	
	public GameHandler() {
		
		GLSettings.initGL();
		
		loadTextures();
		LevelLoader.loadLevels(this);
		
		FontRenderer.init();
		
		enterMenu(new MainMenu(this));
		
		loop();
		
		clean();
	}
	
	public void setState(int state) {
		
		this.state = state;
	}
	
	public void startGame() {
		
		state = INGAME;
		
		menu = null;
		
		entities = new ArrayList<Entity>();
		removals = new ArrayList<Entity>();
		
		setLevel(LevelLoader.getLevel(curLevel, this));
		level.reset();
		level.spawnPlayer();

		setControls(new GameControls(this));
	}
	
	public void nextLevel() {
		
	}
	
	public void enterMenu(Menu menu) {

		if (menu == null) {
			
			state = INGAME;
			this.menu = menu;
			setControls(new GameControls(this));
			return;
		}
		
		this.menu = menu;
		state = MENU;
		menu.takeControl();
		renderGame = menu.shouldRenderGame();
	}
	
	private void loadTextures() {
		
		TextureLibrary.load("textures/logo.png");
		
		TextureLibrary.loadAndSubdivide("textures/level/tileset.png", 16, 16, 16, 16);
		TextureLibrary.loadAndSubdivide("textures/player/player.png", 1, 8, 16, 16);
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
	
	public void resetLevel() {
		
		entities = new ArrayList<Entity>();
		removals = new ArrayList<Entity>();
		
		setLevel(LevelLoader.getLevel(curLevel, this));
		level.reset();
		level.spawnPlayer();

		setControls(new GameControls(this));
	}
	
	public void render() {
		
		GL11.glClear(GL11.GL_COLOR_BUFFER_BIT);
	
		if (state == INGAME || renderGame) {
			
			level.render();
			
			for (Entity e : entities) {
				
				e.getEntityRenderer().render();
			}
		}
		if (state == MENU) {
			
			menu.render();
		}
	}
	
	public void update() {
		
		if (state == INGAME) {
			
			for (Entity e : entities) {
				
				e.update();
			}
			
			for (Entity e : removals) {
				
				entities.remove(e);
			}
			
			level.update();
			
			removals = new ArrayList<Entity>();
		}
		
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
