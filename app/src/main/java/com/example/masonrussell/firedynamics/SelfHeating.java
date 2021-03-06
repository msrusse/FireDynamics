package com.example.masonrussell.firedynamics;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
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

public class SelfHeating extends AppCompatActivity {

    double volumeDoub, heightDoub, mDoub, pDoub, areaDoub, radiusDoub, damkohlerDoub, tempDoub;
    String volumeUnits, heightUnits, tempUnits, materialSelected;
    TextView damkohlerNumberDisplay;
    EditText volumeValue, heightValue, mValue, pValue, tempValue;
    Spinner volumeUnitSpinner, heightUnitSpinner, tempUnitSpinner, materialSelectionSpinner;
    LinearLayout knownTempLayout, damkohlerResultLayout, mLayout, pLayout;
    ViewGroup.LayoutParams mParams, pParams;
    Button getResultsButton;
    public final DecimalFormat twoDigits = new DecimalFormat("0.00");
    private List<String> materialSelectionList = new ArrayList<>();

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_self_heating);
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        damkohlerResultLayout = findViewById(R.id.damkohlerResultLayout);
        knownTempLayout = findViewById(R.id.knownTempLayout);
        damkohlerResultLayout.setVisibility(View.INVISIBLE);
        mLayout = findViewById(R.id.mLayout);
        mLayout.setVisibility(View.INVISIBLE);
        pLayout = findViewById(R.id.pLayout);
        pLayout.setVisibility(View.INVISIBLE);
        knownTempLayout = findViewById(R.id.knownTempLayout);
        damkohlerNumberDisplay = findViewById(R.id.damkohlerResultDisplay);
        volumeValue = findViewById(R.id.pileVolumeValue);
        heightValue = findViewById(R.id.pileHeightValue);
        mValue = findViewById(R.id.mValue);
        pValue = findViewById(R.id.pValue);
        tempValue = findViewById(R.id.tempValue);
        volumeUnitSpinner = findViewById(R.id.pileVolumeSpinner);
        heightUnitSpinner = findViewById(R.id.pileHeightSpinner);
        tempUnitSpinner = findViewById(R.id.tempUnitSpinner);
        materialSelectionSpinner = findViewById(R.id.materialSelectionSpinner);
        mParams = mLayout.getLayoutParams();
        pParams = pLayout.getLayoutParams();
        getResultsButton = findViewById(R.id.getMeasurementsButton);
        addItemsOnMaterialSelectionSpinner(materialSelectionSpinner);
        addUnitsOnVolumeSpinner(volumeUnitSpinner);
        addUnitsOnHeightSpinner(heightUnitSpinner);
        addUnitsOnTemperatureSpinner(tempUnitSpinner);
        if (ValueClassStorage.selfHeating != null)
        {
            ValueClassStorage.SelfHeating selfHeating = ValueClassStorage.selfHeating;
            volumeDoub = selfHeating.pileVolumeDoub;
            heightDoub = selfHeating.pileHeightDoub;
            tempDoub = selfHeating.temperatureDoub;
            mDoub = selfHeating.mDoub;
            pDoub = selfHeating.pDoub;
            volumeValue.setText(String.valueOf(volumeDoub));
            heightValue.setText(String.valueOf(heightDoub));
            tempValue.setText(String.valueOf(tempDoub));
            mValue.setText(String.valueOf(mDoub));
            pValue.setText(String.valueOf(pValue));
            materialSelected = selfHeating.material;
            volumeUnitSpinner.setSelection(2);
            heightUnitSpinner.setSelection(2);
            tempUnitSpinner.setSelection(1);
            materialSelectionSpinner.setSelection(materialSelectionList.indexOf(materialSelected));
            tempUnitSpinner.setSelection(2);
            getResults();
        }
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

        knownTempLayout.setVisibility(View.VISIBLE);

        getResultsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(damkohlerResultLayout.getWindowToken(), 0);
                    volumeDoub = Double.parseDouble(volumeValue.getText().toString());
                    volumeUnits = volumeUnitSpinner.getSelectedItem().toString();
                    heightDoub = Double.parseDouble(heightValue.getText().toString());
                    heightUnits = heightUnitSpinner.getSelectedItem().toString();
                    volumeDoub = ValuesConverstions.toCubicMeters(volumeDoub, volumeUnits);
                    heightDoub = ValuesConverstions.toMeters(heightDoub, heightUnits);
                    tempDoub = Double.parseDouble(tempValue.getText().toString());
                    tempUnits = tempUnitSpinner.getSelectedItem().toString();
                    tempDoub = ValuesConverstions.toDegreesKelvin(tempDoub, tempUnits);
                    if (materialSelected.equals("Enter Statistics for Different Material")) {
                        mDoub = Double.parseDouble(mValue.getText().toString());
                        pDoub = Double.parseDouble(pValue.getText().toString());
                    } else {
                        mDoub = ValuesConverstions.getTamurelloMValue(materialSelected);
                        pDoub = ValuesConverstions.getTamburelloPValue(materialSelected);
                    }
                    getResults();
                } catch (Exception ex) {
                    String error = "Please Fill the Empty Fields";
                    Toast.makeText(SelfHeating.this, error, Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private void getResults()
    {
        areaDoub = volumeDoub / heightDoub;
        radiusDoub = Math.sqrt(areaDoub) / Math.PI;
        radiusDoub = ValuesConverstions.toMillimeters(radiusDoub, "m");
        damkohlerDoub = Math.pow(radiusDoub, 2) / Math.pow(tempDoub, 2) * Math.exp(mDoub - pDoub / tempDoub);
        damkohlerNumberDisplay.setText(twoDigits.format(damkohlerDoub));
        damkohlerResultLayout.setVisibility(View.VISIBLE);
        ValueClassStorage.SelfHeating selfHeating = new ValueClassStorage().new SelfHeating(volumeDoub, heightDoub, materialSelected, tempDoub, mDoub, pDoub);
        ValueClassStorage.selfHeating = selfHeating;
    }

    public void addItemsOnMaterialSelectionSpinner(Spinner spinnerToMake) {
        materialSelectionList.add("Ammonium Nitrate");
        materialSelectionList.add("Animal Feedstuff");
        materialSelectionList.add("Bagasse");
        materialSelectionList.add("Cellulose Insulation");
        materialSelectionList.add("Coal");
        materialSelectionList.add("Cotton");
        materialSelectionList.add("Forrest Floor Material I");
        materialSelectionList.add("Plywood");
        materialSelectionList.add("Wheat Flour");
        materialSelectionList.add("Wood Fiberboard");
        materialSelectionList.add("Enter Statistics for Different Material");
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, materialSelectionList);
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
