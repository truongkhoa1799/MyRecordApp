package com.example.dailyapp.ui.Database;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class DailyAppData {

    public static class Total_Amount{
        private Integer total_km;
        private Integer total_amount;

        public void updateTotalAmount(Integer amount , Integer km)
        {
            total_km = total_km +  km;
            total_amount = total_amount -  amount;
        }

        public Integer getTotal_km() {
            return total_km;
        }

        public Integer getTotal_amount() {
            return total_amount;
        }

        public void setTotal_km(Integer total_km) {
            this.total_km = total_km;
        }

        public void setTotal_amount(Integer total_amount) {
            this.total_amount = total_amount;
        }

        public Total_Amount(){}

        public void print()
        {
            System.out.printf("TOTAL AMOUNT : \n");
            System.out.printf("\t Remaining Amount: " + total_amount + "\n");
            System.out.printf("\t Total Km: "+ total_km  + "\n");
        }

    }

    public static class Km
    {
        private HashMap<String , HashMap<String ,String>>  details;

        public Km(HashMap<String, HashMap<String ,String>> details) {

        }
        public Km(){}

        public HashMap<String, HashMap<String ,String>> getDetails() {
            return details;
        }
        public void setDetails(HashMap<String, HashMap<String ,String>> details) {
            this.details = details;
        }
        public void print()
        {
            System.out.printf("KM : \n");
            for(Map.Entry en: details.entrySet())
            {
                System.out.printf("\t" + en.getKey() + "\n");
                for(Map.Entry child_en: details.get(en.getKey()).entrySet())
                {
                    System.out.printf("\t\t" + child_en.getKey() + " : " + child_en.getValue() + "\n");
                }
            }
        }

        public HashMap<String , String> returnIndex(String date){
            if(details.containsKey(date))
            {
                return details.get(date);
            }
            else return null;
        }

        public void update(String date , String amount , String content)
        {
            HashMap<String , String> temp = returnIndex(date);
            if(temp != null) {
                String str1 = temp.get(date);
                if(temp.containsKey(amount))
                {
                    String str = temp.get(amount);
                    str = str + " + " + content;
                    temp.put(amount, str);
                    details.put(date, temp);
                }
                else {
                    temp.put(amount, content);
                    details.put(date, temp);
                }
            }
            else
            {
                temp = new HashMap<String , String>();
                temp.put(amount, content);
                details.put(date, temp);
            }
        }
    }

    public static class Amount{
        private HashMap<String , HashMap<String ,String>> details;

        public Amount(){}

        public HashMap<String, HashMap<String ,String>> getDetails() {
            return details;
        }

        public void print()
        {
            System.out.printf("AMOUNT DETAIL \n");
            for(Map.Entry en: details.entrySet())
            {
                System.out.printf("\t" + en.getKey() + "\n");
                for(Map.Entry child_en: details.get(en.getKey()).entrySet())
                {
                    System.out.printf("\t" + child_en.getKey() + " : " + child_en.getValue() + "\n");
                }
            }
        }
        public void update(String date , String amount , String content)
        {
            HashMap<String , String> temp = returnIndex(date);
            if(temp != null) {
                String str1 = temp.get(date);
                if(temp.containsKey(amount))
                {
                    String str = temp.get(amount);
                    str = str + " + " + content;
                    temp.put(amount, str);
                    details.put(date, temp);
                }
                else {
                    System.out.printf("NOOOOO" + "\n");
                    temp.put(amount, content);
                    details.put(date, temp);
                }
            }
            else
            {
                temp = new HashMap<String , String>();
                temp.put(amount, content);
                details.put(date, temp);
            }
        }

        public void setDetails(HashMap<String, HashMap<String ,String>> details) {
            this.details = details;
        }

        public HashMap<String , String> returnIndex(String date){
            if(details.containsKey(date))
            {
                return details.get(date);
            }
            else return null;
        }

    }

    public static Total_Amount total_amount;
    public static Km km_details;
    public static Amount amount_detail;

    public static void update(int option ,String date, String amount, String km, String content_amount, String content_km)
    {
        DatabaseReference mDatabase;
        mDatabase = FirebaseDatabase.getInstance().getReference();
        switch (option){
            case 0:
                total_amount.updateTotalAmount(Integer.parseInt(amount) , Integer.parseInt(km));
                amount_detail.update(date , amount , content_amount);

                mDatabase.child("Total Amount").child("Money").setValue(total_amount.getTotal_amount())
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                System.out.printf("TESTING \n");
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                System.out.printf("FAIL TESTING \n");
                            }
                        });
                if(DailyAppData.amount_detail.returnIndex(date) != null) {
                    mDatabase.child("Amount").child(date).setValue(DailyAppData.amount_detail.returnIndex(date))
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {

                                    System.out.printf("TESTING \n");
                                }
                            })
                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    System.out.printf("FAIL TESTING \n");
                                }
                            });
                }
                break;
            case 1:
                total_amount.updateTotalAmount(Integer.parseInt(amount) , Integer.parseInt(km));
                km_details.update(date , km , content_km);
                mDatabase.child("Total Amount").child("Km").setValue(total_amount.getTotal_km())
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                System.out.printf("TESTING \n");
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                System.out.printf("FAIL TESTING \n");
                            }
                        });
                if(DailyAppData.amount_detail.returnIndex(date) != null) {
                    mDatabase.child("Km").child(date).setValue(DailyAppData.km_details.returnIndex(date))
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {

                                    System.out.printf("TESTING \n");
                                }
                            })
                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    System.out.printf("FAIL TESTING \n");
                                }
                            });
                }
                break;
        }

    }


    public DailyAppData(){}

}
