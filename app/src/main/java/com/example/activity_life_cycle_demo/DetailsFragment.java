package com.example.activity_life_cycle_demo;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import java.util.ArrayList;

public class DetailsFragment extends Fragment {

    private static final String TAG = MainActivity.class.getSimpleName();
    private static final String LIFECYCLE_CALLBACKS_TEXT_KEY = "callbacks";
    /* Constant values for the names of each respective lifecycle callback */
    private static final String ON_CREATE = "onCreate executed";
    private static final String ON_START = "onStart executed";
    private static final String ON_RESUME = "onResume executed";
    private static final String ON_PAUSE = "onPause executed";
    private static final String ON_STOP = "onStop executed";
    private static final String ON_DESTROY = "onDestroy executed";
    private static final String ON_SAVE_INSTANCE_STATE = "onSaveInstanceState executed";
    private static final ArrayList<String> mLifecycleCallbacks = new ArrayList<>();
    private TextView lifeCycles, activityName;
    private String lifeCycle;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        lifeCycle = ON_CREATE;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_details, container, false);
        activityName = view.findViewById(R.id.tvActivity);
        activityName.setText(TAG);

        lifeCycles = view.findViewById(R.id.tvCycles);

        if (savedInstanceState != null) {
            if (savedInstanceState.containsKey(LIFECYCLE_CALLBACKS_TEXT_KEY)) {
                String allPreviousLifecycleCallbacks = savedInstanceState
                        .getString(LIFECYCLE_CALLBACKS_TEXT_KEY);
                lifeCycles.setText(allPreviousLifecycleCallbacks);
            }
        }

        for (int i = mLifecycleCallbacks.size() - 1; i >= 0; i--) {
            lifeCycles.append(mLifecycleCallbacks.get(i) + "\n");
        }

        // COMPLETED (5) Clear mLifecycleCallbacks after iterating through it
        /*
         * Once we've appended each callback from the ArrayList to the TextView, we need to clean
         * the ArrayList so we don't get duplicate entries in the TextView.
         */

        mLifecycleCallbacks.clear();
        logAndAppend(ON_CREATE);

        Toast.makeText(getActivity(), ON_CREATE, Toast.LENGTH_SHORT).show();

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();

        logAndAppend(ON_START);
        Toast.makeText(getActivity(), ON_START, Toast.LENGTH_SHORT).show();
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
    public void onDestroy() {
        super.onDestroy();

        mLifecycleCallbacks.add(0, ON_DESTROY);

        logAndAppend(ON_DESTROY);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        logAndAppend(ON_SAVE_INSTANCE_STATE);
        String lifecycleDisplayTextViewContents = lifeCycle;
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

        lifeCycles.append(lifecycleEvent + "\n");
    }
}
