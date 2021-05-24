package com.example.trabalho1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class ListagemAlunos extends AppCompatActivity {
    private ListView listaAlunos;
    private Button btnNovoAluno;
    private int id1, id2;
    Aluno aluno;
    DBHelper helper;
    ArrayList<Aluno> arrayList;
    ArrayAdapter<Aluno> arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listagem_alunos);
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
        listaAlunos.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView,View view, int position, long id){
                aluno = arrayAdapter.getItem(position);
                return false;
            }
        });
        preencherLista();
    }

    public void novoAluno(View view) {
        Intent it = new Intent(this, CadastroAluno.class);
        startActivity(it);
    }

    public void preencherLista() {
        helper = new DBHelper(ListagemAlunos.this);
        arrayList = helper.selecionaTodosAlunos();
        helper.close();
        if (!arrayList.isEmpty()) {
            arrayAdapter = new ArrayAdapter<Aluno>(ListagemAlunos.this, android.R.layout.simple_list_item_1, arrayList);
            listaAlunos.setAdapter(arrayAdapter);
            registerForContextMenu(listaAlunos);
        }
    }

    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        MenuItem mDelete = menu.add(Menu.NONE, id1, 1, "Deleta Registro");
        MenuItem mSair = menu.add(Menu.NONE, id2, 2, "Cancela");
        mDelete.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                long retornoBD;
                helper = new DBHelper(ListagemAlunos.this);
                retornoBD = helper.deleteAluno(aluno);
                helper.close();
                if (retornoBD == -1) {
                    Toast t = Toast.makeText(ListagemAlunos.this, "Erro de exclusão!", Toast.LENGTH_SHORT);
                    t.show();
                } else {
                    Toast t = Toast.makeText(ListagemAlunos.this, "Registro excluído com sucesso!", Toast.LENGTH_SHORT);
                    t.show();
                }
                preencherLista();
                return false;
            }
        });
        super.onCreateContextMenu(menu, v, menuInfo);
    }

    protected void onResume() {
        super.onResume();
        preencherLista();
    }
}