package android.btth.notemanagementsystem.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "status")
public class Status {
    @PrimaryKey(autoGenerate = true)
    public int sttID;

    @ColumnInfo(name ="sttName")
    public String sttName;

    @ColumnInfo(name="timeCre")
    public String timeCre;

    public Status(String sttName, String timeCre) {
        this.sttName = sttName;
        this.timeCre = timeCre;
    }
}
