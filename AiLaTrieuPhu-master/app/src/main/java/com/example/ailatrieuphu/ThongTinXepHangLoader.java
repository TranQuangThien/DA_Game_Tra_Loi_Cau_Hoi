package com.example.ailatrieuphu;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.loader.content.AsyncTaskLoader;

import java.util.HashMap;

public class ThongTinXepHangLoader extends AsyncTaskLoader<String> {
    private final int page;
    private final int limit;

    public ThongTinXepHangLoader(@NonNull Context context,int page, int limit) {
        super(context);
        this.page = page;
        this.limit = limit;
    }

    @Nullable
    @Override
    public String loadInBackground() {
        HashMap<String, String> queryParams = new HashMap<>();
        queryParams.put("page", Integer.toString(this.page));
        queryParams.put("limit", Integer.toString(this.limit));
        String json = "";
        try {
            json = NetworkUtils.getJSONData("nguoi-choi", "GET", queryParams);
        } catch (Exception ex) {
            return null;
        }
        return json;
    }

        @Override
    protected void onStartLoading() {
        super.onStartLoading();
        forceLoad();
    }
}
