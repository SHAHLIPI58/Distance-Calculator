package com.example.converter;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private RadioGroup radiogroup;
    private RadioButton radiobuttonchecked;
    //private Button convert;
    private EditText editTextInputValue;
    private TextView outputTextView;
    private TextView InputUnitValue;
    private TextView OutputUnitValue;
    private TextView History;
    String Historystring = "";
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        History = findViewById(R.id.History);
        History.setMovementMethod(new ScrollingMovementMethod());
        editTextInputValue = findViewById(R.id.editTextInputValue);
        outputTextView = findViewById(R.id.outputTextView);
        radiogroup = (RadioGroup) findViewById(R.id.radioGroup);
        InputUnitValue = findViewById(R.id.InputUnitValue);
        OutputUnitValue = findViewById(R.id.OutputUnitValue);
    }

    public void clearHistoryclick(View v) {
        outputTextView.setText("");
        History.setText("");
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putString("ConversionHistory", History.getText().toString());
        outState.putString("InputUnitValue",InputUnitValue.getText().toString());
        outState.putString("OutputUnitValue",OutputUnitValue.getText().toString());
        outState.putString("outputTextView", outputTextView.getText().toString());
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        Historystring = savedInstanceState.getString("ConversionHistory");
        History.setText(Historystring);
        InputUnitValue.setText(savedInstanceState.getString("InputUnitValue"));
        OutputUnitValue.setText(savedInstanceState.getString("OutputUnitValue"));
        outputTextView.setText(savedInstanceState.getString("outputTextView"));
    }

    public void radiobuttonEventsCheck(View v) {
        editTextInputValue.setText("");
        outputTextView.setText("");
        radiobuttonchecked = (RadioButton) findViewById(radiogroup.getCheckedRadioButtonId());

        switch (radiobuttonchecked.getText().toString()) {
            case "Miles to Kilometers": //
                InputUnitValue.setText("Miles Value:");
                OutputUnitValue.setText("Kilometers Value:");
                break;
            case "Kilometers to Miles":
                InputUnitValue.setText("Kilometers Value:");
                OutputUnitValue.setText("Miles Value:");
                break;
        }
    }

    public void convert(View v) {
        radiogroup = (RadioGroup) findViewById(R.id.radioGroup);
        int id = radiogroup.getCheckedRadioButtonId();
        String input = editTextInputValue.getText().toString();
        Historystring = History.getText().toString();
        if (input.trim().length() > 0) {
            Double inputval = Double.parseDouble(input);
            Double output;
            switch (id) {
                case R.id.radio1_mtk:
                    output = inputval * 1.60934;
                    outputTextView.setText(String.format("%.1f", output));
                    History.setText(String.format("%s Mi ==> %s Km\n%s",
                                                    String.format("%.1f", inputval),
                                                    String.format("%.1f", output),
                                                    Historystring));
                    break;
                case R.id.radio2_ktm:
                    output = inputval * 0.621371;
                    outputTextView.setText(String.format("%.1f", output));
                    History.setText(String.format("%s Km ==> %s Mi\n%s",
                                                    String.format("%.1f", inputval),
                                                    String.format("%.1f", output),
                                                    Historystring));
                    break;
            }
            editTextInputValue.setText("");
        } else {
            Toast.makeText(getApplicationContext(), "Please add input value", Toast.LENGTH_SHORT).show();
        }
    }
}
