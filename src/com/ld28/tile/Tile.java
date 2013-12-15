package com.ld28.tile;

import com.ld28.render.TileRenderer;

public class Tile {

	public static Tile[] tiles = new Tile[256];
	
	public static final int SIZE = 16;
	public static final String TILESET = "assets";
	
	public static final Tile floor = new Tile(0, 1).setObstacle(false);
	public static final Tile wall = new Tile(1, 0).setBorder(16, true);
	public static final Tile carpet = new Tile(2, 3).setObstacle(false).setBorder(19, true);
	public static final Tile door = new Tile(3, 2).setObstacle(false);
	
	
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
	
	public Tile setBorder(int index, boolean bordered) {
		
		getTileRenderer().setBordered(bordered);
		getTileRenderer().setBorderIndex(index);
		return this;
	}
}
