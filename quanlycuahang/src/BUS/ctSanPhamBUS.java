package BUS;

import DAO.ctSanPhamDAO;
import DTO.ctSanPhamDTO;

import java.util.ArrayList;

public class ctSanPhamBUS {
    private final ctSanPhamDAO ctsanphamDAO;

    public ctSanPhamBUS() {
        this.ctsanphamDAO = new ctSanPhamDAO();
    }

    public boolean themctSanPham(ctSanPhamDTO ctspdto) {
        int result = ctsanphamDAO.insert(ctspdto);
        return result > 0;
    }

    public boolean suactSanPham(ctSanPhamDTO ctspdto) {
        int result = ctsanphamDAO.update(ctspdto);
        return result > 0;
    }

    public boolean xoactSanPham(String masp) {
        int result = ctsanphamDAO.delete(masp);
        return result > 0;
    }

    public ArrayList<ctSanPhamDTO> layDanhSachctSanPham() {
        return ctsanphamDAO.selectAll();
    }

    public ArrayList<ctSanPhamDTO> timctSanPhamTheoDieuKien(String condition) {
        return ctsanphamDAO.selectByCondition(condition);
    }

    public ctSanPhamDTO timctSanPhamTheoId(int idsp) {
        return ctsanphamDAO.selectById(idsp);
    }
}
