package com.streamliners.codelab;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import com.streamliners.codelab.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    SharedPreferences preferences;

    // Represents some constants to use as a key to transfer data between activities
    public static final String COUNT_KEY = "Count";
    public static final String COLOR_KEY = "Color";

    // For the count of the text view
    private int mCount;

    // for the back ground color of the text view
    private int mBackgroundColor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Set the title for the activity
        setTitle("Hello Shared Preferences");

        preferences = getPreferences(MODE_PRIVATE);

        setUpEventHandlers();

        if (preferences != null) {
            mCount = preferences.getInt(COUNT_KEY, 0);
            displayCount();
            mBackgroundColor = preferences.getInt(COLOR_KEY, getResources().getColor(R.color.gray));
            updateBackgroundColor();
        }
    }

    /**
     * to setup all the event handlers for the buttons and text views
     */
    private void setUpEventHandlers() {
        binding.countIncButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                increaseCount();
            }
        });

        binding.resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reset();
            }
        });

        binding.blackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 mBackgroundColor = getResources().getColor(R.color.black);
                 updateBackgroundColor();
            }
        });

        binding.redButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mBackgroundColor = getResources().getColor(R.color.red);
                updateBackgroundColor();
            }
        });

        binding.blueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mBackgroundColor = getResources().getColor(R.color.blue);
                updateBackgroundColor();
            }
        });

        binding.greenButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mBackgroundColor = getResources().getColor(R.color.green);
                updateBackgroundColor();
            }
        });
    }

    /**
     * To reset the count value to 0 and display in the text view
     */
    private void reset() {
        // To reset the count
        mCount = 0;
        displayCount();

        // To reset the color
        mBackgroundColor = getResources().getColor(R.color.gray);
        updateBackgroundColor();

        // To clear the data stored in the shared preferences
        preferences.edit().clear().apply();
    }

    /**
     * To increase the count value and display in the text view
     */
    private void increaseCount() {
        ++mCount;
        displayCount();
    }

    /**
     * To display the count value in the text view
     */
    private void displayCount() {
        binding.countTextView.setText(String.valueOf(mCount));
    }

    /**
     * To update the background color of the text view
     */
    private void updateBackgroundColor() {
        binding.countTextView.setBackgroundColor(mBackgroundColor);
    }

    @Override
    protected void onPause() {
        super.onPause();

        preferences.edit()
                .putInt(COUNT_KEY, mCount)
                .putInt(COLOR_KEY, mBackgroundColor)
                .apply();
    }
}