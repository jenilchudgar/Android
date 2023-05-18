package com.example.unitconvertor;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button button;
    private TextView textView;
    private EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = findViewById(R.id.button);
        textView = findViewById(R.id.resultText);
        editText = findViewById(R.id.editText);

        button.setOnClickListener(view -> {
            double kg = Double.parseDouble(editText.getText().toString());
            double pound = 2.205 * kg;

            textView.setText("Result: "+pound);
        });
    }
}