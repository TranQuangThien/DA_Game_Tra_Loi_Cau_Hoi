package com.example.tranquangthien;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class activity_MuaCreadit extends AppCompatActivity {
    Button btn_20k, btn_50k, btn_100k, btn_500k;
    TextView txt_soCraedit;
    private int soCreadit = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__mua_creadit);
        btn_20k = (Button)findViewById(R.id.creadit_20k);
        btn_50k = (Button)findViewById(R.id.creadit_50k);
        btn_100k = (Button)findViewById(R.id.creadit_100k);
        btn_500k = (Button)findViewById(R.id.creadit_500k);
        txt_soCraedit = (TextView)findViewById(R.id.txt_soCreadit);
    }

    public void mua20k(View view) {

        soCreadit+=20;
        String creadit = String.valueOf(soCreadit + ".000 VNĐ");
        txt_soCraedit.setText(creadit);

    }

    public void mua50k(View view) {
        soCreadit+=50;
        String creadit = String.valueOf(soCreadit + ".000 VNĐ");
        txt_soCraedit.setText(creadit);
    }

    public void mua100k(View view) {

        soCreadit+=100;
        String creadit = String.valueOf(soCreadit + ".000 VNĐ");
        txt_soCraedit.setText(creadit);

    }

    public void mua500k(View view) {

        soCreadit+=500;
        String creadit = String.valueOf(soCreadit + ".000 VNĐ");
        txt_soCraedit.setText(creadit);
    }
}
