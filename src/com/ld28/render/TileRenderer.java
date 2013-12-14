package com.ld28.render;

import java.util.Random;

import org.lwjgl.opengl.GL11;

import com.ld28.level.Level;
import com.ld28.texture.TextureLibrary;
import com.ld28.tile.Tile;

public class TileRenderer {
	
	private Tile tile;
	private Random random = new Random();
	private int rand;
	
	public TileRenderer(Tile tile) {
		
		this.tile = tile;
	}
	
	public void render(Level level, int x, int y) {
		
		int h = Tile.SIZE;
		
		random.setSeed(x * y + 303 * 7);
		rand = random.nextInt(100);
		
		if (rand >= 50) {
			
			rand = 0;
		}
		else if (rand >= 25) {
			
			rand = 1;
		}
		else {
			
			rand = 2;
		}
		
		boolean[] overlay = new boolean[4];
		
		float tw = TextureLibrary.get(Tile.TILESET + ":" + tile.index).getWidth() / 2 * 0;
		float th = TextureLibrary.get(Tile.TILESET + ":" + tile.index).getWidth() / 2 * 0;
		
		TextureLibrary.bind(Tile.TILESET + ":" + tile.index);
		
		if (tile.id == Tile.wall.id) {
			
			if (x < 49 && level.getTileAt(x + 1, y) != tile.id) {
				
				overlay[0] = true;
			}
			
			if (x > 0 && level.getTileAt(x - 1, y) != tile.id) {
				
				overlay[2] = true;
			}
			
			if (y < 37 && level.getTileAt(x, y + 1) != tile.id) {
				
				overlay[1] = true;
			}
			
			if (y > 0 && level.getTileAt(x, y - 1) != tile.id) {
				
				overlay[3] = true;
			}
		}
		
		GL11.glBegin(GL11.GL_QUADS);
		{
			
			GL11.glTexCoord2f(1 + tw, 0 + th); GL11.glVertex2f(h, 0);
			GL11.glTexCoord2f(0 + tw, 0 + th); GL11.glVertex2f(0, 0);
			GL11.glTexCoord2f(0 + tw, 1 + th); GL11.glVertex2f(0, h);
			GL11.glTexCoord2f(1 + tw, 1 + th); GL11.glVertex2f(h, h);
			
		}
		GL11.glEnd();
		
		if (tile.id == Tile.wall.id) {
			
			boolean randUsed = false;
			
			h /= 2;
			
			for (int i = 0; i < overlay.length; i++) {
				
				if (overlay[i]) {
					
					int index = 16;
					
					if (!randUsed) {
						
						index += rand;
						randUsed = true;
					}
					
					TextureLibrary.bind(Tile.TILESET + ":" + index);
					
					GL11.glPushMatrix();
					{
						
						GL11.glTranslatef(8, 8, 0);
						GL11.glRotatef(i * 90, 0, 0, 1f);
						
						GL11.glBegin(GL11.GL_QUADS);
						{
							
							GL11.glTexCoord2f(1 + tw, 0 + th); GL11.glVertex2f(h, -h);
							GL11.glTexCoord2f(0 + tw, 0 + th); GL11.glVertex2f(-h, -h);
							GL11.glTexCoord2f(0 + tw, 1 + th); GL11.glVertex2f(-h, h);
							GL11.glTexCoord2f(1 + tw, 1 + th); GL11.glVertex2f(h, h);
							
						}
						GL11.glEnd();
					}
					GL11.glPopMatrix();
				}
			}
		}
	}
}
