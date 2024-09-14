package GUI.Dialog.PhieuNhap;

import BUS.*;
import DTO.ChiTietPhieuNhapDTO;
import DTO.PhieuNhapDTO;
import DTO.SanPhamDTO;
import DTO.ctSanPhamDTO;
import DTO.IMEIDTO;
import GUI.Dialog.SanPhamDialog.themSanPham_Dialog;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Date;

public class ThemPhieuNhap extends JPanel {

    private static final long serialVersionUID = 1L;
    private final JTextField textField_IdPhieuNhap;
    private final JTextField textField_NhanVien;
    private JTextField textField_timKiem;
    public JTable table_chonSanPham;
    private final JTextField textField_idSanPham;
    private final JTextField textField_tenSanPham;
    private final JTextField textField_giaNhap;
    public JTable table_sanPham;
    Label lb_TongTien;
    private DefaultTableModel defaultTableModel;
    private JComboBox<String> comboBox_IMEI;

    /**
     * Create the panel.
     */
    public ThemPhieuNhap(int idPN, int idNV) {
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
        textField_IdPhieuNhap.setText(String.valueOf(idPN));
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
        textField_NhanVien.setText(String.valueOf(idNV));
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
        textField_giaNhap.setEditable(true);
        textField_giaNhap.setFont(new Font("Tahoma", Font.BOLD, 13));
        textField_giaNhap.setBackground(new Color(233, 236, 237));
        textField_giaNhap.setColumns(10);
        textField_giaNhap.setBounds(551, 178, 137, 36);
        panel_2.add(textField_giaNhap);

        JLabel lblNewLabel_1_2_1 = new JLabel("IMEI");
        lblNewLabel_1_2_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblNewLabel_1_2_1.setBounds(702, 147, 100, 27);
        panel_2.add(lblNewLabel_1_2_1);

        comboBox_IMEI = new JComboBox<>();
        comboBox_IMEI.setBounds(702, 178, 137, 36);
        panel_2.add(comboBox_IMEI);

        JButton btn_themIMEI = new JButton("Thêm IMEI");
        btn_themIMEI.setFont(new Font("Tahoma", Font.PLAIN, 16));
        btn_themIMEI.setBounds(702, 220, 137, 36);
        panel_2.add(btn_themIMEI);
        btn_themIMEI.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //kiểm tra textField_idSanPham có rống ko
                if (textField_idSanPham.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Vui lòng chọn sản phẩm!");
                    return;
                }
                int idSP = Integer.parseInt(textField_idSanPham.getText());
                themSanPham_Dialog themSanPham = new themSanPham_Dialog(idSP);
                themSanPham.setSize(1200, 500);
                themSanPham.setVisible(true);
            }
        });

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
                        "Mã sản phẩm", "Tên sản phẩm", "Đơn giá","Màu sắc", "Ram", "Rom", "IMEI"
                }
        ));
        JScrollPane scrollPane_1 = new JScrollPane(table_sanPham);
        scrollPane_1.setBounds(10, 402, 829, 218);
        panel_2.add(scrollPane_1);

        defaultTableModel = (DefaultTableModel) table_chonSanPham.getModel();
        // Load all products of this phieuNhap to the table
        loadAllChiTietByidPhieuNhap(Integer.parseInt(textField_IdPhieuNhap.getText()));
        SanPhamBUS sanPhamBUS = new SanPhamBUS();
        ArrayList<SanPhamDTO> products = sanPhamBUS.layDanhSachTatCaChiTietSanPham();
        DefaultTableModel chonSPModel = (DefaultTableModel) table_chonSanPham.getModel();
        for (SanPhamDTO product : products) {
            ctSanPhamBUS ctSanPhamBUS = new ctSanPhamBUS();
            ctSanPhamDTO ctSanPham = ctSanPhamBUS.timctSanPhamTheoId(product.getIdSP());
            Object[] rowData = {product.getIdSP(), product.getTenSP(), product.getSoLuong(), product.getMauSac(), ctSanPham.getRam(), ctSanPham.getRom() };
            chonSPModel.addRow(rowData);
        }
    }

    private void loadAllChiTietByidPhieuNhap(int inPN) {
        PhieuNhapBUS phieuNhapBUS = new PhieuNhapBUS();
        ArrayList<ChiTietPhieuNhapDTO> ctpnList = phieuNhapBUS.layDanhSachChiTietPhieuNhapTheoIdPhieuNhap(inPN);
        DefaultTableModel model = (DefaultTableModel) table_sanPham.getModel();
        for (ChiTietPhieuNhapDTO ctpn : ctpnList) {
            SanPhamBUS sanPhamBUS = new SanPhamBUS();
            SanPhamDTO sanPham = sanPhamBUS.laySanPhamTheoId(ctpn.getIdSanPham());
            ctSanPhamBUS ctSanPhamBUS = new ctSanPhamBUS();
            ctSanPhamDTO ctSanPham = ctSanPhamBUS.timctSanPhamTheoId(sanPham.getIdSP());
                int maIMEI = ctpn.getIMEI();
                Object[] rowData = {sanPham.getIdSP(), sanPham.getTenSP(), String.format("%,.0f",sanPham.getGiaNhap()), sanPham.getMauSac(), ctSanPham.getRam(), ctSanPham.getRom(), maIMEI};
                model.addRow(rowData);
            }

    }

    private void themTableSanPham(){
        int selectedRow = table_chonSanPham.getSelectedRow();
        if (selectedRow != -1) {
            String idSanPham = table_chonSanPham.getValueAt(selectedRow, 0).toString();
            String tenSanPham = table_chonSanPham.getValueAt(selectedRow, 1).toString();

            String giaNhap = textField_giaNhap.getText();
            String mauSac = table_chonSanPham.getValueAt(selectedRow, 3).toString();
            String ram = table_chonSanPham.getValueAt(selectedRow, 4).toString();
            String rom = table_chonSanPham.getValueAt(selectedRow, 5).toString();
            String imei = (String) comboBox_IMEI.getSelectedItem();
            if (imei == null || imei.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Vui lòng chọn IMEI!");
                return;
            }
            DefaultTableModel sanPhamModel = (DefaultTableModel) table_sanPham.getModel();
            Object[] rowData = {idSanPham, tenSanPham, giaNhap, mauSac, ram, rom, imei};
            //kiểm tra imei có trùng với các imei cũ đang có ở trong bảng không kể cả những imei đã thêm trước đó?
            ctPhieuNhapBUS ctpnBUS = new ctPhieuNhapBUS();
            if (kiemTraTonTaiIMEITrongBang(imei)) {
                JOptionPane.showMessageDialog(null, "IMEI đã tồn tại!");
                return;
            }
            sanPhamModel.addRow(rowData);
        }
        double tongTien = 0.0;
        DefaultTableModel model = (DefaultTableModel) table_sanPham.getModel();
        for (int i = 0; i < model.getRowCount(); i++) {
            try {
                double donGia = Double.parseDouble(model.getValueAt(i, 2).toString().replace(",", "").replace(".", ""));
                int soLuong = 1; // Each IMEI represents one unit
                double tongTienHang = donGia * soLuong;
                tongTien += tongTienHang;
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Giá nhập không hợp lệ!");
            }
        }

        lb_TongTien.setText("Tổng tiền: " + String.format("%,.0f", tongTien));
    }

    private boolean kiemTraTonTaiIMEITrongBang(String imei) {
        DefaultTableModel model = (DefaultTableModel) table_sanPham.getModel();
        for (int i = 0; i < model.getRowCount(); i++) {
            if (model.getValueAt(i, 6).toString().equals(imei)) {
                return true;
            }
        }
        return false;
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
            SanPhamDTO sanPham = sanPhamBUS.laySanPhamTheoId(idSP);

            String giaNhap = String.format("%,.0f", sanPham.getGiaNhap());
            textField_giaNhap.setText(String.valueOf(giaNhap));

            // Load IMEI codes for the selected product
            IMEIBUS imeiBUS = new IMEIBUS();
            ArrayList<IMEIDTO> imeiList = imeiBUS.layDanhSachIMEITheoSanPhamChuaNhap(idSP, Integer.parseInt(textField_IdPhieuNhap.getText()));
            comboBox_IMEI.removeAllItems();
            for (IMEIDTO imei : imeiList) {
                comboBox_IMEI.addItem(String.valueOf(imei.getMaIMEI()));
            }
        }
    }

    private void xoaSanPhamTable() {
        int selectedRow = table_sanPham.getSelectedRow();
        if (selectedRow != -1) {
            int choice = JOptionPane.showConfirmDialog(null, "Bạn muốn xóa?", "Xác nhận", JOptionPane.YES_NO_OPTION);
            if (choice == JOptionPane.YES_OPTION) {
                DefaultTableModel model = (DefaultTableModel) table_sanPham.getModel();
                String imei = model.getValueAt(selectedRow, 6).toString();
                model.removeRow(selectedRow);
                IMEIBUS imeiBUS = new IMEIBUS();
                imeiBUS.xoaIMEI(imei);
                ctPhieuNhapBUS ctpnBUS = new ctPhieuNhapBUS();
                ctpnBUS.xoaChiTietPhieuNhapByIMEI(Integer.parseInt(imei));
                double tongTien = 0.0;
                for (int i = 0; i < model.getRowCount(); i++) {
                    double donGia = Double.parseDouble(model.getValueAt(i, 2).toString().replace(",", "").replace(".", ""));
                    tongTien += donGia;
                }
                lb_TongTien.setText("Tổng tiền: " + String.format("%,.0f", tongTien));
                PhieuNhapBUS phieuNhapBUS = new PhieuNhapBUS();
                int idPhieuNhap = Integer.parseInt(textField_IdPhieuNhap.getText());
                phieuNhapBUS.updateTongTienBy(idPhieuNhap, tongTien);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Vui lòng chọn một sản phẩm để xóa", "Thông báo", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void nhapHang() {
        if (table_sanPham.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Vui lòng chọn ít nhất một sản phẩm để nhập.");
            return;
        }
        String idPhieuNhap = textField_IdPhieuNhap.getText();
        String idNhanVien = textField_NhanVien.getText();
        String tongTien = lb_TongTien.getText().replaceAll("\\D", "");
        Date thoiGian = new Date();
        PhieuNhapDTO pnDTO = new PhieuNhapDTO();
        pnDTO.setIdPhieuNhap(Integer.parseInt(idPhieuNhap));
        pnDTO.setThoiGian(thoiGian);
        pnDTO.setTongTien(Integer.parseInt(tongTien));
        pnDTO.setIdNhanVien(Integer.parseInt(idNhanVien));
        PhieuNhapBUS phieuNhapBUS = new PhieuNhapBUS();

        // If this idPhieuNhap does not exist in the database, add it
        if (!phieuNhapBUS.kiemTraTonTaiIdPhieuNhap(Integer.parseInt(idPhieuNhap))) {
            phieuNhapBUS.addPhieuNhap(pnDTO);
            ctPhieuNhapBUS ctpnBUS = new ctPhieuNhapBUS();
            int soLuongSanPham = table_sanPham.getRowCount();
            for (int i = 0; i < soLuongSanPham; i++) {
                String idSanPham = table_sanPham.getValueAt(i, 0).toString();
                String giaNhap = table_sanPham.getValueAt(i, 2).toString().replace(",", "");
                String tongTienct = giaNhap;
                String imei = table_sanPham.getValueAt(i, 6).toString();
                ctpnBUS.themChiTietPhieuNhap(Integer.parseInt(imei), Integer.parseInt(giaNhap), Integer.parseInt(tongTienct), Integer.parseInt(idPhieuNhap), Integer.parseInt(idSanPham));
                IMEIBUS imeiBUS = new IMEIBUS();
                imeiBUS.updateIdPhieuNhap(Integer.parseInt(imei), Integer.parseInt(idPhieuNhap));
            }
        }
        else {
            ctPhieuNhapBUS ctpnBUS = new ctPhieuNhapBUS();
            ctpnBUS.xoaChiTietPhieuNhapByidPhieuNhap(Integer.parseInt(idPhieuNhap));
            phieuNhapBUS.updateTongTienBy(Integer.parseInt(idPhieuNhap), Integer.parseInt(tongTien));
            int soLuongSanPham = table_sanPham.getRowCount();
            for (int i = 0; i < soLuongSanPham; i++) {
                String idSanPham = table_sanPham.getValueAt(i, 0).toString();
                String giaNhap = table_sanPham.getValueAt(i, 2).toString().replace(",", "");
                String tongTienct = giaNhap;
                String imei = table_sanPham.getValueAt(i, 6).toString();
                ctpnBUS.themChiTietPhieuNhap(Integer.parseInt(imei), Integer.parseInt(giaNhap), Integer.parseInt(tongTienct), Integer.parseInt(idPhieuNhap), Integer.parseInt(idSanPham));
                IMEIBUS imeiBUS = new IMEIBUS();
                imeiBUS.updateIdPhieuNhap(Integer.parseInt(imei), Integer.parseInt(idPhieuNhap));
            }
        }
        JOptionPane.showMessageDialog(null, "Nhập hàng thành công!");
        updateSoLuongSanPham();
    }

    private void updateSoLuongSanPham() {
        SanPhamBUS sanPhamBUS = new SanPhamBUS();
        ArrayList<SanPhamDTO> sanPhamList = sanPhamBUS.layDanhSachSanPham();
        for (SanPhamDTO sanPham : sanPhamList) {
            int soLuongTrongCTPN = sanPhamBUS.laySoLuongSanPhamTrongCTPN(sanPham.getIdSP());
            sanPhamBUS.capNhatSoLuongTon(sanPham.getIdSP(), soLuongTrongCTPN);
        }
    }
}