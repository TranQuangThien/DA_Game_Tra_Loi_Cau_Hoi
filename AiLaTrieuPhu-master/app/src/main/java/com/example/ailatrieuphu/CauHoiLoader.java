package com.example.ailatrieuphu;

import android.os.AsyncTask;

import java.util.HashMap;

public class CauHoiLoader extends AsyncTask<String, Void, String> {
    @Override
    protected String doInBackground(String... strings) {
        String id = strings[0];
        HashMap<String, String> params = new HashMap<>();
        params.put("linh_vuc_id", id);
        return NetworkUtils.doRequest("load-cau-hoi", "POST", params, null);
    }
}
