package Serverinterface;

import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import serverobjects.RegistrationServerojects;

/**
 * Created by yeswanth on 2/7/2018.
 */

public interface ServerinterfaceApi
{

    public static String Base_url = "http://192.168.43.158:8080/Aaro/";

    // for Registration
    @FormUrlEncoded
    @POST("regdetails")
    Call<RegistrationServerojects>  regdetails(@Field("regdetails") JSONObject registrationdetails);

    //for login

    @FormUrlEncoded
    @POST("UserLogin3")
    Call<RegistrationServerojects>   logindetails(@Field("logindetails") JSONObject logindetails);


    // for forgot password

    @FormUrlEncoded
    @POST("emailaddres3")
    Call<RegistrationServerojects> forgotpassword(@Field("emailaddress")JSONObject emailaddress);

    // for change password
    @FormUrlEncoded
    @POST("ChangePassword3")
    Call<RegistrationServerojects> changepassword(@Field("changepassworddetails")JSONObject
                                                          changepassworddetails);

    // for feedback

    @FormUrlEncoded
    @POST("feedbackmail")
    Call<RegistrationServerojects> feedback(@Field("feedbackmail")JSONObject feedback);


}
