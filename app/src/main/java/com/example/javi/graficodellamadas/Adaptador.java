package com.example.javi.graficodellamadas;

import android.database.Cursor;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.javi.graficodellamadas.base_de_datos.gestion.Contrato;

public class Adaptador extends RecyclerView.Adapter<Adaptador.ViewHolder> {

    private Cursor cursor;

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private TextView txNumero;
        private TextView txRegistros;
        private ImageView ivInCall;
        private ImageView ivOutCall;

        public ViewHolder(View itemView) {
            super(itemView);
            txNumero = (TextView) itemView.findViewById(R.id.tvNumero);
            txRegistros = (TextView) itemView.findViewById(R.id.tvRegistros);
            ivInCall = (ImageView) itemView.findViewById(R.id.ivInCall);
            ivOutCall = (ImageView) itemView.findViewById(R.id.ivOutCall);
        }

        public void bindString(String no, String co, String tipo) {
            txNumero.setText(no);
            switch (co){
                case "2":
                    txRegistros.setText("Lunes");
                    break;
                case "3":
                    txRegistros.setText("Martes");
                    break;
                case "4":
                    txRegistros.setText("Miercoles");
                    break;
                case "5":
                    txRegistros.setText("Jueves");
                    break;
                case "6":
                    txRegistros.setText("Viernes");
                    break;
            }
            switch (tipo){
                case "saliente":
                    ivInCall.setVisibility(View.INVISIBLE);
                    ivOutCall.setVisibility(View.VISIBLE);
                    break;
                case "entrante":
                    ivInCall.setVisibility(View.VISIBLE);
                    ivOutCall.setVisibility(View.INVISIBLE);
                    break;
            }
        }
    }

    public Adaptador(Cursor cursor) {
        this.cursor = cursor;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View item = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
        ViewHolder vh = new ViewHolder(item);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        cursor.moveToPosition(position);
        String no = cursor.getString(cursor.getColumnIndex(Contrato.Tabla.NUMERO));
        String co = cursor.getString(cursor.getColumnIndex(Contrato.Tabla.FECHA));
        String tipo = cursor.getString(cursor.getColumnIndex(Contrato.Tabla.TIPO));
        holder.bindString(no, co, tipo);
    }

    @Override
    public int getItemCount() {
        return cursor.getCount();
    }
}