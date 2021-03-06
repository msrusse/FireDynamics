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
import android.widget.NumberPicker;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class Conduction extends AppCompatActivity {

    public Spinner materialSpinner, lengthUnitSpinner, hotSideUnitSpinner, coldSideUnitSpinner, heatFluxResultSpinner, thermalPenTimeResultSpinner;
    public List<String> thermalPenTimeList, heatFluxResultList, materialList;
    public LinearLayout resultLayout;
    public EditText lengthResult, hotSideResult, coldSideResult;
    private TextView heatFluxResult, thermalPenTimeResult;
    private Button getResults;
    private String materialString, currentHeatFluxUnits, currentThermalPenTimeUnits;
    private double lengthDoub, hotSideDoub, coldSideDoub, heatFluxDoub, thermalPenTimeDoub, thermalConductivityDoub, specificHeatDoub, densityDoub, thermalDiffusivityDoub, thermalIntertiaDoub;
    private DecimalFormat twoDigits, fourDigits;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conduction);
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        twoDigits = new DecimalFormat("0.00");
        fourDigits = new DecimalFormat("0.0000");
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
        if (ValueClassStorage.conduction != null) setLastCalculation(ValueClassStorage.conduction);
        getResults.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(resultLayout.getWindowToken(), 0);
                    lengthDoub = Double.parseDouble(lengthResult.getText().toString());
                    hotSideDoub = Double.parseDouble(hotSideResult.getText().toString());
                    coldSideDoub = Double.parseDouble(coldSideResult.getText().toString());
                    materialString = materialSpinner.getSelectedItem().toString();
                    getResults();
                }
                catch (Exception ex) {
                    String error = "Please Fill the Empty Fields";
                    Toast.makeText(Conduction.this, error, Toast.LENGTH_LONG).show();
                }
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

    private void setLastCalculation(ValueClassStorage.Conduction conduction)
    {
        lengthResult.setText(String.valueOf(conduction.lengthDoub));
        lengthDoub = conduction.lengthDoub;
        lengthUnitSpinner.setSelection(2);
        hotSideResult.setText(String.valueOf(conduction.hotSideDoub));
        hotSideDoub = conduction.hotSideDoub;
        coldSideResult.setText(String.valueOf(conduction.coldSideDoub));
        coldSideDoub = conduction.coldSideDoub;
        materialSpinner.setSelection(materialList.indexOf(conduction.materialSelected));
        materialString = conduction.materialSelected;
        getResults();
    }

    private void getResults()
    {
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
        ValueClassStorage.Conduction conduction = new ValueClassStorage().new Conduction(lengthDoub, hotSideDoub, coldSideDoub, heatFluxDoub, thermalPenTimeDoub, materialSpinner.getSelectedItem().toString());
        ValueClassStorage.conduction = conduction;
        resultLayout.setVisibility(View.VISIBLE);
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
        thermalPenTimeList = new ArrayList<>();
        thermalPenTimeList.add("sec");
        thermalPenTimeList.add("min");
        thermalPenTimeList.add("hour");
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, thermalPenTimeList);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerToMake.setAdapter(dataAdapter);
    }

    public void addItemsOnHeatFluxResultsSpinner(Spinner spinnerToMake)
    {
        heatFluxResultList = new ArrayList<>();
        heatFluxResultList.add("kW/m^2");
        heatFluxResultList.add("Btu/sec/ft^2");
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, heatFluxResultList);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerToMake.setAdapter(dataAdapter);
    }

    public void addItemsOnMaterialSpinner(Spinner spinnerToMake)
    {
        materialList = new ArrayList<>();
        materialList.add("Air");
        materialList.add("Asbestos");
        materialList.add("Brick");
        materialList.add("Concrete (high)");
        materialList.add("Concrete (low)");
        materialList.add("Copper");
        materialList.add("Fiber Insulating Board");
        materialList.add("Glass (plate)");
        materialList.add("Gypsum Plaster");
        materialList.add("Oak");
        materialList.add("PMMA");
        materialList.add("Polyurethance Foam");
        materialList.add("Steel (mild)");
        materialList.add("Yellow Pine");
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, materialList);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerToMake.setAdapter(dataAdapter);
    }

    public double getThermalDiffusivity(double thermalConductivity, double specificHeat, double density)
    {
        return ((thermalConductivity/1000)/(specificHeat*density));
    }
}
