package com.example.usuario.vscontroltotal;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class splash extends AppCompatActivity {

    private final int DURACION_SPLASH = 1200; // 3 segundos
config conf;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        conf = new config(this);

        setContentView(R.layout.activity_splash);
        getWindow().setStatusBarColor(getResources().getColor(R.color.gris));
        new Handler().postDelayed(new Runnable(){
            public void run(){

                if(conf.getWebservice().equals("")) {
                    Intent intent = new Intent(splash.this, WebIn.class);
                    startActivity(intent);
                    finish();
                }
                else
                {
                    Intent intent = new Intent(splash.this,LogIn.class);
                    startActivity(intent);
                    finish();
                }

            };
        }, DURACION_SPLASH);
    }
}
