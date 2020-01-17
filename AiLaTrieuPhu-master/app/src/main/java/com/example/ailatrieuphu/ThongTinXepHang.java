package com.example.ailatrieuphu;

public class ThongTinXepHang {
    private int id;
    private String tenTaiKhoan;
    private int diemCaoNhat;

    public ThongTinXepHang(int id, String ten, int diem) {
        this.id = id;
        this.tenTaiKhoan = ten;
        this.diemCaoNhat = diem;
    }

    public int getId() { return id; }

    public void setId(int id) { this.id = id; }

    public String getTenTaiKhoan() { return tenTaiKhoan; }

    public void setTenTaiKhoan(String tenTaiKhoan) { this.tenTaiKhoan = tenTaiKhoan; }

    public int getDiemCaoNhat() {return diemCaoNhat; }

    public void setDiemCaoNhat(int diemCaoNhat) { this.diemCaoNhat = diemCaoNhat; }
}
