package BUS;

import DAO.KhachHangDAO;
import DTO.KhachHangDTO;

import javax.swing.*;
import java.util.ArrayList;

public class KhachHangBUS {

    private final KhachHangDAO khDAO = new KhachHangDAO();
    public ArrayList<KhachHangDTO> listKhachHang = new ArrayList<>();

    public KhachHangBUS() {
        listKhachHang = khDAO.selectAll();
    }
    // trả về danh sách
    public ArrayList<KhachHangDTO> getAll() {
        return this.listKhachHang;
    }
    // lấy khách hàng chỉ mục
    public KhachHangDTO getByIndex(int index) {
        return this.listKhachHang.get(index);
    }
    //chỉ mục của một khách hàng dựa trên mã khách hàng
    public int getIndexByMaDV(int idKH) {
            for (int i = 0; i < this.listKhachHang.size(); i++) {
                if (listKhachHang.get(i).getIdKhachHang() == idKH) {
                    return i;
                }
            }
            return -1;
    }
    public ArrayList<KhachHangDTO> loadDataFromDatabase() {
        return khDAO.selectAllActive();
    }

    // thêm khách hàng
    public boolean add(KhachHangDTO kh) {
        try {
            // Thực hiện thêm vào cơ sở dữ liệu
            int idInserted = khDAO.insert(kh);

            // Kiểm tra xem ID đã được thêm có hợp lệ không
            if (idInserted != 0) {
                // Thêm vào danh sách
                kh.setIdKhachHang(idInserted); // Cập nhật ID cho đối tượng DTO
                listKhachHang.add(kh);
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
    public boolean delete(int idKhachHang) {
        int index = getIndexByMaDV(idKhachHang);
        if (index != -1) {
            boolean check = khDAO.delete(String.valueOf(idKhachHang)) != 0;
            if (check) {
                this.listKhachHang.remove(index);
            }
            return check;
        }
        return false;
    }

    // cập nhâp lại

    public boolean update(KhachHangDTO kh) {
        if(kh.getIsDelete() == 1) {
            boolean check = khDAO.update(kh) != 0;
            if (check) {
                this.listKhachHang.set(getIndexByMaDV(kh.getIdKhachHang()), kh);
            }
            return check;
        }
        return false;
    }
    //tìm kiếm khách hàng dựa trên một từ khóa và loại tìm kiếm được chỉ định

    public String getTenKhachHang(int idKH) {
        String name = "";
        for (KhachHangDTO khachHangDTO : listKhachHang) {
            if (khachHangDTO.getIdKhachHang() == idKH) {
                name = khachHangDTO.getTenKhachHang();
            }
        }
        return name;
    }

    public KhachHangDTO selectKh(int idKH) {
        return khDAO.selectById(Integer.parseInt(idKH+ ""));
    }

    public ArrayList<KhachHangDTO> search(String keyword) {
        return khDAO.search(keyword);
    }

}
