package com.example.ailatrieuphu;

import android.os.AsyncTask;

import java.util.HashMap;

public class NapCreditLoader extends AsyncTask<String, Void, String> {
    @Override
    protected String doInBackground(String... strings) {
        String TaiKhoan = strings[0];
        String GoiCredit = strings[1];
        HashMap<String, String> params = new HashMap<>();
        params.put("nguoi_choi_id", TaiKhoan);
        params.put("goi_credit_id", GoiCredit);
        return NetworkUtils.doRequest("nap-credit", "POST", params, null);
    }
}

