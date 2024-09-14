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
            ketQua += (long) item.getDonGia();
        }
        return ketQua;
    }
    public int getQuantity(ArrayList<ChiTietPhieuNhapDTO> ctphieunhap) {
        int quantity = 0;
        for (ChiTietPhieuNhapDTO item : ctphieunhap) {
            quantity += item.getIMEI();
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
        if (phieunhapDAO.insert(pn) > 0) {
            return true;
        }
        return false;
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

    public ArrayList<ChiTietPhieuNhapDTO> layDanhSachChiTietPhieuNhapTheoIdPhieuNhap(int inPN) {
        return ctPhieuNhapDAO.selectById_ctp(inPN);
    }


    public void updateTongTienBy(int idPhieuNhap, double tongTien) {
        phieunhapDAO.updateTongTien(idPhieuNhap, tongTien);
    }
}