package com.ld28.base;

import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;

public class GLSettings {

	public static int WIDTH, HEIGHT;
	
	public static void initGL() {
		
		GL11.glMatrixMode(GL11.GL_PROJECTION);
		GL11.glLoadIdentity();
		
		GL11.glClearColor(1f, 1f, 1f, 0f);
		
		WIDTH = Display.getWidth() / 2;
		HEIGHT = Display.getHeight() / 2;
		
		GL11.glDisable(GL11.GL_DEPTH_TEST);
		GL11.glViewport(0, 0, Display.getWidth(), Display.getHeight());
		GL11.glOrtho(0, WIDTH, HEIGHT, 0, -1, 1);
		
		GL11.glMatrixMode(GL11.GL_MODELVIEW);
		
		GL11.glEnable(GL11.GL_TEXTURE_2D);

		GL11.glEnable(GL11.GL_BLEND);
		GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
		GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MIN_FILTER, GL11.GL_NEAREST);
        GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_NEAREST);
	}
}
