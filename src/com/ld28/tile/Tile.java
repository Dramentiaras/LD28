package com.ld28.tile;

import java.util.ArrayList;

import com.ld28.render.TileRenderer;

public class Tile {

	public static Tile[] tiles = new Tile[256];
	
	public static final int SIZE = 16;
	public static final String TILESET = "tileset";
	
	public static final Tile air = new Tile(0, 0).setShouldRender(false);
	public static final Tile wall = new Tile(1, 0);
	
	public int id, index;
	private TileRenderer renderer;
	private boolean render = true;
	private boolean obstacle = true;
	
	public Tile(int id, int index) {
		
		this.id = id;
		this.index = index;
		
		if (tiles[id] != null) {
			
			System.out.println("Tile with id: " + id + " does already exist. Overwriting it.");
		}
		
		tiles[id] = this;
		
		renderer = new TileRenderer(this);
	}
	
	public TileRenderer getTileRenderer() {
		
		return renderer;
	}
	
	public Tile setTileRenderer(TileRenderer renderer) {
		
		this.renderer = renderer;
		
		return this;
	}
	
	public Tile setShouldRender(boolean render) {
		
		this.render = render;
		
		return this;
	}
	
	public boolean shouldRender() {
		
		return render;
	}
	
	public Tile setObstacle(boolean obstacle) {
		
		this.obstacle = obstacle;
		
		return this;
	}
	
	public boolean isObstacle() {
		
		return obstacle;
	}
}
