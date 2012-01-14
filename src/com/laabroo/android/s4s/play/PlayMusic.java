package com.laabroo.android.s4s.play;

import java.io.IOException;

import android.app.Activity;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.view.View.OnClickListener;

import com.laabroo.android.s4s.R;
import com.laabroo.android.s4s.impl.MusicImplementasi;

public class PlayMusic extends Activity implements MusicImplementasi {
	private Button button;
	private MediaPlayer mediaPlayer;

	public PlayMusic() {
		mediaPlayer = new MediaPlayer();
	}

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.playmusic);

		initComponent();

	}

	@Override
	public MediaPlayer stopMusic(MediaPlayer mediaPlayer) {
		// TODO Auto-generated method stub
		return null;
	}

	private void initComponent() {
		button = (Button) findViewById(R.id.btnPlay);
		button.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

			}
		});

	}

	@Override
	public MediaPlayer init(String url) throws IllegalArgumentException,
			IllegalStateException, IOException {
		mediaPlayer = new MediaPlayer();
		mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
		mediaPlayer.setDataSource(url);
		mediaPlayer.prepare();

		return mediaPlayer;
	}

	@Override
	public MediaPlayer playMusic(String url) throws IllegalArgumentException,
			IllegalStateException, IOException {

		mediaPlayer = new MediaPlayer();
		init(url);
		mediaPlayer.start();

		return mediaPlayer;
	}

	@Override
	public MediaPlayer pauseMusic(String url) throws IllegalArgumentException,
			IllegalStateException, IOException {
		MediaPlayer mediaPlayer = new MediaPlayer();
		init(url);
		if (mediaPlayer.isPlaying()) {
			mediaPlayer.pause();

		} else {
			mediaPlayer.start();
		}
		return mediaPlayer;
	}

	@Override
	public void play() {

	}

	@Override
	public void stop() {

	}

}
