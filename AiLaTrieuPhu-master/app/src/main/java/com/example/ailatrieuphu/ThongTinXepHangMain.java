package com.example.ailatrieuphu;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.Loader;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ThongTinXepHangMain extends AppCompatActivity implements LoaderManager.LoaderCallbacks<String> {

    private final static ArrayList<ThongTinXepHang> mListThongTinXepHang = new ArrayList<>();
    private ThongTinXepHangAdapter thongTinXepHangAdapter;
    private RecyclerView mRecyclerView;

    private static final int PAGE_SIZE = 25;
    private int currentPage = 1;
    private boolean isLastPage = false;
    private int totalPage;
    private boolean isLoading = false;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thong_tin_xep_hang_main);
        sharedPreferences = getSharedPreferences("com.example.ailatrieuphu", MODE_PRIVATE);
        editor = sharedPreferences.edit();
        String token = sharedPreferences.getString("TOKEN", "");
        Log.d("TOKEN", token);
        if (token == "") {
            Intent intent = new Intent(this, TrangChu.class);
            startActivity(intent);
        }

        this.mRecyclerView = findViewById(R.id.rcv_xep_hang);
        this.thongTinXepHangAdapter = new ThongTinXepHangAdapter(this, mListThongTinXepHang);
        this.mRecyclerView.setAdapter(thongTinXepHangAdapter);
        this.mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        loadData(null);

        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                LinearLayoutManager layoutManager = (LinearLayoutManager)mRecyclerView.getLayoutManager();
                int visibleItemCount = layoutManager.getChildCount();
                int totalItemCount = layoutManager.getItemCount();
                int firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition();

                if(!isLoading && !isLastPage) {
                    if((visibleItemCount + firstVisibleItemPosition) >= totalItemCount
                    && firstVisibleItemPosition >= 0
                    && totalItemCount >=PAGE_SIZE) {
                        isLoading = true;
                        currentPage++;
                        mListThongTinXepHang.add(null);
                        thongTinXepHangAdapter.notifyItemInserted(mListThongTinXepHang.size() - 1);

                        Bundle data = new Bundle();
                        data.putInt("page", currentPage);
                        data.putInt("limit", PAGE_SIZE);
                        loadData(data);
                    }
                }
            }
        });
    }

    private void loadData(@NonNull Bundle data) {
        ConnectivityManager connMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = null;

        if(connMgr != null) {
            networkInfo = connMgr.getActiveNetworkInfo();
        }

        if(networkInfo !=null && networkInfo.isConnected()) {
            if(getSupportLoaderManager().getLoader(0) !=null) {
                getSupportLoaderManager().restartLoader(0,data, this);
            }

            getSupportLoaderManager().initLoader(0, data, this);
        } else {
            taoThongBao("Không thể kết nối internet").show();
        }
    }

    private AlertDialog taoThongBao(String s) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(s).setTitle("Lỗi");
        builder.setPositiveButton("Đồng ý", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        moveTaskToBack(true);
                        android.os.Process.killProcess(android.os.Process.myPid());
                        System.exit(1);
                    }
                });
            return builder.create();
    }

    @NonNull
    @Override
    public Loader<String> onCreateLoader(int id,@NonNull Bundle args) {
        int page = 1;
        int limit = 25;
        if (args != null) {
            page = args.getInt("page");
            limit = args.getInt("limit");
        }
        return new ThongTinXepHangLoader(this, page, limit);
    }

    @Override
    public void onLoadFinished(@NonNull Loader<String> loader, String data) {
        try {
            if(data == null) {
                taoThongBao("Không thể kết nối server").show();
                return;
            }
            JSONObject jsonObject = new JSONObject(data);
            int total = jsonObject.getInt("total");
            totalPage = total / PAGE_SIZE;
            if(total % PAGE_SIZE !=0) {
                totalPage++;
            }
            JSONArray itemArray = jsonObject.getJSONArray("data");
            if(mListThongTinXepHang.size() > 0) {
                mListThongTinXepHang.remove(mListThongTinXepHang.size() - 1);
                int scrollPosition = mListThongTinXepHang.size();
                thongTinXepHangAdapter.notifyItemRemoved(scrollPosition);
            }
            for(int i=0; i<itemArray.length(); i++) {
                int id = itemArray.getJSONObject(i).getInt("id");
                String tenTaiKhoan = itemArray.getJSONObject(i).getString("ten_dang_nhap");
                int diem = itemArray.getJSONObject(i).getInt("diem_cao_nhat");
                mListThongTinXepHang.add(new ThongTinXepHang(id, tenTaiKhoan,diem));
            }
            thongTinXepHangAdapter.notifyDataSetChanged();

            isLoading = false;
            isLastPage = (currentPage == totalPage);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onLoaderReset(@NonNull Loader loader) {

    }

    @Override
    public void finish(){
        mListThongTinXepHang.clear();
        super.finish();
        overridePendingTransition(R.anim.slide_left,R.anim.slide_out_right);
    }
}