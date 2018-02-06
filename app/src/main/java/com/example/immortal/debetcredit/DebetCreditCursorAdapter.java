package com.example.immortal.debetcredit;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.CursorAdapter;
import android.widget.Switch;
import android.widget.TextView;

import java.util.Calendar;
import java.util.Locale;

public class DebetCreditCursorAdapter extends CursorAdapter {

    private LayoutInflater mLayoutInflater;
    private Context mContext;

    public DebetCreditCursorAdapter(Context context, Cursor c) {
        super(context, c);
        mContext = context;
        mLayoutInflater = LayoutInflater.from(context);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        View v = mLayoutInflater.inflate(R.layout.single_debet_credit_layout, parent, false);
        return v;
    }

    @Override
    public void bindView(View v, Context context, Cursor c) {
        final String title = c.getString(c.getColumnIndexOrThrow("title"));
        double summ = c.getDouble(c.getColumnIndexOrThrow("summ"));
        final int id = c.getInt(c.getColumnIndexOrThrow("_id"));

        TextView title_text = (TextView) v.findViewById(R.id.title_debet_credit);
        if (title_text != null) {
            title_text.setText(title);
        }


        TextView summ_text_view = (TextView) v.findViewById(R.id.summ_debet_credit);
        if (summ_text_view != null) {
            summ_text_view.setText(String.format(Locale.US, "%.02f", summ));
        }
    }
}