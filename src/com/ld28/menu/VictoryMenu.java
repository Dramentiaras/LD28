package com.ld28.menu;

import org.lwjgl.opengl.GL11;

import com.ld28.base.GLSettings;
import com.ld28.handler.GameHandler;
import com.ld28.render.FontRenderer;
import com.ld28.render.RenderUtil;

public class VictoryMenu extends Menu {

	public VictoryMenu(GameHandler game) {
		
		super(game);
		addMenuObject("main menu");
	}
	
	@Override
	public void triggerIndex(int index) {
		
		if (getObject(index) == "main menu") {
			
			game.transition(new MainMenu(game));
		}
	}
	
	@Override
	public void render() {
		
		GL11.glPushMatrix();
		{
			
			GL11.glColor3f(.40f, .35f, .35f);
			
			GL11.glDisable(GL11.GL_TEXTURE_2D);
			
			GL11.glBegin(GL11.GL_QUADS);
			{
				
				GL11.glVertex2f(0, 0);
				GL11.glVertex2f(GLSettings.WIDTH, 0);
				GL11.glVertex2f(GLSettings.WIDTH, GLSettings.HEIGHT);
				GL11.glVertex2f(0, GLSettings.HEIGHT);
				
			}
			GL11.glEnd();
			
		}
		GL11.glPopMatrix();
		
		GL11.glPushMatrix();
		{
		
			GL11.glTranslatef(135, 0, 0);
			
			GL11.glColor3f(.35f, .35f, .35f);
			
			GL11.glBegin(GL11.GL_QUADS);
			{
				
				GL11.glVertex2f(530, 0);
				GL11.glVertex2f(0, 0);
				GL11.glVertex2f(0, GLSettings.HEIGHT);
				GL11.glVertex2f(530, GLSettings.HEIGHT);
				
			}
			GL11.glEnd();
			
			GL11.glColor3f(.05f, .05f, .05f);
			
			GL11.glBegin(GL11.GL_LINE_LOOP);
			{
				
				GL11.glVertex2f(530, 0);
				GL11.glVertex2f(0, 0);
				GL11.glVertex2f(0, GLSettings.HEIGHT);
				GL11.glVertex2f(530, GLSettings.HEIGHT);
				
			}
			GL11.glEnd();
			
			GL11.glEnable(GL11.GL_TEXTURE_2D);
		}
		GL11.glPopMatrix();
		
		RenderUtil.renderLogo(200, 0);
		
		for (int i = 0; i < getObjectCount(); i++) {
			
			String s = getObject(i);
			
			if (i == getSelectedIndex()) {
				
				s = "< " + s + " >";
				FontRenderer.drawString(GLSettings.WIDTH / 2 - FontRenderer.getStringWidth(s) / 2, GLSettings.HEIGHT / 2 + 150 + 50 * i, s, Menu.SELECTED_COLOR);
			}
			else {
				
				FontRenderer.drawString(GLSettings.WIDTH / 2 - FontRenderer.getStringWidth(s) / 2, GLSettings.HEIGHT / 2 + 150 + 50 * i, s);
			}
		}
		
		String message = "Congratulations, you completed 4 Directions!";
		
		String message2 = "This was created by Martin Jirlow for the";
		String message3 = "28:th Ludum Dare 48h compo";
		String message4 = "www.ludumdare.com";
		
		FontRenderer.drawString(GLSettings.WIDTH / 2 - FontRenderer.getStringWidth(message) / 2, 250, message);
		FontRenderer.drawString(GLSettings.WIDTH / 2 - FontRenderer.getStringWidth(message2) / 2, 310, message2);
		FontRenderer.drawString(GLSettings.WIDTH / 2 - FontRenderer.getStringWidth(message3) / 2, 345, message3);
		FontRenderer.drawString(GLSettings.WIDTH / 2 - FontRenderer.getStringWidth(message4) / 2, 380, message4);
	}
}
