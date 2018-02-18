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

public class FlameHeight extends AppCompatActivity {

    public Button getMeasurementsButton;
    public TextView lResult, secondSquareView;
    public Spinner qUnitSpinner, typeSelectionSpinner, secondSquareSpinner, typeSelectionUnitSpinner, lSpinner;
    public EditText qText, typeSelectionText, secondSquareText;
    public LinearLayout secondSquareLayout, resultView;
    public double diameterDoub, areaDoub, lengthDoub, widthDoub, qDoub, lResultDoub;
    public String diameterUnits, areaUnits, lengthUnits, widthUnits, qUnits, resultUnits;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flame_height);
        final DecimalFormat twoDigits = new DecimalFormat("0.00");
        getMeasurementsButton = findViewById(R.id.getMeasurementsButton);
        lResult = findViewById(R.id.lResult);
        lSpinner = findViewById(R.id.lSpinner);
        resultView = findViewById(R.id.resultView);
        qUnitSpinner = findViewById(R.id.qHeatUnitSpinner);
        typeSelectionUnitSpinner = findViewById(R.id.typeUnitSpinner);
        typeSelectionSpinner = findViewById(R.id.typeSelectionSpinner);
        qText = findViewById(R.id.qHeatReleaseText);
        typeSelectionText = findViewById(R.id.typeSelectionText);
        secondSquareSpinner = findViewById(R.id.secondSquareSpinner);
        secondSquareText = findViewById(R.id.secondSquareText);
        secondSquareView = findViewById(R.id.secondSquareView);
        secondSquareLayout = findViewById(R.id.secondSquareLayout);
        secondSquareLayout.setVisibility(View.INVISIBLE);
        resultView.setVisibility(View.INVISIBLE);
        addQUnits(qUnitSpinner);
        addDiameterSelections(typeSelectionSpinner);

        typeSelectionSpinner.setOnItemSelectedListener(
                new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        if (typeSelectionSpinner.getSelectedItem().toString().equals("Diameter of Pool Fire"))
                        {
                            addItemsOnUnitSpinner(typeSelectionUnitSpinner);
                            secondSquareLayout.setVisibility(View.INVISIBLE);
                        }
                        else if (typeSelectionSpinner.getSelectedItem().toString().equals("Area of Circular Pool"))
                        {
                            addItemsOnUnitSquaredSpinner(typeSelectionUnitSpinner);
                            secondSquareLayout.setVisibility(View.INVISIBLE);
                        }
                        else if (typeSelectionSpinner.getSelectedItem().toString().equals("Length of Square Pool"))
                        {
                            addItemsOnUnitSpinner(typeSelectionUnitSpinner);
                            addItemsOnUnitSpinner(secondSquareSpinner);
                            String toDisplay = "Width of Pool";
                            secondSquareView.setText(toDisplay);
                            secondSquareLayout.setVisibility(View.VISIBLE);
                        }
                        else
                        {
                            addItemsOnUnitSpinner(typeSelectionUnitSpinner);
                            addItemsOnUnitSpinner(secondSquareSpinner);
                            String toDisplay = "Length of Pool";
                            secondSquareView.setText(toDisplay);
                            secondSquareLayout.setVisibility(View.VISIBLE);
                        }
                    }
                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                }
        );

        getMeasurementsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                qDoub = Double.parseDouble(qText.getText().toString());
                qUnits = qUnitSpinner.getSelectedItem().toString();
                if (qUnitSpinner.getSelectedItem().toString().equals("Btu/sec"))
                {
                    qDoub = Calculations.CalculateBtuPerSec(qDoub);
                }
                switch (typeSelectionSpinner.getSelectedItem().toString())
                {
                    case "Diameter of Pool Fire":
                        diameterDoub = Double.parseDouble(typeSelectionText.getText().toString());
                        diameterUnits = typeSelectionUnitSpinner.getSelectedItem().toString();
                        diameterDoub = ValuesConverstions.toMeters(diameterDoub, diameterUnits);
                        break;
                    case "Area of Circular Pool":
                        areaDoub = Double.parseDouble(typeSelectionText.getText().toString());
                        areaUnits = typeSelectionUnitSpinner.getSelectedItem().toString();
                        areaDoub = ValuesConverstions.toSquareMeters(areaDoub, areaUnits);
                        diameterDoub = Calculations.CalculateDiameterFromArea(areaDoub);
                        //Toast.makeText(FlameHeight.this, String.valueOf(diameterDoub), Toast.LENGTH_LONG).show();
                        break;
                    case "Length of Square Pool":
                        lengthDoub = Double.parseDouble(typeSelectionText.getText().toString());
                        lengthUnits = typeSelectionUnitSpinner.getSelectedItem().toString();
                        widthDoub = Double.parseDouble(secondSquareText.getText().toString());
                        widthUnits = secondSquareSpinner.getSelectedItem().toString();
                        lengthDoub = ValuesConverstions.toMeters(lengthDoub, lengthUnits);
                        widthDoub = ValuesConverstions.toMeters(widthDoub, widthUnits);
                        diameterDoub = Calculations.CalculateDiameterFromLengthWidth(lengthDoub, widthDoub);
                        //Toast.makeText(FlameHeight.this, String.valueOf(diameterDoub), Toast.LENGTH_LONG).show();
                        break;
                    case "Width of Square Pool":
                        widthDoub = Double.parseDouble(typeSelectionText.getText().toString());
                        widthDoub = Double.parseDouble(secondSquareText.getText().toString());
                        lengthDoub = Double.parseDouble(secondSquareText.getText().toString());
                        lengthUnits = typeSelectionUnitSpinner.getSelectedItem().toString();
                        lengthDoub = ValuesConverstions.toMeters(lengthDoub, lengthUnits);
                        widthDoub = ValuesConverstions.toMeters(widthDoub, widthUnits);
                        diameterDoub = Calculations.CalculateDiameterFromLengthWidth(lengthDoub, widthDoub);
                        break;
                }
                lResultDoub = Calculations.CalculateFlameHeight(qDoub, diameterDoub);
                lResult.setText(String.valueOf(twoDigits.format(lResultDoub)));
                addItemsOnUnitSpinner(lSpinner);
                resultView.setVisibility(View.VISIBLE);
                resultUnits = lSpinner.getSelectedItem().toString();
                lSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        switch (lSpinner.getSelectedItem().toString())
                        {
                            case ("m"):
                                lResultDoub = ValuesConverstions.toMeters(lResultDoub, resultUnits);
                                lResult.setText(twoDigits.format(lResultDoub));
                                resultUnits = lSpinner.getSelectedItem().toString();
                                break;
                            case ("ft"):
                                lResultDoub = ValuesConverstions.toFeet(lResultDoub, resultUnits);
                                lResult.setText(twoDigits.format(lResultDoub));
                                resultUnits = lSpinner.getSelectedItem().toString();
                                break;
                            case ("in"):
                                lResultDoub = ValuesConverstions.toInches(lResultDoub, resultUnits);
                                lResult.setText(twoDigits.format(lResultDoub));
                                resultUnits = lSpinner.getSelectedItem().toString();
                                break;
                            case ("mm"):
                                lResultDoub = ValuesConverstions.toMillimeters(lResultDoub, resultUnits);
                                lResult.setText(twoDigits.format(lResultDoub));
                                resultUnits = lSpinner.getSelectedItem().toString();
                                break;
                            case "cm":
                                lResultDoub = ValuesConverstions.toCentimeters(lResultDoub, resultUnits);
                                lResult.setText(twoDigits.format(lResultDoub));
                                resultUnits = lSpinner.getSelectedItem().toString();
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
        list.add("m");
        list.add("ft");
        list.add("in");
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

    public void addQUnits(Spinner spinnerToMake)
    {
        List<String> list = new ArrayList<>();
        list.add("kW");
        list.add("Btu/sec");
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, list);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerToMake.setAdapter(dataAdapter);
    }

    public void addDiameterSelections(Spinner spinnerToMake)
    {
        List<String> list = new ArrayList<>();
        list.add("Diameter of Pool Fire");
        list.add("Area of Circular Pool");
        list.add("Length of Square Pool");
        list.add("Width of Square Pool");
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, list);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerToMake.setAdapter(dataAdapter);
    }
}
