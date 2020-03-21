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

public class FragmentAusente extends Fragment {

    private FragmentAusente.ViewHolder mViewHolder = new FragmentAusente.ViewHolder();
    private ConvidadoService mConvidadoService;
    private OnConvidadoListener listener;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_ausente, container, false);

        Context context = view.getContext();

        this.mViewHolder.mRecyclerViewAusente = view.findViewById(R.id.recycler_ausente);
        this.mViewHolder.mTextAusente = view.findViewById(R.id.text_ausente);

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
        this.mViewHolder.mRecyclerViewAusente.setLayoutManager(new LinearLayoutManager(context));
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
        List<Convidado> convidados = this.mConvidadoService.findAusente();

        // definir um adapter
        ConvidadoAdapter adapter = new ConvidadoAdapter(convidados, listener);
        this.mViewHolder.mRecyclerViewAusente.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    private void loadDashBoard() {
        ConvidadosCount convidadosCount = mConvidadoService.count();
        this.mViewHolder.mTextAusente.setText(String.valueOf(convidadosCount.getAusente()));
    }

    private static class ViewHolder {
        RecyclerView mRecyclerViewAusente;
        TextView mTextAusente;

    }


}
