package com.example.trabalho1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainMenu extends AppCompatActivity {
    private TextView txtNome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);
        txtNome = findViewById(R.id.txtNome);
        Bundle args = getIntent().getExtras();
        txtNome.setText("Bem vindo " + args.getString("ch_usuario"));
    }

    public void listarAlunos(View view) {
        Intent it = new Intent(MainMenu.this, ListagemAlunos.class);
        startActivity(it);
    }

    public void cadastrarAlunos(View view) {
        Intent it = new Intent(MainMenu.this, CadastroAluno.class);
        startActivity(it);
    }
}