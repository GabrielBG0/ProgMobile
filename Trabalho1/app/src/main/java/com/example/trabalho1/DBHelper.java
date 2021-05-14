package com.example.trabalho1;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "sistemaAlunos", TABLE_NAME = "contato.db", COLUMM_ID = "id", COLUMM_NOME = "nome";
    private static final String COLUMM_EMAIL = "email", COLUMM_USER = "usuario", COLUM_PASS = "senha";
    SQLiteOpenHelper db;
    private static final String TABLE_CRATE = "create table contato (" +
            "id integer primary key autoincrement, nome text not null, " +
            "email text not null, usuario text not null, senha text not null);";

    public DBHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}