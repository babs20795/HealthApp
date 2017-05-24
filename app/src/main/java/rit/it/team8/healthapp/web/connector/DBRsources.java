package rit.it.team8.healthapp.web.connector;

/**
 * Created by Admin on 13-Sep-16.
 */
public interface DBRsources {

    String HOST="http://ravanatar.in:81/Health_Monitoring_System/";

    String LOGIN_REQUEST=HOST+"mobile/Login_Check.php";//"http://192.168.43.31:80/HealthApp/patient_result.php";
    String RELATIVE_PATIENT=HOST+"mobile/Relatives_Home.php";
    String LIST_PATIENT=HOST+"mobile/Doctor_Home.php";
    String DETAILS_PATIENT=HOST+"mobile/Patient_Monitor.php";
}
