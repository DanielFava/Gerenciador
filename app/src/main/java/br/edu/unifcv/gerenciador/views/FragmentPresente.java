package br.edu.unifcv.gerenciador.views;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import br.edu.unifcv.gerenciador.R;
import br.edu.unifcv.gerenciador.model.Convidado;
import br.edu.unifcv.gerenciador.model.ConvidadosCount;
import br.edu.unifcv.gerenciador.tools.adapter.ConvidadoAdapter;
import br.edu.unifcv.gerenciador.tools.constants.ConvidadoConstants;
import br.edu.unifcv.gerenciador.tools.listener.OnConvidadoListener;
import br.edu.unifcv.gerenciador.tools.service.ConvidadoService;

public class FragmentPresente extends Fragment {

    private FragmentPresente.ViewHolder mViewHolder = new FragmentPresente.ViewHolder();
    private ConvidadoService mConvidadoService;
    private OnConvidadoListener listener;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_presente, container, false);

        Context context = view.getContext();

        this.mViewHolder.mRecyclerViewPresente = view.findViewById(R.id.recycler_presente);
        this.mViewHolder.mTextPresente = view.findViewById(R.id.text_presente);

        this.mConvidadoService = new ConvidadoService(context);

        listener = new OnConvidadoListener() {
            @Override
            public void onClickList(int id) {
                Bundle bundle = new Bundle();
                bundle.putInt(ConvidadoConstants.BundleConstants.CONVIDADO_ID, id);
                Intent intent = new Intent(getContext(), ConvidadoFormActivity.class);

                Convidado convidado = new Convidado();
                convidado.setId(id);
                intent.putExtra("id",id);
                startActivity(intent);
            }

            @Override
            public void onDeleteClick(int id) {
                mConvidadoService.excluir(id);
                onResume();
            }
        };

        // definir um layout
        this.mViewHolder.mRecyclerViewPresente.setLayoutManager(new LinearLayoutManager(context));
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        this.loadDashBoard();
        this.loadConvidados();
    }

    private void loadConvidados() {
        // todos as pessoas do banco
        List<Convidado> convidados = this.mConvidadoService.findPresente();

        // definir um adapter
        ConvidadoAdapter adapter = new ConvidadoAdapter(convidados, listener);
        this.mViewHolder.mRecyclerViewPresente.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    private void loadDashBoard() {
        ConvidadosCount convidadosCount = mConvidadoService.count();
        this.mViewHolder.mTextPresente.setText(String.valueOf(convidadosCount.getPresente()));
    }

    private static class ViewHolder {
        RecyclerView mRecyclerViewPresente;
        TextView mTextPresente;

    }


}
