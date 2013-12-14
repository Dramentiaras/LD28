package com.ld28.texture;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;

import javax.imageio.ImageIO;

import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;
import org.newdawn.slick.util.BufferedImageUtil;

public class TextureLibrary {

	private static HashMap<String, Texture> textures = new HashMap<String, Texture>();
	
	public static void load(String path) {
		
		try {
			
			Texture texture = TextureLoader.getTexture(".png", ClassLoader.getSystemResourceAsStream(path));
			
			String name = path.substring((path.lastIndexOf('/') == -1 ? 0:path.lastIndexOf('/') + 1));
			name = name.substring(0, name.lastIndexOf("."));
			
			textures.put(name, texture);
			
			System.out.println("Loaded texture: " + name);
			
		} catch (IOException e) {
			
			System.err.println("Unable to load texture " + path + ":");
			
			e.printStackTrace();
		}
	}
	
	public static void loadAndSubdivide(String path, int rows, int cols, int width, int height) {
		
		try {
			
			BufferedImage image = ImageIO.read(ClassLoader.getSystemResourceAsStream(path));
			
			String name = path.substring((path.lastIndexOf('/') == -1 ? 0:path.lastIndexOf('/') + 1));
			name = name.substring(0, name.lastIndexOf("."));
			
			textures.put(path, BufferedImageUtil.getTexture(path, image));
			
			for (int y = 0; y < rows; y++) {
				for (int x = 0; x < cols; x++) {
					
					BufferedImage img = image.getSubimage(x * width, y * height, width, height);
					
					textures.put(name + ":" + (y * cols + x), BufferedImageUtil.getTexture(path, img));
					System.out.println("Loaded: " + name + ":" + (y * cols + x));
				}
			}
			
			System.out.println("Loaded and subdivided texture: " + name);
		}
		catch(Exception ex) {
			
			System.err.println("Unable to load texture " + path + ":");
			
			ex.printStackTrace();
		}
	}
	
	public static Texture get(String name) {
		
		if (textures.containsKey(name)) {
			
			return textures.get(name);
		}
		else {
			
			System.err.println("Can't find texture: " + name);
			return null;
		}
	}
	
	public static void bind(String name) {
		
		if (textures.containsKey(name)) {
			
			textures.get(name).bind();
		}
		else {
			
			System.err.println("Unable to bind texture: " + name);
		}
	}
}
