package com.example.controlcurso;

import androidx.appcompat.app.AppCompatActivity;
import androidx.work.Data;

import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.ActivityNotFoundException;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

public class Voz extends AppCompatActivity {
    TextView tema,informacion;

    Button fecha,hora;
    TextView text_fecha,text_hora;
    public int dia,mes,ano,horas,minuto;

    Calendar actual = Calendar.getInstance();
    Calendar nuevo = Calendar.getInstance();

    private static final int RECOGNIZE_SPEECH_ACTIVITY = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_voz);
        tema=(TextView)findViewById(R.id.textoTema);
        informacion=(TextView)findViewById(R.id.TextoHablado);

        fecha=(Button)findViewById(R.id.fecha);
        hora=(Button)findViewById(R.id.hora);
        text_fecha=(TextView) findViewById(R.id.text_fecha);
        text_hora=(TextView) findViewById(R.id.text_hora);
    }

    public void onClick(View v){
        if(v==fecha){
            //c= Calendar.getInstance();
            dia=actual.get(Calendar.DAY_OF_MONTH);
            mes=actual.get(Calendar.MONTH);
            ano=actual.get(Calendar.YEAR);

            DatePickerDialog datePickerDialog = new DatePickerDialog(this,R.style.datepicker, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                    nuevo.set(Calendar.YEAR,year);
                    nuevo.set(Calendar.MONTH,month);
                    nuevo.set(Calendar.DATE,dayOfMonth);
                    text_fecha.setText(dayOfMonth +"/"+(month+1)+"/"+year);
                }
            },ano,mes,dia);
            datePickerDialog.show();
        }
        if(v==hora){
            //c= Calendar.getInstance();
            horas=actual.get(Calendar.HOUR_OF_DAY);
            minuto=actual.get(Calendar.MINUTE);

            TimePickerDialog timePickerDialog = new TimePickerDialog(this,R.style.datepicker, new TimePickerDialog.OnTimeSetListener() {
                @Override
                public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                    nuevo.set(Calendar.HOUR_OF_DAY,hourOfDay);
                    nuevo.set(Calendar.MINUTE,minute);
                    nuevo.set(Calendar.SECOND,0);
                    text_hora.setText(hourOfDay+":"+minute);
                }
            },horas,minuto,false);
            timePickerDialog.show();
        }
    }

    public void registro(View view){
        BaseDatos admin= new BaseDatos(this,"DEMODB",null, 1);
        SQLiteDatabase bd = admin.getWritableDatabase();//ABRE LA base de datos en modo de lectura y escritura
        String valorTema=tema.getText().toString();
        String valorDes=informacion.getText().toString();
        String valorFecha=text_fecha.getText().toString();
        String valorHora=text_hora.getText().toString();
        if(!valorTema.isEmpty()){
            ContentValues registronuevo = new ContentValues();
            registronuevo.put("tema", valorTema);
            registronuevo.put("descripcion", valorDes);
            registronuevo.put("fecha", valorFecha);
            registronuevo.put("hora", valorHora);
            bd.insert("Evento", null, registronuevo);
        }

        //Notificacion
        realizarNoti(valorTema,valorDes);

        Intent dato= new Intent(this,Eventos.class);
        startActivity(dato);
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case RECOGNIZE_SPEECH_ACTIVITY:
                if (resultCode == RESULT_OK && null != data) {
                    ArrayList<String> speech = data
                            .getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    String strSpeech2Text = speech.get(0);
                    informacion.setText(strSpeech2Text);
                }
                break;
            default:
                break;
        }
    }
    public void onClickImgBtnHablar(View v) {
        Intent intentActionRecognizeSpeech = new Intent(
                RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        // Configura el Lenguaje (Español-México)
        intentActionRecognizeSpeech.putExtra(
                RecognizerIntent.EXTRA_LANGUAGE_MODEL, "es-MX");
        try {
            startActivityForResult(intentActionRecognizeSpeech, RECOGNIZE_SPEECH_ACTIVITY);
        } catch (ActivityNotFoundException a) {
            Toast.makeText(getApplicationContext(),
                    "Tú dispositivo no soporta el reconocimiento por voz",
                    Toast.LENGTH_SHORT).show();
        }
    }

    //Notificacion
    public void realizarNoti(String titulo, String informacion){
        String tag = generatekey();
        Long AllerTime = nuevo.getTimeInMillis() - System.currentTimeMillis();
        int random = (int)(Math.random()*50+1);

        Data data = guardarData(titulo, informacion, random);
        WorkManagemet.guardarNoti(AllerTime,data,tag);
    }
    private String generatekey(){
        return UUID.randomUUID().toString();
    }
    private Data guardarData(String titulo, String detalle, int idNoti){
        return new Data.Builder()
                .putString("titulo",titulo)
                .putString("detalle",detalle)
                .putInt("id_noti",idNoti).build();
    }

}
