package com.example.souravpc.project;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.util.Base64;
import android.graphics.BitmapFactory;
import android.graphics.Bitmap;
import android.content.SharedPreferences;
public class UserAreaActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_area);
        SharedPreferences preferences = getSharedPreferences("login",UserAreaActivity.MODE_PRIVATE);
        final String name=preferences.getString("name", "");
        final int age=preferences.getInt("age", -1);
        final String username=preferences.getString("username", "");
        final String phone=preferences.getString("phone","");
	final String sex=preferences.getString("sex","");
	final String encodedImage=preferences.getString("image","");
        final String past=preferences.getString("past","");
        final String dateofbirth=preferences.getString("dateofbirth","");
        final String blood=preferences.getString("blood","");
        byte[] decodedString = Base64.decode(encodedImage, Base64.DEFAULT);
        Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
	TextView tvWelcomeMsg = (TextView) findViewById(R.id.tvWelcomeMsg);
        TextView etUsername = (TextView) findViewById(R.id.etUsername);
        TextView etAge = (TextView) findViewById(R.id.etAge);
        TextView sextv=(TextView)findViewById(R.id.textView6);
        TextView etdateofbirth=(TextView)findViewById(R.id.textView16);
        TextView etblood=(TextView)findViewById(R.id.textView18);
        TextView phonetv=(TextView)findViewById(R.id.textView8);
        ImageView imageview=(ImageView)findViewById(R.id.imageview3);
	imageview.setImageBitmap(decodedByte);        
	String message = name + " welcome to your user area";
        tvWelcomeMsg.setText(message);
        etUsername.setText(username);
        etAge.setText(age + "");
        sextv.setText(sex);
        phonetv.setText(phone);
        etdateofbirth.setText(dateofbirth);
        etblood.setText(blood);
        Button b=(Button)findViewById(R.id.button4);
        Button pasthistory=(Button)findViewById(R.id.pasthistory);
        b.setOnClickListener(new View.OnClickListener(){
            @Override
        public void onClick(View v){
                SharedPreferences preferences = getSharedPreferences("login",UserAreaActivity.MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();
                editor.putBoolean("loggedin", false);
                editor.remove("name");
                editor.remove("age");
                editor.remove("username");
                editor.remove("password");
                editor.remove("image");
                editor.remove("past");
                editor.remove("sex");
                editor.remove("blood");
                editor.remove("dateofbirth");
                editor.commit();
                Intent logouta=new Intent(UserAreaActivity.this,MainActivity.class);
                startActivity(logouta);
            }
        });
        pasthistory.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent seepasthistory=new Intent(UserAreaActivity.this,pasthistoryActivity.class);
                seepasthistory.putExtra("past",past);
                startActivity(seepasthistory);
            }
        });
    }
}
