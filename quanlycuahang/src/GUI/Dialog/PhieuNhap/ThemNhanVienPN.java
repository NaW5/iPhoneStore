package GUI.Dialog.PhieuNhap;

import BUS.PhieuNhapBUS;
import DTO.PhieuNhapDTO;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ThemNhanVienPN extends JDialog {

    private static final long serialVersionUID = 1L;
    private final JPanel contentPanel = new JPanel();
    public JTextField textField_maNhanVien;
    private PhieuNhapBUS phieuNhapBUS;

    public ThemNhanVienPN() {
        setTitle("Thêm nhân viên và mã phiếu nhập");
        setBounds(100, 100, 386, 213);
        getContentPane().setLayout(new BorderLayout());
        contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        getContentPane().add(contentPanel, BorderLayout.CENTER);
        contentPanel.setLayout(null);

        JLabel lblMNhanVien = new JLabel("Mã nhân viên");
        lblMNhanVien.setFont(new Font("Tahoma", Font.PLAIN, 18));
        lblMNhanVien.setBounds(125, 10, 121, 28);
        contentPanel.add(lblMNhanVien);

        textField_maNhanVien = new JTextField();
        textField_maNhanVien.setFont(new Font("Tahoma", Font.PLAIN, 15));
        textField_maNhanVien.setColumns(10);
        textField_maNhanVien.setBounds(44, 48, 287, 40);
        contentPanel.add(textField_maNhanVien);

        JButton btn_Tao = new JButton("Tạo");
        btn_Tao.setFont(new Font("Tahoma", Font.PLAIN, 18));
        btn_Tao.setBounds(125, 108, 121, 41);
        contentPanel.add(btn_Tao);
        btn_Tao.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Tao();
            }
        });

        // Khởi tạo đối tượng PhieuNhapBUS
        phieuNhapBUS = new PhieuNhapBUS();
    }

    public void Tao(){
        String idNhanVien = textField_maNhanVien.getText();

        // Kiểm tra xem mã nhân viên có hợp lệ không
        if (!idNhanVien.matches("^330\\d{2}$")) {
            JOptionPane.showMessageDialog(null, "Mã nhân viên không hợp lệ!");
            return;
        }

        // Tạo một đối tượng PhieuNhapDTO mới với dữ liệu này
        PhieuNhapDTO phieuNhapDTO = new PhieuNhapDTO();
        java.sql.Date thoiGian = new java.sql.Date(new java.util.Date().getTime());

        phieuNhapDTO.setTongTien(0);
        phieuNhapDTO.setThoiGian(thoiGian);
        phieuNhapDTO.setIdNhanVien(Integer.parseInt(idNhanVien));

        PhieuNhapBUS phieuNhap = new PhieuNhapBUS();
        // Thêm phiếu nhập vào cơ sở dữ liệu
        boolean ketQua = phieuNhap.addPhieuNhap(phieuNhapDTO);
        if (ketQua) {
            JOptionPane.showMessageDialog(null, "Thêm phiếu nhập thành công!");
        } else {
            JOptionPane.showMessageDialog(null, "Thêm phiếu nhập thất bại!");
        }
    }
}

