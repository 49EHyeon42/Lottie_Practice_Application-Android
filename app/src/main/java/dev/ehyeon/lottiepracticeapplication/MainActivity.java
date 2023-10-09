package dev.ehyeon.lottiepracticeapplication;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.airbnb.lottie.LottieAnimationView;
import com.google.gson.GsonBuilder;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "TAG";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BuildConfig.SERVER_URL)
                .addConverterFactory(GsonConverterFactory.create(new GsonBuilder().setLenient().create()))
                .build();

        TestService testService = retrofit.create(TestService.class);

        Button button = findViewById(R.id.button);
        LottieAnimationView lottieAnimationView = findViewById(R.id.lottie_animation_view);

        button.setOnClickListener(ignored -> {
            lottieAnimationView.setVisibility(View.VISIBLE);
            lottieAnimationView.playAnimation();

            testService.hello().enqueue(new Callback<Response>() {
                @Override
                public void onResponse(@NonNull Call<Response> call, @NonNull retrofit2.Response<Response> response) {
                    if (response.isSuccessful()) {
                        Toast.makeText(getBaseContext(), response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    } else {
                        Log.i(TAG, "onResponse error: " + response.code());
                    }

                    lottieAnimationView.setVisibility(View.GONE);
                    lottieAnimationView.pauseAnimation();
                }

                @Override
                public void onFailure(@NonNull Call<Response> call, @NonNull Throwable t) {
                    Log.i(TAG, "onFailure: " + t.getMessage());

                    lottieAnimationView.setVisibility(View.GONE);
                    lottieAnimationView.pauseAnimation();
                }
            });
        });
    }
}
