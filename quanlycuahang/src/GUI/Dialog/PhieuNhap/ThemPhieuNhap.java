package GUI.Dialog.PhieuNhap;

import BUS.PhieuNhapBUS;
import BUS.SanPhamBUS;
import BUS.ctSanPhamBUS;
import DTO.*;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.Date;
import java.util.ArrayList;

public class ThemPhieuNhap extends JPanel {

    private static final long serialVersionUID = 1L;
    private final JTextField textField_IdPhieuNhap;
    private final JTextField textField_NhanVien;
    private JTextField textField_timKiem;
    public JTable table_chonSanPham;
    private final JTextField textField_idSanPham;
    private final JTextField textField_tenSanPham;
    private final JTextField textField_soLuong;
    private final JTextField textField_giaNhap;
    public JTable table_sanPham;
    Label lb_TongTien;
    private int idPN;
    private int idNV;
    private DefaultTableModel defaultTableModel;

    /**
     * Create the panel.
     */
    public ThemPhieuNhap() {
        setLayout(null);

        JPanel panel = new JPanel();
        panel.setBackground(new Color(238, 255, 252));
        panel.setBounds(0, 0, 1146, 650);
        add(panel);
        panel.setLayout(null);

        JPanel panel_1 = new JPanel();
        panel_1.setBackground(new Color(255, 255, 255));
        panel_1.setBounds(869, 10, 267, 630);
        panel.add(panel_1);
        panel_1.setLayout(null);

        JLabel lblNewLabel = new JLabel("Mã phiếu nhập");
        lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 17));
        lblNewLabel.setBounds(10, 10, 130, 38);
        panel_1.add(lblNewLabel);

        textField_IdPhieuNhap = new JTextField();
        textField_IdPhieuNhap.setFont(new Font("Tahoma", Font.BOLD, 14));
        textField_IdPhieuNhap.setEditable(false);
        textField_IdPhieuNhap.setBackground(new Color(233, 236, 237));
        textField_IdPhieuNhap.setBounds(10, 44, 247, 38);
        panel_1.add(textField_IdPhieuNhap);
        textField_IdPhieuNhap.setColumns(10);

        JLabel lblNhnVinNhp = new JLabel("Nhân viên nhập");
        lblNhnVinNhp.setFont(new Font("Tahoma", Font.PLAIN, 17));
        lblNhnVinNhp.setBounds(10, 100, 130, 38);
        panel_1.add(lblNhnVinNhp);

        textField_NhanVien = new JTextField();
        textField_NhanVien.setFont(new Font("Tahoma", Font.BOLD, 14));
        textField_NhanVien.setEditable(false);
        textField_NhanVien.setColumns(10);
        textField_NhanVien.setBackground(new Color(233, 236, 237));
        textField_NhanVien.setBounds(10, 136, 247, 38);
        panel_1.add(textField_NhanVien);

        lb_TongTien = new Label("Tổng tiền:");
        lb_TongTien.setFont(new Font("Tahoma", Font.PLAIN, 21));
        lb_TongTien.setBounds(10, 462, 257, 38);
        panel_1.add(lb_TongTien);

        JButton btn_nhapHang = new JButton("Nhập hàng");
        btn_nhapHang.setFont(new Font("Tahoma", Font.PLAIN, 22));
        btn_nhapHang.setBounds(61, 516, 157, 56);
        panel_1.add(btn_nhapHang);

        btn_nhapHang.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                nhapHang();
            }
        });


        JPanel panel_2 = new JPanel();
        panel_2.setBackground(new Color(255, 255, 255));
        panel_2.setBounds(10, 10, 849, 630);
        panel.add(panel_2);
        panel_2.setLayout(null);

        textField_timKiem = new JTextField(20) {
            @Override
            public void paintComponent(Graphics g) {
                super.paintComponent(g);
                if (getText().isEmpty()) {
                    g.setColor(Color.gray);
                    g.drawString("Tìm tên sản phẩm", getInsets().left, (getHeight() - getInsets().top - getInsets().bottom) / 2 + getFont().getSize() / 2);
                }
            }
        };

        textField_timKiem.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                super.keyReleased(e);
                String searchText = textField_timKiem.getText().trim();
                SanPhamBUS sanPhamBUS = new SanPhamBUS();
                ArrayList<SanPhamDTO> searchResult = sanPhamBUS.search(searchText);
                defaultTableModel.setRowCount(0);
                for (SanPhamDTO sanPham : searchResult) {
                    ctSanPhamBUS ctSanPhamBUS = new ctSanPhamBUS();
                    ctSanPhamDTO ctSanPham = ctSanPhamBUS.timctSanPhamTheoId(sanPham.getIdSP());
                    defaultTableModel.addRow(new Object[]{
                            sanPham.getIdSP(),
                            sanPham.getTenSP(),
                            sanPham.getSoLuong(),
                            sanPham.getMauSac(),
                            ctSanPham.getRam(),
                            ctSanPham.getRom()
                    });
                }
            }
        });

        textField_timKiem.setFont(new Font("Tahoma", Font.PLAIN, 15));
        textField_timKiem.setBounds(10, 10, 532, 36);
        textField_timKiem.setColumns(10);
        panel_2.add(textField_timKiem);

        table_chonSanPham = new JTable();
        table_chonSanPham.setModel(new DefaultTableModel(
                new Object[][] {
                },
                new String[] {
                        "Mã sản phẩm", "Tên sản phẩm", "Số lượng", "Màu sắc", "Ram", "Rom"
                }
        ));
        table_chonSanPham.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent e) {
                chonSanPham();
            }
        });
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        for (int i = 0; i < table_chonSanPham.getColumnCount(); i++) {
            table_chonSanPham.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }
        table_chonSanPham.getColumnModel().getColumn(1).setPreferredWidth(110);
        table_chonSanPham.getColumnModel().getColumn(1).setMinWidth(18);
        table_chonSanPham.getColumnModel().getColumn(1).setMaxWidth(200);
        JScrollPane scrollPane = new JScrollPane(table_chonSanPham);
        scrollPane.setBounds(10, 56, 532, 289);
        panel_2.add(scrollPane);

        JLabel lblNewLabel_1 = new JLabel("Mã sản phẩm");
        lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblNewLabel_1.setBounds(551, 56, 100, 27);
        panel_2.add(lblNewLabel_1);

        textField_idSanPham = new JTextField();
        textField_idSanPham.setEditable(false);
        textField_idSanPham.setFont(new Font("Tahoma", Font.BOLD, 13));
        textField_idSanPham.setBackground(new Color(233, 236, 237));
        textField_idSanPham.setBounds(551, 93, 137, 36);
        panel_2.add(textField_idSanPham);
        textField_idSanPham.setColumns(10);

        JLabel lblNewLabel_1_1 = new JLabel("Tên sản phẩm");
        lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblNewLabel_1_1.setBounds(702, 56, 116, 27);
        panel_2.add(lblNewLabel_1_1);

        textField_tenSanPham = new JTextField();
        textField_tenSanPham.setEditable(false);
        textField_tenSanPham.setFont(new Font("Tahoma", Font.BOLD, 13));
        textField_tenSanPham.setBackground(new Color(233, 236, 237));
        textField_tenSanPham.setColumns(10);
        textField_tenSanPham.setBounds(702, 93, 137, 36);
        panel_2.add(textField_tenSanPham);

        JLabel lblNewLabel_1_2 = new JLabel("Giá nhập");
        lblNewLabel_1_2.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblNewLabel_1_2.setBounds(551, 137, 100, 46);
        panel_2.add(lblNewLabel_1_2);

        textField_giaNhap = new JTextField();
        textField_giaNhap.setEditable(false);
        textField_giaNhap.setFont(new Font("Tahoma", Font.BOLD, 13));
        textField_giaNhap.setBackground(new Color(233, 236, 237));
        textField_giaNhap.setColumns(10);
        textField_giaNhap.setBounds(551, 178, 137, 36);
        panel_2.add(textField_giaNhap);

        JLabel lblNewLabel_1_2_1 = new JLabel("Số lượng");
        lblNewLabel_1_2_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblNewLabel_1_2_1.setBounds(702, 147, 100, 27);
        panel_2.add(lblNewLabel_1_2_1);

        textField_soLuong = new JTextField();
        textField_soLuong.setFont(new Font("Tahoma", Font.BOLD, 13));
        textField_soLuong.setColumns(10);
        textField_soLuong.setBounds(702, 178, 137, 36);
        panel_2.add(textField_soLuong);


        JButton btn_themSanPham = new JButton("Thêm sản phẩm");
        btn_themSanPham.setFont(new Font("Tahoma", Font.PLAIN, 16));
        btn_themSanPham.setBounds(548, 287, 149, 46);
        panel_2.add(btn_themSanPham);
        btn_themSanPham.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                themTableSanPham();
            }
        });


        JButton btn_xoaSanPham = new JButton("Xóa sản phẩm");
        btn_xoaSanPham.setFont(new Font("Tahoma", Font.PLAIN, 16));
        btn_xoaSanPham.setBounds(702, 287, 140, 46);
        panel_2.add(btn_xoaSanPham);

        btn_xoaSanPham.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                xoaSanPhamTable();
            }
        });


        table_sanPham = new JTable();
        table_sanPham.setModel(new DefaultTableModel(
                new Object[][] {
                },
                new String[] {
                        "Mã sản phẩm", "Tên sản phẩm", "Đơn giá","Màu sắc", "Ram", "Rom", "Số lượng"
                }
        ));
        JScrollPane scrollPane_1 = new JScrollPane(table_sanPham);
        scrollPane_1.setBounds(10, 402, 829, 218);
        panel_2.add(scrollPane_1);

        defaultTableModel = (DefaultTableModel) table_chonSanPham.getModel();
    }

    private void themTableSanPham(){
        int selectedRow = table_chonSanPham.getSelectedRow();
        if (selectedRow != -1) {
            String idSanPham = table_chonSanPham.getValueAt(selectedRow, 0).toString();
            String tenSanPham = table_chonSanPham.getValueAt(selectedRow, 1).toString();
            int idSP = Integer.parseInt(idSanPham);
            SanPhamBUS sanPhamBUS = new SanPhamBUS();
            // chưa
            SanPhamDTO sanPham = sanPhamBUS.laySanPhamTheoId(idSP);

            String giaNhap = String.format("%,.0f", sanPham.getGiaNhap());
            String mauSac = table_chonSanPham.getValueAt(selectedRow, 3).toString();
            String ram = table_chonSanPham.getValueAt(selectedRow, 4).toString();
            String rom = table_chonSanPham.getValueAt(selectedRow, 5).toString();
            String soLuong = textField_soLuong.getText();
            if (!soLuong.matches("\\d+") || Integer.parseInt(soLuong) <= 0) {
                JOptionPane.showMessageDialog(null, "Số lượng không hợp lệ!");
                return;
            }
            DefaultTableModel sanPhamModel = (DefaultTableModel) table_sanPham.getModel();
            Object[] rowData = {idSanPham, tenSanPham, giaNhap, mauSac, ram, rom, soLuong};
            sanPhamModel.addRow(rowData);
        }
        double tongTien = 0;
        DefaultTableModel model = (DefaultTableModel) table_sanPham.getModel();
        for (int i = 0; i < model.getRowCount(); i++) {
            double donGia = Double.parseDouble(model.getValueAt(i, 2).toString().replace(",", ""));
            int soLuong = Integer.parseInt(model.getValueAt(i, 6).toString());
            double tongTienHang = donGia * soLuong;
            tongTien += tongTienHang;
        }

        lb_TongTien.setText("Tổng tiền: " + String.format("%,.0f", tongTien));
    }
    private void chonSanPham(){
        int selectedRow = table_chonSanPham.getSelectedRow();
        if (selectedRow != -1) {
            String idSanPham = table_chonSanPham.getValueAt(selectedRow, 0).toString();
            String tenSanPham = table_chonSanPham.getValueAt(selectedRow, 1).toString();

            textField_idSanPham.setText(idSanPham);
            textField_tenSanPham.setText(tenSanPham);

            int idSP = Integer.parseInt(idSanPham);
            SanPhamBUS sanPhamBUS = new SanPhamBUS();
            // chưa
            SanPhamDTO sanPham = sanPhamBUS.laySanPhamTheoId(idSP);

            String giaNhap = String.format("%,.0f", sanPham.getGiaNhap());
            textField_giaNhap.setText(String.valueOf(giaNhap));
        }
    }
    private void xoaSanPhamTable(){
        int selectedRow = table_sanPham.getSelectedRow();
        if (selectedRow != -1) {
            int choice = JOptionPane.showConfirmDialog(null, "Bạn muốn xóa?", "Xác nhận", JOptionPane.YES_NO_OPTION);
            if (choice == JOptionPane.YES_OPTION) {
                DefaultTableModel model = (DefaultTableModel) table_sanPham.getModel();
                model.removeRow(selectedRow);

                double tongTien = 0;
                for (int i = 0; i < model.getRowCount(); i++) {
                    double donGia = Double.parseDouble(model.getValueAt(i, 2).toString().replace(",", ""));
                    int soLuongHang = Integer.parseInt(model.getValueAt(i, 6).toString());
                    double tongTienHang = donGia * soLuongHang;
                    tongTien += tongTienHang;
                }
                lb_TongTien.setText("Tổng tiền: " + String.format("%,.0f", tongTien));
            }
        } else {
            JOptionPane.showMessageDialog(null, "Vui lòng chọn một hàng để xóa.");
        }
    }
    private void nhapHang() {
        String idPhieuNhap = textField_IdPhieuNhap.getText();
        String idNhanVien = textField_NhanVien.getText();
        String tongTien = lb_TongTien.getText().replaceAll("\\D", "");
        Date thoiGian = new Date();
        PhieuNhapDTO pnDTO = new PhieuNhapDTO();
        pnDTO.setThoiGian(thoiGian);

        if (table_sanPham.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Vui lòng chọn ít nhất một sản phẩm để nhập.");
            return;
        }


        DefaultTableModel model = (DefaultTableModel) table_sanPham.getModel();
        for (int i = 0; i < model.getRowCount(); i++) {
            String soLuong = model.getValueAt(i, 6).toString();
            String donGia = model.getValueAt(i, 2).toString().replaceAll(",", "");
            String idSanPham = model.getValueAt(i, 0).toString();

            ChiTietPhieuNhapDTO ctPN = new ChiTietPhieuNhapDTO();
            ctPN.setIdPhieuNhap(Integer.parseInt(idPhieuNhap));
            ctPN.setIdSanPham(Integer.parseInt(idSanPham));
            ctPN.setSoLuong(Integer.parseInt(soLuong));
            ctPN.setDonGia((float) Double.parseDouble(donGia));
            ctPN.setThanhTien(Double.parseDouble(donGia) * Integer.parseInt(soLuong));
            ArrayList<ChiTietPhieuNhapDTO> listCTPN = new ArrayList<>();
            listCTPN.add(ctPN);
            // xong
            PhieuNhapBUS phieuNhapBUS = new PhieuNhapBUS();
            phieuNhapBUS.add(listCTPN);

            SanPhamBUS sanPhamBUS = new SanPhamBUS();
            // xong
            sanPhamBUS.capNhapSoLuongKhiNhapHang(Integer.parseInt(idSanPham), Integer.parseInt(soLuong));
        }
        // xong
        PhieuNhapBUS phieuNhapBUS = new PhieuNhapBUS();
        phieuNhapBUS.getupdateTongTien(Integer.parseInt(idPhieuNhap), Integer.parseInt(tongTien));
        // xong
        lb_TongTien.setText("Tổng tiền: 0");
        phieuNhapBUS.getupdateTongTien(Integer.parseInt(idPhieuNhap),0);
        model.setRowCount(0);
        JOptionPane.showMessageDialog(null, "Nhập hàng thành công.");
    }

    public ThemPhieuNhap(int idPN, int idNV){
        this();
        this.idPN = idPN;
        this.idNV = idNV;
        textField_IdPhieuNhap.setText(String.valueOf(idPN));
        textField_NhanVien.setText(String.valueOf(idNV));
        SanPhamBUS sanPhamBUS = new SanPhamBUS();
        ArrayList<SanPhamDTO> products = sanPhamBUS.layDanhSachTatCaChiTietSanPham();
        DefaultTableModel chonSPModel = (DefaultTableModel) table_chonSanPham.getModel();
        for (SanPhamDTO product : products) {
            // xong
            ctSanPhamBUS ctSanPhamBUS = new ctSanPhamBUS();
            ctSanPhamDTO ctSanPham = ctSanPhamBUS.timctSanPhamTheoId(product.getIdSP());
            Object[] rowData = {product.getIdSP(), product.getTenSP(), product.getSoLuong(), product.getMauSac(), ctSanPham.getRam(), ctSanPham.getRom() };
            chonSPModel.addRow(rowData);
        }
    }
}
