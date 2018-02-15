package com.example.masonrussell.firedynamics;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class FlashOver extends AppCompatActivity {

    public Spinner intLiningSpinner, materialSpinner, ventWidthSpinner, ventHeightSpinner, compWidthSpinner, compLengthSpinner, compHeightSpinner;
    public EditText intLiningText, ventWidthText, ventHeightText, compWidthText, compLengthText, compHeightText;
    public double intLining, thermalConductivity, compWidth, compLength, compHeight, ventHeight, ventWidth;
    public String intLiningUnits, compWidthUnits, compLengthUnits, compHeightUnits, ventHeightUnits, ventWidthUnits;
    public Button calculateButton;
    public TextView at, av, hk, thermalConduct;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flash_over);
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
        thermalConduct = findViewById(R.id.conductivityResult);
        intLiningText = findViewById(R.id.intLiningText);
        intLiningSpinner = findViewById(R.id.intLiningSpinner);
        hk = findViewById(R.id.hkResult);
        av = findViewById(R.id.avResult);
        at = findViewById(R.id.atResult);
        addItemsOnMaterialSpinner(materialSpinner);
        addItemsOnUnitSpinner(intLiningSpinner);
        addItemsOnUnitSpinner(compWidthSpinner);
        addItemsOnUnitSpinner(compLengthSpinner);
        addItemsOnUnitSpinner(compHeightSpinner);
        addItemsOnUnitSpinner(ventHeightSpinner);
        addItemsOnUnitSpinner(ventWidthSpinner);

        calculateButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                compWidth = Double.parseDouble(compWidthText.getText().toString());
                compHeight = Double.parseDouble(compHeightText.getText().toString());
                compLength = Double.parseDouble(compLengthText.getText().toString());
                ventHeight = Double.parseDouble(ventHeightText.getText().toString());
                ventWidth = Double.parseDouble(ventWidthText.getText().toString());
                intLining = Double.parseDouble(intLiningText.getText().toString());
                compWidthUnits = compWidthSpinner.getSelectedItem().toString();
                compHeightUnits = compHeightSpinner.getSelectedItem().toString();
                compLengthUnits = compLengthSpinner.getSelectedItem().toString();
                ventHeightUnits = ventHeightSpinner.getSelectedItem().toString();
                ventWidthUnits = ventWidthSpinner.getSelectedItem().toString();
                intLiningUnits = intLiningSpinner.getSelectedItem().toString();
                compWidth = ValuesConverstions.toMeters(compWidth, compWidthUnits);
                compHeight = ValuesConverstions.toMeters(compHeight, compHeightUnits);
                compLength = ValuesConverstions.toMeters(compLength, compLengthUnits);
                ventHeight = ValuesConverstions.toMeters(ventHeight, ventHeightUnits);
                ventWidth = ValuesConverstions.toMeters(ventWidth, ventHeightUnits);
                intLining = ValuesConverstions.toMeters(intLining, intLiningUnits);
                thermalConductivity = ValuesConverstions.thermalConductivity(materialSpinner.getSelectedItem().toString());
                thermalConduct.setText(String.valueOf(thermalConductivity));
                double hkdoub = Calculations.Calculatehk(thermalConductivity, intLining);
                hk.setText(String.valueOf(hkdoub));
                double avdoub = Calculations.CalculateAv(ventWidth, ventHeight);
                av.setText(String.valueOf(avdoub));
                double atdoub = Calculations.CalculateAT(compWidth, compLength, compHeight, ventWidth, ventHeight);
                at.setText(String.valueOf(atdoub));
                double mccaffreyQ = Calculations.CalculateMccaffreyQ(hkdoub, atdoub, avdoub, ventHeight);
                Toast.makeText(FlashOver.this, String.valueOf(mccaffreyQ), Toast.LENGTH_LONG).show();
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
}
