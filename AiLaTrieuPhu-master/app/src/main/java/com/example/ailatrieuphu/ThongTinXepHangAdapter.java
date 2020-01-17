package com.example.ailatrieuphu;

import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;


import java.util.ArrayList;


public class ThongTinXepHangAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private final static int TYPE_VIEW_ITEM = 0;
    private final static int TYPE_VIEW_LOADING = 1;
    private final ArrayList<ThongTinXepHang> mListThongTinXepHang;
    private final LayoutInflater mInflater;


    public ThongTinXepHangAdapter(Context context, ArrayList<ThongTinXepHang> mListThongTinXepHang) {
        this.mListThongTinXepHang = mListThongTinXepHang;
        this.mInflater = LayoutInflater.from(context);
    }

    @Override
    public int getItemViewType(int position) {
        return mListThongTinXepHang.get(position) == null ? TYPE_VIEW_LOADING : TYPE_VIEW_ITEM;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if(viewType == TYPE_VIEW_ITEM) {
            View itemView = this.mInflater.inflate(R.layout.activity_thong_tin_xep_hang, parent, false);
            return new ThongTinXepHangViewHolder(itemView, this);
        } else if(viewType == TYPE_VIEW_LOADING) {
            View itemView = this.mInflater.inflate(R.layout.activity_loading_item, parent, false);
            return new LoadingViewHolder(itemView);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if(holder instanceof ThongTinXepHangViewHolder) {
            hienThiThongTin((ThongTinXepHangViewHolder)holder, position);
        } else if(holder instanceof LoadingViewHolder) {
            hienThiProgressBar((LoadingViewHolder) holder);
        }
    }

    @Override
    public int getItemCount() {
        return this.mListThongTinXepHang == null ? 0 : this.mListThongTinXepHang.size();
    }

    class ThongTinXepHangViewHolder extends  RecyclerView.ViewHolder {
        private final TextView tenTaiKhoan;
        private final TextView diemCaoNhat;
        private final TextView Ranking;
        private final ThongTinXepHangAdapter mAdapter;

        public ThongTinXepHangViewHolder(@NonNull View itemView, ThongTinXepHangAdapter mAdapter) {
            super(itemView);
            this.mAdapter = mAdapter;
            this.tenTaiKhoan = itemView.findViewById(R.id.lblTenTaiKhoan);
            this.diemCaoNhat = itemView.findViewById(R.id.lblDiem);
            this.Ranking = itemView.findViewById(R.id.profile7);
        }
    }

    class LoadingViewHolder extends  RecyclerView.ViewHolder {
        private final ProgressBar progressBar;

        public LoadingViewHolder(@NonNull View itemView) {
            super(itemView);
            this.progressBar = itemView.findViewById(R.id.progressBar);
        }
    }

    void hienThiThongTin(ThongTinXepHangViewHolder holder, int position) {

        int Rank = position + 1;
        if(Rank == 1) {
            holder.Ranking.setBackgroundResource(R.drawable.buttonstyle6);
        }
        else if(Rank == 2)
            holder.Ranking.setBackgroundResource(R.drawable.buttonstyle7);
        else if(Rank == 3)
            holder.Ranking.setBackgroundResource(R.drawable.buttonstyle8);
        else
            holder.Ranking.setBackgroundResource(R.drawable.buttonstyle2);
        ThongTinXepHang nguoiChoi = this.mListThongTinXepHang.get(position);
        holder.tenTaiKhoan.setText(nguoiChoi.getTenTaiKhoan());
        holder.diemCaoNhat.setText(Integer.toString(nguoiChoi.getDiemCaoNhat()));
        holder.Ranking.setText(Integer.toString(Rank));

    }

    void hienThiProgressBar(LoadingViewHolder holder) {

    }
}
