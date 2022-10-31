package edu.uan.juegodepuzzombis;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Login extends AppCompatActivity {

    EditText correoLogin, passLogin;
    Button BTNLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        correoLogin = findViewById(R.id.correoLogin);
        passLogin = findViewById(R.id.passLogin);
        BTNLogin = findViewById(R.id.BTNLogin);

        BTNLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = correoLogin.getText().toString();
                String pass = passLogin.getText().toString();
                /*Validacion para correo*/
                if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                    correoLogin.setError("correo valido");
                    correoLogin.setFocusable(true);
                    //validacion de contraseña
                }else if (pass.length()<6){
                    passLogin.setError("Contraseña debe ser mayor a 6 ");
                    passLogin.setFocusable(true);
                }else{
                    //LogeoDeJugador(email,pass);
                }
            }
        });

    }
}