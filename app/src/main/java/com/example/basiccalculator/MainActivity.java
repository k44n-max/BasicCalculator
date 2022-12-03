package com.example.basiccalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private EditText numOneTxt;
    private EditText numTwoTxt;
    private TextView resultTxt;

    Button btnOne;
    Button btnClear;

    Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        numOneTxt = findViewById(R.id.numOneTxt);
        numOneTxt.addTextChangedListener(inputWatcher);

        numTwoTxt = findViewById(R.id.numTwoTxt);
        numTwoTxt.addTextChangedListener(inputWatcher);

        resultTxt = findViewById(R.id.resultTxt);

        btnOne = findViewById(R.id.btnOne);
        btnClear = findViewById(R.id.btnClear);

        spinner = findViewById(R.id.spinner);
        spinner.setOnItemSelectedListener(this);

        List<String> operations = new ArrayList<>();
        operations.add("Sum");
        operations.add("Subtract");
        operations.add("Multiply");
        operations.add("Divide");

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, operations);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(dataAdapter);

        btnOne.setOnClickListener(view -> {
            int numOne = Integer.parseInt(numOneTxt.getText().toString());
            int numTwo = Integer.parseInt(numTwoTxt.getText().toString());

            String operation = spinner.getSelectedItem().toString();
            int result;

            switch (operation) {
                case "Sum":
                    result = numOne + numTwo;
                    break;
                case "Subtract":
                    result = numOne - numTwo;
                    break;
                case "Multiply":
                    result = numOne * numTwo;
                    break;
                case "Divide":
                    if (numTwo == 0) {
                        Toast.makeText(MainActivity.this, "Cannot divide by zero!",Toast.LENGTH_SHORT).show();
                        result = -1;
                    }
                    else {
                        result = numOne / numTwo;
                    }
                    break;
                default:
                    result = 0;
            }

            resultTxt.setText(String.valueOf(result));
        });

        btnClear.setOnClickListener(view -> {
            numOneTxt.setText("");
            numTwoTxt.setText("");
            resultTxt.setText("");
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
    }

    private final TextWatcher inputWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            String input1 = numOneTxt.getText().toString().trim();
            String input2 = numTwoTxt.getText().toString().trim();

            btnOne.setEnabled(!input1.isEmpty() && !input2.isEmpty());
            btnClear.setEnabled(!input1.isEmpty() && !input2.isEmpty());
            spinner.setEnabled(!input1.isEmpty() && !input2.isEmpty());
        }

        @Override
        public void afterTextChanged(Editable editable) {
        }
    };

}

