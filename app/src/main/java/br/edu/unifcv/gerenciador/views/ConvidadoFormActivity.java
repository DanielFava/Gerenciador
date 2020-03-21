package br.edu.unifcv.gerenciador.views;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import java.util.List;

import br.edu.unifcv.gerenciador.R;
import br.edu.unifcv.gerenciador.model.Convidado;
import br.edu.unifcv.gerenciador.tools.constants.ConvidadoConstants;
import br.edu.unifcv.gerenciador.tools.service.ConvidadoService;

public class ConvidadoFormActivity extends AppCompatActivity {

    private ViewHolder mViewHolder = new ViewHolder();
    private ConvidadoService mConvidadoService;
    //private Convidado convidado;

    Convidado c;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_convidado_form);

        this.mConvidadoService = new ConvidadoService(this);

        List<Convidado> conv = mConvidadoService.findAll();

        this.loadComponents();

        this.setEvents();

        this.onResume();

        this.c = new Convidado();

        Intent intent = getIntent();
        int id = getIntent().getExtras().getInt("id");
        c = mConvidadoService.findId(id);

        mViewHolder.mEditName.setText(c.getNome());

    }

    private boolean valicao(){
        boolean valicao = false;
        if(valicao == false) {
            if (mViewHolder.mEditName.length() <= 0) {
                mViewHolder.mEditName.setError("Nome Vazio!!");
                mViewHolder.mEditName.requestFocus();
            } else if(mViewHolder.mGroup.getCheckedRadioButtonId() == -1) {
                AlertDialog.Builder dlg = new AlertDialog.Builder(this);
                dlg.setTitle(R.string.title_aviso);
                dlg.setMessage(R.string.message_campos_invalidos_brancos);
                dlg.setNeutralButton(R.string.action_ok, null);
                dlg.show();
            }
            else{
                valicao = true;
            }
        }
        return valicao;
    }

    public void Save(){
        try {
            if(valicao() == true){
                if(c.getId() == 0){
                    c.setNome(this.mViewHolder.mEditName.getText().toString());
                    if (this.mViewHolder.mRadioNotConfirmed.isChecked()) {
                        c.setPresenca(ConvidadoConstants.CONFIRMACAO.NAO_CONFIRMADO);
                    } else if (this.mViewHolder.mRadioPresent.isChecked()) {
                        c.setPresenca(ConvidadoConstants.CONFIRMACAO.PRESENTE);
                    } else if (this.mViewHolder.mRadioAbsent.isChecked()){
                        c.setPresenca(ConvidadoConstants.CONFIRMACAO.AUSENTE);
                    }
                    this.mConvidadoService.insert(c);
                }
                else{
                    c.setNome(this.mViewHolder.mEditName.getText().toString());
                    if (this.mViewHolder.mRadioNotConfirmed.isChecked()) {
                        c.setPresenca(ConvidadoConstants.CONFIRMACAO.NAO_CONFIRMADO);
                    } else if (this.mViewHolder.mRadioPresent.isChecked()) {
                        c.setPresenca(ConvidadoConstants.CONFIRMACAO.PRESENTE);
                    } else if (this.mViewHolder.mRadioAbsent.isChecked()){
                        c.setPresenca(ConvidadoConstants.CONFIRMACAO.AUSENTE);
                    }
                    this.mConvidadoService.update(c);
                }
                finish();
            }
        }catch (Exception e){
            AlertDialog.Builder dlg = new AlertDialog.Builder(this);
            dlg.setTitle(R.string.title_erro);
            dlg.setMessage(e.getMessage());
            dlg.setNeutralButton(R.string.action_ok, null);
            dlg.show();
        }

    }

    private void setEvents() {
        this.mViewHolder.mButtonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Save();
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    private void loadComponents() {
        this.mViewHolder.mEditName = findViewById(R.id.edit_name);
        this.mViewHolder.mRadioNotConfirmed = findViewById(R.id.radio_not_confirmed);
        this.mViewHolder.mRadioPresent = findViewById(R.id.radio_present);
        this.mViewHolder.mRadioAbsent = findViewById(R.id.radio_absent);
        this.mViewHolder.mButtonSave = findViewById(R.id.button_save);
        this.mViewHolder.mGroup = findViewById(R.id.radio_group);
    }

    private static class ViewHolder {
        EditText mEditName;
        RadioButton mRadioNotConfirmed;
        RadioButton mRadioPresent;
        RadioButton mRadioAbsent;
        Button mButtonSave;
        RadioGroup mGroup;
    }
}
