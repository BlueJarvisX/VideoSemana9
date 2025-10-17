package com.example.videokevin;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class AudioActivity extends AppCompatActivity {
    private MediaPlayer mediaPlayer;
    private Button pauseButton;
    private SeekBar seekBar;
    private TextView timeView;
    private Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.audio);

        mediaPlayer = MediaPlayer.create(this, R.raw.audio);
        pauseButton = findViewById(R.id.pauseAudioButton);
        seekBar = findViewById(R.id.seekBar);
        timeView = findViewById(R.id.timeView);

        seekBar.setMax(mediaPlayer.getDuration());

        pauseButton.setOnClickListener(v -> {
            if (mediaPlayer.isPlaying()) {
                mediaPlayer.pause();
                pauseButton.setText("Reproducir");
            } else {
                mediaPlayer.start();
                pauseButton.setText("Pausar");
                updateSeekBar();
            }
        });

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (fromUser) {
                    mediaPlayer.seekTo(progress);
                    updateTime(progress);
                }
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) { }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) { }
        });

        mediaPlayer.setOnCompletionListener(mp -> {
            seekBar.setProgress(0);
            timeView.setText("00:00");
            pauseButton.setText("Reproducir");
        });
    }

    private void updateSeekBar() {
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (mediaPlayer != null && mediaPlayer.isPlaying()) {
                    int currentPos = mediaPlayer.getCurrentPosition();
                    seekBar.setProgress(currentPos);
                    updateTime(currentPos);
                    handler.postDelayed(this, 1000);
                }
            }
        }, 0);
    }

    private void updateTime(int milliseconds) {
        int minutes = (milliseconds / 1000) / 60;
        int seconds = (milliseconds / 1000) % 60;
        timeView.setText(String.format("%02d:%02d", minutes, seconds));
    }

    @Override
    protected void onDestroy() {
        if (mediaPlayer != null) {
            if (mediaPlayer.isPlaying()) mediaPlayer.stop();
            mediaPlayer.release();
            mediaPlayer = null;
        }
        super.onDestroy();
    }
}
