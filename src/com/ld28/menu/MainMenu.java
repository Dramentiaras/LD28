package com.ld28.menu;

import org.lwjgl.opengl.GL11;
import org.newdawn.slick.Color;

import com.ld28.base.GLSettings;
import com.ld28.handler.GameHandler;
import com.ld28.render.FontRenderer;
import com.ld28.render.RenderUtil;
import com.ld28.texture.TextureLibrary;

public class MainMenu extends Menu {

	private InfoMenu instructions;
	
	public MainMenu(GameHandler game) {
		
		super(game);
		
		addMenuObject("start");
		addMenuObject("instructions");
		addMenuObject("options");
		
		instructions = new InfoMenu(game, this);
		
		instructions.setText("In 4 directions you have to get "
				+ "to the end of the level without touching \n"
				+ "anything except the goal. The twist is that you can only "
				+ "press the same \nbutton once per level. \n"
				+ "\nThe controls are simple. Use <WASD> to move"
				+ " and <SPACE> to fire your laser. \n \n"
				+ "But remember, you can only use the same button once in every level!");
	}
	
	@Override
	public void triggerIndex(int index) {
		
		if (getObject(index) == "start") {
			
			game.startGame();
		}
		if (getObject(index) == "instructions") {
			
			game.enterMenu(instructions);
		}
	}
	
	@Override
	public void render() {
		
		RenderUtil.renderLogo(200, 0);
		
		for (int i = 0; i < getObjectCount(); i++) {
			
			String s = getObject(i);
			
			boolean selected = i == getSelectedIndex();
			
			if (i == getSelectedIndex()) {
				
				s = "< " + s + " >";
				FontRenderer.drawString(GLSettings.WIDTH / 2 - FontRenderer.getStringWidth(s) / 2, GLSettings.HEIGHT / 2 + 50 * i, s, SELECTED_COLOR);
			}
			else {
				
				FontRenderer.drawString(GLSettings.WIDTH / 2 - FontRenderer.getStringWidth(s) / 2, GLSettings.HEIGHT / 2 + 50 * i, s);
			}
		}
		
		FontRenderer.drawString(10, GLSettings.HEIGHT - 20, "use <W> and <S> to navigate. <ENTER> to select.");
		FontRenderer.drawString(10, GLSettings.HEIGHT - 40, "Copyright 2013 Martin Jirlow");
	}
}
