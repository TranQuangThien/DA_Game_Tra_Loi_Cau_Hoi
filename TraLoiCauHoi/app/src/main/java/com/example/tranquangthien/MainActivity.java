package com.example.tranquangthien;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity {
    EditText login_mail, login_pass, editText_formsignup_email,getEditText_formsignup_password;
    Button btn_LoginFB;
    CallbackManager callbackManager;

    String[] email = {"quangthien@gmail.com","admin@gmail.com","hongthang@gmail.com","quoccuong@gmail.com","vancuong@gmail.com" };
    String[] pass = {"quangthien123","admin123","hongthang123","quoccuong123","vancuong132"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AnhXa();

        callbackManager = CallbackManager.Factory.create();
        LoginManager.getInstance().registerCallback(callbackManager,
                new FacebookCallback<LoginResult>() {
                    @Override
                    public void onSuccess(LoginResult loginResult) {
                        Intent intent = new Intent(MainActivity.this,activity_Main_Chinh.class);
                        startActivity(intent);
                    }

                    @Override
                    public void onCancel() {
                        // App code
                    }

                    @Override
                    public void onError(FacebookException exception) {
                        // App code
                    }
                });
        AccessToken accessToken = AccessToken.getCurrentAccessToken();
        boolean isLoggedIn = accessToken != null && !accessToken.isExpired();
        if(isLoggedIn){
            Intent intent = new Intent(MainActivity.this,activity_Main_Chinh.class);
            startActivity(intent);
        }

    }

    private void AnhXa() {
        btn_LoginFB = (Button)findViewById(R.id.btn_loginfb);
        login_mail = (EditText)findViewById(R.id.login_email);
        login_pass = (EditText)findViewById(R.id.login_password);
        editText_formsignup_email = (EditText)findViewById(R.id.signup_email);
        getEditText_formsignup_password = (EditText)findViewById(R.id.signup_password);
    }

    public void openFormSignup(View view) {
        Intent intent = new Intent(this, activity_DangKy.class);
        startActivity(intent);
    }

    public void openLogin(View view) {
        if(login_mail.getText().toString().equals("") || login_pass.getText().toString().equals(""))
        {
            Toast.makeText(this, "Đăng nhập thất bại", Toast.LENGTH_SHORT).show();
        }
        else if(login_mail.getText().toString().equals(email[0]) || login_pass.getText().toString().equals(pass[0]))
        {
            Toast.makeText(this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, activity_Main_Chinh.class);
            startActivity(intent);
        }
        else if(login_mail.getText().toString().equals(email[1]) || login_pass.getText().toString().equals(pass[1]))
        {
            Toast.makeText(this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, activity_Main_Chinh.class);
            startActivity(intent);
        }
        else if(login_mail.getText().toString().equals(email[2]) || login_pass.getText().toString().equals(pass[2]))
        {
            Toast.makeText(this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, activity_Main_Chinh.class);
            startActivity(intent);
        } else if(login_mail.getText().toString().equals(email[3]) || login_pass.getText().toString().equals(pass[3]))
        {
            Toast.makeText(this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, activity_Main_Chinh.class);
            startActivity(intent);
        }
        else if(login_mail.getText().toString().equals(email[4]) || login_pass.getText().toString().equals(pass[4 ]))
        {
            Toast.makeText(this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, activity_Main_Chinh.class);
            startActivity(intent);
        }
        else
        {
            Toast.makeText(this, "Đăng nhập thất bại", Toast.LENGTH_SHORT).show();
        }
    }
    public void signup_fb(View view) {
        LoginManager.getInstance().logInWithReadPermissions(this, Arrays.asList("public_profile"));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        callbackManager.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
        Intent intent = new Intent(this, activity_Main_Chinh.class);
        startActivity(intent);
    }

    public void openQuenMatKhau(View view) {
        Intent intent = new Intent(this, activity_QuenMatKhau.class);
        startActivity(intent);
    }
}
