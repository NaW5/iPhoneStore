
package BUS;

import DAO.TaiKhoanDAO;
import DTO.TaiKhoanDTO;
import java.util.ArrayList;

public class TaiKhoanBUS {
    private TaiKhoanDAO tkDAO;

    public TaiKhoanBUS() {
        tkDAO = new TaiKhoanDAO();
    }

    public ArrayList<TaiKhoanDTO> selectAll() {
        return tkDAO.selectAll();
    }

    public ArrayList<TaiKhoanDTO> search(String key) {
        return tkDAO.search(key);
    }

    public int update(TaiKhoanDTO tk) {
        return tkDAO.update(tk);
    }

    public int delete(int idNv) {
        return tkDAO.delete(idNv);
    }

    public int insert(TaiKhoanDTO tk) {
        return tkDAO.insert(tk);
    }

}