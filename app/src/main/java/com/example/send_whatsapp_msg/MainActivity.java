package com.example.send_whatsapp_msg;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.hbb20.CountryCodePicker;

public class MainActivity extends AppCompatActivity {

    private EditText mobileNumber, message;
    private Button send;
    private ImageView setting;
    private CountryCodePicker countryCodePicker;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mobileNumber = findViewById(R.id.mobile_no);
        message = findViewById(R.id.message);
        send = findViewById(R.id.send_btn);
//        countryCode = findViewById(R.id.country_code);
        setting = findViewById(R.id.setting);
        countryCodePicker = findViewById(R.id.select_country_code);





        setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, AboutActivity.class));
            }
        });

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String number = mobileNumber.getText().toString();
                String msg = message.getText().toString();
//                String code = countryCode.getText().toString();
                String fullPhoneNumber = countryCodePicker.getFullNumber() + number;


                boolean installed = appInstalledOrNot("com.whatsapp");
                if(installed) {

                    if (number.trim().isEmpty()) {
                        mobileNumber.setError("This field can't be empty");
                        mobileNumber.requestFocus();
                    } /*else if (code.trim().isEmpty()){
                        countryCode.setError("This field can't be empty");
                        countryCode.requestFocus();
                    }*/else {

                        String url = "https://api.whatsapp.com/send?phone="+fullPhoneNumber+"&text="+msg;
                        Intent i = new Intent(Intent.ACTION_VIEW);
                        i.setData(Uri.parse(url));
                        startActivity(i);

                    }

                } else {

                    Toast.makeText(MainActivity.this, "Whats App is not installed in your device", Toast.LENGTH_LONG).show();

                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            Intent intent = new Intent(Intent.ACTION_VIEW);
                            intent.setData(Uri.parse("https://play.google.com/store/apps/details?id=com.whatsapp"));
                            startActivity(intent);

                            finish();
                        }
                    },3000);

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