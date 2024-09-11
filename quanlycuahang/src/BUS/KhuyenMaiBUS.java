package BUS;

import DAO.KhuyenMaiDAO;
import DTO.KhuyenMaiDTO;

import java.util.ArrayList;

public class KhuyenMaiBUS {
    private KhuyenMaiDAO khuyenMaiDAO;

    public KhuyenMaiBUS() {
        khuyenMaiDAO = KhuyenMaiDAO.getInstance();
    }

    public ArrayList<KhuyenMaiDTO> getAllKhuyenMai() {
        return khuyenMaiDAO.selectAll();
    }

    public ArrayList<KhuyenMaiDTO> getKhuyenMaiByCondition(String condition) {
        return khuyenMaiDAO.selectByCondition(condition);
    }

    public int insertKhuyenMai(KhuyenMaiDTO khuyenMaiDTO) {
        return khuyenMaiDAO.insert(khuyenMaiDTO);
    }

    public int updateKhuyenMai(KhuyenMaiDTO khuyenMaiDTO) {
        return khuyenMaiDAO.update(khuyenMaiDTO);
    }

    public int deleteKhuyenMai(String idKM) {
        return khuyenMaiDAO.delete(idKM);
    }

    public KhuyenMaiDTO getKhuyenMaiById(int id) {
        return khuyenMaiDAO.selectById(id);
    }

    public KhuyenMaiDTO getKhuyenMaiByIdKM(int idKM) {
        return khuyenMaiDAO.selectByIdKM(idKM);
    }

    public static void main(String[] args) {
        KhuyenMaiBUS khuyenMaiBUS = new KhuyenMaiBUS();
        System.out.println(khuyenMaiBUS.getAllKhuyenMai());
    }
}
