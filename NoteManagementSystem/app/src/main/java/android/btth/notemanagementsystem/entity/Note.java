package android.btth.notemanagementsystem.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "note")
public class Note {
    @PrimaryKey(autoGenerate = true)
    public int noteID;

    @ColumnInfo(name ="noteName")
    public String noteName;

    @ColumnInfo(name="catID")
    public int catID;

    @ColumnInfo(name="prioID")
    public int prioID;

    @ColumnInfo(name="sttID")
    public int sttID;

    @ColumnInfo(name="timePlan")
    public String timePlan;

    @ColumnInfo(name="timeCre")
    public String timeCre;
}
