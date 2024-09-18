package DTO;

import java.util.Objects;

public class TaiKhoanDTO {
    private String username;
    private String password;
    private int trangThai;
    private int NHANVIEN_idNV;

    public TaiKhoanDTO() {

    }

    public TaiKhoanDTO(String username, String password, int trangThai, int nHANVIEN_idNV) {
        super();
        this.username = username;
        this.password = password;
        this.trangThai = trangThai;
        NHANVIEN_idNV = nHANVIEN_idNV;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(int trangThai) {
        this.trangThai = trangThai;
    }

    public int getNHANVIEN_idNV() {
        return NHANVIEN_idNV;
    }

    public void setNHANVIEN_idNV(int nHANVIEN_idNV) {
        NHANVIEN_idNV = nHANVIEN_idNV;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 19 * hash + this.NHANVIEN_idNV;
        hash = 19 * hash + Objects.hashCode(this.username);
        hash = 19 * hash + Objects.hashCode(this.password);
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
        if (this.trangThai != other.trangThai) {
            return false;
        }
        if (!Objects.equals(this.username, other.username)) {
            return false;
        }
        return Objects.equals(this.password, other.password);
    }

    @Override
    public String toString() {
        return "ID nhân viên: " + NHANVIEN_idNV + ", Tên đăng nhập: " + username + ", Mật khẩu: " + password + ", Trạng thái: " + trangThai;
    }
}