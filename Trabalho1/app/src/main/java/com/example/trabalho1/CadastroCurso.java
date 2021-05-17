//cesinha
package com.example.trabalho1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import static java.lang.Integer.parseInt;

public class CadastroCurso extends AppCompatActivity {
    private DBHelperCurso helper = new DBHelperCurso(this);
    private EditText cursoNome, cursoQtdHoras;
    long retornoBD;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_curso);
        cursoNome = findViewById(R.id.cursoNome);
        cursoQtdHoras = findViewById(R.id.cursoQtdHoras);
        helper = new DBHelperCurso(CadastroCurso.this);
    }

    public void cadastrar(View view) {
        String nome = cursoNome.getText().toString();
        int qtdHoras = parseInt(cursoQtdHoras.getText().toString());
        Curso c = new Curso(nome, qtdHoras);
        retornoBD = helper.insereCurso(c);
        if (retornoBD == -1) {
            Toast t = Toast.makeText(CadastroCurso.this, "Erro ao cadastrar", Toast.LENGTH_SHORT);
            t.show();
        } else {
            Toast t = Toast.makeText(CadastroCurso.this, "Cadastro realizado com sucesso", Toast.LENGTH_SHORT);
            t.show();
            finish();
        }

    }

    public void cancelar(View view) {
        finish();
    }
}