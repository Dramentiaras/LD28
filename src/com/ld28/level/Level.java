package com.ld28.level;

import java.awt.Rectangle;
import java.util.ArrayList;

import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;
import org.newdawn.slick.Color;

import com.ld28.base.GLSettings;
import com.ld28.entity.Entity;
import com.ld28.entity.EntityGuard;
import com.ld28.entity.EntityPlayer;
import com.ld28.entity.EntityPowerup;
import com.ld28.handler.GameHandler;
import com.ld28.physics.Collision;
import com.ld28.texture.TextureLibrary;
import com.ld28.tile.Tile;

public class Level {
	
	private GameHandler game;
	private int[][] level;
	public float gravity = .2f;
	private int width, height;
	
	private boolean[] keys = new boolean[256];
	
	private int playerXSpawn;
	private int playerYSpawn;

	public float xOffset, yOffset;
	public String name = "";
	
	private ArrayList<int[]> patrols = new ArrayList<int[]>();
	private ArrayList<int[]> powerups = new ArrayList<int[]>();
	private ArrayList<Boolean> powerupsDisable = new ArrayList<Boolean>();
	private ArrayList<Integer> disableKeys = new ArrayList<Integer>();
	
	public Level(int width, int height, GameHandler game) {
		
		this.game = game;
		
		this.width = width;
		this.height = height;
		
		level = new int[width][height];
		load();
	}
	
	public void spawnPlayer() {
		
		game.player = new EntityPlayer(playerXSpawn * Tile.SIZE + Tile.SIZE / 2, playerYSpawn * Tile.SIZE + Tile.SIZE / 2, game);
		game.addEntity(game.player);
	}
	
	public void setOffset(float x, float y) {
		
		xOffset = x;
		yOffset = y;
	}
	
	public void setTileAt(int x, int y, int id) {
		
		level[x][y] = id;
	}
	
	public void setSpawnPoint(int x, int y) {
		
		playerXSpawn = x;
		playerYSpawn = y;
	}
	
	public void addPatrol(int x1, int y1, int x2, int y2) {
		
		patrols.add(new int[] {x1, y1, x2, y2});
	}
	
	public void addPowerup(int type, int x, int y) {
		
		powerups.add(new int[] {type, x, y});
		powerupsDisable.add(false);
	}
	
	public void addPowerup(int type, int x, int y, boolean disable) {
		
		powerups.add(new int[] {type, x, y});
		powerupsDisable.add(false);
	}
	
	private void deployPatrols() {
		
		for (int[] i : patrols) {
			
			EntityGuard guard;
			
			if (i[0] != i[2]) {
				
				guard = new EntityGuard(i[0], i[1], i[2], game);
			}
			else {
				
				guard = new EntityGuard(i[0], i[1], i[2], i[3], game);
			}
			
			game.addEntity(guard);
		}
	}
	
	private void deployPowerups() {
		
		for (int j = 0; j < powerups.size(); j++) {
			
			int[] i = powerups.get(j);
			
			EntityPowerup powerup = new EntityPowerup(i[0], i[1] * Tile.SIZE + Tile.SIZE / 2, i[2] * Tile.SIZE + Tile.SIZE / 2, powerupsDisable.get(j), game);
			game.addEntity(powerup);
		}
	}
	
	public void setBlock(int x1, int y1, int x2, int y2, int id) {
		
		for (int x = x1; x < x2; x++) {
			for (int y = y1; y < y2; y++) {
				
				setTileAt(x, y, id);
			}
		}
	}
	
	public int[][] toArray() {
		
		return level;
	}
	
	public void setArray(int[][] data) {
		
		if (data.length != width || data[0].length != height) {
			
			System.out.println("Invalid level size");
		}
		
		level = data;
	}
	
	public int getTileAt(int x, int y) {
		
		return level[x][y];
	}
	
	public void render() {
		
		for (int x = 0; x < level.length; x++) {
			for (int y = 0; y < level[x].length; y++) {
				
				int var0 = (int) (x * Tile.SIZE + xOffset);
				int var1 = (int) (y * Tile.SIZE + yOffset);
				
				if (Tile.tiles[level[x][y]].shouldRender() && var0 > -16 && var0 < GLSettings.WIDTH + 16
						&& var1 > -16 && var1 < GLSettings.HEIGHT + 16) {
					
					GL11.glPushMatrix();
					{
						
						GL11.glTranslatef(x * Tile.SIZE + xOffset, y * Tile.SIZE + yOffset, 0);
						
						GL11.glColor3f(1f, 1f, 1f);
						Tile.tiles[level[x][y]].getTileRenderer().render(this, x, y);
					}
					GL11.glPopMatrix();
				}
			}
		}
			
		for (int i = 0; i < 5; i++) {
			
			GL11.glPushMatrix();
			{
				
				GL11.glTranslatef(GLSettings.WIDTH / 2 - 54 + i * 32, GLSettings.HEIGHT - 16, 0);
				
				switch (i) {
				
					case 0: {
						
						if (keys[Keyboard.KEY_A] == true) {
							
							GL11.glColor4f(1f, 1f, 1f, .35f);
						}
						else {
							
							GL11.glColor4f(1f, 1f, 1f, 1f);
						}
						
						TextureLibrary.bind("assets:14");
						break;
					}
					case 1: {
						
						if (keys[Keyboard.KEY_S] == true) {
							
							GL11.glColor4f(1f, 1f, 1f, .35f);
						}
						else {
							
							GL11.glColor4f(1f, 1f, 1f, 1f);
						}
						
						TextureLibrary.bind("assets:30");
						break;
					}
					case 2: {
						
						if (keys[Keyboard.KEY_D] == true) {
							
							GL11.glColor4f(1f, 1f, 1f, .35f);
						}
						else {
							
							GL11.glColor4f(1f, 1f, 1f, 1f);
						}
						
						TextureLibrary.bind("assets:31");
						break;
					}
					case 3: {
						
						if (keys[Keyboard.KEY_W] == true) {
							
							GL11.glColor4f(1f, 1f, 1f, .35f);
						}
						else {
							
							GL11.glColor4f(1f, 1f, 1f, 1f);
						}
						
						TextureLibrary.bind("assets:15");
						break;
					}
					case 4: {
						
						if (keys[Keyboard.KEY_SPACE] == true) {
							
							GL11.glColor4f(1f, 1f, 1f, 1f);
							TextureLibrary.bind("assets:47");
						}
						else {
							
							GL11.glColor4f(1f, 1f, 1f, 1f);
							TextureLibrary.bind("assets:46");
						}
						break;
					}
				}
				
				GL11.glBegin(GL11.GL_QUADS);
				{
					
					GL11.glTexCoord2f(1, 0); GL11.glVertex2f(16, 0);
					GL11.glTexCoord2f(0, 0); GL11.glVertex2f(0, 0);
					GL11.glTexCoord2f(0, 1); GL11.glVertex2f(0, 16);
					GL11.glTexCoord2f(1, 1); GL11.glVertex2f(16, 16);
					
				}
				GL11.glEnd();
			}
			GL11.glPopMatrix();
		}
	}
	
	public void update() {
		
		for (Entity e : game.entities) {
			
			for (int x = 0; x < level.length; x++) {
				for (int y = 0; y <= level[x].length; y++) {
					
					Rectangle r1 = new Rectangle(x * Tile.SIZE, y * Tile.SIZE, Tile.SIZE, Tile.SIZE);
					Rectangle r2 = new Rectangle((int) (e.x + e.motionX - e.colWidth / 2), (int) (e.y + e.motionY - e.colHeight / 2), (int) e.colWidth, (int) e.colHeight);
					
					if (Collision.isColliding(r1, r2)) {
						
						e.onTileCollision(x, y, level[x][y], this);
					}
 				}
			}
		}
	}
	
	public void startWithKeyDisabled(int key) {
		
		disableKeys.add(key);
	}
	
	public void load() {
		
		for (int x = 0; x < level.length; x++) {
			for (int y = 0; y < level[x].length; y++) {
				
				if (x == 0 || x == level.length - 1) {
					
					level[x][y] = Tile.wall.id;
				}
				else if (y == 0 || y == level[x].length - 1) {
					
					level[x][y] = Tile.wall.id;
				}
			}
		}
	}
	
	public boolean isKeyPressed(int key) {
		
		return keys[key];
	}
	
	public void setKeyPressed(int key) {
		
		keys[key] = true;
	}
	
	public void resetPressedKey(int key) {
		
		keys[key] = false;
	}
	
	public void reset() {
		
		keys = new boolean[256];
		deployPowerups();
		deployPatrols();
		
		for (int i : disableKeys) {
			
			setKeyPressed(i);
		}
	}
}
