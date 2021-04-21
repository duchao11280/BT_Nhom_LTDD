package android.btth.notemanagementsystem.dao;


import android.btth.notemanagementsystem.entity.Priority;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface PriorityDao {
    @Query("SELECT * FROM priority")
    List<Priority> getAll();

    @Insert
    void insertCat(Priority priority);

    @Delete
    void delete(Priority priority);
}
