package com.example.masonrussell.firedynamics;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.GridLabelRenderer;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.DataPointInterface;
import com.jjoe64.graphview.series.LineGraphSeries;
import com.jjoe64.graphview.series.OnDataPointTapListener;
import com.jjoe64.graphview.series.Series;

import java.util.ArrayList;
import java.util.List;

public class GasConcentration extends AppCompatActivity {

    public Button getResultsButton;
    public LinearLayout resultsLayout;
    public Spinner leakageRateUnitSpinner, gasVolumeUnitSpinner;
    public EditText airchangesResult, leakageRateResult, gasVolumeResult, timestepResult;
    public double airchangeDoub, leakageRateDoub, gasVolumeDoub, timestepDoub, qaDoub;
    public String timestepUnits, leakageRateUnits, gasVolumeUnits;
    public GraphView resultsGraph;
    ArrayList<Double> gasConcentration = new ArrayList<>();
    ArrayList<Double> time = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainActivity(Boolean.FALSE);
    }

    void mainActivity(Boolean isRestarted) {
        setContentView(R.layout.activity_gas_concentation);
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        getResultsButton = findViewById(R.id.getResultsButton);
        resultsLayout = findViewById(R.id.resultLayout);
        leakageRateUnitSpinner = findViewById(R.id.leakageRateUnitSpinner);
        gasVolumeUnitSpinner = findViewById(R.id.gasVolumeUnitSpinner);
        airchangesResult = findViewById(R.id.airChangesValue);
        resultsGraph = findViewById(R.id.resultsGraph);
        addItemsOnVolumeSpinner(gasVolumeUnitSpinner);
        addItemsOnLeakageSpinner(leakageRateUnitSpinner);
        leakageRateResult = findViewById(R.id.leakageRateValue);
        gasVolumeResult = findViewById(R.id.gasVolumeValue);
        timestepResult = findViewById(R.id.timestepValue);
        resultsLayout.setVisibility(View.INVISIBLE);
        timestepUnits = "min";
        if (ValueClassStorage.gasConcentration != null && !isRestarted)
        {
            ValueClassStorage.GasConcentration gasConcentration = ValueClassStorage.gasConcentration;
            airchangeDoub = gasConcentration.airChangesDoub;
            leakageRateDoub = gasConcentration.leakageRateDoub;
            gasVolumeDoub = gasConcentration.gasFilledAreaVolumeDoub;
            timestepDoub = gasConcentration.timestepDoub;
            airchangesResult.setText(String.valueOf(airchangeDoub));
            leakageRateResult.setText(String.valueOf(leakageRateDoub));
            gasVolumeResult.setText(String.valueOf(gasVolumeDoub));
            timestepResult.setText(String.valueOf(timestepDoub*60));
            gasVolumeUnitSpinner.setSelection(4);
            leakageRateUnitSpinner.setSelection(2);
            getValues();
            resultsLayout.setVisibility(View.VISIBLE);
        }
        getResultsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(resultsLayout.getWindowToken(), 0);
                    airchangeDoub = Double.parseDouble(airchangesResult.getText().toString());
                    leakageRateDoub = Double.parseDouble(leakageRateResult.getText().toString());
                    gasVolumeDoub = Double.parseDouble(gasVolumeResult.getText().toString());
                    timestepDoub = Double.parseDouble(timestepResult.getText().toString());
                    timestepDoub = ValuesConverstions.timeToHours(timestepDoub, timestepUnits);
                    leakageRateUnits = leakageRateUnitSpinner.getSelectedItem().toString();
                    gasVolumeUnits = gasVolumeUnitSpinner.getSelectedItem().toString();
                    gasVolumeDoub = ValuesConverstions.toCubicMeters(gasVolumeDoub, gasVolumeUnits);
                    leakageRateDoub = ValuesConverstions.FlowtoMetersCubedPerHour(leakageRateDoub, leakageRateUnits);
                    getValues();
                    resultsLayout.setVisibility(View.VISIBLE);
                    ValueClassStorage.GasConcentration gasConcentration = new ValueClassStorage().new GasConcentration(airchangeDoub, leakageRateDoub, gasVolumeDoub, timestepDoub);
                    ValueClassStorage.gasConcentration = gasConcentration;
                }
                catch (Exception ex) {
                    String error = "Please Fill the Empty Fields";
                    Toast.makeText(GasConcentration.this, error, Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    void getValues()
    {
        setContentView(R.layout.activity_gas_concentation_graph);
        qaDoub = airchangeDoub * gasVolumeDoub;
        for (double i=0; i <= 8.3; i += timestepDoub)
        {
            if (i==0)
            {
                time.add(i);
                gasConcentration.add(0.0);
            }
            else
            {
                time.add(i);
                gasConcentration.add(100*(leakageRateDoub/(qaDoub+leakageRateDoub))*(1-Math.exp(-(qaDoub+leakageRateDoub)*i/gasVolumeDoub)));
            }
        }
        DataPoint[] dp = new DataPoint[gasConcentration.size()-1];
        for (int x=0; x<gasConcentration.size()-1; x++)
            {
                dp[x] = new DataPoint(time.get(x),gasConcentration.get(x));
            }
        LineGraphSeries<DataPoint> series = new LineGraphSeries<>(dp);
        series.setDrawDataPoints(true);
        series.setDataPointsRadius(10);
        resultsGraph.getViewport().setScalable(true);
        resultsGraph.getViewport().setScalableY(true);
        resultsGraph.getViewport().setXAxisBoundsManual(true);
        resultsGraph.getViewport().setMinX(time.get(0));
        resultsGraph.getViewport().setMaxX(time.get(time.size()-1));
        resultsGraph.getViewport().setYAxisBoundsManual(true);
        resultsGraph.getViewport().setMinY(gasConcentration.get(0));
        resultsGraph.getViewport().setMaxY(gasConcentration.get(gasConcentration.size()-1) + 1);
        GridLabelRenderer gridLabel = resultsGraph.getGridLabelRenderer();
        gridLabel.setHorizontalAxisTitle("Time [hours]");
        gridLabel.setVerticalAxisTitle("Gas Concentration [%]");
        resultsGraph.addSeries(series);
        series.setOnDataPointTapListener(new OnDataPointTapListener() {
            @Override
            public void onTap(Series series, DataPointInterface dataPoint) {
                Toast.makeText(GasConcentration.this, String.valueOf(dataPoint), Toast.LENGTH_LONG).show();
            }
        });
        Button back_button = findViewById(R.id.back_button);
        back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainActivity(Boolean.TRUE);
            }
        });
    }

    public void addItemsOnVolumeSpinner(Spinner spinnerToMake)
    {
        List<String> list = new ArrayList<>();
        list.add("ft^3");
        list.add("in^3");
        list.add("gallon US");
        list.add("liter");
        list.add("m^3");
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, list);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerToMake.setAdapter(dataAdapter);
    }

    public void addItemsOnLeakageSpinner(Spinner spinnerToMake)
    {
        List<String> list = new ArrayList<>();
        list.add("cfm");
        list.add("ft^3/sec");
        list.add("m^3/hr");
        list.add("m^3/sec");
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, list);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerToMake.setAdapter(dataAdapter);
    }
}
