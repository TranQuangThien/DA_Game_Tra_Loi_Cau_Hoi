package com.example.ailatrieuphu;

import android.os.AsyncTask;

import java.util.HashMap;

public class RegisterLoader extends AsyncTask<String, Void, String> {
    @Override
    protected String doInBackground(String... strings) {
        String TaiKhoan = strings[0];
        String Email = strings[1];
        String MatKhau = strings[2];
        String NhapLaiMatKhau = strings[3];
        HashMap<String, String> params = new HashMap<>();
        params.put("ten_dang_nhap", TaiKhoan);
        params.put("email", Email);
        params.put("mat_khau", MatKhau);
        params.put("nhap_lai_mk", NhapLaiMatKhau);
        return NetworkUtils.doRequest("dang-ky", "POST", params, null);
    }
}
