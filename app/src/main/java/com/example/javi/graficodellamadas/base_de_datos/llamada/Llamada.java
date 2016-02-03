package com.example.javi.graficodellamadas.base_de_datos.llamada;

import android.database.Cursor;

import com.example.javi.graficodellamadas.base_de_datos.gestion.Contrato;

public class Llamada {

    private long id;
    private String tipo;
    private String fecha;
    private String numero;

    public Llamada() {
    }

    public Llamada(long id, String tipo, String fecha, String numero) {
        this.id = id;
        this.tipo = tipo;
        this.fecha = fecha;
        this.numero = numero;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Llamada llamada = (Llamada) o;

        return id == llamada.id;

    }

    @Override
    public int hashCode() {
        return (int) (id ^ (id >>> 32));
    }

    @Override
    public String toString() {
        return "Llamada{" +
                "id=" + id +
                ", tipo='" + tipo + '\'' +
                ", fecha='" + fecha + '\'' +
                ", numero='" + numero + '\'' +
                '}';
    }

    public void set(Cursor c) {
        setId(c.getLong(c.getColumnIndex(Contrato.Tabla._ID)));
        setTipo(c.getString(c.getColumnIndex(Contrato.Tabla.TIPO)));
        setFecha(c.getString(c.getColumnIndex(Contrato.Tabla.FECHA)));
        setNumero(c.getString(c.getColumnIndex(Contrato.Tabla.NUMERO)));
    }
}