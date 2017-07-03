package br.com.devpi.novocalendar.adpter;

import android.app.Fragment;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import br.com.devpi.novocalendar.R;
import br.com.devpi.novocalendar.entidades.Documentos;

/**
 * Created by esdraspinheiro on 02/07/17.
 */

public class AdpterArrayDocumento extends BaseAdapter {

    private final List<Documentos> listaDocumentos;
    private final LayoutInflater inflater;

    public AdpterArrayDocumento(List<Documentos> listaDocumentos, LayoutInflater inflater) {
        this.listaDocumentos = listaDocumentos;
        this.inflater = inflater;
    }


    @Override
    public int getCount() {
        return listaDocumentos.size();
    }

    @Override
    public Object getItem(int position) {
        return listaDocumentos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        View view = inflater.inflate(R.layout.fragment_home, viewGroup, false);
        Documentos documentos = (Documentos) listaDocumentos.get(position);
        TextView nome = (TextView) view.findViewById(R.id.lista_curso_personalizada_nome);
        TextView descricao = (TextView) view.findViewById(R.id.lista_curso_personalizada_descricao);

        nome.setText(documentos.getNomeDocumento());
        descricao.setText(documentos.getLinkDoc());


        return view;
    }
}
