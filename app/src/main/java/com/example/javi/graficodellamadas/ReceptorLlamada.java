package com.example.javi.graficodellamadas;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;

import com.example.javi.graficodellamadas.base_de_datos.llamada.Gestor;
import com.example.javi.graficodellamadas.base_de_datos.llamada.Llamada;

import java.util.Calendar;


public class ReceptorLlamada extends BroadcastReceiver {
    private Gestor gestor;

    @Override
    public void onReceive(Context context, Intent intent) {
        final Context c = context;
        TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        tm.listen(new PhoneStateListener() {
            public void onCallStateChanged(int state, String incomingNumber) {
                if (state == android.telephony.TelephonyManager.CALL_STATE_RINGING) {
                    // Recibiendo llamada
                    Calendar calendar = Calendar.getInstance();
                    int day = calendar.get(Calendar.DAY_OF_WEEK);
                    gestor = new Gestor(c);
                    gestor.open();
                    gestor.insert(new Llamada(0, "entrante", String.valueOf(day), incomingNumber));
                }else if(state == 2){
                    // Haciendo llamada
                    Calendar calendar = Calendar.getInstance();
                    int day = calendar.get(Calendar.DAY_OF_WEEK);
                    gestor = new Gestor(c);
                    gestor.open();
                    gestor.insert(new Llamada(0, "saliente", String.valueOf(day), incomingNumber));
                }
            }
        }, PhoneStateListener.LISTEN_CALL_STATE);
    }
}