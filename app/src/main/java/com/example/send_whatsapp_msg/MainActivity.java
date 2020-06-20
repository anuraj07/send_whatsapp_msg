package com.example.send_whatsapp_msg;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText mobileNumber, message, countryCode;
    private Button send;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mobileNumber = findViewById(R.id.mobile_no);
        message = findViewById(R.id.message);
        send = findViewById(R.id.send_btn);
        countryCode = findViewById(R.id.country_code);

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String number = mobileNumber.getText().toString();
                String msg = message.getText().toString();
                String code = countryCode.getText().toString();


                boolean installed = appInstalledOrNot("com.whatsapp");
                if(installed) {

                    Toast.makeText(MainActivity.this, "Whats App is not installed in your device", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.setData(Uri.parse("https://play.google.com/store/apps/details?id=com.whatsapp"));
                    startActivity(intent);

                    /*if (number.trim().isEmpty()) {
                        mobileNumber.setError("This field can't be empty");
                        mobileNumber.requestFocus();
                    } else if (code.trim().isEmpty()){
                        countryCode.setError("This field can't be empty");
                        countryCode.requestFocus();
                    }else {

                        String url = "https://api.whatsapp.com/send?phone="+code+number.trim()+"&text="+msg;
                        Intent i = new Intent(Intent.ACTION_VIEW);
                        i.setData(Uri.parse(url));
                        startActivity(i);

                    }*/

                } else {

                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.setData(Uri.parse("https://play.google.com/store/apps/details?id=com.whatsapp"));
                    startActivity(intent);

                }



            }
        });

    }

    private boolean appInstalledOrNot(String uri) {
        PackageManager pm = getPackageManager();
        boolean app_installed;
        try {
            pm.getPackageInfo(uri, PackageManager.GET_ACTIVITIES);
            app_installed = true;
        }
        catch (PackageManager.NameNotFoundException e) {
            app_installed = false;
        }
        return app_installed;
    }
}