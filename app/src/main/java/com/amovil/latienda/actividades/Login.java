package com.amovil.latienda.actividades;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.amovil.latienda.R;

/**
 * Created by Juan Rueda on 2/04/2018.
 * juan.felipe.rueda@gmail.com
 */

public class Login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().hide();/**oculta la barra*/
    }

    /**
     * metodo que se ejecuta ar dal click al boton iniciar sesión
     * @param v
     */
    public void iniciarsesion (View v){
        Intent main = new Intent(Login.this,InitActivity.class);
        main.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(main);
    }

    /**
     * metodo que se ejecuta ar dal click en recuperatucontraseña
     * @param v
     */
    public void recuperatucontrasena(View v){
        Intent recuperacontrasena = new Intent(Login.this,RecuperaTuContrasena.class);
        startActivity(recuperacontrasena);
    }

    /**
     * metodo que se ejecuta ar dal click en Registrate en tiendapp
     * @param v
     */
    public void registrarse(View v){
        Intent registrarse = new Intent(Login.this,Registro.class);
        startActivity(registrarse);
    }

}
