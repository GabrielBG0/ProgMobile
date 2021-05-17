//cesinha

//tabela tem q se chamar curso e tem q ter um id chamado id

package com.example.trabalho1;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DBHelperCurso extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "sistemaAlunos", TABLE_NAME = "curso", COLUMM_ID = "id", COLUMM_NOME = "nome";
    private static final String COLUMM_QTDHORAS = "qtdHoras";
    SQLiteDatabase db;
    private static final String TABLE_CRATE = "create table " + TABLE_NAME + "(" +
            "id integer primary key autoincrement, nome text not null, " +
            "qtdHoras text not null)";

    public DBHelperCurso(@Nullable Context context) {
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

    public long insereCurso(Curso c) {
        db = this.getReadableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMM_NOME, c.getNome());
        values.put(COLUMM_QTDHORAS, c.getQtdHoras());
        long retorno = db.insert(TABLE_NAME, null, values);
        db.close();
        return retorno;
    }

    public ArrayList<Curso> selecionaTodosCursos() {
        String[] coluns = {COLUMM_ID, COLUMM_NOME, COLUMM_QTDHORAS};
        Cursor cursor = getReadableDatabase().query(TABLE_NAME, coluns, null, null, null, null, "upper(nome)", null);
        ArrayList<Curso> listaCursos = new ArrayList<Curso>();
        while (cursor.moveToNext()) {
            Curso a = new Curso();
            cursor.getInt(0);
            cursor.getInt(1);
            cursor.getString(2);
            listaCursos.add(a);
        }
        return listaCursos;
    }
}
