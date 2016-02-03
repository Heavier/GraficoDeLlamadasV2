package com.example.javi.graficodellamadas;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.javi.graficodellamadas.base_de_datos.llamada.Gestor;
import com.example.javi.graficodellamadas.base_de_datos.llamada.Llamada;
import com.example.javi.graficodellamadas.grafico.Grafico;

public class Principal extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private TextView tx1, tx2, tx3;
    private TextView txSinLlamadas;
    private ImageView iv1, iv2, tutorial;

    private SharedPreferences prefs;

    private Gestor gestor;
    private RecyclerView lstLista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);


        tx1 = (TextView)findViewById(R.id.tx1);
        tx2 = (TextView)findViewById(R.id.tx2);
        tx3 = (TextView)findViewById(R.id.tx3);
        txSinLlamadas = (TextView)findViewById(R.id.txSinLlamadas);
        iv1 = (ImageView)findViewById(R.id.iv1);
        iv2 = (ImageView)findViewById(R.id.iv2);
        tutorial = (ImageView) findViewById(R.id.tutorial);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        gestor = new Gestor(getApplicationContext());
        gestor.open();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gestor.insert(new Llamada(0, "entrante", "2", "606741982"));
                gestor.insert(new Llamada(0, "entrante", "3", "606741982"));
                gestor.insert(new Llamada(0, "entrante", "4", "606741982"));
                gestor.insert(new Llamada(0, "entrante", "5", "606741982"));
                gestor.insert(new Llamada(0, "entrante", "6", "606741982"));
                gestor.insert(new Llamada(0, "saliente", "2", "606741982"));
                gestor.insert(new Llamada(0, "saliente", "3", "606741982"));
                gestor.insert(new Llamada(0, "saliente", "4", "606741982"));
                gestor.insert(new Llamada(0, "saliente", "5", "606741982"));
                gestor.insert(new Llamada(0, "saliente", "6", "606741982"));

                // Por qué debo usar un hilo para actualizar la lista?
                Snackbar.make(view, "Llamadas de prueba insertadas", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        lstLista = (RecyclerView) findViewById(R.id.lsLista);
        Cursor cursor = gestor.getCursor();
        Adaptador adaptador = new Adaptador(cursor);
        lstLista.setAdapter(adaptador);

        lstLista.setLayoutManager(
                new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        if(adaptador.getItemCount()==0){
            txSinLlamadas.setVisibility(View.VISIBLE);
        }

        prefs = getSharedPreferences("tutorial", Context.MODE_PRIVATE);
        String mostrarTutorial = prefs.getString("tutorial", "si");
        switch (mostrarTutorial){
            case "si":
                tutorialOn(null);
                break;
            case "no":
                tutorialOff(null);
                break;
        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.principal, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        Intent intent = new Intent(getApplicationContext(), Grafico.class);
        if (id == R.id.llamadas) {
            intent.putExtra("opcion", "llamadas");
            startActivity(intent);
        } else if (id == R.id.salientes) {
            intent.putExtra("opcion", "salientes");
            startActivity(intent);
        } else if (id == R.id.entrantes) {
            intent.putExtra("opcion", "entrantes");
            startActivity(intent);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void tutorialOff(View view) {
        tx1.setVisibility(View.GONE);
        tx2.setVisibility(View.GONE);
        tx3.setVisibility(View.GONE);
        iv1.setVisibility(View.GONE);
        iv2.setVisibility(View.GONE);
        tutorial.setVisibility(View.GONE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("tutorial", "no");
        editor.commit();
    }

    public void tutorialOn(MenuItem item) {
        tx1.setVisibility(View.VISIBLE);
        tx2.setVisibility(View.VISIBLE);
        tx3.setVisibility(View.VISIBLE);
        iv1.setVisibility(View.VISIBLE);
        iv2.setVisibility(View.VISIBLE);
        tutorial.setVisibility(View.VISIBLE);
    }
}

