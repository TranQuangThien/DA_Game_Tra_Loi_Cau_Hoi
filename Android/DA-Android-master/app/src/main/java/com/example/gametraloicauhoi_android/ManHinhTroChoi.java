package com.example.gametraloicauhoi_android;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.AsyncTaskLoader;
import androidx.loader.content.Loader;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONObject;

import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class ManHinhTroChoi extends AppCompatActivity {
    MediaPlayer mediaPlayer;
    Button daA,daB,daC,daD,cauHoi;
    int cauHienTai = -1;
    int so_cau_tra_loi = 0;
    String data;
    final int CAP_NHAT = 1;
    TextView cauHoiHT,thoiGian,txDiem,credit;
    int TIME_COUNT = CauHinhVaLuuTru.cauHinhApp.getThoiGianTraLoi();
    int count_down;
    public static int diem = 0;
    int co_hoi = 3;
    Context _context = this;
    Timer tm = new Timer();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_man_hinh_tro_choi);
        mediaPlayer = MediaPlayer.create(ManHinhTroChoi.this,R.raw.bat_dau);
        mediaPlayer.setLooping(true);
        mediaPlayer.start();

        cauHoi = findViewById(R.id.btnCauHoi);
        daA = findViewById(R.id.btnDapAnA);
        daB = findViewById(R.id.btnDapAnB);
        daC = findViewById(R.id.btnDapAnC);
        daD = findViewById(R.id.btnDapAnD);
        daA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chonDapAn(v);
            }
        });
        daB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chonDapAn(v);
            }
        });
        daC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chonDapAn(v);
            }
        });
        daD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chonDapAn(v);
            }
        });
        cauHoiHT = findViewById(R.id.txtSo);
        thoiGian = findViewById(R.id.txtThoiGian);
        txDiem = findViewById(R.id.txtDiem);
        credit = findViewById(R.id.txtCreditCLV);
        credit.setText(String.valueOf(ManHinhChinh.credit));
        count_down = TIME_COUNT;
        cauHoiTiepTheo();
    }
    Handler status;
    public void cauHoiTiepTheo(){
        cauHienTai++;
        if(CauHinhVaLuuTru.mDSCauHoi.size()-1 > cauHienTai){
            khoiPhucGiaoDien();
            loadCauHoi();
            tm = new Timer();
            tm.schedule(new TimerTask() {
                @Override
                public void run() {
                    ((AppCompatActivity)_context).runOnUiThread(new Runnable() {
                        @SuppressLint("DefaultLocale")
                        @Override
                        public void run() {
                            if(count_down >= 0){
                                thoiGian.setText(String.format("%02d",count_down));
                                count_down--;
                            }
                            else{
                                loadCoHoi(0);
                                co_hoi--;
                            }
                        }
                    });
                }
            },0,1000);

        }
        else{
            taoForm();
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

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        tm.cancel();
        tm.purge();
        finish();
    }

    public void loadCoHoi( int type){
        TextView tx;
        if(co_hoi > 0 && type == 0){
            tm.cancel();
            tm.purge();
            count_down = TIME_COUNT;
            cauHoiTiepTheo();
        }
        switch (co_hoi){
            case 1:
                tx = findViewById(R.id.txtCH1);
                tx.setVisibility(View.INVISIBLE);
                tm.cancel();
                tm.purge();
                taoForm();
                break;
            case 2:
                tx = findViewById(R.id.txtCH2);
                tx.setVisibility(View.INVISIBLE);
                break;
            case 3:
                tx = findViewById(R.id.txtCH3);
                tx.setVisibility(View.INVISIBLE);
                break;

        }
    }

    private void taoForm(){
        mediaPlayer.stop();
        FragmentManager fm = getSupportFragmentManager();
        GameOverDialogFragment gm = new GameOverDialogFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("SO_CAU",so_cau_tra_loi);
        gm.setArguments(bundle);
        gm.show(fm,"assassin");
    }



    public void chonDapAn(final View view){
        final String pa = ((TextView)view).getText().toString(),da =
                CauHinhVaLuuTru.mDSCauHoi.get(cauHienTai).getDapAn();
       view.setBackground(getDrawable(R.drawable.selected_style));
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if(kiemTraDapAn(pa,da)){
                    so_cau_tra_loi++;
                    view.setBackground(getDrawable(R.drawable.right_style));
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            congDiem();
                            tm.cancel();
                            tm.purge();
                            count_down = TIME_COUNT;
                            cauHoiTiepTheo();
                            Drawable drawable = getDrawable(R.drawable.number_game);
                            view.setBackground(drawable);
                        }
                    },1000);

                }
                else{
                    view.setBackground(getDrawable(R.drawable.wrong_style));
                    loadCoHoi(1);
                    co_hoi--;
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            view.setBackground(getDrawable(R.drawable.number_game));

                        }
                    },900);

                }
            }

        },1500);
    }

    private boolean kiemTraDapAn(String pa, String da){
        return pa.equals(da);
    }
    private void congDiem(){
        ManHinhTroChoi.diem += CauHinhVaLuuTru.cauHinhDiemCauHoi.get(cauHienTai).getDiem();
        txDiem.setText("Điểm: "+diem);
    }
    private AlertDialog taoDialog(String message, String title){
        return new AlertDialog.Builder(this)
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton("Huỷ bỏ", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        })
                .setNegativeButton("Đồng ý", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //TruCredit(id);
                        temp.setVisibility(View.INVISIBLE);
                        if (id == 2) {
                            TruCredit(2);
                            TroGiup5050();
                        }
                        else{
                            TruCredit(1);
                            TroGiupTVTC();
                        }

                    }
                }).create();
    }
    int id;
    View temp;
    public void LuaChonTroGiup(View view){
        temp = view;
        id = Integer.parseInt(view.getTag().toString());
        String message =
                "Bạn có muốn sử dụng quyền trợ giúp "+ view.getContentDescription().toString().toUpperCase();
        String title = "Thông báo";
        taoDialog(message,title).show();
    }

    public void TruCredit(int id){
        ManHinhChinh.credit-=CauHinhVaLuuTru.cauHinhTroGiup.get(id).getCredit();
        credit.setText(String.valueOf(ManHinhChinh.credit));
    }

    public void TroGiup5050() {
        ArrayList<Button> buttons = new ArrayList<>();
        buttons.add(daA);
        buttons.add(daB);
        buttons.add(daC);
        buttons.add(daD);
        Random random = new Random();
        buttons.remove(dapAnDung(buttons));
        int vt1 = random.nextInt(3),vt2 = random.nextInt(3);
        buttons.get(vt1).setVisibility(View.INVISIBLE);
        buttons.get(vt2).setVisibility(View.INVISIBLE);
    }

    public void TroGiupTVTC(){
       FragmentManager fm = getSupportFragmentManager();
       TVTCDialogFragment tv = new TVTCDialogFragment();
        ArrayList<Button> buttons = new ArrayList<>();
        buttons.add(daA);
        buttons.add(daB);
        buttons.add(daC);
        buttons.add(daD);
        int vt = dapAnDung(buttons);
       Bundle data = new Bundle();
       data.putInt("dap_an",vt);
       tv.setArguments(data);
       tv.show(fm,"fragment");
    }


    private int dapAnDung(ArrayList<Button> buttons)
    {
        for(int i = 0;i<buttons.size();i++){
            if(kiemTraDapAn(buttons.get(i).getText().toString(),
                    CauHinhVaLuuTru.mDSCauHoi.get(cauHienTai).getDapAn()))
                return i;
        }
        return 0;
    }

    private void khoiPhucGiaoDien(){
        daA.setVisibility(View.VISIBLE);
        daB.setVisibility(View.VISIBLE);
        daC.setVisibility(View.VISIBLE);
        daD.setVisibility(View.VISIBLE);
    }
}
