package org.example;

import java.util.HashMap;

public class TinhLuongNhanVien implements Runnable {
    private final HashMap<String, NV> dsNV;

    public TinhLuongNhanVien(HashMap<String, NV> dsNV) {
        this.dsNV = dsNV;
    }

    @Override
    public void run() {
        System.out.println("Bắt đầu tính lương cho nhân viên...");

        dsNV.values().forEach(nv -> {
            TinhLuong tinhLuongStrategy;

            if ("duan".equalsIgnoreCase(nv.getCachTra())) {
                tinhLuongStrategy = new TinhLuongDuAn();
            } else if ("ngay".equalsIgnoreCase(nv.getCachTra())) {
                tinhLuongStrategy = new TinhLuongNgay();
            } else {
                System.out.println("Không hỗ trợ cách trả lương: " + nv.getCachTra());
                return;
            }

            double luong = tinhLuongStrategy.tinhLuong(nv.getLuongKhoiDiem());

            System.out.println("Nhân viên: " + nv.getHoTen() + " | Lương: " + luong);
        });

        System.out.println("Tính lương hoàn thành!");
    }

    public static void main(String[] args) {
        QLNV qlnv = QLNV.getInstance();

        qlnv.nhapDanhSachNV();

        TinhLuongNhanVien tinhLuongNhanVien = new TinhLuongNhanVien(qlnv.getDsNV());
        Thread threadTinhLuong = new Thread(tinhLuongNhanVien);

        threadTinhLuong.start();
    }
}


