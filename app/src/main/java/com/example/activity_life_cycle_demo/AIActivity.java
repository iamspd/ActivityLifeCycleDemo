package com.example.activity_life_cycle_demo;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class AIActivity extends AppCompatActivity {

    private static final String TAG = AIActivity.class.getSimpleName();
    private static final String LIFECYCLE_CALLBACKS_TEXT_KEY = "callbacks";
    /* Constant values for the names of each respective lifecycle callback */
    private static final String ON_CREATE = "onCreate executed";
    private static final String ON_START = "onStart executed";
    private static final String ON_RESUME = "onResume executed";
    private static final String ON_PAUSE = "onPause executed";
    private static final String ON_STOP = "onStop executed";
    private static final String ON_RESTART = "onRestart executed";
    private static final String ON_DESTROY = "onDestroy executed";
    private static final String ON_SAVE_INSTANCE_STATE = "onSaveInstanceState executed";
    private static final ArrayList<String> mLifecycleCallbacks = new ArrayList<>();
    private TextView lifeCycle, activityName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ai);

        lifeCycle = findViewById(R.id.tvLifeCycle);
        activityName = findViewById(R.id.tvActivity);
        activityName.setText(TAG);

        if (savedInstanceState != null) {
            if (savedInstanceState.containsKey(LIFECYCLE_CALLBACKS_TEXT_KEY)) {
                String allPreviousLifecycleCallbacks = savedInstanceState
                        .getString(LIFECYCLE_CALLBACKS_TEXT_KEY);
                lifeCycle.setText(allPreviousLifecycleCallbacks);
            }
        }

        for (int i = mLifecycleCallbacks.size() - 1; i >= 0; i--) {
            lifeCycle.append(mLifecycleCallbacks.get(i) + "\n");
        }

        // COMPLETED (5) Clear mLifecycleCallbacks after iterating through it
        /*
         * Once we've appended each callback from the ArrayList to the TextView, we need to clean
         * the ArrayList so we don't get duplicate entries in the TextView.
         */
        mLifecycleCallbacks.clear();


        logAndAppend(ON_CREATE);
    }

    @Override
    public void onStart() {
        super.onStart();

        logAndAppend(ON_START);
    }

    /**
     * Called when the activity will start interacting with the user. At this point your activity
     * is at the top of the activity stack, with user input going to it.
     * <p>
     * Always followed by onPause().
     */
    @Override
    public void onResume() {
        super.onResume();

        logAndAppend(ON_RESUME);
    }

    @Override
    public void onPause() {
        super.onPause();

        logAndAppend(ON_PAUSE);
    }

    @Override
    public void onStop() {
        super.onStop();

        mLifecycleCallbacks.add(0, ON_STOP);

        logAndAppend(ON_STOP);
    }

    @Override
    protected void onRestart() {
        super.onRestart();

        logAndAppend(ON_RESTART);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        mLifecycleCallbacks.add(0, ON_DESTROY);

        logAndAppend(ON_DESTROY);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        logAndAppend(ON_SAVE_INSTANCE_STATE);
        String lifecycleDisplayTextViewContents = lifeCycle.getText().toString();
        outState.putString(LIFECYCLE_CALLBACKS_TEXT_KEY, lifecycleDisplayTextViewContents);
    }

    /**
     * Logs to the console and appends the lifecycle method name to the TextView so that you can
     * view the series of method callbacks that are called both from the app and from within
     * Android Studio's Logcat.
     *
     * @param lifecycleEvent The name of the event to be logged.
     */
    private void logAndAppend(String lifecycleEvent) {
        Log.d(TAG, "Lifecycle Event: " + lifecycleEvent);

        lifeCycle.append(lifecycleEvent + "\n");
    }

}
