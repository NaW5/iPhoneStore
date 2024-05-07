package DAO;

import DAOinterface.DAOinterface;
import DTO.NhanVienDTO;
import DTO.TaiKhoanDTO;
import config.JDBCUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TaiKhoanDAO implements DAOinterface<TaiKhoanDTO> {
    public static TaiKhoanDAO getInstance(){
        return new TaiKhoanDAO();
    }

    @Override
    public int insert(TaiKhoanDTO taiKhoanDTO) {
        int kq = 0;
        Connection con = null;
        try{
            con = JDBCUtil.getConnection();
            if(con == null){
                String sql = "insert into taikhoan( tenDangNhap, matKhau, chucVu, trangThai, NHANVIEN_idNV) values(?, ?, ?, 1, ?)";
                PreparedStatement ps = (PreparedStatement) con.prepareStatement(sql);
                ps.setString(1, taiKhoanDTO.getTenDangNhap());
                ps.setString(2, taiKhoanDTO.getMatKhau());
                ps.setString(3, taiKhoanDTO.getChucVu());
                ps.setInt(4, taiKhoanDTO.getNHANVIEN_idNV());

                kq = ps.executeUpdate();
                System.out.println("Không thể kết nối đến cơ sở dữ liệu");
            }else {
                System.out.println("Lỗi khi thêm tài khoản vào cơ sở dữ liệu");
            }

//            JDBCUtil.closeConnection(con);
        }catch (SQLException e){
            e.printStackTrace();
            System.out.println("Lỗi khi thêm tài khoản vào cơ sở dữ liệu: " + e.getMessage());
        }
        return kq;
    }

    @Override
    public int update(TaiKhoanDTO taiKhoanDTO) {
        int kq = 0;
        try{
            Connection con = JDBCUtil.getConnection();
            String sql = "update taikhoan set userName = ?, matKhau = ?, trangThai = ?, chucVu = ? where NHANVIEN_idNV = ?";
            PreparedStatement ps = (PreparedStatement) con.prepareStatement(sql);
            ps.setString(1, taiKhoanDTO.getTenDangNhap());
            ps.setString(2, taiKhoanDTO.getMatKhau());
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
    public int delete(int idNV) {
        int kq = 0;
        try{
            Connection con = JDBCUtil.getConnection();
            String sql = "update taikhoan set trangThai = 0 where NHANVIEN_idNV = ?";
            PreparedStatement ps =  con.prepareStatement(sql);
            ps.setInt(1, idNV);
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
//            String sql = "SELECT tk.* FROM TaiKhoan tk JOIN NhanVien nv ON tk.NHANVIEN_idNV = nv.idNV WHERE tk.trangThai = '0' OR tk.trangThai = '1'";
            String sql = "SELECT * FROM taikhoan where trangThai = 1";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                TaiKhoanDTO taiKhoan = new TaiKhoanDTO();
                taiKhoan.setTenDangNhap(rs.getString("userName"));
                taiKhoan.setMatKhau(rs.getString("matKhau"));
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

    @Override
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
            String sql = "SELECT * FROM taikhoan WHERE tenDangNhap LIKE ?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, "%" + keyword + "%");
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                TaiKhoanDTO taiKhoanDTO = new TaiKhoanDTO(
                        rs.getInt("id_nhanVien"),
                        rs.getString("tenDangNhap"),
                        rs.getString("matKhau"),
                        rs.getString("chucVu"),
                        rs.getInt("trangThai")
                );
                taiKhoanList.add(taiKhoanDTO);
            }
            JDBCUtil.closeConnection(con);
        } catch (SQLException e) {
            Logger.getLogger(NhanVienDAO.class.getName()).log(Level.SEVERE, null, e);
        }
        return taiKhoanList;
    }
}
