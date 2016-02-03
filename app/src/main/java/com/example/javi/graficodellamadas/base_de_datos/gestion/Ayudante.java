package com.example.javi.graficodellamadas.base_de_datos.gestion;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class Ayudante extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "recetario.sqlite";

    public static final int DATABASE_VERSION = 1;

    public Ayudante(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql;
        sql = " create table " + Contrato.Tabla.TABLA
                + " (" + Contrato.Tabla._ID
                + " integer primary key autoincrement , "
                + Contrato.Tabla.TIPO + " text , "
                + Contrato.Tabla.FECHA + " text , "
                + Contrato.Tabla.NUMERO + " text )";
        Log.v("SQLAAD", sql);
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql =" drop table if exists " + Contrato.Tabla.TABLA;
        db.execSQL(sql);
        onCreate(db);
    }
}
