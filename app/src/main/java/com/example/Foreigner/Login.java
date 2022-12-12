package com.example.Foreigner;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.Foreigner.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Login extends AppCompatActivity {

    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://foreigner-4c329-default-rtdb.firebaseio.com/");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        final EditText phone = findViewById(R.id.phone);
        final EditText password = findViewById(R.id.password);
        final TextView register = findViewById(R.id.registernow);
        final Button loginbutton = findViewById(R.id.loginbutton);

        loginbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String telefono = phone.getText().toString();
                final String contrasenia = password.getText().toString();

                if(telefono.isEmpty() || contrasenia.isEmpty()){
                    Toast.makeText(Login.this, "Introduce tu correo y contraseña",Toast.LENGTH_SHORT).show();
                }
                else{
                    databaseReference.child("Usuarios").addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            if(snapshot.hasChild(telefono)){

                                final String getPassword = snapshot.child(telefono).child("Contraseña").getValue(String.class);

                                if (getPassword.equals(contrasenia)){
                                    Toast.makeText(Login.this, "Inicio de Sesión correcto", Toast.LENGTH_SHORT).show();
                                    startActivity(new Intent(Login.this,MainActivity.class));
                                    finish();
                                }
                                else {
                                    Toast.makeText(Login.this, "Los datos no coindicen, vuelvalo a intentar", Toast.LENGTH_SHORT).show();
                                }

                            }   else {
                                Toast.makeText(Login.this, "Los datos no coindicen, vuelvalo a intentar", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
                }

            }
        });


        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Login.this,Registro.class));

            }
        });

    }
}