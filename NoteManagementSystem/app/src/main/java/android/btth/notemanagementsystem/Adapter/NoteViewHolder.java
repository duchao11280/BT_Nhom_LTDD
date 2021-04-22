package android.btth.notemanagementsystem.Adapter;

import android.btth.notemanagementsystem.R;
import android.view.View;

import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class NoteViewHolder extends RecyclerView.ViewHolder{
    TextView txtName, txtCat, txtPrio, txtSTT, txtPlan, txtCre;

    public NoteViewHolder(@NonNull View itemView) {
        super(itemView);
        txtName = (TextView) itemView.findViewById(R.id.txtName);
        txtCat = (TextView) itemView.findViewById(R.id.txtCat);
        txtPrio = (TextView) itemView.findViewById(R.id.txtPrio);
        txtSTT = (TextView) itemView.findViewById(R.id.txtSTT);
        txtPlan = (TextView) itemView.findViewById(R.id.txtPlan);
        txtCre = (TextView) itemView.findViewById(R.id.txtCre);
    }
}
