package org.example;

import java.util.HashMap;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

public class HienThiNVSapXepLuongCmd implements Command {
    private final HashMap<String, NV> dsNV;

    public HienThiNVSapXepLuongCmd(HashMap<String, NV> dsNV) {
        this.dsNV = dsNV;
    }

    @Override
    public void execute() {
        CompletableFuture.runAsync(() -> {
            List<NV> danhSachSapXep = dsNV.values().stream()
                .sorted((nv1, nv2) -> Double.compare(nv1.getLuongKhoiDiem(), nv2.getLuongKhoiDiem()))
                .collect(Collectors.toList());

            System.out.println("Danh sách nhân viên sắp xếp theo lương khởi điểm:");
            danhSachSapXep.forEach(System.out::println);

            System.out.println("Hiển thị danh sách sắp xếp lương đã hoàn thành.");
        });
    }
}
