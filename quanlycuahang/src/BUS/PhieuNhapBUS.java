package BUS;


import DAO.ChiTietPhieuNhapDAO;
import DAO.PhieuNhapDAO;
import DTO.ChiTietPhieuDTO;
import DTO.ChiTietPhieuNhapDTO;
import DTO.NhanVienDTO;
import DTO.PhieuNhapDTO;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.swing.*;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;


public class PhieuNhapBUS {

    public final PhieuNhapDAO phieunhapDAO = new PhieuNhapDAO();
    public final ChiTietPhieuNhapDAO ctPhieuNhapDAO = new ChiTietPhieuNhapDAO();
    ArrayList<PhieuNhapDTO> listPhieuNhap = new ArrayList<>();
    public PhieuNhapBUS() {
        this.listPhieuNhap = phieunhapDAO.selectAll();
    }
    public ArrayList<PhieuNhapDTO> getAll() {
        return this.listPhieuNhap;
    }

    public ArrayList<PhieuNhapDTO> getAllList() {
        return this.listPhieuNhap;
    }

    public ArrayList<ChiTietPhieuNhapDTO> getChiTietPhieuNhap(int idPhieuNhap) {
        return ctPhieuNhapDAO.selectAll(Integer.toString(idPhieuNhap));
    }

    //get max id
    public int getMaxIdPhieuNhap() {
        return phieunhapDAO.getMaxIdPhieuNhap();
    }

    public ArrayList<ChiTietPhieuDTO> getChiTietPhieu_Type(int idPhieuNhap) {
        ArrayList<ChiTietPhieuNhapDTO> arr = ctPhieuNhapDAO.selectAll(Integer.toString(idPhieuNhap));
        ArrayList<ChiTietPhieuDTO> ketQua = new ArrayList<>();
        for (ChiTietPhieuDTO i : arr) {
            ketQua.add(i);
        }
        return ketQua;
    }

    public ChiTietPhieuNhapDTO findCT(ArrayList<ChiTietPhieuNhapDTO> ctphieunhap, int idSanPham) {
        ChiTietPhieuNhapDTO p = null;
        int i = 0;
        while (i < ctphieunhap.size() && p == null) {
            if (ctphieunhap.get(i).getIdSanPham() == idSanPham) {
                p = ctphieunhap.get(i);
            } else {
                i++;
            }
        }
        return p;
    }

    public long getTongTien(ArrayList<ChiTietPhieuNhapDTO> ctphieunhap) {
        long ketQua = 0;
        for (ChiTietPhieuNhapDTO item : ctphieunhap) {
            ketQua += item.getDonGia() * item.getSoLuong();
        }
        return ketQua;
    }
    public int getQuantity(ArrayList<ChiTietPhieuNhapDTO> ctphieunhap) {
        int quantity = 0;
        for (ChiTietPhieuNhapDTO item : ctphieunhap) {
            quantity += item.getSoLuong();
        }
        return quantity;
    }

    // 1 cái load trong phienhap
    public ArrayList<PhieuNhapDTO> loadDataFromDatabase() {
        return phieunhapDAO.selectAllActive();
    }
    public PhieuNhapDTO getPhieuNhapById(int idPhieuNhap) {
        return phieunhapDAO.selectById(idPhieuNhap);
    }
    public ArrayList<ChiTietPhieuNhapDTO> getCTPhieuNhapById(int idPhieuNhap){
        return ctPhieuNhapDAO.selectById_ctp(idPhieuNhap);
    }
    public int getupdateTongTien(int idPhieuNhap, double tongTien) {
        return phieunhapDAO.updateTongTien(idPhieuNhap, tongTien);
    }
    public int add(ArrayList<ChiTietPhieuNhapDTO> ctpn) {
        return ctPhieuNhapDAO.insert(ctpn);
    }
    public ArrayList<PhieuNhapDTO> search(String text) {
        text = text.toLowerCase();
        ArrayList<PhieuNhapDTO> result = new ArrayList<>();
        for (PhieuNhapDTO i : this.listPhieuNhap) {
            if (Integer.toString(i.getIdPhieuNhap()).toLowerCase().contains(text)) {
                result.add(i);
            }
        }
        listPhieuNhap = result;
        return result;
    }
    public boolean addPhieuNhap(PhieuNhapDTO pn) {
        try {
            // Thực hiện thêm vào cơ sở dữ liệu
            int idInserted = phieunhapDAO.insert(pn);

            // Kiểm tra xem ID đã được thêm có hợp lệ không
            if (idInserted != 0) {
                // Thêm vào danh sách
                pn.setIdPhieuNhap(idInserted); // Cập nhật ID cho đối tượng DTO
                listPhieuNhap.add(pn);
                return true;
            } else {
                JOptionPane.showMessageDialog(null, "Lỗi khi thêm vào cơ sở dữ liệu!");
                return false;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Đã xảy ra lỗi: " + ex.getMessage());
            return false;
        }
    }
    public int deletePhieuNhap(int idPhieuNhap){
        return phieunhapDAO.delete(idPhieuNhap);
    }
    public void exportToExcel(ArrayList<PhieuNhapDTO> dspn, String filePath) {
        phieunhapDAO.exportToExcel(dspn, filePath);
    }

    public boolean kiemTraTonTaiIdPhieuNhap(int i) {
        for (PhieuNhapDTO pn : listPhieuNhap) {
            if (pn.getIdPhieuNhap() == i) {
                return true;
            }
        }
        return false;
    }
}