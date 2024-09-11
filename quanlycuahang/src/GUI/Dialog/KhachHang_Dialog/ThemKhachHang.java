package GUI.Dialog.KhachHang_Dialog;

import BUS.KhachHangBUS;
import DTO.KhachHangDTO;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class ThemKhachHang extends JDialog {
    private static final long serialVersionUID = 1L;
    private final JPanel hi = new JPanel();
    private JTextField text_TenKH;
    private JTextField text_diaChi;
    private JTextField text_sdt;
    private KhachHangBUS khachHangBUS;


    public ThemKhachHang() {
        setTitle("Thêm khách hàng");
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

            JLabel jL_themKhachHang = new JLabel("THÊM KHÁCH HÀNG");
            jL_themKhachHang.setBounds(141, 10, 206, 32);
            jL_themKhachHang.setBackground(new Color(128, 255, 255));
            jL_themKhachHang.setFont(new Font("Dialog", Font.BOLD, 24));
            jL_themKhachHang.setHorizontalAlignment(SwingConstants.CENTER);
            jP_header.add(jL_themKhachHang);

            JPanel jP_center = new JPanel();
            jP_center.setBounds(22, 80, 457, 391);
            getContentPane().add(jP_center);
            jP_center.setLayout(new GridLayout(8, 1, 0, 0));

            JLabel jL_tenKhachHang = new JLabel("Tên Khách Hàng:");
            jL_tenKhachHang.setFont(new Font("Tahoma", Font.PLAIN, 16));
            jP_center.add(jL_tenKhachHang);

            text_TenKH = new JTextField();
            jP_center.add(text_TenKH);
            text_TenKH.setColumns(10);

            JLabel jL_diaChi = new JLabel("Địa Chỉ:");
            jL_diaChi.setFont(new Font("Tahoma", Font.PLAIN, 16));
            jP_center.add(jL_diaChi);

            text_diaChi = new JTextField();
            jP_center.add(text_diaChi);
            text_diaChi.setColumns(10);

            JLabel jL_sdt = new JLabel("Số Điện Thoại:");
            jL_sdt.setFont(new Font("Tahoma", Font.PLAIN, 16));
            jP_center.add(jL_sdt);

            text_sdt = new JTextField();
            jP_center.add(text_sdt);
            text_sdt.setColumns(10);


            JPanel jP_btn = new JPanel();
            jP_btn.setBounds(66, 481, 354, 59);
            getContentPane().add(jP_btn);
            jP_btn.setLayout(new GridLayout(1, 2, 5, 5));

            JButton btn_themNguoiDung = new JButton("Thêm Khách Hàng");
            btn_themNguoiDung.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    // Lấy dữ liệu từ các trường nhập liệu
//
                    String hoTen = text_TenKH.getText();
                    String diaChi = text_diaChi.getText();
                    String sdt = text_sdt.getText();


                    // Kiểm tra sdt có phải là số hay không trước khi chuyển đổi
                    if (!sdt.matches("\\d+")) {
                        JOptionPane.showMessageDialog(null, "Số điện thoại không hợp lệ!");
                        return;
                    }

                    // Tạo một đối tượng KhachHangDTO mới với dữ liệu này
                    KhachHangDTO khachHangDTO = new KhachHangDTO();
                    khachHangDTO.setTenKhachHang(hoTen);
                    khachHangDTO.setDiaChiKhachHang(diaChi);
                    khachHangDTO.setSdtKhachHang(Integer.parseInt(sdt));

                    // Thêm khách hàng vào cơ sở dữ liệu
                    khachHangBUS = new KhachHangBUS();
                    boolean ketQua = khachHangBUS.add(khachHangDTO);
                    if (ketQua) {
                        JOptionPane.showMessageDialog(null, "Thêm khách hàng thành công!");
                    } else {
                        JOptionPane.showMessageDialog(null, "Thêm khách hàng thất bại!");
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

}