package com.example.ailatrieuphu;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.Loader;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class NapCredit extends AppCompatActivity implements LoaderManager.LoaderCallbacks<String>, View.OnClickListener {
    private Button btn100C;
    private Button btn500C;
    private Button btn1000C;
    private Button btn2500C;
    private Button btn5000C;
    private Button btn10000C;
    String id1,id2,id3,id4,id5,id6;
    String goi1,goi2,goi3,goi4,goi5,goi6;
    String tien1,tien2,tien3,tien4,tien5,tien6;
    TextView txtCredit;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nap_credit);

        if (getSupportLoaderManager().getLoader(0) != null) {
            getSupportLoaderManager().initLoader(0, null, this);
        }
        getSupportLoaderManager().restartLoader(0, null, this);
        this.btn100C = findViewById(R.id.btn100);
        this.btn500C = findViewById(R.id.btn500);
        this.btn1000C = findViewById(R.id.btn1000);
        this.btn2500C = findViewById(R.id.btn2500);
        this.btn5000C = findViewById(R.id.btn5000);
        this.btn10000C = findViewById(R.id.btn10000);
        sharedPreferences = getSharedPreferences("com.example.ailatrieuphu", MODE_PRIVATE);
        editor = sharedPreferences.edit();
        String token = sharedPreferences.getString("TOKEN", "");
        Log.d("TOKEN", token);
        if (token == "") {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }
        String credit = sharedPreferences.getString("credit", "");
        txtCredit = findViewById(R.id.lblSoDu);
        this.txtCredit.setText("Số dư trong tài khoản: " + credit );

        btn100C.setOnClickListener(this);
        btn500C.setOnClickListener(this);
        btn1000C.setOnClickListener(this);
        btn2500C.setOnClickListener(this);
        btn5000C.setOnClickListener(this);
        btn10000C.setOnClickListener(this);

    }

    @Override
    public void finish(){
        super.finish();
        overridePendingTransition(R.anim.slide_left,R.anim.slide_out_right);
    }

    @NonNull
    @Override
    public Loader onCreateLoader(int id, @Nullable Bundle args) {
        return new CreditLoader(this);
    }

    @Override
    public void onLoadFinished(@NonNull Loader<String> loader, String data) {
        try {
            JSONObject jsonObject = new JSONObject(data);
            JSONArray itemsArray = jsonObject.getJSONArray("lstDanhSach");
            for (int i = 0; i < itemsArray.length(); i++) {

                this.btn100C.setText(itemsArray.getJSONObject(i).getString("credit") + " CREDIT \n" +itemsArray.getJSONObject(i).getString("so_tien") + "VNĐ");
                id1 = itemsArray.getJSONObject(i).getString("id");
                goi1 = itemsArray.getJSONObject(i).getString("credit");
                tien1 = itemsArray.getJSONObject(i).getString("so_tien");
                i++;
                this.btn500C.setText(itemsArray.getJSONObject(i).getString("credit") + " CREDIT \n" +itemsArray.getJSONObject(i).getString("so_tien") + "VNĐ");
                id2 = itemsArray.getJSONObject(i).getString("id");
                goi2 = itemsArray.getJSONObject(i).getString("credit");
                tien2 = itemsArray.getJSONObject(i).getString("so_tien");
                i++;
                this.btn1000C.setText(itemsArray.getJSONObject(i).getString("credit") + " CREDIT \n" +itemsArray.getJSONObject(i).getString("so_tien") + "VNĐ");
                id3 = itemsArray.getJSONObject(i).getString("id");
                goi3 = itemsArray.getJSONObject(i).getString("credit");
                tien3 = itemsArray.getJSONObject(i).getString("so_tien");
                i++;
                this.btn2500C.setText(itemsArray.getJSONObject(i).getString("credit") + " CREDIT \n" +itemsArray.getJSONObject(i).getString("so_tien") + "VNĐ");
                id4 = itemsArray.getJSONObject(i).getString("id");
                goi4 = itemsArray.getJSONObject(i).getString("credit");
                tien4 = itemsArray.getJSONObject(i).getString("so_tien");
                i++;
                this.btn5000C.setText(itemsArray.getJSONObject(i).getString("credit") + " CREDIT \n" +itemsArray.getJSONObject(i).getString("so_tien") + "VNĐ");
                id5 = itemsArray.getJSONObject(i).getString("id");
                goi5 = itemsArray.getJSONObject(i).getString("credit");
                tien5 = itemsArray.getJSONObject(i).getString("so_tien");
                i++;
                this.btn10000C.setText(itemsArray.getJSONObject(i).getString("credit") + " CREDIT \n" +itemsArray.getJSONObject(i).getString("so_tien") + "VNĐ");
                id6 = itemsArray.getJSONObject(i).getString("id");
                goi6 = itemsArray.getJSONObject(i).getString("credit");
                tien6 = itemsArray.getJSONObject(i).getString("so_tien");
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onLoaderReset(@NonNull Loader<String> loader) {

    }





    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn100:
                Confirm(id1,goi1,tien1);break;
            case R.id.btn500:
                Confirm(id2,goi2,tien2);break;
            case R.id.btn1000:
                Confirm(id3,goi3,tien3);break;
            case R.id.btn2500:
                Confirm(id4,goi4,tien4);break;
            case R.id.btn5000:
                Confirm(id5,goi5,tien5);break;
            case R.id.btn10000:
                Confirm(id6,goi6,tien6);break;
        }
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent (NapCredit.this, TrangChu.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_left,R.anim.slide_out_right);
        }
}
