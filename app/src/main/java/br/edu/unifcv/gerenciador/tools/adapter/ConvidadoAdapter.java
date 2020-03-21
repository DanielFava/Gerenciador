package br.edu.unifcv.gerenciador.tools.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import br.edu.unifcv.gerenciador.R;
import br.edu.unifcv.gerenciador.tools.listener.OnConvidadoListener;
import br.edu.unifcv.gerenciador.model.Convidado;
import br.edu.unifcv.gerenciador.tools.viewholder.ConvidadoViewHolder;

public class ConvidadoAdapter extends RecyclerView.Adapter<ConvidadoViewHolder> {

    public List<Convidado> mConvidados;
    private OnConvidadoListener mListener;

    public ConvidadoAdapter(List<Convidado> mConvidados, OnConvidadoListener listener) {
        this.mConvidados = mConvidados;
        this.mListener = listener;
    }

    @Override
    public ConvidadoViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {

        Context context = viewGroup.getContext();

        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.row_convidados_lista, viewGroup, false);

        return new ConvidadoViewHolder(view,context);
    }

    @Override
    public void onBindViewHolder(ConvidadoViewHolder convidadoViewHolder, int i) {
        convidadoViewHolder.bindData(this.mConvidados.get(i),mListener);
    }

    @Override
    public int getItemCount() {
        return mConvidados.size();
    }



}
