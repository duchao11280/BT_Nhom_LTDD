package android.btth.notemanagementsystem.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "priority")
public class Priority {
    @PrimaryKey(autoGenerate = true)
    public int prioID;

    @ColumnInfo(name ="prioName")
    public String prioName;

    @ColumnInfo(name="timeCre")
    public String timeCre;
}
