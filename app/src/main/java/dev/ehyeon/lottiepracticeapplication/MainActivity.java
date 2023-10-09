package dev.ehyeon.lottiepracticeapplication;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.airbnb.lottie.LottieAnimationView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = findViewById(R.id.button);
        LottieAnimationView lottieAnimationView = findViewById(R.id.lottie_animation_view);

        button.setOnClickListener(ignored -> {
            lottieAnimationView.setVisibility(View.VISIBLE);
            lottieAnimationView.playAnimation();

            // something async or await
            new Handler().postDelayed(() -> {
                lottieAnimationView.setVisibility(View.GONE);
                lottieAnimationView.pauseAnimation();
            }, 3000);
        });
    }
}
