package com.example.controlcurso;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

public class BaseDatos extends SQLiteOpenHelper {

    //3 CREACION DEL CONSTRUCTOR
    // 4 CREACION DE LA TABLA
    String tabla= "CREATE TABLE Evento(Id INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "tema Text, descripcion Text, fecha Text, hora Text)";

    public BaseDatos (Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override

    public void onCreate(SQLiteDatabase db) {
        //5 invocar a la creacion de la tabla
        db.execSQL(tabla);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }
}
