package br.com.devpi.novocalendar.adpter;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import br.com.devpi.novocalendar.R;
import br.com.devpi.novocalendar.entidades.Documentos;

/**
 * Created by esdraspinheiro on 02/07/17.
 */

public class AdpterArrayDocumento extends ArrayAdapter<Documentos> {

    private Context context;
    private int resource;
    private ArrayList<Documentos> listaDocumentos;

    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm");

    public AdpterArrayDocumento(Context context, int resource, ArrayList<Documentos> listaDocumentos) {
        super(context, resource, listaDocumentos);
        this.context = context;
        this.resource = resource;
        this.listaDocumentos = listaDocumentos;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null){
            LayoutInflater layoutInflater = (LayoutInflater) getContext()
                    .getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.fragment_home, null, true);

        }
        Documentos documentos = getItem(position);

        TextView txtTitulo = (TextView) convertView.findViewById(R.id.titulo_doc);
        txtTitulo.setText(documentos.getNomeDocumento());

        TextView txtUsuario = (TextView) convertView.findViewById(R.id.usuario_doc);
        txtUsuario.setText(documentos.getUsuario());

        TextView txtDataDoc = (TextView) convertView.findViewById(R.id.datas_doc_doc);
        txtDataDoc.setText(simpleDateFormat.format(documentos.getData()));


        return convertView;
    }
}
