package com.example.masonrussell.firedynamics;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.jjoe64.graphview.DefaultLabelFormatter;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.helper.StaticLabelsFormatter;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.DataPointInterface;
import com.jjoe64.graphview.series.LineGraphSeries;
import com.jjoe64.graphview.series.OnDataPointTapListener;
import com.jjoe64.graphview.series.Series;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class T2Fires extends AppCompatActivity {

    public LinearLayout resultLayout;
    public EditText t1Value, peakHrrValue, timeIntervalValue;
    public double t1Doub, peakHrrDoub, timeIntervalDoub, alphaDoub;
    public Button getResults;
    public GraphView resultsGraph;
    ArrayList<Double> hrr = new ArrayList<>();
    ArrayList<Double> time = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_t2_fires);
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        resultLayout = findViewById(R.id.resultLayout);
        t1Value = findViewById(R.id.t1Value);
        resultsGraph = findViewById(R.id.resultsGraph);
        peakHrrValue = findViewById(R.id.peakHrrValue);
        timeIntervalValue = findViewById(R.id.timeIntervalValue);
        getResults = findViewById(R.id.getMeasurementsButton);
        resultLayout.setVisibility(View.INVISIBLE);

        try {

            getResults.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (!TextUtils.isEmpty(t1Value.getText()) && !TextUtils.isEmpty(peakHrrValue.getText()) && !TextUtils.isEmpty(timeIntervalValue.getText())) {
                        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                        imm.hideSoftInputFromWindow(resultLayout.getWindowToken(), 0);
                        t1Doub = Double.parseDouble(t1Value.getText().toString());
                        peakHrrDoub = Double.parseDouble(peakHrrValue.getText().toString());
                        timeIntervalDoub = Double.parseDouble(timeIntervalValue.getText().toString());
                        alphaDoub = 1000 / Math.pow(t1Doub, 2);
                        ValueClassStorage.T2Fires t2Fires = new ValueClassStorage().new T2Fires(t1Doub, peakHrrDoub, timeIntervalDoub);
                        getValues();
                        resultLayout.setVisibility(View.VISIBLE);
                    } else {
                        String error = "Please Fill the Empty Fields";
                        Toast.makeText(T2Fires.this, error, Toast.LENGTH_LONG).show();
                    }
                }
            });
        }
        catch (Exception ex)
        {
            Log.d("T2Fires", ex.getMessage());
        }
    }

    void getValues()
    {
        int counter = 0;
        for (double i=0; i < 740; i += timeIntervalDoub)
        {
            if (i ==0)
            {
                time.add(i);
                hrr.add(0.0);
            }
            else if (alphaDoub*Math.pow(i,2) > peakHrrDoub)
            {
                if (counter == 10) {
                    break;
                }
                else {
                    time.add(i);
                    hrr.add(peakHrrDoub);
                    counter ++;
                }
            }
            else
            {
                    time.add(i);
                    hrr.add(alphaDoub * Math.pow(i, 2));
            }
        }
        DataPoint[] dp = new DataPoint[hrr.size()-1];
        for (int x=0; x<hrr.size()-1; x++)
        {
            dp[x] = new DataPoint(time.get(x),hrr.get(x));
        }
        resultsGraph.removeAllSeries();
        LineGraphSeries<DataPoint> series = new LineGraphSeries<>(dp);
        series.setDrawDataPoints(true);
        series.setDataPointsRadius(15);
        resultsGraph.getViewport().setScalable(true);
        resultsGraph.getViewport().setScalableY(true);
        resultsGraph.getViewport().setXAxisBoundsManual(true);
        resultsGraph.getViewport().setMinX(time.get(0));
        resultsGraph.getViewport().setMaxX(time.get(time.size()-1));
        resultsGraph.getViewport().setYAxisBoundsManual(true);
        resultsGraph.getViewport().setMinY(hrr.get(0));
        resultsGraph.getViewport().setMaxY(hrr.get(hrr.size()-1) + 1000);
        resultsGraph.addSeries(series);
        series.setOnDataPointTapListener(new OnDataPointTapListener() {
            @Override
            public void onTap(Series series, DataPointInterface dataPoint) {
                Toast.makeText(T2Fires.this, String.valueOf(dataPoint), Toast.LENGTH_LONG).show();
            }
        });
    }
}
