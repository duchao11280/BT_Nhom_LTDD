package android.btth.notemanagementsystem.ui.priority;

import android.app.AlertDialog;
import android.btth.notemanagementsystem.Adapter.PrioAdapter;
import android.btth.notemanagementsystem.AppDatabase;
import android.btth.notemanagementsystem.R;
import android.btth.notemanagementsystem.dao.PriorityDao;
import android.btth.notemanagementsystem.entity.Category;
import android.btth.notemanagementsystem.entity.Priority;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class PriorityFragment extends Fragment {

    public RecyclerView rcvPrio;
    private List<Priority> mListPriority;
    private PrioAdapter prioAdapter;
    private FloatingActionButton fbtnPrio;
    private Button btnAddPrio;
    private Button btnClosePrio;

    public PriorityDao priorityDao;

    AppDatabase appDatabase;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_priority, container, false);
        rcvPrio = (RecyclerView)root.findViewById(R.id.rcv_Prio);

        priorityDao = appDatabase.getInstance(getContext()).priorityDao();

        fbtnPrio =(FloatingActionButton)root.findViewById(R.id.fbtnPrio);
        fbtnPrio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                OpenInfoDialog();

            }
        });


        rcvPrio.setHasFixedSize(true);

        prioAdapter = new PrioAdapter();
        mListPriority = new ArrayList<>();


        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        rcvPrio.setLayoutManager(linearLayoutManager);



        //gan cho mlist

        mListPriority = priorityDao.getListPriority();
        prioAdapter.setData(mListPriority);
        rcvPrio.setAdapter(prioAdapter);

        return root;
    }

    public void OpenInfoDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        View view = getLayoutInflater().inflate(R.layout.layout_dalog_prio,null);
        AlertDialog alertDialog = builder.create();

        alertDialog.setView(view);
//        alertDialog.setTitle("Priority Form");
        alertDialog.show();
        btnClosePrio = view.findViewById(R.id.btnClosePrio);
        btnClosePrio.setOnClickListener(v -> {
            alertDialog.cancel();
//            priorityDao.deleteAllPrio();
        });

//        EditText edtCatName = view.findViewById(R.id.edtCat);
//        String txtCatName = edtCatName.getText().toString();
//
//        Calendar cal = Calendar.getInstance();
//
//        String strDate = DateFormat.format("EEE, MMMM/d/yyyy",cal).toString();
//        //kiem tra du lieu co hay ko
//        if(TextUtils.isEmpty(txtCatName) || TextUtils.isEmpty(strDate)){
//            return;
//        }

        btnAddPrio = view.findViewById(R.id.btnAddPrio);
        btnAddPrio.setOnClickListener(v -> {

            EditText edtPrioName = view.findViewById(R.id.edtPrio);
            String txtPrioName = edtPrioName.getText().toString();

            Calendar cal = Calendar.getInstance();

            String strDate = DateFormat.format("yyyy-MM-dd hh:mm:ss",cal).toString();

            priorityDao.insertPrio(new Priority(txtPrioName, strDate));

            mListPriority=priorityDao.getListPriority();
            prioAdapter.setData(mListPriority);
            rcvPrio.setAdapter(prioAdapter);

            alertDialog.cancel();

        });

    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        Priority c = mListPriority.get(item.getGroupId());
        switch (item.getItemId()){
            case 001:
                mListPriority.remove(item.getGroupId());
                appDatabase.getInstance(getContext()).priorityDao().deletePrio(c);
                prioAdapter.notifyDataSetChanged();
                return true;
            case 002:
                OpenInfoDialog2(c,item);
                return true;
        }
        return super.onContextItemSelected(item);
    }

    public void OpenInfoDialog2(Priority c, MenuItem item){
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        View view = getLayoutInflater().inflate(R.layout.layout_dalog_prio,null);
        Button save =view.findViewById(R.id.btnAddPrio);
        EditText edtPrioName = view.findViewById(R.id.edtPrio);
//        System.out.println(newCatName);
        Calendar cal = Calendar.getInstance();

        String strDate = DateFormat.format("yyyy-MM-dd hh:mm:ss",cal).toString();
        save.setText("Save");
        AlertDialog alertDialog = builder.create();
//        builder.setTitle("dsafhuie").setView(view).show();
        alertDialog.setView(view);
//        alertDialog.setTitle("Category Form");
        alertDialog.show();
        save.setOnClickListener(v -> {
            String newPrioName=edtPrioName.getText().toString();
            c.setPrioName(newPrioName);
            c.setTimeCre(strDate);
            appDatabase.getInstance(getContext()).priorityDao().update(c);
            mListPriority.remove(item.getGroupId());
            mListPriority.add(c);
            prioAdapter.notifyDataSetChanged();
            alertDialog.cancel();
        });

        /**
         * su kien nut close
         */
        btnClosePrio = view.findViewById(R.id.btnClosePrio);
        btnClosePrio.setOnClickListener(v -> {
            alertDialog.cancel();
//            categoryDao.deleteAll();
        });




    }

}