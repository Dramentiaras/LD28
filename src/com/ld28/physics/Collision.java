package com.ld28.physics;

import java.awt.Rectangle;

import com.ld28.entity.Entity;

public class Collision {
	
	public static boolean isColliding(Entity e1, Entity e2) {
		
		Rectangle r1 = new Rectangle((int) (e1.x - e1.colWidth / 2), (int) (e1.y - e1.colHeight / 2), (int) e1.colWidth, (int) e1.colHeight);
		Rectangle r2 = new Rectangle((int) (e2.x - e2.colWidth / 2), (int) (e2.y - e2.colHeight / 2), (int) e2.colWidth, (int) e2.colHeight);
		
		return r1.intersects(r2);
	}
	
	public static boolean isColliding(Rectangle r1, Rectangle r2) {
		
		return r1.intersects(r2);
	}
}
