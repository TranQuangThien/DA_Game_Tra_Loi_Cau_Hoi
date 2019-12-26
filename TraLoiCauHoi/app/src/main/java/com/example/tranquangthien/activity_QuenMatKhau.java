package com.example.tranquangthien;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class activity_QuenMatKhau extends AppCompatActivity {

    String[] email = {"quangthien@gmail.com","admin@gmail.com","hongthang@gmail.com","quoccuong@gmail.com","vancuong@gmail.com" };
    EditText Change_email, Change_pass, Change_rePass;
    String mail, PassWord, Re_Pass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__quen_mat_khau);
        
        AnhXa();
    }

    private void AnhXa() {

        Change_email = (EditText)findViewById(R.id.editText_formQuenPass_email);
        Change_pass = (EditText)findViewById(R.id.editText_formQuenPass_Password);
        Change_rePass = (EditText)findViewById(R.id.editText_formQuenPass_re_Password);
    }

    public void doiMatKhau(View view) {

        if(Change_email.getText().toString().equals("")||Change_pass.getText().toString().equals("")||Change_rePass.getText().toString().equals(""))
        {
            Toast.makeText(this,"nhập đầy đủ thông tin nhé", Toast.LENGTH_SHORT).show();
        }

        else if(Change_pass.getText().toString() != Change_rePass.getText().toString())
        {
            Toast.makeText(this,"mật khẩu không trùng nhau", Toast.LENGTH_SHORT).show();
        }
        else
        {
            Toast.makeText(this,"Đổi mật khẩu thành công", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this,MainActivity.class);
            startActivity(intent);
        }
    }
}
