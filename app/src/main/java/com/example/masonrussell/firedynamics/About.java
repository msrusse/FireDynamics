package com.example.masonrussell.firedynamics;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class About extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        int version;

        try {
            version = this.getPackageManager().getPackageInfo(this.getPackageName(), 0).versionCode;
        }
        catch (Exception ex) {
            version = 1;
        }
        String versionText = getResources().getString(R.string.version) + " " + version;
        TextView versionView = findViewById(R.id.versionView);
        versionView.setText(versionText);
    }
}
