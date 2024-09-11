package DAO;

import DTO.KhuyenMaiDTO;
import config.JDBCUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class KhuyenMaiDAO implements DAOInterface<KhuyenMaiDTO>{
    public static KhuyenMaiDAO getInstance(){return new KhuyenMaiDAO();}
    @Override
    public int insert(KhuyenMaiDTO khuyenMai) {
        int ketQua = 0;
        try {
            Connection con = JDBCUtil.getConnection();
            String sql = "INSERT INTO khuyenmai (idKM, dieuKien, phanTram, ngayBatDau, ngayKetThuc) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, khuyenMai.getIdKM());
            pst.setString(2, khuyenMai.getDieuKien());
            pst.setFloat(3, khuyenMai.getPhanTram());
            pst.setDate(4, khuyenMai.getNgayBatDau());
            pst.setDate(5, khuyenMai.getNgayKetThuc());
            ketQua = pst.executeUpdate();
            System.out.println("Đã thực thi: " + sql);
            System.out.println("Đã thay đổi " + ketQua + " dòng");
            JDBCUtil.closeConnection(con);
        } catch (SQLException e) {
            Logger.getLogger(NhanVienDAO.class.getName()).log(Level.SEVERE, null, e);
        }
        return ketQua;
    }

    @Override
    public int update(KhuyenMaiDTO khuyenMai) {
        int ketQua = 0;
        try {
            Connection con = JDBCUtil.getConnection();
            String sql = "UPDATE khuyenmai SET dieuKien = ?, phanTram = ?, ngayBatDau = ?, ngayKetThuc = ? WHERE idKM = ?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(5, khuyenMai.getIdKM());
            pst.setString(1, khuyenMai.getDieuKien());
            pst.setFloat(2, khuyenMai.getPhanTram());
            pst.setDate(3, khuyenMai.getNgayBatDau());
            pst.setDate(4, khuyenMai.getNgayKetThuc());
            ketQua = pst.executeUpdate();
            System.out.println("Đã thực thi: " + sql);
            System.out.println("Đã thay đổi " + ketQua + " dòng");
            JDBCUtil.closeConnection(con);
        } catch (SQLException e) {
            Logger.getLogger(KhuyenMaiDAO.class.getName()).log(Level.SEVERE, null, e);
        }
        return ketQua;
    }

    @Override
    public int delete(String idKM) {
        int ketQua = 0;
        try {
            Connection con = JDBCUtil.getConnection();
            String sql = "DELETE FROM khuyenmai " + 
                    "WHERE idKM = ?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, idKM);
            ketQua = pst.executeUpdate();
            System.out.println("Đã thực thi: " + sql);
            System.out.println("Đã thay đổi " + ketQua + " dòng");
            JDBCUtil.closeConnection(con);
        } catch (SQLException e) {
            Logger.getLogger(KhuyenMaiDAO.class.getName()).log(Level.SEVERE, null, e);
        }
        return ketQua;
    }

    @Override
    public ArrayList<KhuyenMaiDTO> selectAll() {
        ArrayList<KhuyenMaiDTO> taikhoans = new ArrayList<>();
        try {
            Connection con = JDBCUtil.getConnection();
            String sql = "SELECT * FROM khuyenmai";
            PreparedStatement pst = con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                KhuyenMaiDTO taikhoan = new KhuyenMaiDTO(
                        rs.getInt("idKM"),
                        rs.getString("dieuKien"),
                        rs.getFloat("phanTram"),
                        rs.getDate("ngayBatDau"),
                        rs.getDate("ngayKetThuc")
                );
                taikhoans.add(taikhoan);
            }
            JDBCUtil.closeConnection(con);
        } catch (SQLException e) {
            Logger.getLogger(NhanVienDAO.class.getName()).log(Level.SEVERE, null, e);
        }
        return taikhoans;
    }

    @Override
    public ArrayList<KhuyenMaiDTO> selectByCondition(String condition) {
        ArrayList<KhuyenMaiDTO> taikhoans = new ArrayList<>();
        try {
            Connection con = JDBCUtil.getConnection();
            String sql = "SELECT * FROM khuyenmai WHERE " + condition;
            PreparedStatement pst = con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                KhuyenMaiDTO taikhoan = new KhuyenMaiDTO(
                		rs.getInt("idKM"),
                        rs.getString("dieuKien"),
                        rs.getFloat("phanTram"),
                        rs.getDate("ngayBatDau"),
                        rs.getDate("ngayKetThuc")
                );
                taikhoans.add(taikhoan);
            }
            JDBCUtil.closeConnection(con);
        } catch (SQLException e) {
            Logger.getLogger(NhanVienDAO.class.getName()).log(Level.SEVERE, null, e);
        }
        return taikhoans;
    }

    @Override
    public KhuyenMaiDTO selectById(int idKM) {
        KhuyenMaiDTO taikhoan = null;
        try {
            Connection con = JDBCUtil.getConnection();
            String sql = "SELECT * FROM khuyenmai WHERE phanTram = ?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, idKM);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                taikhoan = new KhuyenMaiDTO(
                		rs.getInt("idKM"),
                        rs.getString("dieuKien"),
                        rs.getFloat("phanTram"),
                        rs.getDate("ngayBatDau"),
                        rs.getDate("ngayKetThuc")
                );
            }
            JDBCUtil.closeConnection(con);
        } catch (SQLException e) {
            Logger.getLogger(NhanVienDAO.class.getName()).log(Level.SEVERE, null, e);
        }
        return taikhoan;
    }
    
    public KhuyenMaiDTO selectByIdKM(int idKM) {
        KhuyenMaiDTO taikhoan = null;
        try {
            Connection con = JDBCUtil.getConnection();
            String sql = "SELECT * FROM khuyenmai WHERE idKM = ?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, idKM);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                taikhoan = new KhuyenMaiDTO(
                		rs.getInt("idKM"),
                        rs.getString("dieuKien"),
                        rs.getFloat("phanTram"),
                        rs.getDate("ngayBatDau"),
                        rs.getDate("ngayKetThuc")
                );
            }
            JDBCUtil.closeConnection(con);
        } catch (SQLException e) {
            Logger.getLogger(NhanVienDAO.class.getName()).log(Level.SEVERE, null, e);
        }
        return taikhoan;
    }
}
