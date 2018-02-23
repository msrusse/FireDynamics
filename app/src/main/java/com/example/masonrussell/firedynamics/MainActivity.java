package com.example.masonrussell.firedynamics;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    public Button flashover, hrr, flameHeight, t2Fire, radiationPool, conduction, solidIgnition, openPipe, gasConcentration, gasAmount, oxygenLevels, selfHeating, tGasLayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        flashover = findViewById(R.id.qflashoverButton);
        hrr = findViewById(R.id.hrrButton);
        flameHeight = findViewById(R.id.flameHeightButton);
        t2Fire = findViewById(R.id.t2FiresButton);
        radiationPool = findViewById(R.id.radiationPoolButton);
        conduction = findViewById(R.id.conductionButton);
        solidIgnition = findViewById(R.id.solidIgnitionButton);
        openPipe = findViewById(R.id.openPipeButton);
        gasConcentration = findViewById(R.id.gasConButton);
        gasAmount = findViewById(R.id.gasAmountButton);
        oxygenLevels = findViewById(R.id.oxygenLevelButton);
        selfHeating = findViewById(R.id.selfHeatingButton);
        tGasLayer = findViewById(R.id.tGasLayersButton);

        flashover.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                 Intent intent = new Intent(getApplicationContext(), FlashOver.class);
                 startActivity(intent);
            }
        });

        hrr.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(getApplicationContext(), HRR.class);
                startActivity(intent);
            }
        });

        flameHeight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), FlameHeight.class);
                startActivity(intent);
            }
        });

        radiationPool.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), RadiationPoolFire.class);
                startActivity(intent);
            }
        });

        openPipe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), OpenPipe.class);
                startActivity(intent);
            }
        });
    }
}
