//cesinha
package com.example.trabalho1;

import java.io.Serializable;

public class Curso implements Serializable {
    int id, qtdHoras;
    String nome;

    public Curso(String nome, int qtdHoras) {
        this.nome = nome;
        this.qtdHoras = qtdHoras;

    }

    public Curso() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getQtdHoras() {
        return qtdHoras;
    }

    public void setQtdHoras(int qtdHoras) {
        this.qtdHoras = qtdHoras;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }


}