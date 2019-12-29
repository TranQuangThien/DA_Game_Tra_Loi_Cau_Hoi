package com.example.tranquangthien;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.net.URL;

public class activity_Main_Chinh extends AppCompatActivity {
    ImageView imviewavtfb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__main__chinh);
        imviewavtfb = (ImageView)findViewById(R.id.imageview_formMain_avt);
    }

    public void openInfomation(View view) {

        Intent intent = new Intent(this, activity_Thong_Tin.class);
        startActivity(intent);
    }

    public void openMuacreadit(View view) {
        Intent intent = new Intent(this, activity_MuaCreadit.class);
        startActivity(intent);
    }

    public void moLinhVuc(View view) {

       Dialog dialog = new Dialog(activity_Main_Chinh.this);
       dialog.setTitle("Chọn lĩnh vực");
       dialog.setContentView(R.layout.activity_linh_vuc);
       dialog.show();
        Button btn_Linhvuc1 = (Button)dialog.findViewById(R.id.btn_linhvuc1);
        Button btn_Linhvuc2 = (Button)dialog.findViewById(R.id.btn_linhvuc2);
        Button btn_Linhvuc3 = (Button)dialog.findViewById(R.id.btn_linhvuc3);
        Button btn_Linhvuc4 = (Button)dialog.findViewById(R.id.btn_linhvuc4);
        btn_Linhvuc1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity_Main_Chinh.this, activity_Trochoi.class);
                startActivity(intent);
            }
        });
        btn_Linhvuc2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity_Main_Chinh.this, activity_Trochoi.class);
                startActivity(intent);
            }
        });
        btn_Linhvuc3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity_Main_Chinh.this, activity_Trochoi.class);
                startActivity(intent);
            }
        });
        btn_Linhvuc4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity_Main_Chinh.this, activity_Trochoi.class);
                startActivity(intent);
            }
        });
    }

    public void openLichSuChoi(View view) {
        Intent intent = new Intent(activity_Main_Chinh.this, Activity_LichSu.class);
        startActivity(intent);

    }

    public void logout(View view) {

        Intent intent = new Intent(activity_Main_Chinh.this, MainActivity.class);
        startActivity(intent);

    }
}
