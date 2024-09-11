package BUS;

import DAO.NhanVienDAO;
import DTO.NhanVienDTO;

import java.awt.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;


public class NhanVienBUS {
    private final NhanVienDAO nvDao = new NhanVienDAO();
    public ArrayList<NhanVienDTO> dsnv ;
    public NhanVienBUS(){
        dsnv = nvDao.selectAll();
    }
    // Mới thêm đoạn này check lại coi có bị gì không
    public ArrayList<NhanVienDTO> getAll() {
        return nvDao.selectAll();
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

//    public void openFile(String file) {
//        try {
//            File path = new File(file);
//            Desktop.getDesktop().open(path);
//        } catch (IOException e) {
//            System.out.println(e);
//        }
//    }
// trong lớp NhanVienBUS
    public void exportToExcel(ArrayList<NhanVienDTO> dsnv, String filePath) {
        nvDao.exportToExcel(dsnv, filePath);
    }
    public int tinhTongSoLuongNhanVien() {
        return nvDao.getTotalEmployees();
    }
    public NhanVienDTO selectnv(int idNv) {
        return nvDao.selectById(Integer.parseInt(idNv+ ""));
    }
//thêm hàm này vô NhanVienBUS

}
