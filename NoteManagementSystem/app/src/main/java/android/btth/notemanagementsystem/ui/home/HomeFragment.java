package android.btth.notemanagementsystem.ui.home;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.btth.notemanagementsystem.R;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

public class HomeFragment extends Fragment {

    private PieChart pie;
    private HomeViewModel homeViewModel;
    int [] color={ Color.rgb(153,153,153),Color.rgb(255,0,0), Color.rgb(0,0,255)};

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        final TextView textView = root.findViewById(R.id.text_home);

        pie= root.findViewById(R.id.piechart);





        pie.setUsePercentValues(true);

        pie.setDragDecelerationFrictionCoef(0.99f);
        pie.setDrawHoleEnabled(false);



        ArrayList<PieEntry> pieEntries= new ArrayList<>();

        pieEntries.add(new PieEntry(60, "Processing"));
        pieEntries.add(new PieEntry(20, "Done"));
        pieEntries.add(new PieEntry(20, "Pending"));

        PieDataSet dataSet = new PieDataSet(pieEntries, "test");

        dataSet.setSliceSpace(3f);
        dataSet.setSelectionShift(5f);
        dataSet.setColors(color);

        PieData data= new PieData((dataSet));
        data.setValueTextSize(10f);
        data.setValueTextColor(Color.WHITE);

        pie.setData(data);




        homeViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }

    public void setupPieChart(){

    }
}