package BUS;

import java.sql.Date;
import java.util.ArrayList;

import DAO.HoaDonDAO;
import DTO.HoaDonDTO;

public class HoaDonBUS {
    private final HoaDonDAO hoaDonDAO;

    public HoaDonBUS() {
        hoaDonDAO = new HoaDonDAO();
    }

    public ArrayList<HoaDonDTO> getAllHoaDon() {
        return hoaDonDAO.selectAll();
    }

    public ArrayList<HoaDonDTO> getHoaDonByCondition(String condition) {
        return hoaDonDAO.selectByCondition(condition);
    }

    public ArrayList<HoaDonDTO> getHoaDonFromDayToDay(Date startDate, Date endDate) {
        return hoaDonDAO.selectFromDayToDay(startDate, endDate);
    }

    public double getMaxTotalAmount() {
        return hoaDonDAO.getMaxTotalAmount();
    }

    public int insertHoaDon(HoaDonDTO hoaDonDTO) {
        return hoaDonDAO.insert(hoaDonDTO);
    }

    public int updateHoaDon(HoaDonDTO hoaDonDTO) {
        return hoaDonDAO.update(hoaDonDTO);
    }

    public int deleteHoaDon(String idHoaDon) {
        return hoaDonDAO.delete(idHoaDon);
    }

    public HoaDonDTO getHoaDonById(int id) {
        return hoaDonDAO.selectById(id);
    }
}
