package DAO;

import DTO.KhachHangDTO;
import config.JDBCUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class KhachHangDAO implements DAOInterface<KhachHangDTO> {

    public static KhachHangDAO getInstance() {
        return new KhachHangDAO();
    }
    @Override
    public int insert(KhachHangDTO t){
        int ketqua = 0;
        try {
            Connection con = JDBCUtil.getConnection();
            String sql = "INSERT INTO khachhang(idKH, tenKH , diaChi, sdt, isDelete) VALUES(?,?,?,?,1)";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, t.getIdKhachHang());
            pst.setString(2, t.getTenKhachHang());
            pst.setString(3, t.getDiaChiKhachHang());
            pst.setInt(4, t.getSdtKhachHang());
            ketqua = pst.executeUpdate();
            JDBCUtil.closeConnection(con);
        } catch (SQLException e) {
            Logger.getLogger(KhachHangDAO.class.getName()).log(Level.SEVERE, null, e);
        }
        return ketqua;
    }
    @Override
    public int update(KhachHangDTO t) {
        int ketqua = 0;
        try {
            Connection con = JDBCUtil.getConnection();
            String sql = "UPDATE khachhang SET tenKH = ?, diaChi = ?, sdt = ? WHERE idKH = ?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, t.getTenKhachHang());
            pst.setString(2, t.getDiaChiKhachHang());
            pst.setInt(3, t.getSdtKhachHang());
            pst.setInt(4, t.getIdKhachHang());

            ketqua = pst.executeUpdate();
            JDBCUtil.closeConnection(con);
        } catch (SQLException e) {
            Logger.getLogger(KhachHangDAO.class.getName()).log(Level.SEVERE, null, e);
        }
        return ketqua;
    }
    @Override
    public int delete(String idKhachHang) {
        int ketqua = 0;
        try {
            Connection con = JDBCUtil.getConnection();
            String sql = "UPDATE khachhang SET isDelete = 0 WHERE idKH = ?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, idKhachHang);
            ketqua = pst.executeUpdate();
            JDBCUtil.closeConnection(con);
        } catch (SQLException e) {
            Logger.getLogger(KhachHangDAO.class.getName()).log(Level.SEVERE, null, e);
        }
        return ketqua;
    }
    @Override
    public ArrayList<KhachHangDTO> selectAll() {
        ArrayList<KhachHangDTO> khachHangList = new ArrayList<>();
        try {
            Connection con = JDBCUtil.getConnection();
            String sql = "SELECT * FROM khachhang WHERE isDelete = 1";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                KhachHangDTO khachHang = new KhachHangDTO(
                        rs.getInt("idKH"),
                        rs.getString("tenKH"),
                        rs.getString("diaChi"),
                        rs.getInt("sdt"),
                        rs.getInt("isDelete")
                );
                khachHangList.add(khachHang);
            }
            JDBCUtil.closeConnection(con);
        } catch (SQLException e) {
            Logger.getLogger(KhachHangDAO.class.getName()).log(Level.SEVERE, null, e);
        }
        return khachHangList;
    }
    public ArrayList<KhachHangDTO> selectByCondition(String dieuKien) {
        ArrayList<KhachHangDTO> khachHangList = new ArrayList<>();
        try {
            Connection con = JDBCUtil.getConnection();
            String sql = "SELECT * FROM khachhang WHERE " + dieuKien;
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                KhachHangDTO khachHang = new KhachHangDTO(
                        rs.getInt("idKH"),
                        rs.getString("tenKH"),
                        rs.getString("diaChi"),
                        rs.getInt("sdt"),
                        rs.getInt("isDelete")
                );
                khachHangList.add(khachHang);
            }
            JDBCUtil.closeConnection(con);
        } catch (SQLException e) {
            Logger.getLogger(KhachHangDAO.class.getName()).log(Level.SEVERE, null, e);
        }
        return khachHangList;
    }

    @Override
    public KhachHangDTO selectById(int chonIdKhachHang) {
        KhachHangDTO khachHang = null;
        try {
            Connection con = JDBCUtil.getConnection();
            String sql = "SELECT * FROM khachhang WHERE idKH = ?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, chonIdKhachHang);
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                khachHang = new KhachHangDTO(
                        rs.getInt("idKH"),
                        rs.getString("tenKH"),
                        rs.getString("diaChi"),
                        rs.getInt("sdt"),
                        rs.getInt("isDelete")
                );
            }
            JDBCUtil.closeConnection(con);
        } catch (SQLException e) {
            Logger.getLogger(KhachHangDAO.class.getName()).log(Level.SEVERE, null, e);
        }
        return khachHang;
    }
    public int getAutoIncrement() {
        int result = -1;
        try {
            Connection con = JDBCUtil.getConnection();
            String sql = "SELECT AUTO_INCREMENT FROM  INFORMATION_SCHEMA.TABLES WHERE TABLE_SCHEMA = quanlykhohang AND TABLE_NAME   = 'khachhang'";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            ResultSet rs2 = pst.executeQuery(sql);
            if (!rs2.isBeforeFirst() ) {
                System.out.println("No data");
            } else {
                while ( rs2.next() ) {
                    result = rs2.getInt("AUTO_INCREMENT");

                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(KhachHangDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }
    public ArrayList<KhachHangDTO> search(String keyword) {
        ArrayList<KhachHangDTO> khachHangList = new ArrayList<>();
        try {
            Connection con = JDBCUtil.getConnection();
            String sql = "SELECT * FROM khachhang WHERE isDelete = 1 AND tenKH LIKE ?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, "%" + keyword + "%");
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                KhachHangDTO khachHang = new KhachHangDTO(
                        rs.getInt("idKH"),
                        rs.getString("tenKH"),
                        rs.getString("diaChi"),
                        rs.getInt("sdt"),
                        rs.getInt("isDelete")
                );
                khachHangList.add(khachHang);
            }
            JDBCUtil.closeConnection(con);
        } catch (SQLException e) {
            Logger.getLogger(KhachHangDAO.class.getName()).log(Level.SEVERE, null, e);
        }
        return khachHangList;
    }
    public ArrayList<KhachHangDTO> selectAllActive() {
        ArrayList<KhachHangDTO> khachHangList = new ArrayList<>();
        try {
            Connection con = JDBCUtil.getConnection();
            String sql = "SELECT * FROM khachhang WHERE isDelete = 1";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                KhachHangDTO khachHang = new KhachHangDTO(
                        rs.getInt("idKH"),
                        rs.getString("tenKH"),
                        rs.getString("diaChi"),
                        rs.getInt("sdt"),
                        rs.getInt("isDelete")
                );
                khachHangList.add(khachHang);
            }
            JDBCUtil.closeConnection(con);
        } catch (SQLException e) {
            Logger.getLogger(KhachHangDAO.class.getName()).log(Level.SEVERE, null, e);
        }
        return khachHangList;
    }
    
    public int getTotalCustomers() {
      int totalCustomers = 0;
      try {
          Connection con = JDBCUtil.getConnection();
          String sql = "SELECT COUNT(*) AS totalCustomers FROM khachhang";
          Statement stmt = con.createStatement();
          ResultSet rs = stmt.executeQuery(sql);
          if (rs.next()) {
              totalCustomers = rs.getInt("totalCustomers");
          }
          JDBCUtil.closeConnection(con);
      } catch (SQLException e) {
          Logger.getLogger(KhachHangDAO.class.getName()).log(Level.SEVERE, null, e);
      }
      return totalCustomers;
  }

}

