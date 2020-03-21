package br.edu.unifcv.gerenciador.tools.repository;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import br.edu.unifcv.gerenciador.tools.constants.ConvidadoConstants;
import br.edu.unifcv.gerenciador.database.DataBase;
import br.edu.unifcv.gerenciador.model.Convidado;
import br.edu.unifcv.gerenciador.model.ConvidadosCount;
import br.edu.unifcv.gerenciador.database.DataBaseHelper;

public class ConvidadoRepository {

    private static ConvidadoRepository INSTANCE;
    private DataBaseHelper dataBaseHelper;

    private ConvidadoRepository(Context context) {
        this.dataBaseHelper = new DataBaseHelper(context);
    }

    public static synchronized ConvidadoRepository getInstance(Context context) {
        if (INSTANCE == null) {
            INSTANCE = new ConvidadoRepository(context);
        }
        return INSTANCE;
    }


    public boolean save(Convidado convidado) {
        try {
            SQLiteDatabase db = this.dataBaseHelper.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put(DataBase.CONVIDADO.COLUMNS.NOME, convidado.getNome());
            values.put(DataBase.CONVIDADO.COLUMNS.PRESENCA, convidado.getPresenca());
            db.insert(DataBase.CONVIDADO.TABLE_NAME, null, values);
        } catch (Exception e) {
            return false;
        }

        return true;
    }


    public boolean update(Convidado convidado) {
        try {
            SQLiteDatabase db = this.dataBaseHelper.getWritableDatabase();
            ContentValues Values = new ContentValues();
            Values.put(DataBase.CONVIDADO.COLUMNS.NOME, convidado.getNome());
            Values.put(DataBase.CONVIDADO.COLUMNS.PRESENCA, convidado.getPresenca());

            String[] parametros = new String[1];
            parametros[0] = String.valueOf(convidado.getId());

            db.update(DataBase.CONVIDADO.TABLE_NAME, Values, DataBase.CONVIDADO.COLUMNS.ID+" = ? ", parametros );
        }catch (Exception e){
            return false;
        }
        return true;
    }

    public boolean delete(int id) {
        try{
            SQLiteDatabase db = this.dataBaseHelper.getWritableDatabase();
            String[] parametros = new String[1];
            parametros[0] = String.valueOf(id);
            db.delete(DataBase.CONVIDADO.TABLE_NAME, DataBase.CONVIDADO.COLUMNS.ID +" = ? ", parametros );
        }catch (Exception e){
            return false;
        }
        return true;
    }

    public List<Convidado> getConvidadoByQuery(String sql) {
        List<Convidado> convidados = new ArrayList<>();
        try {
            SQLiteDatabase db = this.dataBaseHelper.getReadableDatabase();
            Cursor cursor = db.rawQuery(sql, null);
            if (cursor != null && cursor.getCount() > 0) {
                while (cursor.moveToNext()) {
                    Convidado convidado = new Convidado();
                    convidado.setId(cursor.getInt(cursor.getColumnIndexOrThrow(
                            DataBase.CONVIDADO.COLUMNS.ID)));
                    convidado.setNome(cursor.getString(cursor.getColumnIndexOrThrow(
                            DataBase.CONVIDADO.COLUMNS.NOME)));
                    convidado.setPresenca(cursor.getInt(cursor.getColumnIndexOrThrow(
                            DataBase.CONVIDADO.COLUMNS.PRESENCA)));
                    convidados.add(convidado);
                }
                cursor.close();
            }

        } catch (Exception e) {
            return convidados;
        }

        return convidados;
    }

    public Convidado getConvidadoByQueryId(String sql) {
        SQLiteDatabase db = this.dataBaseHelper.getReadableDatabase();

        Cursor c = db.rawQuery(sql,null);

        Convidado o = new Convidado();

        if(c.moveToFirst()) {

            o.setId(c.getInt(c.getColumnIndex("id")));
            o.setNome(c.getString(c.getColumnIndex("nome")));

        }

        c.close(); // Close cursor
        return o;
    }

    public ConvidadosCount getCount() {
        ConvidadosCount convidadosCount = new ConvidadosCount(0, 0, 0,0);
        Cursor cursor;
        try {
            SQLiteDatabase db = this.dataBaseHelper.getReadableDatabase();
            //Ccontando Presente
            cursor = db.rawQuery("select count(*) from "
                            + DataBase.CONVIDADO.TABLE_NAME + " where "
                            + DataBase.CONVIDADO.COLUMNS.PRESENCA + " = "
                            + ConvidadoConstants.CONFIRMACAO.PRESENTE,
                    null);
            if (cursor != null && cursor.getCount() > 0) {
                cursor.moveToFirst();
                convidadosCount.setPresente(cursor.getInt(0));
                cursor.close();
            }
            //Fim Presente

            //Contando Ausente
            cursor = db.rawQuery("select count(*) from "
                            + DataBase.CONVIDADO.TABLE_NAME + " where "
                            + DataBase.CONVIDADO.COLUMNS.PRESENCA + " = "
                            + ConvidadoConstants.CONFIRMACAO.AUSENTE,
                    null);
            if (cursor != null && cursor.getCount() > 0) {
                cursor.moveToFirst();
                convidadosCount.setAusente(cursor.getInt(0));
                cursor.close();
            }
            //Fim Ausente

            //Contador nao confirmado
            cursor = db.rawQuery("select count(*) from "
                            + DataBase.CONVIDADO.TABLE_NAME + " where "
                            + DataBase.CONVIDADO.COLUMNS.PRESENCA + " = "
                            + ConvidadoConstants.CONFIRMACAO.NAO_CONFIRMADO,
                    null);
            if (cursor != null && cursor.getCount() > 0) {
                cursor.moveToFirst();
                convidadosCount.setNao_confirmado(cursor.getInt(0));
                cursor.close();
            }
            //fim nao confirmado

            //Contando Todos
            cursor = db.rawQuery("select count(*) from "
                            + DataBase.CONVIDADO.TABLE_NAME,
                    null);
            if (cursor != null && cursor.getCount() > 0) {
                cursor.moveToFirst();
                convidadosCount.setTodos(cursor.getInt(0));
                cursor.close();
            }
            //Fim Todos
            return convidadosCount;
        } catch (Exception e) {
            return convidadosCount;
        }
    }

}
