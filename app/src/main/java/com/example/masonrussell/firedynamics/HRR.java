package com.example.masonrussell.firedynamics;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

public class HRR extends AppCompatActivity {

    public Button getMeasurementsButton;
    public EditText areaBurningText, circleRadiusText;
    public Spinner areaBurningSpinner, circleRadiusSpinner, fuelSpinner;
    public double maxBurningFlux, heatCombustion, areaDoub;
    public String hrrFuel, areaUnits, radiusUnits;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hrr);
        getMeasurementsButton = findViewById(R.id.getMeasurementsButton);
        areaBurningText = findViewById(R.id.areaText);
        circleRadiusText = findViewById(R.id.radiusText);
        areaBurningSpinner = findViewById(R.id.areaSpinner);
        circleRadiusSpinner = findViewById(R.id.radiusSpinner);
        getMeasurementsButton = findViewById(R.id.getMeasurementsButton);
        fuelSpinner = findViewById(R.id.fuelSpinner);
        addItemsOnFuelSpinner(fuelSpinner);
        addItemsOnUnitSpinner(circleRadiusSpinner);
        addItemsOnUnitSquaredSpinner(areaBurningSpinner);

        getMeasurementsButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                hrrFuel = fuelSpinner.getSelectedItem().toString();
                areaUnits = areaBurningSpinner.getSelectedItem().toString();
                radiusUnits = circleRadiusSpinner.getSelectedItem().toString();
                maxBurningFlux = ValuesConverstions.FuelBurningFlux(hrrFuel);
                heatCombustion = ValuesConverstions.FuelHeatCombustion(hrrFuel);
            }
        });
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
}
