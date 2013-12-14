package com.ld28.level;

import java.awt.Rectangle;
import java.util.Random;

import org.lwjgl.opengl.GL11;

import com.ld28.base.GLSettings;
import com.ld28.entity.Entity;
import com.ld28.entity.EntityPlayer;
import com.ld28.handler.GameHandler;
import com.ld28.physics.Collision;
import com.ld28.tile.Tile;

public class Level {
	
	private GameHandler game;
	private Random random;
	private int[][] level;
	public float gravity = .2f;
	private int width, height;
	
	private int playerXSpawn;
	private int playerYSpawn;

	public float xOffset, yOffset;
	
	public Level(int width, int height, GameHandler game) {
		
		this.game = game;
		random = new Random();
		
		this.width = width;
		this.height = height;
		
		level = new int[width][height];
		load();
	}
	
	public void spawnPlayer() {
		
		game.player = new EntityPlayer(playerXSpawn * Tile.SIZE + 8, playerYSpawn * Tile.SIZE + 8, game);
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
						Tile.tiles[level[x][y]].getTileRenderer().render();
					}
					GL11.glPopMatrix();
				}
			}
		}
	}
	
	public void update() {
		
		for (Entity e : game.entities) {
			
			for (int x = 0; x < level.length; x++) {
				for (int y = 0; y <= level[x].length; y++) {
					
					Rectangle r1 = new Rectangle(x * Tile.SIZE, y * Tile.SIZE, Tile.SIZE, Tile.SIZE);
					Rectangle r2 = new Rectangle((int) (e.x - e.colWidth / 2), (int) (e.y - e.colHeight / 2), (int) e.colWidth, (int) e.colHeight);
					
					if (Collision.isColliding(r1, r2)) {
						
						e.onTileCollision(x, y, level[x][y], this);
					}
 				}
			}
		}
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
}
