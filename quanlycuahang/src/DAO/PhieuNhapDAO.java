package DAO;

import DTO.PhieuNhapDTO;
import config.JDBCUtil;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import static config.JDBCUtil.getConnection;

public class PhieuNhapDAO implements DAOInterface<PhieuNhapDTO> {
    public static PhieuNhapDAO getInstance() {
        return new PhieuNhapDAO();
    }

    @Override
    public int insert(PhieuNhapDTO pnDTO) {
        int ketqua = 0;
        try {
            Connection con = JDBCUtil.getConnection();
            String sql = "INSERT INTO phieunhapkho(idPhieuNhap, thoiGian, tongTien, NHANVIEN_idNV) VALUES (?,?,?,?)";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, pnDTO.getIdPhieuNhap());
            pst.setDate(2, new java.sql.Date(pnDTO.getThoiGian().getTime())); // Convert java.util.Date to java.sql.Date
            pst.setDouble(3, pnDTO.getTongTien());
            pst.setInt(4, pnDTO.getIdNhanVien());
            ketqua = pst.executeUpdate();
            JDBCUtil.closeConnection(con);
        } catch (SQLException ex) {
            Logger.getLogger(PhieuNhapDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ketqua;
    }
    @Override
    public int update(PhieuNhapDTO pnDTO) {
        int ketqua = 0;
        try {
            Connection con = getConnection();
            String sql = "UPDATE phieunhapkho SET idPhieuNhap = ?, thoiGian = ?, tongTien = ?, NHANVIEN_idNV = ? WHERE idPhieuNhap = ?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, pnDTO.getIdPhieuNhap());
            pst.setTimestamp(2, new Timestamp(pnDTO.getThoiGian().getTime()));
            pst.setDouble(3, pnDTO.getTongTien());
            pst.setInt(4, pnDTO.getIdNhanVien());
            ketqua = pst.executeUpdate();
            JDBCUtil.closeConnection(con);
        } catch (SQLException ex) {
            Logger.getLogger(PhieuNhapDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ketqua;
    }

    @Override
    public int delete(String t) {
        return 0;
    }

    public int delete(int t) {
        int ketqua = 0;
        try {
            Connection con = getConnection();
            String sql = "DELETE FROM phieunhapkho WHERE idPhieuNhap = ?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, t);
            ketqua = pst.executeUpdate();
            JDBCUtil.closeConnection(con);
        } catch (SQLException ex) {
            Logger.getLogger(PhieuNhapDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ketqua;
    }

    @Override
    public ArrayList<PhieuNhapDTO> selectAll() {
        ArrayList<PhieuNhapDTO> ketqua = new ArrayList<>();
        try {
            Connection con = getConnection();
            String sql = "SELECT * FROM phieunhapkho ORDER BY idPhieuNhap DESC";
            PreparedStatement pst = con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                int idPhieuNhap = rs.getInt("idPhieuNhap");
                Date thoigiantao = rs.getDate("thoiGian");
                double tongtien = rs.getDouble("tongTien");
                int idNhanVien = rs.getInt("NHANVIEN_idNV");
                PhieuNhapDTO phieunhap = new PhieuNhapDTO(idPhieuNhap, thoigiantao, tongtien, idNhanVien);
                ketqua.add(phieunhap);
            }
            JDBCUtil.closeConnection(con);
        } catch (SQLException e) {
        }
        return ketqua;
    }

    @Override
    public PhieuNhapDTO selectById(int chonIdPhieuNhap) {
        PhieuNhapDTO ketqua = null;
        try {
            Connection con = getConnection();
            String sql = "SELECT * FROM phieunhapkho WHERE idPhieuNhap = ?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, chonIdPhieuNhap);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                int idPhieuNhap = rs.getInt("idPhieuNhap");
                Date thoiGian = rs.getDate("thoiGian");
                double tongTien = rs.getDouble("tongTien");
                int idNhanVien = rs.getInt("NHANVIEN_idNV");
                ketqua = new PhieuNhapDTO(idPhieuNhap, thoiGian, tongTien, idNhanVien);
            }
            JDBCUtil.closeConnection(con);
        } catch (Exception e) {
            Logger.getLogger(PhieuNhapDAO.class.getName()).log(Level.SEVERE, null, e);
        }
        return ketqua;
    }

    @Override
    public ArrayList<PhieuNhapDTO> selectByCondition(String condition) {
        ArrayList<PhieuNhapDTO> ketqua = new ArrayList<>();
        try {
            Connection con = getConnection();
            String sql = "SELECT * FROM phieunhapkho WHERE " + condition;
            PreparedStatement pst = con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                int idPhieuNhap = rs.getInt("idPhieuNhap");
                Date thoiGian = rs.getDate("thoiGian");
                double tongTien = rs.getDouble("tongTien");
                int idNhanVien = rs.getInt("NHANVIEN_idNV");
                PhieuNhapDTO phieunhap = new PhieuNhapDTO(idPhieuNhap, thoiGian, tongTien, idNhanVien);
                ketqua.add(phieunhap);
            }
            JDBCUtil.closeConnection(con);
        } catch (SQLException e) {
            Logger.getLogger(PhieuNhapDAO.class.getName()).log(Level.SEVERE, null, e);
        }
        return ketqua;
    }

    public int getAutoIncrement() {
        int result = -1;
        try {
            Connection con = (Connection) getConnection();
            String sql = "SELECT AUTO_INCREMENT FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_SCHEMA = 'quanlykhohang' AND TABLE_NAME = 'phieunhapkho'";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            ResultSet rs2 = pst.executeQuery(sql);
            if (!rs2.isBeforeFirst()) {
                System.out.println("No data");
            } else {
                while (rs2.next()) {
                    result = rs2.getInt("AUTO_INCREMENT");
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(PhieuNhapDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    public int updateTongTien(int idPhieuNhap, double tongTien) {
        int ketQua = 0;
        try {
            Connection con = getConnection();
            String sql = "UPDATE phieunhapkho SET tongTien = tongTien + ? WHERE idPhieuNhap = ?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setDouble(1, tongTien);
            pst.setInt(2, idPhieuNhap);
            ketQua = pst.executeUpdate();
            JDBCUtil.closeConnection(con);
        } catch (SQLException e) {
            Logger.getLogger(PhieuNhapDAO.class.getName()).log(Level.SEVERE, null, e);
        }
        return ketQua;
    }

    public ArrayList<PhieuNhapDTO> selectAllActive() {
        ArrayList<PhieuNhapDTO> phieuNhapList = new ArrayList<>();
        try {
            Connection con = getConnection();
            String sql = "SELECT * FROM phieunhapkho";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                PhieuNhapDTO phieuNhap = new PhieuNhapDTO(
                        rs.getInt("idPhieuNhap"),
                        rs.getDate("thoiGian"),
                        rs.getDouble("tongTien"),
                        rs.getInt("NHANVIEN_idNV")
                );
                phieuNhapList.add(phieuNhap);
            }
            JDBCUtil.closeConnection(con);
        } catch (SQLException e) {
            Logger.getLogger(KhachHangDAO.class.getName()).log(Level.SEVERE, null, e);
        }
        return phieuNhapList;
    }

    public void exportToExcel(ArrayList<PhieuNhapDTO> dspn, String filePath) {
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Phiếu nhập");

        // Create header row
        Row headerRow = sheet.createRow(0);
        Cell cell = headerRow.createCell(0);
        cell.setCellValue("Mã phiếu nhập ");
        cell = headerRow.createCell(1);
        cell.setCellValue("Mã nhân viên");
        cell = headerRow.createCell(2);
        cell.setCellValue("Tổng tiền");

        // Create data rows
        for (int i = 0; i < dspn.size(); i++) {
            PhieuNhapDTO pn = dspn.get(i);
            Row row = sheet.createRow(i + 1);
            row.createCell(0).setCellValue(pn.getIdPhieuNhap());
            row.createCell(1).setCellValue(pn.getIdNhanVien());
            String tongTien = String.format("%,.0f", pn.getTongTien());
            row.createCell(2).setCellValue(tongTien);
        }

        // Write the output to a file
        try (FileOutputStream fileOut = new FileOutputStream(filePath)) {
            workbook.write(fileOut);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int getMaxIdPhieuNhap() {
        int maxId = -1;
        try {
            Connection con = getConnection();
            String sql = "SELECT MAX(idPhieuNhap) AS maxId FROM phieunhapkho";
            PreparedStatement pst = con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                maxId = rs.getInt("maxId");
            }
            JDBCUtil.closeConnection(con);
        } catch (SQLException ex) {
            Logger.getLogger(PhieuNhapDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return maxId;
    }
}