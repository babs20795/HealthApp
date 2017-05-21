package rit.it.team8.healthapp.web.connector;

/**
 * Created by Admin on 13-Sep-16.
 */
public interface DBRsources {

    String host="192.168.43.31:80";

    String LOGIN_REQUEST="https://hms17.000webhostapp.com/mobile/Login_Check.php";//"http://192.168.43.31:80/HealthApp/patient_result.php";
    String RELATIVE_PATIENT="https://hms17.000webhostapp.com/mobile/Relatives_Home.php";
    String LIST_PATIENT="https://hms17.000webhostapp.com/mobile/Doctor_Home.php";
    String DETAILS_PATIENT="https://hms17.000webhostapp.com/mobile/Patient_Monitor.php";
}
