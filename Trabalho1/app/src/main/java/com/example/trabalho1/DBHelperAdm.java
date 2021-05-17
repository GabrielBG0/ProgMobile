package com.example.trabalho1;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelperAdm extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "sistemaAlunos", TABLE_NAME = "contato.db", COLUMM_ID = "id", COLUMM_NOME = "nome";
    private static final String COLUMM_EMAIL = "email", COLUMM_USER = "usuario", COLUM_PASS = "senha";
    SQLiteDatabase db;
    private static final String TABLE_CRATE = "create table contato (" +
            "id integer primary key autoincrement, nome text not null, " +
            "email text not null, usuario text not null, senha text not null);";

    public DBHelperAdm(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLE_CRATE);
        this.db = db;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String query = "DROP TABLE IF EXISTS " + TABLE_NAME;
        db.execSQL(query);
        this.onCreate(db);
    }

    public void insereContato(Contato c) {
        db = this.getReadableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMM_NOME, c.getNome());
        values.put(COLUMM_EMAIL, c.getNome());
        values.put(COLUMM_USER, c.getUsuario());
        values.put(COLUM_PASS, c.getSenha());
        db.insert(TABLE_NAME, null, values);
        db.close();
    }

    public String buscarSenha(String usuario) {
        db = this.getReadableDatabase();
        String query = "select usuario, senha from " + TABLE_NAME;
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            do {
                if (cursor.getString(0).equals(usuario)) {
                    return cursor.getString(1);
                }
            } while (cursor.moveToNext());
        }
        return "usuario não encontrado";
    }
}
