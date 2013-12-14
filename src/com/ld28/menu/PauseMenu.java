package com.ld28.menu;

import org.lwjgl.opengl.GL11;

import com.ld28.base.GLSettings;
import com.ld28.handler.GameHandler;
import com.ld28.render.FontRenderer;
import com.ld28.render.RenderUtil;
import com.ld28.texture.TextureLibrary;

public class PauseMenu extends Menu {

	public PauseMenu(GameHandler game) {
	
		super(game);
		
		addMenuObject("resume");
		addMenuObject("options");
		addMenuObject("main menu");
	}
	
	@Override
	public boolean shouldRenderGame() {
		
		return true;
	}
	
	@Override
	public void triggerIndex(int index) {
		
		if (getObject(index) == "resume") {
			
			game.enterMenu(null);
		}
		
		if (getObject(index) == "main menu") {
			
			game.enterMenu(new MainMenu(game));
		}
	}
	
	@Override
	public void render() {
		
		GL11.glPushMatrix();
		{
		
			GL11.glTranslatef(150, 0, 0);
			
			GL11.glColor3f(.35f, .35f, .35f);
			
			GL11.glDisable(GL11.GL_TEXTURE_2D);
			
			GL11.glBegin(GL11.GL_QUADS);
			{
				
				GL11.glVertex2f(500, 0);
				GL11.glVertex2f(0, 0);
				GL11.glVertex2f(0, GLSettings.HEIGHT);
				GL11.glVertex2f(500, GLSettings.HEIGHT);
				
			}
			GL11.glEnd();
			
			GL11.glColor3f(.05f, .05f, .05f);
			
			GL11.glBegin(GL11.GL_LINE_LOOP);
			{
				
				GL11.glVertex2f(500, 0);
				GL11.glVertex2f(0, 0);
				GL11.glVertex2f(0, GLSettings.HEIGHT);
				GL11.glVertex2f(500, GLSettings.HEIGHT);
				
			}
			GL11.glEnd();
			
			GL11.glEnable(GL11.GL_TEXTURE_2D);
		}
		GL11.glPopMatrix();
		
		RenderUtil.renderStandardMenu(this);
	}
}
