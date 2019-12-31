package com.example.tranquangthien;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class activity_Trochoi extends AppCompatActivity {

    Button daA,daB,daC,daD;
    TextView cauHoi;

    int cauHienTai = -1;
    TextView cauHoiHT,thoiGian,txDiem;
    int TIME_COUNT = CauHinhVaLuuTru.cauHinhApp.getThoiGianTraLoi();
    int count_down;
    int diem = 0;
    Context _context = this;
    Timer tm = new Timer();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__trochoi);
        cauHoi = findViewById(R.id.txt_cau_hoi);
        daA = findViewById(R.id.btn_CauA);
        daB = findViewById(R.id.btn_CauB);
        daC = findViewById(R.id.btn_CauC);
        daD = findViewById(R.id.btn_CauD);
        daA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ChonDapAn(v);
            }
        });
        daB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ChonDapAn(v);
            }
        });
        daC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ChonDapAn(v);
            }
        });
        daD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ChonDapAn(v);
            }
        });
        cauHoiHT = findViewById(R.id.txt_SoCau);
        txDiem = findViewById(R.id.txt_diem);
        count_down = TIME_COUNT;
        cauHoiTiepTheo();
    }

    public void hoiKhanGia(View view) {
    }

    public void ChonDapAn(View view) {

        String pa = ((TextView)view).getText().toString(),da =
                CauHinhVaLuuTru.mDSCauHoi.get(cauHienTai).getDapAn();
        if(kiemTraDapAn(pa,da)){
            congDiem();
            loadDiem(diem);
            tm.cancel();
            tm.purge();
            count_down = TIME_COUNT;
            cauHoiTiepTheo();
        }
        else{
            Toast.makeText(_context, "Đáp án không chính xác", Toast.LENGTH_SHORT).show();
        }

    }

    @SuppressLint("DefaultLocale")
    public void loadCauHoi(){
        daA.setText(CauHinhVaLuuTru.mDSCauHoi.get(cauHienTai).getPhuongAnA());
        daB.setText(CauHinhVaLuuTru.mDSCauHoi.get(cauHienTai).getPhuongAnB());
        daC.setText(CauHinhVaLuuTru.mDSCauHoi.get(cauHienTai).getPhuongAnC());
        daD.setText(CauHinhVaLuuTru.mDSCauHoi.get(cauHienTai).getPhuongAnD());
        cauHoi.setText(CauHinhVaLuuTru.mDSCauHoi.get(cauHienTai).getNoiDung());
        cauHoiHT.setText(String.format("%02d",(cauHienTai+1)));
    }


    public void cauHoiTiepTheo(){
        cauHienTai++;
        if(CauHinhVaLuuTru.mDSCauHoi.size()-1 > cauHienTai){
            loadCauHoi();
            tm = new Timer();
            tm.schedule(new TimerTask() {
                @Override
                public void run() {
                    ((AppCompatActivity)_context).runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            if(count_down >= 0){
                                thoiGian.setText(String.format("%02d",count_down));
                                count_down--;
                            }
                        }
                    });
                }
            },0,1000);

        }
    }

    public void loadDiem(int diem){
        txDiem.setText(String.valueOf(diem));
    }

    private boolean kiemTraDapAn(String pa, String da) {


            return pa.equals(da);

    }

    private void congDiem(){

        diem+=CauHinhVaLuuTru.cauHinhDiemCauHoi.get(cauHienTai).getDiem();
    }
}
