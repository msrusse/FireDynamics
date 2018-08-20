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
    public String intLiningUnits, compWidthUnits, compLengthUnits, compHeightUnits, ventHeightUnits, ventWidthUnits, intLiningMaterial;
    public Button calculateButton;
    public LinearLayout resultLayout;
    public TextView qMccaffrey, qBabrauskas, qThomas;
    public List<String> materialsList = new ArrayList<>();

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
        if (ValueClassStorage.qFlashover != null)
        {
            ValueClassStorage.QFlashover flashover = ValueClassStorage.qFlashover;
            compWidth = flashover.compartmentWidth;
            compHeight = flashover.compartmentHeight;
            compLength = flashover.compartmentLength;
            ventHeight = flashover.ventHeight;
            ventWidth = flashover.ventWidth;
            intLining = flashover.interiorLiningThickness;
            intLiningMaterial = flashover.interiorLiningMaterial;
            compWidthText.setText(String.valueOf(compWidth));
            compHeightText.setText(String.valueOf(compHeight));
            compLengthText.setText(String.valueOf(compLength));
            ventHeightText.setText(String.valueOf(ventHeight));
            ventWidthText.setText(String.valueOf(ventWidth));
            intLiningText.setText(String.valueOf(intLining));
            materialSpinner.setSelection(materialsList.indexOf(flashover.interiorLiningMaterial));
            compWidthSpinner.setSelection(2);
            compHeightSpinner.setSelection(2);
            compLengthSpinner.setSelection(2);
            ventHeightSpinner.setSelection(2);
            ventWidthSpinner.setSelection(2);
            intLiningSpinner.setSelection(2);
            getResults();
        }

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
                    intLiningMaterial = materialSpinner.getSelectedItem().toString();
                    getResults();
                }
                catch (Exception ex) {
                    String error = "Please Fill the Empty Fields";
                    Toast.makeText(FlashOver.this, error, Toast.LENGTH_LONG).show();
                }
                //Toast.makeText(FlashOver.this, String.valueOf(qThomasDoub), Toast.LENGTH_LONG).show();
            }
        });
    }

    private void getResults()
    {
        thermalConductivity = ValuesConverstions.thermalConductivity(intLiningMaterial);
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
        ValueClassStorage.QFlashover qFlashover = new ValueClassStorage().new QFlashover(compWidth, compLength, compHeight, ventWidth, ventHeight, intLining, intLiningMaterial, qMccaffreyDoub, qBabrauskasDoub, qThomasDoub);
        resultLayout.setVisibility(View.VISIBLE);
        ValueClassStorage.qFlashover = qFlashover;

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

        materialsList.add("Aerated Concrete");
        materialsList.add("Alumina Silicate Block");
        materialsList.add("Aluminum (pure)");
        materialsList.add("Brick");
        materialsList.add("Brick/Concrete Block");
        materialsList.add("Calcium Silicate Board");
        materialsList.add("Chipboard");
        materialsList.add("Concrete");
        materialsList.add("Expended Polystyrene");
        materialsList.add("Fiber Insulation Board");
        materialsList.add("Glass Fiber Insulation");
        materialsList.add("Glass Plate");
        materialsList.add("Gypsum Board");
        materialsList.add("Plasterboard");
        materialsList.add("Plywood");
        materialsList.add("Steel (0.5% Carbon)");
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, materialsList);
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