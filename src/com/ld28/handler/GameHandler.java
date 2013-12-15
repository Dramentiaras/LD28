package com.ld28.handler;

import java.util.ArrayList;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;

import com.ld28.audio.SoundSystem;
import com.ld28.base.GLSettings;
import com.ld28.controls.Controls;
import com.ld28.controls.GameControls;
import com.ld28.entity.Entity;
import com.ld28.entity.EntityPlayer;
import com.ld28.input.Input;
import com.ld28.level.Level;
import com.ld28.level.LevelLoader;
import com.ld28.menu.MainMenu;
import com.ld28.menu.Menu;
import com.ld28.menu.VictoryMenu;
import com.ld28.physics.Collision;
import com.ld28.render.FontRenderer;
import com.ld28.texture.TextureLibrary;

public class GameHandler {

	public ArrayList<Entity> entities;
	private ArrayList<Entity> removals;
	private Controls controls;
	public Level level;
	private float pane1, pane2;
	
	public Menu menu;
	
	private boolean transitioning = false;
	private boolean renderGame = false;
	private String transitioningMessage = "";
	
	private int ticksTransComplete = 0;
	private int transTask = -1;
	private Menu transMenu = null;
	
	public static final int LEVEL_SWITCH = 0, GAME_START = 1, MENU_SWITCH = 2;
	
	private int curLevel;
	
	public static final int MENU = 0, INGAME = 1;
	
	private int state = MENU;
	
	public EntityPlayer player;
	
	public GameHandler() {
		
		GLSettings.initGL();
		
		pane1 = GLSettings.WIDTH / 2 + 4;
		pane2 = GLSettings.WIDTH / 2 + 4;
		ticksTransComplete = 180;
		FontRenderer.init();
		
		loadTextures();
		loadAudio();
		LevelLoader.loadLevels(this);
		
		SoundSystem.initAL();
		
		transition(new MainMenu(this));
		
		SoundSystem.loop("soundtrack1");
		
		loop();
		
		clean();
	}
	
	public void setState(int state) {
		
		this.state = state;
	}
	
	public void startGame() {
		
		transition(GAME_START);
	}
	
	public void transition(int task) {
		
		this.transTask = task;
		
		transitioning = true;
		ticksTransComplete = 0;
	}
	
	public void transition(Menu menu) {
		
		transition(MENU_SWITCH);
		transMenu = menu;
	}
	
	public void nextLevel() {
		
		Level lvl = LevelLoader.getLevel(curLevel + 1, this);
		
		if (lvl == null) {
			
			transition(new VictoryMenu(this));
			return;
		}
		
		transition(LEVEL_SWITCH);
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
		
		TextureLibrary.loadAndSubdivide("textures/assets.png", 16, 16, 16, 16);
	}
	
	private void loadAudio() {
		
		SoundSystem.load("sounds/player/death0.wav");
		SoundSystem.load("sounds/player/death1.wav");
		SoundSystem.load("sounds/player/laser.wav");
		SoundSystem.load("sounds/player/laser_hit.wav");
		SoundSystem.load("sounds/guard/guard_death.wav");
		SoundSystem.load("sounds/tracks/soundtrack1.wav");
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
		
		if (transitioning) {
			
			GL11.glPushMatrix();
			{

				GL11.glColor3f(0f, 0f, 0f);
				
				GL11.glDisable(GL11.GL_TEXTURE_2D);
				
				GL11.glBegin(GL11.GL_QUADS);
				{
					
					GL11.glVertex2f(0, 0);
					GL11.glVertex2f(0, GLSettings.HEIGHT);
					GL11.glVertex2f(pane1, GLSettings.HEIGHT);
					GL11.glVertex2f(pane1, 0);
					
				}
				GL11.glEnd();
			}
			GL11.glPopMatrix();
			
			GL11.glPushMatrix();
			{
				GL11.glTranslatef(GLSettings.WIDTH - pane2, 0, 0);
				
				GL11.glBegin(GL11.GL_QUADS);
				{
					
					GL11.glVertex2f(0, 0);
					GL11.glVertex2f(0, GLSettings.HEIGHT);
					GL11.glVertex2f(pane2, GLSettings.HEIGHT);
					GL11.glVertex2f(pane2, 0);
					
				}
				GL11.glEnd();
				
				GL11.glEnable(GL11.GL_TEXTURE_2D);
			}
			GL11.glPopMatrix();
			
			if (ticksTransComplete < 90 && ticksTransComplete > 30) {
				
				FontRenderer.drawString(GLSettings.WIDTH / 2 - FontRenderer.getStringWidth(transitioningMessage) / 2,
						GLSettings.HEIGHT / 2 - FontRenderer.getStringHeight(transitioningMessage) / 2, transitioningMessage);
			}
		}
	}
	
	public int getCurrentLevel() {
		
		return curLevel;
	}
	
	public void update() {
		
		if (!transitioning) {
			if (state == INGAME) {
				
				for (Entity e : entities) {
					
					e.update();
				}
				
				for (Entity e : entities) {
					
					for (Entity e1 : entities) {
						
						if (e != e1) {
							
							if (Collision.isColliding(e, e1) && !e.isDead() && !e1.isDead()) {
								
								e.onEntityCollision(e1);
							}
						}
					}
				}
				
				for (Entity e : removals) {
					
					entities.remove(e);
				}
				
				removals = new ArrayList<Entity>();
				
				level.update();
			}
	
			controls.check();
				
			Input.update();
		}
		else {
			
			if (pane1 <= GLSettings.WIDTH / 2 && ticksTransComplete == 0) {
				
				pane1 += 8;
			}
			
			if (pane2 <= GLSettings.WIDTH / 2 && ticksTransComplete == 0) {
				
				pane2 += 8;
			}
			
			if (pane1 > GLSettings.WIDTH / 2 && pane2 > GLSettings.WIDTH / 2 && ticksTransComplete < 120) {
				
				if (ticksTransComplete == 0) {
					execTransitionTask();
				}
				
				ticksTransComplete++;
			}
			else if (ticksTransComplete >= 120) {
				
				if (pane1 > 0) {
					
					pane1 -= 8;
				}
				
				if (pane2 > 0) {
					
					pane2 -= 8;
				}
				
				if (pane1 <= 0 && pane2 <= 0) {
					
					transitioning = false;
					transitioningMessage = "";
				}
			}
		}
	}
	
	public void execTransitionTask() {
		
		if (transTask == LEVEL_SWITCH) {
			
			entities = new ArrayList<Entity>();
			removals = new ArrayList<Entity>();
			Level lvl = LevelLoader.getLevel(++curLevel, this);
			
			if (lvl == null) {
				
				
				enterMenu(new VictoryMenu(this));
				transitioning = false;
			}
			else {
				
				setLevel(lvl);
				
				transitioningMessage = (curLevel != 6 ? "Level " + (curLevel + 1) + " - ":"") + level.name;
				
				level.reset();
				level.spawnPlayer();
				
				setControls(new GameControls(this));
			}
		}
		
		if (transTask == GAME_START) {
			
			state = INGAME;
			
			menu = null;
			curLevel = 0;
			
			entities = new ArrayList<Entity>();
			removals = new ArrayList<Entity>();
			
			setLevel(LevelLoader.getLevel(curLevel, this));
			
			transitioningMessage = "Level " + (curLevel + 1) + " - " + level.name; 
			
			level.reset();
			level.spawnPlayer();

			setControls(new GameControls(this));
		}
		
		if (transTask == MENU_SWITCH) {
			
			transitioningMessage = "Loading...";
			enterMenu(transMenu);
		}
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
		
		SoundSystem.destroy();
	}
}
