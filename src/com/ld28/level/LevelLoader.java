package com.ld28.level;

import java.util.HashMap;

import org.lwjgl.input.Keyboard;

import com.ld28.entity.EntityPowerup;
import com.ld28.handler.GameHandler;
import com.ld28.tile.Tile;

public class LevelLoader {

	private static HashMap<Integer, Level> levels = new HashMap<Integer, Level>();
	
	public static void loadLevels(GameHandler game) {
		
		Level level = new Level(50, 38, game);
		
		level.name = "Humble Beginnings";
		
		level.setBlock(1, 1, 49, 18, Tile.wall.id);
		level.setBlock(1, 21, 49, 38, Tile.wall.id);
		level.setBlock(2, 19, 47, 20, Tile.carpet.id);
		
		level.setSpawnPoint(2, 19);
		level.setTileAt(47, 19, Tile.door.id);
		
		levels.put(0, level);
		
		level = new Level(50, 38, game);
		
		level.name = "Square";
		
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
		
		level.setBlock(46, 5, 49, 8, Tile.carpet.id);
		
		level.setSpawnPoint(47, 2);
		level.setTileAt(47, 6, Tile.door.id);
		
		levels.put(1, level);
		
		level = new Level(50, 38, game);
		
		level.name = "Well Guarded";
		
		level.setBlock(1, 1, 46, 2, Tile.wall.id);
		level.setBlock(1, 2, 2, 25, Tile.wall.id);
		level.setBlock(1, 25, 46, 37, Tile.wall.id);
		level.setBlock(45, 26, 49, 37, Tile.wall.id);
		
		level.setBlock(3, 3, 13, 24, Tile.wall.id);
		level.setBlock(14, 3, 46, 24, Tile.wall.id);
		level.setBlock(45, 4, 49, 23, Tile.wall.id);
		
		level.setBlock(46, 23, 49, 26, Tile.carpet.id);
		
		level.addPatrol(2, 2, 15, 2);
		
		level.addPatrol(15, 24, 45, 24);
		
		level.setSpawnPoint(47, 2);
		level.setTileAt(47, 24, Tile.door.id);
		
		levels.put(2, level);
		
		level = new Level(50, 38, game);
		
		level.name = "Breaking The Rules";
		
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
		
		level = new Level(50, 38, game);
		
		level.name = "Hashtag";
		
		level.startWithKeyDisabled(Keyboard.KEY_W);
		
		level.setBlock(0, 0, 50, 38, Tile.wall.id);
		
		level.setBlock(9, 16, 40, 20, Tile.floor.id);
		level.setBlock(9, 17, 40, 19, Tile.carpet.id);
		level.setTileAt(24, 15, Tile.floor.id);
		
		level.setBlock(8, 1, 9, 37, Tile.floor.id);
		level.setBlock(40, 1, 41, 37, Tile.floor.id);
		level.setBlock(1, 8, 49, 9, Tile.floor.id);
		level.setBlock(1, 27, 49, 28, Tile.floor.id);
		
		level.setBlock(10, 21, 15, 26, Tile.carpet.id);
		level.setBlock(34, 21, 39, 26, Tile.carpet.id);
		
		level.setBlock(15, 23, 34, 24, Tile.floor.id);
		level.setBlock(24, 24, 25, 27, Tile.floor.id);
		
		level.setBlock(10, 10, 15, 15, Tile.carpet.id);
		level.setBlock(34, 10, 39, 15, Tile.carpet.id);
		
		level.setBlock(15, 12, 34, 13, Tile.floor.id);
		level.setBlock(24, 9, 25, 12, Tile.floor.id);
		
		level.setSpawnPoint(24, 15);
		level.setTileAt(12, 12, Tile.door.id);
		level.setTileAt(36, 12, Tile.door.id);
		level.setTileAt(12, 23, Tile.door.id);
		level.setTileAt(36, 23, Tile.door.id);
		
		level.addPowerup(EntityPowerup.UP, 8, 16);
		level.addPowerup(EntityPowerup.UP, 40, 16);
		
		level.addPowerup(EntityPowerup.DOWN, 8, 19);
		level.addPowerup(EntityPowerup.DOWN, 40, 19);
		
		level.addPowerup(EntityPowerup.UP, 48, 27);
		level.addPowerup(EntityPowerup.LEFT, 47, 27);
		level.addPowerup(EntityPowerup.RIGHT, 2, 27);
		level.addPowerup(EntityPowerup.LEFT, 1, 27);
		
		level.addPowerup(EntityPowerup.RIGHT, 48, 8);
		level.addPowerup(EntityPowerup.LEFT, 47, 8);
		level.addPowerup(EntityPowerup.UP, 2, 8);
		level.addPowerup(EntityPowerup.DOWN, 1, 8);
		
		level.addPowerup(EntityPowerup.RIGHT, 8, 1);
		level.addPowerup(EntityPowerup.UP, 8, 2);
		level.addPowerup(EntityPowerup.SPACE, 8, 35);
		level.addPowerup(EntityPowerup.DOWN, 8, 36);
		
		level.addPowerup(EntityPowerup.SPACE, 40, 1);
		level.addPowerup(EntityPowerup.LEFT, 40, 2);
		level.addPowerup(EntityPowerup.UP, 40, 35);
		level.addPowerup(EntityPowerup.RIGHT, 40, 36);
		
		levels.put(5, level);
		
		level = new Level(50, 38, game);
		
		level.name = "The Great Red Hall";
		
		level.startWithKeyDisabled(Keyboard.KEY_A);
		level.startWithKeyDisabled(Keyboard.KEY_W);
		level.startWithKeyDisabled(Keyboard.KEY_S);
		
		level.setBlock(2, 2, 48, 36, Tile.carpet.id);
		
		level.setBlock(46, 18, 47, 36, Tile.wall.id);
		level.setBlock(22, 1, 23, 5, Tile.wall.id);
		level.setBlock(22, 6, 23, 37, Tile.wall.id);
		
		level.setSpawnPoint(24, 18);
		level.setTileAt(1, 36, Tile.door.id);
		
		level.addPatrol(45, 15, 45, 21);
		level.addPatrol(46, 36, 46, 36);
		level.addPatrol(21, 5, 21, 5);
		
		level.addPowerup(EntityPowerup.LEFT, 45, 18);
		level.addPowerup(EntityPowerup.DOWN, 44, 18);
		level.addPowerup(EntityPowerup.RIGHT, 23, 18);
		level.addPowerup(EntityPowerup.UP, 48, 36);
		level.addPowerup(EntityPowerup.UP, 47, 36);
		level.addPowerup(EntityPowerup.SPACE, 30, 36);
		level.addPowerup(EntityPowerup.SPACE, 29, 36);
		level.addPowerup(EntityPowerup.LEFT, 48, 1);
		level.addPowerup(EntityPowerup.LEFT, 47, 2);
		level.addPowerup(EntityPowerup.DOWN, 23, 1);
		level.addPowerup(EntityPowerup.DOWN, 23, 2);
		level.addPowerup(EntityPowerup.RIGHT, 23, 3);
		level.addPowerup(EntityPowerup.LEFT, 23, 4);
		level.addPowerup(EntityPowerup.DOWN, 29, 3);
		level.addPowerup(EntityPowerup.DOWN, 1, 5);
		level.addPowerup(EntityPowerup.RIGHT, 29, 26);

		levels.put(4, level);
		
		level = new Level(50, 38, game);
		
		level.name = "Ls36g5Lc10+5tab432-T0951";
		
		level.startWithKeyDisabled(Keyboard.KEY_W);
		level.startWithKeyDisabled(Keyboard.KEY_S);
		level.startWithKeyDisabled(Keyboard.KEY_A);
		level.startWithKeyDisabled(Keyboard.KEY_SPACE);
		
		level.setBlock(1, 1, 49, 37, Tile.wall.id);
		level.setBlock(21, 17, 27, 20, Tile.floor.id);
		level.setBlock(22, 18, 26, 19, Tile.carpet.id);
		
		level.setTileAt(25, 18, Tile.door.id);
		level.setSpawnPoint(22, 18);
		level.addPatrol(23, 18, 23, 18);
		
		levels.put(6, level);
	}
	
	public static Level getLevel(int number, GameHandler game) {
		
		if (!levels.containsKey(number)) {
			
			System.out.println("Level " + number + " does not exist");
			return null;
		}
		
		return levels.get(number);
	}
}
