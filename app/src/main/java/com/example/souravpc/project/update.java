package com.example.souravpc.project;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class update extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        final EditText etUsername = (EditText) findViewById(R.id.editText);
        final EditText etPassword = (EditText) findViewById(R.id.editText2);
        final EditText press=(EditText) findViewById(R.id.editText6);
        final Button bRegister = (Button) findViewById(R.id.button8);
        bRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String username = etUsername.getText().toString();
                final String password = etPassword.getText().toString();
                final String pressure=press.getText().toString();
                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonResponse = new JSONObject(response);
                            boolean success = jsonResponse.getBoolean("success");
                            if (success) {
                                CharSequence text="UPDATED";
                                int duration = Toast.LENGTH_SHORT;
                                Toast toast = Toast.makeText(update.this, text, duration);
                                toast.show();
                                Intent intent = new Intent(update.this, updateregister.class);
                               update.this.startActivity(intent);
                            } else {
                                CharSequence text="wrong username or password";
                                int duration = Toast.LENGTH_SHORT;
                                Toast toast = Toast.makeText(update.this, text, duration);
                                toast.show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                };

                updaterequest registerrequest = new updaterequest(username,password,pressure,responseListener);
                RequestQueue queue2 = Volley.newRequestQueue(update.this);
                queue2.add(registerrequest);
            }
        });
    }
}
