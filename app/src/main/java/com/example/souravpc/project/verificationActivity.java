package com.example.souravpc.project;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class verificationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verification);
        final EditText verify=(EditText)findViewById(R.id.editText4);
        final Button ver=(Button)findViewById(R.id.verify);
        SharedPreferences preferences = getSharedPreferences("register", verificationActivity.MODE_PRIVATE);
        final int pick=preferences.getInt("pickedNumber", -1);
        final String name=preferences.getString("name", "");
        final int age=preferences.getInt("age", -1);
        final String username=preferences.getString("username", "");
        final String Phone=preferences.getString("phone","");
        final String sex=preferences.getString("sex", "");
        final String image=preferences.getString("image", "");
        final String doctorusername=preferences.getString("doctorusername","");
        final String blood=preferences.getString("blood","");
        final String dateofbirth=preferences.getString("dateofbirth","");
        final String password=preferences.getString("password","");
        ver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int pick1=Integer.parseInt(verify.getText().toString());
                if(pick1==pick)
                {
                    Response.Listener<String> responseListener = new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            try {
                                JSONObject jsonResponse = new JSONObject(response);
                                boolean success =jsonResponse.getBoolean("success");
                                if (success) {
                                    Intent intent = new Intent(verificationActivity.this, updateregister.class);
                                    CharSequence text="REGISTERED";
                                    int duration = Toast.LENGTH_SHORT;
                                    Toast toast = Toast.makeText(verificationActivity.this, text, duration);
                                    toast.show();
                                    verificationActivity.this.startActivity(intent);
                                } else {
                                    CharSequence text="Username Already Exists";
                                    int duration = Toast.LENGTH_SHORT;
                                    Toast toast = Toast.makeText(verificationActivity.this, text, duration);
                                    toast.show();
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    };
                    pRegisterRequest registerrequest = new pRegisterRequest(name, username, age, password,Phone,image,sex,doctorusername,blood,dateofbirth, responseListener);
                    RequestQueue queue1 = Volley.newRequestQueue(verificationActivity.this);
                    queue1.add(registerrequest);
                }
                else
                {
                    CharSequence text="wrong verification id";
                    int duration = Toast.LENGTH_SHORT;
                    Toast toast = Toast.makeText(verificationActivity.this, text, duration);
                    toast.show();
                }
            }
        });
    }
}