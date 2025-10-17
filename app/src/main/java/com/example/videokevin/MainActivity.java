package com.example.videokevin;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnVideo = findViewById(R.id.btnVideo);
        Button btnAudio = findViewById(R.id.btnAudio);
        Button btnAnimacion = findViewById(R.id.btnAnimacion);
        Button btnImagen = findViewById(R.id.btnImagen);

        btnVideo.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, VideoActivity.class)));
        btnAudio.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, AudioActivity.class)));
        btnAnimacion.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, Animacion.class)));
        btnImagen.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, Imagen.class)));
    }
}
