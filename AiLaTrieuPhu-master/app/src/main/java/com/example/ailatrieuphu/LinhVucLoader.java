package com.example.ailatrieuphu;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.loader.content.AsyncTaskLoader;

public class LinhVucLoader extends AsyncTaskLoader<String> {
    public LinhVucLoader(@NonNull Context context) {
        super(context);
    }

    @Nullable
    @Override
    public String loadInBackground() {
        return NetworkUtils.getJSONData2("linh-vuc","GET");
    }
    @Override
    protected void onStartLoading() {
        super.onStartLoading();
        forceLoad();
    }
}
