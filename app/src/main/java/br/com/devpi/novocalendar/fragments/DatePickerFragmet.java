package br.com.devpi.novocalendar.fragments;

import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.widget.DatePicker;
import android.widget.EditText;

import java.util.Calendar;

import br.com.devpi.novocalendar.R;

/**
 * Created by esdraspinheiro on 01/07/17.
 */

public class DatePickerFragmet extends DialogFragment implements OnDateSetListener {

    private EditText dataEvento;

    public DatePickerFragmet() {

    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        dataEvento = (EditText) getActivity().findViewById(R.id.dataEvento);

        return new DatePickerDialog(getActivity(), this, year, month, day);
    }

    @Override
    public void onDateSet(DatePicker datePicker, int ano, int mes, int dia) {
        dataEvento.setText(String.format("%02d/%02d/%04d", dia, mes, ano));
    }
}
