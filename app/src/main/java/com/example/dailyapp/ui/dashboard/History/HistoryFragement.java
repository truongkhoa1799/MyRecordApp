package com.example.dailyapp.ui.dashboard.History;

import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.dailyapp.R;
import com.example.dailyapp.ui.Database.DailyAppData;

import java.util.HashMap;
import java.util.Map;

public class HistoryFragement extends Fragment {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    public static HistoryFragement newInstance() {
        return new HistoryFragement();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.history_fragement_fragment, container, false);
        String option = getArguments().getString("option");
        String date = getArguments().getString("date");
        HashMap<String , HashMap<String , String>> map = new HashMap<String , HashMap<String, String>>();

        int count = 0;
        if (option.equals("0")) map = DailyAppData.amount_detail.getDetails();
        else if (option.equals("1")) map = DailyAppData.km_details.getDetails();

        String myString[] = new String[map.size()];
        for (Map.Entry ds : map.entrySet())
        {
            myString[count] = ds.getKey().toString();
            count ++;
        }

        mRecyclerView = root.findViewById(R.id.recylerView);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(getActivity());
        mAdapter = new MyAdapter(myString);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
        return root;
    }

}
