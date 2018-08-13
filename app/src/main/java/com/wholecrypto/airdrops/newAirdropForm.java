package com.wholecrypto.airdrops;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.mukesh.countrypicker.Country;
import com.mukesh.countrypicker.CountryPicker;
import com.mukesh.countrypicker.OnCountryPickerListener;

public class newAirdropForm extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_airdrop_form);

        EditText startDate = findViewById(R.id.startDate);
        EditText endDate = findViewById(R.id.endDate);

        setDate startDateDialog = new setDate(startDate,this);
        setDate endDateDialog = new setDate(endDate,this);



    }
}
