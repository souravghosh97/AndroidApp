package com.example.souravpc.project;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import org.json.JSONException;
import org.json.JSONObject;
import android.content.SharedPreferences;
public class MainActivity extends AppCompatActivity {
    boolean  loggedIn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final EditText etUsername = (EditText) findViewById(R.id.etUsername);
        final EditText etPassword = (EditText) findViewById(R.id.etPassword);
        final Button bLogin = (Button) findViewById(R.id.bSignIn);
        bLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String username = etUsername.getText().toString();
                final String password = etPassword.getText().toString();
                Response.Listener<String> presponselistener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject pjsonResponse = new JSONObject(response);
                            boolean success = pjsonResponse.getBoolean("success");
                            if (success) {
                                String name = pjsonResponse.getString("name");
                                int age = pjsonResponse.getInt("age");
                                String sex=pjsonResponse.getString("sex");
                                String phone=pjsonResponse.getString("phone");
				                String image=pjsonResponse.getString("image");
                                String past=pjsonResponse.getString("past");
                                String dateofbirth=pjsonResponse.getString("dateofbirth");
                                String blood=pjsonResponse.getString("blood");
                                Intent intent = new Intent(MainActivity.this, UserAreaActivity.class);
                                SharedPreferences sharedPreferences = MainActivity.this.getSharedPreferences("login", MainActivity.MODE_PRIVATE);
        			SharedPreferences.Editor editor = sharedPreferences.edit();
				editor.putBoolean("loggedin", true);
        			editor.putString("name",name);
        			editor.putInt("age",age);
        			editor.putString("sex",sex);editor.putString("username",username);
                                editor.putString("phone",phone);
        			editor.putString("image",image);
                                editor.putString("blood",blood);
                                editor.putString("dateofbirth",dateofbirth);
                                editor.putString("past",past);

                                editor.commit();
                                MainActivity.this.startActivity(intent);
                            } else {
                                CharSequence text="wrong user name or pass word";
                                int duration = Toast.LENGTH_SHORT;
                                Toast toast = Toast.makeText(MainActivity.this, text, duration);
                                toast.show();
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                };
                ploginrequest loginRequest = new ploginrequest(username, password, presponselistener);
                RequestQueue queue = Volley.newRequestQueue(MainActivity.this);
                queue.add(loginRequest);
            }
        });
    }
    @Override
    protected void onResume() {
        super.onResume();
        SharedPreferences sharedPreferences = getSharedPreferences("login",MainActivity.MODE_PRIVATE);
        loggedIn = sharedPreferences.getBoolean("loggedin", false);
        if(loggedIn){
            Intent intent = new Intent(MainActivity.this, UserAreaActivity.class);
            startActivity(intent);
        }
    }

    @Override
    public void onBackPressed() {
        Intent intentback=new Intent(MainActivity.this,LoginActivity.class);
        super.onBackPressed();
        startActivity(intentback);
    }
}
