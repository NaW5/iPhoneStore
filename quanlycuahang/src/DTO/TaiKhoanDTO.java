package DTO;
import java.util.Objects;
public class TaiKhoanDTO {
    private int NHANVIEN_idNV;
    private String tenDangNhap;
    private String matKhau;
    private String chucVu;
    private int trangThai;

    public TaiKhoanDTO() {

    }

    public TaiKhoanDTO(int NHANVIEN_idNV, String tenDangNhap, String matKhau, String chucVu, int trangThai) {
        this.NHANVIEN_idNV = NHANVIEN_idNV;
        this.tenDangNhap = tenDangNhap;
        this.matKhau = matKhau;
        this.chucVu = chucVu;
        this.trangThai = trangThai;
    }

    public void setNHANVIEN_idNV(int NHANVIEN_idNV) {
        this.NHANVIEN_idNV = NHANVIEN_idNV;
    }

    public int getNHANVIEN_idNV() {
        return NHANVIEN_idNV;
    }

    public void setTenDangNhap(String tenDangNhap) {
        this.tenDangNhap = tenDangNhap;
    }

    public String getTenDangNhap() {
        return tenDangNhap;
    }

    public void setMatKhau(String matKhau) {
        this.matKhau = matKhau;
    }

    public String getMatKhau() {
        return matKhau;
    }

    public void setChucVu(String chucVu) {
        this.chucVu = chucVu;
    }

    public String getChucVu() {
        return chucVu;
    }

    public void setTrangThai(int trangThai) {
        this.trangThai = trangThai;
    }

    public int getTrangThai() {
        return trangThai;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 19 * hash + this.NHANVIEN_idNV;
        hash = 19 * hash + Objects.hashCode(this.tenDangNhap);
        hash = 19 * hash + Objects.hashCode(this.matKhau);
        hash = 19 * hash + Objects.hashCode(this.chucVu);
        hash = 19 * hash + this.trangThai;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final TaiKhoanDTO other = (TaiKhoanDTO) obj;
        if (this.NHANVIEN_idNV != other.NHANVIEN_idNV) {
            return false;
        }
        if(this.chucVu != other.chucVu) {
            return false;
        }
        if (this.trangThai != other.trangThai) {
            return false;
        }
        if(!Objects.equals(this.tenDangNhap, other.tenDangNhap)) {
            return false;
        }
        return Objects.equals(this.matKhau, other.matKhau);
    }
    @Override
    public String toString() {
        return "ID nhân viên: " + NHANVIEN_idNV + ", Tên đăng nhập: " + tenDangNhap + ", Mật khẩu: " + matKhau + ", Chức vụ: " + chucVu + ", Trạng thái: " + trangThai;
    }
}
