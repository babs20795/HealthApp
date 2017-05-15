package com.example.admin.healthapp;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.example.admin.healthapp.web.connector.PatientDetailsRequest;
import com.example.admin.healthapp.web.connector.RelativeResultRequest;

import org.json.JSONObject;

public class PatientDoctorActivity extends AppCompatActivity {

    String success;
    public static EditText e1;
    Button b1;
    TextView p_name,p_temp,p_pulse,p_fall,t1,t2,t3;
    static String patient_id,doctor_id;
    Handler handler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_doctor);
        setTitle("Patient Details");

        p_name=(TextView) findViewById(R.id.p_name);
        p_temp=(TextView)findViewById(R.id.p_temp);
        p_pulse=(TextView)findViewById(R.id.p_pulse);
        p_fall=(TextView)findViewById(R.id.p_fall);


        Intent i=getIntent();
        patient_id=i.getStringExtra("patient_id");
        doctor_id=i.getStringExtra("doctor_id");
        GetData(patient_id,doctor_id);

        handler=new Handler();

        handler.postDelayed(runnable,15000);
    }

    private final Runnable runnable=new Runnable() {
        @Override
        public void run() {
            GetData(patient_id,doctor_id);
            handler.postDelayed(runnable,15000);
        }
    };


    private void GetData(String patient_id,String doctor_id){


        Response.Listener listener = new Response.Listener<String>() {

            @Override
            public void onResponse(String response)
            {
                try{

                    Log.d("response",response);
                     //Toast.makeText(PatientDoctorActivity.this,response,Toast.LENGTH_LONG).show();

                    JSONObject j=new JSONObject(response);
                    success=j.getString("success");

                    if (success.equals("true")){

                        //  Toast.makeText(getApplicationContext(),"Temp="+j.getString("temp")+"\nPulse="+j.getString("pulse"),Toast.LENGTH_SHORT).show();
                        p_temp.setText(j.getString("temp_cels"));
                        p_pulse.setText(j.getString("pulse"));
                        p_fall.setText(j.getString("accel"));
                        p_name.setText(j.getString("patient_name"));

                    }
                    else
                    {

                        AlertDialog.Builder builder=new AlertDialog.Builder(PatientDoctorActivity.this);
                        builder.setMessage("Login fail")
                                .setNegativeButton("Retry",null)
                                .create()
                                .show();
                    }

                }catch (Exception e){e.printStackTrace();}

            }
        };

        PatientDetailsRequest Request =new PatientDetailsRequest(patient_id,doctor_id,listener);
        RequestQueue requestQueue= Volley.newRequestQueue(PatientDoctorActivity.this);
        requestQueue.add(Request);

    }
}
