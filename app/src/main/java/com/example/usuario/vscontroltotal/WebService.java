package com.example.usuario.vscontroltotal;

import android.util.Log;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.SoapFault;
import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import java.util.EnumMap;

public class WebService {
    //Namespace of the Webservice - can be found in WSDL
    public static String NAMESPACE = "http://localhost/";
    //Webservice URL - WSDL File location
    public static String URL = "http://vsys.com.mx:14800/VSControlTotalWS2019/";//Make sure you changed IP address

    public static String InitSession(String tUsuario, String tPassword, int tEmpresa) {

        //SOAP Action URI again Namespace + Web method name
        String SOAP_ACTION = "http://localhost/Web_InitSession";
        //Method Name of the Webservice
        String METHOD_NAME = "Web_InitSession";

        String loginStatus = "";

        // Create request
        SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);
        // Create envelope
        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
                SoapEnvelope.VER11);
        //Set .NET type
        envelope.dotNet = true;

        // Create HTTP call object
        HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);

        // Property which holds input parameters
        PropertyInfo Login = new PropertyInfo();
        PropertyInfo Password = new PropertyInfo();
        PropertyInfo Empresa = new PropertyInfo();
        PropertyInfo SerialHardDisk = new PropertyInfo();
        PropertyInfo Process = new PropertyInfo();
        PropertyInfo LimpiarSesion = new PropertyInfo();

        //Set Password
        Login.setName("Login");
        //Set dataType
        Login.setValue(tUsuario);
        //Set dataType
        Login.setType(String.class);
        //Add the property to request object
        request.addProperty(Login);

        //Set Password
        Password.setName("Password");
        //Set dataType
        Password.setValue(tPassword);
        //Set dataType
        Password.setType(String.class);
        //Add the property to request object
        request.addProperty(Password);

        //Set Password
        Empresa.setName("Empresa");
        //Set dataType
        Empresa.setValue(tEmpresa);
        //Set dataType
        Empresa.setType(int.class);
        //Add the property to request object
        request.addProperty(Empresa);

        //Set Password
        SerialHardDisk.setName("SerialHardDisk");
        //Set dataType
        SerialHardDisk.setValue(null);
        //Set dataType
        SerialHardDisk.setType(String.class);
        //Add the property to request object
        request.addProperty(SerialHardDisk);

        //Set Password
        Process.setName("Process");
        //Set dataType
        Process.setValue(null);
        //Set dataType
        Process.setType(int.class);
        //Add the property to request object
        request.addProperty(Process);

        //Set Password
        LimpiarSesion.setName("LimpiarSesion");
        //Set dataType
        LimpiarSesion.setValue(true);
        //Set dataType
        LimpiarSesion.setType(boolean.class);
        //Add the property to request object
        request.addProperty(LimpiarSesion);

        // Set output SOAP object
        envelope.setOutputSoapObject(request);

        try {
            // Invoke web service
            androidHttpTransport.call(SOAP_ACTION, envelope);
            // Get the response
            SoapObject response = (SoapObject) envelope.getResponse();
            // Assign it to  boolean variable variable
            loginStatus = response.toString();

        } catch (Exception e) {
            //Assign Error Status true in static variable 'errored'
            LogIn.errored = true;
            e.printStackTrace();
            Log.e("Error", e.toString());
        }
        //Return booleam to calling object
        return loginStatus;
    }

    /*public static String EmpresasGeneral_GetEmpresas() throws SoapFault {
        //SOAP Action URI again Namespace + Web method name
        String SOAP_ACTION = "http://localhost/Web_EmpresasGeneral_GetEmpresas";
        //Method Name of the Webservice
        String METHOD_NAME = "Web_EmpresasGeneral_GetEmpresas";

        String empresasStatus = "";

        SoapObject respuesta = new SoapObject(NAMESPACE, METHOD_NAME);

        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.dotNet =  true;
        envelope.setOutputSoapObject(respuesta);

        HttpTransportSE TransporteHttp = new HttpTransportSE(URL);


        SoapObject resultado = (SoapObject) envelope.getResponse();
        SoapObject diffgram = (SoapObject) resultado.getProperty("diffgram");
        SoapObject newDataSet = (SoapObject) diffgram.getProperty("NewDataSet");

        // Create request
        SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);
        // Create envelope
        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
                SoapEnvelope.VER11);
        //Set .NET type
        envelope.dotNet = true;
        envelope.implicitTypes = true;

        envelope.setOutputSoapObject(request);
        // Create HTTP call object
        HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);

        try {
            // Invoke web service
            androidHttpTransport.call(SOAP_ACTION, envelope);
            // Get the response
            SoapObject response = (SoapObject) envelope.getResponse();
            // Assign it to  boolean variable variable
            empresasStatus = response.toString();
            //Log.e("Empresa: ", response.toString());

        } catch (Exception e) {
            //Assign Error Status true in static variable 'errored'
            LogIn.errored = true;
            e.printStackTrace();
            Log.e("Error", e.toString());
        }

        return empresasStatus;
    }*/
}