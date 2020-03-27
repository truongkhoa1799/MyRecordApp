package com.example.dailyapp.ui.moneyrecord;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.dailyapp.R;
import com.example.dailyapp.ui.Database.DailyAppData;
import com.google.android.material.textfield.TextInputLayout;

public class moneyRecordFragment extends Fragment {

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState)
    {
        View root = inflater.inflate(R.layout.fragment_money_record, container, false);
        Bundle bundle = getArguments().getBundle("date");

        final String current_date = bundle.getString("current_date");
        String current_day = bundle.getString("current_day");

        TextView day = root.findViewById(R.id.date_view_1);
        TextView date = root.findViewById(R.id.date_view_2);

        day.setText(current_day);
        date.setText(current_date);

        final TextView amount = root.findViewById(R.id.remaing_amount);
        String remaing_amount = DailyAppData.total_amount.getTotal_amount().toString() + " VND";
        amount.setText(remaing_amount);


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
                                    DailyAppData.update(0, current_date, send_amount, "0", send_content, "0");
                                    String remaing_amount = DailyAppData.total_amount.getTotal_amount().toString() + " VND";
                                    amount.setText(remaing_amount);
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