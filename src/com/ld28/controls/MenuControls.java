package com.ld28.controls;

import org.lwjgl.input.Keyboard;

import com.ld28.input.Input;
import com.ld28.menu.Menu;

public class MenuControls implements Controls {

	private Menu menu;
	
	public MenuControls(Menu menu) {
		
		this.menu = menu;
	}
	
	@Override
	public void check() {
		
		if (Input.isKeyPressed(Keyboard.KEY_S)) {
			
			if (menu.getSelectedIndex() + 1 >= menu.getObjectCount()) {
				
				menu.setSelectedIndex(0);
			}
			else {
				
				menu.setSelectedIndex(menu.getSelectedIndex() + 1);
			}
		}
		
		if (Input.isKeyPressed(Keyboard.KEY_W)) {
			
			if (menu.getSelectedIndex() - 1 < 0) {
				
				menu.setSelectedIndex(menu.getObjectCount() - 1);
			}
			else {
				
				menu.setSelectedIndex(menu.getSelectedIndex() - 1);
			}
		}
		
		if (Input.isKeyPressed(Keyboard.KEY_RETURN)) {
			
			menu.triggerIndex(menu.getSelectedIndex());
		}
	}
}
