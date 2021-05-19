package com.streamliners.codelab;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.streamliners.codelab.databinding.ActivityMainBinding;

import static com.streamliners.codelab.Constants.COLOR_KEY;
import static com.streamliners.codelab.Constants.COUNT_KEY;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    SharedPreferences preferences;

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

        preferences = getSharedPreferences("myPreferences", MODE_PRIVATE);

        setUpEventHandlers();

        if (preferences != null) {
            mCount = preferences.getInt(COUNT_KEY, 0);
            displayCount();
            mBackgroundColor = preferences.getInt(COLOR_KEY, getResources().getColor(R.color.gray));
            updateBackgroundColor();
        }
    }

    // Application bar menu

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.my_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.settings) {
            startActivity(new Intent(this, SettingsActivity.class));
            return true;
        }
        return super.onOptionsItemSelected(item);
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
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putInt(COUNT_KEY, mCount);
        outState.putInt(COLOR_KEY, mBackgroundColor);
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        // Set the variable and update the UI
        mCount = savedInstanceState.getInt(COUNT_KEY, 0);
        displayCount();
        mBackgroundColor = savedInstanceState.getInt(COLOR_KEY, getResources().getColor(R.color.gray));
        updateBackgroundColor();

        super.onRestoreInstanceState(savedInstanceState);
    }
}