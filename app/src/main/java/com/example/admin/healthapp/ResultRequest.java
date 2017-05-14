package com.example.admin.healthapp;

import android.util.Log;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by admin on 08/03/2017.
 */

public class ResultRequest extends StringRequest implements DBRsources {

    private Map<String,String> params;

    public ResultRequest(String p_id, Response.Listener<String> listener){
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
        params.put("p_id",p_id);

    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}

