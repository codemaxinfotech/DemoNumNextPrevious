package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText etInputNumber;
    private Button btnSubmit, btnPrevious, btnNext;
    private TextView tvDisplayNumber;
    private int currentNumber;
    private int maxNumber;
    private int[] currentGroup;
    private int groupIndex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etInputNumber = findViewById(R.id.et_input_number);
        btnSubmit = findViewById(R.id.btn_submit);
        btnPrevious = findViewById(R.id.btn_previous);
        btnNext = findViewById(R.id.btn_next);
        tvDisplayNumber = findViewById(R.id.tv_display_number);

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String input = etInputNumber.getText().toString();
                if (!input.isEmpty()) {
                    int number = Integer.parseInt(input);
                    if (number >= 10 && number <= 20) {
                        maxNumber = number;
                        groupIndex = 0; // Start at the first group
                        showCurrentGroup(); // Display first group (1-4)
                        btnNext.setEnabled(true);
                        btnPrevious.setEnabled(true);
                    } else {
                        Toast.makeText(MainActivity.this, "Please enter a number between 10 and 20", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(MainActivity.this, "Input cannot be empty", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnPrevious.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                groupIndex--;
                if (groupIndex < 0) {
                    groupIndex = (maxNumber + 3) / 4 - 1; // Wrap around to the last group
                }
                showCurrentGroup();
            }
        });

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                groupIndex++;
                if (groupIndex >= (maxNumber + 3) / 4) {
                    groupIndex = 0; // Wrap around to the first group
                }
                showCurrentGroup();


            }
        });
    }

    // Method to display the current group of numbers
    private void showCurrentGroup() {
        StringBuilder groupString = new StringBuilder();
        for (int i = 0; i < 4; i++) {
            int numberToShow = (groupIndex * 4 + i + 1);
            if (numberToShow > maxNumber) {
                numberToShow = (numberToShow - maxNumber); // Loop back if we exceed the max number
            }
            groupString.append(numberToShow);
            if (i < 3) groupString.append(","); // Add commas between numbers
        }
        tvDisplayNumber.setText(groupString.toString());
    }
}

