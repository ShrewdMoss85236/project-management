package org.example;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Scanner;

public class NV {
    private String maNV;
    private String hoTen;
    private LocalDate ngayVaoLam;
    private double luongKhoiDiem;
    private String cachTra;

    public String getMaNV() {
        return maNV;
    }

    public String getHoTen() {
        return hoTen;
    }

    public LocalDate getNgayVaoLam() {
        return ngayVaoLam;
    }

    public String getCachTra() {
        return cachTra;
    }

    public double getLuongKhoiDiem() {
        return luongKhoiDiem;
    }


    @Override
    public String toString() {
        return "Mã NV: " + maNV +
               ", Tên: " + hoTen +
               ", Ngày vào làm: " + ngayVaoLam +
               ", Lương: " + luongKhoiDiem +
               ", Cách trả: " + cachTra;
    }

    public NV(String maNV, String hoTen, LocalDate ngayVaoLam, double luongKhoiDiem, String cachTra) {
        this.maNV = maNV;
        this.hoTen = hoTen;
        this.ngayVaoLam = ngayVaoLam;
        this.luongKhoiDiem = luongKhoiDiem;
        this.cachTra = cachTra;
    }

    public static HashMap<String, NV> nhapDanhSachNV() {
        HashMap<String, NV> dsNV = new HashMap<>();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            try {
                System.out.print("Nhập mã nhân viên (Nhập '#' để kết thúc): ");
                String maNV = scanner.nextLine().trim();

                if (maNV.equals("#")) {
                    break;
                }

                if (maNV.isEmpty()) {
                    throw new IllegalArgumentException("Mã nhân viên không được để trống!");
                }

                if (dsNV.containsKey(maNV)) {
                    throw new IllegalArgumentException("Mã nhân viên đã tồn tại!");
                }

                System.out.print("Nhập họ tên: ");
                String hoTen = scanner.nextLine().trim();

                if (hoTen.isEmpty()) {
                    throw new IllegalArgumentException("Họ tên không được để trống!");
                }

                System.out.print("Nhập ngày vào làm (dd/MM/yyyy): ");
                String ngayVaoLamStr = scanner.nextLine().trim();
                LocalDate ngayVaoLam = LocalDate.parse(
                    ngayVaoLamStr,
                    DateTimeFormatter.ofPattern("dd/MM/yyyy")
                );

                System.out.print("Nhập lương khởi điểm: ");
                double luongKhoiDiem = Double.parseDouble(scanner.nextLine().trim());

                if (luongKhoiDiem < 0) {
                    throw new IllegalArgumentException("Lương khởi điểm phải là số dương!");
                }

                System.out.print("Nhập cách trả lương (duan/ngay): ");
                String cachTra = scanner.nextLine().trim().toLowerCase();

                if (!cachTra.equals("duan") && !cachTra.equals("ngay")) {
                    throw new IllegalArgumentException("Cách trả lương chỉ được là 'duan' hoặc 'ngay'!");
                }

                NV nhanVien = new NV(maNV, hoTen, ngayVaoLam, luongKhoiDiem, cachTra);
                dsNV.put(maNV, nhanVien);

                System.out.println("Nhập nhân viên thành công!");

            } catch (Exception e) {
                System.out.println("Lỗi: " + e.getMessage());
            }
        }

        return dsNV;
    }

    public static void main(String[] args) {
        HashMap<String, NV> dsNV = nhapDanhSachNV();

        for (NV nv : dsNV.values()) {
            System.out.println("Mã NV: " + nv.maNV +
                               ", Tên: " + nv.hoTen +
                               ", Ngày vào làm: " + nv.ngayVaoLam +
                               ", Lương: " + nv.luongKhoiDiem +
                               ", Cách trả: " + nv.cachTra);
        }
    }
}