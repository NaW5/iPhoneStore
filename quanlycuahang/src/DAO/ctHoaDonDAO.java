package DAO;
import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import DTO.ctHoaDonDTO;
import config.JDBCUtil;

public class ctHoaDonDAO implements DAOInterface<ctHoaDonDTO> {

    public static ctHoaDonDAO getInstance() {
        return new ctHoaDonDAO();
    }

    @Override
    public int insert(ctHoaDonDTO t) {
        int ketQua = 0;
        try {
            Connection con = JDBCUtil.getConnection();
            String sql = "INSERT INTO ctHoaDon (soLuong, donGia, thanhTien, KHUYENMAI_idKM, PHIEUBAOHANH_idBaoHanh, SANPHAM_idSP, HOADON_idHoaDon) VALUES (?,?,?,?,?,?,?)";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, t.getSoLuong());
            pst.setFloat(2, t.getDonGia());
            pst.setDouble(3, t.getThanhTien());
            pst.setInt(4, t.getKHUYENMAI_idKM());
            pst.setInt(5, t.getPHIEUBAOHANH_idBaoHanh());
            pst.setInt(6, t.getSANPHAM_idSP());
            pst.setInt(7, t.getHOADON_idHoaDon());
            ketQua = pst.executeUpdate();
            System.out.println("Đã thực thi: " + sql);
            System.out.println("Đã thay đổi " + ketQua + " dòng");
            JDBCUtil.closeConnection(con);
        } catch (SQLException e) {
            Logger.getLogger(ctHoaDonDAO.class.getName()).log(Level.SEVERE, null, e);
        }
        return ketQua;
    }


    @Override
    public int update(ctHoaDonDTO t) {
        int ketQua = 0;
        try {
            Connection con = JDBCUtil.getConnection();
            String sql = "UPDATE ctHoaDon " +
                    "SET soLuong = ?, " +
                    "donGia = ?, " +
                    "thanhTien = ?, " +
                    "KHUYENMAI_idKM = ?, " +
                    "PHIEUBAOHANH_idBaoHanh = ?, " +
                    "SANPHAM_idSP = ? " +
                    "WHERE HOADON_idHoaDon = ?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, t.getSoLuong());
            pst.setFloat(2, t.getDonGia());
            pst.setDouble(3, t.getThanhTien());
            pst.setInt(4, t.getKHUYENMAI_idKM());
            pst.setInt(5, t.getPHIEUBAOHANH_idBaoHanh());
            pst.setInt(6, t.getSANPHAM_idSP());
            pst.setInt(7, t.getHOADON_idHoaDon());
            ketQua = pst.executeUpdate();
            System.out.println("Đã thực thi: " + sql);
            System.out.println("Đã thay đổi " + ketQua + " dòng");
            JDBCUtil.closeConnection(con);
        } catch (SQLException e) {
            Logger.getLogger(ctHoaDonDAO.class.getName()).log(Level.SEVERE, null, e);
        }
        return ketQua;
    }


    @Override
    public int delete(String id) {
        int ketQua = 0;
        try {
            Connection con = JDBCUtil.getConnection();
            String sql = "DELETE FROM ctHoaDon WHERE HOADON_idHoaDon = ?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, id);
            ketQua = pst.executeUpdate();
            System.out.println("Đã thực thi: " + sql);
            System.out.println("Đã thay đổi " + ketQua + " dòng");
            JDBCUtil.closeConnection(con);
        } catch (SQLException e) {
            Logger.getLogger(ctHoaDonDAO.class.getName()).log(Level.SEVERE, null, e);
        }
        return ketQua;
    }


    @Override
    public ArrayList<ctHoaDonDTO> selectAll() {
        ArrayList<ctHoaDonDTO> ctHoaDonList = new ArrayList<>();
        try {
            Connection con = JDBCUtil.getConnection();
            String sql = "SELECT * FROM ctHoaDon";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                ctHoaDonDTO ctHoaDon = new ctHoaDonDTO(
                        rs.getInt("soLuong"),
                        rs.getFloat("donGia"),
                        rs.getDouble("thanhTien"),
                        rs.getInt("KHUYENMAI_idKM"),
                        rs.getInt("PHIEUBAOHANH_idBaoHanh"),
                        rs.getInt("SANPHAM_idSP"),
                        rs.getInt("HOADON_idHoaDon")
                );
//                ctHoaDon.setId(rs.getInt("id"));
                ctHoaDonList.add(ctHoaDon);
            }
            JDBCUtil.closeConnection(con);
        } catch (SQLException e) {
            Logger.getLogger(ctHoaDonDAO.class.getName()).log(Level.SEVERE, null, e);
        }
        return ctHoaDonList;
    }


    @Override
    public ArrayList<ctHoaDonDTO> selectByCondition(String condition) {
        ArrayList<ctHoaDonDTO> ctHoaDonList = new ArrayList<>();
        try {
            Connection con = JDBCUtil.getConnection();
            String sql = "SELECT * FROM ctHoaDon WHERE " + condition;
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                ctHoaDonDTO ctHoaDon = new ctHoaDonDTO(
                        rs.getInt("soLuong"),
                        rs.getFloat("donGia"),
                        rs.getDouble("thanhTien"),
                        rs.getInt("KHUYENMAI_idKM"),
                        rs.getInt("PHIEUBAOHANH_idBaoHanh"),
                        rs.getInt("SANPHAM_idSP"),
                        rs.getInt("HOADON_idHoaDon")
                );
//                ctHoaDon.setId(rs.getInt("id"));
                ctHoaDonList.add(ctHoaDon);
            }
            JDBCUtil.closeConnection(con);
        } catch (SQLException e) {
            Logger.getLogger(ctHoaDonDAO.class.getName()).log(Level.SEVERE, null, e);
        }
        return ctHoaDonList;
    }


    @Override
    public ctHoaDonDTO selectById(int id) {
        ctHoaDonDTO ctHoaDon = null;
        try {
            Connection con = JDBCUtil.getConnection();
            String sql = "SELECT * FROM cthoadon WHERE HOADON_idHoaDon = ?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                ctHoaDon = new ctHoaDonDTO();
                ctHoaDon.setSoLuong(rs.getInt("soLuong"));
                ctHoaDon.setDonGia(rs.getFloat("donGia"));
                ctHoaDon.setThanhTien(rs.getDouble("thanhTien"));
                ctHoaDon.setKHUYENMAI_idKM(rs.getInt("KHUYENMAI_idKM"));
                ctHoaDon.setPHIEUBAOHANH_idBaoHanh(rs.getInt("PHIEUBAOHANH_idBaoHanh"));
                ctHoaDon.setSANPHAM_idSP(rs.getInt("SANPHAM_idSP"));
                ctHoaDon.setHOADON_idHoaDon(rs.getInt("HOADON_idHoaDon"));
            }
            JDBCUtil.closeConnection(con);
        } catch (SQLException e) {
            Logger.getLogger(ctHoaDonDAO.class.getName()).log(Level.SEVERE, null, e);
        }
        return ctHoaDon;
    }
}
