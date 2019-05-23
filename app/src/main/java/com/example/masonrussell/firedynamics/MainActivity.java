package com.example.masonrussell.firedynamics;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {

    LinearLayout flashoverLayout, hrrLayout, flameHeightLayout, t2FireLayout, radiationPoolLayout, conductionLayout, solidIgnitionLayout, openPipeLayout, gasConcentrationLayout, gasAmountLayout, oxygenLevelsLayout, selfHeatingLayout, tGasLayerLayout, about;
    SharedPreferences sharedPref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sharedPref = getSharedPreferences("eula",0);
        boolean eula_accepted = sharedPref.getBoolean("eula", false);
        if (!eula_accepted) {
            showAlert();
        }
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
        about = findViewById(R.id.about);

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
                Intent intent = new Intent(getApplicationContext(), GasConcentration.class);
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

        about.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), About.class);
                startActivity(intent);
            }
        });
    }

    void showAlert()
    {
        new AlertDialog.Builder(this)
                .setTitle(R.string.euala_title)
                .setMessage(R.string.eula)

                // Specifying a listener allows you to take an action before dismissing the dialog.
                // The dialog is automatically dismissed when a dialog button is clicked.
                .setPositiveButton(R.string.accept, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // Continue with delete operation
                        SharedPreferences.Editor editor = sharedPref.edit();
                        editor.putBoolean("eula", true);
                        editor.apply();
                    }
                })

                // A null listener allows the button to dismiss the dialog and take no further action.
                .setNegativeButton(R.string.decline, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                })
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }
}
