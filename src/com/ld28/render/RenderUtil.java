package com.ld28.render;

import org.lwjgl.opengl.GL11;

import com.ld28.base.GLSettings;
import com.ld28.menu.Menu;
import com.ld28.texture.TextureLibrary;

public class RenderUtil {

	public static void renderLogo(float x, float y) {
		
		GL11.glPushMatrix();
		{
		
			GL11.glTranslatef(x, y, 0);
			GL11.glScalef(1f, 1f, 1f);
			GL11.glColor3f(1f, 1f, 1f);
			
			int width = TextureLibrary.get("logo").getImageWidth();
			int height = TextureLibrary.get("logo").getImageHeight();
			
			TextureLibrary.bind("logo");
			
			float var0 = 1f / width;
			float var1 = 1f / height;
			
			GL11.glBegin(GL11.GL_QUADS);
			{
				
				GL11.glTexCoord2f(1 - var0, 0 - var1); GL11.glVertex2f(width, 0);
				GL11.glTexCoord2f(0 - var0, 0 - var1); GL11.glVertex2f(0, 0);
				GL11.glTexCoord2f(0 - var0, 1 - var1); GL11.glVertex2f(0, height);
				GL11.glTexCoord2f(1 - var0, 1 - var1); GL11.glVertex2f(width, height);
				
			}
			GL11.glEnd();
		}
		GL11.glPopMatrix();
	}
	
	public static void renderStandardMenu(Menu menu) {
		
		RenderUtil.renderLogo(GLSettings.WIDTH / 2 - TextureLibrary.get("logo").getImageWidth() / 2, 50);
		
		for (int i = 0; i < menu.getObjectCount(); i++) {
			
			String s = menu.getObject(i);
			
			if (i == menu.getSelectedIndex()) {
				
				s = "< " + s + " >";
				FontRenderer.drawString(GLSettings.WIDTH / 2 - FontRenderer.getStringWidth(s) / 2, (GLSettings.HEIGHT / 2 - 50) + 50 * i, s, Menu.SELECTED_COLOR);
			}
			else {
				
				FontRenderer.drawString(GLSettings.WIDTH / 2 - FontRenderer.getStringWidth(s) / 2, (GLSettings.HEIGHT / 2 - 50) + 50 * i, s);
			}
		}
	}
}
