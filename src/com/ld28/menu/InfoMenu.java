package com.ld28.menu;

import org.newdawn.slick.Color;

import com.ld28.base.GLSettings;
import com.ld28.handler.GameHandler;
import com.ld28.render.FontRenderer;

public class InfoMenu extends Menu {

	protected Menu parent;
	private String text = "text placeholder";
	
	public InfoMenu(GameHandler game, Menu parent) {
		
		super(game);
		this.parent = parent;
		addMenuObject("exit");
	}
	
	public void setText(String text) {
		
		this.text = text;
	}
	
	public String getText() {
		
		return text;
	}
	
	@Override
	public void render() {
		
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
		
		FontRenderer.drawString(50, 30, text);
	}
	
	@Override
	public void triggerIndex(int index) {
		
		if (getObject(index) == "exit") {
			
			game.enterMenu(parent);
		}
	}
}
