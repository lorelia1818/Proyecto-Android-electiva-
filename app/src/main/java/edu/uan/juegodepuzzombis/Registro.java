package edu.uan.juegodepuzzombis;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

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
                    RegistrarJugador(email,password);
                }
            }
        });

    }
       /*Metodo es para registrar un jugador */
    private void RegistrarJugador(String email, String password) {
        auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        /*Si el jugador fue resgistrado correctamente*/
                        if (task.isSuccessful()){
                            FirebaseUser user = auth.getCurrentUser();

                            int contador = 0;

                            assert user != null; /* el usuario no es nulo*/

                            /* String*/
                            String uidString = user.getUid();
                            String correoString = correoEt.getText().toString();
                            String passString = passEt.getText().toString();
                            String nombreString = nombreEt.getText().toString();
                            String fechaString = fechaTxt.getText().toString();

                            HashMap<Object,Object> DatosJUGADOR = new HashMap<>();

                            DatosJUGADOR.put("Uid",uidString);
                            DatosJUGADOR.put("Email",correoString);
                            DatosJUGADOR.put("Password",passString);
                            DatosJUGADOR.put("Nombres",nombreString);
                            DatosJUGADOR.put("Fecha",fechaString);
                            DatosJUGADOR.put("Zombis",contador);

                            FirebaseDatabase database = FirebaseDatabase.getInstance(); //instancia
                            DatabaseReference reference = database.getReference("MI DATA BASE JUGADORES"); //NOMBRE DE BD
                            reference.child(uidString).setValue(DatosJUGADOR);
                            startActivity(new Intent(Registro.this,Menu.class));
                            Toast.makeText(Registro.this, "USUARIO REGISTRADO EXITOSAMENTE", Toast.LENGTH_SHORT).show();
                            finish();
                        }else{
                            Toast.makeText(Registro.this, "Ha ocurrido un error ", Toast.LENGTH_SHORT).show();
                        }

                    }
                })
                /*Si falla el registro*/
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(Registro.this, ""+e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }
}