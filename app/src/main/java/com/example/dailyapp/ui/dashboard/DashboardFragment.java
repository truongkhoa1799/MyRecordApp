package com.example.dailyapp.ui.dashboard;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.dailyapp.R;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DashboardFragment extends Fragment {

    public String global_date;
    private DashboardViewModel dashboardViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View root = inflater.inflate(R.layout.fragment_dashboard, container, false);

        CalendarView cal = root.findViewById(R.id.calendarView);
        Date date = new Date(cal.getDate());

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        global_date = dateFormat.format(date);

        cal.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                Integer y = year;
                Integer m = month + 1;
                Integer d = dayOfMonth;
                global_date = d.toString() + "-" + m.toString() + "-" + y.toString();
            }
        });

        Button check_his = root.findViewById(R.id.check_his_but);
        check_his.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setMessage("What do you want")
                        .setPositiveButton("Amount money", new DialogInterface.OnClickListener()
                        {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Bundle bundle = new Bundle();
                                bundle.putString("option" , "0");
                                bundle.putString("date" , global_date);
                                Navigation.findNavController(root).navigate(R.id.action_navigation_dashboard_to_historyFragement, bundle);
                            }
                        })
                        .setNegativeButton("Amount Distance", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                Bundle bundle = new Bundle();
                                bundle.putString("option" , "1");
                                bundle.putString("date" , global_date);
                                Navigation.findNavController(root).navigate(R.id.action_navigation_dashboard_to_historyFragement, bundle);
                            }
                        });
                AlertDialog aler = builder.create();
                aler.show();
            }
        });
        return root;
    }
}
