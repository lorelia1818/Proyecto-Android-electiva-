package edu.uan.juegodepuzzombis;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Login extends AppCompatActivity {

    EditText correoLogin, passLogin;
    Button BTNLogin;
    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        correoLogin = findViewById(R.id.correoLogin);
        passLogin = findViewById(R.id.passLogin);
        BTNLogin = findViewById(R.id.BTNLogin);
        auth = FirebaseAuth.getInstance();

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
                    LogeoDeJugador(email,pass);
                }
            }
        });

    }

    private void LogeoDeJugador(String email, String pass) {
        auth.signInWithEmailAndPassword(email,pass)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            FirebaseUser user = auth.getCurrentUser();
                            startActivity(new Intent(Login.this,Menu.class));
                            assert user != null; //afirmamos que el usuario no es nulo 
                            Toast.makeText(Login.this, "BIENVENIDO(A)"+user.getEmail(), Toast.LENGTH_SHORT).show();
                            finish();
                        }

                    }
                    // si es que falla el logueo , muestra un mensaje
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(Login.this, ""+e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }
}