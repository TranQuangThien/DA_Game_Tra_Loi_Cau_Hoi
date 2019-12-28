package com.example.tranquangthien;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class activity_Thong_Tin extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__thong__tin);
    }

    public void openUpdateInfo(View view) {

        Intent intent = new Intent(this, activity_UpDate.class);
        startActivity(intent);

    }

    public void doiavt(View view) {
       
    }
}
