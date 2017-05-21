package rit.it.team8.healthapp.web.connector;

import android.util.Log;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by hp on 5/15/2017.
 */

public class PatientDetailsRequest extends StringRequest implements DBRsources  {
    private Map<String,String> params;

    public PatientDetailsRequest(String patient_id,String doctor_id, Response.Listener<String> listener){
        super(Method.POST,DETAILS_PATIENT,listener,
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
        params.put("patient_id",patient_id);
        params.put("doctor_id",doctor_id);

    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}
