package org.example;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class QLNV {
    private static QLNV instance;

    private List<QuanSat> danhSachQuanSat;

    private HashMap<String, NV> dsNV;

    private QLNV() {
        dsNV = new HashMap<>();
        danhSachQuanSat = new ArrayList<>();
    }

    public static QLNV getInstance() {
        if (instance == null) {
            instance = new QLNV();
        }
        return instance;
    }

    public void themNhanVien(NV nv) {
    if (!dsNV.containsKey(nv.getMaNV())) {
        dsNV.put(nv.getMaNV(), nv);

        for (QuanSat quanSat : danhSachQuanSat) {
            if ("duan".equals(nv.getCachTra()) && quanSat instanceof ThongBaoPM) {
                quanSat.capNhat("themMoi", nv);
            }

            if ("ngay".equals(nv.getCachTra()) && quanSat instanceof ThongBaoHR) {
                quanSat.capNhat("themMoi", nv);
            }
        }

        System.out.println("Nhân viên mới đã được thêm thành công: " + nv.getMaNV());
    } else {
        System.out.println("Lỗi: Nhân viên với mã " + nv.getMaNV() + " đã tồn tại!");
    }
}


    public HashMap<String, NV> getDsNV() {
        return dsNV;
    }

    public void inDanhSachNhanVien() {
        System.out.println("Danh sách nhân viên:");
        for (NV nv : dsNV.values()) {
            System.out.println(nv);
        }
    }

    public void nhapDanhSachNV() {
        this.dsNV = NV.nhapDanhSachNV();
    }

    public void themQuanSat(QuanSat quanSat) {
        danhSachQuanSat.add(quanSat);
    }

    public static void main(String[] args) {
        QLNV qlnv = QLNV.getInstance();

        qlnv.nhapDanhSachNV();

        qlnv.inDanhSachNhanVien();

        QuanSat thongBaoHR = new ThongBaoHR();
        QuanSat thongBaoPM = new ThongBaoPM();

        qlnv.themQuanSat(thongBaoHR);
        qlnv.themQuanSat(thongBaoPM);

        NV nv1 = new NV("NV001", "ダークロード", LocalDate.of(2025, 10, 15), 5000000, "ngay");
        qlnv.themNhanVien(nv1);

        NV nv2 = new NV("NV002", "保護者", LocalDate.of(2025, 2, 22), 6000000, "duan");
        qlnv.themNhanVien(nv2);


    }
}