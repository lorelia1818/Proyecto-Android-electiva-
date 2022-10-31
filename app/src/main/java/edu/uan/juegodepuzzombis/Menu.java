package edu.uan.juegodepuzzombis;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Menu extends AppCompatActivity {
    FirebaseAuth auth;
    FirebaseUser user;

    Button CerrarSesion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();
        CerrarSesion = findViewById(R.id.CerrarSesion);

        CerrarSesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CerrarSesion();
            }
        });
    }

    @Override
    protected void onStart() {
        UsuarioLogueado();
        super.onStart();
    }

    //metodo de prueba si el jugador ha iniciado sesion
    private void UsuarioLogueado(){

        if (user !=null){
            Toast.makeText(this, "juagdor en linea", Toast.LENGTH_SHORT).show();
        }
        else{
            startActivity(new Intent(Menu.this,MainActivity.class));
            finish();
        }
    }

    private void CerrarSesion(){
        auth.signOut();
        startActivity(new Intent(Menu.this,MainActivity.class));
        Toast.makeText(this, "Cerrado sesion exitosamente", Toast.LENGTH_SHORT).show();
    }
}