package com.example.calculator;

import android.content.Intent;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    final static String TAG = "MainActivity";
    final static String OPERATOR = "";
    static int counter = 0;

    Button zero, one, two, three, four, five, six, seven, eight, nine, dot;
    Button calculate;
    Button plus, minus, multiply, divide;
    Button resultCounter;

    EditText newInput, result;
    TextView currentOperation;

    String operation = "=";
    Double newNumber = null;
    Double oldNumber = null;

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putString(OPERATOR, currentOperation.getText().toString());
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();


        if (savedInstanceState != null){
            currentOperation.setText(savedInstanceState.getString(OPERATOR));
        }

        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Button button = (Button) v;
                newInput.append(button.getText().toString());
            }
        };

        zero.setOnClickListener(listener);
        one.setOnClickListener(listener);
        two.setOnClickListener(listener);
        three.setOnClickListener(listener);
        four.setOnClickListener(listener);
        five.setOnClickListener(listener);
        six.setOnClickListener(listener);
        seven.setOnClickListener(listener);
        eight.setOnClickListener(listener);
        nine.setOnClickListener(listener);
        dot.setOnClickListener(listener);

        View.OnClickListener counterListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Button button = (Button) v;
                Intent intent = new Intent(MainActivity.this, ResultsActivity.class);
                intent.putExtra("COUNTER", counter);
                startActivity(intent);
            }
        };

        resultCounter.setOnClickListener(counterListener);


        View.OnClickListener operatorListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Button button = (Button) v;
                String operator = button.getText().toString();
                String value = newInput.getText().toString();
                if (value.length() != 0) {
                    runCalculate(value, operator);
                }
                operation = operator;
                currentOperation.setText(operation);
            }
        };

        plus.setOnClickListener(operatorListener);
        minus.setOnClickListener(operatorListener);
        multiply.setOnClickListener(operatorListener);
        divide.setOnClickListener(operatorListener);
        calculate.setOnClickListener(operatorListener);

    }

    public void runCalculate(String value, String operator) {

        if (oldNumber == null) {
            oldNumber = Double.valueOf(value);
        } else {
            newNumber = Double.valueOf(value);

            if (operation.equals("=")) {
                operation = operator;
            }

            switch (operation) {
                case "=":
                    counter++;
                    oldNumber = newNumber;
                    break;
                case "+":
                    counter++;
                    oldNumber += newNumber;
                    break;
                case "-":
                    counter++;
                    oldNumber -= newNumber;
                    break;
                case "x":
                    counter++;
                    oldNumber = oldNumber * newNumber;
                    break;
                case "/":
                    counter++;
                    oldNumber = oldNumber / newNumber;
                    break;
            }
        }
        result.setText(oldNumber.toString());
        newInput.setText("");
    }



    protected void init(){
        zero = findViewById(R.id.zero);
        one = findViewById(R.id.one);
        two = findViewById(R.id.two);
        three = findViewById(R.id.three);
        four = findViewById(R.id.four);
        five = findViewById(R.id.five);
        six = findViewById(R.id.six);
        seven = findViewById(R.id.seven);
        eight = findViewById(R.id.eight);
        nine = findViewById(R.id.nine);
        calculate = findViewById(R.id.calculate);
        plus = findViewById(R.id.plus);
        minus = findViewById(R.id.minus);
        multiply = findViewById(R.id.multiply);
        divide = findViewById(R.id.divide);
        newInput = findViewById(R.id.newInput);
        result = findViewById(R.id.result);
        result = findViewById(R.id.result);
        dot = findViewById(R.id.dot);
        currentOperation = findViewById(R.id.operation);
        resultCounter = findViewById(R.id.counter);

    }





}
