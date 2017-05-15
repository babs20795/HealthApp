package com.example.admin.healthapp.web.connector;

import android.util.Log;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by admin on 08/03/2017.
 */

public class RelativeResultRequest extends StringRequest implements DBRsources {

    private Map<String,String> params;

    public RelativeResultRequest(String relatives_id, Response.Listener<String> listener){
        super(Method.POST,RELATIVE_PATIENT,listener,
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
        params.put("relatives_id",relatives_id);

    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}

