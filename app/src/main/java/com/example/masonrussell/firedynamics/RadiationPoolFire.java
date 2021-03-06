package com.example.masonrussell.firedynamics;

import android.content.Context;
import android.provider.Telephony;
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
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class RadiationPoolFire extends AppCompatActivity {

    public double diameterDoub, distanceDoub, lengthDoub, widthDoub, ldivdDoub, heatFluxResultDoub;
    public EditText typeSelection, distance, widthLength;
    public Spinner typeSelectionSpinner, typeSelectionUnitSpinner, secondSquareUnitSpinner, distanceSpinner, heatFluxSpinner;
    public TextView heatFluxResult, secondSquareText, ldValidTestResult;
    public String diameterUitType, lengthUnitType, widthUnitType, heatFluxUnitType, distanceUnitType, ldResult;
    public Button calculateButton;
    public LinearLayout resultLayout, secondSquareLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_radiation_pool_fire);
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        final DecimalFormat twoDigits = new DecimalFormat("0.00");
        typeSelection = findViewById(R.id.typeSelectionText);
        secondSquareText = findViewById(R.id.secondSquareView);
        ldValidTestResult = findViewById(R.id.ldValidTestResult);
        distance = findViewById(R.id.targetDistanceText);
        resultLayout = findViewById(R.id.resultView);
        widthLength = findViewById(R.id.secondSquareText);
        secondSquareLayout = findViewById(R.id.secondSquareLayout);
        typeSelectionSpinner = findViewById(R.id.typeSelectionSpinner);
        typeSelectionUnitSpinner = findViewById(R.id.typeUnitSpinner);
        secondSquareUnitSpinner = findViewById(R.id.secondSquareSpinner);
        distanceSpinner = findViewById(R.id.targetDistanceSpinner);
        heatFluxSpinner = findViewById(R.id.heatFluxSpinner);
        heatFluxResult = findViewById(R.id.heatFluxResult);
        resultLayout.setVisibility(View.INVISIBLE);
        calculateButton = findViewById(R.id.getMeasurementsButton);
        secondSquareLayout.setVisibility(View.INVISIBLE);
        addItemsOnUnitSpinner(distanceSpinner);
        addItemsOnUnitSpinner(typeSelectionUnitSpinner);
        addItemsOnUnitSpinner(secondSquareUnitSpinner);
        addDiameterSelections(typeSelectionSpinner);
        typeSelectionSpinner.setOnItemSelectedListener(
                new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        switch (typeSelectionSpinner.getSelectedItem().toString())
                        {
                            case "Length of Square Pool":
                                String toDisplay = "Width of Square Pool";
                                secondSquareText.setText(toDisplay);
                                secondSquareLayout.setVisibility(View.VISIBLE);
                                break;
                            case "Width of Square Pool":
                                toDisplay = "Length of Square Pool";
                                secondSquareText.setText(toDisplay);
                                secondSquareLayout.setVisibility(View.VISIBLE);
                                break;
                            case "Diameter of Pool":
                                secondSquareLayout.setVisibility(View.INVISIBLE);
                                break;
                        }
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                }
        );
        if (ValueClassStorage.radiationPoolFire != null)
        {
            ValueClassStorage.RadiationPoolFire radiationPoolFire = ValueClassStorage.radiationPoolFire;
            distanceDoub = radiationPoolFire.targetDistanceDoub;
            diameterDoub = radiationPoolFire.diameterDoub;
            ldivdDoub = distanceDoub / diameterDoub;
            distance.setText(String.valueOf(distanceDoub));
            typeSelection.setText(String.valueOf(diameterDoub));
            typeSelectionSpinner.setSelection(0);
            distanceSpinner.setSelection(2);
            typeSelectionUnitSpinner.setSelection(2);
            getResults();
        }


        calculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(resultLayout.getWindowToken(), 0);
                    distanceDoub = Double.parseDouble(distance.getText().toString());
                    distanceUnitType = distanceSpinner.getSelectedItem().toString();
                    distanceDoub = ValuesConverstions.toMeters(distanceDoub, distanceUnitType);
                    switch (typeSelectionSpinner.getSelectedItem().toString()) {
                        case "Length of Square Pool":
                            lengthDoub = Double.parseDouble(typeSelection.getText().toString());
                            widthDoub = Double.parseDouble(widthLength.getText().toString());
                            lengthUnitType = typeSelectionUnitSpinner.getSelectedItem().toString();
                            widthUnitType = secondSquareUnitSpinner.getSelectedItem().toString();
                            lengthDoub = ValuesConverstions.toMeters(lengthDoub, lengthUnitType);
                            widthDoub = ValuesConverstions.toMeters(widthDoub, widthUnitType);
                            diameterDoub = Calculations.CalculateDiameterFromLengthWidth(lengthDoub, widthDoub);
                            ldivdDoub = distanceDoub / diameterDoub;
                            break;
                        case "Width of Square Pool":
                            widthDoub = Double.parseDouble(typeSelection.getText().toString());
                            lengthDoub = Double.parseDouble(widthLength.getText().toString());
                            lengthUnitType = secondSquareUnitSpinner.getSelectedItem().toString();
                            widthUnitType = typeSelectionUnitSpinner.getSelectedItem().toString();
                            lengthDoub = ValuesConverstions.toMeters(lengthDoub, lengthUnitType);
                            widthDoub = ValuesConverstions.toMeters(widthDoub, widthUnitType);
                            diameterDoub = Calculations.CalculateDiameterFromLengthWidth(lengthDoub, widthDoub);
                            ldivdDoub = distanceDoub / diameterDoub;
                            break;
                        case "Diameter of Pool":
                            diameterDoub = Double.parseDouble(typeSelection.getText().toString());
                            diameterUitType = typeSelectionUnitSpinner.getSelectedItem().toString();
                            diameterDoub = ValuesConverstions.toMeters(diameterDoub, diameterUitType);
                            ldivdDoub = distanceDoub / diameterDoub;
                            break;
                    }
                    getResults();
                }
                catch (Exception ex) {
                    String error = "Please Fill the Empty Fields";
                    Toast.makeText(RadiationPoolFire.this, error, Toast.LENGTH_LONG).show();
                }
                heatFluxSpinner.setOnItemSelectedListener(
                        new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                switch (heatFluxSpinner.getSelectedItem().toString())
                                {
                                    case "kW/m^2":
                                        heatFluxResultDoub = ValuesConverstions.EnergyDensitytokW(heatFluxResultDoub, heatFluxUnitType);
                                        heatFluxResult.setText(String.valueOf(twoDigits.format(heatFluxResultDoub)));
                                        heatFluxUnitType = heatFluxSpinner.getSelectedItem().toString();
                                        break;
                                    case "Btu/sec/ft^2":
                                        heatFluxResultDoub = ValuesConverstions.EnergyDensitytoBtu(heatFluxResultDoub, heatFluxUnitType);
                                        heatFluxResult.setText(String.valueOf(twoDigits.format(heatFluxResultDoub)));
                                        heatFluxUnitType = heatFluxSpinner.getSelectedItem().toString();
                                        break;
                                }
                            }

                            @Override
                            public void onNothingSelected(AdapterView<?> parent) {

                            }
                        }
                );
            }
        });
    }

    private void getResults()
    {
        heatFluxResultDoub = Calculations.CalculateHeatFluxtoTarget(distanceDoub, diameterDoub);
        heatFluxResult.setText(String.valueOf(Math.round(heatFluxResultDoub)));
        addUnitsOnResultSpinner(heatFluxSpinner);
        heatFluxUnitType = heatFluxSpinner.getSelectedItem().toString();
        resultLayout.setVisibility(View.VISIBLE);
        if (ldivdDoub < 0.7) {
            ldResult = "Too close to pool edge";
        } else if (ldivdDoub > 15) {
            ldResult = "Too far from pool edge";
        } else {
            ldResult = "OK";
        }
        ldValidTestResult.setText(ldResult);
        ValueClassStorage.RadiationPoolFire radiationPoolFire = new ValueClassStorage().new RadiationPoolFire(distanceDoub, diameterDoub);
        ValueClassStorage.radiationPoolFire = radiationPoolFire;
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

    public void addDiameterSelections(Spinner spinnerToMake)
    {
        List<String> list = new ArrayList<>();
        list.add("Diameter of Pool");
        list.add("Length of Square Pool");
        list.add("Width of Square Pool");
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, list);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerToMake.setAdapter(dataAdapter);
    }

    public void addUnitsOnResultSpinner(Spinner spinnerToMake)
    {
        List<String> list = new ArrayList<>();
        list.add("kW/m^2");
        list.add("Btu/sec/ft^2");
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, list);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerToMake.setAdapter(dataAdapter);
    }
}
