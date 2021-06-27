package com.prog.calculadorapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Button;


import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import static com.prog.calculadorapp.R.id.button0;
import static com.prog.calculadorapp.R.id.button1;
import static com.prog.calculadorapp.R.id.button2;
import static com.prog.calculadorapp.R.id.button3;
import static com.prog.calculadorapp.R.id.button4;
import static com.prog.calculadorapp.R.id.button5;
import static com.prog.calculadorapp.R.id.button6;
import static com.prog.calculadorapp.R.id.button7;
import static com.prog.calculadorapp.R.id.button8;
import static com.prog.calculadorapp.R.id.button9;
import static com.prog.calculadorapp.R.id.buttonLimpar;
import static com.prog.calculadorapp.R.id.buttonigual;
import static com.prog.calculadorapp.R.id.buttonsoma;
import static com.prog.calculadorapp.R.id.buttonmult;
import static com.prog.calculadorapp.R.id.buttondiv;
import static com.prog.calculadorapp.R.id.buttonsub;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    int i = 0;
    int count = 0; //Conta quantos numeros foram adicionados para não passar do limite
    int[] Result;
    int Total = 0;
    int zero = 0;
    static int INVALID = 9999999; //Limite de numeros
    String operator;
    TextView RESULTSCREEN;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    private Bundle savedInstanceState;

    @SuppressLint("ResourceType")

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        //iniciando o menu
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    public boolean onOptionsItemSelected(MenuItem item){
        //abrindo a activity do histórico quando clicado no item do menu
        Intent intent = new Intent(this, HistoricoActivity.class);
        startActivity(intent);

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RESULTSCREEN = (TextView) findViewById(R.id.RESULTSCREEN);
        Result = new int[2];
        Button ButtonSoma = (Button) findViewById(buttonsoma);
        Button ButtonMult = (Button) findViewById(buttonmult);
        Button ButtonDiv = (Button) findViewById(buttondiv);
        Button ButtonSub = (Button) findViewById(buttonsub);
        Button ButtonLimpar = (Button) findViewById(buttonLimpar);
        Button ButtonIgual = (Button) findViewById(buttonigual);

        Button Button0 = (Button) findViewById(button0);
        Button Button1 = (Button) findViewById(button1);
        Button Button2 = (Button) findViewById(button2);
        Button Button3 = (Button) findViewById(button3);
        Button Button4 = (Button) findViewById(button4);
        Button Button5 = (Button) findViewById(button5);
        Button Button6 = (Button) findViewById(button6);
        Button Button7 = (Button) findViewById(button7);
        Button Button8 = (Button) findViewById(button8);
        Button Button9 = (Button) findViewById(button9);

    }
    @Override
    public void onClick(View view) {
        //clicando em cada um dos botões irá excutar a proxima ação.
        switch (view.getId()) {
            case R.id.buttonsoma: operator = "+";
                proximoNumero();
                break;
            case R.id.buttonsub: operator = "-";
                proximoNumero();
                break;
            case R.id.buttonmult: operator = "x";
                proximoNumero();
                break;
            case R.id.buttondiv: operator = "/";
                proximoNumero();
                break;
            case R.id.buttonigual: calcular();
                count = 0;
                break;
            case R.id.buttonLimpar: limpar();
                break;
        }
        if(count < 7) {
            //Caso algum número for clicado
            switch (view.getId()) {
                case R.id.button0: //Adicionando o zero no result
                    Result[i] = (Result[i] * 10); count++;
                    break;
                case R.id.button1: //Adicionando o um no result
                    Result[i] = (Result[i] * 10) + 1; count++;
                    break;
                case R.id.button2: //Adicionando o dois no result
                    Result[i] = (Result[i] * 10) + 2; count++;
                    break;
                case R.id.button3: //Adicionando o tres no result
                    Result[i] = (Result[i] * 10) + 3; count++;
                    break;
                case R.id.button4: //Adicionando o quarto no result
                    Result[i] = (Result[i] * 10) + 4; count++;
                    break;
                case R.id.button5: //Adicionando o cinco no result
                    Result[i] = (Result[i] * 10) + 5; count++;
                    break;
                case R.id.button6: //Adicionando o seis no result
                    Result[i] = (Result[i] * 10) + 6; count++;
                    break;
                case R.id.button7: //Adicionando o sete no result
                    Result[i] = (Result[i] * 10) + 7; count++;
                    break;
                case R.id.button8: //Adicionando o oito no result
                    Result[i] = (Result[i] * 10) + 8; count++;
                    break;
                case R.id.button9: //Adicionando o nove no result
                    Result[i] = (Result[i] * 10) + 9; count++;
                    break;
            }
        }
        changesResult();    //Troca o resultado na tela
        Total = 0;          //Zera o velor de total para não dar erro

    }
    private void proximoNumero(){
        count = 0;  //Zera a quantidade de Números
        i = 1;      //Passa para o primeiro numero do vetor
    }
    private void limpar(){
        i = 0;          //Passa para o primeiro numero do vetor
        Result[0] = 0;  //Zera os vetores
        Result[1] = 0;
        Total = 0;      //Zera o total
        count = 0;      //Zera a quantidade de Números
    }
    private void calcular(){
        switch (operator){
            //Executa as operações e sai do switch
            case "+": Total = (Result[0] + Result[1]); break;
            case "-": Total = Result[0] - Result[1]; break;
            case "x": Total = Result[0] * Result[1]; break;
            case "/":
                if (Result[1] == 0){
                    zero = 1;
                }
                else{
                    Total = Result[0] / Result[1];
                    break;
                }

        }
        //Se for um valor válido
        if (Total < INVALID) {
            //inicializa a conexão com o banco de dados e seta o valor numa chave aleatoria gerada pelo proprio Firebase
            inicializarFirebase();
            databaseReference.child("operacao").push().setValue(Result[0]+operator+Result[1]+"="+Total);
            Result[0] = Total;  //Para executar mais operações
            Result[1] = 0;      //Passa para o segundo valor
            i = 1;

        }
    }
    private void inicializarFirebase(){
        //Inicializa a instancia e referencia do banco Firebase,
        FirebaseApp.initializeApp(MainActivity.this);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();
    }
    private void changesResult(){
        if (Total != 0 && Total < INVALID){                   //Se o valor total for válido
            String tela = String.valueOf(Total);
            RESULTSCREEN.setText(tela);
        }else if (Total > INVALID){                           //Se o valor total for inválido
            String tela = "ERROR";
            RESULTSCREEN.setText(tela);
        } else if(zero == 1){
            String tela = "ERROR";
            zero = 0;
            RESULTSCREEN.setText(tela);
            limpar();
        }
        else{
            String tela = String.valueOf(Result[i]);
            RESULTSCREEN.setText(tela);
        }


    }


}