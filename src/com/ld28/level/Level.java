package com.ld28.level;

import java.util.Random;

import org.lwjgl.opengl.GL11;

import com.ld28.base.GLSettings;
import com.ld28.handler.GameHandler;
import com.ld28.tile.Tile;

public class Level {
	
	private GameHandler game;
	private Random random;
	private int[][] level;
	
	public float xOffset, yOffset;
	
	public Level(int width, int height, GameHandler game) {
		
		this.game = game;
		random = new Random();
		
		level = new int[width][height];
		load();
	}
	
	public void setOffset(float x, float y) {
		
		xOffset = x;
		yOffset = y;
	}
	
	public void setTileAt(int x, int y, int id) {
		
		level[x][y] = id;
	}
	
	public int getTileAt(int x, int y) {
		
		return level[x][y];
	}
	
	public void render() {
		
		for (int x = 0; x < level.length; x++) {
			for (int y = 0; y < level.length; y++) {
				
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
	
	public void load() {
		
		for (int i = 0; i < level.length; i++) {
			
			if (level[i].length > 5) {
				
				level[i][5] = Tile.wall.id;
			}
		}
	}
}
