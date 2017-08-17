package com.example.souravpc.project;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
public class updateregister extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_updateregister);
        Button update=(Button)findViewById(R.id.button6);
        Button regiser=(Button)findViewById(R.id.button7);
        Button Logout=(Button)findViewById(R.id.button9);
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent uintent = new Intent(updateregister.this, update.class);
                uintent.putExtra("username", getIntent().getStringExtra("username"));
                updateregister.this.startActivity(uintent);
            }
        });
        regiser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent rintent = new Intent(updateregister.this, RegisterActivity.class);
                rintent.putExtra("username", getIntent().getStringExtra("username"));
                updateregister.this.startActivity(rintent);
            }
        });
        Logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent rintent = new Intent(updateregister.this, MedicActivity.class);
                SharedPreferences preferences = getSharedPreferences("mloggedin", updateregister.MODE_PRIVATE);
                //Getting editor
                SharedPreferences.Editor editor = preferences.edit();

                //Puting the value false for loggedin
                editor.putBoolean("mloggedin", false);
                editor.commit();
                updateregister.this.startActivity(rintent);
            }
        });
    }

}
