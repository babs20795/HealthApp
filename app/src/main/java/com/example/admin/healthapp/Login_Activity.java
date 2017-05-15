package com.example.admin.healthapp;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.example.admin.healthapp.web.connector.login;

import org.json.JSONObject;

public class Login_Activity extends AppCompatActivity {

    EditText e1,e2;
    Button b1,b2;
    String success;
    RadioButton radioButton, admin,doctor,relative;

    RadioGroup rg;

    ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);





        b1=(Button)findViewById(R.id.button_login);

        e1=(EditText)findViewById(R.id.editText_unm);
        e2=(EditText)findViewById(R.id.editText_pass);



        rg=(RadioGroup)findViewById(R.id.radioGroup);



        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               /* Intent i=new Intent(Login_Activity.this,MainActivity.class);
                startActivity(i);
                finish();*/
                String unm=e1.getText().toString();
                String pass=e2.getText().toString();

                progressDialog = new ProgressDialog(Login_Activity.this);

                progressDialog.setMessage("Please wait...");

                progressDialog.show();


                int selectedId = rg.getCheckedRadioButtonId();

                // find the radiobutton by returned id
                radioButton = (RadioButton) findViewById(selectedId);

                Response.Listener listener = new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response)
                    {
                        try{

                            Log.d("response",response);
                           // Toast.makeText(Login_Activity.this,""+response,Toast.LENGTH_LONG).show();



                            JSONObject j=new JSONObject(response);
                            success=j.getString("success");

                            if (success.equals("true")){
                                progressDialog.dismiss();
                                if (radioButton.getText().toString().equals("Relatives")) {

                                    String relative_id = j.getString("relatives_id");
                                    Intent i = new Intent(Login_Activity.this, MainActivity.class);
                                    i.putExtra("relative_id", relative_id);
                                    startActivity(i);
                                }
                                else{
                                    String doctor_id = j.getString("doctor_id");
                                    Intent i = new Intent(Login_Activity.this, PatientListActivity.class);
                                    i.putExtra("doctor_id", doctor_id);
                                    startActivity(i);
                                }
                            }
                            else
                            {
                                progressDialog.dismiss();
                                AlertDialog.Builder al=new AlertDialog.Builder(Login_Activity.this);
                                al.setMessage("Login fail ! Please check username and password...")
                                        .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {
                                                dialog.dismiss();
                                            }
                                        })
                                .show();



                            }

                        }catch (Exception e){e.printStackTrace();}

                    }
                };

                login loginRequest=new login(unm,pass,radioButton.getText().toString(),listener);
                RequestQueue requestQueue= Volley.newRequestQueue(Login_Activity.this);
                requestQueue.add(loginRequest);
            }
        });




    }
}
