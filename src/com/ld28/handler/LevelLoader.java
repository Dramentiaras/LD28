package com.ld28.handler;

import java.util.HashMap;

import com.ld28.level.Level;
import com.ld28.tile.Tile;

public class LevelLoader {

	private static HashMap<Integer, Level> levels = new HashMap<Integer, Level>();
	
	public static void loadLevels(GameHandler game) {
		
		Level level = new Level(50, 38, game);
		level.setTileAt(47, 19, Tile.doorOpen.id);
		
		level.setBlock(1, 1, 49, 18, 1);
		level.setBlock(1, 21, 49, 38, 1);
		
		level.setSpawnPoint(2, 19);
		
		levels.put(0, level);
	}
	
	public static Level getLevel(int number, GameHandler game) {
		
		if (!levels.containsKey(number)) {
			
			System.out.println("Level " + number + " does not exist");
			return null;
		}
		
		return levels.get(number);
	}
}
