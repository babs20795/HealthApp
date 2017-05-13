package com.example.admin.healthapp;

import android.util.Log;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by admin on 10/03/2017.
 */

public class login extends StringRequest implements DBRsources {

    private Map<String,String> params;

    public login(String unm,String pass,Response.Listener<String> listener){
        super(Method.POST,LOGIN_REQUEST,listener,
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error)
                    {
                        //Toast.makeText(getApplicationContext(), error.toString(), Toast.LENGTH_SHORT).show();
                        Log.d("VallyError",error.toString());
                    }
                }
        );
        params=new HashMap<>();
        params.put("unm",unm);
        params.put("pass",pass);

    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }

}



