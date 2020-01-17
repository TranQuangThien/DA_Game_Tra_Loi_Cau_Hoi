package com.example.ailatrieuphu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class ThongTinCaNhan extends AppCompatActivity {
    EditText SuaTaiKhoan,SuaEmail,SuaMatKhau,NhapLaiMatKhau;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thong_tin_ca_nhan);
        SuaTaiKhoan = findViewById(R.id.txtSuaTaiKhoan);
        SuaEmail = findViewById(R.id.txtSuaEmail);
        SuaMatKhau = findViewById(R.id.txtSuaMK);
        NhapLaiMatKhau = findViewById(R.id.txtSuaMK2);
        sharedPreferences = getSharedPreferences("com.example.ailatrieuphu", MODE_PRIVATE);
        editor = sharedPreferences.edit();
        String token = sharedPreferences.getString("TOKEN", "");
        Log.d("TOKEN", token);
        if (token == "") {
            Intent intent = new Intent(this, TrangChu.class);
            startActivity(intent);
        }
        SuaTaiKhoan.setText(sharedPreferences.getString("ten_dang_nhap", ""));
        SuaEmail.setText(sharedPreferences.getString("email", ""));
    }

    public static boolean isEmailValid(String email) {
        String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
        Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }


    public void Sua(View view) {
        SuaTaiKhoan = findViewById(R.id.txtSuaTaiKhoan);
        SuaEmail = findViewById(R.id.txtSuaEmail);
        SuaMatKhau = findViewById(R.id.txtSuaMK);
        NhapLaiMatKhau = findViewById(R.id.txtSuaMK2);
        String TaiKhoan = SuaTaiKhoan.getText().toString();
        String Email = SuaEmail.getText().toString();
        String MatKhau = SuaMatKhau.getText().toString();
        String NhapLaiMK = NhapLaiMatKhau.getText().toString();
        String Check = "";
        String id = sharedPreferences.getString("id", "");

        if(MatKhau.equals(Check) && NhapLaiMK.equals(Check))
        {
            MatKhau =sharedPreferences.getString("password", "");
            NhapLaiMK = sharedPreferences.getString("password", "");

        }
        final String MK = MatKhau;

        if (!MatKhau.equals(Check) && !NhapLaiMK.equals(Check) && MatKhau.equals(NhapLaiMK) && isEmailValid(Email) == true) {
            new SuaThongTinLoader().execute(TaiKhoan, Email, MatKhau, id);
            new SweetAlertDialog(ThongTinCaNhan.this, SweetAlertDialog.SUCCESS_TYPE)
                    .setTitleText("Sửa thành công")
                    .show();
            new DangNhapLoader(){
                @Override
                protected void onPostExecute(String s) {
                    try {

                        JSONObject json = new JSONObject(s);
                        String User = json.getString("ten_dang_nhap");
                        String Mail = json.getString("email");
                        String token = json.getString("token");
                        new DangXuatLoader().execute(token) ;
                        editor.remove("ten_dang_nhap");
                        editor.remove("email");
                        editor.remove("password");
                        editor.putString("ten_dang_nhap", User);
                        editor.putString("email", Mail);
                        editor.putString("password", MK);
                        editor.commit();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }
            }.execute(TaiKhoan,MatKhau);

        } else if (isEmailValid(Email) == false) {
            new SweetAlertDialog(ThongTinCaNhan.this, SweetAlertDialog.ERROR_TYPE)
                    .setTitleText("sai định dạng email")
                    .show();
        } else {
            new SweetAlertDialog(ThongTinCaNhan.this, SweetAlertDialog.ERROR_TYPE)
                    .setTitleText("vui lòng kiểm tra mật khẩu")
                    .show();
        }

    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent (ThongTinCaNhan.this, TrangChu.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_left,R.anim.slide_out_right);
    }
}
