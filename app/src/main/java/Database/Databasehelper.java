package Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Model.Device;
import Model.Friends;
import Model.Playlist;
import Model.Userdata;

/**
 * Created by Rajashekar Reddy on 2/1/2018.
 */

public class Databasehelper extends OrmLiteSqliteOpenHelper {
    private static final String DATABASE_NAME = "login.db";
    private static final int DATABASE_VERSION = 1;
    private Dao<Userdata, Integer> userdataDao = null;

    ConnectionSource objConnectionSource;

    public Databasehelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource) {
        try {
            TableUtils.createTable(connectionSource, Userdata.class);
            Log.e("table status", "creation done");


            userdataDao = DaoManager.createDao(connectionSource, Userdata.class);
            objConnectionSource = connectionSource;

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onUpgrade(SQLiteDatabase database, ConnectionSource connectionSource, int oldVersion, int newVersion) {

        try {
            TableUtils.dropTable(connectionSource, Userdata.class, true);
           /* List<String> allsql = new ArrayList<>();
            for (String sql : allsql)
            {
                database.execSQL(sql);
            }*/
            onCreate(database, connectionSource);

            objConnectionSource = connectionSource;

        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    public Dao<Userdata, Integer> getUserdataDao() {
        if (userdataDao == null) {
            try {
                userdataDao = getDao(Userdata.class);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return userdataDao;
    }

}
