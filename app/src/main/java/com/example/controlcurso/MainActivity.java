package com.example.controlcurso;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }
    //Los dos metodos "goVOz" and "goNotas" inicializan las respectivas
    //actividades
    public void goVoz(View view){
        Intent dato= new Intent(this,Voz.class);
        startActivity(dato);
    }
    public void goLista(View view){
        Intent dato= new Intent(this,Eventos.class);
        startActivity(dato);
    }
    public void goNotas(View view){
        Intent dato= new Intent(this,Notas.class);
        startActivity(dato);
    }
    @Override
    public void onBackPressed()//controlar boton back del celular
    {
        // Añade más funciones si fuese necesario
        super.onBackPressed();  // Invoca al método
        //finish();
        //System.exit(0);
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);

    }
}
