package com.ld28.physics;

import java.awt.Rectangle;

import com.ld28.entity.Entity;

public class Collision {
	
	public static boolean isColliding(Entity e1, Entity e2) {
		
		Rectangle r1 = new Rectangle((int) (e1.x + e1.motionX), (int) (e1.y + e1.motionY), (int) e1.width, (int) e1.height);
		Rectangle r2 = new Rectangle((int) (e2.x + e2.motionX), (int) (e2.y + e2.motionY), (int) e2.width, (int) e2.height);
		
		return r1.intersects(r2);
	}
	
	public static boolean isColliding(Rectangle r1, Rectangle r2) {
		
		return r1.intersects(r2);
	}
}
