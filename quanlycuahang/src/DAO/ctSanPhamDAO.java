package DAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import DTO.ctSanPhamDTO;
import config.JDBCUtil;

public class ctSanPhamDAO implements DAOInterface<ctSanPhamDTO> {
    public static ctSanPhamDAO getInstance() {
        return new ctSanPhamDAO();
    }
    @Override
    public int insert(ctSanPhamDTO t) {
        int ketQua = 0;
        try {
            Connection con = JDBCUtil.getConnection();
            String sql = "INSERT INTO ctSanPham (chip, pin, manHinh, phienBanHDH, cameraSau, cameraTruoc, ram, rom, SANPHAM_idSP) VALUES (?,?,?,?,?,?,?,?,?)";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, t.getChip());
            pst.setString(2, t.getPin());
            pst.setString(3, t.getManHinh());
            pst.setString(4, t.getPhienBanHDH());
            pst.setString(5, t.getCameraSau());
            pst.setString(6, t.getCameraTruoc());
            pst.setString(7, t.getRam());
            pst.setString(8, t.getRom());
            pst.setInt(9, t.getSANPHAM_idSP());
            ketQua = pst.executeUpdate();
            System.out.println("Đã thực thi: " + sql);
            System.out.println("Đã thay đổi " + ketQua + " dòng");
            JDBCUtil.closeConnection(con);
        } catch (SQLException e) {
            Logger.getLogger(ctSanPhamDAO.class.getName()).log(Level.SEVERE, null, e);
        }
        return ketQua;
    }

    @Override
    public int update(ctSanPhamDTO t) {
        int ketQua = 0;
        try {
            Connection con = JDBCUtil.getConnection();
            String sql = "UPDATE ctSanPham " +
                    "SET chip = ?, " +
                    "pin = ?, " +
                    "manHinh = ?, " +
                    "phienBanHDH = ?, " +
                    "cameraSau = ?, " +
                    "cameraTruoc = ?, " +
                    "ram = ?, " +
                    "rom = ? " +
                    "WHERE SANPHAM_idSP = ?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, t.getChip());
            pst.setString(2, t.getPin());
            pst.setString(3, t.getManHinh());
            pst.setString(4, t.getPhienBanHDH());
            pst.setString(5, t.getCameraSau());
            pst.setString(6, t.getCameraTruoc());
            pst.setString(7, t.getRam());
            pst.setString(8, t.getRom());
            pst.setInt(9, t.getSANPHAM_idSP());
            ketQua = pst.executeUpdate();
            System.out.println("Đã thực thi: " + sql);
            System.out.println("Đã thay đổi " + ketQua + " dòng");
            JDBCUtil.closeConnection(con);
        } catch (SQLException e) {
            Logger.getLogger(ctSanPhamDAO.class.getName()).log(Level.SEVERE, null, e);
        }
        return ketQua;
    }

    @Override
    public int delete(String masp) {
        int ketQua = 0;
        try {
            Connection con = JDBCUtil.getConnection();
            String sql = "DELETE FROM ctSanPham WHERE SANPHAM_idSP = ?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, masp);
            ketQua = pst.executeUpdate();
            System.out.println("Đã thực thi: " + sql);
            System.out.println("Đã thay đổi " + ketQua + " dòng");
            JDBCUtil.closeConnection(con);
        } catch (SQLException e) {
            Logger.getLogger(ctSanPhamDAO.class.getName()).log(Level.SEVERE, null, e);
        }
        return ketQua;
    }

    @Override
    public ArrayList<ctSanPhamDTO> selectAll() {
        ArrayList<ctSanPhamDTO> ctSanPhamList = new ArrayList<>();
        try {
            Connection con = JDBCUtil.getConnection();
            String sql = "SELECT * FROM ctSanPham";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                ctSanPhamDTO ctSanPham = new ctSanPhamDTO();
                ctSanPham.setChip(rs.getString("chip"));
                ctSanPham.setPin(rs.getString("pin"));
                ctSanPham.setManHinh(rs.getString("manHinh"));
                ctSanPham.setPhienBanHDH(rs.getString("phienBanHDH"));
                ctSanPham.setCameraSau(rs.getString("cameraSau"));
                ctSanPham.setCameraTruoc(rs.getString("cameraTruoc"));
                ctSanPham.setRam(rs.getString("ram"));
                ctSanPham.setRom(rs.getString("rom"));
                ctSanPham.setSANPHAM_idSP(rs.getInt("SANPHAM_idSP"));
                ctSanPhamList.add(ctSanPham);
            }
            JDBCUtil.closeConnection(con);
        } catch (SQLException e) {
            Logger.getLogger(ctSanPhamDAO.class.getName()).log(Level.SEVERE, null, e);
        }
        return ctSanPhamList;
    }

    @Override
    public ArrayList<ctSanPhamDTO> selectByCondition(String condition) {
        ArrayList<ctSanPhamDTO> ctSanPhamList = new ArrayList<>();
        try {
            Connection con = JDBCUtil.getConnection();
            String sql = "SELECT * FROM ctsanpham WHERE " + condition;
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                ctSanPhamDTO ctSanPham = new ctSanPhamDTO();
                ctSanPham.setChip(rs.getString("chip"));
                ctSanPham.setPin(rs.getString("pin"));
                ctSanPham.setManHinh(rs.getString("manHinh"));
                ctSanPham.setPhienBanHDH(rs.getString("phienBanHDH"));
                ctSanPham.setCameraSau(rs.getString("cameraSau"));
                ctSanPham.setCameraTruoc(rs.getString("cameraTruoc"));
                ctSanPham.setRam(rs.getString("ram"));
                ctSanPham.setRom(rs.getString("rom"));
                ctSanPham.setSANPHAM_idSP(rs.getInt("SANPHAM_idSP"));
                ctSanPhamList.add(ctSanPham);
            }
            JDBCUtil.closeConnection(con);
        } catch (SQLException e) {
            Logger.getLogger(ctSanPhamDAO.class.getName()).log(Level.SEVERE, null, e);
        }
        return ctSanPhamList;
    }

    @Override
    public ctSanPhamDTO selectById(int idsp) {
        ctSanPhamDTO ctSanPham = null;
        try {
            Connection con = JDBCUtil.getConnection();
            String sql = "SELECT * FROM ctSanPham WHERE SANPHAM_idSP = ?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, idsp);
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                ctSanPham = new ctSanPhamDTO();
                ctSanPham.setChip(rs.getString("chip"));
                ctSanPham.setPin(rs.getString("pin"));
                ctSanPham.setManHinh(rs.getString("manHinh"));
                ctSanPham.setPhienBanHDH(rs.getString("phienBanHDH"));
                ctSanPham.setCameraSau(rs.getString("cameraSau"));
                ctSanPham.setCameraTruoc(rs.getString("cameraTruoc"));
                ctSanPham.setRam(rs.getString("ram"));
                ctSanPham.setRom(rs.getString("rom"));
                ctSanPham.setSANPHAM_idSP(rs.getInt("SANPHAM_idSP")); 
            }
            JDBCUtil.closeConnection(con);
        } catch (SQLException e) {
            Logger.getLogger(ctSanPhamDAO.class.getName()).log(Level.SEVERE, null, e);
        }
        return ctSanPham;
    }

}
