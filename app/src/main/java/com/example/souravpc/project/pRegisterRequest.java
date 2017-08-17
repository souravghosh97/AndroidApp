package com.example.souravpc.project;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class pRegisterRequest extends StringRequest {
    private static final String REGISTER_REQUEST_URL = "http://projectmedicapp.net16.net/patientregister.php";
    public Map<String, String> params;

    public pRegisterRequest(String name, String username, int age, String password,String phone,String image,String sex,String doctorusername,String blood,String dateofbirth,Response.Listener<String> listener) {
        super(Method.POST, REGISTER_REQUEST_URL, listener, null);
        params = new HashMap<>();
        params.put("name", name);
        params.put("age",age + "");
        params.put("username", username);
        params.put("password", password);
        params.put ("phone",phone);
        params.put("image",image);
        params.put("sex",sex);
        params.put("doctorusername",doctorusername);
        params.put("blood",blood);
        params.put("dateofbirth",dateofbirth);
    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}
