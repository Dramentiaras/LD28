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
	private InfoMenu credits;
	private OptionMenu options;
	
	public MainMenu(GameHandler game) {
		
		super(game);
		
		addMenuObject("start");
		addMenuObject("instructions");
		addMenuObject("options");
		addMenuObject("credits");
		
		options = new OptionMenu(game, this);
		
		instructions = new InfoMenu(game, this);
		
		instructions.setText(MenuTexts.getInstructionsText());
		
		credits = new InfoMenu(game, this);
		
		credits.setText(MenuTexts.getCreditsText());
	}
	
	@Override
	public void triggerIndex(int index) {
		
		if (getObject(index) == "start") {
			
			game.startGame();
		}
		if (getObject(index) == "instructions") {
			
			game.enterMenu(instructions);
		}
		
		if (getObject(index) == "credits") {
			
			game.enterMenu(credits);
		}
		
		if (getObject(index) == "options") {
			
			game.enterMenu(options);
		}
 	}
	
	@Override
	public void render() {
		
		RenderUtil.renderStandardMenu(this);
		
		FontRenderer.drawString(10, GLSettings.HEIGHT - 20, "use <W> and <S> to navigate. <ENTER> to select.");
		FontRenderer.drawString(10, GLSettings.HEIGHT - 40, "Copyright 2013 Martin Jirlow");
	}
}
