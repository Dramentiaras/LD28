package com.ld28.menu;

import java.util.HashMap;

import com.ld28.controls.MenuControls;
import com.ld28.handler.GameHandler;

public class Menu {

	public GameHandler game;
	private HashMap<Integer, String> menuObjects = new HashMap<Integer, String>();
	private int selObj = 0;
	private int objects = -1;
	
	public Menu(GameHandler game) {
		
		this.game = game;
		game.setControls(new MenuControls(this));
	}
	
	public void addMenuObject(String object) {
		
		if (objects == -1) objects += 1;
		
		menuObjects.put(objects++, object);
	}
	
	public void setSelectedIndex(int obj) {
		
		if (objects == -1) return;
		
		if (obj < 0) obj = 0;
		if (obj > objects) obj = objects;
		
		selObj = obj;
	}
	
	public int getSelectedIndex() {
		
		return selObj;
	}
	
	public int getObjectCount() {
		
		return objects;
	}
	
	public String getObject(int index) {
		
		return menuObjects.get(index);
	}
	
	public void render() {
		
	}
	
	public void triggerIndex(int index) {
		
		
	}
}
