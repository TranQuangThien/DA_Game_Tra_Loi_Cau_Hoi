package com.example.tranquangthien;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.loader.content.AsyncTaskLoader;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;

public class DangNhapLoader extends AsyncTaskLoader {

    String data;
    final String BASE_URL = "http://10.0.2.2:8000/api/dang-nhap";

    public DangNhapLoader(@NonNull Context context, String data) {
        super(context);
        this.data = data;
    }

    @Nullable
    @Override
    public Object loadInBackground() {
        return NetworkUtils.getJSONPostData("dang-nhap",this.data);
    }
}
