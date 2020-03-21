package br.edu.unifcv.gerenciador.tools.service;

import android.content.Context;
import android.database.Cursor;

import java.util.List;

import br.edu.unifcv.gerenciador.database.DataBase;
import br.edu.unifcv.gerenciador.model.Convidado;
import br.edu.unifcv.gerenciador.model.ConvidadosCount;
import br.edu.unifcv.gerenciador.tools.constants.ConvidadoConstants;
import br.edu.unifcv.gerenciador.tools.repository.ConvidadoRepository;

public class ConvidadoService {

    private ConvidadoRepository convidadoRepository;

    public ConvidadoService(Context context) {
        this.convidadoRepository = ConvidadoRepository.getInstance(context);
    }

    public boolean insert(Convidado convidado) {return this.convidadoRepository.save(convidado);}

    public boolean update(Convidado convidado) {return this.convidadoRepository.update(convidado);}

    public void excluir(int id) {this.convidadoRepository.delete(id);}

    public List<Convidado> findAll() {
        return this.convidadoRepository.getConvidadoByQuery("select * from " +
                DataBase.CONVIDADO.TABLE_NAME);
    }

    public Convidado findId(int id) {
        return this.convidadoRepository.getConvidadoByQueryId("select * from "
                + DataBase.CONVIDADO.TABLE_NAME + " where "
                + DataBase.CONVIDADO.COLUMNS.ID + " = "
                + id);
    }


    public List<Convidado> findPresente() {
        return this.convidadoRepository.getConvidadoByQuery("select * from "
                        + DataBase.CONVIDADO.TABLE_NAME + " where "
                        + DataBase.CONVIDADO.COLUMNS.PRESENCA + " = "
                        + ConvidadoConstants.CONFIRMACAO.PRESENTE);
    }

    public List<Convidado> findAusente() {
        return this.convidadoRepository.getConvidadoByQuery("select * from "
                + DataBase.CONVIDADO.TABLE_NAME + " where "
                + DataBase.CONVIDADO.COLUMNS.PRESENCA + " = "
                + ConvidadoConstants.CONFIRMACAO.AUSENTE);
    }

    public List<Convidado> findNaoConfirmado() {
        return this.convidadoRepository.getConvidadoByQuery("select * from "
                + DataBase.CONVIDADO.TABLE_NAME + " where "
                + DataBase.CONVIDADO.COLUMNS.PRESENCA + " = "
                + ConvidadoConstants.CONFIRMACAO.NAO_CONFIRMADO);
    }

    public ConvidadosCount count() {
        return this.convidadoRepository.getCount();
    }

    public List<Convidado> findConvidado() {
        return this.convidadoRepository.getConvidadoByQuery("select * from "
                + DataBase.CONVIDADO.TABLE_NAME + " where "
                + DataBase.CONVIDADO.COLUMNS.ID + " = "
                + ConvidadoConstants.BundleConstants.CONVIDADO_ID);
    }


}
