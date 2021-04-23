package android.btth.notemanagementsystem.dao;


import android.btth.notemanagementsystem.entity.Status;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface StatusDao {
    @Query("SELECT * FROM status")
    List<Status> getAll();

    @Insert
    void insertCat(Status status);

    @Delete
    void delete(Status status);

    @Query("Select sttName From status")
    String[] getSttName();
}
