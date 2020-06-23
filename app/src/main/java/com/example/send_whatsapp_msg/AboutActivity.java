package com.example.send_whatsapp_msg;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AboutActivity extends AppCompatActivity {

    private Button github;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        github = (Button) findViewById(R.id.github);
        github.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openUrl("https://github.com/anuraj07");
            }
        });

    }
    public void openUrl(String url) {
        Uri uri = Uri.parse(url);
        Intent launchGit = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(launchGit);
    }
}