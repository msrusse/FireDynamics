package com.example.masonrussell.firedynamics;

import android.provider.Telephony;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class HRR extends AppCompatActivity {

    public Button getMeasurementsButton;
    public EditText areaBurningText;
    public TextView qResult;
    public Spinner typeSelectionSpinner, typeUnitSpinner, fuelSpinner, qSpinner;
    public double maxBurningFlux, heatCombustion, areaDoub, radiusDoub;
    public String typeSelection, typeUnits;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hrr);
        getMeasurementsButton = findViewById(R.id.getMeasurementsButton);
        areaBurningText = findViewById(R.id.areaText);
        typeSelectionSpinner = findViewById(R.id.typeSelectionSpinner);
        typeUnitSpinner = findViewById(R.id.typeUnitSpinner);
        qSpinner = findViewById(R.id.qSpinner);
        qResult = findViewById(R.id.qResult);
        getMeasurementsButton = findViewById(R.id.getMeasurementsButton);
        fuelSpinner = findViewById(R.id.fuelSpinner);
        addItemsOnFuelSpinner(fuelSpinner);
        addSelectionsOnSpinner(typeSelectionSpinner);

        typeSelectionSpinner.setOnItemSelectedListener(
                new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        if (typeSelectionSpinner.getSelectedItem().toString().equals("Area of Burning"))
                        {
                            addItemsOnUnitSquaredSpinner(typeUnitSpinner);
                        }
                        else
                        {
                            addItemsOnUnitSpinner(typeUnitSpinner);
                        }
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                }
        );

        getMeasurementsButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                maxBurningFlux = ValuesConverstions.FuelBurningFlux(fuelSpinner.getSelectedItem().toString());
                heatCombustion = ValuesConverstions.FuelHeatCombustion(fuelSpinner.getSelectedItem().toString());
                typeSelection = typeSelectionSpinner.getSelectedItem().toString();
                typeUnits = typeUnitSpinner.getSelectedItem().toString();
                if (typeSelection.equals("Area of Burning"))
                {
                    areaDoub = Double.parseDouble(areaBurningText.getText().toString());
                    areaDoub = ValuesConverstions.toMeters(areaDoub, typeUnits);
                }
                else if (typeSelection.equals("Radius"))
                {
                    radiusDoub = Double.parseDouble(areaBurningText.getText().toString());
                    radiusDoub = ValuesConverstions.toSquareMeters(radiusDoub, typeUnits);
                    areaDoub = Calculations.CalculateArea(radiusDoub);
                }
                qResult.setText(String.valueOf(Calculations.CalculateHRRQ(maxBurningFlux, heatCombustion, areaDoub)));
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

    public void addItemsOnUnitSquaredSpinner(Spinner spinnerToMake)
    {
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

    public void addItemsOnFuelSpinner(Spinner spinnerToMake)
    {
        List<String> list = new ArrayList<>();
        list.add("Cellulose");
        list.add("Gasoline");
        list.add("Heptane");
        list.add("Methanol");
        list.add("PMMA");
        list.add("Polyethylene");
        list.add("Polypropylene");
        list.add("Polystyrene");
        list.add("PVC");
        list.add("Wood");
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, list);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerToMake.setAdapter(dataAdapter);
    }

    public void addSelectionsOnSpinner(Spinner spinnerToMake)
    {
        List<String> list = new ArrayList<>();
        list.add("Area of Burning");
        list.add("Radius");
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, list);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerToMake.setAdapter(dataAdapter);

    }
}
