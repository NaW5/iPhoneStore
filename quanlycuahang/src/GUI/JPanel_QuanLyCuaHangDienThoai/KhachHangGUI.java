package GUI.JPanel_QuanLyCuaHangDienThoai;

import BUS.KhachHangBUS;
import DAO.KhachHangDAO;
import DTO.KhachHangDTO;
import GUI.Dialog.KhachHang_Dialog.SuaKhachHang;
import GUI.Dialog.KhachHang_Dialog.ThemKhachHang;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class KhachHangGUI extends JPanel {

    private JTable table_KH;
    private DefaultTableModel tblModel;
    private KhachHangDAO khachHangDAO = new KhachHangDAO();
    private JTextField txtTimKiem;

    public KhachHangGUI() {
        setLayout(new BorderLayout());

        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));

        txtTimKiem = new JTextField(20);
        panel.add(txtTimKiem);

        JButton btn_timKH = new JButton("Tìm kiếm");
        btn_timKH.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                timKiemKhachHang();
            }
        });
        btn_timKH.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().createImage(SanPhamGUI.class.getResource("icon_timkiem.png"))));

        panel.add(btn_timKH);

        JButton btn_themKH = new JButton("Thêm");
        btn_themKH.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Tạo một JDialog để chứa ThemKhachHang_Dialog

                ThemKhachHang themKhachHang = new ThemKhachHang();
                themKhachHang.setLocationRelativeTo(null);
                themKhachHang.setVisible(true);
            }
        });
        btn_themKH.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().createImage(SanPhamGUI.class.getResource("icon_them.png"))));
        panel.add(btn_themKH);

        JButton btn_suaKH = new JButton("Sửa");
        btn_suaKH.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int selectedRow = table_KH.getSelectedRow();
                if (selectedRow != -1) {
                    // Lấy thông tin của khách hàng được chọn
                    DefaultTableModel model = (DefaultTableModel) table_KH.getModel();
                    int idKH = (int) model.getValueAt(selectedRow, 0);
                    // Thêm panel SuaKhachHang_Dialog vào dialog
                    SuaKhachHang suaKhachHang = new SuaKhachHang(idKH);
                    suaKhachHang.setLocationRelativeTo(null);
                    suaKhachHang.setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(null, "Vui lòng chọn khách hàng cần sửa!");
                }
            }
        });
        btn_suaKH.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().createImage(SanPhamGUI.class.getResource("icon_sua.png"))));
        panel.add(btn_suaKH);

        JButton btn_xoaKH = new JButton("Xóa");
        btn_xoaKH.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int selectedRow = table_KH.getSelectedRow();
                if (selectedRow != -1) {
                    int idKhachHang = (int) tblModel.getValueAt(selectedRow, 0);
                    int confirm = JOptionPane.showConfirmDialog(null, "Bạn chắc chắn muốn xóa khách hàng có ID: " + idKhachHang + "?", "Xác nhận xóa", JOptionPane.YES_NO_OPTION);
                    if (confirm == JOptionPane.YES_OPTION) {
                        KhachHangBUS khachHangBUS = new KhachHangBUS();
                        boolean result = khachHangBUS.delete(idKhachHang);
                        if (result) {
                            loadDataTalbe();
                        } else {
                            JOptionPane.showMessageDialog(null, "Đã xảy ra lỗi khi xóa khách hàng!");
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Vui lòng chọn khách hàng cần xóa!");
                }
            }
        });
        btn_xoaKH.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().createImage(SanPhamGUI.class.getResource("icon_xoa.png"))));
        panel.add(btn_xoaKH);

        JButton btnRefresh = new JButton("Tải lại");
        btnRefresh.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                loadDataTalbe();
            }
        });
        btnRefresh.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().createImage(SanPhamGUI.class.getResource("icon_tailai.png"))));

        panel.add(btnRefresh);

        add(panel, BorderLayout.NORTH);

        String[] columnNames = {"ID", "Tên khách hàng", "Địa chỉ", "Số điện thoại"};
        tblModel = new DefaultTableModel(columnNames, 0);

        // Load data from database
        loadDataTalbe();

        table_KH = new JTable(tblModel);
        // Đặt kích thước cố định cho cột
        table_KH.getColumnModel().getColumn(0).setPreferredWidth(100); // ID
        table_KH.getColumnModel().getColumn(1).setPreferredWidth(150); // Tên khách hàng
        table_KH.getColumnModel().getColumn(2).setPreferredWidth(200); // Địa chỉ
        table_KH.getColumnModel().getColumn(3).setPreferredWidth(100); // Số điện thoại

        // Đặt kích thước font cho ô trong bảng
        Font font = table_KH.getFont().deriveFont(Font.PLAIN, 14);
        table_KH.setFont(font);

        JScrollPane scrollPane = new JScrollPane(table_KH);
        add(scrollPane, BorderLayout.CENTER);
    }

    public void loadDataTalbe() {
        ArrayList<KhachHangDTO> khachHangList = new KhachHangBUS().loadDataFromDatabase();
        tblModel.setRowCount(0);
        for (KhachHangDTO khachHang : khachHangList) {
            tblModel.addRow(new Object[]{
                    khachHang.getIdKhachHang(), khachHang.getTenKhachHang(), khachHang.getDiaChiKhachHang(), khachHang.getSdtKhachHang()});
        }
    }


    public void timKiemKhachHang() {
        String keyword = txtTimKiem.getText().trim();
        if (keyword.isEmpty()) {
            loadDataTalbe(); // hiển thị tất cả khách hàng nếu không có từ khóa tìm kiếm
        } else {
            ArrayList<KhachHangDTO> khachHangList = new KhachHangBUS().search(keyword);
            tblModel.setRowCount(0);
            for (KhachHangDTO khachHang : khachHangList) {
                tblModel.addRow(new Object[]{khachHang.getIdKhachHang(), khachHang.getTenKhachHang(), khachHang.getDiaChiKhachHang(), khachHang.getSdtKhachHang()});
            }
        }
    }


    
}
