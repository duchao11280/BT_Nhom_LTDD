package android.btth.notemanagementsystem.ui.category;

import android.app.AlertDialog;
import android.btth.notemanagementsystem.Adapter.CatAdapter;
import android.btth.notemanagementsystem.AppDatabase;
import android.btth.notemanagementsystem.R;
import android.btth.notemanagementsystem.dao.CategoryDao;
import android.btth.notemanagementsystem.entity.Category;
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

public class CategoryFragment extends Fragment {

    public RecyclerView rcvCat;
    private List<Category> mListCategory;
    private CatAdapter catAdapter;
    private FloatingActionButton fbtnCat;
    private Button btnAdd;
    private Button btnClose;

    public CategoryDao categoryDao;

    AppDatabase appDatabase;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        /**
         * tao layout recylerview
         */
        View root = inflater.inflate(R.layout.fragment_category, container, false);
        rcvCat = (RecyclerView)root.findViewById(R.id.rcv_Cat);

//        appDatabase.getInstance(getContext()).categoryDao().insertCat(new Category("dsafgeywyugdddd","uueueiwueiwueiw"));

        /**
         *  lay cac method tu DAO
         */
        categoryDao = appDatabase.getInstance(getContext()).categoryDao();

        /**
         *  mo 1 dialog khi an vao floating button
         */
        fbtnCat =(FloatingActionButton)root.findViewById(R.id.fbtnCat);
        fbtnCat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                OpenInfoDialog();

            }
        });




        rcvCat.setHasFixedSize(true);

        catAdapter = new CatAdapter();
        mListCategory = new ArrayList<>();

//        catAdapter.setData(mListCategory);
        /**
         * tao va set layout cho recylerview
         */
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        rcvCat.setLayoutManager(linearLayoutManager);
//        rcvCat.setAdapter(catAdapter);


        //gan cho mlist
//        mListCategory= CategoryDatabase.getInstance(getContext()).categoryDao().getListCategory();

        /**
         * Lay du lieu tu list category
         */
        mListCategory = categoryDao.getListCategory();
        /**
         * dua du lieu vo adapter va chuyen vao recyclerview
         */
        catAdapter.setData(mListCategory);
        rcvCat.setAdapter(catAdapter);

        return root;
    }

    /**
     * tao mot alertdialog
     */
    public void OpenInfoDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        View view = getLayoutInflater().inflate(R.layout.layout_dialog,null);
        AlertDialog alertDialog = builder.create();
//        builder.setTitle("dsafhuie").setView(view).show();
        alertDialog.setView(view);
        alertDialog.setTitle("Category Form");
        alertDialog.show();

        /**
         * su kien nut close
         */
        btnClose = view.findViewById(R.id.btnClose);
        btnClose.setOnClickListener(v -> {
            alertDialog.cancel();
//            categoryDao.deleteAll();
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
/**
 * su kien nut add voi 1 category
 */
        btnAdd = view.findViewById(R.id.btnAdd);
        btnAdd.setOnClickListener(v -> {

            /**
             * anh xa du lieu tu view
             */
            EditText edtCatName = view.findViewById(R.id.edtCat);
            String txtCatName = edtCatName.getText().toString();

            Calendar cal = Calendar.getInstance();

            String strDate = DateFormat.format("EEE, MMMM/d/yyyy",cal).toString();

            /**
             * them du lieu va roomdatabase
             */
            categoryDao.insertCat(new Category( txtCatName, strDate));
            /**
             * cap nhat lai va hien thi len recyclerview
             */
            mListCategory=categoryDao.getListCategory();
            catAdapter.setData(mListCategory);
            rcvCat.setAdapter(catAdapter);

            alertDialog.cancel();

        });

    }


    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){
            case 001:
                catAdapter.deleteitem(item.getGroupId());
                return true;
            case 002:
                return true;
        }
        return super.onContextItemSelected(item);
    }



}