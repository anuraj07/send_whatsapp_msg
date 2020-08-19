package com.example.send_whatsapp_msg;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

public class AboutActivity extends AppCompatActivity {

    private Button github;
    private Switch darkMode;
    private AdView adView;


    public static final String MyPREFERENCES = "nightModePrefs";
    public static final String KEY_ISNIGHTMODE = "isNightMode";
    SharedPreferences sharedPreferences;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        github = (Button) findViewById(R.id.github);
        darkMode = (Switch) findViewById(R.id.dark_mode);
        adView = findViewById(R.id.adView);



        MobileAds.initialize(this,"ca-app-pub-1717124682197622~3794930388");
        AdRequest adRequest = new AdRequest.Builder().build();
        adView.loadAd(adRequest);


        sharedPreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);

        github.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openUrl("https://github.com/anuraj07");
            }
        });

        checkedNightModeActivated();

        darkMode.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (isChecked) {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                    saveNightModeState(true);
                    recreate();
                } else {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                    saveNightModeState(false);
                }
            }
        });

    }

    private void saveNightModeState(boolean nightMode) {

        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(KEY_ISNIGHTMODE, nightMode);
        editor.apply();

    }

    public void checkedNightModeActivated() {
        if(sharedPreferences.getBoolean(KEY_ISNIGHTMODE, false)) {
            darkMode.setChecked(true);
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        } else {
            darkMode.setChecked(false);
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }
    }

    public void openUrl(String url) {
        Uri uri = Uri.parse(url);
        Intent launchGit = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(launchGit);
    }
}