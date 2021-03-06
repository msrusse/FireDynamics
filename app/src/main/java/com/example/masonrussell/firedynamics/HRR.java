package com.example.masonrussell.firedynamics;

import android.content.Context;
import android.icu.text.Normalizer;
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

import java.util.ArrayList;
import java.util.List;

public class HRR extends AppCompatActivity {

    public Button getMeasurementsButton;
    public EditText areaBurningText;
    public TextView qResult;
    public Spinner typeSelectionSpinner, typeUnitSpinner, fuelSpinner, qSpinner;
    public double maxBurningFlux, heatCombustion, areaDoub, radiusDoub, finalQ;
    public String typeSelection, typeUnits, selectedMaterial;
    public LinearLayout resultLayout;
    private List<String> fuelSelectionList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hrr);
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        resultLayout = findViewById(R.id.resultLayout);
        getMeasurementsButton = findViewById(R.id.getMeasurementsButton);
        areaBurningText = findViewById(R.id.areaText);
        typeSelectionSpinner = findViewById(R.id.typeSelectionSpinner);
        typeUnitSpinner = findViewById(R.id.typeUnitSpinner);
        qSpinner = findViewById(R.id.qSpinner);
        qResult = findViewById(R.id.qResult);
        getMeasurementsButton = findViewById(R.id.getMeasurementsButton);
        fuelSpinner = findViewById(R.id.fuelSpinner);
        resultLayout.setVisibility(View.INVISIBLE);
        addItemsOnFuelSpinner(fuelSpinner);
        addSelectionsOnSpinner(typeSelectionSpinner);
        addFinalUnitsSpinner(qSpinner);
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
        if (ValueClassStorage.hrr != null) getLastHrr(ValueClassStorage.hrr);

        getMeasurementsButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                try {
                    InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(resultLayout.getWindowToken(), 0);
                    selectedMaterial = fuelSpinner.getSelectedItem().toString();
                    typeSelection = typeSelectionSpinner.getSelectedItem().toString();
                    typeUnits = typeUnitSpinner.getSelectedItem().toString();
                    if (typeSelection.equals("Area of Burning")) {
                        areaDoub = Double.parseDouble(areaBurningText.getText().toString());
                        areaDoub = ValuesConverstions.toSquareMeters(areaDoub, typeUnits);
                    } else if (typeSelection.equals("Radius")) {
                        radiusDoub = Double.parseDouble(areaBurningText.getText().toString());
                        radiusDoub = ValuesConverstions.toMeters(radiusDoub, typeUnits);
                        areaDoub = Calculations.CalculateArea(radiusDoub);
                    }
                    getResults();
                }
                catch (Exception ex) {
                    String error = "Please Fill the Empty Fields";
                    Toast.makeText(HRR.this, error, Toast.LENGTH_LONG).show();
                }

                qSpinner.setOnItemSelectedListener(
                        new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                if (qSpinner.getSelectedItem().toString().equals("kW")) {
                                    qResult.setText(String.valueOf(Math.round(finalQ)));
                                } else if (qSpinner.getSelectedItem().toString().equals("Btu/sec")) {
                                    qResult.setText(String.valueOf(Math.round(Calculations.CalculateBtuPerSec(finalQ))));
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

    private void getLastHrr(ValueClassStorage.HRR hrr)
    {
        selectedMaterial = hrr.material;
        fuelSpinner.setSelection(fuelSelectionList.indexOf(selectedMaterial));
        areaDoub = hrr.areaDoub;
        areaBurningText.setText(String.valueOf(areaDoub));
        typeUnitSpinner.setSelection(2);
        getResults();
    }

    private void getResults()
    {
        maxBurningFlux = ValuesConverstions.FuelBurningFlux(selectedMaterial);
        heatCombustion = ValuesConverstions.FuelHeatCombustion(selectedMaterial);
        finalQ = Calculations.CalculateHRRQ(maxBurningFlux, heatCombustion, areaDoub);
        qResult.setText(String.valueOf(Math.round(finalQ)));
        resultLayout.setVisibility(View.VISIBLE);
        ValueClassStorage.HRR hrr = new ValueClassStorage().new HRR(areaDoub, radiusDoub, selectedMaterial);
        ValueClassStorage.hrr = hrr;
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
        fuelSelectionList = new ArrayList<>();
        fuelSelectionList.add("Cellulose");
        fuelSelectionList.add("Gasoline");
        fuelSelectionList.add("Heptane");
        fuelSelectionList.add("Methanol");
        fuelSelectionList.add("PMMA");
        fuelSelectionList.add("Polyethylene");
        fuelSelectionList.add("Polypropylene");
        fuelSelectionList.add("Polystyrene");
        fuelSelectionList.add("PVC");
        fuelSelectionList.add("Wood");
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, fuelSelectionList);
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

    public void addFinalUnitsSpinner(Spinner spinnerToMake)
    {
        List<String> list = new ArrayList<>();
        list.add("kW");
        list.add("Btu/sec");
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, list);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerToMake.setAdapter(dataAdapter);
    }
}
