package com.example.souravpc.project;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import android.net.Uri;
import android.provider.MediaStore;
import java.io.IOException;
import android.util.Base64;
import java.io.ByteArrayOutputStream;
import java.util.Random;

import org.json.JSONException;
import org.json.JSONObject;
public class RegisterActivity extends AppCompatActivity {
private int PICK_IMAGE_REQUEST=1;
    ImageView imageview;
    Bitmap bitmap;
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            Uri filePath = data.getData();
            try {
                //Getting the Bitmap from Gallery
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);
                //Setting the Bitmap to ImageView
                imageview.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    public String getStringImage(Bitmap bmp){
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.JPEG, 20, baos);
        byte[] imageBytes = baos.toByteArray();
        String encodedImage = Base64.encodeToString(imageBytes, Base64.DEFAULT);
        return encodedImage;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final ListView listView = (ListView)findViewById(R.id.blood);
        setContentView(R.layout.activity_register);
        final EditText etAge = (EditText) findViewById(R.id.etAge);
        final EditText etName = (EditText) findViewById(R.id.etName);
        final EditText etUsername = (EditText) findViewById(R.id.etUsername);
        final EditText etPassword = (EditText) findViewById(R.id.etPassword);
        final EditText phone=(EditText) findViewById(R.id.phone);
        final EditText etPassword1=(EditText)findViewById(R.id.etPassword1);
        final EditText bd=(EditText)findViewById(R.id.bd);
        final Button bRegister = (Button) findViewById(R.id.bRegister);
        final Button button5=(Button)findViewById(R.id.button5);
        imageview=(ImageView)findViewById(R.id.imageView2);
        final EditText bloodpressure = (EditText) findViewById(R.id.blood);
        final RadioGroup radioSexGroup=(RadioGroup)findViewById(R.id.radio);
        final EditText doctoruser=(EditText)findViewById(R.id.doctoruser);
        button5.setOnClickListener(new View.OnClickListener(){

          @Override
            public void onClick(View v)
          {
              Intent intent = new Intent();
              intent.setType("image/*");
              intent.setAction(Intent.ACTION_GET_CONTENT);
              startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);

          }

        });
        bRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String name = etName.getText().toString();
                final String username = etUsername.getText().toString();
                final int age = Integer.parseInt(etAge.getText().toString());
                final String password = etPassword.getText().toString();
                final String image=getStringImage(bitmap);
                final String Phone=phone.getText().toString();
                final String doctorusername=doctoruser.getText().toString();
                final String dateofbirth=bd.getText().toString();
                int selectedId=radioSexGroup.getCheckedRadioButtonId();
                final String blood=bloodpressure.getText().toString();
                RadioButton radioSexButton=(RadioButton)findViewById(selectedId);
                final String sex=radioSexButton.getText().toString();
                final String repeat=etPassword1.getText().toString();
                if(name.trim().equals("") || username.trim().equals("") || password.trim().equals("") || etAge.getText().toString().trim().equals("")){
                    CharSequence text="Fill up the fields";
                    int duration = Toast.LENGTH_SHORT;
                    Toast toast = Toast.makeText(RegisterActivity.this, text, duration);
                    toast.show();
                }
                else
                {
                    if (password.equals(repeat))
                {
                    Random rand = new Random();
                    int pickedNumber = rand.nextInt(1000) + (rand.nextInt(9)+1)*1000;
                    SharedPreferences sharedPreferences = RegisterActivity.this.getSharedPreferences("register", RegisterActivity.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("name",name);
                    editor.putInt("age", age);
                    editor.putString("sex",sex);editor.putString("username",username);
                    editor.putString("phone",Phone);
                    editor.putString("image",image);
                    editor.putString("password",password);
                    editor.putString("doctorusername", doctorusername);
                    editor.putString("blood",blood);
                    editor.putString("dateofbirth",dateofbirth);
                    editor.putInt("pickedNumber", pickedNumber);
                    editor.commit();
                    final String message="Your Verification ID is "+(pickedNumber+"");
                    Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonResponse = new JSONObject(response);
                            boolean success =jsonResponse.getBoolean("success");
                            if (success) {
                                try {
                                    SmsManager smsManager = SmsManager.getDefault();
                                    smsManager.sendTextMessage(Phone, null, message, null, null);
                                }catch (Exception e) {
                                    e.printStackTrace();
                                }
                                Intent intent = new Intent(RegisterActivity.this, verificationActivity.class);
                                RegisterActivity.this.startActivity(intent);
                            } else {
                                CharSequence text="Username Already Exists";
                                int duration = Toast.LENGTH_SHORT;
                                Toast toast = Toast.makeText(RegisterActivity.this, text, duration);
                                toast.show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                };
                    check registerrequest = new check(username,"x", responseListener);
                RequestQueue queue1 = Volley.newRequestQueue(RegisterActivity.this);
                queue1.add(registerrequest);
                }
                else{
                    CharSequence text="Give correct password";
                    int duration = Toast.LENGTH_SHORT;
                    Toast toast = Toast.makeText(RegisterActivity.this, text, duration);
                    toast.show();
                }
                }
            }
        });
}
}
