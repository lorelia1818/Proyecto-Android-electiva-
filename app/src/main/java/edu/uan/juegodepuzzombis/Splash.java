package edu.uan.juegodepuzzombis;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class Splash extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        int DURACION_SPLASH = 1500;
        /*HANDLER, EJECUTAR LINEAS DE CODIGO DE UN TIEMPO DETERMINADO*/

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                /*codigo que se ejecuta*/
                Intent intent = new Intent(Splash.this,MainActivity.class);
                /*codigo que ejecuta */
            };
        },DURACION_SPLASH);
    }
}