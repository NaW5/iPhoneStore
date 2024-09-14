package BUS;

import DAO.ctHoaDonDAO;
import DTO.ctHoaDonDTO;

import java.util.ArrayList;

public class ctHoaDonBUS {
    private final ctHoaDonDAO ctHoaDonDAO;

    public ctHoaDonBUS() {
        this.ctHoaDonDAO = new ctHoaDonDAO();
    }

    public ArrayList<ctHoaDonDTO> getAllCTHoaDon() {
        return ctHoaDonDAO.selectAll();
    }

    public ArrayList<ctHoaDonDTO> getCTHoaDonByCondition(String condition) {
        return ctHoaDonDAO.selectByCondition(condition);
    }

    public int insertCTHoaDon(ctHoaDonDTO ctHoaDonDTO) {
        return ctHoaDonDAO.insert(ctHoaDonDTO);
    }

    public int updateCTHoaDon(ctHoaDonDTO ctHoaDonDTO) {
        return ctHoaDonDAO.update(ctHoaDonDTO);
    }

    public int deleteCTHoaDon(String idHoaDon) {
        return ctHoaDonDAO.delete(idHoaDon);
    }

    public ctHoaDonDTO getCTHoaDonById(int id) {
        return ctHoaDonDAO.selectById(id);
    }
}