package android.btth.notemanagementsystem.dao;

import android.btth.notemanagementsystem.entity.Note;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface NoteDao {
    @Query("SELECT * FROM note")
    List<Note> getAll();

    @Insert
    void insertCat(Note note);

    @Delete
    void delete(Note note);
}
