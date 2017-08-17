package com.example.souravpc.project;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class pasthistoryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pasthistory);
        TextView past=(TextView)findViewById(R.id.pastMsg);
        Intent pastintent=getIntent();
        past.setText(pastintent.getStringExtra("past"));
    }
}
