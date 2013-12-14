package com.ld28.render;

import org.lwjgl.opengl.GL11;

import com.ld28.entity.Entity;
import com.ld28.texture.TextureLibrary;

public class EntityRenderer {

	protected Entity entity;
	public int frame = 1;
	
	private float r = 1f, g = 1f, b = 1f;
	private float xScale, yScale;
	private float rotation;
	private boolean textured = false;
	private boolean subdivided = false;
	private String name = "";
	
	private boolean xFlip, yFlip;
	
	public EntityRenderer(Entity entity) {
		
		this.entity = entity;
		
		xScale = yScale = 1.0f;
	}
	
	public void render() {
		
		GL11.glPushMatrix();
		{
			
			GL11.glTranslatef(entity.x, entity.y, 0);
			GL11.glScalef(xFlip ? xScale * -1:xScale, yFlip ? yScale * -1:yScale, 0);
			GL11.glRotatef(rotation, 0, 0, 1f);
			
			GL11.glColor3f(r, g, b);
			
			float w = entity.width / 2;
			float h = entity.height / 2;
			
			if (!textured) GL11.glDisable(GL11.GL_TEXTURE_2D);
			
			String n = subdivided ? name + ":" + frame:name;
			
			if (textured) 					
				TextureLibrary.bind(n);
			
			GL11.glBegin(GL11.GL_QUADS);
			{
				
				if (textured) {
					
					GL11.glTexCoord2f(1, 0); GL11.glVertex2f(w, -h);
					GL11.glTexCoord2f(0 , 0); GL11.glVertex2f(-w, -h);
					GL11.glTexCoord2f(0, 1); GL11.glVertex2f(-w, h);
					GL11.glTexCoord2f(1, 1); GL11.glVertex2f(w, h);
				}
				else {
					
					GL11.glVertex2f(w, -h);
					GL11.glVertex2f(-w, -h);
					GL11.glVertex2f(-w, h);
					GL11.glVertex2f(w, h);
				}
			}
			GL11.glEnd();
			
			if (!textured) GL11.glEnable(GL11.GL_TEXTURE_2D);
		}
		GL11.glPopMatrix();
	}
	
	public boolean isTextured() {
		
		return textured;
	}
	
	public void setTextured(boolean textured) {
		
		this.textured = textured;
	}
	
	public boolean isSubdivided() {
		
		return subdivided;
	}
	
	public void setSubdivided(boolean subdivided) {
		
		this.subdivided = subdivided;
	}
	
	public String getTextureName() {
		
		return name;
	}
	
	public void setTextureName(String name) {
		
		this.name = name;
	}
	
	public void setColor(float r, float g, float b) {
		
		this.r = r;
		this.g = g;
		this.b = b;
	}
	
	public void setRotation(float rot) {
		
		rotation = rot;
	}
	
	public float getRotation() {
		
		return rotation;
	}
	
	public void setScaleX(float scale) {
		
		xScale = scale;
	}
	
	public void flipX(boolean flip) {
		
		xFlip = flip;
	}
	
	public boolean isXFlipped() {
		
		return xFlip;
	}
	
	public void flipY(boolean flip) {
		
		yFlip = flip;
	}
	
	public boolean isYFlipped() {
		
		return yFlip;
	}
	
	public float getScaleX() {
		
		return xScale;
	}
	
	public void setScaleY(float scale) {
		
		yScale = scale;
	}
	
	public float getScaleY() {
		
		return yScale;
	}
}
