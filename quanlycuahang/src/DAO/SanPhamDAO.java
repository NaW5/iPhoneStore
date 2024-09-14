package DAO;

import DTO.SanPhamDTO;
import config.JDBCUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SanPhamDAO implements DAOInterface<SanPhamDTO> {

    public static SanPhamDAO getInstance() {
        return new SanPhamDAO();
    }

    @Override
    public int insert(SanPhamDTO t) {
        int ketQua = 0;
        try {
            Connection con = JDBCUtil.getConnection();
            String sql = "INSERT INTO SanPham (idSP, tenSP, giaNhap, giaBan, soLuong, hinhAnh, mauSac, isDelete) VALUES (?,?,?,?,?,?,?,?)";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, t.getIdSP());
            pst.setString(2, t.getTenSP());
            pst.setFloat(3, t.getGiaNhap());
            pst.setFloat(4, t.getGiaBan());
            pst.setInt(5, t.getSoLuong());
            pst.setString(6, t.getHinhAnh());
            pst.setString(7, t.getMauSac());
            pst.setInt(8, t.getIsDelete());
            ketQua = pst.executeUpdate();
            JDBCUtil.closeConnection(con);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ketQua;
    }


    @Override
    public int update(SanPhamDTO t) {
        int ketQua = 0;
        try {
            Connection con = JDBCUtil.getConnection();
            String sql = "UPDATE SanPham " + "SET tenSP = ?, " + "giaNhap = ?, " + "giaBan = ?, " + "soLuong = ?, " + "hinhAnh = ?, " + "mauSac = ?, " + "isDelete = ? " + "WHERE idSP = ?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, t.getTenSP());
            pst.setFloat(2, t.getGiaBan());
            pst.setFloat(3, t.getGiaNhap());
            pst.setInt(4, t.getSoLuong());
            pst.setString(5, t.getHinhAnh());
            pst.setString(6, t.getMauSac());
            pst.setInt(7, t.getIsDelete());
            pst.setInt(8, t.getIdSP());
            pst.executeUpdate();
            JDBCUtil.closeConnection(con);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ketQua;
    }


    @Override
    public int delete(String idSanPham) {
        int ketQua = 0;
        try {
            Connection con = JDBCUtil.getConnection();
            String sql = "UPDATE SanPham " + "SET isDelete = 1 " + "WHERE idSP = ?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, idSanPham);
            ketQua = pst.executeUpdate();
            JDBCUtil.closeConnection(con);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ketQua;
    }


    @Override
    public ArrayList<SanPhamDTO> selectAll() {
        ArrayList<SanPhamDTO> sanPhamList = new ArrayList<>();
        try {
            Connection con = JDBCUtil.getConnection();
            String sql = "SELECT * FROM SanPham where isDelete = 0";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                SanPhamDTO sanPham = new SanPhamDTO();
                sanPham.setIdSP(rs.getInt("idSP"));
                sanPham.setTenSP(rs.getString("tenSP"));
                sanPham.setGiaBan(rs.getInt("giaBan"));
                sanPham.setGiaNhap(rs.getInt("giaNhap"));
                sanPham.setSoLuong(rs.getInt("soLuong"));
                sanPham.setHinhAnh(rs.getString("hinhAnh"));
                sanPham.setMauSac(rs.getString("mauSac"));
                sanPham.setIsDelete(rs.getInt("isDelete"));
                sanPhamList.add(sanPham);
            }
            JDBCUtil.closeConnection(con);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return sanPhamList;
    }


    public ArrayList<SanPhamDTO> selectAllAll() {
        ArrayList<SanPhamDTO> sanPhamList = new ArrayList<>();
        try {
            Connection con = JDBCUtil.getConnection();
            String sql = "SELECT * FROM SanPham";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                SanPhamDTO sanPham = new SanPhamDTO();
                sanPham.setIdSP(rs.getInt("idSP"));
                sanPham.setTenSP(rs.getString("tenSP"));
                sanPham.setGiaBan(rs.getInt("giaBan"));
                sanPham.setGiaNhap(rs.getInt("giaNhap"));
                sanPham.setSoLuong(rs.getInt("soLuong"));
                sanPham.setHinhAnh(rs.getString("hinhAnh"));
                sanPham.setMauSac(rs.getString("mauSac"));
                sanPham.setIsDelete(rs.getInt("isDelete"));
                sanPhamList.add(sanPham);
            }
            JDBCUtil.closeConnection(con);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return sanPhamList;
    }

    @Override
    public ArrayList<SanPhamDTO> selectByCondition(String condition) {
        ArrayList<SanPhamDTO> sanPhamList = new ArrayList<>();
        try {
            Connection con = JDBCUtil.getConnection();
            String sql = "SELECT * FROM SanPham WHERE " + condition;
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                SanPhamDTO sanPham = new SanPhamDTO();
                sanPham.setIdSP(rs.getInt("idSP"));
                sanPham.setTenSP(rs.getString("tenSP"));
                sanPham.setGiaBan(rs.getInt("giaBan"));
                sanPham.setGiaNhap(rs.getInt("giaNhap"));
                sanPham.setSoLuong(rs.getInt("soLuong"));
                sanPham.setHinhAnh(rs.getString("hinhAnh"));
                sanPham.setMauSac(rs.getString("mauSac"));
                sanPham.setIsDelete(rs.getInt("isDelete"));
                sanPhamList.add(sanPham);
            }
            JDBCUtil.closeConnection(con);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return sanPhamList;
    }


    @Override
    public SanPhamDTO selectById(int id) {
        SanPhamDTO sanPham = null;
        try {
            Connection con = JDBCUtil.getConnection();
            String sql = "SELECT * FROM SanPham WHERE idSP = ?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                sanPham = new SanPhamDTO();
                sanPham.setIdSP(rs.getInt("idSP"));
                sanPham.setTenSP(rs.getString("tenSP"));
                sanPham.setGiaBan(rs.getInt("giaBan"));
                sanPham.setGiaNhap(rs.getInt("giaNhap"));
                sanPham.setSoLuong(rs.getInt("soLuong"));
                sanPham.setHinhAnh(rs.getString("hinhAnh"));
                sanPham.setMauSac(rs.getString("mauSac"));
                sanPham.setIsDelete(rs.getInt("isDelete"));
            }
            JDBCUtil.closeConnection(con);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return sanPham;
    }


    public int updateSoLuongTon(int masp, int soluong) {
        int ketQua = 0;
        try {
            Connection con = JDBCUtil.getConnection();
            String sql = "UPDATE SanPham SET soLuong = ? WHERE idSP = ?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, soluong);
            pst.setInt(2, masp);
            ketQua = pst.executeUpdate();

            JDBCUtil.closeConnection(con);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ketQua;
    }

    public int getTotalQuantity() {
        int totalQuantity = 0;
        try {
            Connection con = JDBCUtil.getConnection();
            String sql = "SELECT SUM(soLuong) AS totalQuantity FROM SanPham WHERE isDelete = 0";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            if (rs.next()) {
                totalQuantity = rs.getInt("totalQuantity");
            }

            JDBCUtil.closeConnection(con);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return totalQuantity;
    }

    public int updateSoLuong(int idSanPham, int soLuongNhap) {
        String sql = "UPDATE sanpham SET soLuong = soLuong + ? WHERE idSP = ?";
        try {
            Connection connection = JDBCUtil.getConnection();
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, soLuongNhap);
            ps.setInt(2, idSanPham);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return idSanPham;
    }

    public ArrayList<SanPhamDTO> selectALLChiTiet() {
        ArrayList<SanPhamDTO> sanPhamList = new ArrayList<>();
        try {
            Connection con = JDBCUtil.getConnection();
            String sql = "SELECT * FROM SanPham WHERE isDelete = 0";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                SanPhamDTO sanPham = new SanPhamDTO();
                sanPham.setIdSP(rs.getInt("idSP"));
                sanPham.setTenSP(rs.getString("tenSP"));
                sanPham.setGiaNhap(rs.getInt("giaNhap"));
                sanPham.setGiaBan(rs.getInt("giaBan"));
                sanPham.setSoLuong(rs.getInt("soLuong"));
                sanPham.setHinhAnh(rs.getString("hinhAnh"));
                sanPham.setMauSac(rs.getString("mauSac"));
                sanPham.setIsDelete(rs.getInt("isDelete"));
                sanPhamList.add(sanPham);
            }
            JDBCUtil.closeConnection(con);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return sanPhamList;
    }


    public int selectSoLuongSanPhamTrongCTPN(int idSP) {
        int soLuong = 0;
        try {
            Connection con = JDBCUtil.getConnection();
            // Count the number of rows where SANPHAM_idSP matches the given idSP
            String sql = "SELECT COUNT(*) AS soLuong FROM ctphieunhapkho WHERE SANPHAM_idSP = ?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, idSP);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                soLuong = rs.getInt("soLuong");
            }
            JDBCUtil.closeConnection(con);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return soLuong;
    }
}
