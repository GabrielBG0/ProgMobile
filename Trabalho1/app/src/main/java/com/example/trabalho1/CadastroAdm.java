package com.example.trabalho1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class CadastroAdm extends AppCompatActivity {
    private DBHelper helper = new DBHelper(this);
    private EditText edtNome, edtEmail, edtUsuario, edtSenha, edtConfSenha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);
        edtNome = findViewById(R.id.edtNome);
        edtEmail = findViewById(R.id.edtEmail);
        edtUsuario = findViewById(R.id.edtUsuario);
        edtSenha = findViewById(R.id.edtSenha);
        edtConfSenha = findViewById(R.id.edtConfSenha);
    }

    public void cadastrar(View view) {
        String nome = edtNome.getText().toString(), email = edtEmail.getText().toString(), usuario = edtUsuario.getText().toString();
        String senha = edtSenha.getText().toString(), confSenha = edtConfSenha.getText().toString();
        if (!senha.equals(confSenha)) {
            Toast t = Toast.makeText(CadastroAdm.this, "senhas não conhecidem", Toast.LENGTH_SHORT);
            t.show();
            edtConfSenha.setText("");
        } else {
            Contato c = new Contato(nome, email, usuario, senha);
            helper.insereContato(c);
            Toast t = Toast.makeText(CadastroAdm.this, "Cadastro realizado com sucesso", Toast.LENGTH_SHORT);
            t.show();
            finish();
        }

    }

    public void cancelar(View view) {
        finish();
    }
}