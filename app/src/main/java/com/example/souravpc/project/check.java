package com.example.souravpc.project;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class check extends StringRequest {
    private static final String REGISTER_REQUEST_URL = "http://rentmapp.epizy.com/usercheck.php";
    public Map<String, String> params;

    public check(String username,String p, Response.Listener<String> listener) {
        super(Method.POST, REGISTER_REQUEST_URL, listener, null);
        params = new HashMap<>();
        params.put("email", username);

    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}
/*chhhhhhhhhhhhhhhhhhhhhhangeeeeeeeeeeeeeeeeeeee*/