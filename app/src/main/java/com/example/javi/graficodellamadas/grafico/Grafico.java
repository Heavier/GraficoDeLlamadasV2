package com.example.javi.graficodellamadas.grafico;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.JavascriptInterface;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.TextView;

import com.example.javi.graficodellamadas.R;
import com.example.javi.graficodellamadas.base_de_datos.llamada.Gestor;
import com.example.javi.graficodellamadas.base_de_datos.llamada.Llamada;

import java.util.List;

public class Grafico extends AppCompatActivity {

    private TextView txTitulo;
    private String opcion;
    private List<Llamada> lista;
    private Gestor gestor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grafico);
        //getActionBar().setDisplayHomeAsUpEnabled(true);
        txTitulo = (TextView) findViewById(R.id.tvTitulo);

        gestor = new Gestor(this);
        gestor.open();

        final WebView myw = (WebView) findViewById(R.id.webView);

        WebSettings webSettings = myw.getSettings();
        myw.loadUrl("file:///android_asset/canvas/index.html");
        webSettings.setJavaScriptEnabled(true);
        // Esta interfaz es el puente de conexión entre ésta actividad y el javascript, el nombre puede variar.
        myw.addJavascriptInterface(this, "MainActivity");


        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if (extras == null) {
                opcion = null;
            } else {
                opcion = extras.getString("opcion");
                switch (opcion){
                    case "llamadas":
                        txTitulo.setText(R.string.llamadas);
                        break;
                    case "entrantes":
                        txTitulo.setText(R.string.entrantes);
                        break;
                    case "salientes":
                        txTitulo.setText(R.string.salientes);
                        break;
                }
            }
        } else {
            opcion = (String) savedInstanceState.getSerializable("opcion");
        }
    }

    // Para mejorarlo cambiarlo por un switch y llamar desde codigo.js entregando un número o día de la semana
    // En canvasjs.com hay más informacion, como añadir una barra secundaria.
    @JavascriptInterface
    public String lunes() {
        if (opcion.equals("salientes")){
            lista = gestor.select("tipo = 'saliente' AND fecha = '2'");
            return lista.size() + "";
        }else if(opcion.equals("entrantes")){
            lista = gestor.select("tipo = 'entrante' AND fecha = '2'");
            return lista.size() + "";
        }else{
            lista = gestor.select("fecha = '2'");
            return lista.size() + "";
        }
    }

    @JavascriptInterface
    public String martes() {
        if (opcion.equals("salientes")){
            lista = gestor.select("tipo = 'saliente' AND fecha = '3'");
            return lista.size() + "";
        }else if(opcion.equals("entrantes")){
            lista = gestor.select("tipo = 'entrante' AND fecha = '3'");
            return lista.size() + "";
        }else{
            lista = gestor.select("fecha = '3'");
            return lista.size() + "";
        }
    }

    @JavascriptInterface
    public String miercoles() {
        if (opcion.equals("salientes")){
            lista = gestor.select("tipo = 'saliente' AND fecha = '4'");
            return lista.size() + "";
        }else if(opcion.equals("entrantes")){
            lista = gestor.select("tipo = 'entrante' AND fecha = '4'");
            return lista.size() + "";
        }else{
            lista = gestor.select("fecha = '4'");
            return lista.size() + "";
        }
    }

    @JavascriptInterface
    public String jueves() {
        if (opcion.equals("salientes")){
            lista = gestor.select("tipo = 'saliente' AND fecha = '5'");
            return lista.size() + "";
        }else if(opcion.equals("entrantes")){
            lista = gestor.select("tipo = 'entrante' AND fecha = '5'");
            return lista.size() + "";
        }else{
            lista = gestor.select("fecha = '5'");
            return lista.size() + "";
        }
    }

    @JavascriptInterface
    public String viernes() {
        if (opcion.equals("salientes")){
            lista = gestor.select("tipo = 'saliente' AND fecha = '6'");
            return lista.size() + "";
        }else if(opcion.equals("entrantes")){
            lista = gestor.select("tipo = 'entrante' AND fecha = '6'");
            return lista.size() + "";
        }else{
            lista = gestor.select("fecha = '6'");
            return lista.size() + "";
        }
    }
}
