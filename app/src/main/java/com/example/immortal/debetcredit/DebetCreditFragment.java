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

public class DebetCreditFragment extends Fragment {
    private Context context;
    ListView listView;
    DebetCreditCursorAdapter cursorAdapter;
    private Cursor cursor;
    DatabaseConnector db;

    int debet_or_credit;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);

        Bundle bundle = this.getArguments();
        if (bundle != null) {
            debet_or_credit = bundle.getInt("type", 0);
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.debet_credit_layout, container, false);
        context = view.getContext();

        listView = (ListView) view.findViewById(R.id.listView);

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        db = new DatabaseConnector(context);
        if(debet_or_credit == 0)
            cursor = db.getAllDebets();
        else
            cursor = db.getAllCredits();


        cursorAdapter = new DebetCreditCursorAdapter(context, cursor);
        listView.setAdapter(cursorAdapter);
        db.close();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        if(debet_or_credit == 0)
            menu.add(0,1,0, "Додати нову статтю доходів");
        else
            menu.add(0,1,0, "Додати нову статтю витрат");
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case 1:
                Intent intent = new Intent(context, CreateDebetCreditActivity.class);
                intent.putExtra("debet_or_credit", debet_or_credit);
                startActivity(intent);
                return true;
            default: return super.onOptionsItemSelected(item);
        }
    }
}
