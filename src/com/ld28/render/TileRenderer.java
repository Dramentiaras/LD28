package com.ld28.render;

import org.lwjgl.opengl.GL11;

import com.ld28.texture.TextureLibrary;
import com.ld28.tile.Tile;

public class TileRenderer {
	
	private Tile tile;
	
	public TileRenderer(Tile tile) {
		
		this.tile = tile;
	}
	
public void render() {
		
		int h = Tile.SIZE / 2;
		
		float tw = TextureLibrary.get(Tile.TILESET + ":" + tile.index).getWidth() / 2 * 0;
		float th = TextureLibrary.get(Tile.TILESET + ":" + tile.index).getWidth() / 2 * 0;
		
		TextureLibrary.bind(Tile.TILESET + ":" + tile.index);
		
		GL11.glBegin(GL11.GL_QUADS);
		{
			
			GL11.glTexCoord2f(1 + tw, 0 + th); GL11.glVertex2f(h, -h);
			GL11.glTexCoord2f(0 + tw, 0 + th); GL11.glVertex2f(-h, -h);
			GL11.glTexCoord2f(0 + tw, 1 + th); GL11.glVertex2f(-h, h);
			GL11.glTexCoord2f(1 + tw, 1 + th); GL11.glVertex2f(h, h);
			
		}
		GL11.glEnd();
	}
}
