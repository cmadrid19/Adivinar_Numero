package com.example.adivinar_numero;

import androidx.appcompat.app.AppCompatActivity;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;

public class LoseActivity extends AppCompatActivity {

    private static MediaPlayer mMediaPlayer = new MediaPlayer();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lose);

        music();
    }

    private void music() {
        mMediaPlayer = MediaPlayer.create(this, R.raw.sad_trombone);
        mMediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        mMediaPlayer.setLooping(true);
        mMediaPlayer.start();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        mMediaPlayer.stop();
        finish();
        System.exit(0);
    }


}
