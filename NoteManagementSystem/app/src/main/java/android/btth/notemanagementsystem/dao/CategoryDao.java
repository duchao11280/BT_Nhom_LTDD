package android.btth.notemanagementsystem.dao;

import android.btth.notemanagementsystem.entity.Category;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface CategoryDao {
    @Query("SELECT * FROM category")
    List<Category> getAll();

    @Insert
    void insertCat(Category category);

    @Delete
    void delete(Category category);
}
