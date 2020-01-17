package com.example.ailatrieuphu;

import android.os.AsyncTask;

import java.util.HashMap;

public class ForgetLoader extends AsyncTask<String, Void, String> {
    @Override
    protected String doInBackground(String... strings) {
        String TaiKhoan = strings[0];
        HashMap<String, String> params = new HashMap<>();
        params.put("ten_dang_nhap", TaiKhoan);
        return NetworkUtils.doRequest("mail/send", "POST", params, null);
    }
}