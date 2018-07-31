package Model;

import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;

/**
 * Created by wave on 2/2/2018.
 */

public class Playlist
{
    @DatabaseField(generatedId = true)
    private int id;
    @DatabaseField(columnName = "file_id",dataType = DataType.INTEGER)
    private int file_id;
    @DatabaseField(columnName = "filename")
    private String filename;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getFile_id() {
        return file_id;
    }

    public void setFile_id(int file_id) {
        this.file_id = file_id;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }
}
