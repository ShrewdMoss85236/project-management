package org.example;

import java.time.LocalDate;
import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public class AsyncNV {

    public static CompletableFuture<List<NV>> sapXepTheoLuongKhoiDiem(HashMap<String, NV> dsNV) {
        return CompletableFuture.supplyAsync(() -> {
            List<NV> danhSach = dsNV.values().stream()
                .sorted(Comparator.comparingDouble(NV::getLuongKhoiDiem))
                .collect(Collectors.toList());

            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            System.out.println("Hàm sapXepTheoLuongKhoiDiem hoàn thành.");
            return danhSach;
        });
    }

    public static CompletableFuture<Void> hienThiDanhSachNhanVien(List<NV> danhSach) {
        return CompletableFuture.runAsync(() -> {
            System.out.println("Danh sách nhân viên:");
            danhSach.forEach(System.out::println);

            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            System.out.println("Hàm hienThiDanhSachNhanVien hoàn thành.");
        });
    }

    public static void main(String[] args) {
        HashMap<String, NV> dsNV = new HashMap<>();
        dsNV.put("NV001", new NV("NV001", "保護者", LocalDate.of(2025, 1, 14), 5000000, "duan"));
        dsNV.put("NV002", new NV("NV002", "ダークロード", LocalDate.of(2025, 4, 18), 4000000, "ngay"));
        dsNV.put("NV003", new NV("NV003", "執行者", LocalDate.of(2025, 7, 21), 6000000, "duan"));

        sapXepTheoLuongKhoiDiem(dsNV)
            .thenApply(danhSach -> {
                System.out.println("Danh sách sau khi sắp xếp theo lương khởi điểm:");
                return danhSach;
            })
            .thenCompose(AsyncNV::hienThiDanhSachNhanVien)
            .thenRun(() -> System.out.println("Quá trình xử lý hoàn thành."))
            .exceptionally(e -> {
                System.out.println("Lỗi xảy ra: " + e.getMessage());
                return null;
            })
            .join();
    }
}
