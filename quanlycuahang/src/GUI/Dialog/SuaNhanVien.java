package GUI.Dialog;

import BUS.NhanVienBUS;
import DAO.NhanVienDAO;
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
import java.text.SimpleDateFormat;
import com.toedter.calendar.JDateChooser;
public class SuaNhanVien extends JDialog {

    private static final long serialVersionUID = 1L;
    private final JPanel hi = new JPanel();
    private final JRadioButton btn_nam;
    private final JRadioButton btn_nu;
    private final JDateChooser JDate_ngaySinh;
    private JTextField jTxt_hoTen;
    private JTextField jTxt_sdt;
    private ButtonGroup buttonGroup;
    private JTextField jTxt_ngaySinh;
    private NhanVienBUS nvBUS ;
    private int idNv;

    /**
     * Launch the application.
     */


    public SuaNhanVien() {

        nvBUS =  new NhanVienBUS();
        setTitle("Sửa Nhân Viên");
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

            JLabel jL_themNhanVien = new JLabel("SỬA NHÂN VIÊN");
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

            btn_nam = new JRadioButton("Nam");
            buttonGroup.add(btn_nam);
            jP_gioiTinh.add(btn_nam);

            btn_nu = new JRadioButton("Nữ");
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

            JButton btn_suaNhanVien = new JButton("Sửa Nhân Viên");
            btn_suaNhanVien.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    // Get the data from the input fields
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

                        // Update the NhanVienDTO object with the new data
                        NhanVienDTO nvdto = new NhanVienDTO();
                        nvdto.setIdNV(idNv);
                        nvdto.setHoTen(hoTen);
                        nvdto.setSdt(Integer.parseInt(sdt));
                        nvdto.setGioiTinh(gioiTinh);
                        nvdto.setNgaySinh(sqlDate);
                        nvdto.setIsDelete(1); // Set isDelete to 1

                        // Update the database

                        int result = nvBUS.update(nvdto);
                        if(result > 0){
                            JOptionPane.showMessageDialog(null, "Sửa nhân viên thành công!");

                        } else {
                            JOptionPane.showMessageDialog(null, "Sửa nhân viên thất bại!");
                        }

                }
            });
            btn_suaNhanVien.setFont(new Font("Tahoma", Font.PLAIN, 16));
            btn_suaNhanVien.setBackground(new Color(128, 255, 255));
            jP_btn.add(btn_suaNhanVien);

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
    public SuaNhanVien(int idNv){
        this(); // Call the no-argument constructor to initialize the GUI components
        this.idNv= idNv;

        NhanVienDTO nhanVienDTO = nvBUS.selectById(idNv );

        // Set the text fields with the existing data
        jTxt_hoTen.setText(nhanVienDTO.getHoTen());
        jTxt_sdt.setText(String.valueOf(nhanVienDTO.getSdt()));
        if(nhanVienDTO.getGioiTinh() == 1) {
            btn_nam.setSelected(true);
        } else {
            btn_nu.setSelected(true);
        }
        JDate_ngaySinh.setDate(nhanVienDTO.getNgaySinh());
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