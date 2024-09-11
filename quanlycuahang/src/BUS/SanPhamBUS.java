package BUS;

import DAO.SanPhamDAO;
import DTO.SanPhamDTO;

import java.util.ArrayList;

public class SanPhamBUS {
    private ArrayList<SanPhamDTO> listSP = new ArrayList<>();


    private final SanPhamDAO sanPhamDAO;

    public SanPhamBUS() {
        this.sanPhamDAO = new SanPhamDAO();
    }

    public int themSanPham(SanPhamDTO sanPham) {
        return sanPhamDAO.insert(sanPham);
    }

    public int capNhatSanPham(SanPhamDTO sanPham) {
        return sanPhamDAO.update(sanPham);
    }

    public int xoaSanPham(String idSanPham) {
        return sanPhamDAO.delete(idSanPham);
    }

    public ArrayList<SanPhamDTO> layDanhSachSanPham() {
        return sanPhamDAO.selectAll();
    }

    public ArrayList<SanPhamDTO> layDanhSachTatCaSanPham() {
        return sanPhamDAO.selectAllAll();
    }

    public ArrayList<SanPhamDTO> timKiemSanPhamTheoDieuKien(String dieuKien) {
        return sanPhamDAO.selectByCondition(dieuKien);
    }

    public SanPhamDTO laySanPhamTheoId(int id) {
        return sanPhamDAO.selectById(id);
    }

    public int capNhatSoLuongTon(int maSanPham, int soLuong) {
        return sanPhamDAO.updateSoLuongTon(maSanPham, soLuong);
    }

    public int tinhTongSoLuong() {
        return sanPhamDAO.getTotalQuantity();
    }

//    public ArrayList<SanPhamDTO> search(String text) {
//        text = text.toLowerCase();
//        listSP = layDanhSachTatCaSanPham(); // Cập nhật danh sách sản phẩm
//        ArrayList<SanPhamDTO> result = new ArrayList<>();
//        for (SanPhamDTO i : this.listSP) {
//            if (i.getTenSP().toLowerCase().contains(text)) {
//                result.add(i);
//            }
//        }
//        return result;
//    }
public ArrayList<SanPhamDTO> search(String text) {
    text = text.toLowerCase();
    listSP = layDanhSachTatCaChiTietSanPham();
    ArrayList<SanPhamDTO> result = new ArrayList<>();
    for (SanPhamDTO i : this.listSP) {
        if (i.getTenSP().toLowerCase().contains(text)) {
            result.add(i);
        }
    }
    return result;
}

    // Vũ thêm
    public int capNhapSoLuongKhiNhapHang(int idSanPham, int soLuongNhap){
        return sanPhamDAO.updateSoLuong(idSanPham, soLuongNhap);
    }
    // vũ thêm
    public ArrayList<SanPhamDTO> layDanhSachTatCaChiTietSanPham() {
        return sanPhamDAO.selectALLChiTiet();
    }

}
