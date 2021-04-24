package android.btth.notemanagementsystem.ui.status;

import android.app.AlertDialog;
import android.btth.notemanagementsystem.Adapter.StatusAdapter;
import android.btth.notemanagementsystem.AppDatabase;
import android.btth.notemanagementsystem.R;
import android.btth.notemanagementsystem.dao.StatusDao;
import android.btth.notemanagementsystem.entity.Priority;
import android.btth.notemanagementsystem.entity.Status;
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

public class StatusFragment extends Fragment {


    public RecyclerView rcvStatus;
    private List<Status> mListStatus;
    private StatusAdapter statusAdapter;
    private FloatingActionButton fbtnStatus;
    private Button btnAddStatus;
    private Button btnCloseStatus;

    public StatusDao statusDao;

    AppDatabase appDatabase;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_status, container, false);
        rcvStatus = (RecyclerView)root.findViewById(R.id.rcv_Status);

        statusDao = appDatabase.getInstance(getContext()).statusDao();

        fbtnStatus =(FloatingActionButton)root.findViewById(R.id.fbtnStatus);
        fbtnStatus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                OpenInfoDialog();

            }
        });


        rcvStatus.setHasFixedSize(true);

        statusAdapter = new StatusAdapter();
        mListStatus = new ArrayList<>();


        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        rcvStatus.setLayoutManager(linearLayoutManager);



        //gan cho mlist

        mListStatus = statusDao.getListStatus();
        statusAdapter.setData(mListStatus);
        rcvStatus.setAdapter(statusAdapter);

        return root;
    }



    public void OpenInfoDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        View view = getLayoutInflater().inflate(R.layout.layout_dialog_status,null);
        AlertDialog alertDialog = builder.create();

        alertDialog.setView(view);
//        alertDialog.setTitle("Status Form");
        alertDialog.show();
        btnCloseStatus = view.findViewById(R.id.btnCloseStatus);
        btnCloseStatus.setOnClickListener(v -> {
            alertDialog.cancel();
//            statusDao.deleteAllStatus();
        });



        btnAddStatus = view.findViewById(R.id.btnAddStatus);
        btnAddStatus.setOnClickListener(v -> {

            EditText edtStatusName = view.findViewById(R.id.edtStatus);
            String txtStatusName = edtStatusName.getText().toString();

            Calendar cal = Calendar.getInstance();

            String strDate = DateFormat.format("yyyy-MM-dd hh:mm:ss",cal).toString();

            statusDao.insertStatus(  new Status( txtStatusName, strDate));

            mListStatus=statusDao.getListStatus();
            statusAdapter.setData(mListStatus);
            rcvStatus.setAdapter(statusAdapter);

            alertDialog.cancel();

        });

    }
    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        Status c = mListStatus.get(item.getGroupId());
        switch (item.getItemId()){
            case 001:
                mListStatus.remove(item.getGroupId());
                appDatabase.getInstance(getContext()).statusDao().delete(c);
                statusAdapter.notifyDataSetChanged();
                return true;
            case 002:
                OpenInfoDialog2(c,item);
                return true;
        }
        return super.onContextItemSelected(item);
    }

    public void OpenInfoDialog2(Status c, MenuItem item){
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        View view = getLayoutInflater().inflate(R.layout.layout_dialog_status,null);
        Button save =view.findViewById(R.id.btnAddStatus);
        EditText edtStatusName = view.findViewById(R.id.edtStatus);
//        System.out.println(newCatName);
        Calendar cal = Calendar.getInstance();
        edtStatusName.setText(c.sttName);

        String strDate = DateFormat.format("yyyy-MM-dd hh:mm:ss",cal).toString();
        save.setText("Save");
        AlertDialog alertDialog = builder.create();
//        builder.setTitle("dsafhuie").setView(view).show();
        alertDialog.setView(view);
//        alertDialog.setTitle("Category Form");
        alertDialog.show();
        save.setOnClickListener(v -> {
            String newStatusName=edtStatusName.getText().toString();
            c.setSttName(newStatusName);
            c.setTimeCre(strDate);
            appDatabase.getInstance(getContext()).statusDao().update(c);
            mListStatus.remove(item.getGroupId());
            mListStatus.add(c);
            statusAdapter.notifyDataSetChanged();
            alertDialog.cancel();
        });

        /**
         * su kien nut close
         */
        btnCloseStatus = view.findViewById(R.id.btnCloseStatus);
        btnCloseStatus.setOnClickListener(v -> {
            alertDialog.cancel();
//            categoryDao.deleteAll();
        });




    }
}
