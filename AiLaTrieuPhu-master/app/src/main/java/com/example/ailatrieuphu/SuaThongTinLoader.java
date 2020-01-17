package com.example.ailatrieuphu;

import android.os.AsyncTask;

import java.util.HashMap;

public class SuaThongTinLoader extends AsyncTask<String, Void, String> {
    @Override
    protected String doInBackground(String... strings) {
        String TaiKhoan = strings[0];
        String Email = strings[1];
        String Password = strings[2];
        String id = strings[3];
        HashMap<String, String> params = new HashMap<>();
        params.put("nguoi_choi_id", id);
        params.put("ten_dang_nhap", TaiKhoan);
        params.put("email", Email);
        params.put("mat_khau", Password);
        return NetworkUtils.doRequest("sua-thong-tin", "POST", params, null);
    }
}
