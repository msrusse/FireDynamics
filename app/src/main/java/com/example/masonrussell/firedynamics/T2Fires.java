package com.example.masonrussell.firedynamics;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class T2Fires extends AppCompatActivity {

    public ListView resultListView, timeListView;
    public LinearLayout resultLayout;
    public EditText t1Value, peakHrrValue, timeIntervalValue;
    public double t1Doub, peakHrrDoub, timeIntervalDoub, alphaDoub;
    public Button getResults;
    ArrayList<String> hrr = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_t2_fires);
        resultListView = findViewById(R.id.resultListView);
        timeListView = findViewById(R.id.timeListView);
        resultLayout = findViewById(R.id.resultLayout);
        t1Value = findViewById(R.id.t1Value);
        peakHrrValue = findViewById(R.id.peakHrrValue);
        timeIntervalValue = findViewById(R.id.timeIntervalValue);
        getResults = findViewById(R.id.getMeasurementsButton);

        getResults.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                t1Doub = Double.parseDouble(t1Value.getText().toString());
                peakHrrDoub = Double.parseDouble(peakHrrValue.getText().toString());
                timeIntervalDoub = Double.parseDouble(timeIntervalValue.getText().toString());
                alphaDoub = 1000/Math.pow(t1Doub,2);
                getValues();
                ArrayAdapter<String> adapter = new ArrayAdapter<>(T2Fires.this,android.R.layout.simple_list_item_1,hrr);
                resultListView.setAdapter(adapter);
            }
        });
    }

    void getValues()
    {
        final DecimalFormat twoDigits = new DecimalFormat("0.00");
        for (int i=0; i < 740; i += timeIntervalDoub)
        {
            if (i ==0)
            {
                hrr.add(String.valueOf(i) + ", " + String.valueOf(0));
            }
            else if (alphaDoub*Math.pow(i,2) > peakHrrDoub)
            {
                hrr.add(String.valueOf(i) + ", " + twoDigits.format(peakHrrDoub));
            }
            else
            {
                hrr.add(String.valueOf(i) + ", " +  twoDigits.format(alphaDoub*Math.pow(i,2)));
            }
        }
    }
}
