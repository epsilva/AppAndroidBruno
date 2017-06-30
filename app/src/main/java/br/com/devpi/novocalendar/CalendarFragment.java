package br.com.devpi.novocalendar;


import android.content.Context;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.github.sundeepk.compactcalendarview.CompactCalendarView;
import com.github.sundeepk.compactcalendarview.domain.Event;

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
import java.util.List;
import java.util.Locale;

import br.com.devpi.novocalendar.entidade.CalendarioEventos;


/**
 * A simple {@link Fragment} subclass.
 */
public class CalendarFragment extends Fragment {

    private TextView mTextDia;
    private TextView mTextMes;
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

        //calendario();

        new MyDownloadTask().execute();

        View view = inflater.inflate(R.layout.fragment_calendar, container, false);

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

                mTextDia.setText(formatDia.format(dateClicked));
                mTextMes.setText(formatMes.format(dateClicked));

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

        mTextDia = (TextView) view.findViewById(R.id.dia);
        mTextMes = (TextView) view.findViewById(R.id.mes);
        mTextInfo = (TextView) view.findViewById(R.id.info);
        mTextMesCalendar = (TextView) view.findViewById(R.id.mesCalendar);

        mTextDia.setText(formatDia.format(new Date()));
        mTextMes.setText(formatMes.format(new Date()));
        mTextMesCalendar.setText(formatMes.format(new Date()));


        return view;
    }

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

    private CalendarioEventos calendario(){
        CalendarioEventos calendarioEventos = new CalendarioEventos();

        URL url = null;
        HttpURLConnection connection = null;
        try {
            url = new URL("http://localhost:8090/calendario/eventos/1");
            connection = (HttpURLConnection) url.openConnection();
            connection.connect();
            String msg = connection.getResponseMessage();
            msg.toString();


        } catch (MalformedURLException e) {
            e.printStackTrace();

        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            connection.disconnect();
        }

        return calendarioEventos;
    }

    class MyDownloadTask extends AsyncTask<Void,Void,Void> {


        protected void onPreExecute() {
            //display progress dialog.

        }

        public Void doInBackground(Void... params) {
            URL url = null;
            try {
                url = new URL("https://web-service-bruno.herokuapp.com/calendario/eventos/1");
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
            HttpURLConnection con = null;
            try {
                BufferedReader reader = null;
                con = (HttpURLConnection) url.openConnection();

                InputStream stream = con.getInputStream();
                reader = new BufferedReader(new InputStreamReader(stream));
                StringBuffer buffer = new StringBuffer();
                String line ="";
                while ((line = reader.readLine()) != null){
                    buffer.append(line);
                }

                String finalJson = buffer.toString();

                JSONObject parentObject = new JSONObject(finalJson);

                int response = con.getResponseCode();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }


            return null;
        }
    }

}
