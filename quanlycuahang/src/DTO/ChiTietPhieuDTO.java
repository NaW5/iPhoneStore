package DTO;

import java.util.Objects;

public class ChiTietPhieuDTO {
    private int IMEI;
    private int idSanPham;
    public ChiTietPhieuDTO(){}
    public ChiTietPhieuDTO(int IMEI, int idSanPham){
        this.IMEI = IMEI;
        this.idSanPham = idSanPham;
    }

    public void setIMEI(int IMEI) {
        this.IMEI = IMEI;
    }

    public int getIMEI() {
        return IMEI;
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
                "IMEI=" + IMEI +
                ", idSanPham=" + idSanPham +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ChiTietPhieuDTO that)) return false;
        return IMEI == that.IMEI && idSanPham == that.idSanPham;
    }

    @Override
    public int hashCode() {
        return Objects.hash(IMEI, idSanPham);
    }
}
