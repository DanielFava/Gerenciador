package br.edu.unifcv.gerenciador.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DataBaseHelper extends SQLiteOpenHelper {

    private static final int DATA_BASE_VERSION = 1;
    private static final String DATA_BASE_NAME = "Convidados.db";


    private static final String SQL_CREATE_TABLE_CONVIDADOS =
            "Create table " + DataBase.CONVIDADO.TABLE_NAME +
             " ( " + DataBase.CONVIDADO.COLUMNS.ID  +
             " integer primary key autoincrement, " +
             DataBase.CONVIDADO.COLUMNS.NOME + " text, " +
             DataBase.CONVIDADO.COLUMNS.PRESENCA  + " integer);";

    private static final String SQL_DROP_TABLE_CONVIDADOS =
            "drop table " + DataBase.CONVIDADO.TABLE_NAME;

    public DataBaseHelper(Context context) {
        super(context, DATA_BASE_NAME, null, DATA_BASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_TABLE_CONVIDADOS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DROP_TABLE_CONVIDADOS);
        onCreate(db);
    }
}
