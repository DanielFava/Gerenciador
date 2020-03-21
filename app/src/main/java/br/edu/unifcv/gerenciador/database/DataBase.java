package br.edu.unifcv.gerenciador.database;

public class DataBase {

    private DataBase() {}

    public static class CONVIDADO {
        public static final String TABLE_NAME = "Convidado";

        public static class COLUMNS {
            public static final String ID = "id";
            public static final String NOME = "nome";
            public static final String PRESENCA = "presenca";
        }
    }
}