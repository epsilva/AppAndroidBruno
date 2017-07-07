package br.com.devpi.novocalendar.fragments;


import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.com.devpi.novocalendar.FormCalendarActivity;
import br.com.devpi.novocalendar.FormDocumentoActivity;
import br.com.devpi.novocalendar.R;
import br.com.devpi.novocalendar.adpter.AdpterArrayDocumento;
import br.com.devpi.novocalendar.entidades.Documentos;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {

    private ArrayList<Documentos> listaDocumentos;
    private ListView listView;
    ImageButton imageButton;

    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_lista_documento, container, false);
        listView = (ListView) view.findViewById(R.id.listView);
        imageButton = (ImageButton) view.findViewById(R.id.form_button_doc);

        listaDocumentos();

        new ReadJSON().execute();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Documentos doc = (Documentos) listView.getItemAtPosition(position);
                Uri uri = Uri.parse(doc.getLinkDoc());
                startActivity( new Intent( Intent.ACTION_VIEW, uri ) );
            }
        });

        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openFormDoc();
            }
        });




        return view;
    }

    public void listaDocumentos(){

        listaDocumentos = new ArrayList<Documentos>();

        for(int i = 0; i <= 0; i++){
            Documentos documento = new Documentos();
            documento.setNomeDocumento("Documento LATAM "+i);
            documento.setUsuario("Bruno Vieira");
            documento.setLinkDoc("https://docs.google.com/a/latam.com/forms/d/e/1FAIpQLScxP3h0hfYIlqxap7yMzofk8mteCsIj3uCYaRJO1pZHmb--IQ/viewform?usp=sf_link"+i);
            documento.setData(new Date());
            listaDocumentos.add(documento);
        }
    }

    public void openFormDoc(){
        Intent intent = new Intent(getActivity(), FormDocumentoActivity.class);
        startActivity(intent);
    }

    class ReadJSON extends AsyncTask<String, Integer, String> {

        @Override
        protected String doInBackground(String... strings) {
            return null;
        }

        @Override
        protected void onPostExecute(String content) {

            AdpterArrayDocumento adapter = new AdpterArrayDocumento(
                    getContext(), R.layout.fragment_home, listaDocumentos
            );

            listView.setAdapter(adapter);
        }
    }

}
