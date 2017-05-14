package com.example.admin.healthapp;

import android.net.Uri;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;

import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {
    String success;
    public static EditText e1;
    Button b1;
    TextView p_name,p_temp,p_pulse,p_fall,t1,t2,t3;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        p_name=(TextView) findViewById(R.id.p_name);
        p_temp=(TextView)findViewById(R.id.p_temp);
        p_pulse=(TextView)findViewById(R.id.p_pulse);
        p_fall=(TextView)findViewById(R.id.p_fall);

        t1=(TextView)findViewById(R.id.textView12);
        t2=(TextView)findViewById(R.id.textView14);
        t3=(TextView)findViewById(R.id.textView2);

        e1 = (EditText) findViewById(R.id.editText_patient_id);
        b1 = (Button) findViewById(R.id.button_see_patient_result);
            b1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {


                        String p_id=e1.getText().toString();

                        Response.Listener listener = new Response.Listener<String>() {

                            @Override
                            public void onResponse(String response)
                            {
                                try{

                                    Log.d("response",response);
                                    JSONObject j=new JSONObject(response);
                                    success=j.getString("result");

                                    if (success.equals("fail")){
                                        AlertDialog.Builder builder=new AlertDialog.Builder(MainActivity.this);
                                        builder.setMessage("Login fail")
                                                .setNegativeButton("Retry",null)
                                                .create()
                                                .show();
                                    }
                                    else
                                    {
                                        JSONObject js=new JSONObject(success);
                                        //Intent i = new Intent(MainActivity.this, Result.class);
                                        //startActivity(i);

                                        Toast.makeText(getApplicationContext(),"Temp="+js.getString("temp")+"\nPulse="+js.getString("pulse"),Toast.LENGTH_SHORT).show();
                                        p_temp.setText(js.getString("temp"));
                                        p_pulse.setText(js.getString("pulse"));
                                        p_fall.setText(js.getString("accel"));
                                        p_name.setText(js.getString("p_name"));
                                    }

                                }catch (Exception e){e.printStackTrace();}

                            }
                        };

                        ResultRequest resultRequest =new ResultRequest(p_id,listener);
                        RequestQueue requestQueue= Volley.newRequestQueue(MainActivity.this);
                        requestQueue.add(resultRequest);

                }
            });
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    public Action getIndexApiAction() {
        Thing object = new Thing.Builder()
                .setName("Main Page") // TODO: Define a title for the content shown.
                // TODO: Make sure this auto-generated URL is correct.
                .setUrl(Uri.parse("http://[ENTER-YOUR-URL-HERE]"))
                .build();
        return new Action.Builder(Action.TYPE_VIEW)
                .setObject(object)
                .setActionStatus(Action.STATUS_TYPE_COMPLETED)
                .build();
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        AppIndex.AppIndexApi.start(client, getIndexApiAction());
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        AppIndex.AppIndexApi.end(client, getIndexApiAction());
        client.disconnect();
    }
}
