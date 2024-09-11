package GUI.Dialog.PhieuNhap;

import BUS.PhieuNhapBUS;
import BUS.SanPhamBUS;
import BUS.ctSanPhamBUS;
import DTO.*;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


public class ChiTietPhieuNhap extends JDialog {

    private static final long serialVersionUID = 1L;
    private JTextField textField_maPhieuNhapCT;
    private JTextField textField_maNhanVien;
    private JTextField textField_tongTien;
    private JTable table_chitietPhieuNhap;

    private int idPN;

    public ChiTietPhieuNhap() {
        setTitle("Chi Tiết Phiếu Nhập");
        setBounds(100, 100, 970, 480);
        getContentPane().setLayout(null);

        JPanel panel = new JPanel();
        panel.setBackground(new Color(30, 144, 255));
        panel.setBounds(0, 0, 959, 107);
        getContentPane().add(panel);
        panel.setLayout(null);

        JLabel lblNewLabel_1 = new JLabel("THÔNG TIN PHIẾU NHẬP");
        lblNewLabel_1.setForeground(new Color(255, 255, 255));
        lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 23));
        lblNewLabel_1.setBounds(320, 30, 292, 39);
        panel.add(lblNewLabel_1);

        JLabel lblNewLabel_2 = new JLabel("Mã phiếu nhập");
        lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblNewLabel_2.setBounds(38, 131, 155, 31);
        getContentPane().add(lblNewLabel_2);

        textField_maPhieuNhapCT = new JTextField();
        textField_maPhieuNhapCT.setEditable(false);
        textField_maPhieuNhapCT.setBackground(new Color(207, 207, 207));
        textField_maPhieuNhapCT.setBounds(38, 159, 190, 44);
        textField_maPhieuNhapCT.setFont(new Font("Tahoma", Font.BOLD, 14));
        getContentPane().add(textField_maPhieuNhapCT);
        textField_maPhieuNhapCT.setColumns(10);

        JLabel lblNewLabel_2_1 = new JLabel("Nhân viên nhập");
        lblNewLabel_2_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblNewLabel_2_1.setBounds(381, 131, 155, 31);
        getContentPane().add(lblNewLabel_2_1);

        textField_maNhanVien = new JTextField();
        textField_maNhanVien.setEditable(false);
        textField_maNhanVien.setBackground(new Color(207, 207, 207));
        textField_maNhanVien.setColumns(10);
        textField_maNhanVien.setBounds(381, 159, 190, 44);
        textField_maNhanVien.setFont(new Font("Tahoma", Font.BOLD, 14));
        getContentPane().add(textField_maNhanVien);

        JLabel lblNewLabel_2_2 = new JLabel("Tổng tiền");
        lblNewLabel_2_2.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblNewLabel_2_2.setBounds(724, 131, 155, 31);
        getContentPane().add(lblNewLabel_2_2);

        textField_tongTien = new JTextField();
        textField_tongTien.setEditable(false);
        textField_tongTien.setColumns(10);
        textField_tongTien.setBackground(new Color(207, 207, 207));
        textField_tongTien.setBounds(724, 159, 190, 44);
        textField_tongTien.setFont(new Font("Tahoma", Font.BOLD, 14));
        getContentPane().add(textField_tongTien);

        table_chitietPhieuNhap = new JTable();
        table_chitietPhieuNhap.setRowSelectionAllowed(false);
        table_chitietPhieuNhap.setModel(new DefaultTableModel(
                new Object[][] {
                },
                new String[] {
                        "Mã sản phẩm", "Tên sản phẩm","Thời gian tạo","Ram","Rom", "Màu sắc","Đơn giá", "Số lượng"
                }
        ));

        JScrollPane scrollPane = new JScrollPane(table_chitietPhieuNhap);
        scrollPane.setBounds(10, 237, 939, 131);
        getContentPane().add(scrollPane);

        JButton btn_dong = new JButton("Đóng");
        btn_dong.setFont(new Font("Tahoma", Font.PLAIN, 20));
        btn_dong.setBounds(420, 378, 92, 39);
        getContentPane().add(btn_dong);
        btn_dong.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
            }
        });
    }
    public ChiTietPhieuNhap(int idPN){
        this();
        this.idPN = idPN;
        // xong
        PhieuNhapBUS phieuNhapBUS = new PhieuNhapBUS();
        PhieuNhapDTO phieuNhapDTO = phieuNhapBUS.getPhieuNhapById(idPN);

        textField_maPhieuNhapCT.setText(String.valueOf(phieuNhapDTO.getIdPhieuNhap()));
        textField_maNhanVien.setText(String.valueOf(phieuNhapDTO.getIdNhanVien()));
        textField_tongTien.setText(String.format("%,.0f", phieuNhapDTO.getTongTien()));

        // xong
        ArrayList<ChiTietPhieuNhapDTO> chiTietPhieuNhapList = phieuNhapBUS.getCTPhieuNhapById(idPN);

        DefaultTableModel model = (DefaultTableModel) table_chitietPhieuNhap.getModel();
        for (ChiTietPhieuNhapDTO chiTietPhieuNhap : chiTietPhieuNhapList) {
            String donGia = String.format("%.0f", chiTietPhieuNhap.getDonGia());

            SanPhamBUS sanPhamBUS = new SanPhamBUS();
            // chưa
            SanPhamDTO sanPham = sanPhamBUS.laySanPhamTheoId(chiTietPhieuNhap.getIdSanPham());

            PhieuNhapDTO phieuNhap = new PhieuNhapDTO();

            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            Date thoiGian = new Date();
            phieuNhap.setThoiGian(thoiGian);
            ctSanPhamBUS ctSanPhamBUS = new ctSanPhamBUS();
            // xong
            ctSanPhamDTO chitietSanPham = ctSanPhamBUS.timctSanPhamTheoId(chiTietPhieuNhap.getIdSanPham());
            model.addRow(new Object[]{
                    sanPham.getIdSP(),
                    sanPham.getTenSP(),
                    dateFormat.format(phieuNhap.getThoiGian()),
                    chitietSanPham.getRam(),
                    chitietSanPham.getRom(),
                    sanPham.getMauSac(),
                    donGia,
                    chiTietPhieuNhap.getSoLuong()
            });
        }
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        for (int i = 0; i < table_chitietPhieuNhap.getColumnCount(); i++) {
            table_chitietPhieuNhap.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }
    }

}
