package com.example.tranquangthien;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class activity_UpDate extends AppCompatActivity {
    EditText update_email,update_pass, update_rePass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__update);

        AnhXa();
    }
    private void AnhXa() {

        update_email = (EditText)findViewById(R.id.txt_formUpdate_email);
        update_pass = (EditText)findViewById(R.id.txt_formUpdate_password);
        update_rePass = (EditText)findViewById(R.id.txt_formUpdate_repassword);

    }

    public void upDate(View view) {

        if(update_email.getText().toString().equals("")||update_pass.getText().toString().equals("")||update_rePass.getText().toString().equals(""))
        {
            Toast.makeText(this,"nhập đầy đủ thông tin nhé", Toast.LENGTH_SHORT).show();
        }

        else
        {

                Toast.makeText(this, "Đổi mật khẩu thành công", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
        }
    }
}
