package DTO;

import java.util.Objects;
import java.util.Date;

public class NhanVienDTO {
    private int idNV;
    private String hoTen;
    private int gioiTinh;
    private Date ngaySinh;
    private int sdt;
    private int isDelete;


    public NhanVienDTO(int idNV, String hoTen, int gioiTinh, Date ngaySinh, int sdt, int isDelete) {
        this.idNV = idNV;
        this.hoTen = hoTen;
        this.gioiTinh = gioiTinh;
        this.ngaySinh = ngaySinh;
        this.sdt = sdt;
        this.isDelete = isDelete;
    }

    public NhanVienDTO() {

    }

    public void setIdNV(int idNV) {
        this.idNV = idNV;
    }

    public int getIdNV() {
        return idNV;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setGioiTinh(int gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    public int getGioiTinh() {
        return gioiTinh;
    }

    public void setNgaySinh(Date ngaySinh) {
        this.ngaySinh = ngaySinh;
    }

    public Date getNgaySinh() {
        return ngaySinh;
    }

    public void setSdt(int sdt) {
        this.sdt = sdt;
    }

    public int getSdt() {
        return sdt;
    }

    public void setIsDelete(int isDelete) {
        this.isDelete = isDelete;
    }
    public int getIsDelete() {
        return isDelete;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 17 * hash + this.idNV;
        hash = 17 * hash + Objects.hashCode(this.hoTen);
        hash = 17 * hash + Objects.hashCode(this.gioiTinh);
        hash = 17 * hash + Objects.hashCode(this.ngaySinh);

        return hash;
    }

    @Override
    public boolean equals(Object obj){
        if(this == obj){
            return true;
        }
        if(obj == null || getClass() != obj.getClass()){
            return false;
        }
        final NhanVienDTO other = (NhanVienDTO) obj;
        return true;
    }

    @Override
    public String toString() {
        return "NhanVienDTO{" + "idNV=" + idNV + ", hoTen=" + hoTen + ", gioiTinh=" + gioiTinh + ", ngaySinh=" + ngaySinh + ", sdt=" + sdt + ", isDelete=" + isDelete + '}';
    }

    //Trả về số lượng trường được khai báo trong lớp của đối tượng hiện tại - > giúp biết được số lượng cột
    public int getNumberOfFields() {
        return this.getClass().getDeclaredFields().length;
    }


}
