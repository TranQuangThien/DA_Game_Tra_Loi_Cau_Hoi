package com.example.ailatrieuphu;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class TrangChu extends AppCompatActivity {

    TextView txtCredit, txtUser;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    boolean doubleBackToExitPressedOnce = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trang_chu);

        txtCredit = findViewById(R.id.Credit);
        txtUser = findViewById(R.id.Username);
        sharedPreferences = getSharedPreferences("com.example.ailatrieuphu", MODE_PRIVATE);
        editor = sharedPreferences.edit();
        String token = sharedPreferences.getString("TOKEN", "");
        Log.d("TOKEN", token);
        if (token == "") {
            Intent intent = new Intent(this, TrangChu.class);
            startActivity(intent);
        }
        String credit = sharedPreferences.getString("credit", "");
        String User = sharedPreferences.getString("ten_dang_nhap", "");
        this.txtUser.setText(User);
        this.txtCredit.setText(credit);

    }

    public void LaunchNapCredit(View view) {
        Intent intent = new Intent (this, NapCredit.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_right,R.anim.slide_out_left);
    }


    public void LaunchChonLinhVuc(View view) {
        Intent intent = new Intent (this, ChonLinhVuc.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_left,R.anim.slide_out_right);
    }

    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            Intent intent = new Intent(Intent.ACTION_MAIN);
            intent.addCategory(Intent.CATEGORY_HOME);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }

        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "Nhấn 1 lần nữa để thoát", Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBackToExitPressedOnce=false;
            }
        }, 2000);
    }

    public void LaunchLogin(View view) {
        final String token = sharedPreferences.getString("TOKEN", "");
        Log.d("TOKEN", token);
        new SweetAlertDialog(TrangChu.this, SweetAlertDialog.WARNING_TYPE)

                .setTitleText("Bạn có chắc chắn muốn đăng xuất ?")
                .setCancelButton("không", new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sweetAlertDialog) {
                        sweetAlertDialog.dismissWithAnimation();
                    }
                })
                .setConfirmText("Có")
                .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sweetAlertDialog) {
                        sweetAlertDialog.dismissWithAnimation();
                        new DangXuatLoader().execute(token);
                        editor.clear();
                        editor.apply();
                        Intent intent = new Intent (TrangChu.this, MainActivity.class);
                        startActivity(intent);
                    }
                })
                .show();
    }

    public void LaunchThongTinXepHang(View view) {
        Intent intent = new Intent (this, ThongTinXepHangMain.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_right,R.anim.slide_out_left);
    }

    public void LaunchThongTinCaNhan(View view) {
        Intent intent = new Intent (this,ThongTinCaNhan.class);
        startActivity(intent);
    }
}
