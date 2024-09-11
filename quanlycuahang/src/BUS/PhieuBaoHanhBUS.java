package BUS;

import DAO.PhieuBaoHanhDAO;
import DTO.PhieuBaoHanhDTO;

import java.util.ArrayList;

public class PhieuBaoHanhBUS {
    private PhieuBaoHanhDAO phieuBaoHanhDAO;

    public PhieuBaoHanhBUS() {
        this.phieuBaoHanhDAO = new PhieuBaoHanhDAO();
    }

    public ArrayList<PhieuBaoHanhDTO> getAllPhieuBaoHanh() {
        return phieuBaoHanhDAO.selectAll();
    }

    public ArrayList<PhieuBaoHanhDTO> getPhieuBaoHanhByCondition(String condition) {
        return phieuBaoHanhDAO.selectByCondition(condition);
    }

    public int insertPhieuBaoHanh(PhieuBaoHanhDTO phieuBaoHanhDTO) {
        return phieuBaoHanhDAO.insert(phieuBaoHanhDTO);
    }

    public int updatePhieuBaoHanh(PhieuBaoHanhDTO phieuBaoHanhDTO) {
        return phieuBaoHanhDAO.update(phieuBaoHanhDTO);
    }

    public int deletePhieuBaoHanh(String idBaoHanh) {
        return phieuBaoHanhDAO.delete(idBaoHanh);
    }

    public PhieuBaoHanhDTO getPhieuBaoHanhById(int id) {
        return phieuBaoHanhDAO.selectById(id);
    }

    public PhieuBaoHanhDTO getPhieuBaoHanhByIdBH(int idBaoHanh) {
        return phieuBaoHanhDAO.selectByIdBH(idBaoHanh);
    }
}
