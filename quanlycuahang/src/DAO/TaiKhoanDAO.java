package DAO;

import DTO.NhanVienDTO;
import DTO.TaiKhoanDTO;
import config.JDBCUtil;
import java.sql.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TaiKhoanDAO implements DAOInterface<TaiKhoanDTO> {
    public static TaiKhoanDAO getInstance(){
        return new TaiKhoanDAO();
    }

    @Override
    public int insert(TaiKhoanDTO taiKhoanDTO) {
        int ketQua = 0;
        try{
            Connection con = JDBCUtil.getConnection();
            String sql = "insert into taikhoan(userName, matKhau, chucVu, trangThai, NHANVIEN_idNV) values(?, ?, ?, 1, ?)";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, taiKhoanDTO.getUsername());
            ps.setString(2, taiKhoanDTO.getPassword());
            ps.setString(3, taiKhoanDTO.getChucVu());
            ps.setInt(4, taiKhoanDTO.getNHANVIEN_idNV());
            ketQua = ps.executeUpdate();
            System.out.println("Đã thực thi: " + sql);
            System.out.println("Đã thay đổi " + ketQua + " dòng");
            JDBCUtil.closeConnection(con);
        } catch (SQLException e) {
            Logger.getLogger(SanPhamDAO.class.getName()).log(Level.SEVERE, null, e);
        }
        return ketQua;
    }

    @Override
    public int update(TaiKhoanDTO taiKhoanDTO) {
        int kq = 0;
        try{
            Connection con = JDBCUtil.getConnection();
            String sql = "update taikhoan set userName = ?, matKhau = ?, trangThai = ?, chucVu = ? where NHANVIEN_idNV = ?";
            PreparedStatement ps = (PreparedStatement) con.prepareStatement(sql);
            ps.setString(1, taiKhoanDTO.getUsername());
            ps.setString(2, taiKhoanDTO.getPassword());
            ps.setInt(3, taiKhoanDTO.getTrangThai());
            ps.setString(4, taiKhoanDTO.getChucVu());
            ps.setInt(5, taiKhoanDTO.getNHANVIEN_idNV());
            kq = ps.executeUpdate();
            JDBCUtil.closeConnection(con);
        }catch (SQLException e){
            Logger.getLogger(TaiKhoanDAO.class.getName()).log(Level.SEVERE, null, e);
        }
        return kq;

    }

//    public void updateMatKhau(TaiKhoanDTO taiKhoanDTO) {
//        try{
//            Connection con = (Connection) JDBCUtil.getConnection();
//            String sql = "update taikhoan tk join nhanvien nv on tk.id_nhanVien = nv.idNV set tk.matKhau = ? where tk.id_nhanVien = ?";
//            PreparedStatement ps = (PreparedStatement) con.prepareStatement(sql);
//            ps.setString(1, taiKhoanDTO.getMatKhau());
//            ps.setInt(2, taiKhoanDTO.getId_nhanVien());
//            ps.executeUpdate();
//            JDBCUtil.closeConnection(con);
//        }catch (SQLException e){
//            Logger.getLogger(TaiKhoanDAO.class.getName()).log(Level.SEVERE, null, e);
//        }
//    }

    @Override
    public int delete(String idNV) {
        int kq = 0;
        try{
            Connection con = JDBCUtil.getConnection();
            String sql = "update taikhoan set trangThai = 0 where NHANVIEN_idNV = ?";
            PreparedStatement ps =  con.prepareStatement(sql);
            ps.setInt(1, Integer.parseInt(idNV));
            kq = ps.executeUpdate();
            JDBCUtil.closeConnection(con);
        }catch (SQLException e){
            Logger.getLogger(TaiKhoanDAO.class.getName()).log(Level.SEVERE, null, e);
        }
        return kq;
    }

    @Override
    public ArrayList<TaiKhoanDTO> selectAll() {
        ArrayList<TaiKhoanDTO>

                taiKhoanList = new ArrayList<>();
        try {
            Connection con =  JDBCUtil.getConnection();
            String sql = "SELECT * FROM taikhoan where trangThai = 1";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                TaiKhoanDTO taiKhoan = new TaiKhoanDTO();
                taiKhoan.setUsername((rs.getString("userName")));
                taiKhoan.setPassword(rs.getString("matKhau"));
                taiKhoan.setChucVu(rs.getString("chucVu"));
                taiKhoan.setTrangThai(rs.getInt("trangThai"));
                taiKhoan.setNHANVIEN_idNV(rs.getInt("NHANVIEN_idNV"));
                taiKhoanList.add(taiKhoan);
            }
            JDBCUtil.closeConnection(con);
        } catch (Exception e) {
            Logger.getLogger(TaiKhoanDAO.class.getName()).log(Level.SEVERE, null, e);
        }
        return taiKhoanList;
    }

    @Override
    public TaiKhoanDTO selectById(int t) {
        TaiKhoanDTO taiKhoanDTO = null;
        try{
            Connection con =  JDBCUtil.getConnection();
            String sql = "select * from taikhoan where NHANVIEN_idNV = ?";
            PreparedStatement ps =  con.prepareStatement(sql);
            ps.setInt(1, t);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                taiKhoanDTO = new TaiKhoanDTO();
                rs.getString("userName");
                rs.getString("matKhau");
                rs.getString("chucVu");
                rs.getInt("trangThai");
                rs.getInt("NHANVIEN_idNV");
            }
            JDBCUtil.closeConnection(con);
        } catch (Exception e) {
            Logger.getLogger(TaiKhoanDAO.class.getName()).log(Level.SEVERE, null, e);
        }
        return taiKhoanDTO;
    }

    public int getAutoIncrement() {
        return 0;
    }


//    @Override
//    public int getAutoIncrement() {
//        int kq = -1;
//        try {
//            Connection con = (Connection) JDBCUtil.getConnection();
//            String sql = "select 'auto_increment' from information_schema.tables where table_name = 'taikhoan' and table_schema = 'quanlycuahang'";
//            PreparedStatement ps = (PreparedStatement) con.prepareStatement(sql);
//            ResultSet rs = ps.executeQuery(sql);
//            if(!rs.isBeforeFirst()){
//                System.out.println("Không có dữ liệu");
//            }else{
//                while(rs.next()){
//                    kq = rs.getInt("auto_increment");
//                }
//            }
//        } catch (SQLException e) {
//            Logger.getLogger(TaiKhoanDAO.class.getName()).log(Level.SEVERE, null, e);
//        }
//        return kq;
//    }

    public ArrayList<TaiKhoanDTO> search(String keyword) {
        ArrayList<TaiKhoanDTO> taiKhoanList = new ArrayList<>();
        try {
            Connection con = JDBCUtil.getConnection();
            String sql = "SELECT * FROM taikhoan WHERE userName LIKE ?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, "%" + keyword + "%");
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                TaiKhoanDTO taiKhoanDTO = new TaiKhoanDTO(
                        rs.getString("userName"),
                        rs.getString("matKhau"),
                        rs.getInt("trangThai"),
                        rs.getString("chucVu"),
                        rs.getInt("NHANVIEN_idNV")
                );
                taiKhoanList.add(taiKhoanDTO);
            }
            JDBCUtil.closeConnection(con);
        } catch (SQLException e) {
            Logger.getLogger(NhanVienDAO.class.getName()).log(Level.SEVERE, null, e);
        }
        return taiKhoanList;
    }



    @Override
    public ArrayList<TaiKhoanDTO> selectByCondition(String condition) {
        // TODO Auto-generated method stub
        return null;
    }

    public int delete(int idNv) {
        int kq = 0;
        try{
            Connection con = JDBCUtil.getConnection();
            String sql = "update taikhoan set trangThai = 0 where NHANVIEN_idNV = ?";
            PreparedStatement ps =  con.prepareStatement(sql);
            ps.setInt(1, idNv);
            kq = ps.executeUpdate();
            JDBCUtil.closeConnection(con);
        }catch (SQLException e){
            Logger.getLogger(TaiKhoanDAO.class.getName()).log(Level.SEVERE, null, e);
        }
        return kq;
    }
}
