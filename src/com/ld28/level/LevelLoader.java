package com.ld28.level;

import java.util.HashMap;

import com.ld28.entity.EntityPowerup;
import com.ld28.handler.GameHandler;
import com.ld28.tile.Tile;

public class LevelLoader {

	private static HashMap<Integer, Level> levels = new HashMap<Integer, Level>();
	
	public static void loadLevels(GameHandler game) {
		
		Level level = new Level(50, 38, game);
		
		level.name = "Humble Beginnings";
		
		level.setTileAt(47, 19, Tile.door.id);
		
		level.setBlock(1, 1, 49, 18, 1);
		level.setBlock(1, 21, 49, 38, 1);
		
		level.setSpawnPoint(2, 19);
		
		levels.put(0, level);
		
		level = new Level(50, 38, game);
		
		level.name = "Square";
		
		level.setSpawnPoint(47, 2);
		level.setTileAt(47, 6, Tile.door.id);
		
		level.setBlock(1, 1, 46, 2, Tile.wall.id);
		level.setBlock(1, 2, 2, 37, Tile.wall.id);
		level.setBlock(1, 36, 49, 37, Tile.wall.id);
		level.setBlock(48, 8, 49, 37, Tile.wall.id);
		
		level.setBlock(3, 3, 35, 7, Tile.wall.id);
		level.setBlock(35, 3, 46, 6, Tile.wall.id);
		level.setBlock(46, 4, 49, 5, Tile.wall.id);
		
		level.setBlock(36, 7, 46, 35, Tile.wall.id);
		level.setBlock(3, 8, 36, 35, Tile.wall.id);
		level.setBlock(46, 8, 47, 35, Tile.wall.id);
		
		levels.put(1, level);
		
		level = new Level(50, 38, game);
		
		level.name = "Well Guarded";
		
		level.setSpawnPoint(47, 2);
		level.setTileAt(47, 24, Tile.door.id);
		
		level.setBlock(1, 1, 46, 2, Tile.wall.id);
		level.setBlock(1, 2, 2, 25, Tile.wall.id);
		level.setBlock(1, 25, 46, 37, Tile.wall.id);
		level.setBlock(45, 26, 49, 37, Tile.wall.id);
		
		level.setBlock(3, 3, 13, 24, Tile.wall.id);
		level.setBlock(14, 3, 46, 24, Tile.wall.id);
		level.setBlock(45, 4, 49, 23, Tile.wall.id);
		
		level.addPatrol(2, 2, 15, 2);
		
		level.addPatrol(15, 24, 45, 24);
		
		levels.put(2, level);
		
		level = new Level(50, 38, game);
		
		level.name = "Breaking the Rules";
		
		level.setBlock(0, 0, 50, 38, Tile.wall.id);
		
		level.setBlock(2, 11, 3, 25, Tile.floor.id);
		level.setBlock(2, 25, 36, 26, Tile.floor.id);
		level.setBlock(35, 25, 36, 33, Tile.floor.id);
		level.setBlock(9, 26, 10, 35, Tile.floor.id);
		level.setBlock(10, 34, 38, 35, Tile.carpet.id);
		level.setBlock(37, 26, 38, 35, Tile.floor.id);
		level.setBlock(37, 25, 48, 26, Tile.floor.id);
		level.setBlock(47, 11, 48, 25, Tile.floor.id);
		
		level.setSpawnPoint(25, 34);
		level.setTileAt(47, 10, Tile.door.id);
		level.setTileAt(2, 10, Tile.door.id);
		
		level.addPowerup(EntityPowerup.LEFT, 35, 32);
		level.addPowerup(EntityPowerup.UP, 2, 25);
		
		levels.put(3, level);
	}
	
	public static Level getLevel(int number, GameHandler game) {
		
		if (!levels.containsKey(number)) {
			
			System.out.println("Level " + number + " does not exist");
			return null;
		}
		
		return levels.get(number);
	}
}
