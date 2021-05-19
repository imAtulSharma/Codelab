package com.streamliners.codelab;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.Toast;

import static com.streamliners.codelab.Constants.COLOR_KEY;
import static com.streamliners.codelab.Constants.COUNT_KEY;

import com.streamliners.codelab.databinding.ActivitySettingsBinding;

public class SettingsActivity extends AppCompatActivity {
    // Binding for the layout
    private ActivitySettingsBinding binding;
    // For preferences
    private SharedPreferences preferences;
    // For the status
    private boolean isCountSelected = false, isColorSelected = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Initiate the binding and set the layout
        binding = ActivitySettingsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Set the title for the activity
        setTitle("Settings");

        // Initiate preferences
        preferences = getSharedPreferences("myPreferences", MODE_PRIVATE);

        // Setup the actions
        setupToggles();
        setupHideError();
        inflateSpinner();
        setupButtons();
    }

    // Setup Methods

    private void setupToggles() {
        // For color button
        binding.toggleColor.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    isColorSelected = true;
                    binding.spinnerColor.setVisibility(View.VISIBLE);
                } else {
                    isColorSelected = false;
                    binding.spinnerColor.setVisibility(View.GONE);
                }
            }
        });

        // For count button
        binding.toggleCount.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    isCountSelected = true;
                    binding.countTextView.setVisibility(View.VISIBLE);
                } else {
                    isCountSelected = false;
                    binding.countTextView.setVisibility(View.GONE);
                }
            }
        });
    }

    private void setupHideError() {
        binding.countTextView.getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                binding.countTextView.setError(null);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    private void inflateSpinner() {
        // Colors Array
        String[] colors = {"Select Color", "Black", "Red", "Blue", "Green"};

        //Creating the ArrayAdapter instance having the colors list
        ArrayAdapter aa = new ArrayAdapter(this, android.R.layout.simple_spinner_item, colors);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        //Setting the ArrayAdapter data on the Spinner
        binding.spinnerColor.setAdapter(aa);
    }

    private void setupButtons() {
        // For reset button
        binding.buttonReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetPreferences();
            }
        });

        // For save button
        binding.buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                savePreferences();
            }
        });
    }

    // Operation Methods

    private void resetPreferences() {
        preferences.edit().clear().apply();

        // Showing completion toast
        Toast.makeText(SettingsActivity.this, "Preferences cleared!", Toast.LENGTH_SHORT).show();
    }

    private void savePreferences() {
        int count = -1, color = -1;

        // When no changes selected
        if (!(isColorSelected || isCountSelected)) {
            Toast.makeText(this, "No changes", Toast.LENGTH_SHORT).show();
            return;
        }

        // when color change selected
        if (isCountSelected) {
            // Get the string from the text view
            String countString = binding.countTextView.getEditText().getText().toString().trim();

            // Guard code
            if (countString.isEmpty()) {
                binding.countTextView.setError("Enter count");
                return;
            }

            // Set the value
            count = Integer.parseInt(countString);
        }

        // When count changed selected
        if (isColorSelected) {
            switch (binding.spinnerColor.getSelectedItemPosition()) {
                case 1: color = getResources().getColor(R.color.red);
                    break;
                case 2: color = getResources().getColor(R.color.black);
                    break;
                case 3: color = getResources().getColor(R.color.blue);
                    break;
                case 4: color = getResources().getColor(R.color.green);
                    break;
                default:
                    Toast.makeText(this, "Please select color", Toast.LENGTH_SHORT).show();
                    return;
            }
        }

        // Update the preferences
        preferences.edit()
                .putInt(COUNT_KEY, count)
                .putInt(COLOR_KEY, color)
                .apply();

        // Showing completion toast
        Toast.makeText(SettingsActivity.this, "Preferences Updated!", Toast.LENGTH_SHORT).show();
    }
}