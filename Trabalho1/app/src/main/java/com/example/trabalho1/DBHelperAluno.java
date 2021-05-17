package com.example.trabalho1;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DBHelperAluno extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "sistemaAlunos", TABLE_NAME = "aluno.db", COLUMM_ID = "id", COLUMM_NOME = "nome";
    private static final String COLUMM_EMAIL = "email", COLUMM_CPF = "cpf", COLUM_TEL = "telefone", COLUM_CURSOID = "cursoId";
    SQLiteDatabase db;
    private static final String TABLE_CRATE = "create table contato (" +
            "id integer primary key autoincrement, nome text not null, " +
            "email text not null, usuario text not null, senha text not null, cursoId integer not null, " +
            "FOREIGN KEY (cursoId) REFERENCES curso(id));";

    public DBHelperAluno(@Nullable Context context) {
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

    public long insereAluno(Aluno a) {
        db = this.getReadableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMM_NOME, a.getNome());
        values.put(COLUMM_EMAIL, a.getNome());
        values.put(COLUMM_CPF, a.getCpf());
        values.put(COLUM_TEL, a.getTelefone());
        values.put(COLUM_CURSOID, a.getCouseId());
        long retorno = db.insert(TABLE_NAME, null, values);
        db.close();
        return retorno;
    }

    public long atulaizarAluno(Aluno a){
        db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMM_NOME, a.getNome());
        values.put(COLUMM_EMAIL, a.getNome());
        values.put(COLUMM_CPF, a.getCpf());
        values.put(COLUM_TEL, a.getTelefone());
        values.put(COLUM_CURSOID, a.getCouseId());
        String[] args = {String.valueOf(a.getId())};
        long retorno = db.update(TABLE_NAME, values, "id=?", args);
        db.close();
        return  retorno;
    }

    public ArrayList<Aluno> selecionaTodosAlunos() {
        String[] coluns = {COLUMM_ID, COLUM_CURSOID, COLUMM_NOME, COLUMM_EMAIL, COLUMM_CPF, COLUM_TEL};
        Cursor cursor = getReadableDatabase().query(TABLE_NAME, coluns, null, null, null, null, "upper(nome)", null);
        ArrayList<Aluno> listaAlunos = new ArrayList<Aluno>();
        while (cursor.moveToNext()) {
            Aluno a = new Aluno(cursor.getInt(0), cursor.getInt(1), cursor.getString(2),
                    cursor.getString(3), cursor.getString(4), cursor.getString(5));
            listaAlunos.add(a);
        }
        return listaAlunos;
    }
}
