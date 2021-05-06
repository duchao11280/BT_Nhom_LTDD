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
import android.widget.Toast;

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
    private int numprio;
    private String m1;
    private String m2;

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

                numprio = priorityDao.getNumberPrio();
                System.out.println(numprio);
                if(numprio<3){
                    OpenInfoDialog();
                }
                else {
                    Toast.makeText(getContext(), "Khong the them Priority vi chi duoc toi da 3 Priority", Toast.LENGTH_LONG).show();

                }

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


            numprio = priorityDao.getNumberPrio();
            System.out.println(txtPrioName);

/**
 * numprio = 0 la ko ca category nao
 * numprio = 1 la co san 1 category
 * numprio = 2 la co san 2 category
 */
            if(numprio == 0 ){
                if(txtPrioName.equals("High")){
                    priorityDao.insertPrio(new Priority( txtPrioName, strDate));
                    mListPriority=priorityDao.getListPriority();
                    prioAdapter.setData(mListPriority);
                    rcvPrio.setAdapter(prioAdapter);

                    alertDialog.cancel();
                    return;
                }
                else if(txtPrioName.equals("Medium")){
                    priorityDao.insertPrio(new Priority( txtPrioName, strDate));
                    mListPriority=priorityDao.getListPriority();
                    prioAdapter.setData(mListPriority);
                    rcvPrio.setAdapter(prioAdapter);

                    alertDialog.cancel();
                    return;
                }
                else if(txtPrioName.equals("Low")){
                    priorityDao.insertPrio(new Priority( txtPrioName, strDate));
                    mListPriority=priorityDao.getListPriority();
                    prioAdapter.setData(mListPriority);
                    rcvPrio.setAdapter(prioAdapter);

                    alertDialog.cancel();
                    return;
                }
                else {
                    Toast.makeText(getContext(), "Khong the them Priority vi them Priority phai nhap 1 trong 3 la High, Medium, Low", Toast.LENGTH_LONG).show();
                    return;
                }
            }
            else if(numprio==1){
                m1 = mListPriority.get(0).prioName;
                System.out.println(m1);

                if(!txtPrioName.equals(m1)){

                    if(txtPrioName.equals("High")){
                        priorityDao.insertPrio(new Priority( txtPrioName, strDate));
                        mListPriority=priorityDao.getListPriority();
                        prioAdapter.setData(mListPriority);
                        rcvPrio.setAdapter(prioAdapter);

                        alertDialog.cancel();
                        return;
                    }
                    else if(txtPrioName.equals("Medium")){
                        priorityDao.insertPrio(new Priority( txtPrioName, strDate));
                        mListPriority=priorityDao.getListPriority();
                        prioAdapter.setData(mListPriority);
                        rcvPrio.setAdapter(prioAdapter);

                        alertDialog.cancel();
                        return;
                    }
                    else if(txtPrioName.equals("Low")){
                        priorityDao.insertPrio(new Priority( txtPrioName, strDate));
                        mListPriority=priorityDao.getListPriority();
                        prioAdapter.setData(mListPriority);
                        rcvPrio.setAdapter(prioAdapter);

                        alertDialog.cancel();
                        return;
                    }
                    else {
                        Toast.makeText(getContext(), "Khong the them Priority vi them Priority phai nhap 1 trong 3 la High, Medium, Low", Toast.LENGTH_LONG).show();
                        return;
                    }

                }else {
                    Toast.makeText(getContext(), "Khong the them Priority vi them Priority phai nhap 1 trong 3 la High, Medium, Low", Toast.LENGTH_LONG).show();
                    return;
                }


            }
            else if(numprio ==2){
                m1 = mListPriority.get(0).prioName;
                m2 = mListPriority.get(1).prioName;
                System.out.println(m1);
                System.out.println(m2);

                if(!txtPrioName.equals(m1) && !txtPrioName.equals(m2)){
                    if(txtPrioName.equals("High")){
                        priorityDao.insertPrio(new Priority( txtPrioName, strDate));
                        mListPriority=priorityDao.getListPriority();
                        prioAdapter.setData(mListPriority);
                        rcvPrio.setAdapter(prioAdapter);

                        alertDialog.cancel();
                        return;
                    }
                    else if(txtPrioName.equals("Medium")){
                        priorityDao.insertPrio(new Priority( txtPrioName, strDate));
                        mListPriority=priorityDao.getListPriority();
                        prioAdapter.setData(mListPriority);
                        rcvPrio.setAdapter(prioAdapter);

                        alertDialog.cancel();
                        return;
                    }
                    else if(txtPrioName.equals("Low")){
                        priorityDao.insertPrio(new Priority( txtPrioName, strDate));
                        mListPriority=priorityDao.getListPriority();
                        prioAdapter.setData(mListPriority);
                        rcvPrio.setAdapter(prioAdapter);

                        alertDialog.cancel();
                        return;
                    }
                    else {
                        Toast.makeText(getContext(), "Khong the them Priority vi them Priority phai nhap 1 trong 3 la High, Medium, Low", Toast.LENGTH_LONG).show();
                        return;
                    }
                }
                else {
                    Toast.makeText(getContext(), "Khong the them Priority vi them Priority phai nhap 1 trong 3 la High, Medium, Low", Toast.LENGTH_LONG).show();
                    return;
                }
            }



//            priorityDao.insertPrio(new Priority(txtPrioName, strDate));

            mListPriority=priorityDao.getListPriority();
            prioAdapter.setData(mListPriority);
            rcvPrio.setAdapter(prioAdapter);

            alertDialog.cancel();

        });

    }
    /**
     * tao context menu cho tung item trong recyclerview
     * @param item
     * @return
     */
    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        Priority c = mListPriority.get(item.getGroupId());
        switch (item.getItemId()){
            /**
             * case 001 la nut delete
             * case 002 la nut edit
             */
            case 001:
                mListPriority.remove(item.getGroupId());
                appDatabase.getInstance(getContext()).priorityDao().deletePrio(c);
                prioAdapter.notifyDataSetChanged();
                return true;
            case 002:
                numprio = priorityDao.getNumberPrio();

                System.out.println(numprio);

                if(numprio<3){
                    OpenInfoDialog2(c,item);
                }
                else {
                    Toast.makeText(getContext(), "Khong the edit Category vi chi duoc toi da 3 category Study, Working, Relax", Toast.LENGTH_LONG).show();
                }

                return true;
        }
        return super.onContextItemSelected(item);
    }
    /**
     * mo dialog khi an nut edit tu 1 item trong priority
     * @param c
     * @param item
     */
    public void OpenInfoDialog2(Priority c, MenuItem item){
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        View view = getLayoutInflater().inflate(R.layout.layout_dalog_prio,null);
        Button save =view.findViewById(R.id.btnAddPrio);
        EditText edtPrioName = view.findViewById(R.id.edtPrio);
//        System.out.println(newCatName);
        Calendar cal = Calendar.getInstance();
        edtPrioName.setText(c.prioName);
        String strDate = DateFormat.format("yyyy-MM-dd hh:mm:ss",cal).toString();
        save.setText("Save");
        AlertDialog alertDialog = builder.create();
//        builder.setTitle("dsafhuie").setView(view).show();
        alertDialog.setView(view);
//        alertDialog.setTitle("Category Form");
        alertDialog.show();
        /**
         * su kien nut save
         */
        save.setOnClickListener(v -> {
            String newPrioName=edtPrioName.getText().toString();


            System.out.println(newPrioName);
            System.out.println(numprio);

        /**
         * numcat = 1 la co san 1 priority trong db
         * numcat = 2 la co san 2 priority trong db
         */
            if(numprio ==1){
                m1 = mListPriority.get(0).prioName;
                System.out.println(m1);

                if(newPrioName.equals("High") || newPrioName.equals("Medium") || newPrioName.equals("Low")){

                    c.setPrioName(newPrioName);
                    c.setTimeCre(strDate);
                    appDatabase.getInstance(getContext()).priorityDao().update(c);
                    mListPriority.remove(item.getGroupId());
                    mListPriority.add(c);
                    prioAdapter.notifyDataSetChanged();
                    alertDialog.cancel();
                }
                else {
                    Toast.makeText(getContext(), "Khong the edit Priority vi phai edit 1 trong 3 High, Medium, Low", Toast.LENGTH_LONG).show();
                    return;
                }
            }
            else if(numprio==2){
                m1 = mListPriority.get(0).prioName;
                m2 = mListPriority.get(1).prioName;

                System.out.println(m1);
                System.out.println(m2);

                if(newPrioName.equals(m1) || newPrioName.equals(m2)){
                    Toast.makeText(getContext(), "Khong the edit Priority vi Priority da ton tai", Toast.LENGTH_LONG).show();
                    return;
                }
                else if(!newPrioName.equals(m1) && !newPrioName.equals(m2)){

                    if(newPrioName.equals("High")){
                        c.setPrioName(newPrioName);
                        c.setTimeCre(strDate);
                        appDatabase.getInstance(getContext()).priorityDao().update(c);
                        mListPriority.remove(item.getGroupId());
                        mListPriority.add(c);
                        prioAdapter.notifyDataSetChanged();
                        alertDialog.cancel();
                    }
                    else if(newPrioName.equals("Medium")){
                        c.setPrioName(newPrioName);
                        c.setTimeCre(strDate);
                        appDatabase.getInstance(getContext()).priorityDao().update(c);
                        mListPriority.remove(item.getGroupId());
                        mListPriority.add(c);
                        prioAdapter.notifyDataSetChanged();
                        alertDialog.cancel();
                    }
                    else if(newPrioName.equals("Low")){
                        c.setPrioName(newPrioName);
                        c.setTimeCre(strDate);
                        appDatabase.getInstance(getContext()).priorityDao().update(c);
                        mListPriority.remove(item.getGroupId());
                        mListPriority.add(c);
                        prioAdapter.notifyDataSetChanged();
                        alertDialog.cancel();
                    }
                    else {
                        Toast.makeText(getContext(), "Khong the edit Priority vi phai edit 1 trong 3 High, Medium, Low", Toast.LENGTH_LONG).show();
                        return;
                    }


                }



            }

/*
            c.setPrioName(newPrioName);
            c.setTimeCre(strDate);
            appDatabase.getInstance(getContext()).priorityDao().update(c);
            mListPriority.remove(item.getGroupId());
            mListPriority.add(c);
            prioAdapter.notifyDataSetChanged();
            alertDialog.cancel();*/

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