package com.ld28.render;

import java.awt.Color;
import java.awt.Font;

import org.newdawn.slick.UnicodeFont;
import org.newdawn.slick.font.effects.ColorEffect;

public class FontRenderer {

	private static UnicodeFont font;
	
	@SuppressWarnings("unchecked")
	public static void init() {
		try {
			
			Font awt = Font.createFont(Font.TRUETYPE_FONT, ClassLoader.getSystemResourceAsStream("fonts/cubica.ttf"));
			
			font = new UnicodeFont(awt, 20, false, false);
			font.addAsciiGlyphs();
			font.addGlyphs(400, 600);
			font.getEffects().add(new ColorEffect(java.awt.Color.WHITE));
			font.loadGlyphs();
		}
		catch(Exception ex) {
			
			System.err.println("Unable to initialize font");
			ex.printStackTrace();
		}
	}
	
	@SuppressWarnings("unchecked")
	public static void setColor(float r, float g, float b) {
		
		Color color = new Color(r, g, b);
		font.getEffects().add(new ColorEffect(color));
	}
	
	public static void drawString(float x, float y, String par0) {
		
		font.drawString(x, y, par0);
	}
}
