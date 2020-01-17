package com.example.ailatrieuphu;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONException;
import org.json.JSONObject;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class Forget extends AppCompatActivity {
    EditText User;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget);
    }

    @Override
    public void finish(){
        super.finish();
        overridePendingTransition(R.anim.slide_right,R.anim.slide_out_left);
    }

    public void GetPassword(View view) {
        User = findViewById(R.id.txtUsername2);
        final String TaiKhoan = User.getText().toString();
        final SweetAlertDialog pDialog = new SweetAlertDialog(this, SweetAlertDialog.PROGRESS_TYPE);
        pDialog.getProgressHelper().setBarColor(Color.parseColor("#A5DC86"));
        pDialog.setTitleText("Loading");
        pDialog.setCancelable(false);
        pDialog.show();
        new ForgetLoader(){
            @Override
            protected void onPostExecute(String s) {
                try {
                    JSONObject json = new JSONObject(s);
                    boolean success = json.getBoolean("success");
                    if (success) {
                        pDialog.dismissWithAnimation();
                        new SweetAlertDialog(Forget.this, SweetAlertDialog.SUCCESS_TYPE)
                                .setTitleText("Thành công")
                                .setContentText("bạn vui lòng kiểm tra mail để lấy lại mật khẩu")
                                .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                    @Override
                                    public void onClick(SweetAlertDialog sweetAlertDialog) {
                                        sweetAlertDialog.dismissWithAnimation();
                                    }
                                })
                                .show();
                    } else {
                        new SweetAlertDialog(Forget.this, SweetAlertDialog.ERROR_TYPE)
                                .setTitleText("Tài khoản không tồn tại")
                                .show();
                        pDialog.dismissWithAnimation();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }.execute(TaiKhoan);

    }
}
