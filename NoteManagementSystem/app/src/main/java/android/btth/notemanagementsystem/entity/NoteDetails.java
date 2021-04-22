package android.btth.notemanagementsystem.entity;

import androidx.room.DatabaseView;

@DatabaseView("SELECT noteID,noteName,catName,prioName,sttName,timePlan,note.timeCre " +
                " FROM note,category,priority,status "+
                " Where note.catID= category.catID and note.prioID = priority.prioID and note.sttID= status.sttID")
public class NoteDetails {
    public int noteID;
    public String noteName,catName,prioName,sttName,timePlane,timeCre;

    public int getNoteID() {
        return noteID;
    }

    public String getNoteName() {
        return noteName;
    }

    public String getCatName() {
        return catName;
    }

    public String getPrioName() {
        return prioName;
    }

    public String getSttName() {
        return sttName;
    }

    public String getTimePlane() {
        return timePlane;
    }

    public String getTimeCre() {
        return timeCre;
    }
}
