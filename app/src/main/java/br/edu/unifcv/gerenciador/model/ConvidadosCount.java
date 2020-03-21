package br.edu.unifcv.gerenciador.model;

public class ConvidadosCount {

    private int presente;
    private int ausente;
    private int nao_confirmado;
    private int todos;

    public ConvidadosCount(int presente, int ausente,int nao_confirmado ,int todos) {
        this.presente = presente;
        this.ausente = ausente;
        this.todos = todos;
        this.nao_confirmado = nao_confirmado;
    }

    public int getPresente() {
        return presente;
    }

    public void setPresente(int presente) {
        this.presente = presente;
    }

    public int getAusente() {
        return ausente;
    }

    public void setAusente(int ausente) {
        this.ausente = ausente;
    }

    public int getNao_confirmado() {
        return nao_confirmado;
    }

    public void setNao_confirmado(int nao_confirmado) {
        this.nao_confirmado = nao_confirmado;
    }

    public int getTodos() {
        return todos;
    }

    public void setTodos(int todos) {
        this.todos = todos;
    }


}
