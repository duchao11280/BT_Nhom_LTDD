package android.btth.notemanagementsystem.ui.note;

import android.btth.notemanagementsystem.Adapter.NoteAdapter;
import android.btth.notemanagementsystem.R;
import android.btth.notemanagementsystem.entity.NoteDetails;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class NoteFragment extends Fragment {

    RecyclerView recyclerView;
    NoteAdapter noteAdapter;
    LinearLayoutManager layoutManager;
    List<NoteDetails> noteDetailsList;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_note, container, false);
        recyclerView = (RecyclerView)root.findViewById(R.id.rcvNote);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);

        noteAdapter.setData(noteDetailsList);
        recyclerView.setAdapter(noteAdapter);

        return root;
    }
}
