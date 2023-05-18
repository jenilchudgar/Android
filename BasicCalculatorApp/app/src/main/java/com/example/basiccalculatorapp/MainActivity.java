package com.example.basiccalculatorapp;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

//    Objects
    private EditText no1;
    private EditText no2;
    private Spinner spinner;
    private TextView result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        Initialize the objects
        no1 = findViewById(R.id.no1);
        no2 = findViewById(R.id.no2);
        spinner = findViewById(R.id.spinner);
        result = findViewById(R.id.resultTextView);
        Button button = findViewById(R.id.button);



//        Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this,R.array.operations, android.R.layout.simple_spinner_item
        );

//         Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

//        Apply the adapter to the spinner
        spinner.setAdapter(adapter);


        button.setOnClickListener(view -> {
            String data = spinner.getSelectedItem().toString();

            double n1 = Double.parseDouble(no1.getText().toString());
            double n2 = Double.parseDouble(no2.getText().toString());
            double res = 0d;

            switch(data)
            {
                case "+":
                   res = n1 + n2;
                    break;
                case "-":
                    res = n1 - n2;
                    break;
                case "x":
                    res = n1 * n2;
                    break;
                case "÷":
                    res = n1 / n2;
                    break;
                case "^":
                    res = Math.pow(n1,n2);
                    break;
                case "Choose":
                    Toast.makeText(this, "Choose an Operation.", Toast.LENGTH_SHORT).show();
                    break;
            }
            result.setText(String.valueOf(res));
        });
    }

}