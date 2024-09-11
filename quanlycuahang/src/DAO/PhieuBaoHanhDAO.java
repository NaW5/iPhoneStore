package DAO;

import DTO.PhieuBaoHanhDTO;
import config.JDBCUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PhieuBaoHanhDAO implements DAOInterface<PhieuBaoHanhDTO> {

    public static PhieuBaoHanhDAO getInstance() {
        return new PhieuBaoHanhDAO();
    }

    @Override
    public int insert(PhieuBaoHanhDTO phieuBaoHanh) {
        int ketQua = 0;
        try {
            Connection con = JDBCUtil.getConnection();
            String sql = "INSERT INTO PhieuBaoHanh (idBaoHanh, thoiGian) VALUES (?, ?)";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, phieuBaoHanh.getIdBaoHanh());
            pst.setString(2, phieuBaoHanh.getThoiGian());
            ketQua = pst.executeUpdate();
            System.out.println("Đã thực thi: " + sql);
            System.out.println("Đã thay đổi " + ketQua + " dòng");
            JDBCUtil.closeConnection(con);
        } catch (SQLException e) {
            Logger.getLogger(PhieuBaoHanhDAO.class.getName()).log(Level.SEVERE, null, e);
        }
        return ketQua;
    }

    @Override
    public int update(PhieuBaoHanhDTO phieuBaoHanh) {
        int ketQua = 0;
        try {
            Connection con = JDBCUtil.getConnection();
            String sql = "UPDATE PhieuBaoHanh SET thoiGian = ? WHERE idBaoHanh = ?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, phieuBaoHanh.getThoiGian());
            pst.setInt(2, phieuBaoHanh.getIdBaoHanh());
            ketQua = pst.executeUpdate();
            System.out.println("Đã thực thi: " + sql);
            System.out.println("Đã thay đổi " + ketQua + " dòng");
            JDBCUtil.closeConnection(con);
        } catch (SQLException e) {
            Logger.getLogger(PhieuBaoHanhDAO.class.getName()).log(Level.SEVERE, null, e);
        }
        return ketQua;
    }

    @Override
    public int delete(String idBaoHanh) {
        int ketQua = 0;
        try {
            Connection con = JDBCUtil.getConnection();
            String sql = "DELETE FROM PhieuBaoHanh WHERE idBaoHanh = ?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, idBaoHanh);
            ketQua = pst.executeUpdate();
            System.out.println("Đã thực thi: " + sql);
            System.out.println("Đã thay đổi " + ketQua + " dòng");
            JDBCUtil.closeConnection(con);
        } catch (SQLException e) {
            Logger.getLogger(PhieuBaoHanhDAO.class.getName()).log(Level.SEVERE, null, e);
        }
        return ketQua;
    }

    @Override
    public ArrayList<PhieuBaoHanhDTO> selectAll() {
        ArrayList<PhieuBaoHanhDTO> dsPhieuBH = new ArrayList<>();
        try {
            Connection con = JDBCUtil.getConnection();
            String sql = "SELECT * FROM PhieuBaoHanh";
            PreparedStatement pst = con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                PhieuBaoHanhDTO phieuBH = new PhieuBaoHanhDTO(
                        rs.getInt("idBaoHanh"),
                        rs.getString("thoiGian")
                );
                dsPhieuBH.add(phieuBH);
            }
            JDBCUtil.closeConnection(con);
        } catch (SQLException e) {
            Logger.getLogger(PhieuBaoHanhDAO.class.getName()).log(Level.SEVERE, null, e);
        }
        return dsPhieuBH;
    }

	@Override
	public ArrayList<PhieuBaoHanhDTO> selectByCondition(String condition) {
		ArrayList<PhieuBaoHanhDTO> taikhoans = new ArrayList<>();
        try {
            Connection con = JDBCUtil.getConnection();
            String sql = "SELECT * FROM phieubaohanh WHERE " + condition;
            PreparedStatement pst = con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                PhieuBaoHanhDTO taikhoan = new PhieuBaoHanhDTO(
                		rs.getInt("idBaoHanh"),
                        rs.getString("thoiGian")
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
	public PhieuBaoHanhDTO selectById(int t) {
		PhieuBaoHanhDTO baohanh = null;
        try {
            Connection con = JDBCUtil.getConnection();
            String sql = "SELECT * FROM phieubaohanh WHERE thoiGian = ?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, t);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                 baohanh = new PhieuBaoHanhDTO(
                        rs.getInt("idBaoHanh"),
                        rs.getString("thoiGian")
                );
            }
            JDBCUtil.closeConnection(con);
        } catch (SQLException e) {
            Logger.getLogger(PhieuBaoHanhDAO.class.getName()).log(Level.SEVERE, null, e);
        }
        return baohanh;
	}
	
	public PhieuBaoHanhDTO selectByIdBH(int t) {
		PhieuBaoHanhDTO baohanh = null;
        try {
            Connection con = JDBCUtil.getConnection();
            String sql = "SELECT * FROM phieubaohanh WHERE idBaoHanh = ?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, t);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                 baohanh = new PhieuBaoHanhDTO(
                        rs.getInt("idBaoHanh"),
                        rs.getString("thoiGian")
                );
            }
            JDBCUtil.closeConnection(con);
        } catch (SQLException e) {
            Logger.getLogger(PhieuBaoHanhDAO.class.getName()).log(Level.SEVERE, null, e);
        }
        return baohanh;
	}
}
