package com.example.masonrussell.firedynamics;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class OpenPipe extends AppCompatActivity {

    public Spinner pressureDropSpinner, pipeDiameterSpinner, pipeLengthSpinner, resultSpinner;
    public EditText pressureDropText, pipeDiameterText, pipeLengthText, specificGravityText;
    public double pressureDropDoub, pipeDiameterDoub, pipeLengthDoub, specificGravityDoub, resultDoub;
    public Button calculateButton;
    public TextView resultText;
    public String pressureDropUnits, pipeDiameterUnits, pipeLengthUnits, resultUnits;
    public LinearLayout resultLayout;
    private DecimalFormat twoDigits;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_open_pipe);
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        twoDigits = new DecimalFormat("0.00");
        pressureDropSpinner = findViewById(R.id.pressureDropSpinner);
        pipeDiameterSpinner = findViewById(R.id.pipeDiameterSpinner);
        pipeLengthSpinner = findViewById(R.id.pipeLengthSpinner);
        resultSpinner = findViewById(R.id.qResultSpinner);
        pressureDropText = findViewById(R.id.pressureDropText);
        pipeDiameterText = findViewById(R.id.pipeDiamterText);
        pipeLengthText = findViewById(R.id.pipeLengthText);
        specificGravityText = findViewById(R.id.specificGravityText);
        resultLayout = findViewById(R.id.resultLayout);
        resultText = findViewById(R.id.qResult);
        calculateButton = findViewById(R.id.getMeasurementsButton);
        resultLayout.setVisibility(View.INVISIBLE);
        addItemsOnResultSpinner(resultSpinner);
        addItemsOnUnitSpinner(pipeDiameterSpinner);
        addItemsOnUnitSpinner(pipeLengthSpinner);
        addItemsOnPressureSpinner(pressureDropSpinner);
        if (ValueClassStorage.openPipe != null)
        {
            ValueClassStorage.OpenPipe openPipe = ValueClassStorage.openPipe;
            pressureDropDoub = openPipe.pressureDropDoub;
            pipeDiameterDoub = openPipe.pipeDiameterDoub;
            pipeLengthDoub = openPipe.lengthDoub;
            specificGravityDoub = openPipe.specificGravityDoub;
            pressureDropText.setText(String.valueOf(pressureDropDoub));
            pipeDiameterText.setText(String.valueOf(pipeDiameterDoub));
            pipeLengthText.setText(String.valueOf(pipeLengthDoub));
            specificGravityText.setText(String.valueOf(specificGravityDoub));
            getResults();
        }
        calculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(resultLayout.getWindowToken(), 0);
                    pressureDropDoub = Double.parseDouble(pressureDropText.getText().toString());
                    pressureDropUnits = pressureDropSpinner.getSelectedItem().toString();
                    pressureDropDoub = ValuesConverstions.PressureToMbar(pressureDropDoub, pressureDropUnits);
                    pipeDiameterDoub = Double.parseDouble(pipeDiameterText.getText().toString());
                    pipeDiameterUnits = pipeDiameterSpinner.getSelectedItem().toString();
                    pipeDiameterDoub = ValuesConverstions.toMillimeters(pipeDiameterDoub, pipeDiameterUnits);
                    pipeLengthDoub = Double.parseDouble(pipeLengthText.getText().toString());
                    pipeLengthUnits = pipeLengthSpinner.getSelectedItem().toString();
                    pipeLengthDoub = ValuesConverstions.toMeters(pipeLengthDoub, pipeLengthUnits);
                    specificGravityDoub = Double.parseDouble(specificGravityText.getText().toString());
                    getResults();
                }
                catch (Exception ex) {
                    String error = "Please Fill the Empty Fields";
                    Toast.makeText(OpenPipe.this, error, Toast.LENGTH_LONG).show();
                }

                resultSpinner.setOnItemSelectedListener(
                        new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                switch (resultSpinner.getSelectedItem().toString())
                                {
                                    case "m^3/hr":
                                        resultDoub = ValuesConverstions.FlowtoMetersCubedPerHour(resultDoub, resultUnits);
                                        resultUnits = resultSpinner.getSelectedItem().toString();
                                        resultText.setText(twoDigits.format(resultDoub));
                                        break;
                                    case "cfm":
                                        resultDoub = ValuesConverstions.Flowtocfm(resultDoub, resultUnits);
                                        resultUnits = resultSpinner.getSelectedItem().toString();
                                        resultText.setText(twoDigits.format(resultDoub));
                                        break;
                                    case "ft^3/sec":
                                        resultDoub = ValuesConverstions.FlowtoFeetCubedPerSec(resultDoub, resultUnits);
                                        resultUnits = resultSpinner.getSelectedItem().toString();
                                        resultText.setText(twoDigits.format(resultDoub));
                                        break;
                                    case "m^3/sec":
                                        resultDoub = ValuesConverstions.FlowtoMetersCubedPerSec(resultDoub, resultUnits);
                                        resultUnits = resultSpinner.getSelectedItem().toString();
                                        resultText.setText(twoDigits.format(resultDoub));
                                        break;
                                }
                            }

                            @Override
                            public void onNothingSelected(AdapterView<?> parent) {

                            }
                        }
                );
            }
        });
    }

    private void getResults()
    {
        resultDoub = Calculations.CalculateOpenPipeQ(pressureDropDoub, pipeDiameterDoub, pipeLengthDoub, specificGravityDoub);
        resultText.setText(twoDigits.format(resultDoub));
        resultLayout.setVisibility(View.VISIBLE);
        resultUnits = resultSpinner.getSelectedItem().toString();
        ValueClassStorage.OpenPipe openPipe = new ValueClassStorage().new OpenPipe(pressureDropDoub, pipeDiameterDoub, pipeLengthDoub, specificGravityDoub, resultDoub);
        ValueClassStorage.openPipe = openPipe;
    }

    public void addItemsOnUnitSpinner(Spinner spinnerToMake)
    {
        List<String> list = new ArrayList<>();
        list.add("m");
        list.add("ft");
        list.add("in");
        list.add("cm");
        list.add("mm");
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, list);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerToMake.setAdapter(dataAdapter);
    }

    public void addItemsOnResultSpinner(Spinner spinnerToMake)
    {
        List<String> list = new ArrayList<>();
        list.add("m^3/hr");
        list.add("ft^3/sec");
        list.add("cfm");
        list.add("m^3/sec");
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, list);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerToMake.setAdapter(dataAdapter);
    }

    public void addItemsOnPressureSpinner(Spinner spinnerToMake)
    {
        List<String> list = new ArrayList<>();
        list.add("in H2O");
        list.add("kPa");
        list.add("mbar");
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, list);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerToMake.setAdapter(dataAdapter);
    }
}
