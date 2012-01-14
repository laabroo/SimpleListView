package com.laabroo.android.s4s.impl;

import java.io.IOException;

import android.media.MediaPlayer;

public interface MusicImplementasi {

	public MediaPlayer playMusic(String url) throws IllegalArgumentException,
			IllegalStateException, IOException;

	public MediaPlayer pauseMusic(String url) throws IllegalArgumentException,
			IllegalStateException, IOException;

	public MediaPlayer stopMusic(MediaPlayer mediaPlayer);

	public MediaPlayer init(String url) throws IllegalArgumentException,
			IllegalStateException, IOException;
	
	public void play();
	public void stop();

}
