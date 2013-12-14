package com.ld28.input;

import java.util.ArrayList;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;

public class Input {

	private static boolean[] keys = new boolean[256];
	private static boolean[] buttons = new boolean[3];
	
	public static void update() {
		
		for (int i = 0; i < keys.length; i++) {
			
			keys[i] = Keyboard.isKeyDown(i);
		}
		
		for (int i = 0; i < buttons.length; i++) {
			
			buttons[i] = Mouse.isButtonDown(i);
		}
	}
	
	public static boolean isKeyPressed(int key) {
		
		return Keyboard.isKeyDown(key) && !keys[key];
	}
	
	public static boolean isKeyReleased(int key) {
		
		 return !Keyboard.isKeyDown(key) && keys[key];
	}
	
	public static boolean isKeyDown(int key) {
		
		return Keyboard.isKeyDown(key);
	}
	
	public static boolean isButtonPressed(int button) {
		
		return Mouse.isButtonDown(button) && !buttons[button];
	}
	
	public static boolean isButtonReleased(int button) {
		
		return !Mouse.isButtonDown(button) && buttons[button];
	}
	
	public static boolean isButtonDown(int button) {
		
		return Mouse.isButtonDown(button);
	}
	
	public static int getMouseX() {
		
		return Mouse.getX();
	}
	
	public static int getMouseY() {
		
		return Mouse.getY();
	}
}
