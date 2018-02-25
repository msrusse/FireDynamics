package com.example.masonrussell.firedynamics;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class GasConcentation extends AppCompatActivity {

    public Button getResultsButton;
    public LinearLayout resultsLayout;
    public Spinner leakageRateUnitSpinner, gasVolumeUnitSpinner;
    public EditText airchangesResult, leakageRateResult, gasVolumeResult, timestepResult;
    public double airchangeDoub, leakageRateDoub, gasVolumeDoub, timestepDoub, qaDoub;
    public ListView resultListView;
    public String timestepUnits, leakageRateUnits, gasVolumeUnits;
    ArrayList<String> gasConcentration = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gas_concentation);
        getResultsButton = findViewById(R.id.getResultsButton);
        resultsLayout = findViewById(R.id.resultLayout);
        leakageRateUnitSpinner = findViewById(R.id.leakageRateUnitSpinner);
        gasVolumeUnitSpinner = findViewById(R.id.gasVolumeUnitSpinner);
        airchangesResult = findViewById(R.id.airChangesValue);
        leakageRateResult = findViewById(R.id.leakageRateValue);
        gasVolumeResult = findViewById(R.id.gasVolumeValue);
        timestepResult = findViewById(R.id.timestepValue);
        resultsLayout.setVisibility(View.INVISIBLE);
        resultListView = findViewById(R.id.resultListView);
        timestepUnits = "min";
        getResultsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                airchangeDoub = Double.parseDouble(airchangesResult.getText().toString());
                leakageRateDoub = Double.parseDouble(leakageRateResult.getText().toString());
                gasVolumeDoub = Double.parseDouble(gasVolumeResult.getText().toString());
                timestepDoub = Double.parseDouble(timestepResult.getText().toString());
                timestepDoub = ValuesConverstions.timeToHours(timestepDoub, timestepUnits);
                getValues();
                ArrayAdapter<String> adapter = new ArrayAdapter<>(GasConcentation.this,android.R.layout.simple_list_item_1,gasConcentration);
                resultListView.setAdapter(adapter);
                resultsLayout.setVisibility(View.VISIBLE);
            }
        });
    }

    void getValues()
    {
        qaDoub = airchangeDoub * gasVolumeDoub;
        Toast.makeText(GasConcentation.this, String.valueOf(qaDoub), Toast.LENGTH_LONG).show();
        final DecimalFormat fourDigits = new DecimalFormat("0.0000");
        for (double i=0; i <= 8.3; i += timestepDoub)
        {
            if (i==0)
            {
                gasConcentration.add(String.valueOf(i) + ", " + "0");
            }
            else
            {
                gasConcentration.add(fourDigits.format(i) + ", " + String.valueOf(100*(leakageRateDoub/(qaDoub+leakageRateDoub))*(1-Math.exp(-(qaDoub+leakageRateDoub)*i/gasVolumeDoub))));
            }
        }
    }
}
