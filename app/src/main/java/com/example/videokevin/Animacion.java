package com.example.videokevin;

import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;

public class Animacion extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.animacion);

        Button animateButton = findViewById(R.id.animateButton);
        ImageView imageView = findViewById(R.id.myImageView);

        animateButton.setOnClickListener(v -> {
            Animation moveAnimation = AnimationUtils.loadAnimation(this, R.anim.move_left_right);
            imageView.startAnimation(moveAnimation);
        });
    }
}
