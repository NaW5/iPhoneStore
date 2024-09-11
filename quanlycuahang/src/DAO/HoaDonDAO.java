package DAO;

import DTO.HoaDonDTO;
import config.JDBCUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class HoaDonDAO implements DAOInterface<HoaDonDTO> {

    public static HoaDonDAO getInstance() {
        return new HoaDonDAO();
    }

    @Override
    public int insert(HoaDonDTO t) {
        int ketQua = 0;
        try {
            Connection con = JDBCUtil.getConnection();
            String sql = "INSERT INTO HoaDon (idHoaDon, thoiGian, tongTien, NHANVIEN_idNV, KHACHHANG_idKH) VALUES (?,?,?,?,?)";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, t.getIdHoaDon());
            pst.setDate(2, t.getThoiGian());
            pst.setDouble(3, t.getTongTien());
            pst.setInt(4, t.getNHANVIEN_idNV());
            pst.setInt(5, t.getKHACHHANG_idKH());
            ketQua = pst.executeUpdate();
            System.out.println("Đã thực thi: " + sql) ;
            System.out.println("Đã thay đổi " + ketQua + " dòng");
            JDBCUtil.closeConnection(con);
        } catch (SQLException e) {
            Logger.getLogger(HoaDonDAO.class.getName()).log(Level.SEVERE, null, e);
        }
        return ketQua;
    }


    @Override
    public int update(HoaDonDTO t) {
        int ketQua = 0;
        try {
            Connection con = JDBCUtil.getConnection();
            String sql = "UPDATE HoaDon " +
                    "SET thoiGian = ?, " +
                    "tongTien = ?, " +
                    "NHANVIEN_idNV = ?, " +
                    "KHACHHANG_idKH = ? " +
                    "WHERE idHoaDon = ?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setDate(1, t.getThoiGian());
            pst.setDouble(2, t.getTongTien());
            pst.setInt(3, t.getNHANVIEN_idNV());
            pst.setInt(4, t.getKHACHHANG_idKH());
            pst.setInt(5, t.getIdHoaDon());
            ketQua = pst.executeUpdate();
            System.out.println("Đã thực thi: " + sql);
            System.out.println("Đã thay đổi " + ketQua + " dòng");
            JDBCUtil.closeConnection(con);
        } catch (SQLException e) {
            Logger.getLogger(HoaDonDAO.class.getName()).log(Level.SEVERE, null, e);
        }
        return ketQua;
    }


    @Override
    public int delete(String idHoaDon) {
        int ketQua = 0;
        try {
        	ctHoaDonDAO.getInstance().delete(idHoaDon);
            Connection con = JDBCUtil.getConnection();
            String sql = "DELETE FROM HoaDon WHERE idHoaDon = ?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, idHoaDon);
            ketQua = pst.executeUpdate();
            System.out.println("Đã thực thi: " + sql);
            System.out.println("Đã thay đổi " + ketQua + " dòng");
            JDBCUtil.closeConnection(con);
        } catch (SQLException e) {
            Logger.getLogger(HoaDonDAO.class.getName()).log(Level.SEVERE, null, e);
        }
        return ketQua;
    }


    @Override
    public ArrayList<HoaDonDTO> selectAll() {
        ArrayList<HoaDonDTO> hoaDonList = new ArrayList<>();
        try {
            Connection con = JDBCUtil.getConnection();
            String sql = "SELECT * FROM hoadon";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                HoaDonDTO hoaDon = new HoaDonDTO(
                        rs.getInt("idHoaDon"),
                        rs.getDate("thoiGian"),
                        rs.getDouble("tongTien"),
                        rs.getInt("NHANVIEN_idNV"),
                        rs.getInt("KHACHHANG_idKH")
                );
                hoaDonList.add(hoaDon);
            }
            JDBCUtil.closeConnection(con);
        } catch (SQLException e) {
            Logger.getLogger(HoaDonDAO.class.getName()).log(Level.SEVERE, null, e);
        }
        return hoaDonList;
    }


    @Override
    public ArrayList<HoaDonDTO> selectByCondition(String condition) {
        ArrayList<HoaDonDTO> hoaDonList = new ArrayList<>();
        try {
            Connection con = JDBCUtil.getConnection();
            String sql = "SELECT * FROM HoaDon WHERE " + condition;
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                HoaDonDTO hoaDon = new HoaDonDTO(
                        rs.getInt("idHoaDon"),
                        rs.getDate("thoiGian"),
                        rs.getDouble("tongTien"),
                        rs.getInt("NHANVIEN_idNV"),
                        rs.getInt("KHACHHANG_idKH")
                );
                hoaDonList.add(hoaDon);
            }
            JDBCUtil.closeConnection(con);
        } catch (SQLException e) {
            Logger.getLogger(HoaDonDAO.class.getName()).log(Level.SEVERE, null, e);
        }
        return hoaDonList;
    }

    public ArrayList<HoaDonDTO> selectFromDayToDay(Date startDate, Date endDate) {
        ArrayList<HoaDonDTO> hoaDonList = new ArrayList<>();
        try {
            Connection con = JDBCUtil.getConnection();
            String sql = "SELECT * FROM HoaDon WHERE thoiGian >= ? AND thoiGian <= ?";
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setDate(1, startDate);
            pstmt.setDate(2, endDate);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                HoaDonDTO hoaDon = new HoaDonDTO(
                        rs.getInt("idHoaDon"),
                        rs.getDate("thoiGian"),
                        rs.getDouble("tongTien"),
                        rs.getInt("NHANVIEN_idNV"),
                        rs.getInt("KHACHHANG_idKH")
                );
                hoaDonList.add(hoaDon);
            }
            JDBCUtil.closeConnection(con);
        } catch (SQLException e) {
            Logger.getLogger(HoaDonDAO.class.getName()).log(Level.SEVERE, null, e);
        }
        return hoaDonList;
    }

    @Override
    public HoaDonDTO selectById(int id) {
        HoaDonDTO hoaDon = null;
        try {
            Connection con = JDBCUtil.getConnection();
            String sql = "SELECT * FROM HoaDon WHERE idHoaDon = ?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                hoaDon = new HoaDonDTO(
                        rs.getInt("idHoaDon"),
                        rs.getDate("thoiGian"),
                        rs.getDouble("tongTien"),
                        rs.getInt("NHANVIEN_idNV"),
                        rs.getInt("KHACHHANG_idKH")
                );
            }
            JDBCUtil.closeConnection(con);
        } catch (SQLException e) {
            Logger.getLogger(HoaDonDAO.class.getName()).log(Level.SEVERE, null, e);
        }
        return hoaDon;
    }
    
    public double getMaxTotalAmount() {
        double maxTotalAmount = 0;
        try {
            Connection con = JDBCUtil.getConnection();
            String sql = "SELECT MAX(tongTien) AS maxTotalAmount FROM HoaDon";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            if (rs.next()) {
                maxTotalAmount = rs.getDouble("maxTotalAmount");
            }
            JDBCUtil.closeConnection(con);
        } catch (SQLException e) {
            Logger.getLogger(HoaDonDAO.class.getName()).log(Level.SEVERE, null, e);
        }
        return maxTotalAmount;
    }

    
    public static void main(String[] args) {
    	System.out.println(HoaDonDAO.getInstance().selectAll());
	}
}
