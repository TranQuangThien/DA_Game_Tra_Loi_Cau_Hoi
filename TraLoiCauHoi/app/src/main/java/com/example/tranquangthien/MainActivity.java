package com.example.tranquangthien;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.Loader;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.facebook.CallbackManager;
import com.facebook.login.LoginManager;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.URLEncoder;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<String> {
    EditText login_mail, login_pass, editText_formsignup_email,getEditText_formsignup_password;
    Button btn_LoginFB;
    String data = "";
    ProgressDialog progressDialog;
    CallbackManager callbackManager;
    boolean doubleBackToExitPressedOnce = false;
    public SharedPreferences sharedPreferences;
    public static final String SHARE_NAME = "LUU_TOKEN";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        AnhXa();
        login_mail.setText("quangthien");
        login_pass.setText("123");
        sharedPreferences = getSharedPreferences(SHARE_NAME, Context.MODE_PRIVATE);
        NguoiChoi.token = sharedPreferences.getString("token",null);
        if(NguoiChoi.token != null){
            Intent intent = new Intent(this,activity_Main_Chinh.class);
            startActivity(intent);}


//        callbackManager = CallbackManager.Factory.create();
//        LoginManager.getInstance().registerCallback(callbackManager,
//                new FacebookCallback<LoginResult>() {
//                    @Override
//                    public void onSuccess(LoginResult loginResult) {
//                        Intent intent = new Intent(MainActivity.this,activity_Main_Chinh.class);
//                        startActivity(intent);
//                    }
//
//                    @Override
//                    public void onCancel() {
//                        // App code
//                    }
//
//                    @Override
//                    public void onError(FacebookException exception) {
//                        // App code
//                    }
//                });
//        AccessToken accessToken = AccessToken.getCurrentAccessToken();
//        boolean isLoggedIn = accessToken != null && !accessToken.isExpired();
//        if(isLoggedIn){
//            Intent intent = new Intent(MainActivity.this,activity_Main_Chinh.class);
//            startActivity(intent);
//        }
//


    }

    private void AnhXa() {
        btn_LoginFB = (Button)findViewById(R.id.btn_loginfb);
        login_mail = (EditText)findViewById(R.id.login_email);
        login_pass = (EditText)findViewById(R.id.login_password);
        editText_formsignup_email = (EditText)findViewById(R.id.signup_email);
        getEditText_formsignup_password = (EditText)findViewById(R.id.signup_password);
    }

    @Override
    protected void onPause() {
        super.onPause();
        SharedPreferences.Editor share = sharedPreferences.edit();
        share.putString("token",NguoiChoi.token).apply();
    }

    @Override
    public void onBackPressed() {

        if(doubleBackToExitPressedOnce){
            super.onBackPressed();
            return;
        }
        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "Nhấn lần nữa để thoát", Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBackToExitPressedOnce=false;
            }
        }, 3000);
    }


    public void openFormSignup(View view) {
        Intent intent = new Intent(this, activity_DangKy.class);
        startActivity(intent);
    }


    public void openLogin(View view) {

        String user = login_mail.getText().toString();
        String pass = login_pass.getText().toString();
        this.data = URLEncoder.encode("ten_dang_nhap")
                +"="+URLEncoder.encode(user)
                +"&"+ URLEncoder.encode("mat_khau")
                +"="+URLEncoder.encode(pass);
        progressDialog = new ProgressDialog(this);
        progressDialog.create();
        progressDialog.setMessage("Đang đăng nhập");
        progressDialog.show();
        if(getSupportLoaderManager().getLoader(0) != null)
            getSupportLoaderManager().restartLoader(0,null,this);
        getSupportLoaderManager().initLoader(0,null,this);
    }

    @NonNull
    @Override
    public Loader<String> onCreateLoader(int id, @Nullable Bundle args) {
        return new DangNhapLoader(this,this.data);
    }

    @Override
    public void onLoadFinished(@NonNull Loader<String> loader, String data) {
        //String message = "";
        try {
            JSONObject jsonObject = new JSONObject(data);
            NguoiChoi.token = jsonObject.getString("token");
            boolean success = jsonObject.getBoolean("success");

            if(!success){
                Toast.makeText(this, "Tên đăng nhập hoặc mật khẩu không đúng",
                        Toast.LENGTH_SHORT).show();
            }
            else{
                Intent intent =new Intent(this,activity_Main_Chinh.class);
                startActivity(intent);

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        progressDialog.dismiss();
    }

    @Override
    public void onLoaderReset(@NonNull Loader<String> loader) {

    }

    public void signup_fb(View view) {
        LoginManager.getInstance().logInWithReadPermissions(this, Arrays.asList("public_profile"));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        callbackManager.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);

    }

    public void openQuenMatKhau(View view) {
        Intent intent = new Intent(this, activity_QuenMatKhau.class);
        startActivity(intent);
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}
