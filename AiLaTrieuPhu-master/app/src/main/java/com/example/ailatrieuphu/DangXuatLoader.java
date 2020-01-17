package com.example.ailatrieuphu;

import android.os.AsyncTask;

import java.util.HashMap;

public class DangXuatLoader extends AsyncTask<String, Void, String> {
    @Override
    protected String doInBackground(String... strings) {
        String Token = strings[0];
        HashMap<String, String> params = new HashMap<>();
        params.put("token", Token);
        return NetworkUtils.doRequest("dang-xuat", "POST", params, null);
    }
}