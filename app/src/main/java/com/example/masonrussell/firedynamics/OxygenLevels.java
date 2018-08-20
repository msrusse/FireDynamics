package com.example.masonrussell.firedynamics;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
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

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.DataPointInterface;
import com.jjoe64.graphview.series.LineGraphSeries;
import com.jjoe64.graphview.series.OnDataPointTapListener;
import com.jjoe64.graphview.series.Series;

import java.util.ArrayList;
import java.util.List;

public class OxygenLevels extends AppCompatActivity {

    EditText roomWidthValue, roomLengthValue, roomHeightValue, initialO2Value, heatReleaseRateValue, timestepValue;
    double roomWidthDoub, roomLengthDoub, roomHeightDoub, roomVolumeDoub, initialO2Doub, heatReleaseRateDoub, timestepDoub, oxygenMolecularWeightDoub, molarMassO2PerMolePerAirDoub, o2PerCubicMeterDoub, initialWeightO2Doub, kiloJoulePerKiloGramO2ConsumedDoub, timeToReduceO2By1Doub, timeToReduceO2By14Doub, t2AlphaValueDoub;
    String roomWidthUnits, roomLengthUnits, roomHeightUnits, t2FireGrowthRate;
    Spinner roomWidthUnitSpinner, roomLengthUnitSpinner, roomHeightUnitSpinner, t2FireGrowthRateSpinner, typeOfFireSelectionSpinner, graphSelectionSpinner;
    TextView timeToReduceO2By1Result, timeToReduce02By14Result;
    LinearLayout resultsLayout, steadyStateFireLayout, t2FireLayout;
    Button getResultsButton, returnButton;
    ViewGroup.LayoutParams resultsParams, t2Params, steadyStateFireParams;
    GraphView resultsGraph;
    ArrayList<Double> time = new ArrayList<>(), q = new ArrayList<>(), totalEnergy = new ArrayList<>(), totalO2Consumed = new ArrayList<>(), percentO2InRoom = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_oxygen_levels);
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
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
        graphSelectionSpinner = findViewById(R.id.graphSelectionSpinner);
        addGraphOptionsOnSpinner(graphSelectionSpinner);
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
                        t2Params.height = LinearLayout.LayoutParams.WRAP_CONTENT;
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
                try {
                    InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(resultsLayout.getWindowToken(), 0);
                    roomWidthDoub = Double.parseDouble(roomWidthValue.getText().toString());
                    roomWidthUnits = roomWidthUnitSpinner.getSelectedItem().toString();
                    roomLengthDoub = Double.parseDouble(roomLengthValue.getText().toString());
                    roomLengthUnits = roomLengthUnitSpinner.getSelectedItem().toString();
                    roomHeightDoub = Double.parseDouble(roomHeightValue.getText().toString());
                    roomHeightUnits = roomHeightUnitSpinner.getSelectedItem().toString();
                    initialO2Doub = Double.parseDouble(initialO2Value.getText().toString()) / 100;
                    roomWidthDoub = ValuesConverstions.toMeters(roomWidthDoub, roomWidthUnits);
                    roomLengthDoub = ValuesConverstions.toMeters(roomLengthDoub, roomLengthUnits);
                    roomHeightDoub = ValuesConverstions.toMeters(roomHeightDoub, roomHeightUnits);
                    roomVolumeDoub = roomLengthDoub * roomWidthDoub * roomHeightDoub;
                    molarMassO2PerMolePerAirDoub = oxygenMolecularWeightDoub * initialO2Doub;
                    o2PerCubicMeterDoub = 1000 / 22.4 * molarMassO2PerMolePerAirDoub / 1000;
                    initialWeightO2Doub = o2PerCubicMeterDoub * roomVolumeDoub;
                    kiloJoulePerKiloGramO2ConsumedDoub = 13100;
                    switch (typeOfFireSelectionSpinner.getSelectedItem().toString()) {
                        case "Steady State Fire":
                            heatReleaseRateDoub = Double.parseDouble(heatReleaseRateValue.getText().toString());
                            timeToReduceO2By1Doub = ((((initialWeightO2Doub / initialO2Doub) / 100) * kiloJoulePerKiloGramO2ConsumedDoub) / heatReleaseRateDoub);
                            timeToReduceO2By14Doub = ((initialO2Doub - .14) * 100 * timeToReduceO2By1Doub);
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
                            calculateTime();
                            calculateQ();
                            calculateTotalEnergy();
                            calculateTotalO2Consumed();
                            calculatePercentO2InRoom();
                            switch (graphSelectionSpinner.getSelectedItem().toString()) {
                                case "Q Over Time":
                                    setContentView(R.layout.activity_oxygen_levels_result_graph);
                                    returnButton = findViewById(R.id.button);
                                    resultsGraph = findViewById(R.id.resultsGraph);
                                    addPointsOnGraph(q);
                                    returnButton.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
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
                                            graphSelectionSpinner = findViewById(R.id.graphSelectionSpinner);
                                            addGraphOptionsOnSpinner(graphSelectionSpinner);
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
                                                            t2Params.height = LinearLayout.LayoutParams.WRAP_CONTENT;
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
                                                    try {
                                                        InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                                                        roomWidthDoub = Double.parseDouble(roomWidthValue.getText().toString());
                                                        roomWidthUnits = roomWidthUnitSpinner.getSelectedItem().toString();
                                                        roomLengthDoub = Double.parseDouble(roomLengthValue.getText().toString());
                                                        roomLengthUnits = roomLengthUnitSpinner.getSelectedItem().toString();
                                                        roomHeightDoub = Double.parseDouble(roomHeightValue.getText().toString());
                                                        roomHeightUnits = roomHeightUnitSpinner.getSelectedItem().toString();
                                                        initialO2Doub = Double.parseDouble(initialO2Value.getText().toString()) / 100;
                                                        roomWidthDoub = ValuesConverstions.toMeters(roomWidthDoub, roomWidthUnits);
                                                        roomLengthDoub = ValuesConverstions.toMeters(roomLengthDoub, roomLengthUnits);
                                                        roomHeightDoub = ValuesConverstions.toMeters(roomHeightDoub, roomHeightUnits);
                                                        getResults();
                                                    }
                                                    catch (Exception ex) {
                                                        String error = "Please Fill the Empty Fields";
                                                        Toast.makeText(OxygenLevels.this, error, Toast.LENGTH_LONG).show();
                                                    }

                                                }
                                            });
                                        }
                                    });
                                    break;
                                case "Total Energy Over Time":
                                    setContentView(R.layout.activity_oxygen_levels_result_graph);
                                    returnButton = findViewById(R.id.button);
                                    resultsGraph = findViewById(R.id.resultsGraph);
                                    addPointsOnGraph(totalEnergy);
                                    returnButton.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
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
                                            graphSelectionSpinner = findViewById(R.id.graphSelectionSpinner);
                                            addGraphOptionsOnSpinner(graphSelectionSpinner);
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
                                                            t2Params.height = LinearLayout.LayoutParams.WRAP_CONTENT;
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
                                                    try {
                                                        InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                                                        roomWidthDoub = Double.parseDouble(roomWidthValue.getText().toString());
                                                        roomWidthUnits = roomWidthUnitSpinner.getSelectedItem().toString();
                                                        roomLengthDoub = Double.parseDouble(roomLengthValue.getText().toString());
                                                        roomLengthUnits = roomLengthUnitSpinner.getSelectedItem().toString();
                                                        roomHeightDoub = Double.parseDouble(roomHeightValue.getText().toString());
                                                        roomHeightUnits = roomHeightUnitSpinner.getSelectedItem().toString();
                                                        initialO2Doub = Double.parseDouble(initialO2Value.getText().toString()) / 100;
                                                        roomWidthDoub = ValuesConverstions.toMeters(roomWidthDoub, roomWidthUnits);
                                                        roomLengthDoub = ValuesConverstions.toMeters(roomLengthDoub, roomLengthUnits);
                                                        roomHeightDoub = ValuesConverstions.toMeters(roomHeightDoub, roomHeightUnits);
                                                        getResults();
                                                    }
                                                    catch (Exception ex) {
                                                        String error = "Please Fill the Empty Fields";
                                                        Toast.makeText(OxygenLevels.this, error, Toast.LENGTH_LONG).show();
                                                    }

                                                }
                                            });
                                        }
                                    });
                                    break;
                                case "Total O2 Consumed Over Time":
                                    setContentView(R.layout.activity_oxygen_levels_result_graph);
                                    returnButton = findViewById(R.id.button);
                                    resultsGraph = findViewById(R.id.resultsGraph);
                                    addPointsOnGraph(totalO2Consumed);
                                    returnButton.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
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
                                            graphSelectionSpinner = findViewById(R.id.graphSelectionSpinner);
                                            addGraphOptionsOnSpinner(graphSelectionSpinner);
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
                                                            t2Params.height = LinearLayout.LayoutParams.WRAP_CONTENT;
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
                                                    try {
                                                        InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                                                        imm.hideSoftInputFromWindow(resultsLayout.getWindowToken(), 0);
                                                        roomWidthDoub = Double.parseDouble(roomWidthValue.getText().toString());
                                                        roomWidthUnits = roomWidthUnitSpinner.getSelectedItem().toString();
                                                        roomLengthDoub = Double.parseDouble(roomLengthValue.getText().toString());
                                                        roomLengthUnits = roomLengthUnitSpinner.getSelectedItem().toString();
                                                        roomHeightDoub = Double.parseDouble(roomHeightValue.getText().toString());
                                                        roomHeightUnits = roomHeightUnitSpinner.getSelectedItem().toString();
                                                        initialO2Doub = Double.parseDouble(initialO2Value.getText().toString()) / 100;
                                                        roomWidthDoub = ValuesConverstions.toMeters(roomWidthDoub, roomWidthUnits);
                                                        roomLengthDoub = ValuesConverstions.toMeters(roomLengthDoub, roomLengthUnits);
                                                        roomHeightDoub = ValuesConverstions.toMeters(roomHeightDoub, roomHeightUnits);
                                                        getResults();
                                                    }
                                                    catch (Exception ex) {
                                                        String error = "Please Fill the Empty Fields";
                                                        Toast.makeText(OxygenLevels.this, error, Toast.LENGTH_LONG).show();
                                                    }

                                                }
                                            });
                                        }
                                    });
                                    break;
                                case "Total Percent O2 In Room":
                                    setContentView(R.layout.activity_oxygen_levels_result_graph);
                                    returnButton = findViewById(R.id.button);
                                    resultsGraph = findViewById(R.id.resultsGraph);
                                    addPointsOnGraph(percentO2InRoom);
                                    returnButton.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
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
                                            graphSelectionSpinner = findViewById(R.id.graphSelectionSpinner);
                                            addGraphOptionsOnSpinner(graphSelectionSpinner);
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
                                                            t2Params.height = LinearLayout.LayoutParams.WRAP_CONTENT;
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
                                                    try {
                                                        InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                                                        imm.hideSoftInputFromWindow(resultsLayout.getWindowToken(), 0);
                                                        roomWidthDoub = Double.parseDouble(roomWidthValue.getText().toString());
                                                        roomWidthUnits = roomWidthUnitSpinner.getSelectedItem().toString();
                                                        roomLengthDoub = Double.parseDouble(roomLengthValue.getText().toString());
                                                        roomLengthUnits = roomLengthUnitSpinner.getSelectedItem().toString();
                                                        roomHeightDoub = Double.parseDouble(roomHeightValue.getText().toString());
                                                        roomHeightUnits = roomHeightUnitSpinner.getSelectedItem().toString();
                                                        initialO2Doub = Double.parseDouble(initialO2Value.getText().toString()) / 100;
                                                        roomWidthDoub = ValuesConverstions.toMeters(roomWidthDoub, roomWidthUnits);
                                                        roomLengthDoub = ValuesConverstions.toMeters(roomLengthDoub, roomLengthUnits);
                                                        roomHeightDoub = ValuesConverstions.toMeters(roomHeightDoub, roomHeightUnits);
                                                        getResults();
                                                    }
                                                    catch (Exception ex) {
                                                        String error = "Please Fill the Empty Fields";
                                                        Toast.makeText(OxygenLevels.this, error, Toast.LENGTH_LONG).show();
                                                    }

                                                }
                                            });
                                        }
                                    });
                                    break;
                            }
                            break;
                    }
                    ValueClassStorage.OxygenLevels oxygenLevels = new ValueClassStorage().new OxygenLevels(roomWidthDoub, roomLengthDoub, roomHeightDoub, initialO2Doub, heatReleaseRateDoub, timestepDoub, typeOfFireSelectionSpinner.getSelectedItem().toString(), t2FireGrowthRateSpinner.getSelectedItem().toString(), graphSelectionSpinner.getSelectedItem().toString());
                    ValueClassStorage.oxygenLevels = oxygenLevels;
                }
                catch (Exception ex) {
                    String error = "Please Fill the Empty Fields";
                    Toast.makeText(OxygenLevels.this, error, Toast.LENGTH_LONG).show();
                }

            }
        });
    }

    private void getResults()
    {


        roomVolumeDoub = roomLengthDoub * roomWidthDoub * roomHeightDoub;
        molarMassO2PerMolePerAirDoub = oxygenMolecularWeightDoub * initialO2Doub;
        o2PerCubicMeterDoub = 1000 / 22.4 * molarMassO2PerMolePerAirDoub / 1000;
        initialWeightO2Doub = o2PerCubicMeterDoub * roomVolumeDoub;
        kiloJoulePerKiloGramO2ConsumedDoub = 13100;
        switch (typeOfFireSelectionSpinner.getSelectedItem().toString()) {
            case "Steady State Fire":
                heatReleaseRateDoub = Double.parseDouble(heatReleaseRateValue.getText().toString());
                timeToReduceO2By1Doub = ((((initialWeightO2Doub / initialO2Doub) / 100) * kiloJoulePerKiloGramO2ConsumedDoub) / heatReleaseRateDoub);
                timeToReduceO2By14Doub = ((initialO2Doub - .14) * 100 * timeToReduceO2By1Doub);
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
                calculateTime();
                calculateQ();
                calculateTotalEnergy();
                calculateTotalO2Consumed();
                calculatePercentO2InRoom();
                switch (graphSelectionSpinner.getSelectedItem().toString()) {
                    case "Q Over Time":
                        setContentView(R.layout.activity_oxygen_levels_result_graph);
                        returnButton = findViewById(R.id.button);
                        resultsGraph = findViewById(R.id.resultsGraph);
                        addPointsOnGraph(q);
                        returnButton.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                setContentView(R.layout.activity_oxygen_levels);
                            }
                        });
                        break;
                    case "Total Energy Over Time":
                        setContentView(R.layout.activity_oxygen_levels_result_graph);
                        returnButton = findViewById(R.id.button);
                        resultsGraph = findViewById(R.id.resultsGraph);
                        addPointsOnGraph(totalEnergy);
                        returnButton.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                setContentView(R.layout.activity_oxygen_levels);
                            }
                        });
                        break;
                    case "Total O2 Consumed Over Time":
                        setContentView(R.layout.activity_oxygen_levels_result_graph);
                        returnButton = findViewById(R.id.button);
                        resultsGraph = findViewById(R.id.resultsGraph);
                        addPointsOnGraph(totalO2Consumed);
                        returnButton.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                setContentView(R.layout.activity_oxygen_levels);
                            }
                        });
                        break;
                    case "Total Percent O2 In Room":
                        setContentView(R.layout.activity_oxygen_levels_result_graph);
                        returnButton = findViewById(R.id.button);
                        resultsGraph = findViewById(R.id.resultsGraph);
                        addPointsOnGraph(percentO2InRoom);
                        returnButton.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                setContentView(R.layout.activity_oxygen_levels);
                            }
                        });
                        break;
                }
                break;
        }
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

    public void addGraphOptionsOnSpinner(Spinner spinnerToMake)
    {
        List<String> list = new ArrayList<>();
        list.add("Q Over Time");
        list.add("Total Energy Over Time");
        list.add("Total O2 Consumed Over Time");
        list.add("Total Percent O2 In Room");
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, list);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerToMake.setAdapter(dataAdapter);
    }

    void calculateTime()
    {
        for (double i=0; i<350;i += timestepDoub)
        {
            time.add(i);
        }
    }

    void calculateQ()
    {
        for (double i=0; i<350;i += timestepDoub)
        {
            q.add(t2AlphaValueDoub*Math.pow(i,2));
        }
    }

    void calculateTotalEnergy()
    {
        int counter=0;
        for (double i=0; i<350;i+=timestepDoub)
        {
            if (counter==0)
            {
                totalEnergy.add(0.0);
            }
            else {
                totalEnergy.add(q.get(counter) * (i - (i-timestepDoub)) + totalEnergy.get(counter - 1));
            }
            counter ++;
        }
    }

    void calculateTotalO2Consumed()
    {
        int counter=0;
        for (double i=0;i<350;i+=timestepDoub)
        {
            if (counter==0)
            {
                totalO2Consumed.add(0.0);
            }
            else
            {
                totalO2Consumed.add((totalEnergy.get(counter)/kiloJoulePerKiloGramO2ConsumedDoub));
            }
            counter ++;
        }
    }

    void calculatePercentO2InRoom()
    {
        for (int i=0;i<time.size();i++)
        {
            percentO2InRoom.add(((initialWeightO2Doub-totalO2Consumed.get(i))/(initialWeightO2Doub/initialO2Doub))*100);
        }
    }

    void addPointsOnGraph(ArrayList<Double> yAxisArray)
    {
        resultsGraph.removeAllSeries();
        DataPoint[] dp = new DataPoint[time.size()-1];
        for (int x=0; x<time.size()-1; x++)
        {
            dp[x] = new DataPoint(time.get(x),yAxisArray.get(x));
        }
        LineGraphSeries<DataPoint> series = new LineGraphSeries<>(dp);
        series.setDrawDataPoints(true);
        series.setDataPointsRadius(15);
        resultsGraph.getViewport().setScalable(true);
        resultsGraph.getViewport().setScalableY(true);
        resultsGraph.getViewport().setXAxisBoundsManual(true);
        resultsGraph.getViewport().setMinX(time.get(0));
        resultsGraph.getViewport().setMaxX(time.get(time.size()-1));
        resultsGraph.getViewport().setYAxisBoundsManual(true);
        if (yAxisArray.get(0) != 0)
        {
            resultsGraph.getViewport().setMinY(0);
        }
        else
        {
            resultsGraph.getViewport().setMinY(yAxisArray.get(0));
        }
        resultsGraph.getViewport().setMaxY(yAxisArray.get(yAxisArray.size() - 1));
        resultsGraph.addSeries(series);
        series.setOnDataPointTapListener(new OnDataPointTapListener() {
            @Override
            public void onTap(Series series, DataPointInterface dataPoint) {
                Toast.makeText(OxygenLevels.this, String.valueOf(dataPoint), Toast.LENGTH_LONG).show();
            }
        });
    }
}
