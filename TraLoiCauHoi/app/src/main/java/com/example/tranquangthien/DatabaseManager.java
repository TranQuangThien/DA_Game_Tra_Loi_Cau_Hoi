package com.example.tranquangthien;

import android.content.Context;
import android.content.res.AssetManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Environment;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class DatabaseManager {

    private static final String PATHDB = Environment.getDataDirectory().getPath() + "/data/com.TranQuangThien.ailatrieuphu/databases";
    private static final String DB_NAME = "Question";
    private static final String SQL_GET_15_QUESTION = "Select * from(select * from Question order by RANDOM()) group by level order by level LIMIT 15";
    private SQLiteDatabase mSqLiteDatabase;


    public DatabaseManager(Context context) {
        copyDB(context);
        openDB();
}
    public void get15Question(List<NguoiChoiLoader.Question> questions) {
        Cursor cursor = mSqLiteDatabase.rawQuery(SQL_GET_15_QUESTION, null);
        if (cursor != null) {
            cursor.moveToFirst();
            int questionIndex = cursor.getColumnIndex("question");
            int levelIndex = cursor.getColumnIndex("level");
            int caseAIndex = cursor.getColumnIndex("casea");
            int caseBIndex = cursor.getColumnIndex("caseb");
            int caseCIndex = cursor.getColumnIndex("casec");
            int caseDIndex = cursor.getColumnIndex("cased");
            int trueCaseIndex = cursor.getColumnIndex("truecase");

            while (cursor.isAfterLast() == false) {
                String question, caseA, caseB, caseC, caseD;
                int level, trueCase;
                question = cursor.getString(questionIndex);
                level = Integer.parseInt(cursor.getString(levelIndex));
                caseA = cursor.getString(caseAIndex);
                caseB = cursor.getString(caseBIndex);
                caseC = cursor.getString(caseCIndex);
                caseD = cursor.getString(caseDIndex);
                trueCase = Integer.parseInt(cursor.getString(trueCaseIndex));

                NguoiChoiLoader.Question qItem = new NguoiChoiLoader.Question(question, level, caseA, caseB, caseC, caseD, trueCase);

                questions.add(qItem);

                cursor.moveToNext();
            }
        }
    }
    private void openDB() {
        if (mSqLiteDatabase == null || !mSqLiteDatabase.isOpen()) {
            mSqLiteDatabase = SQLiteDatabase.openDatabase(PATHDB + "/" + DB_NAME, null, SQLiteDatabase.OPEN_READWRITE);

        }
    }

    private void copyDB(Context context) {
        new File(PATHDB).mkdir();
        File db = new File(PATHDB + "/" + DB_NAME);
        if (db.exists()) {
            return;
        }
        AssetManager assetManage = context.getAssets();
        try {
            InputStream input = assetManage.open(DB_NAME);
            FileOutputStream ouput = new FileOutputStream(db);
            byte b[] = new byte[1024];
            int lenght = input.read(b);
            while (lenght > 0) {
                ouput.write(b, 0, lenght);
                lenght = input.read(b);
            }
            input.close();
            ouput.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    }
