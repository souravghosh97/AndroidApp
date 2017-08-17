package com.example.souravpc.project;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.content.Intent;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class MedicActivity extends AppCompatActivity {
    boolean mloggedIn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medic);
      final Button bu=(Button)findViewById(R.id.button3);
        final EditText medicalperson=(EditText)findViewById(R.id.medicusername);
        final EditText medicalpersonpass=(EditText)findViewById(R.id.editText5);
        bu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String mediuser=medicalperson.getText().toString();
                String medipass=medicalpersonpass.getText().toString();
                Response.Listener<String> mresponselistener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject pjsonResponse = new JSONObject(response);
                            boolean success = pjsonResponse.getBoolean("success");
                            if (success) {
                                Intent in=new Intent(MedicActivity.this,updateregister.class);
                                in.putExtra("username",mediuser);
                                SharedPreferences sharedPreferences = MedicActivity.this.getSharedPreferences("mloggedin", MedicActivity.MODE_PRIVATE);

                                //Creating editor to store values to shared preferences
                                SharedPreferences.Editor editor = sharedPreferences.edit();

                                //Adding values to editor
                                editor.putBoolean("mloggedin", true);

                                //Saving values to editor
                                editor.commit();

                                startActivity(in);

                            } else {
                                CharSequence text="wrong user name or pass word";
                                int duration = Toast.LENGTH_SHORT;
                                Toast toast = Toast.makeText(MedicActivity.this, text, duration);
                                toast.show();
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                };
                check xheck = new check(mediuser, medipass, mresponselistener);
                RequestQueue queue2 = Volley.newRequestQueue(MedicActivity.this);
                queue2.add(xheck);

            }
        });
    }
    @Override
    protected void onResume() {
        super.onResume();
        //In onresume fetching value from sharedpreference
        SharedPreferences sharedPreferences = getSharedPreferences("mloggedin",MedicActivity.MODE_PRIVATE);

        //Fetching the boolean value form sharedpreferences
        mloggedIn = sharedPreferences.getBoolean("mloggedin", false);

        //If we will get true
        if(mloggedIn){
            //We will start the Profile Activity
            Intent intent = new Intent(MedicActivity.this, updateregister.class);
            startActivity(intent);
        }
    }

    @Override
    public void onBackPressed() {
        Intent intentback=new Intent(MedicActivity.this,LoginActivity.class);
        super.onBackPressed();
        startActivity(intentback);
    }
}
