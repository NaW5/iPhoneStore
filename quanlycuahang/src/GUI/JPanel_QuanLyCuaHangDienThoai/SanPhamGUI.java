package GUI.JPanel_QuanLyCuaHangDienThoai;

import BUS.SanPhamBUS;
import DTO.SanPhamDTO;
import GUI.Dialog.SanPhamDialog.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.Normalizer;
import java.util.ArrayList;

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
        table_SP = new JTable(tblModel);
        loadDataTalbe();
        setLayout(new BorderLayout()); // Change layout of panel_SanPham to BorderLayout
        JPanel panel_btn = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JScrollPane scrollPane = new JScrollPane(table_SP);
        add(scrollPane, BorderLayout.CENTER);

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
                    spdialog.setLocationRelativeTo(null);
                    spdialog.setVisible(true);
                }
            }
        });
        btn_them.setForeground(new Color(0, 0, 0));
        btn_them.setBackground(new Color(255, 255, 255));
        btn_them.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().createImage(SanPhamGUI.class.getResource("icon_them.png"))));
        panel_btn.add(btn_them);

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
        btn_xoa.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().createImage(SanPhamGUI.class.getResource("icon_xoa.png"))));
        panel_btn.add(btn_xoa);

        JButton btn_chitiet = new JButton("Chi tiết");
        btn_chitiet.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int selectedRow = table_SP.getSelectedRow();
                if (selectedRow != -1) {
                    DefaultTableModel model = (DefaultTableModel) table_SP.getModel();
                    int idSP = (int) model.getValueAt(selectedRow, 0);
                    xemthongtinSanPham_Dialog spdialog = new xemthongtinSanPham_Dialog(idSP);
                    spdialog.setSize(1200, 500);
                    spdialog.setLocationRelativeTo(null);
                    spdialog.setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(null, "Vui lòng chọn sản phẩm cần xem!", "Cảnh báo", JOptionPane.WARNING_MESSAGE);
                }
            }
        });
        btn_chitiet.setIcon(new ImageIcon(SanPhamGUI.class.getResource("/GUI/JPanel_QuanLyCuaHangDienThoai/icon_info.png")));
        panel_btn.add(btn_chitiet);

        JButton btn_xemDS = new JButton("Xem DS");
        btn_xemDS.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int selectedRow = table_SP.getSelectedRow();
                if (selectedRow != -1) {
                    DefaultTableModel model = (DefaultTableModel) table_SP.getModel();
                    int idSP = (int) model.getValueAt(selectedRow, 0);
                    xemDanhSachImeiSanPham_Dialog spdialog = new xemDanhSachImeiSanPham_Dialog(idSP);
                    spdialog.setSize(800, 600);
                    spdialog.setLocationRelativeTo(null);
                    spdialog.setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(null, "Vui lòng chọn sản phẩm cần xem!", "Cảnh báo", JOptionPane.WARNING_MESSAGE);
                }
            }
        });
        btn_xemDS.setIcon(new ImageIcon(SanPhamGUI.class.getResource("/GUI/JPanel_QuanLyCuaHangDienThoai/icon_xemDS.png")));
        panel_btn.add(btn_xemDS);

        JButton btn_tailai = new JButton("Tải lại");
        btn_tailai.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                refreshTable();
            }
        });
        btn_tailai.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().createImage(SanPhamGUI.class.getResource("icon_tailai.png"))));
        panel_btn.add(btn_tailai);

        JComboBox comboBox_loc = new JComboBox();
        comboBox_loc.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String selectedValue = (String) comboBox_loc.getSelectedItem();
                if (selectedValue != null && selectedValue.equals(comboBox_loc.getItemAt(0))) {
                    loadDataTalbe(); // Gọi hàm loadDataTable mà không truyền tham số
                } else {
                    loadDataTalbe(selectedValue); // Gọi hàm loadDataTable với tham số
                }
            }
        });
        comboBox_loc.setModel(new DefaultComboBoxModel(new String[]{"Tất cả", "IPhone X", "IPhone XR", "IPhone XS", "IPhone XS Max", "IPhone 11", "IPhone 11 Pro", "IPhone 11 Pro Max", "IPhone 12", "IPhone 12 Pro", "IPhone 12 Pro Max"}));
        panel_btn.add(comboBox_loc);

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
        textField_timkiem.setColumns(10);
        panel_btn.add(textField_timkiem);

        add(panel_btn, BorderLayout.NORTH);
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
}