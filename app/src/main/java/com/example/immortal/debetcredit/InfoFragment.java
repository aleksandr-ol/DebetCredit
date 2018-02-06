package com.example.immortal.debetcredit;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TableLayout;
import android.widget.TextView;

import java.text.Format;
import java.util.Locale;

public class InfoFragment extends Fragment {
    private Context context;
    private Cursor cursor;

    TextView summDebet, summCredit, moneySumm;

    DatabaseConnector db;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.info_fragment_layout, container, false);
        context = view.getContext();

        summCredit = (TextView) view.findViewById(R.id.summ_credit_textView);
        summDebet = (TextView) view.findViewById(R.id.summ_debet_textView);
        moneySumm = (TextView) view.findViewById(R.id.money_summ_textView);

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        db = new DatabaseConnector(context);

        db.open();
        summDebet.setText(String.format(Locale.US, "%.02f", db.getSummDebets()));
        summCredit.setText(String.format(Locale.US, "%.02f", db.getSummCredits()));
        moneySumm.setText(String.format(Locale.US, "%.02f", db.getSummMoney()));
        db.close();
    }
}
