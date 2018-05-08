package com.amovil.latienda.actividades;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.WindowManager;

import com.amovil.latienda.R;

/**
 * Created by Juan Rueda on 2/04/2018.
 * juan.felipe.rueda@gmail.com
 */

public class SplashScreen extends AppCompatActivity {

    /**
     * //tiempo que muestra la pantalla inicial en milisegundos
     */
    private int tiempoMilisegundos = 2000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,/** elimina la barra de notificaciones*/
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash_screen);
        /**
         * //tarea que congela la pantalla el tiempo establecido
         */
        Thread myThread = new Thread(){
            @Override
            public void run() {
                try {
                    sleep(tiempoMilisegundos);
                    Intent intent = new Intent(getApplicationContext(),Login.class);
                    startActivity(intent);
                    finish();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        myThread.start(); //
    }
}
