package DTO;

import java.util.Objects;

public class ChiTietPhieuDTO {
    private int soLuong;
    private int idSanPham;
    public ChiTietPhieuDTO(){}
    public ChiTietPhieuDTO(int soLuong, int idSanPham){
        this.soLuong = soLuong;
        this.idSanPham = idSanPham;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setIdSanPham(int idSanPham) {
        this.idSanPham = idSanPham;
    }

    public int getIdSanPham() {
        return idSanPham;
    }

    @Override
    public String toString() {
        return "ChiTietPhieu{" +
                "soLuong=" + soLuong +
                ", idSanPham=" + idSanPham +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ChiTietPhieuDTO that)) return false;
        return soLuong == that.soLuong && idSanPham == that.idSanPham;
    }

    @Override
    public int hashCode() {
        return Objects.hash(soLuong, idSanPham);
    }
}
