package DAO;

import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import config.JDBCUtil;
import DAOinterface.DAOinterface;
import DTO.NhanVienDTO;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class NhanVienDAO implements DAOinterface<NhanVienDTO> {

    public static NhanVienDAO getInstance() {
        return new NhanVienDAO();
    }

    @Override
    public int insert(NhanVienDTO nhanVienDTO) {
        int kq = 0;
        Connection con = null;
        try{
            con = JDBCUtil.getConnection();
            if (con != null) {
                String sql = "insert into nhanvien(idNV, hoTen, gioiTinh, ngaySinh, sdt, isDelete) values(?, ?, ?, ?, ?,1)";
                PreparedStatement ps = con.prepareStatement(sql);
                ps.setInt(1, nhanVienDTO.getIdNV());
                ps.setString(2, nhanVienDTO.getHoTen());
                ps.setInt(3, nhanVienDTO.getGioiTinh());
                ps.setDate(4, (Date) (nhanVienDTO.getNgaySinh()));
                ps.setInt(5, nhanVienDTO.getSdt());
                kq = ps.executeUpdate();
            } else {
                System.out.println("Không thể kết nối đến cơ sở dữ liệu");
            }
        }catch (SQLException e){
            e.printStackTrace();
            System.out.println("Lỗi khi thêm nhân viên vào cơ sở dữ liệu: " + e.getMessage());
        } finally {
            JDBCUtil.closeConnection(con);
        }
        return kq;
    }

    @Override
    public int update(NhanVienDTO nhanVienDTO) {
        int kq = 0;
        try {
            Connection con =  JDBCUtil.getConnection();
            String sql = "update nhanvien set hoTen = ?, gioiTinh = ?, ngaySinh = ?, sdt = ?, isDelete = ? where idNV = ?";
            PreparedStatement ps = (PreparedStatement) con.prepareStatement(sql);
            ps.setString(1, nhanVienDTO.getHoTen());
            ps.setInt(2, nhanVienDTO.getGioiTinh());
            ps.setDate(3, (Date) (nhanVienDTO.getNgaySinh()));
            ps.setInt(4, nhanVienDTO.getSdt());
            ps.setInt(5, nhanVienDTO.getIsDelete());
            ps.setInt(6, nhanVienDTO.getIdNV());
            // Check if the employee exists in the database
            // Check if the employee exists in the database
            kq = ps.executeUpdate();
        }catch (SQLException e){
            Logger.getLogger(NhanVienDAO.class.getName()).log(Level.SEVERE, null, e);
        }
        return kq;

    }

    @Override
    public int delete(int idNV) {
        int kq = 0;
        try {
            Connection con =  JDBCUtil.getConnection();
            String sql = "update nhanvien set isDelete = 0 where idNV = ?";
            PreparedStatement ps = (PreparedStatement) con.prepareStatement(sql);
            ps.setInt(1, idNV);
            kq = ps.executeUpdate();
            JDBCUtil.closeConnection(con);
        }catch (SQLException e){
            Logger.getLogger(NhanVienDAO.class.getName()).log(Level.SEVERE, null, e);
        }
        return kq;
    }

    @Override
    public ArrayList<NhanVienDTO> selectAll() {
        ArrayList<NhanVienDTO> nhanVienList = new ArrayList<>();
        try {
            Connection con = JDBCUtil.getConnection();
            String sql = "select * from nhanvien where isDelete = 1";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                NhanVienDTO nhanVien = new NhanVienDTO(
                        rs.getInt("idNV"),
                        rs.getString("hoTen"),
                        rs.getInt("gioiTinh"),
                        rs.getDate("ngaySinh"),
                        rs.getInt("sdt"),
                        rs.getInt("isDelete")
                );
                nhanVienList.add(nhanVien);
            }
            JDBCUtil.closeConnection(con);
        } catch (SQLException e) {
            Logger.getLogger(NhanVienDAO.class.getName()).log(Level.SEVERE, null, e);
        }
        return nhanVienList;
    }

    @Override
    public NhanVienDTO selectById(int t) {
        NhanVienDTO nhanVien_dto = null;
        try{
            Connection con =  JDBCUtil.getConnection();
            String sql = "select * from nhanvien where idNV = ?";
            PreparedStatement ps =  con.prepareStatement(sql);
            ps.setInt(1, t);
            ResultSet rs = (ResultSet) ps.executeQuery();

            if(rs.next()){
                nhanVien_dto = new NhanVienDTO(
                        rs.getInt("idNV"),
                        rs.getString("hoTen"),
                        rs.getInt("gioiTinh"),
                        rs.getDate("ngaySinh"),
                        rs.getInt("sdt"),
                        rs.getInt("isDelete")
                );
            }
            JDBCUtil.closeConnection(con);
        }catch (Exception e){
            Logger.getLogger(NhanVienDAO.class.getName()).log(Level.SEVERE, null, e);
        }
        return nhanVien_dto;
    }

    @Override
    public int getAutoIncrement() {
        int kq = -1;
        try {
            Connection con = (Connection) JDBCUtil.getConnection();
            String sql = "select 'auto_increment' from information_schema.tables where table_name = 'nhanvien' and table_schema = 'quanlycuahang' and table_name = 'nhanvien'";
            PreparedStatement ps = (PreparedStatement) con.prepareStatement(sql);
            ResultSet rs = (ResultSet) ps.executeQuery();
            if (!rs.isBeforeFirst()) {
                System.out.println("Không có dữ liệu");
            } else {
                while (rs.next()) {
                    kq = rs.getInt("auto_increment");
                }
            }
        }catch (SQLException e){
            Logger.getLogger(NhanVienDAO.class.getName()).log(Level.SEVERE, null, e);
        }
        return kq;
    }

    public ArrayList<NhanVienDTO> search(String keyword) {
        ArrayList<NhanVienDTO> nhanVienList = new ArrayList<>();
        try {
            Connection con = JDBCUtil.getConnection();
            String sql = "SELECT * FROM nhanvien WHERE hoTen LIKE ?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, "%" + keyword + "%");
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                NhanVienDTO nhanVienDTO = new NhanVienDTO(
                        rs.getInt("idNV"),
                        rs.getString("hoTen"),
                        rs.getInt("gioiTinh"),
                        rs.getDate("ngaySinh"),
                        rs.getInt("sdt"),
                        rs.getInt("isDelete")
                );
                nhanVienList.add(nhanVienDTO);
            }
            JDBCUtil.closeConnection(con);
        } catch (SQLException e) {
            Logger.getLogger(NhanVienDAO.class.getName()).log(Level.SEVERE, null, e);
        }
        return nhanVienList;
    }
    public void exportToExcel(ArrayList<NhanVienDTO> dsnv, String filePath) {
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("NhanVien");

        // Create header row
        Row headerRow = sheet.createRow(0);
        Cell cell = headerRow.createCell(0);
        cell.setCellValue("ID");
        cell = headerRow.createCell(1);
        cell.setCellValue("HoTen");
        cell = headerRow.createCell(2);
        cell.setCellValue("GioiTinh");
        cell = headerRow.createCell(3);
        cell.setCellValue("NgaySinh");
        cell = headerRow.createCell(4);
        cell.setCellValue("SDT");

        // Create data rows
        for (int i = 0; i < dsnv.size(); i++) {
            NhanVienDTO nv = dsnv.get(i);
            Row row = sheet.createRow(i + 1);
            row.createCell(0).setCellValue(nv.getIdNV());
            row.createCell(1).setCellValue(nv.getHoTen());
            row.createCell(2).setCellValue(nv.getGioiTinh() == 1 ? "Nam" : "Nữ");
            row.createCell(3).setCellValue(nv.getNgaySinh().toString());
            row.createCell(4).setCellValue(nv.getSdt());
        }

        // Write the output to a file
        try (FileOutputStream fileOut = new FileOutputStream(filePath)) {
            workbook.write(fileOut);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public int getTotalEmployees() {
        int totalEmployees = 0;
        try {
            Connection con = JDBCUtil.getConnection();
            String sql = "SELECT COUNT(*) AS total FROM nhanvien WHERE isDelete = 1";
            PreparedStatement pst = con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                totalEmployees = rs.getInt("total");
            }
            JDBCUtil.closeConnection(con);
        } catch (SQLException e) {
            Logger.getLogger(NhanVienDAO.class.getName()).log(Level.SEVERE, null, e);
        }
        return totalEmployees;
    }
//NhanVienDAO
}
