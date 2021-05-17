package com.example.trabalho1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import static java.lang.Integer.parseInt;

public class CadastroAluno extends AppCompatActivity {
    private DBHelperAluno helper = new DBHelperAluno(this);
    private EditText alunoNome, alunoEmail, alunoCPF, alunoTelefone, alunoCurso;
    private Button botao;
    private Aluno aluno, altAluno;
    long retornoBD;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_aluno);
        alunoNome = findViewById(R.id.alunoNome);
        alunoEmail = findViewById(R.id.alunoEmail);
        alunoCPF = findViewById(R.id.alunoCPF);
        alunoTelefone = findViewById(R.id.alunoTelefone);
        alunoCurso = findViewById(R.id.alunoCurso);
        botao = findViewById(R.id.btnCadastrarAluno);
        Intent it = getIntent();
        altAluno = (Aluno) it.getSerializableExtra("ch_aluno");
        aluno = new Aluno();
        helper = new DBHelperAluno(CadastroAluno.this);

        if (altAluno != null) {
            botao.setText("Alterar");
            alunoNome.setText(altAluno.getNome());
            alunoEmail.setText(altAluno.getEmail());
            alunoCPF.setText(altAluno.getCpf());
            alunoTelefone.setText(altAluno.getTelefone());
            alunoCurso.setText(altAluno.getCouseId());

        } else {
            botao.setText("Cadastrar");
        }

    }

    public void cadastrar(View view) {
        String nome = alunoNome.getText().toString(), email = alunoEmail.getText().toString(), cpf = alunoCPF.getText().toString();
        String telefone = alunoTelefone.getText().toString();
        int curso = parseInt(alunoCurso.getText().toString());
        Aluno a = new Aluno(curso, email, cpf, telefone, nome);
        if (botao.getText().toString().equals("Cadastrar")) {
            retornoBD = helper.insereAluno(a);
        } else {
            helper.atulaizarAluno(aluno);
            helper.close();
        }
        if (retornoBD == -1) {
            Toast t = Toast.makeText(CadastroAluno.this, "Erro ao cadastrar", Toast.LENGTH_SHORT);
            t.show();
        } else {
            Toast t = Toast.makeText(CadastroAluno.this, "Cadastro realizado com sucesso", Toast.LENGTH_SHORT);
            t.show();
            finish();
        }

    }

    public void cancelar(View view) {
        finish();
    }
}