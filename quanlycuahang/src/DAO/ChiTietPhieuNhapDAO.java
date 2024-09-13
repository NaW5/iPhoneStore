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

    // In ChiTietPhieuNhapDAO.java
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
                    // Insert into phieunhapkho if not exists
                    String insertPhieuNhapSql = "INSERT INTO phieunhapkho(idPhieuNhap) VALUES (?)";
                    PreparedStatement insertPhieuNhapPst = con.prepareStatement(insertPhieuNhapSql);
                    insertPhieuNhapPst.setInt(1, chiTiet.getIdPhieuNhap());
                    insertPhieuNhapPst.executeUpdate();
                }
                // Insert into ctphieunhapkho
                String sql = "INSERT INTO ctphieunhapkho(PHIEUNHAP_idPhieuNhap, SANPHAM_idSP, soLuong, donGia, thanhTien) VALUES (?,?,?,?,?)";
                PreparedStatement pst = con.prepareStatement(sql);
                pst.setInt(1, chiTiet.getIdPhieuNhap());
                pst.setInt(2, chiTiet.getIdSanPham());
                pst.setInt(3, chiTiet.getSoLuong());
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
                int soLuong = rs.getInt("soLuong");
                int donGia = rs.getInt("donGia");
                int thanhTien = rs.getInt("thanhTien");
                int idPhieuNhap = rs.getInt("PHIEUNHAP_idPhieuNhap");
                int idSanPham = rs.getInt("SANPHAM_idSP");
                ChiTietPhieuNhapDTO ctphieu = new ChiTietPhieuNhapDTO(idPhieuNhap, donGia, thanhTien, soLuong, idSanPham);
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
                int soLuong = rs.getInt("soLuong");
                float donGia = rs.getFloat("donGia");
                double thanhTien = rs.getDouble("thanhTien");
                int idSanPham = rs.getInt("SANPHAM_idSP");
                ChiTietPhieuNhapDTO ctphieu = new ChiTietPhieuNhapDTO(idPhieuNhap, donGia, thanhTien, soLuong, idSanPham);
                result.add(ctphieu);
            }
            JDBCUtil.closeConnection(con);
        } catch (SQLException ex) {
            Logger.getLogger(ChiTietPhieuNhapDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }



}
