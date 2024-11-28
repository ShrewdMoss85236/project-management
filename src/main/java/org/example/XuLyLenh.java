package org.example;

import java.util.HashMap;
import java.util.Scanner;

public class XuLyLenh {
    private final Command hienThiNVDuAnCmd;
    private final Command hienThiNVSapXepLuongCmd;

    public XuLyLenh(Command hienThiNVDuAnCmd, Command hienThiNVSapXepLuongCmd) {
        this.hienThiNVDuAnCmd = hienThiNVDuAnCmd;
        this.hienThiNVSapXepLuongCmd = hienThiNVSapXepLuongCmd;
    }

    public void xuLyLenh(String lenh) {
        switch (lenh) {
            case "1":
                hienThiNVDuAnCmd.execute();
                break;
            case "2":
                hienThiNVSapXepLuongCmd.execute();
                break;
            default:
                System.out.println("Lệnh không hợp lệ. Bỏ qua.");
        }
    }

    public static void main(String[] args) {
        QLNV qlnv = QLNV.getInstance();

        qlnv.nhapDanhSachNV();

        Command hienThiDuAn = new HienThiNVDuAnCmd(qlnv.getDsNV());
        Command hienThiSapXepLuong = new HienThiNVSapXepLuongCmd(qlnv.getDsNV());

        XuLyLenh dispatcher = new XuLyLenh(hienThiDuAn, hienThiSapXepLuong);

        Scanner scanner = new Scanner(System.in);
        String lenh;

        System.out.println("Nhập lệnh:");
        System.out.println("1 - Hiển thị danh sách nhân viên trả lương 'duan'.");
        System.out.println("2 - Hiển thị danh sách nhân viên sắp xếp theo lương khởi điểm.");
        System.out.println("Khác - Thoát.");

        while (true) {
            System.out.print("Nhập lệnh: ");
            lenh = scanner.nextLine().trim();

            if ("exit".equalsIgnoreCase(lenh)) {
                System.out.println("Thoát chương trình.");
                break;
            }

            dispatcher.xuLyLenh(lenh);
        }
    }
}
