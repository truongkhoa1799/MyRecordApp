package com.example.dailyapp.ui.home;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CalendarView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.Navigation;

import com.example.dailyapp.R;

import java.text.SimpleDateFormat;
import java.util.Date;

public class HomeFragment extends Fragment{

    private HomeViewModel homeViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState)
    {
        homeViewModel = ViewModelProviders.of(this).get(HomeViewModel.class);
        final View root = inflater.inflate(R.layout.fragment_home, container, false);
        final Button moneyRecordFragment = root.findViewById(R.id.money_record_but);

        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        Bundle bundle = new Bundle();

        CalendarView cal = root.findViewById(R.id.calendarView);
        Date date = new Date(cal.getDate());

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        SimpleDateFormat dayFormat = new SimpleDateFormat("EEE");

        String current_date = dateFormat.format(date);
        String current_day = dayFormat.format(date);

        bundle.putString("current_date" , current_date);
        bundle.putString("current_day" , current_day);

        final Bundle pass_bundle = new Bundle();
        pass_bundle.putBundle("date" , bundle);

        moneyRecordFragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.action_navigation_home_to_moneyRecordFragment , pass_bundle);
            }
        });

        final Button oilRecordFragment = root.findViewById(R.id.oil_record_but);
        oilRecordFragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(R.id.action_navigation_home_to_oilRecordFragment , pass_bundle);
            }
        });

        return root;
    }

}
