package com.example.immortal.debetcredit;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class CreateDebetCreditActivity extends AppCompatActivity {
    EditText titleET, summET;
    Button submitBTN;

    DatabaseConnector db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_debet_credit);

        titleET = (EditText) findViewById(R.id.title_new_debet_credit);
        summET = (EditText) findViewById(R.id.summ_new_debet_credit);
        submitBTN = (Button) findViewById(R.id.new_submit_button);

        db = new DatabaseConnector(this);

        Intent intent = getIntent();
        final int debet_or_credit = intent.getIntExtra("debet_or_credit", 0);

        submitBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (debet_or_credit) {
                    case 0:
                        db.insertDebet(titleET.getText().toString(), Double.parseDouble(summET.getText().toString()));
                        break;
                    case 1:
                        db.insertCredit(titleET.getText().toString(), Double.parseDouble(summET.getText().toString()));
                        break;
                    default:
                        break;
                }
                finish();
            }
        });
    }
}
