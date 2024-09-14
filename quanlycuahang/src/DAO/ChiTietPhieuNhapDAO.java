// ChiTietPhieuNhapDAO.java
package DAO;

import DTO.ChiTietPhieuNhapDTO;
import config.JDBCUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ChiTietPhieuNhapDAO implements ChiTietInterface<ChiTietPhieuNhapDTO> {
    public static ChiTietPhieuNhapDAO getInstance(){
        return new ChiTietPhieuNhapDAO();
    }

    @Override
    public int insert(ArrayList<ChiTietPhieuNhapDTO> t) {
        int ketQua = 0;
        for (ChiTietPhieuNhapDTO chiTiet : t) {
            try {
                Connection con = JDBCUtil.getConnection();
                String checkSql = "SELECT COUNT(*) FROM phieunhapkho WHERE idPhieuNhap = ?";
                PreparedStatement checkPst = con.prepareStatement(checkSql);
                checkPst.setInt(1, chiTiet.getIdPhieuNhap());
                ResultSet rs = checkPst.executeQuery();
                if (rs.next() && rs.getInt(1) == 0) {
                    String insertPhieuNhapSql = "INSERT INTO phieunhapkho(idPhieuNhap) VALUES (?)";
                    PreparedStatement insertPhieuNhapPst = con.prepareStatement(insertPhieuNhapSql);
                    insertPhieuNhapPst.setInt(1, chiTiet.getIdPhieuNhap());
                    insertPhieuNhapPst.executeUpdate();
                }
                String sql = "INSERT INTO ctphieunhapkho(PHIEUNHAP_idPhieuNhap, SANPHAM_idSP, IMEI, donGia, thanhTien) VALUES (?,?,?,?,?)";
                PreparedStatement pst = con.prepareStatement(sql);
                pst.setInt(1, chiTiet.getIdPhieuNhap());
                pst.setInt(2, chiTiet.getIdSanPham());
                pst.setInt(3, chiTiet.getIMEI());
                pst.setFloat(4, chiTiet.getDonGia());
                pst.setDouble(5, chiTiet.getThanhTien());
                ketQua += pst.executeUpdate();
                JDBCUtil.closeConnection(con);
            } catch (SQLException ex) {
                Logger.getLogger(ChiTietPhieuNhapDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return ketQua;
    }

    @Override
    public int delete(String idPhieuNhap) {
        int ketQua= 0;
        try {
            Connection con =  JDBCUtil.getConnection();
            String sql = "DELETE FROM ctphieunhapkho WHERE PHIEUNHAP_idPhieuNhap = ?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, idPhieuNhap);
            ketQua = pst.executeUpdate();
            JDBCUtil.closeConnection(con);
        } catch (SQLException ex) {
            Logger.getLogger(ChiTietPhieuNhapDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ketQua;
    }

    @Override
    public int update(ArrayList<ChiTietPhieuNhapDTO> t, String pk) {
        int ketQua = this.delete(pk);
        if (ketQua != 0) {
            ketQua = this.insert(t);
        }
        return ketQua;
    }

    @Override
    public ArrayList<ChiTietPhieuNhapDTO> selectAll(String t) {
        ArrayList<ChiTietPhieuNhapDTO> result = new ArrayList<>();
        try {
            Connection con = JDBCUtil.getConnection();
            String sql = "SELECT * FROM ctphieunhapkho WHERE PHIEUNHAP_idPhieuNhap = ?";
            PreparedStatement pst =  con.prepareStatement(sql);
            pst.setString(1, t);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                int IMEI = rs.getInt("IMEI");
                float donGia = rs.getFloat("donGia");
                double thanhTien = rs.getDouble("thanhTien");
                int idPhieuNhap = rs.getInt("PHIEUNHAP_idPhieuNhap");
                int idSanPham = rs.getInt("SANPHAM_idSP");
                ChiTietPhieuNhapDTO ctphieu = new ChiTietPhieuNhapDTO(idPhieuNhap, donGia, thanhTien, IMEI, idSanPham);
                result.add(ctphieu);
            }
            JDBCUtil.closeConnection(con);
        } catch (SQLException e) {
            System.out.println(e);
        }
        return result;
    }

    @Override
    public ChiTietPhieuNhapDTO selectById(int t) {
        return null;
    }

    @Override
    public ArrayList<ChiTietPhieuNhapDTO> selectById_ctp(int idPhieuNhap) {
        ArrayList<ChiTietPhieuNhapDTO> result = new ArrayList<>();
        try {
            Connection con = JDBCUtil.getConnection();
            String sql = "SELECT * FROM ctphieunhapkho WHERE PHIEUNHAP_idPhieuNhap = ?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, idPhieuNhap);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                int IMEI = rs.getInt("IMEI");
                float donGia = rs.getFloat("donGia");
                double thanhTien = rs.getDouble("thanhTien");
                int idSanPham = rs.getInt("SANPHAM_idSP");
                ChiTietPhieuNhapDTO ctphieu = new ChiTietPhieuNhapDTO(idPhieuNhap, donGia, thanhTien, IMEI, idSanPham);
                result.add(ctphieu);
            }
            JDBCUtil.closeConnection(con);
        } catch (SQLException ex) {
            Logger.getLogger(ChiTietPhieuNhapDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    public int themChiTietPhieuNhap(int IMEI, float donGia, double thanhTien, int phieunhapIdPhieuNhap, int sanphamIdSP) {
        int ketQua = 0;
        try {
            Connection con = JDBCUtil.getConnection();
            String sql = "INSERT INTO ctphieunhapkho(IMEI, donGia, thanhTien, PHIEUNHAP_idPhieuNhap, SANPHAM_idSP) VALUES (?,?,?,?,?)";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, IMEI);
            pst.setFloat(2, donGia);
            pst.setDouble(3, thanhTien);
            pst.setInt(4, phieunhapIdPhieuNhap);
            pst.setInt(5, sanphamIdSP);
            ketQua = pst.executeUpdate();
            JDBCUtil.closeConnection(con);
        } catch (SQLException ex) {
            Logger.getLogger(ChiTietPhieuNhapDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ketQua;
    }

    public void xoaChiTietPhieuNhapByIMEI(int i) {
        try {
            Connection con = JDBCUtil.getConnection();
            String updateIMEISql = "UPDATE imei SET idPhieuNhap = NULL WHERE maIMEI = ?";
            PreparedStatement updateIMEIPst = con.prepareStatement(updateIMEISql);
            updateIMEIPst.setInt(1, i);
            updateIMEIPst.executeUpdate();

            String sql = "DELETE FROM ctphieunhapkho WHERE IMEI = ?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, i);
            pst.executeUpdate();
            JDBCUtil.closeConnection(con);
        } catch (SQLException ex) {
            Logger.getLogger(ChiTietPhieuNhapDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public boolean kiemTraTonTaiIMEI(int i) {
        try {
            Connection con = JDBCUtil.getConnection();
            String sql = "SELECT COUNT(*) FROM ctphieunhapkho WHERE IMEI = ?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, i);
            ResultSet rs = pst.executeQuery();
            if (rs.next() && rs.getInt(1) > 0) {
                return true;
            }
            JDBCUtil.closeConnection(con);
        } catch (SQLException ex) {
            Logger.getLogger(ChiTietPhieuNhapDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public void xoaChiTietPhieuNhapByidPhieuNhap(int i) {
        try {
            Connection con = JDBCUtil.getConnection();

            // Step 1: update idPhieuNhap in imei table to null
            String updateIMEISql = "UPDATE imei SET idPhieuNhap = NULL WHERE idPhieuNhap = ?";
            PreparedStatement updateIMEIPst = con.prepareStatement(updateIMEISql);
            updateIMEIPst.setInt(1, i);
            updateIMEIPst.executeUpdate();

            //step 2: delete from ctphieunhapkho
            String sql = "DELETE FROM ctphieunhapkho WHERE PHIEUNHAP_idPhieuNhap = ?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, i);
            pst.executeUpdate();

            JDBCUtil.closeConnection(con);
        } catch (SQLException ex) {
            Logger.getLogger(ChiTietPhieuNhapDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}