package com.dynnamicdevz.dynnamiccalculator;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.ContextThemeWrapper;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import static com.dynnamicdevz.dynnamiccalculator.MainActivity.Operation.*;

//Model View Controller
public class MainActivity extends AppCompatActivity {

    enum Operation {
        DIVIDE,
        ADDITION,
        SUBTRACT,
        MULTIPLY,
        MODULUS,
        PERIOD
    }

    private Operation OP = null;
    private int value = 0;
    private double current = 0.0;
    private TextView outputTV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        outputTV = findViewById(R.id.value_textview);
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(NUM_KEY, outputTV.getText().toString().trim());
    }

    public static final String NUM_KEY = "KEY";

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        outputTV.setText(savedInstanceState.getString(NUM_KEY));
    }

    public void onCalcClick(View view) {
        switch (view.getId()) {
            case R.id.one_button:
                //value = 1;
                handleClick(1);
                break;
            case R.id.two_button:
                //value = 2;
                handleClick(2);
                break;
            case R.id.three_button:
                //value = 3;
                handleClick(3);
                break;
            case R.id.four_button:
                //value = 4;
                handleClick(4);
                break;
            case R.id.five_button:
                //value = 5;
                handleClick(5);
                break;
            case R.id.six_button:
                //value = 6;
                handleClick(6);
                break;
            case R.id.seven_button:
                //value = 7;
                handleClick(7);
                break;
            case R.id.eight_button:
                //value = 8;
                handleClick(8);
                break;
            case R.id.nine_button:
                //value = 9;
                handleClick(9);
                break;
            case R.id.zero_button:
                //value = 0;
                handleClick(0);
                break;
            case R.id.division_button:
                operation(DIVIDE);
                break;
            case R.id.plus_button:
                operation(ADDITION);
                break;
            case R.id.minus_button:
                operation(SUBTRACT);
                break;
            case R.id.multiply_button:
                operation(MULTIPLY);
                break;
            case R.id.period_button:
                value = Integer.parseInt(outputTV.getText().toString().trim());
                outputTV.setText(value + ".");
                break;
            case R.id.plus_minus_button:
                current = -1 * Double.parseDouble(outputTV.getText().toString().trim());
                outputTV.setText(""+ current);
                break;
            case R.id.modulus_button:
                operation(MODULUS);
                break;
            case R.id.equals_button:
                calculateResult();
                break;
            case R.id.ac_button:
                clearAll();
                break;

                //Scientific Calculator
            case R.id.sqrt_button:
                current = Math.sqrt(Double.parseDouble(outputTV.getText().toString().trim()));
                outputTV.setText(current + "");
                break;
            case R.id.log_button:
                current = Math.log10(Double.parseDouble(outputTV.getText().toString().trim()));
                outputTV.setText(current + "");
                break;
        }
    }



    private void clearAll() {
        current = 0.0;
        outputTV.setText("0");
        OP = null;
    }

    private void operation(Operation operation) {
        OP = operation;
        current = Double.parseDouble(outputTV.getText().toString().trim());
        outputTV.setText("0");
    }

    private void calculateResult() {
        if (OP == null) {
            new AlertDialog.Builder(new ContextThemeWrapper(this, R.style.Theme_AppCompat))
                    .setTitle("Operation Error!")
                    .setMessage("Please select operator.")
                    .setPositiveButton("Thanks", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    }).create().show();
            return;
        }
        double ans = 0.0;
        switch (OP) {
            case DIVIDE:
                ans = current / Double.parseDouble(outputTV.getText().toString().trim());
                outputTV.setText(ans + "");
                break;
            case MULTIPLY:
                ans = current * Double.parseDouble(outputTV.getText().toString().trim());
                outputTV.setText(ans + "");
                break;
            case MODULUS:
                ans = current % Double.parseDouble(outputTV.getText().toString().trim());
                outputTV.setText(ans + "");
                break;
            case ADDITION:
                ans = current + Double.parseDouble(outputTV.getText().toString().trim());
                outputTV.setText(ans + "");
                break;
            case SUBTRACT:
                ans = current - Double.parseDouble(outputTV.getText().toString().trim());
                outputTV.setText(ans + "");
                break;
        }
    }

    private void handleClick(int i) {
        String curr = outputTV.getText().toString();

        if (Double.parseDouble(curr) > 0) {
            outputTV.setText(curr + i);
        }
        else {
            outputTV.setText(" " + i);
        }
    }
}