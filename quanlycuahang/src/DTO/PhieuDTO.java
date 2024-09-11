package DTO;

import java.util.Date;
import java.util.Objects;

public class PhieuDTO {
    private Date thoiGian;
    private double tongTien;
    private int idNhanVien;
    public PhieuDTO(){}
    public PhieuDTO(Date thoiGian, double tongTien, int idNhanVien){
        this.thoiGian = thoiGian;
        this.tongTien = tongTien;
        this.idNhanVien = idNhanVien;
    }

    public void setThoiGian(Date thoiGian) {
        this.thoiGian = thoiGian;
    }

    public Date getThoiGian() {
        return thoiGian;
    }

    public void setTongTien(double tongTien) {
        this.tongTien = tongTien;
    }

    public double getTongTien() {
        return tongTien;
    }

    public void setIdNhanVien(int idNhanVien) {
        this.idNhanVien = idNhanVien;
    }

    public int getIdNhanVien() {
        return idNhanVien;
    }

    @Override
    public String toString() {
        return "PhieuDTO{" +
                "thoiGian=" + thoiGian +
                ", tongTien=" + tongTien +
                ", idNhanVien=" + idNhanVien +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PhieuDTO phieuDTO)) return false;
        return Double.compare(phieuDTO.getTongTien(), getTongTien()) == 0 && getIdNhanVien() == phieuDTO.getIdNhanVien() && Objects.equals(getThoiGian(), phieuDTO.getThoiGian());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getThoiGian(), getTongTien(), getIdNhanVien());
    }
}
