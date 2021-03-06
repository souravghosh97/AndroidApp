package com.example.souravpc.project;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class ploginrequest extends StringRequest {
    private static final String LOGIN_REQUEST_URL = "http://projectmedicapp.net16.net/patientlogin.php";
    public Map<String, String> params;

    public ploginrequest(String username, String password, Response.Listener<String> listener) {
        super(Method.POST, LOGIN_REQUEST_URL, listener, null);
        params = new HashMap<>();
        params.put("username", username);
        params.put("password", password);
    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}
