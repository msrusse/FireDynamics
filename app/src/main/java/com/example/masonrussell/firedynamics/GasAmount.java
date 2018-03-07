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
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class GasAmount extends AppCompatActivity {

    Spinner gasSelectionSpinner, areaUnitSpinner, heightUnitSpinner, gasVolumeResultSpinner, weightResultSpinner, liquidVolumeResultSpinner;
    EditText areaValue, heightValue, lelValue, stoichValue, uelValue, vaporDensityValue, liquidDensityValue;
    TextView lelGasVolumeResult, lelWeightResult, lelLiquidVolumeResult, stoichGasVolumeResult, stoichWeightResult, stoichLiquidVolumeResult, uelGasVolumeResult, uelWeightVolumeResult, uelLiquidVolumeResult;
    Double areaDoub, heightDoub, volumeDoub, lelDoub, stoichDoub, uelDoub, vaporDensityDoub, liquidDensityDoub, lelGasVolumeDoub, lelWeightDoub, lelLiquidVolumeDoub, stoichGasVolumeDoub, stoichWeightDoub, stoichLiquidVolumeDoub, uelGasVolumeDoub, uelWeightDoub, uelLiquidVolumeDoub;
    String areaUnits, heightUnits, gasVolumeUnits, gasWeightUnits, liquidVolumeUnits, gasSelected;
    Button getResultsButton;
    TableLayout resultsTable;
    LinearLayout lelLayout, stoichLayout, uelLayout, vaporDensityLayout, liquidDensityLayout;
    DecimalFormat fourDigits = new DecimalFormat("0.00");
    ViewGroup.LayoutParams lelLayoutParams, stoichLayoutParams, uelLayoutParams, vaporDensityLayoutParams, liquidDensityLayourParams;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gas_amount);
        gasSelectionSpinner = findViewById(R.id.typeOfGasSpinner);
        areaUnitSpinner = findViewById(R.id.areaSpinner);
        heightUnitSpinner = findViewById(R.id.heightSpinner);
        gasVolumeResultSpinner = findViewById(R.id.gasVolumeUnitSpinner);
        weightResultSpinner = findViewById(R.id.gasWeightUnitSpinner);
        liquidVolumeResultSpinner = findViewById(R.id.uelLiquidVolumeUnitSpinner);
        areaValue = findViewById(R.id.areaValue);
        heightValue = findViewById(R.id.heightValue);
        lelValue = findViewById(R.id.lelValue);
        stoichValue = findViewById(R.id.stoichValue);
        uelValue = findViewById(R.id.uelValue);
        vaporDensityValue = findViewById(R.id.vaporDensityValue);
        liquidDensityValue = findViewById(R.id.liquidDensityValue);
        lelGasVolumeResult = findViewById(R.id.lelGasVolumeResult);
        lelWeightResult = findViewById(R.id.lelGasWeightResult);
        lelLiquidVolumeResult = findViewById(R.id.lelLiquidVolumeResult);
        stoichGasVolumeResult = findViewById(R.id.stoichGasVolumeResult);
        stoichWeightResult = findViewById(R.id.stoichGasWeightResult);
        stoichLiquidVolumeResult = findViewById(R.id.stoichLiquidVolumeResult);
        uelGasVolumeResult = findViewById(R.id.uelGasVolumeResult);
        uelWeightVolumeResult = findViewById(R.id.uelGasWeightResult);
        uelLiquidVolumeResult = findViewById(R.id.uelLiquidVolumeResult);
        lelLayout = findViewById(R.id.lelLayout);
        stoichLayout = findViewById(R.id.stoichLayout);
        uelLayout = findViewById(R.id.uelLayout);
        vaporDensityLayout = findViewById(R.id.vaporDensityLayout);
        liquidDensityLayout = findViewById(R.id.liquidDensityLayout);
        resultsTable = findViewById(R.id.resultsTable);
        lelLayoutParams = lelLayout.getLayoutParams();
        stoichLayoutParams = stoichLayout.getLayoutParams();
        uelLayoutParams = uelLayout.getLayoutParams();
        vaporDensityLayoutParams = vaporDensityLayout.getLayoutParams();
        liquidDensityLayourParams = liquidDensityLayout.getLayoutParams();
        getResultsButton = findViewById(R.id.getMeasurementsButton);
        resultsTable.setVisibility(View.INVISIBLE);
        addSquaredUnitsOnSpinner(areaUnitSpinner);
        addUnitsOnSpinner(heightUnitSpinner);
        addUnitsOnVolumeSpinner(gasVolumeResultSpinner);
        addUnitsOnVolumeSpinner(liquidVolumeResultSpinner);
        addUnitsOnWeightSpinner(weightResultSpinner);
        addOptionsOnGasTypeSpinner(gasSelectionSpinner);

        gasSelectionSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                gasSelected = gasSelectionSpinner.getSelectedItem().toString();
                switch (gasSelected) {
                    case "Propane":
                        lelLayoutParams.height = 0;
                        uelLayoutParams.height = 0;
                        stoichLayoutParams.height = 0;
                        vaporDensityLayoutParams.height = 0;
                        liquidDensityLayourParams.height = 0;
                        lelLayout.setLayoutParams(lelLayoutParams);
                        uelLayout.setLayoutParams(uelLayoutParams);
                        stoichLayout.setLayoutParams(stoichLayoutParams);
                        vaporDensityLayout.setLayoutParams(vaporDensityLayoutParams);
                        liquidDensityLayout.setLayoutParams(liquidDensityLayourParams);
                        break;
                    case "Methane":
                        lelLayoutParams.height = 0;
                        uelLayoutParams.height = 0;
                        stoichLayoutParams.height = 0;
                        vaporDensityLayoutParams.height = 0;
                        liquidDensityLayourParams.height = 0;
                        lelLayout.setLayoutParams(lelLayoutParams);
                        uelLayout.setLayoutParams(uelLayoutParams);
                        stoichLayout.setLayoutParams(stoichLayoutParams);
                        vaporDensityLayout.setLayoutParams(vaporDensityLayoutParams);
                        liquidDensityLayout.setLayoutParams(liquidDensityLayourParams);
                        break;
                    case "Enter Manually":
                        lelLayoutParams.height = LinearLayout.LayoutParams.WRAP_CONTENT;
                        uelLayoutParams.height = LinearLayout.LayoutParams.WRAP_CONTENT;
                        stoichLayoutParams.height = LinearLayout.LayoutParams.WRAP_CONTENT;
                        vaporDensityLayoutParams.height = LinearLayout.LayoutParams.WRAP_CONTENT;
                        liquidDensityLayourParams.height = LinearLayout.LayoutParams.WRAP_CONTENT;
                        lelLayout.setLayoutParams(lelLayoutParams);
                        uelLayout.setLayoutParams(uelLayoutParams);
                        stoichLayout.setLayoutParams(stoichLayoutParams);
                        vaporDensityLayout.setLayoutParams(vaporDensityLayoutParams);
                        liquidDensityLayout.setLayoutParams(liquidDensityLayourParams);
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
                    areaDoub = Double.parseDouble(areaValue.getText().toString());
                    heightDoub = Double.parseDouble(heightValue.getText().toString());
                    areaUnits = areaUnitSpinner.getSelectedItem().toString();
                    heightUnits = heightUnitSpinner.getSelectedItem().toString();
                    areaDoub = ValuesConverstions.toSquareMeters(areaDoub, areaUnits);
                    heightDoub = ValuesConverstions.toMeters(heightDoub, heightUnits);
                    volumeDoub = areaDoub * heightDoub;
                    gasVolumeUnits = gasVolumeResultSpinner.getSelectedItem().toString();
                    gasWeightUnits = weightResultSpinner.getSelectedItem().toString();
                    liquidVolumeUnits = liquidVolumeResultSpinner.getSelectedItem().toString();

                    if (gasSelected.equals("Propane") || gasSelected.equals("Methane")) {
                        lelDoub = ValuesConverstions.getGasLelValue(gasSelected);
                        stoichDoub = ValuesConverstions.getGasStoichiometricValue(gasSelected);
                        uelDoub = ValuesConverstions.getGasUelValue(gasSelected);
                        vaporDensityDoub = ValuesConverstions.getGasVaporDensityValue(gasSelected);
                        liquidDensityDoub = ValuesConverstions.getGasLiquidDensityValue(gasSelected);
                        lelGasVolumeDoub = lelDoub * volumeDoub;
                        lelWeightDoub = lelGasVolumeDoub * vaporDensityDoub;
                        lelLiquidVolumeDoub = lelWeightDoub / liquidDensityDoub;
                        stoichGasVolumeDoub = stoichDoub * volumeDoub;
                        stoichWeightDoub = stoichGasVolumeDoub * vaporDensityDoub;
                        stoichLiquidVolumeDoub = stoichWeightDoub / liquidDensityDoub;
                        uelGasVolumeDoub = uelDoub * volumeDoub;
                        uelWeightDoub = uelGasVolumeDoub * vaporDensityDoub;
                        uelLiquidVolumeDoub = uelWeightDoub / liquidDensityDoub;
                        if (liquidDensityDoub == -1) {
                            lelGasVolumeResult.setText(fourDigits.format(lelGasVolumeDoub));
                            lelWeightResult.setText(fourDigits.format(lelWeightDoub));
                            lelLiquidVolumeResult.setText("N/A");
                            stoichGasVolumeResult.setText(fourDigits.format(stoichGasVolumeDoub));
                            stoichWeightResult.setText(fourDigits.format(stoichWeightDoub));
                            stoichLiquidVolumeResult.setText("N/A");
                            uelGasVolumeResult.setText(fourDigits.format(uelGasVolumeDoub));
                            uelWeightVolumeResult.setText(fourDigits.format(uelWeightDoub));
                            uelLiquidVolumeResult.setText("N/A");
                        } else {

                            lelGasVolumeResult.setText(fourDigits.format(lelGasVolumeDoub));
                            lelWeightResult.setText(fourDigits.format(lelWeightDoub));
                            lelLiquidVolumeResult.setText(fourDigits.format(lelLiquidVolumeDoub));
                            stoichGasVolumeResult.setText(fourDigits.format(stoichGasVolumeDoub));
                            stoichWeightResult.setText(fourDigits.format(stoichWeightDoub));
                            stoichLiquidVolumeResult.setText(fourDigits.format(stoichLiquidVolumeDoub));
                            uelGasVolumeResult.setText(fourDigits.format(uelGasVolumeDoub));
                            uelWeightVolumeResult.setText(fourDigits.format(uelWeightDoub));
                            uelLiquidVolumeResult.setText(fourDigits.format(uelLiquidVolumeDoub));
                        }
                        resultsTable.setVisibility(View.VISIBLE);
                    } else {
                        try {
                            lelDoub = Double.parseDouble(lelValue.getText().toString()) / 100;
                            stoichDoub = Double.parseDouble(stoichValue.getText().toString()) / 100;
                            uelDoub = Double.parseDouble(uelValue.getText().toString()) / 100;
                            vaporDensityDoub = Double.parseDouble(vaporDensityValue.getText().toString());
                        } catch (Exception ex) {
                            Toast.makeText(GasAmount.this, ex.toString(), Toast.LENGTH_LONG).show();
                        }
                        try {
                            liquidDensityDoub = Double.parseDouble(liquidDensityValue.getText().toString());
                        } catch (Exception ex) {
                        }
                        lelGasVolumeDoub = lelDoub * volumeDoub;
                        lelWeightDoub = lelGasVolumeDoub * vaporDensityDoub;
                        stoichGasVolumeDoub = stoichDoub * volumeDoub;
                        stoichWeightDoub = stoichGasVolumeDoub * vaporDensityDoub;
                        uelGasVolumeDoub = uelDoub * volumeDoub;
                        uelWeightDoub = uelGasVolumeDoub * vaporDensityDoub;

                        if (liquidDensityDoub == null || liquidDensityDoub == -1) {
                            lelGasVolumeResult.setText(fourDigits.format(lelGasVolumeDoub));
                            lelWeightResult.setText(fourDigits.format(lelWeightDoub));
                            lelLiquidVolumeResult.setText("N/A");
                            stoichGasVolumeResult.setText(fourDigits.format(stoichGasVolumeDoub));
                            stoichWeightResult.setText(fourDigits.format(stoichWeightDoub));
                            stoichLiquidVolumeResult.setText("N/A");
                            uelGasVolumeResult.setText(fourDigits.format(uelGasVolumeDoub));
                            uelWeightVolumeResult.setText(fourDigits.format(uelWeightDoub));
                            uelLiquidVolumeResult.setText("N/A");
                        } else {
                            lelLiquidVolumeDoub = lelWeightDoub / liquidDensityDoub;
                            stoichLiquidVolumeDoub = stoichWeightDoub / liquidDensityDoub;
                            uelLiquidVolumeDoub = uelWeightDoub / liquidDensityDoub;
                            lelGasVolumeResult.setText(fourDigits.format(lelGasVolumeDoub));
                            lelWeightResult.setText(fourDigits.format(lelWeightDoub));
                            lelLiquidVolumeResult.setText(fourDigits.format(lelLiquidVolumeDoub));
                            stoichGasVolumeResult.setText(fourDigits.format(stoichGasVolumeDoub));
                            stoichWeightResult.setText(fourDigits.format(stoichWeightDoub));
                            stoichLiquidVolumeResult.setText(fourDigits.format(stoichLiquidVolumeDoub));
                            uelGasVolumeResult.setText(fourDigits.format(uelGasVolumeDoub));
                            uelWeightVolumeResult.setText(fourDigits.format(uelWeightDoub));
                            uelLiquidVolumeResult.setText(fourDigits.format(uelLiquidVolumeDoub));
                        }
                        resultsTable.setVisibility(View.VISIBLE);
                    }
                } catch (Exception ex) {
                    String error = "Please Fill the Empty Fields";
                    Toast.makeText(GasAmount.this, error, Toast.LENGTH_LONG).show();
                }
                gasVolumeResultSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        switch (gasVolumeResultSpinner.getSelectedItem().toString())
                        {
                            case "m^3":
                                lelGasVolumeDoub = ValuesConverstions.toCubicMeters(lelGasVolumeDoub, gasVolumeUnits);
                                stoichGasVolumeDoub = ValuesConverstions.toCubicMeters(stoichGasVolumeDoub, gasVolumeUnits);
                                uelGasVolumeDoub = ValuesConverstions.toCubicMeters(uelGasVolumeDoub, gasVolumeUnits);
                                lelGasVolumeResult.setText(fourDigits.format(lelGasVolumeDoub));
                                stoichGasVolumeResult.setText(fourDigits.format(stoichGasVolumeDoub));
                                uelGasVolumeResult.setText(fourDigits.format(uelGasVolumeDoub));
                                gasVolumeUnits = gasVolumeResultSpinner.getSelectedItem().toString();
                                break;
                            case "ft^3":
                                lelGasVolumeDoub = ValuesConverstions.volumeToCubicFeet(lelGasVolumeDoub, gasVolumeUnits);
                                stoichGasVolumeDoub = ValuesConverstions.volumeToCubicFeet(stoichGasVolumeDoub, gasVolumeUnits);
                                uelGasVolumeDoub = ValuesConverstions.volumeToCubicFeet(uelGasVolumeDoub, gasVolumeUnits);
                                lelGasVolumeResult.setText(fourDigits.format(lelGasVolumeDoub));
                                stoichGasVolumeResult.setText(fourDigits.format(stoichGasVolumeDoub));
                                uelGasVolumeResult.setText(fourDigits.format(uelGasVolumeDoub));
                                gasVolumeUnits = gasVolumeResultSpinner.getSelectedItem().toString();
                                break;
                            case "in^3":
                                lelGasVolumeDoub = ValuesConverstions.volumeToCubicInches(lelGasVolumeDoub, gasVolumeUnits);
                                stoichGasVolumeDoub = ValuesConverstions.volumeToCubicInches(stoichGasVolumeDoub, gasVolumeUnits);
                                uelGasVolumeDoub = ValuesConverstions.volumeToCubicInches(uelGasVolumeDoub, gasVolumeUnits);
                                lelGasVolumeResult.setText(fourDigits.format(lelGasVolumeDoub));
                                stoichGasVolumeResult.setText(fourDigits.format(stoichGasVolumeDoub));
                                uelGasVolumeResult.setText(fourDigits.format(uelGasVolumeDoub));
                                gasVolumeUnits = gasVolumeResultSpinner.getSelectedItem().toString();
                                break;
                            case "liters":
                                lelGasVolumeDoub = ValuesConverstions.volumeToLiters(lelGasVolumeDoub, gasVolumeUnits);
                                stoichGasVolumeDoub = ValuesConverstions.volumeToLiters(stoichGasVolumeDoub, gasVolumeUnits);
                                uelGasVolumeDoub = ValuesConverstions.volumeToLiters(uelGasVolumeDoub, gasVolumeUnits);
                                lelGasVolumeResult.setText(fourDigits.format(lelGasVolumeDoub));
                                stoichGasVolumeResult.setText(fourDigits.format(stoichGasVolumeDoub));
                                uelGasVolumeResult.setText(fourDigits.format(uelGasVolumeDoub));
                                gasVolumeUnits = gasVolumeResultSpinner.getSelectedItem().toString();
                                break;
                            case "gallons":
                                lelGasVolumeDoub = ValuesConverstions.volumeToUSGallons(lelGasVolumeDoub, gasVolumeUnits);
                                stoichGasVolumeDoub = ValuesConverstions.volumeToUSGallons(stoichGasVolumeDoub, gasVolumeUnits);
                                uelGasVolumeDoub = ValuesConverstions.volumeToUSGallons(uelGasVolumeDoub, gasVolumeUnits);
                                lelGasVolumeResult.setText(fourDigits.format(lelGasVolumeDoub));
                                stoichGasVolumeResult.setText(fourDigits.format(stoichGasVolumeDoub));
                                uelGasVolumeResult.setText(fourDigits.format(uelGasVolumeDoub));
                                gasVolumeUnits = gasVolumeResultSpinner.getSelectedItem().toString();
                                break;
                        }
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });
                weightResultSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        switch (weightResultSpinner.getSelectedItem().toString())
                        {
                            case "kg":
                                lelWeightDoub = ValuesConverstions.massToKilograms(lelWeightDoub, gasWeightUnits);
                                stoichWeightDoub = ValuesConverstions.massToKilograms(stoichWeightDoub, gasWeightUnits);
                                uelWeightDoub = ValuesConverstions.massToKilograms(uelWeightDoub, gasWeightUnits);
                                gasWeightUnits = weightResultSpinner.getSelectedItem().toString();
                                lelWeightResult.setText(fourDigits.format(lelWeightDoub));
                                stoichWeightResult.setText(fourDigits.format(stoichWeightDoub));
                                uelWeightVolumeResult.setText(fourDigits.format(uelWeightDoub));
                                break;
                            case "g":
                                lelWeightDoub = ValuesConverstions.massToGrams(lelWeightDoub, gasWeightUnits);
                                stoichWeightDoub = ValuesConverstions.massToGrams(stoichWeightDoub, gasWeightUnits);
                                uelWeightDoub = ValuesConverstions.massToGrams(uelWeightDoub, gasWeightUnits);
                                gasWeightUnits = weightResultSpinner.getSelectedItem().toString();
                                lelWeightResult.setText(fourDigits.format(lelWeightDoub));
                                stoichWeightResult.setText(fourDigits.format(stoichWeightDoub));
                                uelWeightVolumeResult.setText(fourDigits.format(uelWeightDoub));
                                break;
                            case "lb":
                                lelWeightDoub = ValuesConverstions.massToPounds(lelWeightDoub, gasWeightUnits);
                                stoichWeightDoub = ValuesConverstions.massToPounds(stoichWeightDoub, gasWeightUnits);
                                uelWeightDoub = ValuesConverstions.massToPounds(uelWeightDoub, gasWeightUnits);
                                gasWeightUnits = weightResultSpinner.getSelectedItem().toString();
                                lelWeightResult.setText(fourDigits.format(lelWeightDoub));
                                stoichWeightResult.setText(fourDigits.format(stoichWeightDoub));
                                uelWeightVolumeResult.setText(fourDigits.format(uelWeightDoub));
                        }
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });
                liquidVolumeResultSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        switch (liquidVolumeResultSpinner.getSelectedItem().toString())
                        {
                            case "m^3":
                                lelLiquidVolumeDoub = ValuesConverstions.toCubicMeters(lelLiquidVolumeDoub, liquidVolumeUnits);
                                stoichLiquidVolumeDoub = ValuesConverstions.toCubicMeters(stoichLiquidVolumeDoub, liquidVolumeUnits);
                                uelLiquidVolumeDoub = ValuesConverstions.toCubicMeters(uelLiquidVolumeDoub, liquidVolumeUnits);
                                lelLiquidVolumeResult.setText(fourDigits.format(lelLiquidVolumeDoub));
                                stoichLiquidVolumeResult.setText(fourDigits.format(stoichLiquidVolumeDoub));
                                uelLiquidVolumeResult.setText(fourDigits.format(uelLiquidVolumeDoub));
                                gasVolumeUnits = gasVolumeResultSpinner.getSelectedItem().toString();
                                break;
                            case "ft^3":
                                lelLiquidVolumeDoub = ValuesConverstions.volumeToCubicFeet(lelLiquidVolumeDoub, liquidVolumeUnits);
                                stoichLiquidVolumeDoub = ValuesConverstions.volumeToCubicFeet(stoichLiquidVolumeDoub, liquidVolumeUnits);
                                uelLiquidVolumeDoub = ValuesConverstions.volumeToCubicFeet(uelLiquidVolumeDoub, liquidVolumeUnits);
                                lelLiquidVolumeResult.setText(fourDigits.format(lelLiquidVolumeDoub));
                                stoichLiquidVolumeResult.setText(fourDigits.format(stoichLiquidVolumeDoub));
                                uelLiquidVolumeResult.setText(fourDigits.format(uelLiquidVolumeDoub));
                                gasVolumeUnits = gasVolumeResultSpinner.getSelectedItem().toString();
                                break;
                            case "in^3":
                                lelLiquidVolumeDoub = ValuesConverstions.volumeToCubicInches(lelLiquidVolumeDoub, liquidVolumeUnits);
                                stoichLiquidVolumeDoub = ValuesConverstions.volumeToCubicInches(stoichLiquidVolumeDoub, liquidVolumeUnits);
                                uelLiquidVolumeDoub = ValuesConverstions.volumeToCubicInches(uelLiquidVolumeDoub, liquidVolumeUnits);
                                lelLiquidVolumeResult.setText(fourDigits.format(lelLiquidVolumeDoub));
                                stoichLiquidVolumeResult.setText(fourDigits.format(stoichLiquidVolumeDoub));
                                uelLiquidVolumeResult.setText(fourDigits.format(uelLiquidVolumeDoub));
                                gasVolumeUnits = gasVolumeResultSpinner.getSelectedItem().toString();
                                break;
                            case "liters":
                                lelLiquidVolumeDoub = ValuesConverstions.volumeToLiters(lelLiquidVolumeDoub, liquidVolumeUnits);
                                stoichLiquidVolumeDoub = ValuesConverstions.volumeToLiters(stoichLiquidVolumeDoub, liquidVolumeUnits);
                                uelLiquidVolumeDoub = ValuesConverstions.volumeToLiters(uelLiquidVolumeDoub, liquidVolumeUnits);
                                lelLiquidVolumeResult.setText(fourDigits.format(lelLiquidVolumeDoub));
                                stoichLiquidVolumeResult.setText(fourDigits.format(stoichLiquidVolumeDoub));
                                uelLiquidVolumeResult.setText(fourDigits.format(uelLiquidVolumeDoub));
                                gasVolumeUnits = gasVolumeResultSpinner.getSelectedItem().toString();
                                break;
                            case "gallons":
                                lelLiquidVolumeDoub = ValuesConverstions.volumeToUSGallons(lelLiquidVolumeDoub, liquidVolumeUnits);
                                stoichLiquidVolumeDoub = ValuesConverstions.volumeToUSGallons(stoichLiquidVolumeDoub, liquidVolumeUnits);
                                uelLiquidVolumeDoub = ValuesConverstions.volumeToUSGallons(uelLiquidVolumeDoub, liquidVolumeUnits);
                                lelLiquidVolumeResult.setText(fourDigits.format(lelLiquidVolumeDoub));
                                stoichLiquidVolumeResult.setText(fourDigits.format(stoichLiquidVolumeDoub));
                                uelLiquidVolumeResult.setText(fourDigits.format(uelLiquidVolumeDoub));
                                gasVolumeUnits = gasVolumeResultSpinner.getSelectedItem().toString();
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

    public void addUnitsOnSpinner(Spinner spinnerToMake) {
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

    public void addSquaredUnitsOnSpinner(Spinner spinnerToMake) {
        List<String> list = new ArrayList<>();
        list.add("ft^2");
        list.add("in^2");
        list.add("m^2");
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, list);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerToMake.setAdapter(dataAdapter);
    }

    public void addUnitsOnVolumeSpinner(Spinner spinnerToMake) {
        List<String> list = new ArrayList<>();
        list.add("m^3");
        list.add("ft^3");
        list.add("in^3");
        list.add("liters");
        list.add("gallons");
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, list);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerToMake.setAdapter(dataAdapter);
    }

    public void addUnitsOnWeightSpinner(Spinner spinnerToMake) {
        List<String> list = new ArrayList<>();
        list.add("kg");
        list.add("g");
        list.add("lb");
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, list);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerToMake.setAdapter(dataAdapter);
    }

    public void addOptionsOnGasTypeSpinner(Spinner spinnerToMake) {
        List<String> list = new ArrayList<>();
        list.add("Propane");
        list.add("Methane");
        list.add("Enter Manually");
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, list);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerToMake.setAdapter(dataAdapter);
    }
}