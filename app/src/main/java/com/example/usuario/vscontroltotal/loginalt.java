package com.example.usuario.vscontroltotal;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class loginalt extends AppCompatActivity {
    Spinner spinner;
    TextView tv;
    EditText et,et1;
    AlertDialog.Builder dialogo12;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loginalt);
        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        String[] letra = {"A","B","C","D","E"};

        et = (EditText) findViewById(R.id.et_cont);
        et1 = (EditText) findViewById(R.id.et_usu);


        ArrayAdapter adapter = new ArrayAdapter<String>(this,R.layout.spinner_item, letra);
        adapter.setDropDownViewResource(R.layout.spinner_dropdown_item);
        spinner.setAdapter(adapter);
    }

    public void Log(View view)
    {
        if(et.getText().toString().equals("a") && et1.getText().toString().equals("a")) {

            dialogo12 = new AlertDialog.Builder(loginalt.this);
            dialogo12.setView(R.layout.alert);
            dialogo12.setCancelable(false);
            final AlertDialog testDialog = dialogo12.create();
            testDialog.show();

            new Handler().postDelayed(new Runnable(){
                public void run(){
                    testDialog.dismiss();
                    Intent intent = new Intent(loginalt.this, Facturas.class);
                    startActivity(intent);

                };
            }, 2000);

        }
        else
        {
            Toast.makeText(this,"Usuario y/o contraseña incorrectos",Toast.LENGTH_SHORT).show();
        }

    } public void con(View view){
        Intent intent = new Intent(this,LogIn.class);
        startActivity(intent);
    }
}
