package com.ld28.menu;

import com.ld28.audio.SoundSystem;
import com.ld28.handler.GameHandler;
import com.ld28.render.RenderUtil;
import com.ld28.settings.Settings;

public class OptionMenu extends Menu {

	private Menu parent;
	
	public OptionMenu(GameHandler game, Menu parent) {
		
		super(game);
		this.parent = parent;
		addMenuObject("sound: " + (Settings.SOUND_MUTED ? "off":"on"));
		addMenuObject("music: " + (Settings.MUSIC_MUTED ? "off":"on"));
		addMenuObject("back");
	}
	
	@Override
	public void triggerIndex(int index) {
		
		if (getObject(index).startsWith("sound")) {
			
			Settings.SOUND_MUTED = !Settings.SOUND_MUTED;
			changeMenuObject(index, "sound: " + (Settings.SOUND_MUTED ? "off":"on"));
		}
		else if (getObject(index).startsWith("music")) {
			
			Settings.MUSIC_MUTED = !Settings.MUSIC_MUTED;
			
			if (Settings.MUSIC_MUTED) {
				
				SoundSystem.stop("soundtrack1");
			}
			else {
				
				SoundSystem.loop("soundtrack1");
			}
			
			changeMenuObject(index, "music: " + (Settings.MUSIC_MUTED ? "off":"on"));
		}
		else if (getObject(index) == "back") {
			
			game.enterMenu(parent);
		}
	}
	
	@Override
	public void render() {
		
		RenderUtil.renderStandardMenu(this);
	}
}
