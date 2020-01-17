package com.example.ailatrieuphu;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class Register extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
    }

    @Override
    public void finish(){
        super.finish();
        overridePendingTransition(R.anim.slide_left,R.anim.slide_out_right);
    }

    public static boolean isEmailValid(String email) {
        String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
        Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    public void DangKy(View view) {
        EditText txtUser,Email,txtPassword,txtReEnter;
        txtUser = findViewById(R.id.txtSuaTaiKhoan);
        Email = findViewById(R.id.txtEmail);
        txtPassword = findViewById(R.id.txtMatKhau);
        txtReEnter = findViewById(R.id.txtNhapLai);

        String User = txtUser.getText().toString();
        String Mail = Email.getText().toString();
        String Pass = txtPassword.getText().toString();
        String ReEnter = txtReEnter.getText().toString();

        if(isEmailValid(Mail)==false) {
            new SweetAlertDialog(Register.this, SweetAlertDialog.ERROR_TYPE)
                    .setTitleText("Đăng ký thất bại")
                    .setContentText("sai định dạng mail")
                    .show();
        }
        else {
            new RegisterLoader() {
                @Override
                protected void onPostExecute(String s) {
                    try {
                        JSONObject json = new JSONObject(s);
                        boolean success = json.getBoolean("success");
                        if (success) {
                            String msg = json.getString("msg");
                            new SweetAlertDialog(Register.this, SweetAlertDialog.SUCCESS_TYPE)
                                    .setTitleText(msg)
                                    .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                        @Override
                                        public void onClick(SweetAlertDialog sweetAlertDialog) {
                                            sweetAlertDialog.dismissWithAnimation();
                                            finish();
                                        }
                                    })
                                    .show();
                        } else {
                            String msg = json.getString("msg");
                            new SweetAlertDialog(Register.this, SweetAlertDialog.ERROR_TYPE)
                                    .setTitleText("Đăng ký thất bại")
                                    .setContentText(msg)
                                    .show();
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }
            }.execute(User, Mail, Pass, ReEnter);
        }
    }
}
