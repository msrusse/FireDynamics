package com.example.masonrussell.firedynamics;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class Conduction extends AppCompatActivity {

    public Spinner materialSpinner, lengthUnitSpinner, hotSideUnitSpinner, coldSideUnitSpinner, heatFluxResultSpinner, thermalPenTimeResultSpinner;
    public LinearLayout resultLayout;
    public EditText lengthResult, hotSideResult, coldSideResult;
    public TextView heatFluxResult, thermalPenTimeResult;
    public Button getResults;
    public String materialString, currentHeatFluxUnits, currentThermalPenTimeUnits;
    public double lengthDoub, hotSideDoub, coldSideDoub, heatFluxDoub, thermalPenTimeDoub, thermalConductivityDoub, specificHeatDoub, densityDoub, thermalDiffusivityDoub, thermalIntertiaDoub;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conduction);
        final DecimalFormat twoDigits = new DecimalFormat("0.00");
        final DecimalFormat fourDigits = new DecimalFormat("0.0000");
        materialSpinner = findViewById(R.id.materialSpinner);
        lengthUnitSpinner = findViewById(R.id.lengthSpinner);
        hotSideUnitSpinner= findViewById(R.id.hotSideSpinner);
        coldSideUnitSpinner = findViewById(R.id.coldSideSpinner);
        heatFluxResultSpinner = findViewById(R.id.heatFluxResultSpinner);
        thermalPenTimeResultSpinner = findViewById(R.id.thermalPenTimeResultSpinner);
        resultLayout = findViewById(R.id.resultLayout);
        lengthResult = findViewById(R.id.lengthResult);
        hotSideResult = findViewById(R.id.hotSideResult);
        coldSideResult = findViewById(R.id.coldSideResult);
        heatFluxResult = findViewById(R.id.heatFluxResult);
        thermalPenTimeResult = findViewById(R.id.thermalPenTimeResult);
        getResults = findViewById(R.id.getResultsButton);
        resultLayout.setVisibility(View.INVISIBLE);
        addItemsOnUnitSpinner(lengthUnitSpinner);
        addItemsOnTempSpinner(coldSideUnitSpinner);
        addItemsOnTempSpinner(hotSideUnitSpinner);
        addItemsOnMaterialSpinner(materialSpinner);
        addItemsOnHeatFluxResultsSpinner(heatFluxResultSpinner);
        addItemsOnThermalPenTimeResultSpinner(thermalPenTimeResultSpinner);
        getResults.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lengthDoub = Double.parseDouble(lengthResult.getText().toString());
                hotSideDoub = Double.parseDouble(hotSideResult.getText().toString());
                coldSideDoub = Double.parseDouble(coldSideResult.getText().toString());
                materialString = materialSpinner.getSelectedItem().toString();
                lengthDoub = ValuesConverstions.toMeters(lengthDoub, lengthUnitSpinner.getSelectedItem().toString());
                hotSideDoub = ValuesConverstions.toDegreesCentigrade(hotSideDoub, hotSideUnitSpinner.getSelectedItem().toString());
                coldSideDoub = ValuesConverstions.toDegreesCentigrade(coldSideDoub, coldSideUnitSpinner.getSelectedItem().toString());
                thermalConductivityDoub = ValuesConverstions.ConductionThermalConductivity(materialString);
                specificHeatDoub = ValuesConverstions.ConductionSpecificHeat(materialString);
                densityDoub = ValuesConverstions.ConductionDensity(materialString);
                thermalDiffusivityDoub = getThermalDiffusivity(thermalConductivityDoub, specificHeatDoub, densityDoub);
                thermalIntertiaDoub = ValuesConverstions.ConductionThermalInertia(materialString);
                thermalConductivityDoub = thermalConductivityDoub / 1000;
                heatFluxDoub = Calculations.CalculateConductiveHeatFlux(lengthDoub, thermalConductivityDoub, hotSideDoub, coldSideDoub);
                heatFluxResult.setText(fourDigits.format(heatFluxDoub));
                currentHeatFluxUnits = heatFluxResultSpinner.getSelectedItem().toString();
                thermalPenTimeDoub = Calculations.CalculateConductiveThermalPenTime(lengthDoub, thermalDiffusivityDoub);
                thermalPenTimeResult.setText(twoDigits.format(thermalPenTimeDoub));
                currentThermalPenTimeUnits = thermalPenTimeResultSpinner.getSelectedItem().toString();
                resultLayout.setVisibility(View.VISIBLE);
                heatFluxResultSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        switch (heatFluxResultSpinner.getSelectedItem().toString())
                        {
                            case "Btu/sec/ft^2":
                                heatFluxDoub = ValuesConverstions.heatFluxToBtuPerSecondPerSquaredFeet(heatFluxDoub, currentHeatFluxUnits);
                                heatFluxResult.setText(fourDigits.format(heatFluxDoub));
                                currentHeatFluxUnits = heatFluxResultSpinner.getSelectedItem().toString();
                                break;
                            case "kW/m^2":
                                heatFluxDoub = ValuesConverstions.heatFluxToKillowattPerSquaredMeters(heatFluxDoub, currentHeatFluxUnits);
                                heatFluxResult.setText(fourDigits.format(heatFluxDoub));
                                currentHeatFluxUnits = heatFluxResultSpinner.getSelectedItem().toString();
                                break;
                        }
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });
                thermalPenTimeResultSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        switch (thermalPenTimeResultSpinner.getSelectedItem().toString())
                        {
                            case "sec":
                                thermalPenTimeDoub = ValuesConverstions.timeToSeconds(thermalPenTimeDoub, currentThermalPenTimeUnits);
                                thermalPenTimeResult.setText(twoDigits.format(thermalPenTimeDoub));
                                currentThermalPenTimeUnits = thermalPenTimeResultSpinner.getSelectedItem().toString();
                                break;
                            case "min":
                                thermalPenTimeDoub = ValuesConverstions.timeToMinutes(thermalPenTimeDoub, currentThermalPenTimeUnits);
                                thermalPenTimeResult.setText(twoDigits.format(thermalPenTimeDoub));
                                currentThermalPenTimeUnits = thermalPenTimeResultSpinner.getSelectedItem().toString();
                                break;
                            case "hour":
                                thermalPenTimeDoub = ValuesConverstions.timeToHours(thermalPenTimeDoub, currentThermalPenTimeUnits);
                                thermalPenTimeResult.setText(twoDigits.format(thermalPenTimeDoub));
                                currentThermalPenTimeUnits = thermalPenTimeResultSpinner.getSelectedItem().toString();
                                break;
                        }
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });
            }
        });

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

    public void addItemsOnTempSpinner(Spinner spinnerToMake)
    {
        List<String> list = new ArrayList<>();
        list.add("C");
        list.add("F");
        list.add("K");
        list.add("R");
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, list);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerToMake.setAdapter(dataAdapter);
    }

    public void addItemsOnThermalPenTimeResultSpinner(Spinner spinnerToMake)
    {
        List<String> list = new ArrayList<>();
        list.add("sec");
        list.add("min");
        list.add("hour");
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, list);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerToMake.setAdapter(dataAdapter);
    }

    public void addItemsOnHeatFluxResultsSpinner(Spinner spinnerToMake)
    {
        List<String> list = new ArrayList<>();
        list.add("kW/m^2");
        list.add("Btu/sec/ft^2");
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, list);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerToMake.setAdapter(dataAdapter);
    }

    public void addItemsOnMaterialSpinner(Spinner spinnerToMake)
    {
        List<String> list = new ArrayList<>();
        list.add("Air");
        list.add("Asbestos");
        list.add("Brick");
        list.add("Concrete (high)");
        list.add("Concrete (low)");
        list.add("Copper");
        list.add("Fiber Insulating Board");
        list.add("Glass (plate)");
        list.add("Gypsum Plaster");
        list.add("Oak");
        list.add("PMMA");
        list.add("Polyurethance Foam");
        list.add("Steel (mild)");
        list.add("Yellow Pine");
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, list);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerToMake.setAdapter(dataAdapter);
    }

    public double getThermalDiffusivity(double thermalConductivity, double specificHeat, double density)
    {
        return ((thermalConductivity/1000)/(specificHeat*density));
    }
}
