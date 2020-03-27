package com.example.dailyapp.ui.Database;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class RetrieveData {
    private FirebaseDatabase mDatabase;

    public RetrieveData() {
        mDatabase = FirebaseDatabase.getInstance();
        DailyAppData.km_details = new DailyAppData.Km();
        DailyAppData.amount_detail = new DailyAppData.Amount();
        DailyAppData.total_amount = new DailyAppData.Total_Amount();
        RetrieveKm();
        RetrieveAmount();
        RetrieveTotalAmount();
    }

    private void RetrieveKm()
    {
        DatabaseReference ref = mDatabase.getReference("Km");
        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                HashMap<String , HashMap<String ,String>> km = new HashMap<>();
                for(DataSnapshot ds: dataSnapshot.getChildren())
                {
                    HashMap<String , String> content = new HashMap<String, String>();
                    for(DataSnapshot child_ds : ds.getChildren()) {
                        String temp = child_ds.getValue(String.class);
                        String amount_km = child_ds.getKey();
                        content.put(amount_km , temp);
                    }
                    String amount_km = ds.getKey();
                    km.put(amount_km,content);
                }
                DailyAppData.km_details.setDetails(km);
                DailyAppData.km_details.print();

            };

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                System.out.printf("Faild to retrieve Km \n");
            }
        });
    }
    private void RetrieveTotalAmount()
    {
        DatabaseReference ref = mDatabase.getReference("Total Amount");
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                int count = 0;
                for(DataSnapshot ds: dataSnapshot.getChildren())
                {
                    Integer temp = ds.getValue(Integer.class);
                    switch (count){
                        case 0:
                            DailyAppData.total_amount.setTotal_km(temp);
                            count ++;
                            break;
                        case 1:
                            DailyAppData.total_amount.setTotal_amount(temp);
                            count++;
                            break;

                    }
                }
                DailyAppData.total_amount.print();
            };

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                System.out.printf("Faild to retrieve Km \n");
            }
        });
    }
    private void RetrieveAmount()
    {
        DatabaseReference ref = mDatabase.getReference("Amount");
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                HashMap<String , HashMap<String ,String>> money = new HashMap<>();
                for(DataSnapshot ds: dataSnapshot.getChildren())
                {
                    HashMap<String , String> content = new HashMap<String, String>();
                    for(DataSnapshot child_ds : ds.getChildren()) {
                        String temp = child_ds.getValue(String.class);
                        String amount_money = child_ds.getKey();
                        content.put(amount_money , temp);
                    }
                    String date = ds.getKey();
                    money.put(date,content);
                }
                DailyAppData.amount_detail.setDetails(money);
                DailyAppData.amount_detail.print();

            };

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                System.out.printf("Faild to retrieve money \n");
            }
        });
    }

}
