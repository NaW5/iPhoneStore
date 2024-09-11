package DTO;

import java.util.Objects;

public class KhachHangDTO {
    private int idKhachHang;
    private String tenKhachHang;
    private String diaChiKhachHang;
    private int sdtKhachHang;
    private int isDelete;
    public KhachHangDTO(){}
    public KhachHangDTO(int idKhachHang, String tenKhachHang, String diaChiKhachHang, int sdtKhachHang,int isDelete){
        this.idKhachHang = idKhachHang;
        this.tenKhachHang = tenKhachHang;
        this.diaChiKhachHang = diaChiKhachHang;
        this.sdtKhachHang = sdtKhachHang;
        this.isDelete = isDelete;
    }

    public void setIdKhachHang(int idKhachHang) {
        this.idKhachHang = idKhachHang;
    }

    public int getIdKhachHang() {
        return idKhachHang;
    }

    public void setTenKhachHang(String tenKhachHang) {
        this.tenKhachHang = tenKhachHang;
    }

    public String getTenKhachHang() {
        return tenKhachHang;
    }

    public void setDiaChiKhachHang(String diaChiKhachHang) {
        this.diaChiKhachHang = diaChiKhachHang;
    }

    public String getDiaChiKhachHang() {
        return diaChiKhachHang;
    }

    public void setSdtKhachHang(int sdtKhachHang) {
        this.sdtKhachHang = sdtKhachHang;
    }

    public int getSdtKhachHang() {
        return sdtKhachHang;
    }

    public void setIsDelete(int isDelete) {
        this.isDelete = isDelete;
    }

    public int getIsDelete() {
        return isDelete;
    }

    @Override
    public String toString() {
        return "KhachHangDTO{" +
                "idKhachHang=" + idKhachHang +
                ", tenKhachHang='" + tenKhachHang + '\'' +
                ", diaChiKhachHang='" + diaChiKhachHang + '\'' +
                ", sdtKhachHang=" + sdtKhachHang +
                ", isDelete=" + isDelete +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof KhachHangDTO that)) return false;
        return getIdKhachHang() == that.getIdKhachHang() && getSdtKhachHang() == that.getSdtKhachHang() && getIsDelete() == that.getIsDelete() && Objects.equals(getTenKhachHang(), that.getTenKhachHang()) && Objects.equals(getDiaChiKhachHang(), that.getDiaChiKhachHang());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getIdKhachHang(), getTenKhachHang(), getDiaChiKhachHang(), getSdtKhachHang(), getIsDelete());
    }
}
