package com.example.trabalho1;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "sistemaAlunos", TABLE_ADM_NAME = "contato", ADM_ID = "id", ADM_NOME = "nome", ADM_EMAIL = "email", ADM_USUARIO = "usuario", ADM_PASS = "senha";
    private static final String TABLE_ALUNO_NAME = "aluno", ALUNO_ID = "id", ALUNO_NOME = "nome", ALUNO_EMAIL = "email", ALUNO_CPF = "cpf", ALUNO_TEL = "telefone", ALUNO_CURSOID = "cursoId";
    private static final String TABLE_CURSO_NAME = "curso", CURSO_ID = "id", CURSO_NOME = "nome", CURSO_QTDHORAS = "qtdHoras";
    SQLiteDatabase db;
    private static final String TABLE_CRATE_ADM = "create table " + TABLE_ADM_NAME + " (" +
            "id integer primary key autoincrement, nome text not null, " +
            "email text not null, usuario text not null, senha text not null);";
    private static final String TABLE_CRATE_ALUNO = "create table " + TABLE_ALUNO_NAME + " (" +
            "id integer primary key autoincrement, nome text not null, " +
            "email text not null, cpf text not null, telefone text not null, cursoId integer not null, " +
            "FOREIGN KEY (cursoId) REFERENCES curso(id));";
    private static final String TABLE_CRATE_CURSO = "create table " + TABLE_CURSO_NAME + "(" +
            "id integer primary key autoincrement, nome text not null, " +
            "qtdHoras text not null);";

    public DBHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLE_CRATE_ADM);
        db.execSQL(TABLE_CRATE_ALUNO);
        db.execSQL(TABLE_CRATE_CURSO);
        this.db = db;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ADM_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ALUNO_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CURSO_NAME);
        this.onCreate(db);
    }

    public void insereContato(Contato c) {
        db = this.getReadableDatabase();
        ContentValues values = new ContentValues();
        values.put(ADM_NOME, c.getNome());
        values.put(ADM_EMAIL, c.getNome());
        values.put(ADM_USUARIO, c.getUsuario());
        values.put(ADM_PASS, c.getSenha());
        db.insert(TABLE_ADM_NAME, null, values);
        db.close();
    }

    public String buscarSenha(String usuario) {
        db = this.getReadableDatabase();
        String query = "select usuario, senha from " + TABLE_ADM_NAME;
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

    public long insereAluno(Aluno a) {
        db = this.getReadableDatabase();
        ContentValues values = new ContentValues();
        values.put(ALUNO_NOME, a.getNome());
        values.put(ALUNO_EMAIL, a.getNome());
        values.put(ALUNO_CPF, a.getCpf());
        values.put(ALUNO_TEL, a.getTelefone());
        values.put(ALUNO_CURSOID, a.getCouseId());
        long retorno = db.insert(TABLE_ALUNO_NAME, null, values);
        db.close();
        return retorno;
    }

    public long atulaizarAluno(Aluno a) {
        db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(ALUNO_NOME, a.getNome());
        values.put(ALUNO_EMAIL, a.getEmail());
        values.put(ALUNO_CPF, a.getCpf());
        values.put(ALUNO_TEL, a.getTelefone());
        values.put(ALUNO_CURSOID, a.getCouseId());
        String[] args = {String.valueOf(a.getId())};
        long retorno = db.update(TABLE_ALUNO_NAME, values, "id=?", args);
        db.close();
        return retorno;
    }

    public ArrayList<Aluno> selecionaTodosAlunos() {
        String[] coluns = {ALUNO_ID, ALUNO_CURSOID, ALUNO_NOME, ALUNO_EMAIL, ALUNO_CPF, ALUNO_TEL};
        Cursor cursor = getReadableDatabase().query(TABLE_ALUNO_NAME, coluns, null, null, null, null, "upper(nome)", null);
        ArrayList<Aluno> listaAlunos = new ArrayList<Aluno>();
        while (cursor.moveToNext()) {
            Aluno a = new Aluno(cursor.getInt(0), cursor.getInt(1), cursor.getString(2),
                    cursor.getString(3), cursor.getString(4), cursor.getString(5));
            listaAlunos.add(a);
        }

        return listaAlunos;
    }

    public long deleteAluno(Aluno a) {
        db = this.getWritableDatabase();
        return db.delete(TABLE_ALUNO_NAME, "id= ?", new String[]{String.valueOf(a.getId())});
    }

    public long insereCurso(Curso c) {
        db = this.getReadableDatabase();
        ContentValues values = new ContentValues();
        values.put(CURSO_NOME, c.getNome());
        values.put(CURSO_QTDHORAS, c.getQtdHoras());
        long retorno = db.insert(TABLE_CURSO_NAME, null, values);
        //db.close();
        return retorno;
    }

    public ArrayList<Curso> selecionaTodosCursos() {
        String[] coluns = {CURSO_ID, CURSO_NOME, CURSO_QTDHORAS};
        Cursor cursor = getReadableDatabase().query(TABLE_CURSO_NAME, coluns, null, null, null, null, "upper(nome)", null);
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
