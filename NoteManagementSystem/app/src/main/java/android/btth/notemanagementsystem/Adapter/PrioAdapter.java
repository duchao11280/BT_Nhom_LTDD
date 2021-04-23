package android.btth.notemanagementsystem.Adapter;

import android.btth.notemanagementsystem.R;
import android.btth.notemanagementsystem.entity.Priority;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class PrioAdapter extends  RecyclerView.Adapter<PrioAdapter.PrioViewHolder>  {

    private List<Priority> mListPriority;

    public void setData(List<Priority> list){
        this.mListPriority = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public PrioViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_row_cat,parent,false);

        return new PrioViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull PrioViewHolder holder, int position) {
        Priority priority = mListPriority.get(position);
        if(priority ==null)
            return;
        holder.txtCatName.setText("Name: " + priority.getPrioName());
        holder.txtCatDate.setText("Created Date: " + priority.getTimeCre());
    }

    @Override
    public int getItemCount() {
        if(mListPriority != null){
            return mListPriority.size();
        }
        return 0;
    }

    //Thiet ke de truyen vao catadapter
    public class PrioViewHolder extends RecyclerView.ViewHolder{

        private TextView txtCatName;
        private TextView txtCatDate;

        public PrioViewHolder(@NonNull View itemView) {
            super(itemView);

            txtCatName = itemView.findViewById(R.id.txtCatName);
            txtCatDate = itemView.findViewById(R.id.txtCatDate);
        }
    }


}
