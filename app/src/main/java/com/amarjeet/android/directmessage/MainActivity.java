package com.amarjeet.android.directmessage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.hbb20.CountryCodePicker;

public class MainActivity extends AppCompatActivity {
    Button send;
    EditText message,mobNum;
    CountryCodePicker mCountryCodePicker;
    String strMessage,strMobileNumber="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mobNum=(EditText)findViewById(R.id.mobNum);
        message=(EditText)findViewById(R.id.message);
        send=(Button)findViewById(R.id.send);
        mCountryCodePicker=findViewById(R.id.countryCode);

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                strMessage=message.getText().toString();
                strMobileNumber=mobNum.getText().toString();

                if(mobNum.getText().toString().isEmpty() && message.getText().toString().isEmpty()){
                    Toast.makeText(MainActivity.this,"Attention Required!\nEnter Mobile Number",
                            Toast.LENGTH_SHORT).show();
                } else {
                    mCountryCodePicker.registerCarrierNumberEditText(mobNum);
                    strMobileNumber=mCountryCodePicker.getFullNumber();
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://api.whatsapp.com/send?phone="
                            + strMobileNumber+"&text="+strMessage));
                    startActivity(intent);
                    mobNum.setText("");
                    message.setText("");
                }
            }
        });
    }
}
