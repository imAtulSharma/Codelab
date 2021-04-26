package com.streamliners.codelab;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private int mCount = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * Increase the counter and show in the text field
     * @param view root view of the UI
     */
    public void countUp(View view) {
        TextView showTextView = findViewById(R.id.show_count);

        // Make sure that there should be a view with id show_count
        if (showTextView != null)
            // Increment by 1 and set the text in the text field
            showTextView.setText(Integer.toString(++mCount));
    }

    /**
     * show toast on the screen
     * @param view root view of the UI
     */
    public void showToast(View view) {
        Toast.makeText(this, R.string.toast_message, Toast.LENGTH_SHORT).show();
    }
}