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

import java.util.ArrayList;
import java.util.List;

public class FlameHeight extends AppCompatActivity {

    public Button getMeasurementsButton;
    public TextView lResult, secondSquareView;
    public Spinner qUnitSpinner, typeSelectionSpinner, secondSquareSpinner, typeSelectionUnitSpinner;
    public EditText qText, typeSelectionText, secondSquareText;
    public LinearLayout secondSquareLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flame_height);

        getMeasurementsButton = findViewById(R.id.getMeasurementsButton);
        lResult = findViewById(R.id.lResult);
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
                            addItemsOnUnitSpinner(secondSquareSpinner);
                            String toDisplay = "Width of Pool";
                            secondSquareView.setText(toDisplay);
                            secondSquareLayout.setVisibility(View.VISIBLE);
                        }
                        else
                        {
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
