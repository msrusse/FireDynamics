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
import android.widget.Toast;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class SolidIgnition extends AppCompatActivity {

    public Spinner calculationSelectionSpinner, densityUnitSpinner, thicknessUnitSpinner, ignitionTempUnitSpinner, ambientTempUnitSpinner, heatFluxUnitSpinner, materialSelectionSpinner;
    public EditText densityValue, specificHeatValue, thicknessValue, ignitionTempValue, ambientTempValue, heatFluxValue, thermalConductivityValue, cValue;
    public LinearLayout resultLayout;
    public TextView resultView;
    public Button getResultsButton;
    public double densityDoub, specificHeatDoub, thicknessDoub, ignitionTempDoub, ambientTempDoub, heatFluxDoub, thermalConductivityDoub, thermalInertiaDoub, criticalIgnitionFluxDoub, cDoub, resultDoub;
    public String densityUnits, thicknessUnits, ignitionTempUnits, ambientTempUnits, heatFluxUnits, materialSelected;
    public final DecimalFormat twoDigits = new DecimalFormat("0.00");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_solid_ignition);
        calculationSelectionSpinner = findViewById(R.id.calculationSelectionSpinner);
        addItemsOnSelectionSpinner(calculationSelectionSpinner);
        calculationSelectionSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (calculationSelectionSpinner.getSelectedItem().toString())
                {
                    case "Thermally Thin Ignition":
                        startThermallyThin();
                        break;
                    case "Thermally Thick Ignition":
                        startThermallyThick();
                        break;
                    case "Thermally Thick Ignition with Materials":
                        startThermallyThickWithMaterial();
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    public void startThermallyThick()
    {
        setContentView(R.layout.activity_solid_ignition_thermally_thick);
        calculationSelectionSpinner = findViewById(R.id.calculationSelectionSpinner);
        addItemsOnSecondSelection(calculationSelectionSpinner);
        getResultsButton = findViewById(R.id.getMeasurementsButton);
        resultLayout = findViewById(R.id.resultLayout);
        resultLayout.setVisibility(View.INVISIBLE);
        densityValue = findViewById(R.id.densityValue);
        densityUnitSpinner = findViewById(R.id.densityUnitSpinner);
        specificHeatValue = findViewById(R.id.specificHeatText);
        thermalConductivityValue = findViewById(R.id.thermalConductivityValue);
        ignitionTempValue = findViewById(R.id.ignitionTempValue);
        ignitionTempUnitSpinner = findViewById(R.id.ignitionTempUnitSpinner);
        ambientTempValue = findViewById(R.id.ambientTempValue);
        ambientTempUnitSpinner = findViewById(R.id.ambientTempUnitSpinner);
        heatFluxValue = findViewById(R.id.heatFluxValue);
        heatFluxUnitSpinner = findViewById(R.id.heatFluxUnitSpinner);
        resultView = findViewById(R.id.resultDisplayView);
        cValue = findViewById(R.id.cValue);
        addItemsOnDensitySpinner(densityUnitSpinner);
        addItemsOnTempSpinner(ignitionTempUnitSpinner);
        addItemsOnTempSpinner(ambientTempUnitSpinner);
        addItemsOnHeatFluxSpinner(heatFluxUnitSpinner);
        calculationSelectionSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (calculationSelectionSpinner.getSelectedItem().toString())
                {
                    case "Thermally Thin Ignition":
                        startThermallyThin();
                        break;
                    case "Thermally Thick Ignition":
                        startThermallyThick();
                        break;
                    case "Thermally Thick Ignition with Materials":
                        startThermallyThickWithMaterial();
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
                    densityDoub = Double.parseDouble(densityValue.getText().toString());
                    densityUnits = densityUnitSpinner.getSelectedItem().toString();
                    specificHeatDoub = Double.parseDouble(specificHeatValue.getText().toString());
                    ignitionTempDoub = Double.parseDouble(ignitionTempValue.getText().toString());
                    ignitionTempUnits = ignitionTempUnitSpinner.getSelectedItem().toString();
                    ambientTempDoub = Double.parseDouble(ambientTempValue.getText().toString());
                    ambientTempUnits = ambientTempUnitSpinner.getSelectedItem().toString();
                    thermalConductivityDoub = Double.parseDouble(thermalConductivityValue.getText().toString());
                    heatFluxDoub = Double.parseDouble(heatFluxValue.getText().toString());
                    heatFluxUnits = heatFluxUnitSpinner.getSelectedItem().toString();
                    cDoub = Double.parseDouble(cValue.getText().toString());
                    resultDoub = Calculations.CalculateThermallyThickTimeToIgnition(cDoub, densityDoub, specificHeatDoub, thermalConductivityDoub, ignitionTempDoub, ambientTempDoub, heatFluxDoub);
                    resultView.setText(twoDigits.format(resultDoub));
                    resultLayout.setVisibility(View.VISIBLE);
                }
                catch (Exception ex) {
                    String error = "Please Fill the Empty Fields";
                    Toast.makeText(SolidIgnition.this, ex.getMessage(), Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    public void startThermallyThin()
    {
        setContentView(R.layout.activity_solid_ignition_thermally_thin);
        calculationSelectionSpinner = findViewById(R.id.calculationSelectionSpinner);
        addItemsOnSecondSelection(calculationSelectionSpinner);
        getResultsButton = findViewById(R.id.getMeasurementsButton);
        resultLayout = findViewById(R.id.resultLayout);
        resultLayout.setVisibility(View.INVISIBLE);
        densityValue = findViewById(R.id.densityValue);
        densityUnitSpinner = findViewById(R.id.densityUnitSpinner);
        thicknessValue = findViewById(R.id.thicknessValue);
        thicknessUnitSpinner = findViewById(R.id.thicknessUnitSpinner);
        specificHeatValue = findViewById(R.id.specificHeatText);
        ignitionTempValue = findViewById(R.id.ignitionTempValue);
        ignitionTempUnitSpinner = findViewById(R.id.ignitionTempUnitSpinner);
        ambientTempValue = findViewById(R.id.ambientTempValue);
        ambientTempUnitSpinner = findViewById(R.id.ambientTempUnitSpinner);
        heatFluxValue = findViewById(R.id.heatFluxValue);
        heatFluxUnitSpinner = findViewById(R.id.heatFluxUnitSpinner);
        resultView = findViewById(R.id.resultDisplayView);
        addItemsOnDensitySpinner(densityUnitSpinner);
        addItemsOnThicknessSpinner(thicknessUnitSpinner);
        addItemsOnTempSpinner(ignitionTempUnitSpinner);
        addItemsOnTempSpinner(ambientTempUnitSpinner);
        addItemsOnHeatFluxSpinner(heatFluxUnitSpinner);
        calculationSelectionSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (calculationSelectionSpinner.getSelectedItem().toString())
                {
                    case "Thermally Thin Ignition":
                        startThermallyThin();
                        break;
                    case "Thermally Thick Ignition":
                        startThermallyThick();
                        calculationSelectionSpinner.setSelection(2);
                        break;
                    case "Thermally Thick Ignition with Materials":
                        startThermallyThickWithMaterial();
                        calculationSelectionSpinner.setSelection(3);
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
                    densityDoub = Double.parseDouble(densityValue.getText().toString());
                    densityUnits = densityUnitSpinner.getSelectedItem().toString();
                    specificHeatDoub = Double.parseDouble(specificHeatValue.getText().toString());
                    ignitionTempDoub = Double.parseDouble(ignitionTempValue.getText().toString());
                    ignitionTempUnits = ignitionTempUnitSpinner.getSelectedItem().toString();
                    thicknessDoub = Double.parseDouble(thicknessValue.getText().toString());
                    thicknessUnits = thicknessUnitSpinner.getSelectedItem().toString();
                    ambientTempDoub = Double.parseDouble(ambientTempValue.getText().toString());
                    ambientTempUnits = ambientTempUnitSpinner.getSelectedItem().toString();
                    heatFluxDoub = Double.parseDouble(heatFluxValue.getText().toString());
                    heatFluxUnits = heatFluxUnitSpinner.getSelectedItem().toString();
                    densityDoub = ValuesConverstions.densityToKilogramsPerMetersCubed(densityDoub, densityUnits);
                    thicknessDoub = ValuesConverstions.toMeters(thicknessDoub, thicknessUnits);
                    ignitionTempDoub = ValuesConverstions.toDegreesCentigrade(ignitionTempDoub, ignitionTempUnits);
                    ambientTempDoub = ValuesConverstions.toDegreesCentigrade(ambientTempDoub, ambientTempUnits);
                    heatFluxDoub = ValuesConverstions.heatFluxToKillowattPerSquaredMeters(heatFluxDoub, heatFluxUnits);
                    resultDoub = Calculations.CalculateThermallyThinTimeToIgnition(densityDoub, specificHeatDoub, thicknessDoub, ignitionTempDoub, ambientTempDoub, heatFluxDoub);
                    resultView.setText(twoDigits.format(resultDoub));
                    resultLayout.setVisibility(View.VISIBLE);
                }
                catch (Exception ex) {
                    String error = "Please Fill the Empty Fields";
                    Toast.makeText(SolidIgnition.this, error, Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    public void startThermallyThickWithMaterial()
    {
        setContentView(R.layout.activity_solid_ignition_thermally_thick_with_materials);
        calculationSelectionSpinner = findViewById(R.id.calculationSelectionSpinner);
        addItemsOnSecondSelection(calculationSelectionSpinner);
        getResultsButton = findViewById(R.id.getMeasurementsButton);
        resultLayout = findViewById(R.id.resultLayout);
        resultLayout.setVisibility(View.INVISIBLE);
        materialSelectionSpinner = findViewById(R.id.materialSelectionSpinner);
        cValue = findViewById(R.id.cValue);
        ambientTempValue = findViewById(R.id.ambientTempValue);
        ambientTempUnitSpinner = findViewById(R.id.ambientTempUnitSpinner);
        heatFluxValue = findViewById(R.id.heatFluxValue);
        heatFluxUnitSpinner = findViewById(R.id.heatFluxUnitSpinner);
        resultView = findViewById(R.id.resultDisplayView);
        addItemsOnTempSpinner(ambientTempUnitSpinner);
        addItemsOnHeatFluxSpinner(heatFluxUnitSpinner);
        addItemsOnMaterialSpinner(materialSelectionSpinner);
        calculationSelectionSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (calculationSelectionSpinner.getSelectedItem().toString())
                {
                    case "Thermally Thin Ignition":
                        startThermallyThin();
                        break;
                    case "Thermally Thick Ignition":
                        startThermallyThick();
                        break;
                    case "Thermally Thick Ignition with Materials":
                        startThermallyThickWithMaterial();
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
                    materialSelected = materialSelectionSpinner.getSelectedItem().toString();
                    ambientTempDoub = Double.parseDouble(ambientTempValue.getText().toString());
                    ambientTempUnits = ambientTempUnitSpinner.getSelectedItem().toString();
                    heatFluxDoub = Double.parseDouble(heatFluxValue.getText().toString());
                    heatFluxUnits = heatFluxUnitSpinner.getSelectedItem().toString();
                    cDoub = Double.parseDouble(cValue.getText().toString());
                    thermalInertiaDoub = ValuesConverstions.getSolidIgnitionKPC(materialSelected);
                    ignitionTempDoub = ValuesConverstions.getSolidIgnitionTig(materialSelected);
                    criticalIgnitionFluxDoub = ValuesConverstions.getSolidIgnitionQCrit(materialSelected);
                    ambientTempDoub = ValuesConverstions.toDegreesCentigrade(ambientTempDoub, ambientTempUnits);
                    heatFluxDoub = ValuesConverstions.heatFluxToKillowattPerSquaredMeters(heatFluxDoub, heatFluxUnits);
                    resultDoub = Calculations.CalculateThermallyThickTimeToIgnitionWithMaterialSelected(thermalInertiaDoub, ignitionTempDoub, criticalIgnitionFluxDoub, cDoub, ambientTempDoub, heatFluxDoub);
                    if (resultDoub == 0) {
                        resultView.setText("Below Critical Flux");
                    } else {
                        resultView.setText(twoDigits.format(resultDoub));
                    }
                    resultLayout.setVisibility(View.VISIBLE);
                }
                catch (Exception ex) {
                    String error = "Please Fill the Empty Fields";
                    Toast.makeText(SolidIgnition.this, error, Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    public void addItemsOnSelectionSpinner(Spinner spinnerToMake)
    {
        List<String> list = new ArrayList<>();
        list.add("Please Make Selection");
        list.add("Thermally Thin Ignition");
        list.add("Thermally Thick Ignition");
        list.add("Thermally Thick Ignition with Materials");
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, list);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerToMake.setAdapter(dataAdapter);
    }

    public void addItemsOnSecondSelection(Spinner spinnerToMake)
    {
        List<String> list = new ArrayList<>();
        list.add("Change Selection");
        list.add("Thermally Thin Ignition");
        list.add("Thermally Thick Ignition");
        list.add("Thermally Thick Ignition with Materials");
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, list);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerToMake.setAdapter(dataAdapter);
    }

    public void addItemsOnDensitySpinner(Spinner spinnerToMake)
    {
        List<String> list = new ArrayList<>();
        list.add("kg/m^3");
        list.add("lb/ft^3");
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, list);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerToMake.setAdapter(dataAdapter);
    }

    public void addItemsOnThicknessSpinner(Spinner spinnerToMake)
    {
        List<String> list = new ArrayList<>();
        list.add("cm");
        list.add("feet");
        list.add("in");
        list.add("m");
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
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, list);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerToMake.setAdapter(dataAdapter);
    }

    public void addItemsOnHeatFluxSpinner(Spinner spinnerToMake)
    {
        List<String> list = new ArrayList<>();
        list.add("kW/m^2");
        list.add("Btu/sec/ft^2");
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, list);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerToMake.setAdapter(dataAdapter);
    }

    public void addItemsOnMaterialSpinner(Spinner spinnerToMake)
    {
        List<String> list = new ArrayList<>();
        list.add("Aircraft Panel, Epoxy Fiberite");
        list.add("Asphalt Shingle");
        list.add("Acrylic Carpet");
        list.add("Nylon/Wool Blend Carpet");
        list.add("Stock Wool Carpet");
        list.add("Treated Wool Carpet");
        list.add("Untreated Wool Carpet");
        list.add("Douglass Fir Particleboard (1.27 cm)");
        list.add("Fiber Insulation Board");
        list.add("Fiberglass Shingle");
        list.add("Flexible Foam (2.54 cm)");
        list.add("Rigid Foam (2.54 cm)");
        list.add("Glass Reinforced Polyester (1.14 mm)");
        list.add("Glass Reinforced Plyester (2.24 mm)");
        list.add("Hardboard (3.175 mm)");
        list.add("Hardboard (6.35 mm)");
        list.add("Gloss Paint Hardboard (3.4 mm)");
        list.add("Nitrocellulose Paint Hardboard");
        list.add("Particleboard");
        list.add("FR Plywood (1.27 cm)");
        list.add("Plain Plywood (0.635 cm)");
        list.add("Plain Plywood (1.27 cm)");
        list.add("PMMA Polycast (1.599 mm)");
        list.add("PMMA Type G (1.27 cm)");
        list.add("Polycarbonate (1.52 mm)");
        list.add("Polyisocyanurate");
        list.add("Polystyrene (5.08 cm)");
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, list);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerToMake.setAdapter(dataAdapter);
    }
}

