package com.ld28.render;

import java.awt.Font;

import org.newdawn.slick.Color;
import org.newdawn.slick.UnicodeFont;
import org.newdawn.slick.font.effects.ColorEffect;
import org.newdawn.slick.font.effects.ShadowEffect;

public class FontRenderer {

	private static UnicodeFont font;
	private static float r, g, b;
	private static Color defaultColor = new Color(0f, .65f, 0f);
	
	@SuppressWarnings("unchecked")
	public static void init() {
		try {
			
			Font awt = Font.createFont(Font.TRUETYPE_FONT, ClassLoader.getSystemResourceAsStream("fonts/cubica.ttf"));
			
			font = new UnicodeFont(awt, 20, false, false);
			font.addAsciiGlyphs();
			font.addGlyphs(400, 600);
			font.getEffects().add(new ColorEffect(java.awt.Color.WHITE));
			//font.getEffects().add(new ShadowEffect());
			font.loadGlyphs();
		}
		catch(Exception ex) {
			
			System.err.println("Unable to initialize font");
			ex.printStackTrace();
		}
	}
	
	public static int getStringWidth(String s) {
		
		return font.getWidth(s);
	}
	
	public static int getStringHeight(String s) {
		
		return font.getHeight(s);
	}
	
	public static void setSize(float size) {
		
	}
	
	public static void drawString(float x, float y, String par0, Color color) {
		
		font.drawString(x, y, par0, color);
	}
	
	public static void drawString(float x, float y, String par0) {
		
		drawString(x, y, par0, defaultColor);
	}
}
