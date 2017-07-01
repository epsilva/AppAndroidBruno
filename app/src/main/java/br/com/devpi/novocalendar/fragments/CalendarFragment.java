package br.com.devpi.novocalendar.fragments;


import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;

import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.github.sundeepk.compactcalendarview.CompactCalendarView;
import com.github.sundeepk.compactcalendarview.domain.Event;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import br.com.devpi.novocalendar.FormCalendarActivity;
import br.com.devpi.novocalendar.R;
import br.com.devpi.novocalendar.entidades.CalendarioEventos;
import br.com.devpi.novocalendar.httpclient.HttpCall;
import br.com.devpi.novocalendar.httpclient.HttpRequest;


/**
 * A simple {@link Fragment} subclass.
 */
public class CalendarFragment extends Fragment {

    private TextView mTextDiaMes;
    private TextView mTextInfo;
    private TextView mTextMesCalendar;

    private SimpleDateFormat formatDia = new SimpleDateFormat("dd");
    private SimpleDateFormat formatMes = new SimpleDateFormat("MMMM - yyyy", Locale.getDefault());
    private SimpleDateFormat formatoDiaMesAno = new SimpleDateFormat("dd-MM-yyyy");

    private CompactCalendarView calendarView;

    public CalendarFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_calendar, container, false);

        mTextDiaMes = (TextView) view.findViewById(R.id.diaMes);
        mTextInfo = (TextView) view.findViewById(R.id.info);
        mTextMesCalendar = (TextView) view.findViewById(R.id.mesCalendar);

        mTextDiaMes.setText(formatDia.format(new Date()));
        mTextMesCalendar.setText(formatMes.format(new Date()));

        HttpCall httpCall = new HttpCall();
        httpCall.setMethodtype(HttpCall.GET);
        httpCall.setUrl("https://web-service-bruno.herokuapp.com/calendario/eventos/1");
        HashMap<String,String> params = new HashMap<>();
        params.put("name","James Bond");
        httpCall.setParams(params);
        new HttpRequest(){
            @Override
            public void onResponse(String response) {
                super.onResponse(response);
                Gson gson = new Gson();
                CalendarioEventos calendarioEventos = gson.fromJson(response, CalendarioEventos.class);
                mTextInfo.setText(calendarioEventos.getDescricaocao());
            }
        }.execute(httpCall);

        calendarView = (CompactCalendarView) view.findViewById(R.id.compactcalendar_view);
        calendarView.setUseThreeLetterAbbreviation(true);

        for(String data : listaDatasTrabalhadas()){
            Event evnt1 = null;
            try {
                evnt1 = new Event(Color.RED, formatoDiaMesAno.parse(data).getTime(), "Teste de Hoje");
                calendarView.addEvent(evnt1);
            } catch (ParseException e) {
                e.printStackTrace();
            }

        }


        calendarView.setListener(new CompactCalendarView.CompactCalendarViewListener() {
            @Override
            public void onDayClick(Date dateClicked) {
                Context context = getContext();

                mTextDiaMes.setText(formatDia.format(dateClicked));

                if(listaDatasTrabalhadas().contains(formatoDiaMesAno.format(dateClicked))){
                    mTextInfo.setText("Dia Trabalhado");
                }else{
                    mTextInfo.setText("Dia não Trabalhado");
                }

            }

            @Override
            public void onMonthScroll(Date firstDayOfNewMonth) {
                mTextMesCalendar.setText(formatMes.format(firstDayOfNewMonth));
            }
        });



        BottomNavigationView navigation = (BottomNavigationView) view.findViewById(R.id.navigationCalendar);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        return view;
    }

//    private List<String> listarEventos(){
//        HttpCall httpCall = new HttpCall();
//        httpCall.setMethodtype(HttpCall.GET);
//        httpCall.setUrl("https://web-service-bruno.herokuapp.com/calendario/eventos/1");
//        HashMap<String,String> params = new HashMap<>();
//        params.put("name","James Bond");
//        httpCall.setParams(params);
//        new HttpRequest(){
//            @Override
//            public void onResponse(String response) {
//                super.onResponse(response);
//                Gson gson = new Gson();
//                CalendarioEventos calendarioEventos = gson.fromJson(response, CalendarioEventos.class);
//                mTextInfo.setText(calendarioEventos.getDescricaocao());
//            }
//        }.execute(httpCall);
//    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {

            switch (item.getItemId()) {
                case R.id.add_event:

                    //if(item.isCheckable() && !item.isChecked()) {
                        Intent intent = new Intent(getActivity(), FormCalendarActivity.class);
                        startActivity(intent);
                    //}

                    return true;

            }
            return false;
        }

    };

    private List<String> listaDatasTrabalhadas(){
        List<String> listaData = new ArrayList<String>();
        listaData.add(formatoDiaMesAno.format(data(2017, 05, 1)));
        listaData.add(formatoDiaMesAno.format(data(2017, 05, 2)));
        listaData.add(formatoDiaMesAno.format(data(2017, 05, 3)));
        listaData.add(formatoDiaMesAno.format(data(2017, 05, 4)));
        listaData.add(formatoDiaMesAno.format(data(2017, 05, 6)));
        listaData.add(formatoDiaMesAno.format(data(2017, 05, 7)));
        listaData.add(formatoDiaMesAno.format(data(2017, 05, 25)));

        return listaData;
    }

    private Date data(int ano, int mes, int dia){
        Calendar cal = Calendar.getInstance();
        cal.set(ano,mes,dia, 0, 0, 0);

        return cal.getTime();
    }



}
