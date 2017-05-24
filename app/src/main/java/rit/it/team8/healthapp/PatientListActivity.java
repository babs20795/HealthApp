package rit.it.team8.healthapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import rit.it.team8.healthapp.list.resources.Patient;
import rit.it.team8.healthapp.list.resources.PatientAdapter;
import rit.it.team8.healthapp.web.connector.PatientListRequest;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class PatientListActivity extends AppCompatActivity {
    public String doctor_id;
    private ProgressDialog progressDialog;
    private List<Patient> patientList = new ArrayList<>();
    private RecyclerView recyclerView;
    private PatientAdapter mAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_list);
        setTitle("Patient List");
        recyclerView = (RecyclerView) findViewById(R.id.recyclePatient);

        Intent i=getIntent();
        doctor_id=i.getStringExtra("doctor_id");

        mAdapter = new PatientAdapter(patientList ,doctor_id);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
      //  recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);


        prepareMovieData(doctor_id);

    }

    private void prepareMovieData(String doctor_id) {

       progressDialog = new ProgressDialog(PatientListActivity.this);

        progressDialog.setMessage("Please wait...");

        progressDialog.show();




        Response.Listener listener = new Response.Listener<String>() {

            @Override
            public void onResponse(String response)
            {
                try{

                    Log.d("response",response);
                    // Toast.makeText(Login_Activity.this,""+response,Toast.LENGTH_LONG).show();
                    progressDialog.dismiss();

                    JSONArray jsonArray=new JSONArray(response);

                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject=jsonArray.getJSONObject(i);
                        Patient patient=new Patient();
                        patient.setPatient_id(Integer.parseInt(  jsonObject.getString("patient_id")));
                        patient.setPatient_name( jsonObject.getString("patient_name"));
                        patient.setPatient_disease(  jsonObject.getString("patient_disease"));
                        patient.setParient_age(jsonObject.getString("patient_age"));

                        patientList.add(patient);

                    }




                }catch (Exception e){e.printStackTrace();}

            }
        };

        PatientListRequest Request=new PatientListRequest(doctor_id,listener);
        RequestQueue requestQueue= Volley.newRequestQueue(PatientListActivity.this);
        requestQueue.add(Request);

        mAdapter.notifyDataSetChanged();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.patient_menu, menu);
        return true;

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.item_refresh:
                prepareMovieData(doctor_id);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
