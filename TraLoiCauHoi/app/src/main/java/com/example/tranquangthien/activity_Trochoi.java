package com.example.tranquangthien;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class activity_Trochoi extends AppCompatActivity {
    private static final int CASEA = 0;
    private static final int CASEB = 1;
    private static final int CASEC = 2;
    private static final int CASED = 3;
    private TableRow tbImg_help;
    private Button btnBound1, btnBound2, btnBound3, btnBound4, btnBound5, btnBound6,
            btnBound7, btnBound8, btnBound9, btnBound10, btnBound11, btnBound12, btnBound13, btnBound14, btnBound15;
    private ImageView img_help_stop, img_change_question, img_help_5050, img_help_audience, img_help_call_phone;

    private TextView txt_player_question, mTxt_money;
    private Button btncaseA, btncaseB, btncaseC, btncaseD;

    private int trueCase = 0;
    private int index = 0;
    private Button listButtons[], liButtonAnser[];
    private List<Question> listQuestion = new ArrayList<>();
    private List<Question> listChangeQuestion = new ArrayList<>();

    private DatabaseManager dataBaseManager;
    private View rootView;

    private Dialog mDialog;

    private MyAsynTask myAsynTask;
    private boolean isRuning = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__trochoi);
        dataBaseManager = new DatabaseManager(new activity_Trochoi());
        dataBaseManager.get15Question(listQuestion);
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.activity__trochoi, container, false);
        findViewBys();
        init();
        defaultQuestion();
        setEvent();
        initAnimation();
        disableButton();
        return rootView;
    }

    private void findViewBys() {



        mTxt_money = (TextView) rootView.findViewById(R.id.txt_money);






        btncaseA = (Button) rootView.findViewById(R.id.btn_CauA);
        btncaseB = (Button) rootView.findViewById(R.id.btn_CauB);
        btncaseC = (Button) rootView.findViewById(R.id.btn_CauC);
        btncaseD = (Button) rootView.findViewById(R.id.btn_CauD);

        tbImg_help = (TableRow) rootView.findViewById(R.id.img_help);

        img_change_question = (ImageView) rootView.findViewById(R.id.img_change_question);
        img_help_5050 = (ImageView) rootView.findViewById(R.id.img_help_5050);
        img_help_audience = (ImageView) rootView.findViewById(R.id.img_help_audience);
        img_help_call_phone = (ImageView) rootView.findViewById(R.id.img_help_call_phone);
        img_help_stop = (ImageView) rootView.findViewById(R.id.img_help_stop);
    }

    private void init() {
        listButtons = new Button[]{btnBound15, btnBound14, btnBound13, btnBound12, btnBound11, btnBound10,
                btnBound9, btnBound8, btnBound7, btnBound6, btnBound5, btnBound4, btnBound3, btnBound2, btnBound1};

        liButtonAnser = new Button[]{btncaseA, btncaseB, btncaseC, btncaseD};
    }

    private void setEvent() {
        img_help_call_phone.setOnClickListener(this);
        img_help_audience.setOnClickListener(this);
        img_help_5050.setOnClickListener(this);
        img_change_question.setOnClickListener(this);
        img_help_stop.setOnClickListener(this);

        btncaseA.setOnClickListener(this);
        btncaseB.setOnClickListener(this);
        btncaseC.setOnClickListener(this);
        btncaseD.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.img_help_stop:
                initDialogEndGame();
                break;
            case R.id.img_change_question:
                img_change_question.setImageResource(R.drawable.icon_doicauhoi_x);
                img_change_question.setEnabled(false);
                changeQuestion(Integer.parseInt(btn_order_question.getText().toString()));
                break;
            case R.id.img_help_5050:
                img_help_5050.setImageResource(R.drawable.ic_5050_x);
                img_help_5050.setEnabled(false);
                help5050();
                break;
            case R.id.img_help_audience:
                img_help_audience.setImageResource(R.drawable.icon_doicauhoi_x);
                img_help_audience.setEnabled(false);
                MainActivity activity1 = (MainActivity) getActivity();
                new Dialog_Audience(activity1, trueCase).show();
                break;
            case R.id.img_help_call_phone:
                img_help_call_phone.setImageResource(R.drawable.icon_doicauhoi_x);
                img_help_call_phone.setEnabled(false);
                MainActivity activity = (MainActivity) getActivity();
                new Dialog_CallPhone(activity, trueCase).show();
                break;
            case R.id.btn_CauA:
                isRuning = false;
                disableButton();
                listButtons[index].setBackgroundResource(R.drawable.icon_doicauhoi_x);
                effectfButton(btncaseA, 0);
                break;
            case R.id.btn_CauB:
                isRuning = false;
                disableButton();
                listButtons[index].setBackgroundResource(R.drawable.icon_doicauhoi_x);
                effectfButton(btncaseB, 1);
                break;
            case R.id.btn_CauC:
                isRuning = false;
                disableButton();
                listButtons[index].setBackgroundResource(R.drawable.atp__activity_player_layout_play_answer_background_selected);
                effectfButton(btncaseC, 2);
                break;
            case R.id.btn_CauD:
                isRuning = false;
                disableButton();
                listButtons[index].setBackgroundResource(R.drawable.atp__activity_player_layout_play_answer_background_selected);
                effectfButton(btncaseD, 3);
                break;
            default:
                break;
        }
    }

    private void initAnimation() {
        lnMenu.setVisibility(View.VISIBLE);
        Animation animationMenu = AnimationUtils.loadAnimation(getActivity(), R.anim.menuleft);
        lnMenu.startAnimation(animationMenu);

        animationMenu.setAnimationListener(this);
    }

    private void defaultQuestion() {

        txt_player_question.setText(listQuestion.get(0).getQuestion());
        btn_order_question.setText(listQuestion.get(0).getLevel() + "");
        btncaseA.setText(listQuestion.get(0).getCaseA());
        btncaseB.setText(listQuestion.get(0).getCaseB());
        btncaseC.setText(listQuestion.get(0).getCaseC());
        btncaseD.setText(listQuestion.get(0).getCaseD());
        trueCase = listQuestion.get(0).getTrueCase();

        listButtons[0].setBackgroundResource(R.drawable.background_bounds);
    }

    private void nextQuestion() {
        if (index < 14) {
            index++;
            txt_player_question.setText(listQuestion.get(index).getQuestion());
            btn_order_question.setText(listQuestion.get(index).getLevel() + "");
            btncaseA.setText(listQuestion.get(index).getCaseA());
            btncaseB.setText(listQuestion.get(index).getCaseB());
            btncaseC.setText(listQuestion.get(index).getCaseC());
            btncaseD.setText(listQuestion.get(index).getCaseD());
            trueCase = listQuestion.get(index).getTrueCase();
            listButtons[index].setBackgroundResource(R.drawable.background_bounds);
        } else {
            return;
        }

    }

    @Override
    public void onAnimationStart(Animation animation) {

    }

    @Override
    public void onAnimationEnd(Animation animation) {

        lnQuestion.setVisibility(View.VISIBLE);
        Animation animation_question = AnimationUtils.loadAnimation(getActivity(), R.anim.animation_display_layout_question);
        lnQuestion.startAnimation(animation_question);
        enableButton();
        isRuning = true;
        mTxt_time.setText("30");
        myAsynTask = new MyAsynTask();
        myAsynTask.execute(Integer.parseInt(mTxt_time.getText().toString()));
    }

    @Override
    public void onAnimationRepeat(Animation animation) {

    }

    private void resetGame() {
        txt_player_question.setText("");
        btn_order_question.setText("");
        btncaseA.setText("");
        btncaseB.setText("");
        btncaseC.setText("");
        btncaseD.setText("");
        for (int i = 0; i < liButtonAnser.length; i++) {
            liButtonAnser[i].setBackgroundResource(R.drawable.atp__activity_player_layout_play_answer_background_normal);
        }
        enableButton();
    }

    private void effectfButton(final Button button, final int checkTrue) {

        new CountDownTimer(5000, 500) {
            int time = 0;

            @Override
            public void onTick(long millisUntilFinished) {
                time++;
                if (time < 5) {
                    button.setBackgroundResource(R.drawable.atp__button_select);
                } else {
                    if (time % 2 == 0) {
                        liButtonAnser[trueCase - 1].setBackgroundResource(R.drawable.ic_answer_question_true);

                    } else {
                        liButtonAnser[trueCase - 1].setBackgroundResource(R.drawable.atp__activity_player_layout_play_answer_background_normal);
                    }
                }
            }

            @Override
            public void onFinish() {
                liButtonAnser[trueCase - 1].setBackgroundResource(R.drawable.ic_answer_question_true);
                time = 0;
                checkCase(checkTrue);

            }

        }.start();
    }

    private void checkCase(int checkTrue) {
        if (this.trueCase - 1 == checkTrue) {
            lnQuestion.setVisibility(View.GONE);
            initAnimation();
            resetGame();
            nextQuestion();
            mTxt_money.setText(listButtons[index - 1].getText() + "");
        } else {
            initDialogEndGame();
        }

    }

    private void disableButton() {
        btncaseA.setEnabled(false);
        btncaseB.setEnabled(false);
        btncaseC.setEnabled(false);
        btncaseD.setEnabled(false);
        // img_help_stop.setEnabled(false);
//        img_change_question.setEnabled(false);
//        img_help_stop.setEnabled(false);
//        img_help_5050.setEnabled(false);
//        img_help_audience.setEnabled(false);
//        img_help_call_phone.setEnabled(false);
    }

    private void enableButton() {
        btncaseA.setEnabled(true);
        btncaseB.setEnabled(true);
        btncaseC.setEnabled(true);
        btncaseD.setEnabled(true);
//        img_help_stop.setEnabled(true);
//        img_change_question.setEnabled(true);
//        img_help_stop.setEnabled(true);
//        img_help_5050.setEnabled(true);
//        img_help_audience.setEnabled(true);
//        img_help_call_phone.setEnabled(true);
    }

    private void help5050() {
        int da = trueCase - 1;
        int answer = 0;
        int b[];
        Random rd = new Random();
        switch (da) {
            case CASEA:
                answer = rd.nextInt(3) + 1;
                break;
            case CASEB:
                b = new int[]{0, 2, 3};
                answer = b[rd.nextInt(3)];
                break;
            case CASEC:
                b = new int[]{0, 1, 3};
                answer = b[rd.nextInt(3)];
                break;
            case CASED:
                b = new int[]{0, 1, 2};
                answer = b[rd.nextInt(3)];
                break;
            default:
                break;
        }
        //an 2 dap an khac dap an dung va 1 dap an sai vua tim duoc o tren
        for (int i = 0; i < liButtonAnser.length; i++) {
            if (i != da && i != answer) {
                liButtonAnser[i].setText("");
            }
        }
    }

    private void changeQuestion(int index) {
        dataBaseManager.get15Question(listChangeQuestion);
        txt_player_question.setText(listChangeQuestion.get(index).getQuestion());
        btncaseA.setText(listChangeQuestion.get(index).getCaseA());
        btncaseB.setText(listChangeQuestion.get(index).getCaseB());
        btncaseC.setText(listChangeQuestion.get(index).getCaseC());
        btncaseD.setText(listChangeQuestion.get(index).getCaseD());
        trueCase = listChangeQuestion.get(index).getTrueCase();

    }

    private void initDialogEndGame() {
        TextView txtMoney;
        Button btnDong;
        mDialog = new Dialog(getActivity(), android.R.style.Theme_DeviceDefault_Dialog_NoActionBar_MinWidth);
        mDialog.setContentView(R.layout.dialog_endgame);
        mDialog.show();

        txtMoney = (TextView) mDialog.findViewById(R.id.txtMoney);
        btnDong = (Button) mDialog.findViewById(R.id.btn_ketThuc);
        if (index > 0) {
            txtMoney.setText(listButtons[index - 1].getText().toString());
        } else {
            txtMoney.setText("0");
        }
        mDialog.setCanceledOnTouchOutside(false);
        mDialog.setCancelable(false);

        btnDong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDialog.dismiss();
                ((MainActivity) getActivity()).showMenu_Fragment();
                for (int i = 0; i < liButtonAnser.length; i++) {
                    liButtonAnser[i].setBackgroundResource(R.drawable.atp__activity_player_layout_play_answer_background_normal);
                }
                index = 0;
                enableButton();
            }
        });
    }

    private class MyAsynTask extends AsyncTask<Integer, Integer, Boolean> {

        @Override
        protected Boolean doInBackground(Integer... params) {
            int time = params[0];
            while (time >= 0) {
                try {
                    if (isRuning == true) {
                        Thread.sleep(1000);
                        publishProgress(time);
                        time--;
                    } else {
                        return true;
                    }


                } catch (InterruptedException e) {
                    e.printStackTrace();
                }


            }
            return false;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            Log.i("TAG", values[0] + "");
            mTxt_time.setText(values[0] + "");
        }

        @Override
        protected void onPostExecute(Boolean bool) {
            if (bool == false) {
                initDialogEndGame();
            }
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        index = 0;
    }
}
