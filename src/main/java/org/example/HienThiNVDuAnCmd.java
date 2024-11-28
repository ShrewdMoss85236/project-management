package org.example;

import java.util.HashMap;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

public class HienThiNVDuAnCmd implements Command {
    private final HashMap<String, NV> dsNV;

    public HienThiNVDuAnCmd(HashMap<String, NV> dsNV) {
        this.dsNV = dsNV;
    }

    @Override
    public void execute() {
        CompletableFuture.runAsync(() -> {
            List<NV> danhSachDuAn = dsNV.values().stream()
                .filter(nv -> "duan".equalsIgnoreCase(nv.getCachTra()))
                .collect(Collectors.toList());

            System.out.println("Danh sách nhân viên có cách trả lương là 'duan':");
            danhSachDuAn.forEach(System.out::println);

            System.out.println("Hiển thị nhân viên trả lương 'duan' đã hoàn thành.");
        });
    }
}
