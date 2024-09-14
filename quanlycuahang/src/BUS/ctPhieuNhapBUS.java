package BUS;

import DAO.ChiTietPhieuNhapDAO;
import DTO.ChiTietPhieuNhapDTO;

import java.util.ArrayList;

public class ctPhieuNhapBUS {
    private ChiTietPhieuNhapDAO chiTietPhieuNhapDAO;

    public ctPhieuNhapBUS() {
        chiTietPhieuNhapDAO = ChiTietPhieuNhapDAO.getInstance();
    }

    public int insert(ArrayList<ChiTietPhieuNhapDTO> chiTietPhieuNhapList) {
        return chiTietPhieuNhapDAO.insert(chiTietPhieuNhapList);
    }

    public int delete(String idPhieuNhap) {
        return chiTietPhieuNhapDAO.delete(idPhieuNhap);
    }

    public int update(ArrayList<ChiTietPhieuNhapDTO> chiTietPhieuNhapList, String idPhieuNhap) {
        return chiTietPhieuNhapDAO.update(chiTietPhieuNhapList, idPhieuNhap);
    }

    public ArrayList<ChiTietPhieuNhapDTO> selectAll(String idPhieuNhap) {
        return chiTietPhieuNhapDAO.selectAll(idPhieuNhap);
    }

    public ArrayList<ChiTietPhieuNhapDTO> selectById_ctp(int idPhieuNhap) {
        return chiTietPhieuNhapDAO.selectById_ctp(idPhieuNhap);
    }

    public int themChiTietPhieuNhap(int soLuong, int donGia, int thanhTien, int PHIEUNHAP_idPhieuNhap, int SANPHAM_idSP) {
        return chiTietPhieuNhapDAO.themChiTietPhieuNhap(soLuong, donGia, thanhTien, PHIEUNHAP_idPhieuNhap, SANPHAM_idSP);
    }
}