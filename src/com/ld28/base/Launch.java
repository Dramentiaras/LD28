package com.ld28.base;

import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;

import com.ld28.handler.GameHandler;

public class Launch {

	public static void main(String[] args) {
		
		try {
			
			Display.setDisplayMode(new DisplayMode(800, 608));
			Display.setTitle("4 Directions");
			
			Display.create();
			Mouse.create();
			Keyboard.create();
		}
		catch(LWJGLException ex) {
			
			ex.printStackTrace();
		}
		
		new GameHandler();
	}
}
