package com.ld28.menu;

import java.util.HashMap;

import org.newdawn.slick.Color;

import com.ld28.controls.MenuControls;
import com.ld28.handler.GameHandler;

public class Menu {

	public GameHandler game;
	private HashMap<Integer, String> menuObjects = new HashMap<Integer, String>();
	private int selObj = 0;
	private int objects = -1;
	
	public static final Color SELECTED_COLOR = new Color(0f, .9f, 0f);
	
	public Menu(GameHandler game) {
		
		this.game = game;
	}
	
	public void takeControl() {
		
		game.setControls(new MenuControls(this));
	}
	
	public void addMenuObject(String object) {
		
		if (objects == -1) objects += 1;
		
		menuObjects.put(objects++, object);
	}
	
	public void removeMenuObject(String object) {
		
		menuObjects.remove(object);
		
		objects -= 1;
	}
	
	public void changeMenuObject(int index, String object) {
		
		menuObjects.put(index, object);
	}
	
	public void setSelectedIndex(int obj) {
		
		if (objects == -1) return;
		
		if (obj < 0) obj = 0;
		if (obj > objects) obj = objects;
		
		selObj = obj;
	}
	
	public boolean shouldRenderGame() {
		
		return false;
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
