package edu.calpoly.android.databinding_gyllenhaalorgosling;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import edu.calpoly.android.databinding_gyllenhaalorgosling.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private MainActivityViewModel mMainActivityViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Just FYI - No other messages will be processed on the MainThread if
        // "onRetainCustomNonConfigurationInstance" was just called
        mMainActivityViewModel = (MainActivityViewModel) getLastCustomNonConfigurationInstance();
        if (mMainActivityViewModel == null) {
            mMainActivityViewModel = new MainActivityViewModel();
        }

        ActivityMainBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        binding.setViewModel(mMainActivityViewModel);

        mMainActivityViewModel.bind(this);
    }

    protected void startResultActivity(int result) {
        Intent i = new Intent(this, ResultActivity.class);
        i.putExtra(ResultActivity.EXTRA_WINNER, result);
        startActivity(i);
    }

    @Override
    public Object onRetainCustomNonConfigurationInstance() {
        mMainActivityViewModel.unbind();
        return mMainActivityViewModel;
    }
}
