package br.com.devpi.novocalendar.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import br.com.devpi.novocalendar.R;
import br.com.devpi.novocalendar.adpter.AdpterArrayDocumento;
import br.com.devpi.novocalendar.entidades.Documentos;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {

    List<Documentos> listaDocumentos;

    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_lista_documento, container, false);

        List<String> lista = new ArrayList<String>();
        lista.add("Teste 0");
        lista.add("Teste 1");
        lista.add("Teste 2");
        lista.add("Teste 3");
        lista.add("Teste 4");
        lista.add("Teste 5");
        lista.add("Teste 6");
        lista.add("Teste 7");
        lista.add("Teste 8");
        lista.add("Teste 9");
        lista.add("Teste 10");

        listaDocumentos();

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1, lista);

        final ListView listView = (ListView) view.findViewById(R.id.lista);
//        listView.setAdapter(arrayAdapter);

//        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
//                Toast.makeText(getContext(), listView.getItemAtPosition(position).toString(), Toast.LENGTH_SHORT).show();
//            }
//        });

        AdpterArrayDocumento adapter =
                new AdpterArrayDocumento(listaDocumentos, inflater);
        listView.setAdapter(adapter);

        return view;
    }

    public void listaDocumentos(){

        listaDocumentos = new ArrayList<Documentos>();

        for(int i = 0; i <= 10; i++){
            Documentos documento = new Documentos();
            documento.setNomeDocumento("Teste "+i);
            documento.setLinkDoc("Link "+i);
            listaDocumentos.add(documento);
        }
    }

}
