package com.example.masonrussell.firedynamics;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {

    LinearLayout flashoverLayout, hrrLayout, flameHeightLayout, t2FireLayout, radiationPoolLayout, conductionLayout, solidIgnitionLayout, openPipeLayout, gasConcentrationLayout, gasAmountLayout, oxygenLevelsLayout, selfHeatingLayout, tGasLayerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        flashoverLayout = findViewById(R.id.qFlashoverLayout);
        hrrLayout = findViewById(R.id.hrrLayout);
        flameHeightLayout = findViewById(R.id.flameHeightLayout);
        t2FireLayout = findViewById(R.id.t2FiresLayout);
        radiationPoolLayout = findViewById(R.id.radiationPoolLayout);
        conductionLayout = findViewById(R.id.conductionLayout);
        solidIgnitionLayout = findViewById(R.id.solidIgnitionLayout);
        openPipeLayout = findViewById(R.id.openPipeLayout);
        gasConcentrationLayout = findViewById(R.id.gasConLayout);
        gasAmountLayout = findViewById(R.id.gasAmountLayout);
        oxygenLevelsLayout = findViewById(R.id.oxygenLevelLayout);
        selfHeatingLayout = findViewById(R.id.selfHeatingLayout);
        tGasLayerLayout = findViewById(R.id.tGasLayersLayout);

        flashoverLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), FlashOver.class);
                startActivity(intent);
            }
        });

        hrrLayout.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(getApplicationContext(), HRR.class);
                startActivity(intent);
            }
        });

        flameHeightLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), FlameHeight.class);
                startActivity(intent);
            }
        });

        radiationPoolLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), RadiationPoolFire.class);
                startActivity(intent);
            }
        });

        openPipeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), OpenPipe.class);
                startActivity(intent);
            }
        });

        t2FireLayout.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(getApplicationContext(), T2Fires.class);
                startActivity(intent);
            }
        });

        conductionLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Conduction.class);
                startActivity(intent);
            }
        });

        gasConcentrationLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), GasConcentation.class);
                startActivity(intent);
            }
        });

        solidIgnitionLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), SolidIgnition.class);
                startActivity(intent);
            }
        });

        tGasLayerLayout.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v)
                {
                    Intent intent = new Intent(getApplicationContext(), TGasLayer.class);
                    startActivity(intent);
                }
        });

        oxygenLevelsLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), OxygenLevels.class);
                startActivity(intent);
            }
        });

        selfHeatingLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), SelfHeating.class);
                startActivity(intent);
            }
        });

        gasAmountLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), GasAmount.class);
                startActivity(intent);
            }
        });
    }
}
