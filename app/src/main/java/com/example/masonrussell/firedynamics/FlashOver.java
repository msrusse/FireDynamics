package com.example.masonrussell.firedynamics;

import android.content.Context;
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

public class FlashOver extends AppCompatActivity {

    public Spinner mccaffreyQSpinner, babrauskasQSpinner, qThomasSpinner, intLiningSpinner, materialSpinner, ventWidthSpinner, ventHeightSpinner, compWidthSpinner, compLengthSpinner, compHeightSpinner;
    public EditText intLiningText, ventWidthText, ventHeightText, compWidthText, compLengthText, compHeightText;
    public double intLining, thermalConductivity, compWidth, compLength, compHeight, ventHeight, ventWidth, qMccaffreyDoub, qBabrauskasDoub, qThomasDoub;
    public String intLiningUnits, compWidthUnits, compLengthUnits, compHeightUnits, ventHeightUnits, ventWidthUnits;
    public Button calculateButton;
    public LinearLayout resultLayout;
    public TextView qMccaffrey, qBabrauskas, qThomas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flash_over);
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
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
        qMccaffrey = findViewById(R.id.mccaffreyQResult);
        mccaffreyQSpinner = findViewById(R.id.mccaffreyQSpinner);
        babrauskasQSpinner = findViewById(R.id.babrauskasQSpinner);
        qThomasSpinner = findViewById(R.id.thomasQSpinner);
        qBabrauskas = findViewById(R.id.babrauskasQResult);
        qThomas = findViewById(R.id.thomasQResult);
        resultLayout.setVisibility(View.INVISIBLE);
        addItemsOnMaterialSpinner(materialSpinner);
        addItemsOnUnitSpinner(intLiningSpinner);
        addItemsOnUnitSpinner(compWidthSpinner);
        addItemsOnUnitSpinner(compLengthSpinner);
        addItemsOnUnitSpinner(compHeightSpinner);
        addItemsOnUnitSpinner(ventHeightSpinner);
        addItemsOnUnitSpinner(ventWidthSpinner);
        addFinalUnitsSpinner(qThomasSpinner);
        addFinalUnitsSpinner(babrauskasQSpinner);
        addFinalUnitsSpinner(mccaffreyQSpinner);

        calculateButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                try {
                    InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(resultLayout.getWindowToken(), 0);
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
                    double hkdoub = Calculations.Calculatehk(thermalConductivity, intLining);
                    double avdoub = Calculations.CalculateAv(ventWidth, ventHeight);
                    double atdoub = Calculations.CalculateAT(compWidth, compLength, compHeight, ventWidth, ventHeight);
                    qMccaffreyDoub = Calculations.CalculateMccaffreyQ(hkdoub, atdoub, avdoub, ventHeight);
                    qBabrauskasDoub = Calculations.CalculateBarbraukas(avdoub, ventHeight);
                    qThomasDoub = Calculations.CalculateThomas(avdoub, atdoub, ventHeight);
                    if (mccaffreyQSpinner.getSelectedItem().toString().equals("kW")) {
                        qMccaffrey.setText(String.valueOf(Math.round(qMccaffreyDoub)));
                    } else if (mccaffreyQSpinner.getSelectedItem().toString().equals("Btu/sec")) {
                        qMccaffrey.setText(String.valueOf(Math.round(Calculations.CalculateBtuPerSec(qMccaffreyDoub))));
                    }
                    if (babrauskasQSpinner.getSelectedItem().toString().equals("kW")) {
                        qBabrauskas.setText(String.valueOf(Math.round(qBabrauskasDoub)));
                    } else if (babrauskasQSpinner.getSelectedItem().toString().equals("Btu/sec")) {
                        qBabrauskas.setText(String.valueOf(Math.round(Calculations.CalculateBtuPerSec(qBabrauskasDoub))));
                    }
                    if (qThomasSpinner.getSelectedItem().toString().equals("kW")) {
                        qThomas.setText(String.valueOf(Math.round(qThomasDoub)));
                    } else if (qThomasSpinner.getSelectedItem().toString().equals("Btu/sec")) {
                        qThomas.setText(String.valueOf(Math.round(Calculations.CalculateBtuPerSec(qThomasDoub))));
                    }
                    resultLayout.setVisibility(View.VISIBLE);
                }
                catch (Exception ex) {
                    String error = "Please Fill the Empty Fields";
                    Toast.makeText(FlashOver.this, error, Toast.LENGTH_LONG).show();
                }
                mccaffreyQSpinner.setOnItemSelectedListener(
                        new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                if (mccaffreyQSpinner.getSelectedItem().toString().equals("kW"))
                                {
                                    qMccaffrey.setText(String.valueOf(Math.round(qMccaffreyDoub)));
                                }
                                else if (mccaffreyQSpinner.getSelectedItem().toString().equals("Btu/sec")) {
                                    qMccaffrey.setText(String.valueOf(Math.round(Calculations.CalculateBtuPerSec(qMccaffreyDoub))));
                                }
                            }

                            @Override
                            public void onNothingSelected(AdapterView<?> parent) {

                            }
                        }
                );
                babrauskasQSpinner.setOnItemSelectedListener(
                        new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                if (babrauskasQSpinner.getSelectedItem().toString().equals("kW"))
                                {
                                    qBabrauskas.setText(String.valueOf(Math.round(qBabrauskasDoub)));
                                }
                                else if (babrauskasQSpinner.getSelectedItem().toString().equals("Btu/sec")) {
                                    qBabrauskas.setText(String.valueOf(Math.round(Calculations.CalculateBtuPerSec(qBabrauskasDoub))));
                                }
                            }

                            @Override
                            public void onNothingSelected(AdapterView<?> parent) {

                            }
                        }
                );
                qThomasSpinner.setOnItemSelectedListener(
                        new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                if (qThomasSpinner.getSelectedItem().toString().equals("kW"))
                                {
                                    qThomas.setText(String.valueOf(Math.round(qThomasDoub)));
                                }
                                else if (qThomasSpinner.getSelectedItem().toString().equals("Btu/sec")) {
                                    qThomas.setText(String.valueOf(Math.round(Calculations.CalculateBtuPerSec(qThomasDoub))));
                                }
                            }

                            @Override
                            public void onNothingSelected(AdapterView<?> parent) {

                            }
                        }
                );
                //Toast.makeText(FlashOver.this, String.valueOf(qThomasDoub), Toast.LENGTH_LONG).show();
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