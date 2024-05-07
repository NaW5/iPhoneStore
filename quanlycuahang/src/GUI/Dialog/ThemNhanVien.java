package GUI.Dialog;

import BUS.NhanVienBUS;
import DTO.NhanVienDTO;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.toedter.calendar.JDateChooser;

public class ThemNhanVien extends JDialog {
    private static final long serialVersionUID = 1L;
    private final JPanel hi = new JPanel();
    private JTextField jTxt_hoTen;
    private JTextField jTxt_sdt;
    private ButtonGroup buttonGroup;
    private final JDateChooser JDate_ngaySinh;
    private NhanVienBUS nvBus ;


    public ThemNhanVien() {
        setTitle("Thêm Nhân viên");
        setBounds(100, 100, 507, 590);
        getContentPane().setLayout(new BorderLayout());
        hi.setLayout(new FlowLayout());
        hi.setBorder(new EmptyBorder(5, 5, 5, 5));

        getContentPane().add(hi, BorderLayout.CENTER);
        {

            getContentPane().setLayout(null);

            JPanel jP_header = new JPanel();
            jP_header.setBackground(new Color(128, 255, 255));
            jP_header.setBounds(0, 0, 501, 69);
            getContentPane().add(jP_header);
            jP_header.setLayout(null);

            JLabel jL_themNhanVien = new JLabel("THÊM NHÂN VIÊN");
            jL_themNhanVien.setBounds(141, 10, 206, 32);
            jL_themNhanVien.setBackground(new Color(128, 255, 255));
            jL_themNhanVien.setFont(new Font("Dialog", Font.BOLD, 24));
            jL_themNhanVien.setHorizontalAlignment(SwingConstants.CENTER);
            jP_header.add(jL_themNhanVien);

            JPanel jP_center = new JPanel();
            jP_center.setBounds(22, 80, 457, 391);
            getContentPane().add(jP_center);
            jP_center.setLayout(new GridLayout(8, 1, 0, 0));

            JLabel jL_hoTen = new JLabel("Họ Tên:");
            jL_hoTen.setFont(new Font("Tahoma", Font.PLAIN, 16));
            jP_center.add(jL_hoTen);

            jTxt_hoTen = new JTextField();
            jP_center.add(jTxt_hoTen);
            jTxt_hoTen.setColumns(10);

            JLabel jL_sdt = new JLabel("Số Điện Thoại:");
            jL_sdt.setFont(new Font("Tahoma", Font.PLAIN, 16));
            jP_center.add(jL_sdt);

            jTxt_sdt = new JTextField();
            jP_center.add(jTxt_sdt);
            jTxt_sdt.setColumns(10);

            JLabel jL_gioiTinh = new JLabel("Giới Tính:");
            jL_gioiTinh.setFont(new Font("Tahoma", Font.PLAIN, 16));
            jP_center.add(jL_gioiTinh);

            JPanel jP_gioiTinh = new JPanel();
            jP_center.add(jP_gioiTinh);
            jP_gioiTinh.setLayout(new GridLayout(1, 2, 0, 0));

            buttonGroup = new ButtonGroup();

            JRadioButton btn_nam = new JRadioButton("Nam");
            buttonGroup.add(btn_nam);
            jP_gioiTinh.add(btn_nam);

            JRadioButton btn_nu = new JRadioButton("Nữ");
            buttonGroup.add(btn_nu);
            jP_gioiTinh.add(btn_nu);

            JLabel jL_ngaySinh = new JLabel("Ngày Sinh:");
            jL_ngaySinh.setFont(new Font("Tahoma", Font.PLAIN, 16));
            jP_center.add(jL_ngaySinh);

            JDate_ngaySinh = new JDateChooser();
            jP_center.add(JDate_ngaySinh);

            JPanel jP_btn = new JPanel();
            jP_btn.setBounds(66, 481, 354, 59);
            getContentPane().add(jP_btn);
            jP_btn.setLayout(new GridLayout(1, 2, 5, 5));

            JButton btn_themNguoiDung = new JButton("Thêm Người Dùng");
            btn_themNguoiDung.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    // Lấy dữ liệu từ các trường nhập liệu
//
                    String hoTen = jTxt_hoTen.getText();
                    if(!kiemTraHoTen(hoTen)){
                        return;
                    }
                    String sdt = jTxt_sdt.getText();
                    if(!kiemTraSoDienThoai(sdt)){
                        return;
                    }
                    int gioiTinh = btn_nam.isSelected() ? 1 : 0;
                    if(!kiemTraGioiTinh()){
                        return;
                    }
                    java.util.Date ngaySinhDate = JDate_ngaySinh.getDate();
                    if(!kiemTraNgaySinh(ngaySinhDate)){
                        return;
                    }
                    java.time.LocalDate localDate = ngaySinhDate.toInstant().atZone(java.time.ZoneId.systemDefault()).toLocalDate();
                    java.sql.Date sqlDate = java.sql.Date.valueOf(localDate);

                    // Tạo một đối tượng NhanVienDTO mới với dữ liệu này
                    NhanVienDTO nvdto = new NhanVienDTO();
                    nvdto.setHoTen(hoTen);
                    nvdto.setSdt(Integer.parseInt(sdt));
                    nvdto.setGioiTinh(gioiTinh);
                    nvdto.setNgaySinh(sqlDate);
                    //thêm nhân viên vào csdl
                    nvBus =  new NhanVienBUS();
                    int kq = nvBus.insert(nvdto);
                    if(kq >0){
                        JOptionPane.showMessageDialog(null, "Thêm nhân viên thành công!");
                    }else{
                        JOptionPane.showMessageDialog(null, "Thêm nhân viên thất bại!");
                    }
                }

            });
            btn_themNguoiDung.setFont(new Font("Tahoma", Font.PLAIN, 16));
            btn_themNguoiDung.setBackground(new Color(128, 255, 255));
            jP_btn.add(btn_themNguoiDung);

            JButton btn_huyBo = new JButton("Hủy Bỏ");
            btn_huyBo.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    setVisible(false);
                }
            });
            btn_huyBo.setFont(new Font("Tahoma", Font.PLAIN, 16));
            btn_huyBo.setBackground(new Color(221, 55, 92));
            jP_btn.add(btn_huyBo);

        }
    }
    public boolean kiemTraHoTen(String hoTen){
        if (hoTen.trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Tên nhân viên không được để trống!");
            return false;
        }
        // Kiểm tra xem tên nhân viên có chứa ký tự không hợp lệ nào không
        if (!hoTen.matches("[\\p{L}\\s]+")) {
            JOptionPane.showMessageDialog(null, "Tên nhân viên chỉ được chứa chữ cái và khoảng trắng!");
            return false;
        }
        return true;
    }
    public boolean kiemTraSoDienThoai(String sdt){
        if (sdt.trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Số điện thoại không được để trống!");
            return false;
        }

        // Kiểm tra xem số điện thoại có bắt đầu bằng số 0 hay không
        if (!sdt.startsWith("0")) {
            JOptionPane.showMessageDialog(null, "Số điện thoại của bạn phải bắt đầu từ số 0!");
            return false;
        }

        // Kiểm tra xem số điện thoại có đúng 10 số hay không
        if (sdt.length() != 10) {
            JOptionPane.showMessageDialog(null, "Số điện thoại phải có đúng 10 số!");
            return false;
        }
        return true;
    }
    public boolean kiemTraGioiTinh(){
        if(buttonGroup.getSelection()==null){
            JOptionPane.showMessageDialog(null, "Vui lòng chọn giới tính!");
            return false;
        }
        return true;
    }
    public boolean kiemTraNgaySinh(java.util.Date ngaySinhDate){
        if(ngaySinhDate == null){
            JOptionPane.showMessageDialog(null, "Vui lòng chọn ngày sinh!");
            return false;
        }
        return true;
    }

}
