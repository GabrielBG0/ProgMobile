package com.example.trabalho1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import static java.lang.Integer.parseInt;

public class CadastroAluno extends AppCompatActivity {
    private DBHelperAluno helper = new DBHelperAluno(this);
    private EditText alunoNome, alunoEmail, alunoCPF, alunoTelefone, alunoCurso;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_aluno);
        alunoNome = findViewById(R.id.alunoNome);
        alunoEmail = findViewById(R.id.alunoEmail);
        alunoCPF = findViewById(R.id.alunoCPF);
        alunoTelefone = findViewById(R.id.alunoTelefone);
        alunoCurso = findViewById(R.id.alunoCurso);
    }

    public void cadastrar(View view) {
        String nome = alunoNome.getText().toString(), email = alunoEmail.getText().toString(), cpf = alunoCPF.getText().toString();
        String telefone = alunoTelefone.getText().toString();
        int curso = parseInt(alunoCurso.getText().toString());
            Aluno a = new Aluno(curso, email, cpf, telefone, nome);
            helper.insereAluno(a);
            Toast t = Toast.makeText(CadastroAluno.this, "Cadastro realizado com sucesso", Toast.LENGTH_SHORT);
            t.show();
            limpar();
    }

    private void limpar() {
        alunoNome.setText("");
        alunoEmail.setText("");
        alunoCPF.setText("");
        alunoTelefone.setText("");
        alunoCurso.setText("");
    }

    public void cancelar(View view) {
    }
}