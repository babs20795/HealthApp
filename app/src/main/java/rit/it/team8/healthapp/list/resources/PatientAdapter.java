package rit.it.team8.healthapp.list.resources;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import rit.it.team8.healthapp.PatientDoctorActivity;

import java.util.List;

/**
 * Created by hp on 5/15/2017.
 */

public class PatientAdapter extends RecyclerView.Adapter<PatientAdapter.MyViewHolder> {

     List<Patient> patientList;
    String doctor_id;
    public PatientAdapter(List<Patient> patientList,String doctor_id) {
        this.patientList = patientList;
        this.doctor_id=doctor_id;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView patient_id, name, age,disease;

        public MyViewHolder(final View view) {
            super(view);
            patient_id = (TextView) view.findViewById(rit.it.team8.healthapp.R.id.tvPatientId);
            name= (TextView) view.findViewById(rit.it.team8.healthapp.R.id.tvPatientName);
            age = (TextView) view.findViewById(rit.it.team8.healthapp.R.id.tvPatientAge);
            disease = (TextView) view.findViewById(rit.it.team8.healthapp.R.id.tvPatientDisease);

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Patient patient=patientList.get(getAdapterPosition());
                    Intent i=new Intent(view.getContext(), PatientDoctorActivity.class);
                    i.putExtra("patient_id",patient.getPatient_id()+"");
                    i.putExtra("doctor_id",doctor_id);
                    view.getContext().startActivity(i);

                   // Toast.makeText(view.getContext(),""+patient.getPatient_name(),Toast.LENGTH_LONG).show();
                }
            });
        }
    }



    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(rit.it.team8.healthapp.R.layout.patient_list_row, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Patient patient=patientList.get(position);
        holder.patient_id.setText(patient.getPatient_id()+"");
        holder.name.setText(patient.getPatient_name());
        holder.age.setText("Age : "+patient.getParient_age());
        holder.disease.setText("Disease : "+patient.getPatient_disease());
    }

    @Override
    public int getItemCount() {
       return patientList.size();
    }
}
