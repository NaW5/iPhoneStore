package DTO;


import java.util.Date;
import java.util.Objects;

public class PhieuNhapDTO extends PhieuDTO {
    private int idPhieuNhap;
    public PhieuNhapDTO(){}
    public PhieuNhapDTO(int idphieuNhap, Date thoiGian, double tongTien, int idNhanVien){
        super(thoiGian, tongTien, idNhanVien);
        this.idPhieuNhap = idphieuNhap;
    }

    public void setIdPhieuNhap(int idPhieuNhap) {
        this.idPhieuNhap = idPhieuNhap;
    }

    public int getIdPhieuNhap() {
        return idPhieuNhap;
    }

    @Override
    public String toString() {
        return "PhieuNhapKhoDTO{" +
                "idPhieuNhap=" + idPhieuNhap +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PhieuNhapDTO that)) return false;
        if (!super.equals(o)) return false;
        return idPhieuNhap == that.idPhieuNhap;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), idPhieuNhap);
    }
}
