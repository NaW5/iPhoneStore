package DAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import DTO.IMEIDTO;
import config.JDBCUtil;

public class IMEIDAO implements DAOInterface<IMEIDTO> {

    public static IMEIDAO getInstance() {
        return new IMEIDAO();
    }

    @Override
    public int insert(IMEIDTO t) {
        int ketQua = 0;
        try {
            Connection con = JDBCUtil.getConnection();
            String sql = "INSERT INTO IMEI (maIMEI, SANPHAM_idSP, idPhieuNhap, trangThai) VALUES (?,?,?,?)";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, t.getMaIMEI());
            pst.setInt(2, t.getSANPHAM_idSP());
            if (t.getIdPhieuNhap() == null) {
                pst.setNull(3, Types.INTEGER);
            } else {
                pst.setInt(3, t.getIdPhieuNhap());
            }
            pst.setInt(4, t.getTrangThai());
            ketQua = pst.executeUpdate();
            System.out.println("Đã thực thi: " + sql);
            System.out.println("Đã thay đổi " + ketQua + " dòng");
            JDBCUtil.closeConnection(con);
        } catch (SQLException e) {
            Logger.getLogger(IMEIDAO.class.getName()).log(Level.SEVERE, null, e);
        }
        return ketQua;
    }


    @Override
    public int update(IMEIDTO t) {
        int ketQua = 0;
        try {
            Connection con = JDBCUtil.getConnection();
            String sql = "UPDATE IMEI " +
                    "SET SANPHAM_idSP = ?, " +
                    "idPhieuNhap = ?, " +
                    "trangThai = ?, " +
                    "WHERE maIMEI = ?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, t.getMaIMEI());
            pst.setInt(2, t.getSANPHAM_idSP());
            pst.setInt(3, t.getIdPhieuNhap());
            pst.setInt(4, t.getTrangThai());
            ketQua = pst.executeUpdate();
            System.out.println("Đã thực thi: " + sql);
            System.out.println("Đã thay đổi " + ketQua + " dòng");
            JDBCUtil.closeConnection(con);
        } catch (SQLException e) {
            Logger.getLogger(IMEIDAO.class.getName()).log(Level.SEVERE, null, e);
        }
        return ketQua;
    }


    @Override
    public int delete(String maIMEI) {
        int rowsAffected = 0;
        try {
            Connection con = JDBCUtil.getConnection();
            String sql = "DELETE FROM IMEI " +
                    "WHERE maIMEI = ?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, maIMEI);
            rowsAffected = pst.executeUpdate();
            System.out.println("Đã thực thi: " + sql);
            System.out.println("Đã xóa " + rowsAffected + " dòng");
            JDBCUtil.closeConnection(con);
        } catch (SQLException e) {
            Logger.getLogger(IMEIDAO.class.getName()).log(Level.SEVERE, null, e);
        }
        return rowsAffected;
    }


    @Override
    public ArrayList<IMEIDTO> selectAll() {
        ArrayList<IMEIDTO> ctsanPhamList = new ArrayList<>();
        try {
            Connection con = JDBCUtil.getConnection();
            String sql = "SELECT * FROM IMEI";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                IMEIDTO sanPham = new IMEIDTO();
                sanPham.setMaIMEI(rs.getInt("maIMEI"));
                sanPham.setSANPHAM_idSP(rs.getInt("SANPHAM_idSP"));
                sanPham.setIdPhieuNhap(rs.getInt("idPhieuNhap"));
                sanPham.setTrangThai(rs.getInt("trangThai"));
                ctsanPhamList.add(sanPham);
            }
            JDBCUtil.closeConnection(con);
        } catch (SQLException e) {
            Logger.getLogger(IMEIDAO.class.getName()).log(Level.SEVERE, null, e);
        }
        return ctsanPhamList;
    }


    @Override
    public ArrayList<IMEIDTO> selectByCondition(String condition) {
        ArrayList<IMEIDTO> ctsanPhamList = new ArrayList<>();
        try {
            Connection con = JDBCUtil.getConnection();
            String sql = "SELECT * FROM IMEI WHERE " + condition;
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                IMEIDTO sanPham = new IMEIDTO();
                sanPham.setMaIMEI(rs.getInt("maIMEI"));
                sanPham.setSANPHAM_idSP(rs.getInt("SANPHAM_idSP"));
                sanPham.setIdPhieuNhap(rs.getInt("idPhieuNhap"));
                sanPham.setTrangThai(rs.getInt("trangThai"));
                ctsanPhamList.add(sanPham);
            }
            JDBCUtil.closeConnection(con);
        } catch (SQLException e) {
            Logger.getLogger(IMEIDAO.class.getName()).log(Level.SEVERE, null, e);
        }
        return ctsanPhamList;
    }


    @Override
    public IMEIDTO selectById(int id) {
        IMEIDTO sanPham = null;
        try {
            Connection con = JDBCUtil.getConnection();
            String sql = "SELECT * FROM IMEI WHERE maIMEI = ?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                sanPham = new IMEIDTO();
                sanPham.setMaIMEI(rs.getInt("maIMEI"));
                sanPham.setSANPHAM_idSP(rs.getInt("SANPHAM_idSP"));
                sanPham.setIdPhieuNhap(rs.getInt("idPhieuNhap"));
                sanPham.setTrangThai(rs.getInt("trangThai"));
            }
            JDBCUtil.closeConnection(con);
        } catch (SQLException e) {
            Logger.getLogger(IMEIDAO.class.getName()).log(Level.SEVERE, null, e);
        }
        return sanPham;
    }


    public ArrayList<IMEIDTO> selectAllIMEIBySanPham(int idSP) {
        ArrayList<IMEIDTO> ctsanPhamList = new ArrayList<>();
        try {
            Connection con = JDBCUtil.getConnection();
            String sql = "SELECT * FROM IMEI WHERE SANPHAM_idSP = ?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, idSP);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                IMEIDTO sanPham = new IMEIDTO();
                sanPham.setMaIMEI(rs.getInt("maIMEI"));
                sanPham.setSANPHAM_idSP(rs.getInt("SANPHAM_idSP"));
                sanPham.setIdPhieuNhap(rs.getInt("idPhieuNhap"));
                sanPham.setTrangThai(rs.getInt("trangThai"));
                ctsanPhamList.add(sanPham);
            }
            JDBCUtil.closeConnection(con);
        } catch (SQLException e) {
            Logger.getLogger(IMEIDAO.class.getName()).log(Level.SEVERE, null, e);
        }
        return ctsanPhamList;
    }

    public boolean kiemTraTrungIMEI(int maIMEI) {
        boolean exists = false;
        try {
            Connection con = JDBCUtil.getConnection();
            String sql = "SELECT 1 FROM IMEI WHERE maIMEI = ?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, maIMEI);
            ResultSet rs = pst.executeQuery();
            exists = rs.next();
            JDBCUtil.closeConnection(con);
        } catch (SQLException e) {
            Logger.getLogger(IMEIDAO.class.getName()).log(Level.SEVERE, null, e);
        }
        return exists;
    }

    public ArrayList<IMEIDTO> selectAllIMEIByPhieuNhap(int inPN) {
        ArrayList<IMEIDTO> IMEIList = new ArrayList<>();
        try {
            Connection con = JDBCUtil.getConnection();
            String sql = "SELECT * FROM IMEI WHERE idPhieuNhap = ?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, inPN);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                IMEIDTO imei = new IMEIDTO();
                imei.setMaIMEI(rs.getInt("maIMEI"));
                imei.setSANPHAM_idSP(rs.getInt("SANPHAM_idSP"));
                imei.setIdPhieuNhap(rs.getInt("idPhieuNhap"));
                imei.setTrangThai(rs.getInt("trangThai"));
                IMEIList.add(imei);
            }
            JDBCUtil.closeConnection(con);
        } catch (SQLException e) {
            Logger.getLogger(IMEIDAO.class.getName()).log(Level.SEVERE, null, e);
        }
        return IMEIList;
    }

    public int updateIdPhieuNhap(int maIMEI, int idPhieuNhap) {
        int ketQua = 0;
        try {
            Connection con = JDBCUtil.getConnection();
            String sql = "UPDATE IMEI " +
                    "SET idPhieuNhap = ? " +
                    "WHERE maIMEI = ?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, idPhieuNhap);
            pst.setInt(2, maIMEI);
            ketQua = pst.executeUpdate();
            System.out.println("Đã thực thi: " + sql);
            System.out.println("Đã thay đổi " + ketQua + " dòng");
            JDBCUtil.closeConnection(con);
        } catch (SQLException e) {
            Logger.getLogger(IMEIDAO.class.getName()).log(Level.SEVERE, null, e);
        }
        return ketQua;
    }

    public int updatePhieuNhap0(int idPhieuNhap) {
        int ketQua = 0;
        try {
            Connection con = JDBCUtil.getConnection();
            String sql = "UPDATE IMEI " +
                    "SET idPhieuNhap = NULL " +
                    "WHERE idPhieuNhap = ?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, idPhieuNhap);
            ketQua = pst.executeUpdate();
            System.out.println("Đã thực thi: " + sql);
            System.out.println("Đã thay đổi " + ketQua + " dòng");
            JDBCUtil.closeConnection(con);
        } catch (SQLException e) {
            Logger.getLogger(IMEIDAO.class.getName()).log(Level.SEVERE, null, e);
        }
        return ketQua;
    }

    public ArrayList<IMEIDTO> selectAllIMEIBySanPhamChuaNhap(int idSP, int i) {
        ArrayList<IMEIDTO> IMEIList = new ArrayList<>();
        try {
            Connection con = JDBCUtil.getConnection();
            String sql = "SELECT * FROM IMEI WHERE SANPHAM_idSP = ? AND idPhieuNhap IS NULL";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, idSP);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                IMEIDTO imei = new IMEIDTO();
                imei.setMaIMEI(rs.getInt("maIMEI"));
                imei.setSANPHAM_idSP(rs.getInt("SANPHAM_idSP"));
                imei.setIdPhieuNhap(rs.getInt("idPhieuNhap"));
                imei.setTrangThai(rs.getInt("trangThai"));
                IMEIList.add(imei);
            }
            JDBCUtil.closeConnection(con);
        } catch (SQLException e) {
            Logger.getLogger(IMEIDAO.class.getName()).log(Level.SEVERE, null, e);
        }
        return IMEIList;
    }
}
