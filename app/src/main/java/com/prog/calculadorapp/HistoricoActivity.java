package com.prog.calculadorapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class HistoricoActivity extends AppCompatActivity {
    TextView ApresentaHistorico;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historico);

        ApresentaHistorico = (TextView) findViewById(R.id.ApresentaHistorico);
        //acessa o banco e recupera o no "operacao".
        databaseReference = FirebaseDatabase.getInstance().getReference().child("operacao");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String a;
                //recupera o conteúdo do no "operacao" e apresenta em tela.
                a = snapshot.getValue().toString();
                ApresentaHistorico.setText(a);
            }

            @Override
            public void onCancelled(DatabaseError error) {

            }
        });

    }
}