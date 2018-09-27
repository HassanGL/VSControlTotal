package com.example.usuario.vscontroltotal;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.app.AlertDialog;
import android.graphics.PorterDuff;
import android.graphics.Typeface;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.webkit.WebView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;

import android.widget.ExpandableListView;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.DefaultValueFormatter;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.ColorTemplate;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Facturas extends AppCompatActivity   implements NavigationView.OnNavigationItemSelectedListener {


    ExpandableListAdapter expandableListAdapter;
    ExpandableListView expandableListView;
    List<MenuModel> headerList = new ArrayList<>();
    HashMap<MenuModel, List<MenuModel>> childList = new HashMap<>();

    TableLayout table;
    LinearLayout layout,lay2,lla,linearLayout,llb;
    int i = 0;
    ListView listView;

    int as;
    PieChart pieChart;

    ProgressBar progressBar;
    NavigationView navigationView;
    Toolbar appbar;
    AlertDialog.Builder dialogo1,dialogo12;
    EditText descriptionBox;
    DrawerLayout drawerLayout;
    int bs;



    LinearLayout.LayoutParams layoutParams;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_facturasal);
        navigationView = findViewById(R.id.list_view);
        expandableListView = findViewById(R.id.expandableListView);
        prepareMenuData();
        pieChart = (PieChart) findViewById(R.id.piechart);
        pieChart.setUsePercentValues(true);
        getWindow().setStatusBarColor(getResources().getColor(R.color.gris));

        populateExpandableList();
        navigationView.setNavigationItemSelectedListener(this);

        as = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 30, getResources().getDisplayMetrics());

        bs = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 10, getResources().getDisplayMetrics());
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("VS Control Total");


        layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);

        layoutParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);

        layoutParams.setMargins(60, 20, 60, 20);


        descriptionBox = new EditText(this);

        descriptionBox.setHint("Por favor,ingrese el motivo de esta acción");
        descriptionBox.setBackgroundColor(Color.WHITE);
        layout.addView(descriptionBox, layoutParams);

        dialogo1 = new AlertDialog.Builder(this,R.style.MyDialogTheme);
        dialogo1.setTitle("Confirmación");

        dialogo1.setMessage("¿Está seguro de que desea RECHAZAR esta factura?");
        dialogo1.setCancelable(false);

        dialogo1.setPositiveButton("Cancelar", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogo1, int id) {
                cancelar();
            }
        });




        getSupportActionBar().setHomeAsUpIndicator(R.mipmap.ic_ast);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ImageView iv = new ImageView(this);
        iv.setImageResource(R.drawable.logocontrol);

        TextView tv1 = new TextView(this);
        tv1.setText("VS Control Total");

        LinearLayout lin1 = new LinearLayout(this);
        lin1.setOrientation(LinearLayout.HORIZONTAL);
        lin1.addView(iv);
        lin1.addView(tv1);



         drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);


       int NUM_COLS=6;
      int NUM_ROWS=2;

      cuadro1();
      cuadro3();
      cuadro2();



        LinearLayout linearLayout1 = new LinearLayout(this);
        linearLayout1.setOrientation(LinearLayout.HORIZONTAL);
        linearLayout1.setGravity(Gravity.RIGHT);

        ImageView but12 = new ImageView(this);
        but12.setImageResource(R.drawable.corr);
        but12.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                preg2();
            }
        });

        ImageView but13 = new ImageView(this);
        but13.setImageResource(R.drawable.incorr);
        but13.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                preg();
            }
        });
        ImageView sp2 = new ImageView(this);
        sp2.setImageResource(R.drawable.space);



        linearLayout1.addView(but12);
        linearLayout1.addView(sp2);
        linearLayout1.addView(but13);


        but12.setLayoutParams(new LinearLayout.LayoutParams(75, 75));
        sp2.setLayoutParams(new LinearLayout.LayoutParams(35,75));
        but13.setLayoutParams(new LinearLayout.LayoutParams(75, 75));


    }

    public void cuadro1(){
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        params.setMargins(0, 30, 0, 0);


        LinearLayout linearLayouta = new LinearLayout(this);
        linearLayouta.setOrientation(LinearLayout.VERTICAL);
        linearLayouta.setBackgroundResource(R.drawable.border4);
        linearLayouta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog2(v);
            }
        });
linearLayouta.setLayoutParams(params);


        LinearLayout linearLayoutb = new LinearLayout(this);
        linearLayoutb.setOrientation(LinearLayout.VERTICAL);
        linearLayoutb.setBackgroundResource(R.drawable.titback);

        TextView textViewa = new TextView(this);
        textViewa.setText("  Factura: 1-100 (A4088)");
        textViewa.setTextAppearance(R.style.Estilo7);

        linearLayoutb.addView(textViewa);
        linearLayouta.addView(linearLayoutb);


        llb = new LinearLayout(this);
        llb.setOrientation(LinearLayout.HORIZONTAL);

        LinearLayout llc = new LinearLayout(this);
        llc.setOrientation(LinearLayout.VERTICAL);


        LinearLayout ll_2 = new LinearLayout(this);
        ll_2.setOrientation(LinearLayout.HORIZONTAL);

        TextView tv_1 = new TextView(this);
        tv_1.setText("  Fecha: ");
        tv_1.setTextAppearance(R.style.Estilo7);

        TextView tv_2 = new TextView(this);
        tv_2.setText("09-08-2018");
        tv_2.setTextAppearance(R.style.Estilo2);

        ll_2.addView(tv_1);
        ll_2.addView(tv_2);
//-
        LinearLayout ll_3 = new LinearLayout(this);
        ll_3.setOrientation(LinearLayout.HORIZONTAL);

        TextView tv_3 = new TextView(this);
        tv_3.setText("  Proveedor: ");
        tv_3.setTextAppearance(R.style.Estilo7);

        TextView tv_4 = new TextView(this);
        tv_4.setText("Construcciones y proyectos");
        tv_4.setTextAppearance(R.style.Estilo2);

        ll_3.addView(tv_3);
        ll_3.addView(tv_4);
//-
        LinearLayout ll_4 = new LinearLayout(this);
        ll_4.setOrientation(LinearLayout.HORIZONTAL);

        TextView tv_5 = new TextView(this);
        tv_5.setText("  Importe total: ");
        tv_5.setTextAppearance(R.style.Estilo7);

        TextView tv_6 = new TextView(this);
        tv_6.setText("188,016.00");
        tv_6.setTextAppearance(R.style.Estilo2);

        ll_4.addView(tv_5);
        ll_4.addView(tv_6);

        llc.addView(ll_2);
        llc.addView(ll_3);
        llc.addView(ll_4);
        llb.addView(llc);





        lla = (LinearLayout) findViewById(R.id.ll1);
        lla.addView(linearLayouta);


        LinearLayout linearLayoutQ = new LinearLayout(this);
        linearLayoutQ.setOrientation(LinearLayout.VERTICAL);
        linearLayoutQ.setGravity(Gravity.RIGHT);

        LinearLayout linearLayout = new LinearLayout(this);
        linearLayout.setOrientation(LinearLayout.HORIZONTAL);
        linearLayout.setGravity(Gravity.RIGHT);


        ImageView but = new ImageView(this);
        but.setImageResource(R.drawable.corr);
        but.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                preg2();
            }
        });

        ImageView but1 = new ImageView(this);
        but1.setImageResource(R.drawable.incorr);
        but1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                preg();
            }
        });

        ImageView sp = new ImageView(this);
        sp.setImageResource(R.drawable.space);

        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);

        layoutParams.setMargins(2, 2, 2, 2);

        ImageView spa = new ImageView(this);
        spa.setImageResource(R.drawable.space);

        linearLayout.addView(but,layoutParams);
        linearLayout.addView(sp);
        linearLayout.addView(but1,layoutParams);
        linearLayout.addView(spa);
        spa.setLayoutParams(new LinearLayout.LayoutParams(bs,as));
        but.setLayoutParams(new LinearLayout.LayoutParams(as, as));
        sp.setLayoutParams(new LinearLayout.LayoutParams(bs,as));
        but1.setLayoutParams(new LinearLayout.LayoutParams(as, as));

        LinearLayout linearLayout2 = new LinearLayout(this);
        linearLayout2.setOrientation(LinearLayout.HORIZONTAL);
        linearLayout2.setGravity(Gravity.RIGHT);

        linearLayout2.setLayoutParams(new LinearLayout.LayoutParams(392,20));

        linearLayoutQ.addView(linearLayout2);
        linearLayoutQ.addView(linearLayout);

        llb.addView(linearLayoutQ);
        linearLayouta.addView(llb);

    }
    public void cuadro2(){

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        params.setMargins(0, 30, 0, 0);

        LinearLayout linearLayouta = new LinearLayout(this);
        linearLayouta.setOrientation(LinearLayout.VERTICAL);
        linearLayouta.setBackgroundResource(R.drawable.border4);
        linearLayouta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog3(v);
            }
        });
        linearLayouta.setLayoutParams(params);



        LinearLayout linearLayoutb = new LinearLayout(this);
        linearLayoutb.setOrientation(LinearLayout.VERTICAL);
        linearLayoutb.setBackgroundResource(R.drawable.titback);

        TextView textViewa = new TextView(this);
        textViewa.setText("  Factura: 1-135 (1055)");
        textViewa.setTextAppearance(R.style.Estilo7);

        linearLayoutb.addView(textViewa);
        linearLayouta.addView(linearLayoutb);


        llb = new LinearLayout(this);
        llb.setOrientation(LinearLayout.HORIZONTAL);

        LinearLayout llc = new LinearLayout(this);
        llc.setOrientation(LinearLayout.VERTICAL);


        LinearLayout ll_2 = new LinearLayout(this);
        ll_2.setOrientation(LinearLayout.HORIZONTAL);

        TextView tv_1 = new TextView(this);
        tv_1.setText("  Fecha: ");
        tv_1.setTextAppearance(R.style.Estilo7);

        TextView tv_2 = new TextView(this);
        tv_2.setText("26-08-2018");
        tv_2.setTextAppearance(R.style.Estilo2);

        ll_2.addView(tv_1);
        ll_2.addView(tv_2);
//-
        LinearLayout ll_3 = new LinearLayout(this);
        ll_3.setOrientation(LinearLayout.HORIZONTAL);

        TextView tv_3 = new TextView(this);
        tv_3.setText("  Proveedor: ");
        tv_3.setTextAppearance(R.style.Estilo7);

        TextView tv_4 = new TextView(this);
        tv_4.setText("Cimpulsora                            ");
        tv_4.setTextAppearance(R.style.Estilo2);

        ll_3.addView(tv_3);
        ll_3.addView(tv_4);
//-
        LinearLayout ll_4 = new LinearLayout(this);
        ll_4.setOrientation(LinearLayout.HORIZONTAL);

        TextView tv_5 = new TextView(this);
        tv_5.setText("  Importe total: ");
        tv_5.setTextAppearance(R.style.Estilo7);

        TextView tv_6 = new TextView(this);
        tv_6.setText("pi^11.00");
        tv_6.setTextAppearance(R.style.Estilo2);

        ll_4.addView(tv_5);
        ll_4.addView(tv_6);

        llc.addView(ll_2);
        llc.addView(ll_3);
        llc.addView(ll_4);
        llb.addView(llc);





        lla = (LinearLayout) findViewById(R.id.ll1);
        lla.addView(linearLayouta);


        LinearLayout linearLayoutQ = new LinearLayout(this);
        linearLayoutQ.setOrientation(LinearLayout.VERTICAL);
        linearLayoutQ.setGravity(Gravity.RIGHT);

        LinearLayout linearLayout = new LinearLayout(this);
        linearLayout.setOrientation(LinearLayout.HORIZONTAL);
        linearLayout.setGravity(Gravity.RIGHT);


        ImageView but = new ImageView(this);
        but.setImageResource(R.drawable.corr);
        but.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                preg2();
            }
        });

        ImageView but1 = new ImageView(this);
        but1.setImageResource(R.drawable.incorr);
        but1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                preg();
            }
        });

        ImageView sp = new ImageView(this);
        sp.setImageResource(R.drawable.space);

        ImageView spa = new ImageView(this);
        spa.setImageResource(R.drawable.space);

        linearLayout.addView(but);
        linearLayout.addView(sp);
        linearLayout.addView(but1);
        linearLayout.addView(spa);
        spa.setLayoutParams(new LinearLayout.LayoutParams(bs,as));
        but.setLayoutParams(new LinearLayout.LayoutParams(as, as));
        sp.setLayoutParams(new LinearLayout.LayoutParams(bs,as));
        but1.setLayoutParams(new LinearLayout.LayoutParams(as, as));


        LinearLayout linearLayout2 = new LinearLayout(this);
        linearLayout2.setOrientation(LinearLayout.HORIZONTAL);
        linearLayout2.setGravity(Gravity.RIGHT);

        linearLayout2.setLayoutParams(new LinearLayout.LayoutParams(392,20));

        linearLayoutQ.addView(linearLayout2);
        linearLayoutQ.addView(linearLayout);

        llb.addView(linearLayoutQ);
        linearLayouta.addView(llb);

    }
    public void cuadro3(){
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        params.setMargins(0, 30, 0, 0);

        LinearLayout linearLayouta = new LinearLayout(this);
        linearLayouta.setOrientation(LinearLayout.VERTICAL);
        linearLayouta.setBackgroundResource(R.drawable.border4);
        linearLayouta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog(v);
            }
        });
        linearLayouta.setLayoutParams(params);

        LinearLayout linearLayoutb = new LinearLayout(this);
        linearLayoutb.setOrientation(LinearLayout.VERTICAL);
        linearLayoutb.setBackgroundResource(R.drawable.titback);

        TextView textViewa = new TextView(this);
        textViewa.setText("  Factura: 1-123 (F4567)");
        textViewa.setTextAppearance(R.style.Estilo7);

        linearLayoutb.addView(textViewa);
        linearLayouta.addView(linearLayoutb);


        llb = new LinearLayout(this);
        llb.setOrientation(LinearLayout.HORIZONTAL);

        LinearLayout llc = new LinearLayout(this);
        llc.setOrientation(LinearLayout.VERTICAL);


        LinearLayout ll_2 = new LinearLayout(this);
        ll_2.setOrientation(LinearLayout.HORIZONTAL);

        TextView tv_1 = new TextView(this);
        tv_1.setText("  Fecha: ");
        tv_1.setTextAppearance(R.style.Estilo7);

        TextView tv_2 = new TextView(this);
        tv_2.setText("20-08-2018");
        tv_2.setTextAppearance(R.style.Estilo2);

        ll_2.addView(tv_1);
        ll_2.addView(tv_2);
//-
        LinearLayout ll_3 = new LinearLayout(this);
        ll_3.setOrientation(LinearLayout.HORIZONTAL);

        TextView tv_3 = new TextView(this);
        tv_3.setText("  Proveedor: ");
        tv_3.setTextAppearance(R.style.Estilo7);

        TextView tv_4 = new TextView(this);
        tv_4.setText("Edificaciones S.A. de C.V.     ");
        tv_4.setTextAppearance(R.style.Estilo2);

        ll_3.addView(tv_3);
        ll_3.addView(tv_4);
//-
        LinearLayout ll_4 = new LinearLayout(this);
        ll_4.setOrientation(LinearLayout.HORIZONTAL);

        TextView tv_5 = new TextView(this);
        tv_5.setText("  Importe total: ");
        tv_5.setTextAppearance(R.style.Estilo7);

        TextView tv_6 = new TextView(this);
        tv_6.setText("129,641.60");
        tv_6.setTextAppearance(R.style.Estilo2);

        ll_4.addView(tv_5);
        ll_4.addView(tv_6);

        llc.addView(ll_2);
        llc.addView(ll_3);
        llc.addView(ll_4);
        llb.addView(llc);





        lla = (LinearLayout) findViewById(R.id.ll1);
        lla.addView(linearLayouta);


        LinearLayout linearLayoutQ = new LinearLayout(this);
        linearLayoutQ.setOrientation(LinearLayout.VERTICAL);
        linearLayoutQ.setGravity(Gravity.RIGHT);

        LinearLayout linearLayout = new LinearLayout(this);
        linearLayout.setOrientation(LinearLayout.HORIZONTAL);
        linearLayout.setGravity(Gravity.RIGHT);


        ImageView but = new ImageView(this);
        but.setImageResource(R.drawable.corr);
        but.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                preg2();
            }
        });

        ImageView but1 = new ImageView(this);
        but1.setImageResource(R.drawable.incorr);
        but1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                preg();
            }
        });

        ImageView sp = new ImageView(this);
        sp.setImageResource(R.drawable.space);

        ImageView spa = new ImageView(this);
        spa.setImageResource(R.drawable.space);
        linearLayout.addView(but);
        linearLayout.addView(sp);
        linearLayout.addView(but1);
        linearLayout.addView(spa);
        spa.setLayoutParams(new LinearLayout.LayoutParams(bs,as));
        but.setLayoutParams(new LinearLayout.LayoutParams(as, as));
        sp.setLayoutParams(new LinearLayout.LayoutParams(bs,as));
        but1.setLayoutParams(new LinearLayout.LayoutParams(as, as));

        LinearLayout linearLayout2 = new LinearLayout(this);
        linearLayout2.setOrientation(LinearLayout.HORIZONTAL);
        linearLayout2.setGravity(Gravity.RIGHT);

        linearLayout2.setLayoutParams(new LinearLayout.LayoutParams(392,20));

        linearLayoutQ.addView(linearLayout2);

        linearLayoutQ.addView(linearLayout);

        llb.addView(linearLayoutQ);
        linearLayouta.addView(llb);

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
    public void preg(){
        InputMethodManager imm = (InputMethodManager)   getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0);

        if(layout.getParent()!=null)
            ((ViewGroup)layout.getParent()).removeView(layout);
        dialogo1.setView(layout);
        dialogo1.setNegativeButton("Rechazar", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogo12, int id) {
                rechazar();
            }
        });
        dialogo1.setMessage("¿Está seguro de que desea RECHAZAR esta factura?");

        dialogo1.create().show();
    }

public void preg2(){
    InputMethodManager imm = (InputMethodManager)   getSystemService(Context.INPUT_METHOD_SERVICE);
    imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0);
    if(layout.getParent()!=null)

        ((ViewGroup)layout.getParent()).removeView(layout);
    dialogo1.setView(layout);
    dialogo1.setNegativeButton("Autorizar", new DialogInterface.OnClickListener() {
        public void onClick(DialogInterface dialogo12, int id) {
            aceptar();
        }
    });
    dialogo1.setMessage("¿Está seguro de que desea AUTORIZAR esta factura?");
    dialogo1.create().show();
}

public void dialog(View view){
        dialogo12 = new AlertDialog.Builder(Facturas.this);
        dialogo12.setView(R.layout.alert);
        dialogo12.setCancelable(false);
final AlertDialog testDialog = dialogo12.create();
        testDialog.show();

        new Handler().postDelayed(new Runnable(){
public void run(){
        testDialog.dismiss();
        Intent intent = new Intent(Facturas.this, DetallesFac.class);
        intent.putExtra("Folio","F4567 (1-123)");

        startActivity(intent);

        };
        }, 2000);

        }

        public void dialog2(View view){
            dialogo12 = new AlertDialog.Builder(Facturas.this);
            dialogo12.setCancelable(false);
            dialogo12.setView(R.layout.alert);
            final AlertDialog testDialog = dialogo12.create();
            testDialog.show();
            new Handler().postDelayed(new Runnable(){
                public void run(){
                    testDialog.dismiss();
                    Intent intent = new Intent(Facturas.this, DetallesFac.class);
                    intent.putExtra("Folio","A4088 (1-100)");

                    startActivity(intent);

                };
            }, 2000);
        }
    public void dialog3(View view){
        dialogo12 = new AlertDialog.Builder(Facturas.this);
        dialogo12.setView(R.layout.alert);
        dialogo12.setCancelable(false);
        final AlertDialog testDialog = dialogo12.create();
        testDialog.show();

        new Handler().postDelayed(new Runnable(){
            public void run(){
                testDialog.dismiss();
                Intent intent = new Intent(Facturas.this, DetallesFac.class);
                intent.putExtra("Folio","1-135 (1055)");

                startActivity(intent);

            };
        }, 2000);

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                if (drawerLayout.isDrawerOpen(navigationView)) {
                    drawerLayout.closeDrawers();
                } else {
                    drawerLayout.openDrawer(navigationView);
                }
                return true;

            default:
                return true;
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_activity_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }


    private void action(int resid) {
        Toast.makeText(this, getText(resid), Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        return false;
    }
    private void prepareMenuData() {

        MenuModel menuModel = new MenuModel("\uD83D\uDCB2 Estatus Financiero de Obras", true, false, ""); //Menu of Android Tutorial. No sub menus
        headerList.add(menuModel);
        menuModel = new MenuModel("\uD83D\uDCB5 Análisis de Estado de Cuenta \n\t\t\t\tMensual", true, false, ""); //Menu of Android Tutorial. No sub menus
        headerList.add(menuModel);
        menuModel = new MenuModel("\uD83D\uDCC9 Cuentas por Pagar", true, false, ""); //Menu of Android Tutorial. No sub menus
        headerList.add(menuModel);
        menuModel = new MenuModel("\uD83D\uDCC8 Cuentas por Cobrar", true, false, ""); //Menu of Android Tutorial. No sub menus
        headerList.add(menuModel);
        menuModel = new MenuModel("\uD83D\uDCB9 Análisis del Seguimiento de \n\t\t\t\tEstimaciones", true, false, ""); //Menu of Android Tutorial. No sub menus
        headerList.add(menuModel);
        menuModel = new MenuModel("\uD83D\uDCDD Reporte de Órdenes de Compra", true, false, ""); //Menu of Android Tutorial. No sub menus
        headerList.add(menuModel);
        menuModel = new MenuModel("\uD83D\uDDF3 Reporte de Requisiciones", true, false, ""); //Menu of Android Tutorial. No sub menus
        headerList.add(menuModel);

        if (!menuModel.hasChildren) {
            childList.put(menuModel, null);
        }

        menuModel = new MenuModel("▼ Compras", true, true, ""); //Menu of Java Tutorials
        headerList.add(menuModel);
        List<MenuModel> childModelsList = new ArrayList<>();
        MenuModel childModel = new MenuModel("Análisis Estadistico de Requisiciones", false, false, "");
        childModelsList.add(childModel);

        childModel = new MenuModel("Análisis del Seguimiento de Requisiciones vs Presupuesto", false, false, "");
        childModelsList.add(childModel);

        childModel = new MenuModel("Análisis Estadistico de Atención a Requisiciones", false, false, "");
        childModelsList.add(childModel);

        childModel = new MenuModel("Reporte de Cancelación de Insumos", false, false, "");
        childModelsList.add(childModel);
        childModel = new MenuModel("Balance de Pagos a Órdenes de compra ", false, false, "");
        childModelsList.add(childModel);
        childModel = new MenuModel("Historial de Compras", false, false, "");
        childModelsList.add(childModel);
        childModel = new MenuModel("Estatus de Anticipo y Fondos de Garantía de Proveedores", false, false, "");
        childModelsList.add(childModel);


        if (menuModel.hasChildren) {
            childList.put(menuModel, childModelsList);
        }

        childModelsList = new ArrayList<>();
        menuModel = new MenuModel("▼ Almacenes", true, true, ""); //Menu of Python Tutorials
        headerList.add(menuModel);
        childModel = new MenuModel("Kardex de Movimientos", false, false, "");
        childModelsList.add(childModel);
        childModel = new MenuModel("Herramientas de Préstamo", false, false, "");
        childModelsList.add(childModel);
        childModel = new MenuModel("Reporte de Entradas", false, false, "");
        childModelsList.add(childModel);
        childModel = new MenuModel("Reporte de Salidas", false, false, "");
        childModelsList.add(childModel);
        childModel = new MenuModel("Historial de Movimientos por Serie", false, false, "");
        childModelsList.add(childModel);

        if (menuModel.hasChildren) {
            childList.put(menuModel, childModelsList);
        }
        childModelsList = new ArrayList<>();
        menuModel = new MenuModel("▼ Administración", true, true, ""); //Menu of Python Tutorials
        headerList.add(menuModel);
        childModel = new MenuModel("Cuentas por pagar", false, false, "");
        childModelsList.add(childModel);
        childModel = new MenuModel("Cuentas por cobrar", false, false, "");
        childModelsList.add(childModel);
        childModel = new MenuModel("Flujo de Efectivo", false, false, "");
        childModelsList.add(childModel);
        childModel = new MenuModel("Movimientos Bancarios con Desglose de Afectaciones", false, false, "");
        childModelsList.add(childModel);

        if (menuModel.hasChildren) {
            childList.put(menuModel, childModelsList);
        }
        childModelsList = new ArrayList<>();
        menuModel = new MenuModel("▼ Control de Obras", true, true, ""); //Menu of Python Tutorials
        headerList.add(menuModel);
        childModel = new MenuModel("Estatus financiero de obras", false, false, "");
        childModelsList.add(childModel);
        childModel = new MenuModel("Análisis del seguimiento de estimaciones por concepto", false, false, "");
        childModelsList.add(childModel);

        if (menuModel.hasChildren) {
            childList.put(menuModel, childModelsList);
        }
        childModelsList = new ArrayList<>();
        menuModel = new MenuModel("▼ Equipos", true, true, ""); //Menu of Python Tutorials
        headerList.add(menuModel);
        childModel = new MenuModel("Kardex de movimientos de equipos", false, false, "");
        childModelsList.add(childModel);
        childModel = new MenuModel("Cargo por órdenes de trabajo", false, false, "");
        childModelsList.add(childModel);
        childModel = new MenuModel("Consumibles y combustibles", false, false, "");
        childModelsList.add(childModel);
        childModel = new MenuModel("Rendimiento de consumibles y combustibles", false, false, "");
        childModelsList.add(childModel);
        childModel = new MenuModel("Utilización de equipos", false, false, "");
        childModelsList.add(childModel);
        childModel = new MenuModel("Mantenimiento de equipos pendientes", false, false, "");
        childModelsList.add(childModel);
        childModel = new MenuModel("Estatus financiero de equipos", false, false, "");
        childModelsList.add(childModel);


        if (menuModel.hasChildren) {
            childList.put(menuModel, childModelsList);
        }
            childModelsList = new ArrayList<>();
            menuModel = new MenuModel("▼ Producción", true, true, ""); //Menu of Python Tutorials
            headerList.add(menuModel);
            childModel = new MenuModel("Remisiones", false, false, "");
            childModelsList.add(childModel);
            childModel = new MenuModel("Pedidos", false, false, "");
            childModelsList.add(childModel);


            if (menuModel.hasChildren) {
                childList.put(menuModel, childModelsList);
            }
                childModelsList = new ArrayList<>();
                menuModel = new MenuModel("▼ Gráficas", true, true, ""); //Menu of Python Tutorials
                headerList.add(menuModel);
                childModel = new MenuModel("Estatus financiero de obra", false, false, "");
                childModelsList.add(childModel);
                childModel = new MenuModel("Análisis de ingresos vs egresos", false, false, "");
                childModelsList.add(childModel);
        childModel = new MenuModel("Costos estimados vs costos reales", false, false, "");
        childModelsList.add(childModel);


                if (menuModel.hasChildren) {
                    childList.put(menuModel, childModelsList);
                }
    }

    private void populateExpandableList() {

        expandableListAdapter = new ExpandableListAdapter(this, headerList, childList);
        expandableListView.setAdapter(expandableListAdapter);



        expandableListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {

                if (headerList.get(groupPosition).isGroup) {
                    if (!headerList.get(groupPosition).hasChildren) {

                    }
                }

                return false;
            }
        });

        expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {

                return false;
            }
        });
    }

}

