package com.example.calculator;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class ResultsActivity extends AppCompatActivity {

    private static final String TAG = "ResultsActivity";
    private static final String RESULT = "";

    TextView resultTotal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);
        resultTotal = findViewById(R.id.resultsTotal);

        if (savedInstanceState != null){
            resultTotal.setText(savedInstanceState.getString(RESULT));
        }



        Intent receivedIntent = getIntent();

        int result = fetchResults(receivedIntent);

        resultTotal.setText("You used " + result + " operators");
    }

    protected int fetchResults(Intent intent){
        int result = intent.getIntExtra("COUNTER", 0);
        Log.d(TAG, "fetchresults: " + result);
        return result;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putString(RESULT, resultTotal.getText().toString());
    }
}
