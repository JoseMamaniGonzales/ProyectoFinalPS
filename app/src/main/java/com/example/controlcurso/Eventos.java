package com.example.controlcurso;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;

public class Eventos extends AppCompatActivity {

    ListView lista;
    String tema,descripcion,fecha,hora;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_eventos);
        lista=(ListView)findViewById(R.id.listaEventos);

        cargar();
    }
    public void goVoz(View view){
        Intent dato= new Intent(this,Voz.class);
        startActivity(dato);
    }

    private void cargar() {

        BaseDatos basedeDatos = new BaseDatos(this, "DEMODB", null, 1); //creacion del puntero
        SQLiteDatabase db = basedeDatos.getWritableDatabase();//abre la base en modo escritura y lectura
        if (db != null) {
            Cursor c = db.rawQuery("select * from Evento", null);
            int cantidad = c.getCount();
            int i = 0;
            String[] arreglo = new String[cantidad];
            if (c.moveToFirst()) {
                do {
                    String linea =  "\n"+c.getString(1)+"\n"+c.getString(2)+"\n"+c.getString(3)+
                            "  "+c.getString(4)+"\n";
                    arreglo[i] = linea;
                    i++;
                } while (c.moveToNext());
            }
            ArrayAdapter<String> adapter=new ArrayAdapter<String>(this,R.layout.diseno_lista,arreglo);
            lista.setAdapter(adapter);
        }
    }
    @Override
    public void onBackPressed()//controlar boton back del celular
    {
        // Añade más funciones si fuese necesario
        super.onBackPressed();  // Invoca al método
        Intent dato= new Intent(Eventos.this,MainActivity.class);
        startActivity(dato);
    }
}
