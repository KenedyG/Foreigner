package com.example.Foreigner;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

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

public class Registro extends AppCompatActivity {
    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://foreigner-4c329-default-rtdb.firebaseio.com/");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        final EditText phonestr = findViewById(R.id.regphone);
        final EditText fname = findViewById(R.id.user);
        final EditText mail = findViewById(R.id.regmail);
        final EditText regpassword = findViewById(R.id.regpassword);
        final EditText conpassword = findViewById(R.id.passwordconfirmation);
        final Button registerButton = findViewById(R.id.registernow);
        final TextView loginnow = findViewById(R.id.joinnow);

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String phone = phonestr.getText().toString();
                final String fullname = fname.getText().toString();
                final String email = mail.getText().toString();
                final String password = regpassword.getText().toString();
                final String confirm = conpassword.getText().toString();

                if (fullname.isEmpty() || email.isEmpty() || password.isEmpty() || confirm.isEmpty()){
                    Toast.makeText(Registro.this,"Por favor introduce todos los datos.",Toast.LENGTH_SHORT).show();

                }
                else if (!password.equals(confirm)){
                    Toast.makeText(Registro.this,"Las contraseñas no coinciden, vuelva a intentarlo",Toast.LENGTH_SHORT).show();
                }
                else {
                    databaseReference.child("Usuarios").addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {

                            if(snapshot.hasChild(phone)){
                                Toast.makeText(Registro.this,"Este numero ya está registrado",Toast.LENGTH_SHORT).show();
                            }
                            else {
                                databaseReference.child("Usuarios").child(phone).child("Nombre").setValue(fullname);
                                databaseReference.child("Usuarios").child(phone).child("Contraseña").setValue(password);
                                databaseReference.child("Usuarios").child(phone).child("Correo").setValue(email);


                                Toast.makeText(Registro.this,"Registro Exitoso",Toast.LENGTH_SHORT).show();
                                finish();
                            }

                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });

                    databaseReference.child("Usuarios").child(phone).child("Nombre").setValue(fullname);
                    databaseReference.child("Usuarios").child(phone).child("Contraseña").setValue(password);
                    databaseReference.child("Usuarios").child(phone).child("Correo").setValue(email);

                    Toast.makeText(Registro.this,"Registro Exitoso",Toast.LENGTH_SHORT).show();
                    finish();
                }
            }
        });
        loginnow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}