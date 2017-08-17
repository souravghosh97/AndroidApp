package com.example.souravpc.project;

/**
 * Created by Sourav PC on 13-09-2016.
 */
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class updaterequest extends StringRequest {
    private static final String LOGIN_REQUEST_URL = "http://projectmedicapp.net16.net/patientupdate.php";
    public Map<String, String> params;

    public updaterequest(String username, String password,String pressure,Response.Listener<String> listener) {
        super(Method.POST, LOGIN_REQUEST_URL, listener, null);
        params = new HashMap<>();
        params.put("username", username);
        params.put("password", password);
        params.put("blood",pressure);
    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}
