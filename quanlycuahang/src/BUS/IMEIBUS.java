package BUS;

import java.util.ArrayList;
import DAO.IMEIDAO;
import DAO.ctSanPhamDAO;
import DTO.IMEIDTO;

public class IMEIBUS {
    private final IMEIDAO imeiDAO;

    public IMEIBUS() {
        this.imeiDAO = new IMEIDAO();
    }

    public boolean themIMEI(IMEIDTO imeiDTO) {
        int result = imeiDAO.insert(imeiDTO);
        return result > 0;
    }

    public boolean suaIMEI(IMEIDTO imeiDTO) {
        int result = imeiDAO.update(imeiDTO);
        return result > 0;
    }

    //update idPhieuNhap
    public boolean updateIdPhieuNhap(int maIMEI, int idPhieuNhap) {
        int result = imeiDAO.updateIdPhieuNhap(maIMEI, idPhieuNhap);
        return result > 0;
    }

    //delete by phieu nhap
    public boolean updatePhieuNhap0(int idPhieuNhap) {
        int result = imeiDAO.updatePhieuNhap0(idPhieuNhap);
        return result > 0;
    }
    public boolean xoaIMEI(String maIMEI) {
        int result = imeiDAO.updatePhieuNhap0(Integer.parseInt(maIMEI));
        return result > 0;
    }

    public ArrayList<IMEIDTO> layDanhSachIMEI() {
        return imeiDAO.selectAll();
    }

    public ArrayList<IMEIDTO> timIMEITheoDieuKien(String condition) {
        return imeiDAO.selectByCondition(condition);
    }

    public IMEIDTO timIMEITheoId(int id) {
        return imeiDAO.selectById(id);
    }

    public ArrayList<IMEIDTO> layDanhSachIMEITheoSanPham(int idSP) {
        return imeiDAO.selectAllIMEIBySanPham(idSP);
    }

    public boolean kiemTraTrungIMEI(int maIMEI) {
        return imeiDAO.kiemTraTrungIMEI(maIMEI);
    }

    public ArrayList<IMEIDTO> layDanhSachIMEITheoPhieuNhap(int inPN) {
        return imeiDAO.selectAllIMEIByPhieuNhap(inPN);
    }

    public ArrayList<IMEIDTO> layDanhSachIMEITheoSanPhamChuaNhap(int idSP, int i) {
        return imeiDAO.selectAllIMEIBySanPhamChuaNhap(idSP, i);
    }
}
