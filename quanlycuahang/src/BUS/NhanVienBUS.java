package BUS;

import DAO.NhanVienDAO;
import DTO.NhanVienDTO;

import java.util.ArrayList;

public class NhanVienBUS {
    private final NhanVienDAO nvDao = new NhanVienDAO();
    public ArrayList<NhanVienDTO> dsnv ;
    public NhanVienBUS(){
        dsnv = nvDao.selectAll();
    }

    public ArrayList<NhanVienDTO> selectAll(){
        return nvDao.selectAll();
    }

    public NhanVienDTO selectById(int id){
        return nvDao.selectById(id);
    }

    public int insert(NhanVienDTO nvdto) {
        return nvDao.insert(nvdto);
    }

    public int delete(int id) {
        return nvDao.delete(id);
    }

    public int update(NhanVienDTO nvdto) {
        return nvDao.update(nvdto);
    }
    public ArrayList<NhanVienDTO> search(String key) {
        return nvDao.search(key);
    }

}
