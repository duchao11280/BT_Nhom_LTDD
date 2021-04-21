package android.btth.notemanagementsystem;

import android.btth.notemanagementsystem.dao.CategoryDao;
import android.btth.notemanagementsystem.dao.NoteDao;
import android.btth.notemanagementsystem.dao.PriorityDao;
import android.btth.notemanagementsystem.dao.StatusDao;
import android.btth.notemanagementsystem.entity.Category;
import android.btth.notemanagementsystem.entity.Note;
import android.btth.notemanagementsystem.entity.NoteDetails;
import android.btth.notemanagementsystem.entity.Priority;
import android.btth.notemanagementsystem.entity.Status;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Category.class, Priority.class, Status.class, Note.class},
        views = {NoteDetails.class},version =1 )
public abstract class AppDatabase extends RoomDatabase {
    public abstract CategoryDao categoryDao();
    public abstract PriorityDao priorityDao();
    public abstract StatusDao statusDao();
    public abstract NoteDao noteDao();
}
