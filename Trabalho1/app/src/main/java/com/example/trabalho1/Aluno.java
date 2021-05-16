package com.example.trabalho1;

public class Aluno {
    int id, couseId;
    String nome, email, cpf, telefone;

    public Aluno(int couseId, String nome, String email, String cpf, String telefone) {
        this.couseId = couseId;
        this.nome = nome;
        this.email = email;
        this.cpf = cpf;
        this.telefone = telefone;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCouseId() {
        return couseId;
    }

    public void setCouseId(int couseId) {
        this.couseId = couseId;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }
}
