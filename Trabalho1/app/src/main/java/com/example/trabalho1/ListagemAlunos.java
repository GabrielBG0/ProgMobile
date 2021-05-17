package com.example.trabalho1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class ListagemAlunos extends AppCompatActivity {
    private ListView listaAlunos;
    private Button btnNovoAluno;
    Aluno aluno;
    DBHelperAluno alunoHelper;
    DBHelperCurso cursoHelper;
    ArrayList<Aluno> arrayList;
    ArrayAdapter<Aluno> arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listagem_alunos);
        preencherLista();
        listaAlunos = findViewById(R.id.listAluno);
        btnNovoAluno = findViewById(R.id.btnNovoAluno);
        listaAlunos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Aluno aluno1 = (Aluno) arrayAdapter.getItem(position);
                Intent it = new Intent(ListagemAlunos.this, CadastroAluno.class);
                it.putExtra("ch_aluno", aluno1);
                startActivity(it);
            }
        });
    }

    public void novoAluno(View view) {
        Intent it = new Intent(this, CadastroAluno.class);
        startActivity(it);
    }

    public void preencherLista() {
        alunoHelper = new DBHelperAluno(ListagemAlunos.this);
        arrayList = alunoHelper.selecionaTodosAlunos();
        alunoHelper.close();
        if (arrayList != null) {
            arrayAdapter = new ArrayAdapter<Aluno>(ListagemAlunos.this, android.R.layout.simple_list_item_1, arrayList);
            listaAlunos.setAdapter(arrayAdapter);
        }
    }

    protected void onResume() {
        super.onResume();
        preencherLista();
    }
}