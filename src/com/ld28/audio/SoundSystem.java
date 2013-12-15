package com.ld28.audio;

import java.io.BufferedInputStream;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.util.ArrayList;
import java.util.HashMap;

import org.lwjgl.BufferUtils;
import org.lwjgl.openal.AL;
import org.lwjgl.openal.AL10;
import org.newdawn.slick.openal.WaveData;

import com.ld28.settings.Settings;

public class SoundSystem {

	public static int SOUNDS_COUNT;
	private static HashMap<String, Integer> ids = new HashMap<String, Integer>();
	private static ArrayList<String> paths = new ArrayList<String>();
	private static boolean initiated;
	
	private static IntBuffer buffers;
	private static IntBuffer sources;
	
	private static FloatBuffer sourcePos;
	private static FloatBuffer sourceVel;
	private static FloatBuffer listenerPos = BufferUtils.createFloatBuffer(3).put(new float[] {
			0.0f, 0.0f, 0.0f
	});
	private static FloatBuffer listenerVel = BufferUtils.createFloatBuffer(3).put(new float[] {
			0.0f, 0.0f, 0.0f
	});
	private static FloatBuffer listenerOri = BufferUtils.createFloatBuffer(6).put(new float[] {
			0.0f, 0.0f, -1.0f, 0.0f, 1.0f, 0.0f
	});
	
	public static void load(String path) {
		
		if (initiated) {
			
			System.err.println("Audio loading must occur before sound system initialization");
			return;
		}
		
		String name = path.substring(path.lastIndexOf("/") == -1 ? 0:path.lastIndexOf("/") + 1);
		
		if (!name.endsWith(".wav")) {
			
			System.err.println("File format must be .wav");
			return;
		}
		
		name = name.substring(0, name.lastIndexOf("."));
		
		ids.put(name, SOUNDS_COUNT);
		paths.add(SOUNDS_COUNT++, path);
		
		System.out.println("Loaded sound: " + name);
	}
	
	public static void initAL() {
		
		try {
			
			AL.create();
		}
		catch(Exception ex) {
			
			ex.printStackTrace();
			System.exit(0);
		}
		
		listenerPos.flip();
		listenerVel.flip();
		listenerOri.flip();
		
		buffers = BufferUtils.createIntBuffer(SOUNDS_COUNT);
		sources = BufferUtils.createIntBuffer(SOUNDS_COUNT);
		sourcePos = BufferUtils.createFloatBuffer(SOUNDS_COUNT * 3);
		sourceVel = BufferUtils.createFloatBuffer(SOUNDS_COUNT * 3);
		
		AL10.alGenBuffers(buffers);
		
		if (AL10.alGetError() != AL10.AL_NO_ERROR) {
			
			System.err.println("Error in AL initialization: 1");
		}
		
		for (int i = 0; i < SOUNDS_COUNT; i++) {
			
			String path = paths.get(i);
			
			try {
				
				WaveData wave = WaveData.create(new BufferedInputStream(ClassLoader.getSystemResourceAsStream(path)));
				AL10.alBufferData(buffers.get(i), wave.format, wave.data, wave.samplerate);
				wave.dispose();
				
			}
			catch(Exception ex) {
				
				ex.printStackTrace();
			}
			
			if (AL10.alGetError() != AL10.AL_NO_ERROR) {
				
				System.err.println("Error in AL initialization: 2 " + path);
			}
		}
		
		AL10.alGenSources(sources);
		
		for (int i = 0; i < SOUNDS_COUNT; i++) {
			
			AL10.alSourcei(sources.get(i), AL10.AL_BUFFER, buffers.get(i));
			AL10.alSourcef(sources.get(i), AL10.AL_PITCH, 1.0f);
			AL10.alSource(sources.get(i), AL10.AL_POSITION, (FloatBuffer) sourcePos.position(i * 3));
			AL10.alSource(sources.get(i), AL10.AL_VELOCITY, (FloatBuffer) sourceVel.position(i * 3));
			
			if (AL10.alGetError() != AL10.AL_NO_ERROR) {
				
				System.err.println("Error in AL initialization: " + 1);
			}
		}
		
		AL10.alListener(AL10.AL_POSITION, listenerPos);
		AL10.alListener(AL10.AL_VELOCITY, listenerVel);
		AL10.alListener(AL10.AL_ORIENTATION, listenerOri);
		
		initiated = true;
	}
	
	public static void play(String sound) {
		
		if (!initiated || Settings.SOUND_MUTED) return;
		
		int id = ids.get(sound);
		
		AL10.alSourcei(sources.get(id), AL10.AL_LOOPING, AL10.AL_FALSE);
		AL10.alSourcef(sources.get(id), AL10.AL_GAIN, 1f);
		AL10.alSourcePlay(sources.get(id));
	}
	
	public static void stop(String sound) {
		
		if (!initiated) return;
		
		AL10.alSourceStop(sources.get(ids.get(sound)));
	}
	
	public static void stopAll() {
		
		if (!initiated) return;
		
		for (int i = 0; i < sources.capacity(); i++) {
			
			AL10.alSourceStop(sources.get(i));
		}
	}
	
	public static void loop(String sound) {
		
		if (!initiated || Settings.MUSIC_MUTED) return;
		
		int id = ids.get(sound);
		
		AL10.alSourcei(sources.get(id), AL10.AL_LOOPING, AL10.AL_TRUE);
		AL10.alSourcef(sources.get(id), AL10.AL_GAIN, .15f);
		AL10.alSourcePlay(sources.get(id));
	}
	
	public static void destroy() {
		
		AL10.alDeleteBuffers(buffers);
		AL10.alDeleteSources(sources);
		
		AL.destroy();
	}
}
