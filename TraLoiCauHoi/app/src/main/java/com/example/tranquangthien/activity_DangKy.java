package com.example.tranquangthien;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.AsyncTaskLoader;
import androidx.loader.content.Loader;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.URLEncoder;

public class activity_DangKy extends AppCompatActivity {
    EditText username, email, password, re_password, formlogin_email, formlogin_password;
    Button btn_dangky, btn_huy;
    private final int DANG_KY = 1;
    private String data = "";
    Context _context;
    String login_email, login_pass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dangky);
        AnhXa();
    }

    private void AnhXa() {
        formlogin_email = (EditText) findViewById(R.id.login_email);
        formlogin_password = (EditText) findViewById(R.id.login_password);
        username = (EditText) findViewById(R.id.signup_username);
        email = (EditText) findViewById(R.id.signup_email);
        password = (EditText) findViewById(R.id.signup_password);
        re_password = (EditText) findViewById(R.id.signup_re_password);
        btn_dangky = (Button) findViewById(R.id.btn_formSignup_signup);
        btn_huy = (Button) findViewById(R.id.btn_formSignup_huy);
    }

    private LoaderManager.LoaderCallbacks<String> dangKy = new LoaderManager.LoaderCallbacks<String>() {
        @NonNull
        @Override
        public Loader<String> onCreateLoader(int id, @Nullable Bundle args) {
            return new DangKyLoader(_context, data);
        }

        @Override
        public void onLoadFinished(@NonNull Loader<String> loader, String data) {
            try {
                JSONObject jsonObject = new JSONObject(data);
                String success = jsonObject.getString("success");
                String message = jsonObject.getString("message");
                Toast.makeText(activity_DangKy.this, message, Toast.LENGTH_SHORT).show();
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onLoaderReset(@NonNull Loader<String> loader) {

        }
    };


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
        String tenDangNhap = URLEncoder.encode(username.getText().toString());
        String matKhau = URLEncoder.encode(password.getText().toString());
        String nlMatKhau = URLEncoder.encode(re_password.getText().toString());
        String mail = URLEncoder.encode(email.getText().toString());
        if(!nlMatKhau.equals(matKhau)){
            Toast.makeText(this, "Mật khẩu nhập lại không giống nhau", Toast.LENGTH_SHORT).show();
        }
        else if(mail.contains("@")) {
            Toast.makeText(this, "Email không hợp lệ", Toast.LENGTH_SHORT).show();
        }
        else{
            data = URLEncoder.encode("ten_dang_nhap")+"="+tenDangNhap+"&"+URLEncoder.encode("mat_khau")+"="+matKhau+"&"+URLEncoder.encode("email")+"="+mail;
            getSupportLoaderManager().initLoader(DANG_KY,null,dangKy);
        }
    }
}
class DangKyLoader extends AsyncTaskLoader<String> {

    final String data;

    public DangKyLoader(@NonNull Context context, String data) {
        super(context);
        this.data = data;
    }

    @Override
    protected void onStartLoading() {
        super.onStartLoading();
        forceLoad();
    }

    @Nullable
    @Override
    public String loadInBackground() {
        return NetworkUtils.getJSONPostData("dang-ky", this.data);
    }

}
