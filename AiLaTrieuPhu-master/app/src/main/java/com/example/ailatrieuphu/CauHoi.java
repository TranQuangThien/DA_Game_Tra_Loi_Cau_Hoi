package com.example.ailatrieuphu;

import androidx.annotation.DrawableRes;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.SystemClock;
import android.renderscript.Sampler;
import android.util.Log;
import android.view.ContextThemeWrapper;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Random;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class CauHoi extends AppCompatActivity {
    Button btn1;
    Button btn2;
    Button btn3;
    Button btn4;
    TextView NoiDung,txtCredit,txtLife,txtDiem;
    Button BtnCau;
    int DapAn;
    int Life = 3;
    int Diem = 0;
    int Cau = 1;

    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    private static final long START_TIME_IN_MILLIS= 30000;

    private TextView mTextViewCountDown;
    private ProgressBar mProgressBar;

    private CountDownTimer mCountDownTimer;
    private boolean mTimerRunning;
    private long mTimeLeftInMillis = START_TIME_IN_MILLIS;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cau_hoi);

        this.btn1= findViewById(R.id.btnTraLoi1);
        this.btn2= findViewById(R.id.btnTraLoi2);
        this.btn3= findViewById(R.id.btnTraLoi3);
        this.btn4= findViewById(R.id.btnTraLoi4);
        txtDiem = findViewById(R.id.txtDiem);
        BtnCau = findViewById(R.id.btnSoCau);
        txtCredit = findViewById(R.id.TienNap);
        NoiDung = findViewById(R.id.txtNoiDung);
        txtLife = findViewById(R.id.txtLife);

        sharedPreferences = getSharedPreferences("com.example.ailatrieuphu", MODE_PRIVATE);
        editor = sharedPreferences.edit();
        String token = sharedPreferences.getString("TOKEN", "");
        Log.d("TOKEN", token);
        if (token == "") {
            Intent intent = new Intent(this, TrangChu.class);
            startActivity(intent);
        }
        String credit = sharedPreferences.getString("credit", "");
        this.txtCredit.setText(credit);

        Intent intent = getIntent();
        String ID = intent.getStringExtra("ID");
        Life = intent.getIntExtra("Life", 3);
        Diem = intent.getIntExtra("Diem", 0);
        Cau = intent.getIntExtra("Cau", 1);

        String D = String.valueOf(Diem);
        txtDiem.setText("Điểm: "+D);
        BtnCau.setText(String.valueOf(Cau));
        txtLife.setText(String.valueOf(Life));


        new CauHoiLoader(){
            @Override
            protected void onPostExecute(String a) {
                try {
                    JSONObject json = new JSONObject(a);
                    JSONArray item = json.getJSONArray("dsCauHoi");
                    NoiDung.setText(item.getJSONObject(0).getString("noi_dung"));
                    btn1.setText(item.getJSONObject(0).getString("phuong_an_a"));
                    btn2.setText(item.getJSONObject(0).getString("phuong_an_b"));
                    btn3.setText(item.getJSONObject(0).getString("phuong_an_c"));
                    btn4.setText(item.getJSONObject(0).getString("phuong_an_d"));
                    DapAn = item.getJSONObject(0).getInt("dap_an");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }.execute(ID);


        Button mShowDialog = findViewById(R.id.btnTroGiup);
        mShowDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogChart();
            }
        });

        Button mShowDialogCall = findViewById(R.id.btnCall);
        mShowDialogCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogCall();
            }
        });

        mTextViewCountDown = findViewById(R.id.txtCountDown);
        mProgressBar = findViewById(R.id.ThanhThoiGian);
        mProgressBar.setMax((int) START_TIME_IN_MILLIS / 1000);
        mProgressBar.setProgress((int) START_TIME_IN_MILLIS / 1000);
        if(!mTimerRunning)
        startTimer();
    }

    private void startTimer() {
        mCountDownTimer = new CountDownTimer(mTimeLeftInMillis, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                mTimeLeftInMillis = millisUntilFinished;
                mProgressBar.setProgress((int) (millisUntilFinished / 1000));
                updateCountDownText();
            }

            @Override
            public void onFinish() {
                if (Life == 0) {
                    inCorrect();
                } else {
                    new SweetAlertDialog(CauHoi.this, SweetAlertDialog.ERROR_TYPE)
                            .setTitleText("Đã hết giờ")
                            .setConfirmText("Tiếp theo")
                            .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                @Override
                                public void onClick(SweetAlertDialog sweetAlertDialog) {
                                    sweetAlertDialog.dismissWithAnimation();
                                    Cau = Cau + 1;
                                    Life = Life - 1;
                                    Intent intent = new Intent(CauHoi.this, ChonLinhVuc.class);
                                    intent.putExtra("Diem", Diem);
                                    intent.putExtra("Life", Life);
                                    intent.putExtra("Cau", Cau);
                                    startActivity(intent);
                                    overridePendingTransition(0, 0);
                                }
                            })
                            .show();
            }
            }
        }.start();

        mTimerRunning = true;
    }

    private void updateCountDownText(){
        int minutes = (int) (mTimeLeftInMillis / 1000 ) / 60;
        int seconds = (int) (mTimeLeftInMillis / 1000) % 60;


        String timeLeftFormatted = String.format(Locale.getDefault(), "%02d:%02d", minutes, seconds);

        mTextViewCountDown.setText(timeLeftFormatted);
    }

    public void Reset(View view) {
        new SweetAlertDialog(CauHoi.this, SweetAlertDialog.WARNING_TYPE)
                .setTitleText("Thông báo")
                .setContentText("Bạn có chắc là muốn Reset ?? điểm của bạn sẽ mất")
                .setCancelButton("không", new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sweetAlertDialog) {
                        sweetAlertDialog.dismissWithAnimation();
                    }
                })
                .setConfirmText("có")
                .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sweetAlertDialog) {
                        mCountDownTimer.cancel();
                        mTimerRunning = false;
                        mTimeLeftInMillis = START_TIME_IN_MILLIS;
                        Intent intent = new Intent (CauHoi.this,ChonLinhVuc.class);
                        startActivity(intent);
                        overridePendingTransition(0, 0);
                    }
                })
                .show();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        final String NguoiChoi = sharedPreferences.getString("id", "");
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            new SweetAlertDialog(CauHoi.this, SweetAlertDialog.WARNING_TYPE)
                    .setTitleText("Thông báo")
                    .setContentText("Bạn có chắc là muốn thoát ??")
                    .setCancelButton("không", new SweetAlertDialog.OnSweetClickListener() {
                        @Override
                        public void onClick(SweetAlertDialog sweetAlertDialog) {
                            sweetAlertDialog.dismissWithAnimation();
                        }
                    })
                    .setConfirmText("có")
                    .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                        @Override
                        public void onClick(SweetAlertDialog sweetAlertDialog) {
                            mCountDownTimer.cancel();
                            mTimerRunning = false;
                            mTimeLeftInMillis = START_TIME_IN_MILLIS;
                            new UpdateDiem().execute(NguoiChoi,String.valueOf(Diem));
                            Intent intent = new Intent (CauHoi.this,TrangChu.class);
                            startActivity(intent);
                            overridePendingTransition(R.anim.slide_right,R.anim.slide_out_left);
                        }
                    })
                    .show();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    private void DialogChart(){

        final Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialog_chart);
        dialog.show();

        dialog.setCanceledOnTouchOutside(false);

        dialog.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);

        Button mThank = dialog.findViewById(R.id.thank);
        mThank.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        BarChart barChart;
        barChart = dialog.findViewById(R.id.chart1);

        BarDataSet barDataSet1 = new BarDataSet(dataValues1(), "");

        ArrayList<Integer> colors = new ArrayList<Integer>();
        colors.add(ContextCompat.getColor(this,android.R.color.holo_orange_light));
        colors.add(ContextCompat.getColor(this, android.R.color.holo_blue_light));
        colors.add(ContextCompat.getColor(this, android.R.color.holo_green_light));
        colors.add(ContextCompat.getColor(this, android.R.color.holo_red_light));
        barDataSet1.setColors(colors);

        barDataSet1.setStackLabels(new String[]{"A","B","C","D"});

        barChart.getDescription().setEnabled(false);
        barDataSet1.setValueTextColor(Color.WHITE);
        barDataSet1.setValueTextSize(13f);
        BarData barData = new BarData();
        barData.addDataSet(barDataSet1);
        XAxis xAxis = barChart.getXAxis();
        xAxis.setTextColor(Color.WHITE);
        YAxis Left = barChart.getAxisLeft();
        Left.setTextColor(Color.WHITE);
        YAxis Right = barChart.getAxisRight();
        Right.setTextColor(Color.WHITE);
        xAxis.setDrawGridLines(false);
        Left.setDrawGridLines(false);
        Right.setDrawGridLines(false);
        xAxis.setDrawAxisLine(false);
        Left.setDrawAxisLine(false);
        Right.setDrawAxisLine(false);
        xAxis.setDrawLabels(false);
        Left.setDrawLabels(false);
        Right.setDrawLabels(false);

        barChart.setData(barData);
        barChart.invalidate();


    }

    Random rand1 = new Random();
    int a = rand1.nextInt(20);
    Random rand2 = new Random();
    int b = rand2.nextInt(20);
    Random rand3 = new Random();
    int c = rand3.nextInt(20);
    Random rand4 = new Random();
    int d = rand4.nextInt(20);

    String name[] = {"A","B","C","D"};
    Random random = new Random();
    int index = random.nextInt(name.length - 0) + 0;

    private void DialogCall() {
        final Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialog_call);
        dialog.show();

        dialog.setCanceledOnTouchOutside(false);

        TextView mCauTraLoi = dialog.findViewById(R.id.CauTraLoi);
        Button mCamOn = dialog.findViewById(R.id.btn_Thank);

        mCamOn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });


        mCauTraLoi.setText(name[index]);



        if(name[index] == "a")
        {
            mCauTraLoi.setTextColor(getResources().getColor(android.R.color.holo_orange_light));
        }
        if(name[index] == "b")
        {
            mCauTraLoi.setTextColor(getResources().getColor(android.R.color.holo_blue_light));
        }
        if(name[index] == "c")
        {
            mCauTraLoi.setTextColor(getResources().getColor(android.R.color.holo_green_light));
        }
        if(name[index] == "d")
        {
            mCauTraLoi.setTextColor(getResources().getColor(android.R.color.holo_red_light));
        }

    }

    private ArrayList<BarEntry> dataValues1(){
        ArrayList<BarEntry> dataVals = new ArrayList<>();
        dataVals.add(new BarEntry(0,a));
        dataVals.add(new BarEntry(1,b));
        dataVals.add(new BarEntry(2,c));
        dataVals.add(new BarEntry(3,d));
        return dataVals;
    }

    public void Correct()
    {
        mCountDownTimer.cancel();
        mTimerRunning = false;
        mTimeLeftInMillis = START_TIME_IN_MILLIS;
        new SweetAlertDialog(CauHoi.this, SweetAlertDialog.SUCCESS_TYPE)
                .setTitleText("Bạn đã trả lời chính xác")
                .setConfirmText("tiếp tục")
                .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sweetAlertDialog) {
                        sweetAlertDialog.dismissWithAnimation();
                        Diem = Diem + 200;
                        Cau = Cau + 1;
                        Intent intent = new Intent (CauHoi.this,ChonLinhVuc.class);
                        intent.putExtra("Diem", Diem);
                        intent.putExtra("Life", Life);
                        intent.putExtra("Cau", Cau);
                        startActivity(intent);
                        overridePendingTransition(0, 0);
                    }
                })
                .show();
    }

    public void inCorrect()
    {
        final String NguoiChoi = sharedPreferences.getString("id", "");
        if(Life == 1)
        {
            Life = 0;
            txtLife.setText(String.valueOf(Life));
            mCountDownTimer.cancel();
            mTimerRunning = false;
            mTimeLeftInMillis = START_TIME_IN_MILLIS;
            new SweetAlertDialog(CauHoi.this, SweetAlertDialog.ERROR_TYPE)
                    .setTitleText("Bạn đã thua, chúc bạn may mắn lần sau")
                    .setConfirmText("tiếp tục")
                    .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                        @Override
                        public void onClick(SweetAlertDialog sweetAlertDialog) {
                            sweetAlertDialog.dismissWithAnimation();
                            new UpdateDiem(){
                                @Override
                                protected void onPostExecute(String s) {
                                    try {
                                        JSONObject json = new JSONObject(s);
                                        boolean success = json.getBoolean("success");
                                        if (success) {
                                            new SweetAlertDialog(CauHoi.this, SweetAlertDialog.SUCCESS_TYPE)
                                                    .setTitleText("Chúc mừng bạn đã đạt được kỉ lục mới")
                                                    .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                                        @Override
                                                        public void onClick(SweetAlertDialog sweetAlertDialog) {
                                                            sweetAlertDialog.dismissWithAnimation();
                                                            Intent intent = new Intent (CauHoi.this,TrangChu.class);
                                                            startActivity(intent);
                                                            overridePendingTransition(R.anim.slide_right,R.anim.slide_out_left);
                                                        }
                                                    })
                                                    .show();
                                        } else {
                                            Intent intent = new Intent (CauHoi.this,TrangChu.class);
                                            startActivity(intent);
                                            overridePendingTransition(R.anim.slide_right,R.anim.slide_out_left);
                                        }
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                }
                            }.execute(NguoiChoi,String.valueOf(Diem));
                        }
                    })
                    .show();
        } else {
            mCountDownTimer.cancel();
            mTimerRunning = false;
            mTimeLeftInMillis = START_TIME_IN_MILLIS;
            new SweetAlertDialog(CauHoi.this, SweetAlertDialog.ERROR_TYPE)
                    .setTitleText("Bạn đã trả lời sai")
                    .setConfirmText("tiếp tục")
                    .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                        @Override
                        public void onClick(SweetAlertDialog sweetAlertDialog) {
                            sweetAlertDialog.dismissWithAnimation();
                            Cau = Cau + 1;
                            Life = Life - 1;
                            Intent intent = new Intent(CauHoi.this, ChonLinhVuc.class);
                            intent.putExtra("Diem", Diem);
                            intent.putExtra("Life", Life);
                            intent.putExtra("Cau", Cau);
                            startActivity(intent);
                            overridePendingTransition(0, 0);
                        }
                    })
                    .show();
        }
    }

    public void TraLoiA(View view) {
        if (DapAn == 1) {
            view.setBackgroundResource(R.drawable.buttonstyle4);
            Correct();
        }
        if (DapAn == 2)
            btn2.setBackgroundResource(R.drawable.buttonstyle4);
        if (DapAn == 3)
            btn3.setBackgroundResource(R.drawable.buttonstyle4);
        if (DapAn == 4)
            btn4.setBackgroundResource(R.drawable.buttonstyle4);
        if (DapAn != 1) {
            view.setBackgroundResource(R.drawable.buttonstyle5);
            inCorrect();
        }

    }
    public void TraLoiB(View view) {
        if (DapAn == 2) {
            view.setBackgroundResource(R.drawable.buttonstyle4);
            Correct();
        }
        if (DapAn == 1)
            btn1.setBackgroundResource(R.drawable.buttonstyle4);
        if (DapAn == 3)
            btn3.setBackgroundResource(R.drawable.buttonstyle4);
        if (DapAn == 4)
            btn4.setBackgroundResource(R.drawable.buttonstyle4);
        if (DapAn != 2){
            view.setBackgroundResource(R.drawable.buttonstyle5);
            inCorrect();
        }

    }
    public void TraLoiC(View view) {
        if (DapAn == 3) {
            view.setBackgroundResource(R.drawable.buttonstyle4);
            Correct();
        }
        if (DapAn == 1)
            btn1.setBackgroundResource(R.drawable.buttonstyle4);
        if (DapAn == 2)
            btn2.setBackgroundResource(R.drawable.buttonstyle4);
        if (DapAn == 4)
            btn4.setBackgroundResource(R.drawable.buttonstyle4);
        if (DapAn != 3){
            view.setBackgroundResource(R.drawable.buttonstyle5);
            inCorrect();
        }
    }
    public void TraLoiD(View view) {
        if (DapAn == 4) {
            view.setBackgroundResource(R.drawable.buttonstyle4);
            Correct();
        }
        if (DapAn == 1)
            btn1.setBackgroundResource(R.drawable.buttonstyle4);
        if (DapAn == 2)
            btn2.setBackgroundResource(R.drawable.buttonstyle4);
        if (DapAn == 3)
            btn3.setBackgroundResource(R.drawable.buttonstyle4);
        if (DapAn != 4){
            view.setBackgroundResource(R.drawable.buttonstyle5);
            inCorrect();
        }

    }

    public void LoaiBo(final View view) {
        final String User = sharedPreferences.getString("ten_dang_nhap", "");
        final String Password = sharedPreferences.getString("password", "");
        final String NguoiChoi = sharedPreferences.getString("id", "");
        new SweetAlertDialog(CauHoi.this, SweetAlertDialog.WARNING_TYPE)
                .setTitleText("Thông báo")
                .setContentText("Việc loại bỏ đáp án sẽ tốn 1000 Credit bạn có chắc chứ ?")
                .setCancelButton("không", new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sweetAlertDialog) {
                        sweetAlertDialog.dismissWithAnimation();
                    }
                })
                .setConfirmText("có")
                .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sweetAlertDialog) {
                        new TruCredit(){
                            @Override
                            protected void onPostExecute(String s) {
                                try {
                                    JSONObject json = new JSONObject(s);
                                    boolean success = json.getBoolean("success");
                                    String msg = json.getString("msg");
                                    if (success) {
                                        new DangNhapLoader(){
                                            @Override
                                            protected void onPostExecute(String s) {
                                                try {
                                                    JSONObject json = new JSONObject(s);
                                                    String credit = json.getString("credit");
                                                    String token = json.getString("token");
                                                    new DangXuatLoader().execute(token) ;
                                                    editor.remove("credit");
                                                    editor.putString("credit", credit);
                                                    editor.commit();
                                                } catch (JSONException e) {
                                                    e.printStackTrace();
                                                }

                                            }
                                        }.execute(User,Password);
                                        new SweetAlertDialog(CauHoi.this, SweetAlertDialog.SUCCESS_TYPE)
                                                .setTitleText(msg)
                                                .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                                    @Override
                                                    public void onClick(SweetAlertDialog sweetAlertDialog) {
                                                        sweetAlertDialog.dismissWithAnimation();
                                                        String credit = sharedPreferences.getString("credit", "");
                                                        txtCredit = findViewById(R.id.TienNap);
                                                        txtCredit.setText(credit);
                                                        int i=0;
                                                        boolean A=false;
                                                        boolean B=false;
                                                        boolean C=false;
                                                        boolean D=false;
                                                        while(i<2) {
                                                            final int random = new Random().nextInt((4 - 1) + 1) + 1;
                                                            if (random == 1 && DapAn != 1 && A==false) {
                                                                btn1.setBackgroundResource(R.drawable.buttonstyle5);
                                                                A = true;
                                                                i++;
                                                            } else if (random == 2 && DapAn != 2 && B==false) {
                                                                btn2.setBackgroundResource(R.drawable.buttonstyle5);
                                                                B = true;
                                                                i++;
                                                            } else if (random == 3 && DapAn != 3 && C==false) {
                                                                btn3.setBackgroundResource(R.drawable.buttonstyle5);
                                                                C = true;
                                                                i++;
                                                            } else if (random == 4 && DapAn != 4 && D==false) {
                                                                btn4.setBackgroundResource(R.drawable.buttonstyle5);
                                                                D = true;
                                                                i++;
                                                            }
                                                            if(i==2)
                                                                view.setEnabled(false);
                                                        }
                                                    }
                                                })
                                                .show();
                                    } else {
                                        new SweetAlertDialog(CauHoi.this, SweetAlertDialog.ERROR_TYPE)
                                                .setTitleText(msg)
                                                .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                                    @Override
                                                    public void onClick(SweetAlertDialog sweetAlertDialog) {
                                                        sweetAlertDialog.dismissWithAnimation();

                                                    }
                                                })
                                                .show();
                                    }
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }

                            }
                        }.execute(NguoiChoi,"1000");
                        sweetAlertDialog.dismissWithAnimation();
                    }
                })
                .show();

    }
}
