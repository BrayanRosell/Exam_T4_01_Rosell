package com.example.vargasrosell_t4;

import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class BasicLoginActivity extends AppCompatActivity {
    SharedPreferences mSharedPref;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basic_login);


        SharedPreferences mSharedPref = getSharedPreferences("com.example.vargasrosell_t4.SHARED_PREFERENCES",Context.MODE_PRIVATE);
        String token = mSharedPref.getString("com.example.vargasrosell_t4.TOKEN","");

        if(token != ""){
            Intent intent = new Intent(this,MainActivity.class);
            startActivity(intent);
            return;
        }
            EditText editUsername = findViewById(R.id.etxtUsuario);
            EditText editPassword = findViewById(R.id.etxtPassword);

            Button btnLogin = findViewById(R.id.btnLogin);

            btnLogin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String token = "MITOKENASDFGHJKL";
                    SharedPreferences.Editor prefEditor = mSharedPref.edit();
                    prefEditor.putString("com.example.vargasrosell_t4.TOKEN", token);
                    prefEditor.apply();
                    Log.i("APP_VJ20202", "El token es: " + token);
                    Intent intent = new Intent(BasicLoginActivity.this,MainActivity.class);
                    startActivity(intent);
                }
            });

    }
}