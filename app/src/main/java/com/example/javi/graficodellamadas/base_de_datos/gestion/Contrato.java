package com.example.javi.graficodellamadas.base_de_datos.gestion;

import android.provider.BaseColumns;

/**
 * Created by javi on 30/01/2016.
 */
public class Contrato {
    public Contrato(){
    }
    public static abstract class Tabla implements BaseColumns {
        public static final String TABLA = "llamada";
        public static final String TIPO = "tipo";
        public static final String FECHA = "fecha";
        public static final String NUMERO = "numero";
    }
}
