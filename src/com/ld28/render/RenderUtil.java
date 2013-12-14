package com.ld28.render;

import org.lwjgl.opengl.GL11;

import com.ld28.texture.TextureLibrary;

public class RenderUtil {

	public static void renderLogo(float x, float y) {
		
		GL11.glPushMatrix();
		{
		
			GL11.glTranslatef(x, y, 0);
			
			GL11.glColor3f(1f, 1f, 1f);
			
			TextureLibrary.bind("logo");
			
			float var0 = 1f / 400;
			float var1 = 1f / 250;
			
			GL11.glBegin(GL11.GL_QUADS);
			{
				
				GL11.glTexCoord2f(1 - var0, 0); GL11.glVertex2f(400, 0);
				GL11.glTexCoord2f(0 - var0, 0); GL11.glVertex2f(0, 0);
				GL11.glTexCoord2f(0 - var0, 1); GL11.glVertex2f(0, 250);
				GL11.glTexCoord2f(1 - var0, 1); GL11.glVertex2f(400, 250);
				
			}
			GL11.glEnd();
		}
		GL11.glPopMatrix();
	}
}
