package GUI.JPanel_QuanLyCuaHangDienThoai;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import java.awt.*;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.DefaultComboBoxModel;
import javax.swing.table.DefaultTableModel;

import BUS.SanPhamBUS;

import DTO.SanPhamDTO;
import GUI.Dialog.SanPhamDialog.suaSanPham_Dialog;
import GUI.Dialog.SanPhamDialog.themSanPham_Dialog;
import GUI.Dialog.SanPhamDialog.xemDanhSachImeiSanPham_Dialog;
import GUI.Dialog.SanPhamDialog.xemthongtinSanPham_Dialog;
import GUI.Dialog.SanPhamDialog.xoaSanPham_Dialog;

import java.text.Normalizer;
import javax.swing.JTextField;

public class SanPhamGUI extends JPanel {

    private static final long serialVersionUID = 1L;
    private JTable table_SP;
    private JTextField textField_timkiem;
    DefaultTableModel tblModel;
    public SanPhamBUS spBUS = new SanPhamBUS();

    public static String removeDiacriticsAndSpaces(String str) {
        str = Normalizer.normalize(str, Normalizer.Form.NFD);
        str = str.replaceAll("\\p{InCombiningDiacriticalMarks}+", "");
        str = str.replaceAll("\\s+", ""); // Loại bỏ các khoảng trắng
        return str;
    }

    public void loadDataTalbe() {
        ArrayList<SanPhamDTO> result = spBUS.layDanhSachSanPham();
        System.out.println("Number of records retrieved: " + result.size());
        tblModel.setRowCount(0); // Clear existing data
        for (SanPhamDTO sp : result) {
            tblModel.addRow(new Object[]{sp.getIdSP(), sp.getTenSP(), sp.getSoLuong(), sp.getMauSac()});
        }
    }

    public void loadDataTalbe(String t) {
        String str = removeDiacriticsAndSpaces(t.toLowerCase());
        if (str.equalsIgnoreCase("tatca")) {
            ArrayList<SanPhamDTO> result = spBUS.timKiemSanPhamTheoDieuKien("tenSP LIKE '%%'");
            System.out.println("Number of records retrieved: " + result.size());
            tblModel.setRowCount(0); // Clear existing data
            for (SanPhamDTO sp : result) {
                tblModel.addRow(new Object[]{sp.getIdSP(), sp.getTenSP(), sp.getSoLuong(), sp.getMauSac()});
            }
        }
        ArrayList<SanPhamDTO> result = spBUS.timKiemSanPhamTheoDieuKien("tenSP = '" + t + "'");
        System.out.println("Number of records retrieved: " + result.size());
        tblModel.setRowCount(0); // Clear existing data
        for (SanPhamDTO sp : result) {
            tblModel.addRow(new Object[]{sp.getIdSP(), sp.getTenSP(), sp.getSoLuong(), sp.getMauSac()});
        }
        System.out.println("tatca".equalsIgnoreCase(str));
    }

    public SanPhamGUI() {
        String[] columnNames = {"Mã SP", "Tên sản phẩm", "Số lượng tồn", "Màu sắc"};
        tblModel = new DefaultTableModel(columnNames, 0);
        setLayout(null);
        loadDataTalbe();
        JPanel panel_SanPham = new JPanel();
        panel_SanPham.setBounds(0, 0, 987, 595);
        add(panel_SanPham, BorderLayout.NORTH);
        panel_SanPham.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
        add(panel_SanPham);
        GridBagLayout gbl_panel_SanPham = new GridBagLayout();
        gbl_panel_SanPham.columnWidths = new int[]{104, 123, 129, 126, 129, 116, 189, 0};
        gbl_panel_SanPham.rowHeights = new int[]{68, 456, 0};
        gbl_panel_SanPham.columnWeights = new double[]{1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, Double.MIN_VALUE};
        gbl_panel_SanPham.rowWeights = new double[]{1.0, 1.0, Double.MIN_VALUE};
        panel_SanPham.setLayout(gbl_panel_SanPham);

        JButton btn_them = new JButton("Thêm");
        btn_them.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int selectedRow = table_SP.getSelectedRow();
                if (selectedRow != -1) {
                    DefaultTableModel model = (DefaultTableModel) table_SP.getModel();
                    int idSP = (int) model.getValueAt(selectedRow, 0);
                    themSanPham_Dialog spdialog = new themSanPham_Dialog(idSP);
                    spdialog.setSize(1200, 500);
                    spdialog.setVisible(true);
                } else {
                    themSanPham_Dialog spdialog = new themSanPham_Dialog();
                    spdialog.setSize(1200, 500);
                    spdialog.setVisible(true);
                }

            }
        });
        btn_them.setForeground(new Color(0, 0, 0));
        btn_them.setBackground(new Color(255, 255, 255));
        GridBagConstraints gbc_btn_them = new GridBagConstraints();
        gbc_btn_them.fill = GridBagConstraints.HORIZONTAL;
        gbc_btn_them.insets = new Insets(0, 0, 5, 5);
        gbc_btn_them.gridx = 0;
        gbc_btn_them.gridy = 0;
        panel_SanPham.add(btn_them, gbc_btn_them);
        btn_them.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().createImage(SanPhamGUI.class.getResource("icon_them.png"))));


        JButton btn_sua = new JButton("Sửa");
        btn_sua.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int selectedRow = table_SP.getSelectedRow();
                if (selectedRow != -1) {
                    DefaultTableModel model = (DefaultTableModel) table_SP.getModel();
                    int idSP = (int) model.getValueAt(selectedRow, 0);
                    suaSanPham_Dialog spdialog = new suaSanPham_Dialog(idSP);
                    spdialog.setSize(1200, 500);
                    spdialog.setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(null, "Vui lòng chọn sản phẩm cần sửa!", "Cảnh báo", JOptionPane.WARNING_MESSAGE);
                }
            }
        });
        GridBagConstraints gbc_btn_sua = new GridBagConstraints();
        gbc_btn_sua.fill = GridBagConstraints.HORIZONTAL;
        gbc_btn_sua.insets = new Insets(0, 0, 5, 5);
        gbc_btn_sua.gridx = 1;
        gbc_btn_sua.gridy = 0;
        panel_SanPham.add(btn_sua, gbc_btn_sua);
        btn_sua.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().createImage(SanPhamGUI.class.getResource("icon_sua.png"))));


        JButton btn_xoa = new JButton("Xóa");
        btn_xoa.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int selectedRow = table_SP.getSelectedRow();
                if (selectedRow != -1) {
                    DefaultTableModel model = (DefaultTableModel) table_SP.getModel();
                    int idSP = (int) model.getValueAt(selectedRow, 0);
                    xoaSanPham_Dialog spdialog = new xoaSanPham_Dialog(idSP);
                    spdialog.setSize(500, 300);
                    spdialog.setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(null, "Vui lòng chọn sản phẩm cần xóa!", "Cảnh báo", JOptionPane.WARNING_MESSAGE);
                }
            }
        });
        GridBagConstraints gbc_btn_xoa = new GridBagConstraints();
        gbc_btn_xoa.fill = GridBagConstraints.HORIZONTAL;
        gbc_btn_xoa.insets = new Insets(0, 0, 5, 5);
        gbc_btn_xoa.gridx = 2;
        gbc_btn_xoa.gridy = 0;
        panel_SanPham.add(btn_xoa, gbc_btn_xoa);
        btn_xoa.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().createImage(SanPhamGUI.class.getResource("icon_xoa.png"))));

        JButton btn_chitiet = new JButton("Chi tiết");
        btn_chitiet.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int selectedRow = table_SP.getSelectedRow();
                if (selectedRow != -1) {
                    DefaultTableModel model = (DefaultTableModel) table_SP.getModel();
                    int idSP = (int) model.getValueAt(selectedRow, 0);
                    xemthongtinSanPham_Dialog spdialog = new xemthongtinSanPham_Dialog(idSP);
                    spdialog.setSize(1200, 500);
                    spdialog.setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(null, "Vui lòng chọn sản phẩm cần xem!", "Cảnh báo", JOptionPane.WARNING_MESSAGE);
                }
            }
        });
        btn_chitiet.setIcon(new ImageIcon(SanPhamGUI.class.getResource("/GUI/JPanel_QuanLyCuaHangDienThoai/icon_info.png")));
        GridBagConstraints gbc_btn_chitiet = new GridBagConstraints();
        gbc_btn_chitiet.fill = GridBagConstraints.HORIZONTAL;
        gbc_btn_chitiet.insets = new Insets(0, 0, 5, 5);
        gbc_btn_chitiet.gridx = 3;
        gbc_btn_chitiet.gridy = 0;
        panel_SanPham.add(btn_chitiet, gbc_btn_chitiet);

        JButton btn_xemDS = new JButton("Xem DS");
        btn_xemDS.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int selectedRow = table_SP.getSelectedRow();
                if (selectedRow != -1) {
                    DefaultTableModel model = (DefaultTableModel) table_SP.getModel();
                    int idSP = (int) model.getValueAt(selectedRow, 0);
                    xemDanhSachImeiSanPham_Dialog spdialog = new xemDanhSachImeiSanPham_Dialog(idSP);
                    spdialog.setSize(800, 600);
                    spdialog.setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(null, "Vui lòng chọn sản phẩm cần xem!", "Cảnh báo", JOptionPane.WARNING_MESSAGE);
                }
            }
        });
        btn_xemDS.setIcon(new ImageIcon(SanPhamGUI.class.getResource("/GUI/JPanel_QuanLyCuaHangDienThoai/icon_xemDS.png")));
        GridBagConstraints gbc_btn_xemDS = new GridBagConstraints();
        gbc_btn_xemDS.fill = GridBagConstraints.HORIZONTAL;
        gbc_btn_xemDS.insets = new Insets(0, 0, 5, 5);
        gbc_btn_xemDS.gridx = 4;
        gbc_btn_xemDS.gridy = 0;
        panel_SanPham.add(btn_xemDS, gbc_btn_xemDS);

        JComboBox comboBox_loc = new JComboBox();
        comboBox_loc.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String selectedValue = (String) comboBox_loc.getSelectedItem();
                loadDataTalbe(selectedValue);
            }
        });
        comboBox_loc.setModel(new DefaultComboBoxModel(new String[]{"Tất cả", "IPhone X", "IPhone XR", "IPhone XS", "IPhone XS Max", "IPhone 11", "IPhone 11 Pro", "IPhone 11 Pro Max", "IPhone 12", "IPhone 12 Pro", "IPhone 12 Pro Max"}));
        GridBagConstraints gbc_comboBox_loc = new GridBagConstraints();
        gbc_comboBox_loc.insets = new Insets(0, 0, 5, 5);
        gbc_comboBox_loc.fill = GridBagConstraints.HORIZONTAL;
        gbc_comboBox_loc.gridx = 5;
        gbc_comboBox_loc.gridy = 0;
        panel_SanPham.add(comboBox_loc, gbc_comboBox_loc);

        textField_timkiem = new JTextField();
        textField_timkiem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String t = textField_timkiem.getText();
                ArrayList<SanPhamDTO> result = spBUS.timKiemSanPhamTheoDieuKien("tenSP LIKE '%" + t + "%'");
                System.out.println("Number of records retrieved: " + result.size());
                tblModel.setRowCount(0); // Clear existing data
                for (SanPhamDTO sp : result) {
                    tblModel.addRow(new Object[]{sp.getIdSP(), sp.getTenSP(), sp.getSoLuong(), sp.getMauSac()});
                }
            }
        });
        GridBagConstraints gbc_textField_timkiem = new GridBagConstraints();
        gbc_textField_timkiem.insets = new Insets(0, 0, 5, 0);
        gbc_textField_timkiem.fill = GridBagConstraints.HORIZONTAL;
        gbc_textField_timkiem.gridx = 6;
        gbc_textField_timkiem.gridy = 0;
        panel_SanPham.add(textField_timkiem, gbc_textField_timkiem);
        textField_timkiem.setColumns(10);

        table_SP = new JTable(tblModel);
        JScrollPane scrollPane = new JScrollPane(table_SP); // Tạo JScrollPane và đặt table_SP vào đó
        table_SP.getTableHeader().setBackground(Color.LIGHT_GRAY); // Đặt màu nền cho thanh tiêu đề

        GridBagConstraints gbc_scrollPane = new GridBagConstraints();
        gbc_scrollPane.gridwidth = 7;
        gbc_scrollPane.insets = new Insets(0, 0, 0, 5);
        gbc_scrollPane.fill = GridBagConstraints.BOTH;
        gbc_scrollPane.gridx = 0;
        gbc_scrollPane.gridy = 1;
        panel_SanPham.add(scrollPane, gbc_scrollPane); // Thêm JScrollPane chứa table_SP vào panel_SanPham

        JButton btn_tailai = new JButton("Tải lại");
        btn_tailai.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                refreshTable();
            }
        });
        scrollPane.setRowHeaderView(btn_tailai);


    }

    {
// GUI initializer generated by IntelliJ IDEA GUI Designer
// >>> IMPORTANT!! <<<
// DO NOT EDIT OR ADD ANY CODE HERE!
        $$$setupUI$$$();
    }

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        final JPanel panel1 = new JPanel();
//        panel1.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
    }

    public void refreshTable() {
        loadDataTalbe();
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Test SanPham_GUI");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 600);

        // Tạo một instance của SanPham_GUI
        SanPhamGUI sanPhamGUI = new SanPhamGUI();

        // Thêm SanPham_GUI vào JFrame
        frame.getContentPane().add(sanPhamGUI);

        // Hiển thị JFrame
        frame.setVisible(true);
        //240x300
    }
}
