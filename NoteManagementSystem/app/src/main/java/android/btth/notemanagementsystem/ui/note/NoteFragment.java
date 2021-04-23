package android.btth.notemanagementsystem.ui.note;

import android.app.AlertDialog;
import android.btth.notemanagementsystem.Adapter.NoteAdapter;
import android.btth.notemanagementsystem.AppDatabase;
import android.btth.notemanagementsystem.R;
import android.btth.notemanagementsystem.entity.NoteDetails;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.Calendar;
import java.util.List;

public class NoteFragment extends Fragment implements AdapterView.OnItemSelectedListener {

    RecyclerView recyclerView;
    NoteAdapter noteAdapter;
    LinearLayoutManager layoutManager;
    List<NoteDetails> noteDetailsList;
    AppDatabase adb;
    Button btnClose,btnAdd,btnTimePlan;
    EditText edtNoteName;
    FloatingActionButton fbtnAddNote;
    private SharedPreferences sharedPreferences;
    int userID;
    String[] lstCatName, lstPrioName,lstSttName;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        sharedPreferences = this.getActivity().getSharedPreferences("dataLogin", Context.MODE_PRIVATE);
        userID = sharedPreferences.getInt("userID",0);
        View root = inflater.inflate(R.layout.fragment_note, container, false);
        recyclerView = (RecyclerView)root.findViewById(R.id.rcvNote);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);

//        AppDatabase.getInstance(getContext()).categoryDao().insertCat(new Category("huhucat","aa"));
//        AppDatabase.getInstance(getContext()).priorityDao().insertCat(new Priority("huhuprio","aa"));
//        AppDatabase.getInstance(getContext()).statusDao().insertCat(new Status("sttt","aa"));
        fbtnAddNote =(FloatingActionButton)root.findViewById(R.id.fbtnAddNote);
        fbtnAddNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                OpenInfoDialog();

            }
        });


//        AppDatabase.getInstance(getContext()).noteDao().deleteAll();
        noteDetailsList = adb.getInstance(getContext()).noteDao().getNoteByUserID(userID);

        noteAdapter = new NoteAdapter(getContext(),noteDetailsList);
        recyclerView.setAdapter(noteAdapter);

        return root;
    }
    public void OpenInfoDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        View view = getLayoutInflater().inflate(R.layout.dialog_add_note,null);
        AlertDialog alertDialog = builder.create();
//        builder.setTitle("dsafhuie").setView(view).show();
        alertDialog.setView(view);
        alertDialog.show();
        //Set Data to add
        edtNoteName = view.findViewById(R.id.edtNoteName);
        String txtNoteName = edtNoteName.getText().toString();

        Calendar cal = Calendar.getInstance();

        String strDate = DateFormat.format("EEE, MMMM/d/yyyy", cal).toString();

        // Spiner Category
        Spinner spnCat = (Spinner) view.findViewById(R.id.spnCat);
        lstCatName = adb.getInstance(getContext()).categoryDao().getCatName();
        ArrayAdapter lstCat = new ArrayAdapter(getContext(),android.R.layout.simple_spinner_item,lstCatName);
        lstCat.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnCat.setAdapter(lstCat);
        //Spiner Prio

        Spinner spnPrio = (Spinner) view.findViewById(R.id.spnPrio);
        lstPrioName = adb.getInstance(getContext()).priorityDao().getPrioName();

        ArrayAdapter lstPrio = new ArrayAdapter(getContext(),android.R.layout.simple_spinner_item,lstPrioName);
        lstPrio.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnPrio.setAdapter(lstPrio);
        //Spiner Stt
        Spinner spnStt = (Spinner) view.findViewById(R.id.spnStt);
        lstSttName = adb.getInstance(getContext()).statusDao().getSttName();

        ArrayAdapter lstStt = new ArrayAdapter(getContext(),android.R.layout.simple_spinner_item,lstSttName);
        lstStt.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnStt.setAdapter(lstStt);


        btnClose = view.findViewById(R.id.btnClose);
        btnClose.setOnClickListener(v -> {
                    alertDialog.cancel();
        });
        btnAdd = view.findViewById(R.id.btnAdd);
        btnAdd.setOnClickListener(v -> {

//            Note note = new Note("Lancuo",catID,prioID,sttID,"22/04/2022",strDate,userID);
//            AppDatabase.getInstance(getContext()).noteDao().insertNote(note);
//
//            noteDetailsList = adb.getInstance(getContext()).noteDao().getNoteByUserID(userID);
//
//            noteAdapter = new NoteAdapter(getContext(),noteDetailsList);
//            recyclerView.setAdapter(noteAdapter);

            alertDialog.cancel();
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(getContext(),lstCatName[position] , Toast.LENGTH_LONG).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}