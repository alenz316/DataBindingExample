package edu.calpoly.android.databinding_gyllenhaalorgosling;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableLong;
import android.view.View;
import android.widget.Toast;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

public class MainActivityViewModel extends BaseObservable {
    private static final int DEFAULT_PERCENT = 50;
    private static final int DEFAULT_COUNTDOWN_SECONDS = 3;
    private static final int DEFAULT_COUNTDOWN_MILLIS = DEFAULT_COUNTDOWN_SECONDS * 1000;

    /**
     * Bindable Properties
     **/
    private int mPercentage = DEFAULT_PERCENT;
    public ObservableLong timerTime = new ObservableLong(0);
    public ObservableBoolean timerStarted = new ObservableBoolean(false);

    /**
     * Private Instance Variables
     **/
    // CAUTION: AVOID LEAKING THE ACTIVITY
    private MainActivity mMainActivity;
    private boolean mCountdownCompleted = false;


    public void bind(MainActivity mainActivity) {
        mMainActivity = mainActivity;
        executePendingContextItems();
    }

    public void unbind() {
        mMainActivity = null;
    }

    /**
     * Items that require a Context to fire should be here, to ensure that there is a valid Context
     * to handle it.
     */
    private void executePendingContextItems() {
        if (mCountdownCompleted) {
            countDownCompleted();
        }
    }

    private void countDownCompleted() {
        if (mMainActivity != null) {
            int result = Hottie.CHEATER;
            if (getPercentage() > 0 && getPercentage() < 100) {
                result = ((int) (Math.random() * 100)) < getPercentage()
                        ? Hottie.JAKE_WINNER : Hottie.RYAN_WINNER;
            }
            mMainActivity.startResultActivity(result);
            mCountdownCompleted = false;
        } else {
            // Mark as completed so it can be executed when a Context is available
            mCountdownCompleted = true;
        }
    }

    @Bindable
    public int getPercentage() {
        return mPercentage;
    }

    public void setPercentage(int percentage) {
        this.mPercentage = percentage;
        notifyPropertyChanged(BR.percentage);
    }

    public void startTimer(View v) {
//        Toast.makeText(v.getContext(), "" + getPercentage() + "%", Toast.LENGTH_SHORT).show();
        timerStarted.set(true);
        timerTime.set(DEFAULT_COUNTDOWN_MILLIS);
        Observable.interval(1, TimeUnit.MILLISECONDS, Schedulers.io())
                .map(new Func1<Long, Long>() {

                    @Override
                    public Long call(Long aLong) {
                        return DEFAULT_COUNTDOWN_MILLIS - aLong;
                    }
                })
                .takeUntil(new Func1<Long, Boolean>() {
                    @Override
                    public Boolean call(Long aLong) {
                        return aLong == 0;
                    }
                })
                .subscribe(new Subscriber<Long>() {
                    @Override
                    public void onCompleted() {
                        timerStarted.set(false);
                        countDownCompleted();
                    }

                    @Override
                    public void onError(Throwable e) {
                        // Do nothing for now :)
                    }

                    @Override
                    public void onNext(Long aLong) {
                        timerTime.set(aLong);
                    }
                });
    }

    public void resetPercentage(View v) {
        setPercentage(DEFAULT_PERCENT);
    }
}
