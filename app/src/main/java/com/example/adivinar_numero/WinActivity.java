package com.example.adivinar_numero;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.widget.TextView;

public class WinActivity extends AppCompatActivity {
    private static MediaPlayer mMediaPlayer = new MediaPlayer();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_win);

        Intent intent = getIntent();

        //ansawer
        final int answare = intent.getIntExtra("answer", 0);
        TextView textView_answare = findViewById(R.id.textView_answare);
        textView_answare.setText("The correct number is " + answare);

        //attempts
        final int attempts = intent.getIntExtra("attempts", 0);
        TextView textView_attempts = findViewById(R.id.textView_attempts_win);
        textView_attempts.setText(String.valueOf(attempts));

        music();
    }

    private void music() {
        mMediaPlayer = MediaPlayer.create(this, R.raw.cheering_sound);
        mMediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        mMediaPlayer.setLooping(true);
        mMediaPlayer.start();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        mMediaPlayer.stop();
    }
}
