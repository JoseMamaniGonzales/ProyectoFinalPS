package com.example.controlcurso;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class Notas extends AppCompatActivity {
    private EditText uno,dos,tres,cuatro,cinco,seis;
    private Spinner lista1,lista2,lista3,lista4,lista5,lista6;
    private TextView nota;
    private Button calcular;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notas);

        uno = (EditText) findViewById(R.id.nota1);
        dos = (EditText)findViewById(R.id.nota2);
        tres = (EditText)findViewById(R.id.nota3);
        cuatro =(EditText) findViewById(R.id.nota4);
        cinco =(EditText) findViewById(R.id.nota5);
        seis =(EditText) findViewById(R.id.nota6);
        nota = (TextView) findViewById(R.id.notaFinal);
        calcular= (Button) findViewById(R.id.button);
        lista1 = (Spinner) findViewById(R.id.lista1);
        lista2 = (Spinner) findViewById(R.id.lista2);
        lista3 = (Spinner) findViewById(R.id.lista3);
        lista4 = (Spinner) findViewById(R.id.lista4);
        lista5 = (Spinner) findViewById(R.id.lista5);
        lista6 = (Spinner) findViewById(R.id.lista6);
        cargarLista();
    }
    public double nota(int a,int b,int c,int d,int e,int f){
        double suma = (a*convertir(lista1))+(b*convertir(lista2))+(c*convertir(lista3))+(
                d*convertir(lista4))+(e*convertir(lista5))+(f*convertir(lista6));
        return suma;
    }
    public double convertir(Spinner dato){
        String dato1 = dato.getSelectedItem().toString();
        String dato2 = dato1.substring(0,dato1.length()-1);
        int dato3 = Integer.parseInt(dato2);
        double dato4 = dato3/100.0;
        return dato4;
    }
    public int convertirEntero(Spinner dato){
        String dato1 = dato.getSelectedItem().toString();
        String dato2 = dato1.substring(0,dato1.length()-1);
        int dato3 = Integer.parseInt(dato2);
        return dato3;
    }
    public boolean camposVacio(){
        if(uno.getText().toString().isEmpty() || dos.getText().toString().isEmpty()
                || tres.getText().toString().isEmpty()|| cuatro.getText().toString().isEmpty()
                || cinco.getText().toString().isEmpty()|| seis.getText().toString().isEmpty()){
            Toast.makeText(this, "Campos incorrectos", Toast.LENGTH_SHORT).show();
            return true;
        }
        return false;
    }
    public boolean numErroneo(){
        int a=Integer.parseInt(uno.getText().toString());
        int b=Integer.parseInt(dos.getText().toString());
        int c=Integer.parseInt(tres.getText().toString());
        int d=Integer.parseInt(cuatro.getText().toString());
        int e=Integer.parseInt(cinco.getText().toString());
        int f=Integer.parseInt(seis.getText().toString());

        if((a>20||a<0)||(b>20||b<0)||(c>20||c<0)||(d>20||d<0)||
                (e>20||e<0)||(f>20||f<0)){
            Toast.makeText(this, "Notas erroneas", Toast.LENGTH_SHORT).show();
            return true;
        }
        return false;
    }
    public boolean porErroneo(){
        int suma=convertirEntero(lista1)+convertirEntero(lista2)+convertirEntero(lista3)+
                convertirEntero(lista4)+convertirEntero(lista5)+convertirEntero(lista6);

        if(suma!=100){
            Toast.makeText(this, "Porcentajes erroneos", Toast.LENGTH_SHORT).show();
            return true;
        }
        return false;
    }
    public void calcular(View v) {
        nota.setText("--");
        if(camposVacio()||numErroneo()||porErroneo()){

        }else {
            double datoCalculado;
            datoCalculado = nota(Integer.parseInt(uno.getText().toString()),Integer.parseInt(dos.getText().toString()),
                    Integer.parseInt(tres.getText().toString()), Integer.parseInt(cuatro.getText().toString())
                    , Integer.parseInt(cinco.getText().toString()), Integer.parseInt(seis.getText().toString()));
            int a = (int) Math.round(datoCalculado);
            nota.setText("" + a);
        }
    }
    public void cargarLista(){
        String[] datos = new String[] {"30%", "25%", "20%", "18%", "16%", "15%", "10%"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, datos);
        lista1.setAdapter(adapter);
        lista2.setAdapter(adapter);
        lista3.setAdapter(adapter);
        lista4.setAdapter(adapter);
        lista5.setAdapter(adapter);
        lista6.setAdapter(adapter);
    }
}
