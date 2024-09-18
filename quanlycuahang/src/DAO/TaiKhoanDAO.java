package DAO;

import DTO.TaiKhoanDTO;
import config.JDBCUtil;
import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TaiKhoanDAO implements DAOInterface<TaiKhoanDTO> {
    public static TaiKhoanDAO getInstance() {
        return new TaiKhoanDAO();
    }

    @Override
    public int insert(TaiKhoanDTO taiKhoanDTO) {
        int ketQua = 0;
        try {
            Connection con = JDBCUtil.getConnection();
            String sql = "insert into taikhoan(userName, matKhau, trangThai, NHANVIEN_idNV) values(?, ?, 1, ?)";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, taiKhoanDTO.getUsername());
            ps.setString(2, taiKhoanDTO.getPassword());
            ps.setInt(3, taiKhoanDTO.getNHANVIEN_idNV());
            ketQua = ps.executeUpdate();
            System.out.println("Đã thực thi: " + sql);
            System.out.println("Đã thay đổi " + ketQua + " dòng");
            JDBCUtil.closeConnection(con);
        } catch (SQLException e) {
            Logger.getLogger(TaiKhoanDAO.class.getName()).log(Level.SEVERE, null, e);
        }
        return ketQua;
    }

    @Override
    public int update(TaiKhoanDTO taiKhoanDTO) {
        int kq = 0;
        try {
            Connection con = JDBCUtil.getConnection();
            String sql = "update taikhoan set userName = ?, matKhau = ?, trangThai = ? where NHANVIEN_idNV = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, taiKhoanDTO.getUsername());
            ps.setString(2, taiKhoanDTO.getPassword());
            ps.setInt(3, taiKhoanDTO.getTrangThai());
            ps.setInt(4, taiKhoanDTO.getNHANVIEN_idNV());
            kq = ps.executeUpdate();
            JDBCUtil.closeConnection(con);
        } catch (SQLException e) {
            Logger.getLogger(TaiKhoanDAO.class.getName()).log(Level.SEVERE, null, e);
        }
        return kq;
    }

    @Override
    public int delete(String idNV) {
        int kq = 0;
        try {
            Connection con = JDBCUtil.getConnection();
            String sql = "update taikhoan set trangThai = 0 where NHANVIEN_idNV = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, Integer.parseInt(idNV));
            kq = ps.executeUpdate();
            JDBCUtil.closeConnection(con);
        } catch (SQLException e) {
            Logger.getLogger(TaiKhoanDAO.class.getName()).log(Level.SEVERE, null, e);
        }
        return kq;
    }

    @Override
    public ArrayList<TaiKhoanDTO> selectAll() {
        ArrayList<TaiKhoanDTO> taiKhoanList = new ArrayList<>();
        try {
            Connection con = JDBCUtil.getConnection();
            String sql = "SELECT * FROM taikhoan where trangThai = 1";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                TaiKhoanDTO taiKhoan = new TaiKhoanDTO();
                taiKhoan.setUsername(rs.getString("userName"));
                taiKhoan.setPassword(rs.getString("matKhau"));
                taiKhoan.setTrangThai(rs.getInt("trangThai"));
                taiKhoan.setNHANVIEN_idNV(rs.getInt("NHANVIEN_idNV"));
                taiKhoanList.add(taiKhoan);
            }
            JDBCUtil.closeConnection(con);
        } catch (Exception e) {
            Logger.getLogger(TaiKhoanDAO.class.getName()).log(Level.SEVERE, null, e);
        }
        return taiKhoanList;
    }

    @Override
    public TaiKhoanDTO selectById(int t) {
        TaiKhoanDTO taiKhoanDTO = null;
        try {
            Connection con = JDBCUtil.getConnection();
            String sql = "select * from taikhoan where NHANVIEN_idNV = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, t);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                taiKhoanDTO = new TaiKhoanDTO();
                taiKhoanDTO.setUsername(rs.getString("userName"));
                taiKhoanDTO.setPassword(rs.getString("matKhau"));
                taiKhoanDTO.setTrangThai(rs.getInt("trangThai"));
                taiKhoanDTO.setNHANVIEN_idNV(rs.getInt("NHANVIEN_idNV"));
            }
            JDBCUtil.closeConnection(con);
        } catch (Exception e) {
            Logger.getLogger(TaiKhoanDAO.class.getName()).log(Level.SEVERE, null, e);
        }
        return taiKhoanDTO;
    }

    public ArrayList<TaiKhoanDTO> search(String keyword) {
        ArrayList<TaiKhoanDTO> taiKhoanList = new ArrayList<>();
        try {
            Connection con = JDBCUtil.getConnection();
            String sql = "SELECT * FROM taikhoan WHERE userName LIKE ?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, "%" + keyword + "%");
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                TaiKhoanDTO taiKhoanDTO = new TaiKhoanDTO(
                        rs.getString("userName"),
                        rs.getString("matKhau"),
                        rs.getInt("trangThai"),
                        rs.getInt("NHANVIEN_idNV")
                );
                taiKhoanList.add(taiKhoanDTO);
            }
            JDBCUtil.closeConnection(con);
        } catch (SQLException e) {
            Logger.getLogger(TaiKhoanDAO.class.getName()).log(Level.SEVERE, null, e);
        }
        return taiKhoanList;
    }

    @Override
    public ArrayList<TaiKhoanDTO> selectByCondition(String condition) {
        return null;
    }

    public int delete(int idNv) {
        int kq = 0;
        try {
            Connection con = JDBCUtil.getConnection();
            String sql = "update taikhoan set trangThai = 0 where NHANVIEN_idNV = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, idNv);
            kq = ps.executeUpdate();
            JDBCUtil.closeConnection(con);
        } catch (SQLException e) {
            Logger.getLogger(TaiKhoanDAO.class.getName()).log(Level.SEVERE, null, e);
        }
        return kq;
    }
}