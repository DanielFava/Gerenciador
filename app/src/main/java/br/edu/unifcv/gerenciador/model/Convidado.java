package br.edu.unifcv.gerenciador.model;

import java.io.Serializable;

public class Convidado implements Serializable {

    private int id;
    private String nome;
    private int presenca;

    public int getId() {return id;}

    public void setId(int id) {this.id = id;}

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getPresenca() {
        return presenca;
    }

    public void setPresenca(int presenca) {
        this.presenca = presenca;
    }
}
