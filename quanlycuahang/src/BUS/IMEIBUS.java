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

    public boolean xoaIMEI(String maIMEI) {
        int result = imeiDAO.delete(maIMEI);
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
}
