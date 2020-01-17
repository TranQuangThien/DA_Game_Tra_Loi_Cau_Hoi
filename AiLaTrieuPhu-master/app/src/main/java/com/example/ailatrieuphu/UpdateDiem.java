package com.example.ailatrieuphu;

import android.os.AsyncTask;

import java.util.HashMap;

public class UpdateDiem extends AsyncTask<String, Void, String> {
    @Override
    protected String doInBackground(String... strings) {
        String TaiKhoan = strings[0];
        String Diem = strings[1];
        HashMap<String, String> params = new HashMap<>();
        params.put("nguoi_choi_id", TaiKhoan);
        params.put("diem", Diem);
        return NetworkUtils.doRequest("lay-diem-cao-nhat", "POST", params, null);
    }
}
