package Model;

import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;

/**
 * Created by wave on 2/1/2018.
 */

public class Userdata
{

    @DatabaseField(generatedId = true)
    private int id;
    @DatabaseField(columnName = "username")
    private String username;
    @DatabaseField(columnName = "password")
    private String password;
    @DatabaseField(columnName = "firstname")
    private String firstname;
    @DatabaseField(columnName = "lastname")
    private String lastname;
    @DatabaseField(columnName = "country")
    private String country;
    @DatabaseField(columnName = "email")
    private String email;
    @DatabaseField(columnName = "optimize_photos", dataType = DataType.BOOLEAN)
    private boolean optimize_photos;
    @DatabaseField(columnName = "autoaccept_friend", dataType = DataType.BOOLEAN)
    private boolean autoaccept_friend;

    public Userdata() {

    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getEmail() {
        return email;
    }
/*

    public Userdata(int id, String username, String password, String firstname, String lastname, String country, String email, boolean optimize_photos, boolean autoaccept_friend) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.firstname = firstname;
        this.lastname = lastname;
        this.country = country;
        this.email = email;
        this.optimize_photos = optimize_photos;
        this.autoaccept_friend = autoaccept_friend;
    }
*/

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isOptimize_photos() {
        return optimize_photos;
    }

    public void setOptimize_photos(boolean optimize_photos)
    {
        this.optimize_photos = optimize_photos;
    }

    public boolean isAutoaccept_friend() {
        return autoaccept_friend;
    }

    public void setAutoaccept_friend(boolean autoaccept_friend) {
        this.autoaccept_friend = autoaccept_friend;
    }


}
