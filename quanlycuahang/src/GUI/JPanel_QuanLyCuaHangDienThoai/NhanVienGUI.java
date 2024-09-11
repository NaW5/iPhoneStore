package GUI.JPanel_QuanLyCuaHangDienThoai;

import BUS.NhanVienBUS;
import DTO.NhanVienDTO;
import GUI.Dialog.NhanVienDialog.SuaNhanVien;
import GUI.Dialog.NhanVienDialog.ThemNhanVien;
//import GUI.Dialog.XoaNhanVien;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.File;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;

public class NhanVienGUI extends JPanel {

    private static final long serialVersionUID = 1L;
    private JTable table_Nv;
    DefaultTableModel tblModel;
    private JTextField jTxt_timKiem;
    public NhanVienBUS nvBUS = new NhanVienBUS();

    public NhanVienGUI() {
        setLayout(new BorderLayout());
        JPanel panel_btn = new JPanel(new FlowLayout(FlowLayout.LEFT));
        String[] columnNames = {
                "ID Nhân Viên", "Họ Tên", "Giới Tính", "Ngày Sinh", "Số Điện Thoại", "Trạng Thái"
        };
        tblModel = new DefaultTableModel(columnNames,0);
        loadDataTable();
        table_Nv = new JTable(tblModel);
        table_Nv.getColumnModel().getColumn(0).setPreferredWidth(40);
        table_Nv.getColumnModel().getColumn(1).setPreferredWidth(150);
        table_Nv.getColumnModel().getColumn(2).setPreferredWidth(30);
        table_Nv.getColumnModel().getColumn(3).setPreferredWidth(50);
        table_Nv.getColumnModel().getColumn(4).setPreferredWidth(50);
        table_Nv.getColumnModel().getColumn(5).setPreferredWidth(20);

        JScrollPane scrollPane = new JScrollPane(table_Nv);
        add(scrollPane, BorderLayout.CENTER);

        jTxt_timKiem = new JTextField(20);
        panel_btn.add(jTxt_timKiem);
        JButton btn_timKiem = new JButton("Tìm Kiếm");
        btn_timKiem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                timKiemNhanVien();
            }
        });
        btn_timKiem.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().createImage(SanPhamGUI.class.getResource("icon_timkiem.png"))));
        panel_btn.add(btn_timKiem);

        JButton btn_them = new JButton("Thêm");
        btn_them.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ThemNhanVien themNhanVien = new ThemNhanVien();
                themNhanVien.setLocationRelativeTo(null);
                themNhanVien.setVisible(true);
            }
        });
        btn_them.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().createImage(SanPhamGUI.class.getResource("icon_them.png"))));
        panel_btn.add(btn_them);


        JButton btn_sua = new JButton("Sửa");
        btn_sua.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int row = table_Nv.getSelectedRow();
                if(row != -1){ // Check if a row is selected
                    DefaultTableModel model = (DefaultTableModel) table_Nv.getModel();
                    int idNv = (int) model.getValueAt(row, 0);
                    SuaNhanVien suaNhanVien = new SuaNhanVien(idNv);
                    suaNhanVien.setLocationRelativeTo(null);
                    suaNhanVien.setVisible(true);
                }else{
                    JOptionPane.showMessageDialog(null, "Vui lòng chọn nhân viên cần sửa");
                }
            }
        });
        btn_sua.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().createImage(SanPhamGUI.class.getResource("icon_sua.png"))));
        panel_btn.add(btn_sua);

        JButton btn_xoa = new JButton("Xóa");
        btn_xoa.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int row = table_Nv.getSelectedRow();
                if(row != -1){ // Check if a row is selected
                    int idNv = (int) tblModel.getValueAt(row, 0);
                    int confirm = JOptionPane.showConfirmDialog(null, "Bạn chắc chắn muốn xóa nhân viên có ID: " + idNv + "?", "Xác nhận xóa", JOptionPane.YES_NO_OPTION);
                    if (confirm == JOptionPane.YES_OPTION) {
                        // Update the isVisible status of the employee to 0 instead of deleting
                        nvBUS.delete(idNv);
                        loadDataTable();
                    }
                }else{
                    JOptionPane.showMessageDialog(null, "Vui lòng chọn nhân viên cần xóa");
                }
            }
        });
        btn_xoa.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().createImage(SanPhamGUI.class.getResource("icon_xoa.png"))));
        panel_btn.add(btn_xoa);

        JButton btn_taiLai = new JButton("Tải Lại");
        btn_taiLai.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                loadDataTable();
            }
        });
        btn_taiLai.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().createImage(SanPhamGUI.class.getResource("icon_tailai.png"))));

        panel_btn.add(btn_taiLai);



        JButton btn_xuatExel = new JButton("Xuất Exel");
        btn_xuatExel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JFileChooser jFileChooser = new JFileChooser();
                jFileChooser.setDialogTitle("Chọn nơi lưu file");
                int userSelection = jFileChooser.showSaveDialog(null);

                if (userSelection == JFileChooser.APPROVE_OPTION) {
                    File fileToSave = jFileChooser.getSelectedFile();
                    ArrayList<NhanVienDTO> dsnv = nvBUS.selectAll();
                    int kq = JOptionPane.showConfirmDialog(null, "Bạn có muốn xuất file Excel không?", "Xác nhận", JOptionPane.YES_NO_OPTION);

                    if (kq == JOptionPane.YES_OPTION) {
                        nvBUS.exportToExcel(dsnv, fileToSave.getAbsolutePath());

                        JOptionPane.showMessageDialog(null, "Xuất Excel thành công!");
                    }
                }
            }
        });
        btn_xuatExel.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().createImage(SanPhamGUI.class.getResource("icon_exel.png"))));

        panel_btn.add(btn_xuatExel);

        add(panel_btn, BorderLayout.NORTH);




    }
    public void loadDataTable(){
        ArrayList<NhanVienDTO> dsnv = nvBUS.selectAll();
        tblModel.setRowCount(0);
        for(NhanVienDTO nv : dsnv){
            tblModel.addRow(new Object[]{
                    nv.getIdNV(), nv.getHoTen(), nv.getGioiTinh(), nv.getNgaySinh(), nv.getSdt(), nv.getIsDelete()
            });
        }
    }
    public void timKiemNhanVien(){
        String key = jTxt_timKiem.getText().trim();
        if(key.isEmpty()){
            loadDataTable();
        }else {
            ArrayList<NhanVienDTO> dsnv = nvBUS.search(key);
            tblModel.setRowCount(0);
            for(NhanVienDTO nv : dsnv) {
                tblModel.addRow(new Object[]{
                        nv.getIdNV(), nv.getHoTen(), nv.getGioiTinh(), nv.getNgaySinh(), nv.getSdt(), nv.getIsDelete()});
            }
        }
    }
}
