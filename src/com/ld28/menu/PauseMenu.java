package com.ld28.menu;

import org.lwjgl.opengl.GL11;

import com.ld28.base.GLSettings;
import com.ld28.handler.GameHandler;
import com.ld28.render.FontRenderer;
import com.ld28.render.RenderUtil;
import com.ld28.texture.TextureLibrary;

public class PauseMenu extends Menu {

	private OptionMenu options;
	private InfoMenu instructions;
	
	public PauseMenu(GameHandler game) {
	
		super(game);
		
		addMenuObject("resume");
		addMenuObject("restart");
		addMenuObject("skip level");
		addMenuObject("instructions");
		addMenuObject("options");
		addMenuObject("main menu");
		
		options = new OptionMenu(game, this);
		instructions = new InfoMenu(game, this);
		
		instructions.setText(MenuTexts.getInstructionsText());
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
			
			game.transition(new MainMenu(game));
		}
		
		if (getObject(index) == "restart") {
			
			game.resetLevel();
			game.enterMenu(null);
		}
		
		if (getObject(index) == "options") {
			
			game.enterMenu(options);
		}
		
		if (getObject(index) == "instructions") {
			
			game.enterMenu(instructions);
		}
		
		if (getObject(index) == "skip level") {
			
			game.enterMenu(null);
			game.nextLevel();
		}
	}
	
	@Override
	public void render() {
		
		GL11.glPushMatrix();
		{
		
			GL11.glTranslatef(150, 0, 0);
			
			GL11.glColor3f(.40f, .35f, .35f);
			
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
