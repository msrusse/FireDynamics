package com.example.masonrussell.firedynamics;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class GasAmount extends AppCompatActivity {

    Spinner gasSelectionSpinner, areaUnitSpinner, heightUnitSpinner, gasVolumeResultSpinner, weightResultSpinner, liquidVolumeResultSpinner;
    EditText areaValue, heightValue, lelValue, stoichValue, uelValue, vaporDensityValue, liquidDensityValue;
    TextView lelGasVolumeResult, lelWeightResult, lelLiquidVolumeResult, stoichGasVolumeResult, stoichWeightResult, stoichLiquidVolumeResult, uelGasVolumeResult, uelWeightVolumeResult, uelLiquidVolumeResult;
    double areaDoub, heightDoub, volumeDoub, lelDoub, stoichDoub, uelDoub, lelGasVolumeDoub, lelWeightDoub, lelLiquidVolumeDoub, stoichGasVolumeDoub, stoichWeightDoub, stoichLiquidVolumeDoub, uelGasVolumeDoub, uelWeightDoub, uelLiquidVolumeDoub;
    String areaUnits, heightUnits, gasVolumeUnits, gasWeightUnits, liquidVolumeUnits;
    Button getResultsButton;
    TableLayout resultsTable;
    LinearLayout lelLayout, stoichLayout, uelLayout, vaporDensityLayout, liquidDensityLayout;
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
        //resultsTable.setVisibility(View.INVISIBLE);
        addSquaredUnitsOnSpinner(areaUnitSpinner);
        addUnitsOnSpinner(heightUnitSpinner);
        addUnitsOnVolumeSpinner(gasVolumeResultSpinner);
        addUnitsOnVolumeSpinner(liquidVolumeResultSpinner);
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
        list.add("cm^2");
        list.add("mm^2");
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
        list.add("lb");
        list.add("g");
        list.add("kg");
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, list);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerToMake.setAdapter(dataAdapter);
    }
}
