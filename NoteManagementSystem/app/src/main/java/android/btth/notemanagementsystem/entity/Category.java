package android.btth.notemanagementsystem.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "category")
public class Category {
    @PrimaryKey(autoGenerate = true)
    public int catID;

    @ColumnInfo(name ="catName")
    public String catName;

    @ColumnInfo(name="timeCre")
    public String timeCre;
}
