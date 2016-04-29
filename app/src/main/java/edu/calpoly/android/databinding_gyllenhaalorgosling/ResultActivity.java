package edu.calpoly.android.databinding_gyllenhaalorgosling;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import edu.calpoly.android.databinding_gyllenhaalorgosling.databinding.ActivityResultBinding;

public class ResultActivity extends AppCompatActivity {

    public static final String EXTRA_WINNER = "WINNER_WINNER";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent i = getIntent();

        if (!i.hasExtra(EXTRA_WINNER)) {
            throw new IllegalStateException("A winner is needed for a ResultActivity");
        }

        ActivityResultBinding binding = ActivityResultBinding.inflate(getLayoutInflater());
        int result = i.getIntExtra(EXTRA_WINNER, Hottie.CHEATER);
        binding.setViewModel(Hottie.HOTTIES[result]);
        binding.setCheater(result == Hottie.CHEATER);

        setContentView(binding.getRoot());
    }
}
