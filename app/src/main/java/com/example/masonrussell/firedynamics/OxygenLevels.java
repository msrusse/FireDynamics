package com.example.masonrussell.firedynamics;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class OxygenLevels extends AppCompatActivity {

    public EditText roomWidthValue, roomLengthValue, roomHeightValue, initialO2Value, heatReleaseRateValue, timestepValue;
    public double roomWidthDoub, roomLengthDoub, roomHeightDoub, roomVolumeDoub, initialO2Doub, heatReleaseRateDoub, timestepDoub, oxygenMolecularWeightDoub, molarMassO2PerMolePerAirDoub, o2PerCubicMeterDoub, initialWeightO2Doub, kiloJoulePerKiloGramO2ConsumedDoub, timeToReduceO2By1Doub, timeToReduceO2By14Doub, t2AlphaValueDoub;
    public String roomWidthUnits, roomLengthUnits, roomHeightUnits, t2FireGrowthRate;
    public Spinner roomWidthUnitSpinner, roomLengthUnitSpinner, roomHeightUnitSpinner, t2FireGrowthRateSpinner, typeOfFireSelectionSpinner;
    public TextView timeToReduceO2By1Result, timeToReduce02By14Result;
    public LinearLayout resultsLayout, steadyStateFireLayout, t2FireLayout;
    public Button getResultsButton;
    public ViewGroup.LayoutParams resultsParams, t2Params, steadyStateFireParams;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_oxygen_levels);
        roomWidthValue = findViewById(R.id.roomWidthValue);
        roomLengthValue = findViewById(R.id.roomLengthValue);
        roomHeightValue = findViewById(R.id.roomHeightValue);
        initialO2Value = findViewById(R.id.initialO2Value);
        heatReleaseRateValue = findViewById(R.id.heatReleaseRateValue);
        timestepValue = findViewById(R.id.timestepValue);
        roomWidthUnitSpinner = findViewById(R.id.roomWidthSpinner);
        roomLengthUnitSpinner = findViewById(R.id.roomLengthSpinner);
        roomHeightUnitSpinner = findViewById(R.id.roomHeightSpinner);
        t2FireGrowthRateSpinner = findViewById(R.id.t2FireGrowthRateSelection);
        typeOfFireSelectionSpinner = findViewById(R.id.fireTypeSelectionSpinner);
        addSelectionsOnFireTypeSpinner(typeOfFireSelectionSpinner);
        addItemsOnUnitSpinner(roomHeightUnitSpinner);
        addItemsOnUnitSpinner(roomWidthUnitSpinner);
        addItemsOnUnitSpinner(roomLengthUnitSpinner);
        addt2GrowthRateSpinner(t2FireGrowthRateSpinner);
        timeToReduceO2By1Result = findViewById(R.id.timetoReduceO2By1Result);
        timeToReduce02By14Result = findViewById(R.id.timetoReduceO2By14Result);
        resultsLayout = findViewById(R.id.resultsLayout);
        steadyStateFireLayout = findViewById(R.id.steadyStateFire);
        t2FireLayout = findViewById(R.id.t2Fire);
        resultsLayout.setVisibility(View.INVISIBLE);
        steadyStateFireLayout.setVisibility(View.INVISIBLE);
        t2FireLayout.setVisibility(View.INVISIBLE);
        getResultsButton = findViewById(R.id.getMeasurementsButton);
        resultsParams = resultsLayout.getLayoutParams();
        t2Params = t2FireLayout.getLayoutParams();
        steadyStateFireParams = steadyStateFireLayout.getLayoutParams();
        oxygenMolecularWeightDoub = 32.0;
        typeOfFireSelectionSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (typeOfFireSelectionSpinner.getSelectedItem().toString())
                {
                    case "Steady State Fire":
                        resultsParams.height = 0;
                        resultsLayout.setLayoutParams(resultsParams);
                        resultsLayout.setVisibility(View.INVISIBLE);
                        t2Params.height = 0;
                        t2FireLayout.setLayoutParams(t2Params);
                        t2FireLayout.setVisibility(View.INVISIBLE);
                        steadyStateFireParams.height = LinearLayout.LayoutParams.WRAP_CONTENT;
                        steadyStateFireLayout.setLayoutParams(steadyStateFireParams);
                        steadyStateFireLayout.setVisibility(View.VISIBLE);
                        break;
                    case "t^2 Fire":
                        resultsParams.height = 0;
                        resultsLayout.setLayoutParams(resultsParams);
                        resultsLayout.setVisibility(View.INVISIBLE);
                        steadyStateFireParams.height = 0;
                        steadyStateFireLayout.setLayoutParams(steadyStateFireParams);
                        steadyStateFireLayout.setVisibility(View.INVISIBLE);
                        t2Params.height = LinearLayout.LayoutParams.MATCH_PARENT;
                        t2FireLayout.setLayoutParams(t2Params);
                        t2FireLayout.setVisibility(View.VISIBLE);
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        getResultsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                roomWidthDoub = Double.parseDouble(roomWidthValue.getText().toString());
                roomWidthUnits = roomWidthUnitSpinner.getSelectedItem().toString();
                roomLengthDoub = Double.parseDouble(roomLengthValue.getText().toString());
                roomLengthUnits = roomLengthUnitSpinner.getSelectedItem().toString();
                roomHeightDoub = Double.parseDouble(roomHeightValue.getText().toString());
                roomHeightUnits = roomHeightUnitSpinner.getSelectedItem().toString();
                initialO2Doub = Double.parseDouble(initialO2Value.getText().toString())/100;
                roomWidthDoub = ValuesConverstions.toMeters(roomWidthDoub, roomWidthUnits);
                roomLengthDoub = ValuesConverstions.toMeters(roomLengthDoub, roomLengthUnits);
                roomHeightDoub = ValuesConverstions.toMeters(roomHeightDoub, roomHeightUnits);
                roomVolumeDoub = roomLengthDoub*roomWidthDoub*roomHeightDoub;
                molarMassO2PerMolePerAirDoub = oxygenMolecularWeightDoub * initialO2Doub;
                o2PerCubicMeterDoub = 1000/22.4*molarMassO2PerMolePerAirDoub/1000;
                initialWeightO2Doub = o2PerCubicMeterDoub*roomVolumeDoub;
                kiloJoulePerKiloGramO2ConsumedDoub = 13100;
                switch (typeOfFireSelectionSpinner.getSelectedItem().toString())
                {
                    case "Steady State Fire":
                        heatReleaseRateDoub = Double.parseDouble(heatReleaseRateValue.getText().toString());
                        timeToReduceO2By1Doub = ((((initialWeightO2Doub/initialO2Doub)/100)*kiloJoulePerKiloGramO2ConsumedDoub)/heatReleaseRateDoub);
                        timeToReduceO2By14Doub = ((initialO2Doub-.14)*100*timeToReduceO2By1Doub);
                        timeToReduceO2By1Result.setText(String.valueOf(Math.round(timeToReduceO2By1Doub)));
                        timeToReduce02By14Result.setText(String.valueOf(Math.round(timeToReduceO2By14Doub)));
                        resultsParams.height = LinearLayout.LayoutParams.WRAP_CONTENT;
                        resultsLayout.setLayoutParams(resultsParams);
                        resultsLayout.setVisibility(View.VISIBLE);
                        break;
                    case "t^2 Fire":
                        t2FireGrowthRate = t2FireGrowthRateSpinner.getSelectedItem().toString();
                        t2AlphaValueDoub = ValuesConverstions.t2GrowthRateAlphaValue(t2FireGrowthRate);
                        timestepDoub = Double.parseDouble(timestepValue.getText().toString());
                        break;
                }

            }
        });
    }

    public void addSelectionsOnFireTypeSpinner(Spinner spinnerToMake)
    {
        List<String> list = new ArrayList<>();
        list.add("Steady State Fire");
        list.add("t^2 Fire");
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, list);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerToMake.setAdapter(dataAdapter);
    }

    public void addItemsOnUnitSpinner(Spinner spinnerToMake)
    {
        List<String> list = new ArrayList<>();
        list.add("ft");
        list.add("in");
        list.add("m");
        list.add("cm");
        list.add("mm");
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, list);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerToMake.setAdapter(dataAdapter);
    }

    public void addt2GrowthRateSpinner(Spinner spinnerToMake)
    {
        List<String> list = new ArrayList<>();
        list.add("Fast");
        list.add("Medium");
        list.add("Slow");
        list.add("Ultrafast");
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, list);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerToMake.setAdapter(dataAdapter);
    }
}
