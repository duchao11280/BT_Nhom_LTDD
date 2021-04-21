package android.btth.notemanagementsystem.entity;

import androidx.room.DatabaseView;

@DatabaseView("SELECT noteID,noteName,catName,prioName,sttName,timePlan,note.timeCre " +
                " FROM note,category,priority,status "+
                " Where note.catID= category.catID and note.prioID = priority.prioID and note.sttID= status.sttID")
public class NoteDetails {
    public int noteID;
    public String noteName,catName,prioName,sttName,timePlane,timeCre;

}
