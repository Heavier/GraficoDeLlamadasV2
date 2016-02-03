package com.example.javi.graficodellamadas.base_de_datos.llamada;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.javi.graficodellamadas.base_de_datos.gestion.Ayudante;
import com.example.javi.graficodellamadas.base_de_datos.gestion.Contrato;

import java.util.ArrayList;
import java.util.List;

public class Gestor {

    private Ayudante abd;
    private SQLiteDatabase bd;

    public Gestor(Context c){
        abd = new Ayudante(c);
    }

    public void open(){
        bd = abd.getWritableDatabase();
    }

    public void openRead(){
        bd = abd.getReadableDatabase();
    }

    public void close(){
        abd.close();
    }

    public long insert(Llamada l){
        ContentValues valores = new ContentValues();
        valores.put(Contrato.Tabla.TIPO, l.getTipo());
        valores.put(Contrato.Tabla.FECHA, l.getFecha());
        valores.put(Contrato.Tabla.NUMERO, l.getNumero());
        long id = bd.insert(Contrato.Tabla.TABLA, null, valores);
        return id;
    }

    public int delete(Llamada l){
        return delete(l.getId());
    }

    public int delete(long id){
        String condicion = Contrato.Tabla._ID + "=?";
        String[] argumentos = {id + ""};
        int cuenta = bd.delete(Contrato.Tabla.TABLA, condicion, argumentos);
        return cuenta;
    }

    public int update(Llamada l){
        ContentValues valores = new ContentValues();
        valores.put(Contrato.Tabla.TIPO, l.getTipo());
        valores.put(Contrato.Tabla.FECHA, l.getFecha());
        valores.put(Contrato.Tabla.NUMERO, l.getNumero());
        String condicion = Contrato.Tabla._ID + "= ?";
        String[] argumentos = {l.getId() + ""};
        int cuenta = bd.update(Contrato.Tabla.TABLA, valores, condicion, argumentos);
        return cuenta;
    }

    public List<Llamada> select(String condicion){
        List<Llamada> lc = new ArrayList<>();
        Cursor cursor = bd.query(
                Contrato.Tabla.TABLA, null, condicion, null, null, null, null);
        cursor.moveToFirst();
        Llamada ag;
        while (!cursor.isAfterLast()) {
            ag = getRow(cursor);
            lc.add(ag);
            cursor.moveToNext();
        }
        cursor.close();
        return lc;
    }

    public List<Llamada> select(String condicion, String[] paramentros){
        List<Llamada> la = new ArrayList<>();
        Cursor cursor = getCursor(condicion, paramentros);
        Llamada l;
        while (cursor.moveToNext()){
            l = getRow(cursor);
            la.add(l);
        }
        cursor.close();
        return la;
    }

    public List<Llamada> select(){
        return select(null, null);
    }


    public Llamada getRow(Cursor c) {
        Llamada re = new Llamada();
        re.setId(c.getLong(c.getColumnIndex(Contrato.Tabla._ID)));
        re.setTipo(c.getString(c.getColumnIndex(Contrato.Tabla.TIPO)));
        re.setFecha(c.getString(c.getColumnIndex(Contrato.Tabla.FECHA)));
        re.setNumero(c.getString(c.getColumnIndex(Contrato.Tabla.NUMERO)));
        return re;
    }

    public Llamada getRow(long id) {
        Cursor c = getCursor("_id = ?",new String[]{id+""});
        return getRow(c);
    }

    public Llamada getRowPrimero(Cursor c) {
        Llamada re = new Llamada();
        re.setId(c.getLong(0));
        re.setTipo(c.getString(1));
        re.setFecha(c.getString(2));
        re.setNumero(c.getString(3));
        return re;
    }

    public Llamada getRow(String numero) {
        String[] parametros = new String[] { numero };
        Cursor c = bd.rawQuery(" select * from " + Contrato.Tabla.TABLA + " where " + Contrato.Tabla.NUMERO + " = ?", parametros);
        c.moveToFirst();
        Llamada ag = getRow(c);
        c.close();
        return ag;
    }



    public Cursor getCursor(){
        return getCursor(null, null);
    }

    public Cursor getCursor(String condicion, String[] parametros) {
        Cursor cursor = bd.query(
                Contrato.Tabla.TABLA, null, condicion, parametros, null, null, Contrato.Tabla.FECHA);
        return cursor;
    }
}
