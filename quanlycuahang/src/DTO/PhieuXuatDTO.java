package DTO;

import java.util.Date;
import java.util.Objects;

public class PhieuXuatDTO extends PhieuDTO{
    private int idPhieuXuat;
    public PhieuXuatDTO(){}
    public PhieuXuatDTO(int idPhieuXuat, Date thoiGian, double tongTien, int idNhanVien){
        super(thoiGian, tongTien, idNhanVien);
        this.idPhieuXuat = idPhieuXuat;
    }

    public void setIdPhieuXuat(int idPhieuXuat) {
        this.idPhieuXuat = idPhieuXuat;
    }

    public int getIdPhieuXuat() {
        return idPhieuXuat;
    }

    @Override
    public String toString() {
        return "PhieuXuatDTO{" +
                "idPhieuXuat=" + idPhieuXuat +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PhieuXuatDTO that)) return false;
        if (!super.equals(o)) return false;
        return idPhieuXuat == that.idPhieuXuat;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), idPhieuXuat);
    }
}
