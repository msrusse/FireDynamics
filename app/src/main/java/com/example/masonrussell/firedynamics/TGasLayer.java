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

public class TGasLayer extends AppCompatActivity {

    public Spinner qSpinner, ambientTempSpinner, resultSpinner, intLiningSpinner, materialSpinner, ventWidthSpinner, ventHeightSpinner, compWidthSpinner, compLengthSpinner, compHeightSpinner;
    public EditText intLiningText, ventWidthText, ventHeightText, compWidthText, compLengthText, compHeightText, qValue, ambientTempValue;
    public double intLining, thermalConductivity, compWidth, compLength, compHeight, ventHeight, ventWidth, qDoub, ambientTempDoub, resultDoub;
    public String intLiningUnits, compWidthUnits, compLengthUnits, compHeightUnits, ventHeightUnits, ventWidthUnits, qUnits, ambientTempUnits, resultUnits;
    public Button calculateButton;
    public LinearLayout resultLayout;
    public TextView resultsView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tgas_layer);
        resultLayout = findViewById(R.id.resultLayout);
        calculateButton = findViewById(R.id.getFlashoverButton);
        compWidthSpinner = findViewById(R.id.compWidthSpinner);
        compWidthText = findViewById(R.id.compWidthText);
        compLengthSpinner = findViewById(R.id.compLengthSpinner);
        compLengthText = findViewById(R.id.compLengthText);
        compHeightSpinner = findViewById(R.id.compHeightSpinner);
        compHeightText = findViewById(R.id.compHeightText);
        ventHeightSpinner = findViewById(R.id.ventHeightSpinner);
        ventHeightText = findViewById(R.id.ventHeightText);
        ventWidthSpinner = findViewById(R.id.ventWidthSpinner);
        ventWidthText = findViewById(R.id.ventWidthText);
        materialSpinner = findViewById(R.id.materialSpinner);
        intLiningText = findViewById(R.id.intLiningText);
        intLiningSpinner = findViewById(R.id.intLiningSpinner);
        qSpinner = findViewById(R.id.qSpinner);
        ambientTempSpinner = findViewById(R.id.ambientTempSpinner);
        resultSpinner = findViewById(R.id.resultSpinner);
        resultsView = findViewById(R.id.resultView);
        qValue = findViewById(R.id.qValue);
        ambientTempValue = findViewById(R.id.ambientTempValue);
        resultLayout.setVisibility(View.INVISIBLE);
        addItemsOnMaterialSpinner(materialSpinner);
        addItemsOnUnitSpinner(intLiningSpinner);
        addItemsOnUnitSpinner(compWidthSpinner);
        addItemsOnUnitSpinner(compLengthSpinner);
        addItemsOnUnitSpinner(compHeightSpinner);
        addItemsOnUnitSpinner(ventHeightSpinner);
        addItemsOnUnitSpinner(ventWidthSpinner);
        addQUnits(qSpinner);
        addItemsOnTempSpinner(ambientTempSpinner);
        addItemsOnResultSpinner(resultSpinner);

        calculateButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                compWidth = Double.parseDouble(compWidthText.getText().toString());
                compHeight = Double.parseDouble(compHeightText.getText().toString());
                compLength = Double.parseDouble(compLengthText.getText().toString());
                ventHeight = Double.parseDouble(ventHeightText.getText().toString());
                ventWidth = Double.parseDouble(ventWidthText.getText().toString());
                intLining = Double.parseDouble(intLiningText.getText().toString());
                qDoub = Double.parseDouble(qValue.getText().toString());
                ambientTempDoub = Double.parseDouble(ambientTempValue.getText().toString());
                compWidthUnits = compWidthSpinner.getSelectedItem().toString();
                compHeightUnits = compHeightSpinner.getSelectedItem().toString();
                compLengthUnits = compLengthSpinner.getSelectedItem().toString();
                ventHeightUnits = ventHeightSpinner.getSelectedItem().toString();
                ventWidthUnits = ventWidthSpinner.getSelectedItem().toString();
                intLiningUnits = intLiningSpinner.getSelectedItem().toString();
                qUnits = qSpinner.getSelectedItem().toString();
                ambientTempUnits = ambientTempSpinner.getSelectedItem().toString();
                compWidth = ValuesConverstions.toMeters(compWidth, compWidthUnits);
                compHeight = ValuesConverstions.toMeters(compHeight, compHeightUnits);
                compLength = ValuesConverstions.toMeters(compLength, compLengthUnits);
                ventHeight = ValuesConverstions.toMeters(ventHeight, ventHeightUnits);
                ventWidth = ValuesConverstions.toMeters(ventWidth, ventHeightUnits);
                intLining = ValuesConverstions.toMeters(intLining, intLiningUnits);
                qDoub = ValuesConverstions.tGasLayerEnergyToKW(qDoub, qUnits);
                ambientTempDoub = ValuesConverstions.toDegreesKelvin(ambientTempDoub, ambientTempUnits);
                thermalConductivity = ValuesConverstions.thermalConductivity(materialSpinner.getSelectedItem().toString());
                double hkdoub = Calculations.Calculatehk(thermalConductivity, intLining);
                double aoDoub = ventWidth * ventHeight;
                double atdoub = Calculations.CalculateTGasLayerAT(compWidth, compLength, compHeight, aoDoub);
                resultDoub = Calculations.CalculateTempOfUpperGasLayerAccordingtoMQH(qDoub, ambientTempDoub, hkdoub, aoDoub, atdoub, ventHeight);
                resultsView.setText(String.valueOf(Math.round(resultDoub)));
                resultUnits = resultSpinner.getSelectedItem().toString();
                resultLayout.setVisibility(View.VISIBLE);
                resultSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        switch (resultSpinner.getSelectedItem().toString())
                        {
                            case "K":
                                resultDoub = ValuesConverstions.toDegreesKelvin(resultDoub, resultUnits);
                                resultsView.setText(String.valueOf(Math.round(resultDoub)));
                                resultUnits = resultSpinner.getSelectedItem().toString();
                                break;
                            case "C":
                                final DecimalFormat oneDigit = new DecimalFormat("0.0");
                                resultDoub = ValuesConverstions.toDegreesCentigrade(resultDoub, resultUnits);
                                resultsView.setText(oneDigit.format(resultDoub));
                                resultUnits = resultSpinner.getSelectedItem().toString();
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

    public void addItemsOnMaterialSpinner(Spinner spinnerToMake)
    {
        List<String> list = new ArrayList<>();
        list.add("Aerated Concrete");
        list.add("Alumina Silicate Block");
        list.add("Aluminum (pure)");
        list.add("Brick");
        list.add("Brick/Concrete Block");
        list.add("Calcium Silicate Board");
        list.add("Chipboard");
        list.add("Concrete");
        list.add("Expended Polystyrene");
        list.add("Fiber Insulation Board");
        list.add("Glass Fiber Insulation");
        list.add("Glass Plate");
        list.add("Gypsum Board");
        list.add("Plasterboard");
        list.add("Plywood");
        list.add("Steel (0.5% Carbon)");
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, list);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerToMake.setAdapter(dataAdapter);
    }

    public void addQUnits(Spinner spinnerToMake)
    {
        List<String> list = new ArrayList<>();
        list.add("kW");
        list.add("Btu/sec");
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, list);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerToMake.setAdapter(dataAdapter);
    }

    public void addItemsOnTempSpinner(Spinner spinnerToMake)
    {
        List<String> list = new ArrayList<>();
        list.add("K");
        list.add("F");
        list.add("C");
        list.add("R");
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, list);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerToMake.setAdapter(dataAdapter);
    }

    public void addItemsOnResultSpinner(Spinner spinnerToMake)
    {
        List<String> list = new ArrayList<>();
        list.add("K");
        list.add("C");
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, list);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerToMake.setAdapter(dataAdapter);
    }
}