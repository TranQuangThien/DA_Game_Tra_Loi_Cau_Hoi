package com.example.tranquangthien;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class activity_DangKy extends AppCompatActivity {
    EditText username, email, password, re_password, formlogin_email, formlogin_password;
    Button btn_dangky, btn_huy;
    String login_email, login_pass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dangky);
        AnhXa();
    }

    private void AnhXa() {
        formlogin_email = (EditText)findViewById(R.id.login_email);
        formlogin_password = (EditText)findViewById(R.id.login_password);
        username = (EditText)findViewById(R.id.signup_username);
        email = (EditText)findViewById(R.id.signup_email);
        password = (EditText)findViewById(R.id.signup_password);
        re_password = (EditText)findViewById(R.id.signup_re_password);
        btn_dangky = (Button)findViewById(R.id.btn_formSignup_signup);
        btn_huy = (Button)findViewById(R.id.btn_formSignup_huy);
    }

    public void resetForm(View view) {

        username.setText("");
        email.setText("");
        password.setText("");
        re_password.setText("");
        username.requestFocus();
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);

    }

    public void suKienDangKy(View view) {

        if (username.getText().toString().equals("")||email.getText().toString().equals("")||password.getText().toString().equals("")||re_password.getText().toString().equals(""))
        {

            Toast.makeText(this,"nhập đầy đủ thông tin nha",Toast.LENGTH_SHORT).show();

        }
        else
        {
            Toast.makeText(this,"Đăng kí thành công",Toast.LENGTH_SHORT).show();
            login_email = username.getText().toString().trim();
            login_pass = password.getText().toString().trim();
            formlogin_email.setText(login_email);
            formlogin_password.setText(login_pass);
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }
    }
}
