package android.btth.notemanagementsystem.ui.category;

import android.app.AlertDialog;
import android.btth.notemanagementsystem.Adapter.CatAdapter;
import android.btth.notemanagementsystem.AppDatabase;
import android.btth.notemanagementsystem.R;
import android.btth.notemanagementsystem.dao.CategoryDao;
import android.btth.notemanagementsystem.entity.Category;
import android.btth.notemanagementsystem.entity.Status;
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

public class CategoryFragment extends Fragment {

    public RecyclerView rcvCat;
    private List<Category> mListCategory;
    private CatAdapter catAdapter;
    private FloatingActionButton fbtnCat;
    private Button btnAdd;
    private Button btnClose;
    private int numcat;
    private String m1;
    private String m2;


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

                numcat = categoryDao.getNumberCat();
                System.out.println(numcat);
                if(numcat<3){
                    OpenInfoDialog();
                }
                else {
                    Toast.makeText(getContext(), "Khong the them Category vi chi duoc toi da 3 category", Toast.LENGTH_LONG).show();

                }



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
//        alertDialog.setTitle("Category Form");
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
            String strDate = DateFormat.format("yyyy-MM-dd hh:mm:ss",cal).toString();

//            System.out.println(mListCategory.get(0).catName);
//            System.out.println(mListCategory.get(1).catName);

            numcat = categoryDao.getNumberCat();

            System.out.println(txtCatName);

            /**
             * numcat = 0 la ko ca category nao
             * numcat = 1 la co san 1 category
             * numcat = 2 la co san 2 category
             */
            if(numcat == 0 ){
                if(txtCatName.equals("Study")){
                    categoryDao.insertCat(new Category( txtCatName, strDate));
                    mListCategory=categoryDao.getListCategory();
                    catAdapter.setData(mListCategory);
                    rcvCat.setAdapter(catAdapter);

                    alertDialog.cancel();
                    return;
                }
                else if(txtCatName.equals("Working")){
                    categoryDao.insertCat(new Category( txtCatName, strDate));
                    mListCategory=categoryDao.getListCategory();
                    catAdapter.setData(mListCategory);

                    rcvCat.setAdapter(catAdapter);

                    alertDialog.cancel();
                    return;
                }
                else if(txtCatName.equals("Relax")){
                    categoryDao.insertCat(new Category( txtCatName, strDate));
                    mListCategory=categoryDao.getListCategory();
                    catAdapter.setData(mListCategory);
                    rcvCat.setAdapter(catAdapter);

                    alertDialog.cancel();
                    return;
                }
                else {
                    Toast.makeText(getContext(), "Khong the them Category vi them Category phai nhap 1 trong 3 la Study, Working, Relax", Toast.LENGTH_LONG).show();
                    return;
                }
            }
            else if(numcat==1){
                m1 = mListCategory.get(0).catName;
                System.out.println(m1);

                if(!txtCatName.equals(m1)){

                    if(txtCatName.equals("Study")){
                        categoryDao.insertCat(new Category( txtCatName, strDate));
                        mListCategory=categoryDao.getListCategory();
                        catAdapter.setData(mListCategory);
                        rcvCat.setAdapter(catAdapter);

                        alertDialog.cancel();
                        return;
                    }
                    else if(txtCatName.equals("Working")){
                        categoryDao.insertCat(new Category( txtCatName, strDate));
                        mListCategory=categoryDao.getListCategory();
                        catAdapter.setData(mListCategory);

                        rcvCat.setAdapter(catAdapter);

                        alertDialog.cancel();
                        return;
                    }
                    else if(txtCatName.equals("Relax")){
                        categoryDao.insertCat(new Category( txtCatName, strDate));
                        mListCategory=categoryDao.getListCategory();
                        catAdapter.setData(mListCategory);
                        rcvCat.setAdapter(catAdapter);

                        alertDialog.cancel();
                        return;
                    }
                    else {
                        Toast.makeText(getContext(), "Khong the them Category vi them Category phai nhap 1 trong 3 la Study, Working, Relax", Toast.LENGTH_LONG).show();
                        return;
                    }

                }else {
                    Toast.makeText(getContext(), "Khong the them Category vi them Category phai nhap 1 trong 3 la Study, Working, Relax", Toast.LENGTH_LONG).show();
                    return;
                }


            }
            else if(numcat ==2){
                m1 = mListCategory.get(0).catName;
                m2 = mListCategory.get(1).catName;
                System.out.println(m1);
                System.out.println(m2);

                if(!txtCatName.equals(m1) && !txtCatName.equals(m2)){
                    if(txtCatName.equals("Study")){
                        categoryDao.insertCat(new Category( txtCatName, strDate));
                        mListCategory=categoryDao.getListCategory();
                        catAdapter.setData(mListCategory);
                        rcvCat.setAdapter(catAdapter);

                        alertDialog.cancel();
                        return;
                    }
                    else if(txtCatName.equals("Working")){
                        categoryDao.insertCat(new Category( txtCatName, strDate));
                        mListCategory=categoryDao.getListCategory();
                        catAdapter.setData(mListCategory);

                        rcvCat.setAdapter(catAdapter);

                        alertDialog.cancel();
                        return;
                    }
                    else if(txtCatName.equals("Relax")){
                        categoryDao.insertCat(new Category( txtCatName, strDate));
                        mListCategory=categoryDao.getListCategory();
                        catAdapter.setData(mListCategory);
                        rcvCat.setAdapter(catAdapter);

                        alertDialog.cancel();
                        return;
                    }
                    else {
                        Toast.makeText(getContext(), "Khong the them Category vi them Category phai nhap 1 trong 3 la Study, Working, Relax", Toast.LENGTH_LONG).show();
                        return;
                    }
                }
                else {
                    Toast.makeText(getContext(), "Khong the them Category vi them Category phai nhap 1 trong 3 la Study, Working, Relax", Toast.LENGTH_LONG).show();
                    return;
                }
            }



            /*if(txtCatName != "Study" || txtCatName != "Working" || txtCatName != "Relax")
            {
                Toast.makeText(getContext(), "Khong the them Category vi them Category phai nhap 1 trong 3 la Study, Working, Relax", Toast.LENGTH_LONG).show();
                return;
            }
            else {
                *//**
                 * them du lieu va roomdatabase
                 *//*
                categoryDao.insertCat(new Category( txtCatName, strDate));
            }
*/


            /**
             * cap nhat lai va hien thi len recyclerview
             */
            mListCategory=categoryDao.getListCategory();
            catAdapter.setData(mListCategory);
            rcvCat.setAdapter(catAdapter);

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
        Category c = mListCategory.get(item.getGroupId());


        int categoryDetailsID = c.catID;

        int numberofnote = appDatabase.getInstance(getContext()).noteDao().countNotewithCategoryID(categoryDetailsID);


        switch (item.getItemId()){
            /**
             * case 001 la nut delete
             * case 002 la nut edit
             */
            case 001:

                System.out.println(numberofnote);
                if(numberofnote > 0){
                    Toast.makeText(getContext(), "Khong the xoa vi category nay co note su dung", Toast.LENGTH_LONG).show();

                }
                else {
                    mListCategory.remove(item.getGroupId());
                    appDatabase.getInstance(getContext()).categoryDao().delete(c);
                    catAdapter.notifyDataSetChanged();
                }

                return true;
            case 002:
                numcat = categoryDao.getNumberCat();

                System.out.println(numcat);

                if(numcat<3){
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
     * mo dialog khi an nut edit tu 1 item trong category
     * @param c
     * @param item
     */
    public void OpenInfoDialog2(Category c, MenuItem item){
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        View view = getLayoutInflater().inflate(R.layout.layout_dialog,null);
        Button save =view.findViewById(R.id.btnAdd);
        EditText edtCatName = view.findViewById(R.id.edtCat);
//        System.out.println(newCatName);
        Calendar cal = Calendar.getInstance();

        String strDate = DateFormat.format("yyyy-MM-dd hh:mm:ss",cal).toString();
        edtCatName.setText(c.catName);

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
            String newCatName=edtCatName.getText().toString();

            System.out.println(newCatName);
            System.out.println(numcat);

            /**
             * numcat = 1 la co san 1 category trong db
             * numcat = 2 la co san 2 category trong db
             */
            if(numcat ==1){
                m1 = mListCategory.get(0).catName;
                System.out.println(m1);

                if(newCatName.equals("Study") || newCatName.equals("Working") || newCatName.equals("Relax")){

                    c.setCatName(newCatName);
                    c.setTimeCre(strDate);
                    appDatabase.getInstance(getContext()).categoryDao().update(c);
                    mListCategory.remove(item.getGroupId());
                    mListCategory.add(c);
                    catAdapter.notifyDataSetChanged();
                    alertDialog.cancel();
                }
                else {
                    Toast.makeText(getContext(), "Khong the edit Category vi phai edit 1 trong 3 Study, Working, Relax", Toast.LENGTH_LONG).show();
                    return;
                }
            }
            else if(numcat==2){
                m1 = mListCategory.get(0).catName;
                m2 = mListCategory.get(1).catName;

                System.out.println(m1);
                System.out.println(m2);

                if(newCatName.equals(m1) || newCatName.equals(m2)){
                    Toast.makeText(getContext(), "Khong the edit Category vi category da ton tai", Toast.LENGTH_LONG).show();
                    return;
                }
                else if(!newCatName.equals(m1) && !newCatName.equals(m2)){

                    if(newCatName.equals("Study")){
                        c.setCatName(newCatName);
                        c.setTimeCre(strDate);
                        appDatabase.getInstance(getContext()).categoryDao().update(c);
                        mListCategory.remove(item.getGroupId());
                        mListCategory.add(c);
                        catAdapter.notifyDataSetChanged();
                        alertDialog.cancel();
                    }
                    else if(newCatName.equals("Working")){
                        c.setCatName(newCatName);
                        c.setTimeCre(strDate);
                        appDatabase.getInstance(getContext()).categoryDao().update(c);
                        mListCategory.remove(item.getGroupId());
                        mListCategory.add(c);
                        catAdapter.notifyDataSetChanged();
                        alertDialog.cancel();
                    }
                    else if(newCatName.equals("Relax")){
                        c.setCatName(newCatName);
                        c.setTimeCre(strDate);
                        appDatabase.getInstance(getContext()).categoryDao().update(c);
                        mListCategory.remove(item.getGroupId());
                        mListCategory.add(c);
                        catAdapter.notifyDataSetChanged();
                        alertDialog.cancel();
                    }
                    else {
                        Toast.makeText(getContext(), "Khong the edit Category vi phai edit 1 trong 3 Study, Working, Relax", Toast.LENGTH_LONG).show();
                        return;
                    }


                }



            }

            /*Toast.makeText(getContext(), "Khong the edit Category vi phai edit 1 trong 3 Study, Working, Relax", Toast.LENGTH_LONG).show();
            return;*/


//
//
//            c.setCatName(newCatName);
//            c.setTimeCre(strDate);
//            appDatabase.getInstance(getContext()).categoryDao().update(c);
//            mListCategory.remove(item.getGroupId());
//            mListCategory.add(c);
//            catAdapter.notifyDataSetChanged();
//            alertDialog.cancel();
        });

        /**
         * su kien nut close
         */
        btnClose = view.findViewById(R.id.btnClose);
        btnClose.setOnClickListener(v -> {
            alertDialog.cancel();
//            categoryDao.deleteAll();
        });




    }

}