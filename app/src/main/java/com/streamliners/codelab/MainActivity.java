package com.streamliners.codelab;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;

import com.streamliners.codelab.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;

    private int mCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Set the title for the activity
        setTitle("Hello Shared Preferences");

        setUpEventHandlers();
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
                binding.countTextView.setBackgroundColor(getResources().getColor(R.color.black));
            }
        });

        binding.redButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.countTextView.setBackgroundColor(getResources().getColor(R.color.red));
            }
        });

        binding.blueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.countTextView.setBackgroundColor(getResources().getColor(R.color.blue));
            }
        });

        binding.greenButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.countTextView.setBackgroundColor(getResources().getColor(R.color.green));
            }
        });
    }

    /**
     * To reset the count value to 0 and display in the text view
     */
    private void reset() {
        mCount = 0;
        binding.countTextView.setBackgroundColor(getResources().getColor(R.color.gray));
        displayCount();
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
}