package com.streamliners.codelab;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;

import com.streamliners.codelab.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Set the title for the activity
        setTitle("Codelab");

        // setup event handlers
        setupEventHandlers();
    }

    /**
     * to make the given view colored accordingly
     * @param view view to be colored
     */
    private void makeColored(View view) {
        // checking the id of the view and setting the color to it accordingly
        if (view.getId() == binding.boxOneText.getId()) {
            view.setBackgroundColor(Color.DKGRAY);
        } else if (view.getId() == binding.boxTwoText.getId()) {
            view.setBackgroundColor(Color.GRAY);
        } else if (view.getId() == binding.boxThreeText.getId()) {
            view.setBackgroundColor(Color.BLUE);
        } else if (view.getId() == binding.boxFourText.getId()) {
            view.setBackgroundColor(Color.MAGENTA);
        } else if (view.getId() == binding.boxFiveText.getId()) {
            view.setBackgroundColor(Color.BLUE);
        } else if (view.getId() == binding.redButton.getId()) {
            binding.boxThreeText.setBackgroundColor(getResources().getColor(R.color.my_red));
        } else if (view.getId() == binding.yellowButton.getId()) {
            binding.boxFourText.setBackgroundColor(getResources().getColor(R.color.my_yellow));
        } else if (view.getId() == binding.greenButton.getId()) {
            binding.boxFiveText.setBackgroundColor(getResources().getColor(R.color.my_green));
        } else {
            view.setBackgroundColor(Color.LTGRAY);
        }
    }

    /**
     *  to setup event handlers for the views
     */
    private void setupEventHandlers() {
        // list of all the clickable views
        List<View> clickableViews = new ArrayList<>(
                Arrays.asList(binding.boxOneText, binding.boxTwoText, binding.boxThreeText,
                        binding.boxFourText, binding.boxFiveText, binding.constraintLayout,
                        binding.redButton, binding.yellowButton, binding.greenButton)
        );

        // setting the on click listeners to all the views using loop
        for (View item :
                clickableViews) {
            item.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    makeColored(item);
                }
            });
        }
    }
}