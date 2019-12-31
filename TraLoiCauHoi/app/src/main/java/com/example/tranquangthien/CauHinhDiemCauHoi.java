package com.example.tranquangthien;

public class CauHinhDiemCauHoi {

    private int id;
    private int thuTu;
    private int diem;

    public int getDiem() {
        return diem;
    }

    public void setDiem(int diem) {
        this.diem = diem;
    }

    public int getThuTu() {
        return thuTu;
    }

    public void setThuTu(int thuTu) {
        this.thuTu = thuTu;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public CauHinhDiemCauHoi(int id, int thutu, int diem){
        setDiem(diem);
        setId(id);
        setThuTu(thutu);
    }

}
