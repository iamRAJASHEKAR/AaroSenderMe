package Model;

import com.j256.ormlite.field.DatabaseField;

/**
 * Created by Rajashekar Reddy on 2/2/2018.
 */

public class Device
{
    @DatabaseField(generatedId = true)
    private int id;
    @DatabaseField(columnName = "device_id")
    private int device_id;

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public int getDevice_id()
    {
        return device_id;
    }

    public void setDevice_id(int device_id)
    {
        this.device_id = device_id;
    }

}

