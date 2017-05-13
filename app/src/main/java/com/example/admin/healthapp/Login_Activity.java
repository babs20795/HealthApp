package com.example.admin.healthapp;

import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

public class Login_Activity extends AppCompatActivity {

    EditText e1,e2;
    Button b1,b2;
    String success;
    RadioButton radioButton, admin,doctor,relative;

    RadioGroup rg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        e1=(EditText)findViewById(R.id.editText_unm);
        e2=(EditText)findViewById(R.id.editText_pass);

        b1=(Button)findViewById(R.id.button_login);
        b2=(Button)findViewById(R.id.button_clear);

        rg=(RadioGroup)findViewById(R.id.radioGroup);

        int selectedId = rg.getCheckedRadioButtonId();

        // find the radiobutton by returned id
        radioButton = (RadioButton) findViewById(selectedId);

        Toast.makeText(Login_Activity.this,
                radioButton.getText(), Toast.LENGTH_SHORT).show();

        /* admin=(RadioButton)findViewById(R.id.admin);
        doctor=(RadioButton)findViewById(R.id.doctor);
        relative=(RadioButton)findViewById(R.id.relatives);
*/
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String unm=e1.getText().toString();
                String pass=e2.getText().toString();


                Response.Listener listener = new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response)
                    {
                        try{

                            Log.d("response",response);
                            JSONObject j=new JSONObject(response);
                            success=j.getString("result");

                            if (success.equals("fail")){
                                AlertDialog.Builder builder=new AlertDialog.Builder(Login_Activity.this);
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

                            }

                        }catch (Exception e){e.printStackTrace();}

                    }
                };

                login loginRequest=new login(unm,pass,listener);
                RequestQueue requestQueue= Volley.newRequestQueue(Login_Activity.this);
                requestQueue.add(loginRequest);


        //xbjdcjhdjhbj


            }
        });



        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                e1.setText("");
                e2.setText("");
            }
        });

    }
}
