package com.example.dailyapp.ui.oilRecord;

import androidx.lifecycle.ViewModelProviders;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.dailyapp.R;
import com.example.dailyapp.ui.Database.DailyAppData;
import com.google.android.material.textfield.TextInputLayout;

public class oilRecordFragment extends Fragment {

    private OilRecordViewModel mViewModel;

    public static oilRecordFragment newInstance() {
        return new oilRecordFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        final View root = inflater.inflate(R.layout.fragment_oil_record, container, false);
        Bundle bundle = getArguments().getBundle("date");

        final String current_date = bundle.getString("current_date");
        String current_day = bundle.getString("current_day");

        TextView day = root.findViewById(R.id.date_view_1);
        TextView date = root.findViewById(R.id.date_view_2);

        day.setText(current_day);
        date.setText(current_date);

        final TextView km = root.findViewById(R.id.total_km);
        String total_km = DailyAppData.total_amount.getTotal_km().toString() + " KM";
        km.setText(total_km);

        Button submit_but = root.findViewById(R.id.submit_button);
        final TextInputLayout input_amount = root.findViewById(R.id.input_amount);
        final TextInputLayout input_content = root.findViewById(R.id.input_content);
        submit_but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setMessage("Are you sure")
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                String send_content = input_content.getEditText().getText().toString().toString();
                                String send_amount = input_amount.getEditText().getText().toString();
                                input_amount.getEditText().setText("");
                                input_content.getEditText().setText("");
                                if (!send_amount.isEmpty() && !send_content.isEmpty()) {

                                    DailyAppData.update(1, current_date, "0", send_amount, "0",send_content);
                                    String remaing_amount = DailyAppData.total_amount.getTotal_km().toString() + " VND";
                                    km.setText(remaing_amount);
                                }
                            }
                        })
                        .setNegativeButton("Cancel" , null);
                AlertDialog aler = builder.create();
                aler.show();
            }
        });
        return root;
    }

}
