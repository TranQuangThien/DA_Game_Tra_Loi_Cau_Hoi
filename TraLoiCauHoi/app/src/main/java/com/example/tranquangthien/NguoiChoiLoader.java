package com.example.tranquangthien;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.loader.content.AsyncTaskLoader;

import java.io.InputStream;
import java.util.Objects;

public class NguoiChoiLoader extends AsyncTaskLoader<Bitmap> {

    private String urlImage;

    public NguoiChoiLoader(@NonNull Context context, String img) {
        super(context);
        urlImage = img;
    }
    @Override
    protected void onStartLoading() {
        super.onStartLoading();
        forceLoad();
    }
    @Nullable
    @Override
    public Bitmap loadInBackground() {
        String urldisplay = urlImage;
        Bitmap bmp = null;
        try {
            InputStream in = new java.net.URL(urldisplay).openStream();
            bmp = BitmapFactory.decodeStream(in);
        } catch (Exception e) {
            Log.e("Error", Objects.requireNonNull(e.getMessage()));
            e.printStackTrace();
        }
        return bmp;
    }

    public static class Question {

        private String question;
        private int level;
        private String caseA, caseB, caseC, caseD;
        int trueCase;

        public Question(String question, int level, String caseA, String caseB, String caseC, String caseD, int trueCase) {
            this.question = question;
            this.level = level;
            this.caseA = caseA;
            this.caseB = caseB;
            this.caseC = caseC;
            this.caseD = caseD;
            this.trueCase = trueCase;
        }

        public String getQuestion() {
            return question;
        }

        public int getLevel() {
            return level;
        }

        public String getCaseA() {
            return caseA;
        }

        public String getCaseB() {
            return caseB;
        }

        public String getCaseC() {
            return caseC;
        }

        public int getTrueCase() {
            return trueCase;
        }

        public String getCaseD() {
            return caseD;
        }
    }
}
