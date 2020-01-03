package com.example.gametraloicauhoi_android;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.AsyncTaskLoader;
import androidx.loader.content.Loader;

import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.Profile;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class ManHinhChinh extends AppCompatActivity {

    Button btnplaygame,btnLichSuChoi,btnBangXepHang,btnMuaCredit,btnQuanLiTaiKhoan,btnDangXuat;
    boolean doubleBackToExitPressedOnce = false;
    private ImageView img;
    Context _context;
    private TextView tvPlayerName, txCredit;
    private Profile profile;
    final int LAY_THONG_TIN = 0,LAY_CH_APP = 1, LAY_CH_DIEM = 2, LAY_CH_TRO_GIUP = 3;
    public static int credit = 0;
    public static String tenNguoiDung = "";
    public static int ID = 0;
    private NguoiChoiAsync nguoiChoiAsync;
    //private static final String MAIN_URL = "http://10.0.3.2:8000";
    MediaPlayer mediaPlayer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_man_hinh_chinh);

        btnplaygame = (Button) findViewById(R.id.btnPlay_game);
        btnBangXepHang = (Button) findViewById(R.id.btnbangxephang);
        btnDangXuat = (Button) findViewById(R.id.btnDangXuat);
        btnMuaCredit = (Button) findViewById(R.id.btnMuaCredit);
        btnQuanLiTaiKhoan = (Button) findViewById(R.id.btnQLTaiKhoan);

        mediaPlayer = MediaPlayer.create(ManHinhChinh.this ,R.raw.nhac_man_hinh_chinh);
        mediaPlayer.setLooping(true);
        mediaPlayer.start();
        tvPlayerName = findViewById(R.id.soLanChoi);
        img = findViewById(R.id.imgMuaC2);
        _context = this;
        txCredit = findViewById(R.id.txtCreditCLV);
        nguoiChoiAsync = new NguoiChoiAsync(this,tvPlayerName,txCredit,img);
        CauHinhVaLuuTru.cauHinhDiemCauHoi = new ArrayList<>();
        CauHinhVaLuuTru.cauHinhTroGiup = new ArrayList<>();
        layCauHinhVaLuuTru();
//        SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss");
//        Date date = new Date(System.currentTimeMillis());
//        formatter.format(date);
//        long y = formatter.getCalendar().getTime().getTime();
//
//        //long y = date.getTime();
//        Log.d("TM",y+"");
        //Log.d("STATE","Create");

//        if(GoogleSignIn.getLastSignedInAccount(this) != null)
//            tvPlayerName.setText(GoogleSignIn.getLastSignedInAccount(this).getDisplayName());
//        else{
//            if(AccessToken.getCurrentAccessToken() != null && !AccessToken.getCurrentAccessToken().isExpired())
//                tvPlayerName.setText(Profile.getCurrentProfile().getName());
//        }


    }

    @Override
    public void onBackPressed() {
        if(doubleBackToExitPressedOnce){
            super.onBackPressed();

            return;
        }
        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "Nhấn lần nữa để thoát", Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBackToExitPressedOnce=false;
            }
        }, 3000);
    }
    public void HienThiQuanLyTaiKhoan(View view) {
        btnQuanLiTaiKhoan.startAnimation(AnimationUtils.loadAnimation(this,R.anim.fade_in));
        Intent intent = new Intent(this,QuanLyTaiKhoan.class);
        startActivity(intent);
    }

    public void HienThiMuaCredit(View view) {
        btnMuaCredit.startAnimation(AnimationUtils.loadAnimation(this,R.anim.fade_in));
        Intent intent = new Intent(this,MuaCredit.class);
        startActivity(intent);
    }

    public void HienThiBXH(View view) {
        btnBangXepHang.startAnimation(AnimationUtils.loadAnimation(this,R.anim.fade_in));
        Intent intent = new Intent(this,BangXepHang.class);
        startActivity(intent);
    }

    public void HienThiLichSuChoi(View view) {
        btnLichSuChoi.startAnimation(AnimationUtils.loadAnimation(this,R.anim.fade_in));
        Intent intent = new Intent(this,LichSuChoi.class);
        startActivity(intent);
    }

    public void HienThiChonLinhVuc(View view) {
        btnplaygame.startAnimation(AnimationUtils.loadAnimation(this,R.anim.fade_in));
        mediaPlayer.stop();
        Intent intent = new Intent(this,ChonLinhVuc.class);
        startActivity(intent);
    }

    //    private LoaderManager.LoaderCallbacks<Bitmap> loadAnh = new LoaderManager.LoaderCallbacks<Bitmap>() {
//        @NonNull
//        @Override
//        public Loader<Bitmap> onCreateLoader(int id, @Nullable Bundle args) {
//            return null;
//
//        }
//
//        @Override
//        public void onLoadFinished(@NonNull Loader<Bitmap> loader, Bitmap data) {
//
//        }
//
//        @Override
//        public void onLoaderReset(@NonNull Loader<Bitmap> loader) {
//
//        }
//    };
    LoaderManager.LoaderCallbacks<String> lay_ch_app = new LoaderManager.LoaderCallbacks<String>() {
        @NonNull
        @Override
        public Loader<String> onCreateLoader(int id, @Nullable Bundle args) {
            return new CauHinhAppLoader(_context);
        }

        @Override
        public void onLoadFinished(@NonNull Loader<String> loader, String data) {
            try {
                JSONObject jsonObject = new JSONObject(data);
                int id = jsonObject.getInt("id");
                int luotTraLoi = jsonObject.getInt("co_hoi_sai");
                int thoiGian = jsonObject.getInt("thoi_gian_tra_loi");
                CauHinhVaLuuTru.cauHinhApp = new CauHinhApp(id,luotTraLoi,thoiGian);
            }

            catch (Exception e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onLoaderReset(@NonNull Loader<String> loader) {

        }
    };

    public LoaderManager.LoaderCallbacks<String> lay_ch_tro_giup = new LoaderManager.LoaderCallbacks<String>() {
        @NonNull
        @Override
        public Loader<String> onCreateLoader(int id, @Nullable Bundle args) {
            return new CauHinhTroGiupLoader(_context);
        }

        @Override
        public void onLoadFinished(@NonNull Loader<String> loader, String data) {
            try {
                JSONObject jsonObject = new JSONObject(data);
                JSONArray items = jsonObject.getJSONArray("data");
                for(int i = 0;i<items.length();i++){
                    int id = items.getJSONObject(i).getInt("id");
                    int loaiTroGiup = items.getJSONObject(i).getInt("loai_tro_giup");
                    int thuTu = items.getJSONObject(i).getInt("thu_tu");
                    int credit = items.getJSONObject(i).getInt("credit");
                    CauHinhVaLuuTru.cauHinhTroGiup.add(new CauHinhTroGiup(id,loaiTroGiup,thuTu,
                            credit));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        }

        @Override
        public void onLoaderReset(@NonNull Loader<String> loader) {

        }
    };

    private LoaderManager.LoaderCallbacks<String> lay_ch_diem = new LoaderManager.LoaderCallbacks<String>() {
        @NonNull
        @Override
        public Loader<String> onCreateLoader(int id, @Nullable Bundle args) {
            return new CauHinhDiemLoader(_context);
        }

        @Override
        public void onLoadFinished(@NonNull Loader<String> loader, String data) {
            try {
                JSONObject jsonObject = new JSONObject(data);
                JSONArray items = jsonObject.getJSONArray("data");
                for(int i = 0;i<items.length();i++){
                    int id = items.getJSONObject(i).getInt("id");
                    int thutu = items.getJSONObject(i).getInt("thu_tu");
                    int diem = items.getJSONObject(i).getInt("diem");
                    CauHinhVaLuuTru.cauHinhDiemCauHoi.add(new CauHinhDiemCauHoi(id,thutu,diem));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onLoaderReset(@NonNull Loader<String> loader) {

        }
    };

    public void layCauHinhVaLuuTru(){
        getSupportLoaderManager().initLoader(LAY_THONG_TIN,null,nguoiChoiAsync.nguoiChoi);
        getSupportLoaderManager().initLoader(LAY_CH_APP,null,lay_ch_app);
        getSupportLoaderManager().initLoader(LAY_CH_DIEM,null,lay_ch_diem);
        getSupportLoaderManager().initLoader(LAY_CH_TRO_GIUP,null,lay_ch_tro_giup);

    }

    public void DangXuat(View view) {
        btnDangXuat.startAnimation(AnimationUtils.loadAnimation(this,R.anim.fade_in));
        nguoiChoiAsync.logOut();
//        if(AccessToken.getCurrentAccessToken() != null){
//            LoginManager.getInstance().logOut();
//            Intent intent = new Intent(this,MainActivity.class);
//            startActivity(intent);
//        }
//        else if(GoogleSignIn.getLastSignedInAccount(this) != null)
//        {
//            GoogleSignIn.getClient(this,GoogleSignInOptions.DEFAULT_GAMES_SIGN_IN).signOut();
//            Intent intent = new Intent(this,MainActivity.class);
//            startActivity(intent);
//        }
    }

}
class ThongTinNguoiChoiLoader extends AsyncTaskLoader<String>{

    public ThongTinNguoiChoiLoader(@NonNull Context context) {
        super(context);
    }

    @Nullable
    @Override
    public String loadInBackground() {
        return NetworkUtils.getJSONData("lay-thong-tin","GET",NguoiChoi.token);
    }

    @Override
    protected void onStartLoading() {
        super.onStartLoading();
        forceLoad();
    }
}
class CauHinhAppLoader extends AsyncTaskLoader<String>{

    public CauHinhAppLoader(@NonNull Context context) {
        super(context);
    }

    @Nullable
    @Override
    public String loadInBackground() {
        return NetworkUtils.getJSONData("cau-hinh-app","GET",NguoiChoi.token);
    }

    @Override
    protected void onStartLoading() {
        super.onStartLoading();
        forceLoad();
    }
}
class CauHinhTroGiupLoader extends  AsyncTaskLoader<String>{

    public CauHinhTroGiupLoader(@NonNull Context context) {
        super(context);
    }

    @Override
    protected void onStartLoading() {
        super.onStartLoading();
        forceLoad();
    }

    @Nullable
    @Override
    public String loadInBackground() {
        return NetworkUtils.getJSONData("cau-hinh-tro-giup","GET",NguoiChoi.token);
    }
}

class CauHinhDiemLoader extends AsyncTaskLoader<String>{


    public CauHinhDiemLoader(@NonNull Context context) {
        super(context);
    }

    @Nullable
    @Override
    public String loadInBackground() {
        return NetworkUtils.getJSONData("cau-hinh-diem","GET",NguoiChoi.token);
    }

    @Override
    protected void onStartLoading() {
        super.onStartLoading();
        forceLoad();
    }
}