package com.example.masonrussell.firedynamics;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
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

public class SelfHeating extends AppCompatActivity {

    double volumeDoub, heightDoub, mDoub, pDoub, areaDoub, radiusDoub, damkohlerDoub, tempDoub;
    String volumeUnits, heightUnits, tempUnits, materialSelected;
    TextView damkohlerNumberDisplay, temperatureToDisplay;
    EditText volumeValue, heightValue, mValue, pValue, tempValue, damkohlerValue;
    Spinner volumeUnitSpinner, heightUnitSpinner, tempUnitSpinner, materialSelectionSpinner, tempResultUnitSpinner, knownValueSelectionSpinner;
    LinearLayout knownTempLayout, knownDamkohlerLayout, damkohlerResultLayout, tempResultLayout, mLayout, pLayout;
    ViewGroup.LayoutParams mParams, pParams, knownTempParams, knownTempResultParams, knownDamkohlerParams;
    Button getResultsButton;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_self_heating);
        damkohlerResultLayout = findViewById(R.id.damkohlerResultLayout);
        tempResultLayout = findViewById(R.id.tempResultsLayout);
        damkohlerResultLayout.setVisibility(View.INVISIBLE);
        tempResultLayout.setVisibility(View.INVISIBLE);
        mLayout = findViewById(R.id.mLayout);
        mLayout.setVisibility(View.INVISIBLE);
        pLayout = findViewById(R.id.pLayout);
        pLayout.setVisibility(View.INVISIBLE);
        knownDamkohlerLayout = findViewById(R.id.damkohlerNumLayout);
        knownTempLayout = findViewById(R.id.knownTempLayout);
        knownDamkohlerLayout.setVisibility(View.INVISIBLE);
        knownTempLayout.setVisibility(View.INVISIBLE);
        damkohlerNumberDisplay = findViewById(R.id.damkohlerResultDisplay);
        temperatureToDisplay = findViewById(R.id.tempResultDisplay);
        volumeValue = findViewById(R.id.pileVolumeValue);
        heightValue = findViewById(R.id.pileHeightValue);
        mValue = findViewById(R.id.mValue);
        pValue = findViewById(R.id.pValue);
        tempValue = findViewById(R.id.tempValue);
        damkohlerValue = findViewById(R.id.damkohlerNumValue);
        volumeUnitSpinner = findViewById(R.id.pileVolumeSpinner);
        heightUnitSpinner = findViewById(R.id.pileHeightSpinner);
        tempUnitSpinner = findViewById(R.id.tempUnitSpinner);
        materialSelectionSpinner = findViewById(R.id.materialSelectionSpinner);
        tempResultUnitSpinner = findViewById(R.id.tempResultUnitSpinner);
        knownValueSelectionSpinner = findViewById(R.id.knownValueSelectionSpinner);
        mParams = mLayout.getLayoutParams();
        pParams = pLayout.getLayoutParams();
        knownTempParams = knownTempLayout.getLayoutParams();
        knownTempResultParams = tempResultLayout.getLayoutParams();
        knownDamkohlerParams = knownDamkohlerLayout.getLayoutParams();
        getResultsButton = findViewById(R.id.getMeasurementsButton);
        addItemsOnMaterialSelectionSpinner(materialSelectionSpinner);
        addItemsOnUnknownValueSpinner(knownValueSelectionSpinner);
        addUnitsOnVolumeSpinner(volumeUnitSpinner);
        addUnitsOnHeightSpinner(heightUnitSpinner);
        addUnitsOnTemperatureSpinner(tempUnitSpinner);
        addUnitsOnTemperatureSpinner(tempResultUnitSpinner);
        materialSelectionSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                materialSelected = materialSelectionSpinner.getSelectedItem().toString();
                if (materialSelected.equals("Enter Statistics for Different Material")) {
                    mParams.height = LinearLayout.LayoutParams.WRAP_CONTENT;
                    pParams.height = LinearLayout.LayoutParams.WRAP_CONTENT;
                    mLayout.setLayoutParams(mParams);
                    pLayout.setLayoutParams(pParams);
                    mLayout.setVisibility(View.VISIBLE);
                    pLayout.setVisibility(View.VISIBLE);
                } else {
                    mParams.height = 0;
                    pParams.height = 0;
                    mLayout.setLayoutParams(mParams);
                    pLayout.setLayoutParams(pParams);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        knownValueSelectionSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (knownValueSelectionSpinner.getSelectedItem().toString())
                {
                    case "Known Temperature/Unknown Damkohler":
                        knownDamkohlerParams.height=0;
                        knownTempParams.height=LinearLayout.LayoutParams.WRAP_CONTENT;
                        knownDamkohlerLayout.setLayoutParams(knownDamkohlerParams);
                        knownTempLayout.setLayoutParams(knownTempParams);
                        knownDamkohlerLayout.setVisibility(View.INVISIBLE);
                        knownTempLayout.setVisibility(View.VISIBLE);
                        break;
                    case "Known Damkohler/Unknown Temperature":
                        knownDamkohlerParams.height=LinearLayout.LayoutParams.WRAP_CONTENT;
                        knownTempParams.height=0;
                        knownDamkohlerLayout.setLayoutParams(knownDamkohlerParams);
                        knownTempLayout.setLayoutParams(knownTempParams);
                        knownTempLayout.setVisibility(View.INVISIBLE);
                        knownDamkohlerLayout.setVisibility(View.VISIBLE);
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
                    volumeDoub = Double.parseDouble(volumeValue.getText().toString());
                    volumeUnits = volumeUnitSpinner.getSelectedItem().toString();
                    heightDoub = Double.parseDouble(heightValue.getText().toString());
                    heightUnits = heightUnitSpinner.getSelectedItem().toString();
                    volumeDoub = ValuesConverstions.toCubicMeters(volumeDoub, volumeUnits);
                    heightDoub = ValuesConverstions.toMeters(heightDoub, heightUnits);
                    areaDoub = volumeDoub / heightDoub;
                    radiusDoub = Math.sqrt(areaDoub) / Math.PI;
                    radiusDoub = ValuesConverstions.toMillimeters(radiusDoub, "m");
                    if (materialSelected.equals("Enter Statistics for Different Material")) {
                        mDoub = Double.parseDouble(mValue.getText().toString());
                        pDoub = Double.parseDouble(pValue.getText().toString());
                    } else {
                        mDoub = ValuesConverstions.getTamurelloMValue(materialSelected);
                        pDoub = ValuesConverstions.getTamburelloPValue(materialSelected);
                    }
                    if (knownValueSelectionSpinner.getSelectedItem().toString().equals("Known Temperature/Unknown Damkohler")) {
                        damkohlerResultLayout.setVisibility(View.INVISIBLE);
                        tempDoub = Double.parseDouble(tempValue.getText().toString());
                        tempUnits = tempUnitSpinner.getSelectedItem().toString();
                        tempDoub = ValuesConverstions.toDegreesKelvin(tempDoub, tempUnits);
                        damkohlerDoub = Math.pow(radiusDoub, 2) / Math.pow(tempDoub, 2) * Math.exp(mDoub - pDoub / tempDoub);
                        damkohlerNumberDisplay.setText(String.valueOf(damkohlerDoub));
                        knownTempParams.height = LinearLayout.LayoutParams.WRAP_CONTENT;
                        knownTempLayout.setLayoutParams(knownTempParams);
                        damkohlerResultLayout.setVisibility(View.VISIBLE);
                    } else {
                        knownTempLayout.setVisibility(View.INVISIBLE);
                        knownTempParams.height = 0;
                        knownTempLayout.setLayoutParams(knownTempParams);
                        damkohlerDoub = Double.parseDouble(damkohlerValue.getText().toString());
                        damkohlerResultLayout.setVisibility(View.VISIBLE);
                    }
                }
                catch (Exception ex) {
                    String error = "Please Fill the Empty Fields";
                    Toast.makeText(SelfHeating.this, error, Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    public void addItemsOnMaterialSelectionSpinner(Spinner spinnerToMake) {
        List<String> list = new ArrayList<>();
        list.add("Ammonium Nitrate");
        list.add("Animal Feedstuff");
        list.add("Bagasse");
        list.add("Cellulose Insulation");
        list.add("Coal");
        list.add("Cotton");
        list.add("Forrest Floor Material I");
        list.add("Plywood");
        list.add("Wheat Flour");
        list.add("Wood Fiberboard");
        list.add("Enter Statistics for Different Material");
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, list);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerToMake.setAdapter(dataAdapter);
    }

    public void addItemsOnUnknownValueSpinner(Spinner spinnerToMake) {
        List<String> list = new ArrayList<>();
        list.add("Known Temperature/Unknown Damkohler");
        list.add("Known Damkohler/Unknown Temperature");
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, list);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerToMake.setAdapter(dataAdapter);
    }

    public void addUnitsOnVolumeSpinner(Spinner spinnerToMake) {
        List<String> list = new ArrayList<>();
        list.add("ft^3");
        list.add("in^3");
        list.add("m^3");
        list.add("liters");
        list.add("gallons");
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, list);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerToMake.setAdapter(dataAdapter);
    }

    public void addUnitsOnHeightSpinner(Spinner spinnerToMake) {
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

    public void addUnitsOnTemperatureSpinner(Spinner spinnerToMake) {
        List<String> list = new ArrayList<>();
        list.add("F");
        list.add("C");
        list.add("K");
        list.add("R");
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, list);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerToMake.setAdapter(dataAdapter);
    }
}
