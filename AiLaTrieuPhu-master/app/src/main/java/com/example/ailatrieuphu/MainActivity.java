package com.example.ailatrieuphu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import org.json.JSONException;
import org.json.JSONObject;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class MainActivity extends AppCompatActivity {

    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    private final static String FILE_NAME_SHAREREF = "com.example.ailatrieuphu";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (getIntent().getBooleanExtra("EXIT", false)) {
            finish();
            System.exit(0);
        }

        sharedPreferences = getSharedPreferences(FILE_NAME_SHAREREF, MODE_PRIVATE);
        editor = sharedPreferences.edit();
        String token = sharedPreferences.getString("TOKEN", "");
        Log.d("TOKEN", token);
        if (token != "") {
            Intent intent = new Intent(this, TrangChu.class);
            startActivity(intent);
        }
    }

    public void launchActivityMenu() {
        Intent intent = new Intent(this, TrangChu.class);
        startActivity(intent); }

    public void LaunchTrangChu(View view) {
        EditText txtUsername = findViewById(R.id.txtTaiKhoan);
        EditText txtPassword = findViewById(R.id.txtMatKhau);

        final String TaiKhoan = txtUsername.getText().toString();
        final String MatKhau = txtPassword.getText().toString();
        final SweetAlertDialog pDialog = new SweetAlertDialog(this, SweetAlertDialog.PROGRESS_TYPE);
        pDialog.getProgressHelper().setBarColor(Color.parseColor("#A5DC86"));
        pDialog.setTitleText("Loading");
        pDialog.setCancelable(false);
        pDialog.show();

        new DangNhapLoader(){
            @Override
            protected void onPostExecute(String s) {
                try {
                    JSONObject json = new JSONObject(s);
                    boolean success = json.getBoolean("success");
                    if (success) {
                        pDialog.dismissWithAnimation();
                        String token = json.getString("token");
                        String credit = json.getString("credit");
                        String Username = json.getString("ten_dang_nhap");
                        String id = json.getString("id");
                        String Email = json.getString("email");
                        editor.putString("TOKEN", token);
                        editor.putString("credit", credit);
                        editor.putString("ten_dang_nhap", Username);
                        editor.putString("id", id);
                        editor.putString("email", Email);
                        editor.putString("password", MatKhau);
                        editor.commit();
                        new SweetAlertDialog(MainActivity.this, SweetAlertDialog.SUCCESS_TYPE)
                                .setTitleText("Đăng nhập thành công")
                                .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                    @Override
                                    public void onClick(SweetAlertDialog sweetAlertDialog) {
                                        sweetAlertDialog.dismissWithAnimation();
                                        launchActivityMenu();
                                    }
                                })
                                .show();
                    } else {
                        new SweetAlertDialog(MainActivity.this, SweetAlertDialog.ERROR_TYPE)
                        .setTitleText("Đăng nhập thất bại")
                        .setContentText("Sai tài khoản hoặc mật khẩu, vui lòng nhập lại")
                        .show();
                        pDialog.dismissWithAnimation();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }.execute(TaiKhoan, MatKhau);
    }


    public void LaunchForget(View view) {
        Intent intent = new Intent(this, Forget.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_left,R.anim.slide_out_right);
    }

    public void LaunchRegister(View view) {
        Intent intent = new Intent(this, Register.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_right,R.anim.slide_out_left);
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        }
}
