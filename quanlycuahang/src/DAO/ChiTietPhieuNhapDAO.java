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
        for (int i = 0; i < t.size(); i++) {
            try {
                Connection con =  JDBCUtil.getConnection();
                String sql = "INSERT INTO ctphieunhapkho(soLuong, donGia, thanhTien, PHIEUNHAP_idPhieuNhap, SANPHAM_idSP) VALUES (?,?,?,?,?)";
                PreparedStatement pst = con.prepareStatement(sql);
                pst.setInt(1, t.get(i).getSoLuong());
                pst.setFloat(2, t.get(i).getDonGia());
                pst.setDouble(3, t.get(i).getThanhTien());
                pst.setInt(4, t.get(i).getIdPhieuNhap());
                pst.setInt(5, t.get(i).getIdSanPham());
                ketQua = pst.executeUpdate();
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
