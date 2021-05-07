package android.btth.notemanagementsystem.ui.note;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.btth.notemanagementsystem.Adapter.NoteAdapter;
import android.btth.notemanagementsystem.AppDatabase;
import android.btth.notemanagementsystem.R;
import android.btth.notemanagementsystem.entity.Category;
import android.btth.notemanagementsystem.entity.Note;
import android.btth.notemanagementsystem.entity.NoteDetails;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Message;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Arrays;
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
    TextView txtDate;
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
        txtDate =  view.findViewById(R.id.txtDate);

        Calendar cal = Calendar.getInstance();

        String strDate = DateFormat.format("yyyy-MM-dd hh:mm:ss", cal).toString();
        String strPlan = DateFormat.format("d/M/yyyy",cal).toString();
        // Spiner Category
        String[] initCat= {"Select category..."};

        Spinner spnCat = (Spinner) view.findViewById(R.id.spnCat);
        lstCatName = adb.getInstance(getContext()).categoryDao().getCatName();
        // Plus two String



        List lista = new ArrayList(Arrays.asList(initCat));
        lista.addAll(Arrays.asList(lstCatName));
        Object[] a = lista.toArray();




        ArrayAdapter lstCat = new ArrayAdapter(getContext(),android.R.layout.simple_spinner_item,a);
        lstCat.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnCat.setAdapter(lstCat);
        //Spiner Prio

        Spinner spnPrio = (Spinner) view.findViewById(R.id.spnPrio);
        String[] initprio= {"Select priority..."};

        lstPrioName = adb.getInstance(getContext()).priorityDao().getPrioName();
        // Plus two String
        List listb = new ArrayList(Arrays.asList(initprio));
        listb.addAll(Arrays.asList(lstPrioName));
        Object[] b = listb.toArray();

        ArrayAdapter lstPrio = new ArrayAdapter(getContext(),android.R.layout.simple_spinner_item,b);
        lstPrio.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnPrio.setAdapter(lstPrio);
        //Spiner Stt

        String[] initStt= {"Select status..."};
        Spinner spnStt = (Spinner) view.findViewById(R.id.spnStt);
        lstSttName = adb.getInstance(getContext()).statusDao().getSttName();
        // Plus two String
        List listc = new ArrayList(Arrays.asList(initStt));
        listc.addAll(Arrays.asList(lstSttName));
        Object[] c = listc.toArray();

        ArrayAdapter lstStt = new ArrayAdapter(getContext(),android.R.layout.simple_spinner_item,c);
        lstStt.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnStt.setAdapter(lstStt);


        btnClose = view.findViewById(R.id.btnClose);
        btnClose.setOnClickListener(v -> {
            alertDialog.cancel();
        });
        btnTimePlan = view.findViewById(R.id.btnTimePlan);
        txtDate.setText(strPlan);
        btnTimePlan.setText("...");
        btnTimePlan.setOnClickListener(v -> {

            OpenCalender();
        });
        btnAdd = view.findViewById(R.id.btnAdd);
        btnAdd.setOnClickListener(v -> {
            edtNoteName =view.findViewById(R.id.edtNoteName);
            String txtNoteName = edtNoteName.getText().toString();
            int catID = AppDatabase.getInstance(getContext()).categoryDao().getCatIdByCatName(spnCat.getSelectedItem().toString());
            int prioID = AppDatabase.getInstance(getContext()).priorityDao().getPrioIdByPrioName(spnPrio.getSelectedItem().toString());
            int sttID = AppDatabase.getInstance(getContext()).statusDao().getSttIdBySttName(spnStt.getSelectedItem().toString());
            if(edtNoteName.length()==0) {
                edtNoteName.setError("Vui long nhap ten");
                return;
            }
            else
                edtNoteName.setError(null);
            if(catID ==0 ) {
                Toast.makeText(getContext(), "Vui long chon Category", Toast.LENGTH_LONG).show();
                return;
            }
            if(prioID ==0 ) {
                Toast.makeText(getContext(), "Vui long chon Priority", Toast.LENGTH_LONG).show();
                return;
            }
            if(sttID ==0 ){
                Toast.makeText(getContext(),"Vui long chon Status", Toast.LENGTH_LONG).show();
                return;
            }

            Note note = new Note(txtNoteName, catID, prioID, sttID, txtDate.getText().toString(), strDate, userID);
            AppDatabase.getInstance(getContext()).noteDao().insertNote(note);

            noteDetailsList = adb.getInstance(getContext()).noteDao().getNoteByUserID(userID);
            System.out.println("NoteName: "+ txtNoteName);
            noteAdapter = new NoteAdapter(getContext(),noteDetailsList);
            recyclerView.setAdapter(noteAdapter);

            alertDialog.cancel();
        });

    }
    public void OpenCalender()
    {
        //Initialize year, month, day
        Calendar cal = Calendar.getInstance();
        final int year = cal.get(Calendar.YEAR);
        final int month = cal.get(Calendar.MONTH);
        final int day = cal.get(Calendar.DAY_OF_MONTH);
        //Initialize Date Picker Dialog
        DatePickerDialog datePickerDialog = new DatePickerDialog(
                getContext(), R.style.DateTimeDialog,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int day) {

                        //String date = DateFormat.format("EEE, MMMM/d/yyyy",cal).toString();
                        Calendar date = Calendar.getInstance();
                        date.set(year,month,day);
                        String sdate = DateFormat.format("d/M/yyyy",date).toString();
                        //Display Date

                        txtDate.setText(sdate);
                    }
                },year,month,day);
        //Show Date Picker Dialog
        datePickerDialog.setTitle("Select the date");

        datePickerDialog.show();
    }
    public void OpenDialogEdit(Note note, MenuItem item){
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        View view = getLayoutInflater().inflate(R.layout.dialog_add_note,null);
        AlertDialog alertDialog = builder.create();
//        builder.setTitle("dsafhuie").setView(view).show();
        alertDialog.setView(view);
        alertDialog.show();
        //Set Data to add
        txtDate =  view.findViewById(R.id.txtDate);

        NoteDetails ndd = AppDatabase.getInstance(getContext()).noteDao().getNoteDetailByNoteIDID(note.noteID);

        Calendar cal = Calendar.getInstance();

        String strDate = DateFormat.format("yyyy-MM-dd hh:mm:ss", cal).toString();
//        String strPlan =
        // Spiner Category

//        String[] initCat= {"Select category..."};
        String[] initCat= {ndd.catName};

        Spinner spnCat = (Spinner) view.findViewById(R.id.spnCat);



        lstCatName = adb.getInstance(getContext()).categoryDao().getCatName();


        // Plus two String
        List lista = new ArrayList(Arrays.asList(initCat));
        lista.addAll(Arrays.asList(lstCatName));

        int flagfordeletename = 0;
        for (Object abc: lista
        ) {
            if(abc.equals(ndd.catName)){
                 flagfordeletename +=1 ;

                if(flagfordeletename == 2){
                    lista.remove(abc);

                }

            }

        }


        Object[] a = lista.toArray();

        ArrayAdapter lstCat = new ArrayAdapter(getContext(),android.R.layout.simple_spinner_item,a);
        lstCat.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnCat.setAdapter(lstCat);
        //Spiner Prio

        Spinner spnPrio = (Spinner) view.findViewById(R.id.spnPrio);
        String[] initprio= {ndd.prioName};

        lstPrioName = adb.getInstance(getContext()).priorityDao().getPrioName();
        // Plus two String
        List listb = new ArrayList(Arrays.asList(initprio));
        listb.addAll(Arrays.asList(lstPrioName));
        Object[] b = listb.toArray();

        ArrayAdapter lstPrio = new ArrayAdapter(getContext(),android.R.layout.simple_spinner_item,b);
        lstPrio.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnPrio.setAdapter(lstPrio);
        //Spiner Stt

        String[] initStt= {ndd.sttName};
        Spinner spnStt = (Spinner) view.findViewById(R.id.spnStt);
        lstSttName = adb.getInstance(getContext()).statusDao().getSttName();
        // Plus two String
        List listc = new ArrayList(Arrays.asList(initStt));
        listc.addAll(Arrays.asList(lstSttName));
        Object[] c = listc.toArray();

        ArrayAdapter lstStt = new ArrayAdapter(getContext(),android.R.layout.simple_spinner_item,c);
        lstStt.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnStt.setAdapter(lstStt);


        btnClose = view.findViewById(R.id.btnClose);
        btnClose.setOnClickListener(v -> {
            alertDialog.cancel();
        });
        btnTimePlan = view.findViewById(R.id.btnTimePlan);
        txtDate.setText(note.timePlan);
        btnTimePlan.setText("...");
        btnTimePlan.setOnClickListener(v -> {

            OpenCalender();
        });
        btnAdd = view.findViewById(R.id.btnAdd);
        edtNoteName =view.findViewById(R.id.edtNoteName);
        edtNoteName.setText(note.noteName);
        btnAdd.setText("Update");
        btnAdd.setOnClickListener(v -> {
            String txtNoteName = edtNoteName.getText().toString();
            System.out.println(txtNoteName);
            int catID = AppDatabase.getInstance(getContext()).categoryDao().getCatIdByCatName(spnCat.getSelectedItem().toString());
            int prioID = AppDatabase.getInstance(getContext()).priorityDao().getPrioIdByPrioName(spnPrio.getSelectedItem().toString());
            int sttID = AppDatabase.getInstance(getContext()).statusDao().getSttIdBySttName(spnStt.getSelectedItem().toString());
            if(edtNoteName.length()==0) {
                edtNoteName.setError("Vui long nhap ten");
                return;
            }
            else
                edtNoteName.setError(null);
            if(catID ==0 ) {
                Toast.makeText(getContext(), "Vui long chon Category", Toast.LENGTH_LONG).show();
                return;
            }
            if(prioID ==0 ) {
                Toast.makeText(getContext(), "Vui long chon Priority", Toast.LENGTH_LONG).show();
                return;
            }
            if(sttID ==0 ){
                Toast.makeText(getContext(),"Vui long chon Status", Toast.LENGTH_LONG).show();
                return;
            }

            note.noteName = txtNoteName;
            note.catID = catID;
            note.prioID=prioID;
            note.sttID=sttID;
            note.timePlan=txtDate.getText().toString();
            note.timeCre=strDate;
            adb.getInstance(getContext()).noteDao().Update(note);
//
            noteDetailsList = adb.getInstance(getContext()).noteDao().getNoteByUserID(userID);
//            System.out.println("NoteName: "+ txtNoteName);
            noteAdapter = new NoteAdapter(getContext(),noteDetailsList);
            recyclerView.setAdapter(noteAdapter);

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

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
//        Category c = mListCategory.get(item.getGroupId());
        NoteDetails n = noteDetailsList.get(item.getGroupId());
        int noteDetailsID = n.noteID;
        Note note = adb.getInstance(getContext()).noteDao().getNotebyNoteID(noteDetailsID);
        switch (item.getItemId()){
            case 001:
                adb.getInstance(getContext()).noteDao().delete(note);
                noteDetailsList = adb.getInstance(getContext()).noteDao().getNoteByUserID(userID);
                noteAdapter = new NoteAdapter(getContext(),noteDetailsList);
                recyclerView.setAdapter(noteAdapter);
                return true;
            case 002:
                OpenDialogEdit(note,item);
                return true;
        }
        return super.onContextItemSelected(item);
    }

}