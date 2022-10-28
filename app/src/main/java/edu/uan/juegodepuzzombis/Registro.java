package edu.uan.juegodepuzzombis;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Registro extends AppCompatActivity {

    //DECLARAR UNA VARIABLE
    EditText correoEt,passEt,nombreEt;
    TextView fechaTxt;
    Button Registrar;
FirebaseAuth auth; //FIREBASE AUTETICACION
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
        //CONEXION CON LA VISTA
        correoEt = findViewById(R.id.correoEt);
        passEt = findViewById(R.id.passEt);
        nombreEt = findViewById(R.id.nombreEt);
        fechaTxt= findViewById(R.id.fechaTxt);
        Registrar= findViewById(R.id.Registrar);

        auth = FirebaseAuth.getInstance();
        Date date = new Date();
        SimpleDateFormat fecha = new SimpleDateFormat("d 'de' MMMM 'del' yyyy" );
        String StringFecha = fecha.format(date);
        fechaTxt.setText(StringFecha);

        Registrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = correoEt.getText().toString();
                String password = passEt.getText().toString();
                //valicion para correo
                if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                    correoEt.setError("correo valido");
                    correoEt.setFocusable(true);
                    //validacion de contraseña
                }else if (password.length()<6){
                    passEt.setError("Contraseña debe ser mayor a 6 ");
                    correoEt.setFocusable(true);
                }else{
                    
                }
            }
        });

    }
}