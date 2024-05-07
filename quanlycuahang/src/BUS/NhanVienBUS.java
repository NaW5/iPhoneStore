package BUS;

import DAO.NhanVienDAO;
import DTO.NhanVienDTO;


import javax.swing.*;

import java.util.ArrayList;

public class NhanVienBUS {
    private final NhanVienDAO nvDao = new NhanVienDAO();
    public ArrayList<NhanVienDTO> dsnv ;
    public NhanVienBUS(){
        dsnv = nvDao.selectAll();
    }

//    public ArrayList<NhanVienDTO> getAll(){
//        return this.dsnv;
//    }
//
//    public NhanVienDTO getByIndex(int index){
//        return this.dsnv.get(index);
//    }
//    public int getIndexById(int id){
//       for(int i = 0; i < dsnv.size(); i++){
//           if(dsnv.get(i).getIdNV() == id){
//               return i;
//           }
//       }
//       return -1;
//    }



    public boolean addd(NhanVienDTO nvdto) {
        try {
            // Thực hiện thêm vào cơ sở dữ liệu
            int idInserted = nvDao.insert(nvdto);

            // Kiểm tra xem ID đã được thêm có hợp lệ không
            if (idInserted != 0) {
                // Thêm vào danh sách
                nvdto.setIdNV(idInserted); // Cập nhật ID cho đối tượng DTO
                dsnv.add(nvdto);
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


    // xóa một khách hàng
//    public boolean delete(NhanVienDTO nvdto) {
//        int index = getIndexById(nvdto.getIdNV());
//        if (index != -1) {
//            boolean check = nvDao.delete(nvdto.getIdNV()) != 0;
//            if (check) {
//                this.dsnv.remove(index);
//            }
//            return check;
//        }
//        return false;
//    }
    // cập nhâp lại
//    public boolean update(KhachHangDTO kh) {
//        int index = getIndexByMaDV(kh.getIdKhachHang());
//        if (index != -1) {
//            boolean check = khDAO.update(kh) != 0;
//            if (check) {
//                this.listKhachHang.set(index, kh);
//            }
//            return check;
//        }
//        return false;
//    }
//    public boolean update(NhanVienDTO nvdto) {
//        boolean check = nvDao.update(nvdto) != 0;
//        if (check) {
//            this.dsnv.set(getIndexById(nvdto.getIdNV()), nvdto);
//        }
//        return check;
//    }

//    public String getTenNhanVien(int idKH) {
//        String name = "";
//        for (NhanVienDTO nhanVienDTO : dsnv) {
//            if (nhanVienDTO.getIdNV() == idKH) {
//                name = nhanVienDTO.getHoTen();
//            }
//        }
//        return name;
//    }
//
//    public NhanVienDTO selectnv(int idNv) {
//        return nvDao.selectById(Integer.parseInt(idNv+ ""));
//    }

}
