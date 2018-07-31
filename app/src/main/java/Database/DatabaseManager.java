package Database;

import android.content.Context;
import android.util.Log;

import com.j256.ormlite.android.apptools.OrmLiteBaseActivity;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.UpdateBuilder;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Model.Userdata;

/**
 * Created by wave on 2/2/2018.
 */

public class DatabaseManager extends OrmLiteBaseActivity {
    private Databasehelper databasehelper;
    public ArrayList<Userdata> users = new ArrayList<>();
    public static DatabaseManager instance;
    public Userdata currentUser;

/*
    public static void init(Context context)
    {
        if (null == instance)
        {
            instance = new DatabaseManager(context);
        }
    }

*/

    public static DatabaseManager getInstance() {
        if (instance == null) {
            instance = new DatabaseManager();
        }
        return instance;
    }

    public void fillContext(Context context1) {

        databasehelper = new Databasehelper(context1);
        Log.e("DBStatus", "Fill Context Called");
    }

    public void add_user(Userdata user) {

        try {
            databasehelper.getUserdataDao().create(user);
            Log.e("aashok", "employess added sucessfully");
            getalluser();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void update_user(String username, String password, String firstname, String lastname, String country, String email, boolean optimize_photos, boolean auto_accept) {
        try {
            UpdateBuilder<Userdata, Integer> updateBuilder = databasehelper.getUserdataDao().updateBuilder();
            updateBuilder.updateColumnValue("password", password);
            updateBuilder.updateColumnValue("firstname", firstname);
            updateBuilder.updateColumnValue("lastname", lastname);
            updateBuilder.updateColumnValue("country", country);
            updateBuilder.updateColumnValue("email", email);
            updateBuilder.updateColumnValue("optimize_photos", optimize_photos);
            updateBuilder.updateColumnValue("autoaccept_friend", auto_accept);
            updateBuilder.where().eq("username", username);
            updateBuilder.update();
            Log.e("iamsolo", "invoked and updated");
            getalluser();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete_user(Userdata userdata) {
        try {
            databasehelper.getUserdataDao().delete(userdata);

            Log.e("deleting", "actiondone");

        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    public ArrayList<Userdata> getalluser() {
        users = new ArrayList<Userdata>();

        try {
            users = (ArrayList<Userdata>) databasehelper.getUserdataDao().queryForAll();
            if (users.size() > 0) {
                currentUser = users.get(0);
                Log.e("current", currentUser.getFirstname());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return users;

    }

    public void delete_allusers(List<Userdata> user_list) {

        try {

            databasehelper.getUserdataDao().delete(user_list);
            Log.e("delme", "mama method excute");
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    //get single user
    public List<Userdata> get_user(int id) {
        List<Userdata> list = new ArrayList<>();
        try {
            list = (List<Userdata>) databasehelper.getUserdataDao().queryForId(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}
