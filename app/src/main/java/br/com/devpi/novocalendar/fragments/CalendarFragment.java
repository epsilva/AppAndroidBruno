package br.com.devpi.novocalendar.fragments;


import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.github.sundeepk.compactcalendarview.CompactCalendarView;
import com.github.sundeepk.compactcalendarview.domain.Event;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
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
    private SimpleDateFormat formatoDiaMesAno = new SimpleDateFormat("dd/MM/yyyy");

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
        httpCall.setUrl("https://web-service-bruno.herokuapp.com/calendario/eventos/listaCalendarioEventos");
        new HttpRequest(){
            @Override
            public void onResponse(String response) {
                super.onResponse(response);
                Gson gson = new Gson();
                List<CalendarioEventos> listaCalendario = new ArrayList<CalendarioEventos>();
                Type type = new TypeToken<List<CalendarioEventos>>(){}.getType();
                listaCalendario = gson.fromJson(response, type);
                if(!listaCalendario.isEmpty()) {
                    List<Event> listEvents = new ArrayList<Event>();
                    for(CalendarioEventos calendarioEventos : listaCalendario){
                        Event evnt1 = null;

                        try {
                            evnt1 = new Event(Color.RED, formatoDiaMesAno.parse(calendarioEventos.getDataEvento()).getTime(), calendarioEventos.getNomeEvento());
                            listEvents.add(evnt1);
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                    }
                    calendarView.addEvents(listEvents);
                }
            }
        }.execute(httpCall);

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
//                if(calendarioEventos != null) {
//                    mTextInfo.setText(calendarioEventos.getDescricaocao());
//                }
//            }
//        }.execute(httpCall);

        calendarView = (CompactCalendarView) view.findViewById(R.id.compactcalendar_view);
        calendarView.setUseThreeLetterAbbreviation(true);

//        for(String data : listaDatasTrabalhadas()){
//            Event evnt1 = null;
//            try {
//                evnt1 = new Event(Color.RED, formatoDiaMesAno.parse(data).getTime(), "Teste de Hoje");
//                calendarView.addEvent(evnt1);
//            } catch (ParseException e) {
//                e.printStackTrace();
//            }
//
//        }


        calendarView.setListener(new CompactCalendarView.CompactCalendarViewListener() {
            @Override
            public void onDayClick(Date dateClicked) {
                mTextDiaMes.setText(formatDia.format(dateClicked));

                List<Event> listaEventos = calendarView.getEvents(dateClicked);

                if(!listaEventos.isEmpty()){
                    if(listaEventos.size() > 1){
                        Toast.makeText(getContext(), "Você possui mais de um evento neste dia!", Toast.LENGTH_SHORT).show();

                    }
                    mTextInfo.setText(listaEventos.get(0).getData().toString());
                }else{
                    mTextInfo.setText("Você não possui evento neste dia!");
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
