package android.btth.notemanagementsystem.Adapter;

import android.btth.notemanagementsystem.R;
import android.btth.notemanagementsystem.entity.Category;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CatAdapter extends  RecyclerView.Adapter<CatAdapter.CatViewHolder>  {


    private List<Category> mListCategory;

    public void setData(List<Category> list){
        this.mListCategory = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public CatViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_row_cat,parent,false);

        return new CatViewHolder(view);
    }

    //set du lieu len
    @Override
    public void onBindViewHolder(@NonNull CatViewHolder holder, int position) {

        Category category = mListCategory.get(position);
        if(category ==null)
            return;
        holder.txtCatName.setText(category.getCatName());
        holder.txtCatDate.setText(category.getTimeCre());
    }

    @Override
    public int getItemCount() {
        if(mListCategory != null){
            return mListCategory.size();
        }
        return 0;
    }



    //Thiet ke de truyen vao catadapter
    public class CatViewHolder extends RecyclerView.ViewHolder{

        private TextView txtCatName;
        private TextView txtCatDate;

        public CatViewHolder(@NonNull View itemView) {
            super(itemView);

            txtCatName = itemView.findViewById(R.id.txtCatName);
            txtCatDate = itemView.findViewById(R.id.txtCatDate);
        }
    }


}
