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
    List<Status> getListStatus();

    @Insert
    void insertStatus(Status status);

    @Delete
    void delete(Status status);

    @Query("DELETE FROM status")
    void deleteAllStatus();

    @Query("Select sttName From status")
    String[] getSttName();

    @Query("select sttName from status where sttID=:sID")
    String getStatusNameBySttID(int sID);
}
