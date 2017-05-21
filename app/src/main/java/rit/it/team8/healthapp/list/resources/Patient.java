package rit.it.team8.healthapp.list.resources;

/**
 * Created by hp on 5/15/2017.
 */

public class Patient {
    private int patient_id;
    private String patient_name;
    private String parient_age;
    private String patient_disease;

    public Patient() {
        this.patient_id = 0;
        this.patient_name = "none";
        this.parient_age = "55";
        this.patient_disease = "none";
    }

    public Patient(int patient_id, String patient_name, String parient_age, String patient_disease) {
        this.patient_id = patient_id;
        this.patient_name = patient_name;
        this.parient_age = parient_age;
        this.patient_disease = patient_disease;
    }

    public int getPatient_id() {
        return patient_id;
    }

    public void setPatient_id(int patient_id) {
        this.patient_id = patient_id;
    }

    public String getPatient_name() {
        return patient_name;
    }

    public void setPatient_name(String patient_name) {
        this.patient_name = patient_name;
    }

    public String getParient_age() {
        return parient_age;
    }

    public void setParient_age(String parient_age) {
        this.parient_age = parient_age;
    }

    public String getPatient_disease() {
        return patient_disease;
    }

    public void setPatient_disease(String patient_disease) {
        this.patient_disease = patient_disease;
    }

    @Override
    public String toString() {
        return "Patient{" +
                "patient_id=" + patient_id +
                ", patient_name='" + patient_name + '\'' +
                ", parient_age='" + parient_age + '\'' +
                ", patient_disease='" + patient_disease + '\'' +
                '}';
    }


}
