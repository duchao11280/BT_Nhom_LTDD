package android.btth.notemanagementsystem.Adapter;

import android.btth.notemanagementsystem.R;
import android.btth.notemanagementsystem.entity.Status;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class StatusAdapter extends  RecyclerView.Adapter<StatusAdapter.StatusViewHolder> {

    private List<Status> mListStatus;

    public void setData(List<Status> list){
        this.mListStatus = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public StatusViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_row_cat,parent,false);

        return new StatusViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StatusViewHolder holder, int position) {
        Status status = mListStatus.get(position);
        if(status ==null)
            return;
        holder.txtCatName.setText("Name: " + status.getSttName());
        holder.txtCatDate.setText("Created Date: " + status.getTimeCre());
    }

    @Override
    public int getItemCount() {
        if(mListStatus != null){
            return mListStatus.size();
        }
        return 0;
    }

    public class StatusViewHolder extends RecyclerView.ViewHolder implements View.OnCreateContextMenuListener{

        private TextView txtCatName;
        private TextView txtCatDate;
        LinearLayout item_row;


        public StatusViewHolder(@NonNull View itemView) {
            super(itemView);

            txtCatName = itemView.findViewById(R.id.txtCatName);
            txtCatDate = itemView.findViewById(R.id.txtCatDate);
            item_row = itemView.findViewById(R.id.item_row);
            item_row.setOnCreateContextMenuListener(this);
        }

        @Override
        public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
            menu.add(this.getAdapterPosition(),001,0,"Delete");
            menu.add(this.getAdapterPosition(),002,1,"Edit");
        }
    }

    public void deleteitem(int position){
        mListStatus.remove(position);
        notifyDataSetChanged();
    }

}
