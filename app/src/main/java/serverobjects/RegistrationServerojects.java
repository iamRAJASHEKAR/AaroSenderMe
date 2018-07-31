package serverobjects;

import com.google.gson.annotations.SerializedName;

import org.json.JSONObject;

/**
 * Created by yeswanth on 2/7/2018.
 */

public class RegistrationServerojects {


    //for registration
    @SerializedName("regdetails")
    private JSONObject registrationdetails;

    //for login
    @SerializedName("logindetails")
    private JSONObject logindetails;


    //for forgot password
    @SerializedName("emailaddress")

    private JSONObject emailaddress;


    //for change password
    @SerializedName("changepassworddetails")
    private JSONObject changepassworddetails;

    // for feedback
    @SerializedName(" feedbackmail")
    private JSONObject feedbackmail;

    @SerializedName("response")
    private String response;

    @SerializedName("message")
    private String message;

    @SerializedName("UserName")
    private String username;

    @SerializedName("EmailAddress")
    private String emailid;

    @SerializedName("Country")
    private String country;

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @SerializedName("FirstName")
    private String firstname;
    @SerializedName("LastName")
    private String lastname;
    @SerializedName("Password")
    private String password;

    public String getEmailid() {
        return emailid;
    }

    public void setEmailid(String emailid) {
        this.emailid = emailid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }


    public JSONObject getRegistrationdetails()
    {
        return registrationdetails;
    }

    public void setRegistrationdetails(JSONObject registrationdetails)
    {
        this.registrationdetails = registrationdetails;
    }
    public JSONObject getLogindetails() {
        return logindetails;
    }

    public void setLogindetails(JSONObject logindetails) {
        this.logindetails = logindetails;
    }

    public JSONObject getEmailaddress() {
        return emailaddress;
    }

    public void setEmailaddress(JSONObject emailaddress) {
        this.emailaddress = emailaddress;
    }

    public JSONObject getChangepassworddetails() {
        return changepassworddetails;
    }

    public void setChangepassworddetails(JSONObject changepassworddetails) {
        this.changepassworddetails = changepassworddetails;
    }

    public JSONObject getFeedbackmail() {
        return feedbackmail;
    }

    public void setFeedbackmail(JSONObject feedbackmail) {
        this.feedbackmail = feedbackmail;
    }

}
