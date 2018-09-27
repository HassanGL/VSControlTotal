package com.example.usuario.vscontroltotal;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;

public class WebIn extends AppCompatActivity {
    EditText ws;
    String a;
    AlertDialog.Builder dialogo12;
    private config conf;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webin);
        conf = new config(this);
        ws= (EditText) findViewById(R.id.et_ws);
        getWindow().setStatusBarColor(getResources().getColor(R.color.gris));
        ws.setText(conf.getWebservice());
    }

    public void ac_ws(View view){

        a = ws.getText().toString();


            dialogo12 = new AlertDialog.Builder(WebIn.this);
            dialogo12.setView(R.layout.alert);
            dialogo12.setCancelable(false);
            final AlertDialog testDialog = dialogo12.create();
            testDialog.show();

            new Handler().postDelayed(new Runnable(){
                public void run(){
                    if(a.equals("a") || a.equals("b")) {
                        testDialog.dismiss();
                        dialogo12 = new AlertDialog.Builder(WebIn.this);
                        dialogo12.setTitle("Acceso correcto");
                        dialogo12.setMessage("La dirección del Webservice (" + a + ") ha sido correctamente verificada.");
                        LinearLayout linearLayoutQ = new LinearLayout(WebIn.this);
                        linearLayoutQ.setOrientation(LinearLayout.VERTICAL);
                        dialogo12.setView(linearLayoutQ);
                        dialogo12.setPositiveButton("Continuar", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialogo12, int id) {
                                conf.setWebservice(a);
                                Intent intent = new Intent(WebIn.this, LogIn.class);
                                startActivity(intent);
                            }
                        });
                        dialogo12.setCancelable(false);
                        final AlertDialog testDialog = dialogo12.create();
                        testDialog.show();
                    }
                    else{
                        testDialog.dismiss();
                        dialogo12 = new AlertDialog.Builder(WebIn.this);
                        dialogo12.setTitle("Acceso incorrecto");
                        dialogo12.setMessage("La conexión al Webservice no se ha podido realizar, asegurese de que lo haya ingresado adecuadamente.");
                        LinearLayout linearLayoutQ = new LinearLayout(WebIn.this);
                        linearLayoutQ.setOrientation(LinearLayout.VERTICAL);
                        dialogo12.setView(linearLayoutQ);
                        dialogo12.setPositiveButton("Cancelar", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialogo12, int id) {
                            dialogo12.dismiss();
                            }
                        });
                        dialogo12.setCancelable(false);
                        final AlertDialog testDialog = dialogo12.create();
                        testDialog.show();

                    }
                };
            }, 2000);


    }

    public void salir(View view){
        System.exit(0);
    }
}
