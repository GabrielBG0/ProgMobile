package com.example.trabalho1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private DBHelperAdm helper = new DBHelperAdm(this);
    private EditText edtUsuario, edtSenha;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edtUsuario = findViewById(R.id.edtUsuario);
        edtSenha = findViewById(R.id.edtSenha);
    }

    public void conectar(View view) {
        String usr = edtUsuario.getText().toString(), senha = edtSenha.getText().toString();
        String pass = helper.buscarSenha(usr);
        if (senha.equals(pass)){
            Intent it = new Intent(this, MainMenu.class);
            it.putExtra("ch_usuario", usr);
            startActivity(it);
        } else {
            Toast t = Toast.makeText(MainActivity.this, "Usuario não encontrado", Toast.LENGTH_LONG);
            t.show();
        }
    }

    public void cadastrar(View view) {
        Intent it = new Intent(this, CadastroAdm.class);
        startActivity(it);
    }
}