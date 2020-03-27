package com.example.dailyapp.ui.moneyrecord;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MoneyRecordViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public MoneyRecordViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is notifications fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}