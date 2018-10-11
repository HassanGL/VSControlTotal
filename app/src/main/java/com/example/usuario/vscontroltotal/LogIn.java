package com.example.usuario.vscontroltotal;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONObject;
import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.sql.DataTruncation;

public class LogIn extends AppCompatActivity {
    //Namespace of the Webservice - can be found in WSDL
    public static String NAMESPACE = "http://localhost/";
    //Webservice URL - WSDL File location
    public static String URL = "http://vsys.com.mx:14800/VSControlTotalWS2019/";//Make sure you changed IP address
    //SOAP Action URI again Namespace + Web method name
    String SOAP_ACTION = "http://localhost/Web_EmpresasGeneral_GetEmpresas";
    //Method Name of the Webservice
    String METHOD_NAME = "Web_EmpresasGeneral_GetEmpresas";

    static boolean errored = false;

    Spinner spinner;
    EditText et,et1;
    TextView prueba;
    AlertDialog.Builder dialogo12;
    private ProgressDialog dialogo;

    String loginStatus;
    String editTextUsername;
    String editTextPassword;
    int Compañia;
    private String [] DatosEmpresas;
    private String [] DatosID;
    private String [] DatosBD;
    int intEmpresa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loginalt);

        getWindow().setStatusBarColor(getResources().getColor(R.color.gris));
        et = (EditText) findViewById(R.id.et_cont);
        et1 = (EditText) findViewById(R.id.et_usu);
        prueba = findViewById(R.id.pruena);

        spinner = (Spinner) findViewById(R.id.spinner);

        new AsynEmpresas().execute();

    }

    public Boolean EmpresasGeneral_GetEmpresas(){
        Boolean error = true;

        try{
            SoapObject respuesta = new SoapObject(NAMESPACE, METHOD_NAME);

            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
            envelope.dotNet =  true;
            envelope.setOutputSoapObject(respuesta);

            HttpTransportSE TransporteHttp = new HttpTransportSE(URL);
            TransporteHttp.call(SOAP_ACTION, envelope);

            SoapObject resultado = (SoapObject) envelope.getResponse();
            SoapObject diffgram = (SoapObject) resultado.getProperty("diffgram");
            SoapObject newDataSet = (SoapObject) diffgram.getProperty("NewDataSet");

            Log.e("Valor de Resultado", resultado.toString());
            Log.e("Valor de Diffgram", diffgram.toString());
            Log.e("Valor de newDataSet", newDataSet.toString());

            DatosID = new String[newDataSet.getPropertyCount()];
            DatosEmpresas = new String[newDataSet.getPropertyCount()];
            DatosBD = new String[newDataSet.getPropertyCount()];

            int ID = 0, Empresas = 0, BD = 0;

            for(int i = 0; i < newDataSet.getPropertyCount() ; i++){
                SoapObject DatosXML = (SoapObject) newDataSet.getProperty(i);
                Log.e("Valor de ID", DatosXML.getProperty(0).toString());
                Log.e("Valor de Empresa", DatosXML.getProperty(1).toString());
                Log.e("Valor de Empresa Corta", DatosXML.getProperty(2).toString());
                Log.e("Valor de Base de Datos", DatosXML.getProperty(3).toString());
                Log.e("Valor de Fecha Creacion", DatosXML.getProperty(4).toString());
                Log.e("Valor de UsuarioCreador", DatosXML.getProperty(5).toString());

                DatosID[ID] = DatosXML.getProperty(0).toString();
                DatosEmpresas[Empresas] = DatosXML.getProperty(1).toString();
                DatosBD[BD] = DatosXML.getProperty(3).toString();


                ID += 1 ; Empresas += 1; BD += 1 ;
            }

        }
        catch (IOException e){
            e.printStackTrace();
            error = false;
        }
        catch (XmlPullParserException e){
            e.printStackTrace();
            error = false;
        }
        return error;
    }


    public void Log(View view)
    {
        editTextUsername = et1.getText().toString();
        editTextPassword = et.getText().toString();
        Compañia = spinner.getSelectedItemPosition();

        switch (Compañia){
            case 0:
                intEmpresa = Integer.parseInt(DatosID[0]);
                break;
            case 1:
                intEmpresa = Integer.parseInt(DatosID[1]);
                break;
            case 2:
                intEmpresa = Integer.parseInt(DatosID[2]);
                break;
            case 3:
                intEmpresa = Integer.parseInt(DatosID[3]);
                break;
            case 4:
                intEmpresa = Integer.parseInt(DatosID[4]);
                break;
            case 5:
                intEmpresa = Integer.parseInt(DatosID[5]);
                break;
            case 6:
                intEmpresa = Integer.parseInt(DatosID[6]);
                break;
        }

        //editTextComapy = spinner.getSelectedItem().toString();
        //Create instance for AsyncCallWS
        InitSession task = new InitSession();
        //Call execute
        task.execute();

        dialogo12 = new AlertDialog.Builder(LogIn.this);
        dialogo12.setView(R.layout.alert);
        dialogo12.setCancelable(false);
        final AlertDialog testDialog = dialogo12.create();
        testDialog.show();

        new Handler().postDelayed(new Runnable(){
            public void run(){

                testDialog.dismiss();
            };
        }, 3000);

    }
    /*public void con(View view){
        if (errored != true){
            Intent intent = new Intent(this,WebIn.class);
            startActivity(intent);
        }
        else {
            Toast.makeText(LogIn.this,loginStatus,Toast.LENGTH_SHORT).show();
        }

    }*/

    private class InitSession extends AsyncTask<String, Void, Void> {
        @Override
        protected Void doInBackground(String... params) {
            //Call Web Method
            loginStatus = WebService.InitSession(editTextUsername, editTextPassword,
                    intEmpresa);


            //Leer el valor Code de la cadena loginStatus y Message

            return null;
        }

        @Override
        //Once WebService returns response
        protected void onPostExecute(Void result) {

            Intent Facturas =  new Intent(LogIn.this, Facturas.class);

            if (errored != false){
                //Error Message
                Toast.makeText(LogIn.this,loginStatus,Toast.LENGTH_SHORT).show();
                Toast.makeText(LogIn.this, String.valueOf(intEmpresa), Toast.LENGTH_LONG).show();
                prueba.setText(loginStatus);
            }else{
                //Connection Message
                Toast.makeText(LogIn.this,loginStatus,Toast.LENGTH_SHORT).show();
                Toast.makeText(LogIn.this, String.valueOf(intEmpresa), Toast.LENGTH_LONG).show();
                //startActivity(Facturas);
            }
        }

    }

    public void Cargar_Elemenos(){
        ArrayAdapter<String> adaptador = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, DatosEmpresas);
        spinner.setAdapter(adaptador);
    }

    class AsynEmpresas extends AsyncTask<String, String, String>{

        protected void onPreExecute(){
            //super.onPreExecute();

            dialogo = new ProgressDialog(LogIn.this);
            dialogo.setMessage("Cargando Datos...");
            dialogo.setIndeterminate(false);
            dialogo.setCancelable(false);
            dialogo.show();
        }
        @Override
        protected String doInBackground(String... params) {
            if (EmpresasGeneral_GetEmpresas()){
                return "ok";
            }else{
                return "error";
            }
        }

        @Override
        protected void onPostExecute(String s) {
            //super.onPostExecute(s);
            dialogo.dismiss();
            if (s.equals("ok")){
                Toast.makeText(LogIn.this,"Ok",Toast.LENGTH_SHORT).show();
                Cargar_Elemenos();
            }else{
                Toast.makeText(LogIn.this,"Fallo",Toast.LENGTH_SHORT).show();
            }
        }

    }
}
