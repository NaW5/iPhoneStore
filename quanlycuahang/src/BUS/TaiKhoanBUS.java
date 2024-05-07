package BUS;

import DAO.TaiKhoanDAO;
import DTO.TaiKhoanDTO;

import javax.swing.*;
import java.util.ArrayList;

public class TaiKhoanBUS {
    private final TaiKhoanDAO tkDao = new TaiKhoanDAO();
    private ArrayList<TaiKhoanDTO> dsTaiKhoan;
    public TaiKhoanBUS() {
        dsTaiKhoan = tkDao.selectAll();
    }

    public boolean add(TaiKhoanDTO tkdto) {
        try {
            // Thực hiện thêm vào cơ sở dữ liệu
            int idInserted = tkDao.insert(tkdto);

            // Kiểm tra xem ID đã được thêm có hợp lệ không
            if (idInserted != 0) {
                // Thêm vào danh sách
                tkdto.setNHANVIEN_idNV(idInserted); // Cập nhật ID cho đối tượng DTO
                dsTaiKhoan.add(tkdto);
                return true;
            } else {
                JOptionPane.showMessageDialog(null, "Lỗi khi thêm vào cơ sở dữ liệu!");
                return false;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Đã xảy ra lỗi: " + ex.getMessage());
            return false;
        }
    }

//    public ArrayList<TaiKhoanDTO> getTaiKhoanAll() {
//        return dsTaiKhoan;
//    }
//
//    public TaiKhoanDTO getTaiKhoan(int index){
//        return dsTaiKhoan.get(index);
//    }
//
//    public int getTaiKhoanByIdNV(int idNV){
//        int i = 0;
//        int vitri = -1;
//        while(i< this.dsTaiKhoan.size() && vitri == -1){
//            if(this.dsTaiKhoan.get(i).getId_nhanVien() == idNV){
//                vitri = i;
//            }
//            i++;
//        }
//        return vitri;
//    }
//    public void addTaiKhoan(TaiKhoanDTO tk){
//        dsTaiKhoan.add(tk);
//    }
//
//    public void uadateTaiKhoan(int index, TaiKhoanDTO tk){
//        dsTaiKhoan.set(index, tk);
//    }
//
//    public void deleteTaiKhoan(int index){
////        dsTaiKhoan.remove(index);
//    }


}
