package com.example.ailatrieuphu;

import android.os.AsyncTask;

import java.util.HashMap;

public class DangNhapLoader extends AsyncTask<String, Void, String> {
    @Override
    protected String doInBackground(String... strings) {
        String TaiKhoan = strings[0];
        String MatKhau = strings[1];
        HashMap<String, String> params = new HashMap<>();
        params.put("ten_dang_nhap", TaiKhoan);
        params.put("mat_khau", MatKhau);
        return NetworkUtils.doRequest("dang-nhap", "POST", params, null);
    }
}