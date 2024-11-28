package org.example;

interface QuanSat {
    void capNhat(String action, NV nhanVien);
}

class ThongBaoHR implements QuanSat {
    @Override
    public void capNhat(String action, NV nhanVien) {
        if ("themMoi".equals(action)) {
            System.out.println("Nhân viên mới đã được bổ sung vào hệ thống:");
            System.out.println("- Mã NV: " + nhanVien.getMaNV());
            System.out.println("- Họ tên: " + nhanVien.getHoTen());
            System.out.println("- Ngày vào làm: " + nhanVien.getNgayVaoLam());
        }
    }
}

class ThongBaoPM implements QuanSat {
    @Override
    public void capNhat(String action, NV nhanVien) {
        if ("themMoi".equals(action)) {
            System.out.println("Nhân viên mới đã được bổ sung vào hệ thống:");
            System.out.println("- Mã NV: " + nhanVien.getMaNV());
            System.out.println("- Họ tên: " + nhanVien.getHoTen());
            System.out.println("- Cách trả lương: " + nhanVien.getCachTra());
        }
    }
}


