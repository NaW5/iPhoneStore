package DTO;

import java.util.Objects;

public class ChiTietPhieuNhapDTO extends ChiTietPhieuDTO{
    private int idPhieuNhap;
    private float donGia;
    private double thanhTien;
    public ChiTietPhieuNhapDTO(){}
    public ChiTietPhieuNhapDTO(int idPhieuNhap, float donGia, double thanhTien, int soLuong, int idSanPham){
        super(soLuong, idSanPham);
        this.idPhieuNhap = idPhieuNhap;
        this.donGia = donGia;
        this.thanhTien = thanhTien;
    }

    public void setIdPhieuNhap(int idPhieuNhap) {
        this.idPhieuNhap = idPhieuNhap;
    }

    public int getIdPhieuNhap() {
        return idPhieuNhap;
    }

    public void setDonGia(float donGia) {
        this.donGia = donGia;
    }

    public float getDonGia() {
        return donGia;
    }

    public void setThanhTien(double thanhTien) {
        this.thanhTien = thanhTien;
    }

    public double getThanhTien() {
        return thanhTien;
    }

    @Override
    public String toString() {
        return "ChiTietPhieuNhapDTO{" +
                "idPhieuNhap=" + idPhieuNhap +
                ", donGia=" + donGia +
                ", thanhTien=" + thanhTien +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ChiTietPhieuNhapDTO that)) return false;
        if (!super.equals(o)) return false;
        return idPhieuNhap == that.idPhieuNhap && Float.compare(that.donGia, donGia) == 0 && Double.compare(that.thanhTien, thanhTien) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), idPhieuNhap, donGia, thanhTien);
    }
}
