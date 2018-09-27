package com.example.usuario.vscontroltotal;

import android.content.Context;
import android.content.DialogInterface;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.app.AlertDialog;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class DetallesFac extends AppCompatActivity {
    LinearLayout layout;
    LinearLayout.LayoutParams layoutParams;
    TextView tit2;
    TextView tv_df1,tv_df2,tv_df3,tv_df4,tv_df5,tv_df6,tv_df7,tv_df8,tv_df9,tv_df10,tv_df11;
    EditText descriptionBox;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalles_fac);

        tit2 = (TextView) findViewById(R.id.tit2);

        String valor = getIntent().getExtras().getString("Folio");
        getWindow().setStatusBarColor(getResources().getColor(R.color.gris));
        layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);

        layoutParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);

        layoutParams.setMargins(60, 20, 60, 20);


        descriptionBox = new EditText(this);
        descriptionBox.setBackgroundColor(Color.WHITE);

        descriptionBox.setHint("Por favor,ingrese el motivo de esta acción");
 layout.addView(descriptionBox, layoutParams);

        if (valor.equals("A4088 (1-100)")) {

            tit2.setText(tit2.getText() + valor);

            tv_df1 = (TextView) findViewById(R.id.tv_df1);

            tv_df1.setText("05-08-2018");
            tv_df2 = (TextView) findViewById(R.id.tv_df2);
            tv_df2.setText("(1-127) Construcciones y proyectos");
            tv_df3 = (TextView) findViewById(R.id.tv_df3);
            tv_df3.setText("CONSTRUCCION_2018");
            tv_df4 = (TextView) findViewById(R.id.tv_df4);
            tv_df4.setText("FACTURA CORRESPONDIENTE AL FOLIO A4088");
            tv_df5 = (TextView) findViewById(R.id.tv_df5);
            tv_df5.setText("SJNP (06-08-2018");
            tv_df6 = (TextView) findViewById(R.id.tv_df6);
            tv_df6.setText("JFGP (07-08-2018)");
            tv_df7 = (TextView) findViewById(R.id.tv_df7);
            tv_df7.setText("1,800.00");
            tv_df8 = (TextView) findViewById(R.id.tv_df8);
            tv_df8.setText("288.00");
            tv_df9 = (TextView) findViewById(R.id.tv_df9);
            tv_df9.setText("          2,016.00");
            tv_df10 = (TextView) findViewById(R.id.tv_df10);
            tv_df10.setText("655539 (1-1068)");
            tv_df11 = (TextView) findViewById(R.id.tv_df11);
            tv_df11.setText("CFPR (05-08-2018)");

        } else if (valor.equals("F4567 (1-123)")) {
            tit2.setText(tit2.getText() + valor);

            tv_df1 = (TextView) findViewById(R.id.tv_df1);

            tv_df1.setText("04-08-2018");
            tv_df2 = (TextView) findViewById(R.id.tv_df2);
            tv_df2.setText("(1-158) EDIFICACIONES S.A. DE C.V.");
            tv_df3 = (TextView) findViewById(R.id.tv_df3);
            tv_df3.setText("CONSTRUCCION_2018");
            tv_df4 = (TextView) findViewById(R.id.tv_df4);
            tv_df4.setText("FACTURA CORRESPONDIENTE AL FOLIO F4567");
            tv_df5 = (TextView) findViewById(R.id.tv_df5);
            tv_df5.setText("RSC (06-08-2018");
            tv_df6 = (TextView) findViewById(R.id.tv_df6);
            tv_df6.setText("ALPM (07-08-2018)");
            tv_df7 = (TextView) findViewById(R.id.tv_df7);
            tv_df7.setText("111,760.00");
            tv_df8 = (TextView) findViewById(R.id.tv_df8);
            tv_df8.setText("17,881.60");
            tv_df9 = (TextView) findViewById(R.id.tv_df9);
            tv_df9.setText("          129,641.60");
            tv_df10 = (TextView) findViewById(R.id.tv_df10);
            tv_df10.setText("EST-06 (1-67)");
            tv_df11 = (TextView) findViewById(R.id.tv_df11);
            tv_df11.setText("CFPR (05-08-2018)");
        }
        else if (valor.equals( "1-135 (1055)"))
        { tit2.setText(tit2.getText() + valor);

            tv_df1 = (TextView) findViewById(R.id.tv_df1);

            tv_df1.setText("26-08-2018");
            tv_df2 = (TextView) findViewById(R.id.tv_df2);
            tv_df2.setText("(1055) CIMPULSORA");
            tv_df3 = (TextView) findViewById(R.id.tv_df3);
            tv_df3.setText("CIMPULSORA_2018");
            tv_df4 = (TextView) findViewById(R.id.tv_df4);
            tv_df4.setText("FACTURA CORRESPONDIENTE AL FOLIO 1-135");
            tv_df5 = (TextView) findViewById(R.id.tv_df5);
            tv_df5.setText("RSC (26-08-2018");
            tv_df6 = (TextView) findViewById(R.id.tv_df6);
            tv_df6.setText("ALPM (27-08-2018)");
            tv_df7 = (TextView) findViewById(R.id.tv_df7);
            tv_df7.setText("PI^11 - (.15 * PI^11)");
            tv_df8 = (TextView) findViewById(R.id.tv_df8);
            tv_df8.setText(".15 * PI^11");
            tv_df9 = (TextView) findViewById(R.id.tv_df9);
            tv_df9.setText("          PI^11");
            tv_df10 = (TextView) findViewById(R.id.tv_df10);
            tv_df10.setText("EST-26 (1-67)");
            tv_df11 = (TextView) findViewById(R.id.tv_df11);
            tv_df11.setText("CFPR (25-08-2018)");

        }
    }
    public void autorizar(View view)
    {InputMethodManager imm = (InputMethodManager)   getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0);
        if(layout.getParent()!=null)
            ((ViewGroup)layout.getParent()).removeView(layout);
        AlertDialog.Builder dialogo1 = new AlertDialog.Builder(this,R.style.MyDialogTheme);
        dialogo1.setView(layout);
        dialogo1.setTitle("Confirmación");
        dialogo1.setMessage("¿Está seguro de que desea AUTORIZAR esta factura?");
        dialogo1.setCancelable(false);
        dialogo1.setNegativeButton("Autorizar", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogo1, int id) {
                aceptar();
            }
        });
        dialogo1.setPositiveButton("Cancelar", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogo1, int id) {
          cancelar();
            }
        });
        dialogo1.show();
    }
    public void rechazar(View view)
    {InputMethodManager imm = (InputMethodManager)   getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0);
        if(layout.getParent()!=null)
            ((ViewGroup)layout.getParent()).removeView(layout);
        AlertDialog.Builder dialogo1 = new AlertDialog.Builder(this,R.style.MyDialogTheme);
        dialogo1.setTitle("Confirmación");
        dialogo1.setView(layout);
        dialogo1.setMessage("¿Está seguro de que desea RECHAZAR esta factura?");
        dialogo1.setCancelable(false);
        dialogo1.setNegativeButton("Rechazar", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogo1, int id) {
                rechazar();
            }
        });
        dialogo1.setPositiveButton("Cancelar", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogo1, int id) {
cancelar();
            }
        });
        dialogo1.show();
    }

    public void aceptar() {
        InputMethodManager imm = (InputMethodManager)   getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0);
        Toast t=Toast.makeText(this,"La factura ha sido autorizada", Toast.LENGTH_SHORT);
        t.show();
    }
    public void rechazar() {
        InputMethodManager imm = (InputMethodManager)   getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0);
        Toast t=Toast.makeText(this,"La factura ha sido rechazada", Toast.LENGTH_SHORT);
        t.show();
    }

    public void cancelar() {
        InputMethodManager imm = (InputMethodManager)   getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0);
    }

    public void bye ( View view){
        onBackPressed();
    }

    }



