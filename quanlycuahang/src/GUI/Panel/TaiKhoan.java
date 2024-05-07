package GUI.Panel;


import BUS.TaiKhoanBUS;
import DAO.TaiKhoanDAO;
import DTO.TaiKhoanDTO;
import GUI.Dialog.SuaNhanVien;
import GUI.Dialog.SuaTaiKhoan;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;

public class TaiKhoan extends JPanel {

    private static final long serialVersionUID = 1L;
    private JTable table_Tk;
    DefaultTableModel tblModel;
    public TaiKhoanBUS tkBUS = new TaiKhoanBUS();

    private JTextField jTxt_timKiem;


    public TaiKhoan() {
        setLayout(new BorderLayout());
        JPanel panel_btn = new JPanel(new FlowLayout(FlowLayout.LEFT));
        String[] columnNames = {
                 "Tên Đăng Nhập", "Mật Khẩu", "Trạng Thái", "Chức Vụ", "Id nhân viên"
        };
        tblModel = new DefaultTableModel(columnNames,0);
        loadDataTable();
        table_Tk = new JTable(tblModel);
        table_Tk.getColumnModel().getColumn(0).setPreferredWidth(100);
        table_Tk.getColumnModel().getColumn(1).setPreferredWidth(100);
        table_Tk.getColumnModel().getColumn(2).setPreferredWidth(100);
        table_Tk.getColumnModel().getColumn(3).setPreferredWidth(100);
        table_Tk.getColumnModel().getColumn(4).setPreferredWidth(100);


        JScrollPane scrollPane = new JScrollPane(table_Tk);
        add(scrollPane, BorderLayout.CENTER);

        JButton btn_sua = new JButton("Sửa");
        btn_sua.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int row = table_Tk.getSelectedRow();
                if(row != -1){ // Check if a row is selected
                    DefaultTableModel model = (DefaultTableModel) table_Tk.getModel();
                    String tenDangNhap = (String) model.getValueAt(row, 0);
                    String matKhau = (String) model.getValueAt(row, 1);
                    String chucVu = (String) model.getValueAt(row, 3);
                    int idNv = (int) model.getValueAt(row, 4);
                    SuaTaiKhoan suaTaiKhoan = new SuaTaiKhoan(idNv, tenDangNhap, matKhau, chucVu);
                    suaTaiKhoan.setVisible(true);
                }else{
                    JOptionPane.showMessageDialog(null, "Vui lòng chọn nhân viên cần sửa");
                }
            }
        });
        panel_btn.add(btn_sua);


        JButton btn_xoa = new JButton("Xóa");
        btn_xoa.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int row = table_Tk.getSelectedRow();
                if(row != -1){ // Check if a row is selected
                    int idNv = (int) tblModel.getValueAt(row, 4);
                    int confirm = JOptionPane.showConfirmDialog(null, "Bạn chắc chắn muốn xóa tài khoản của nhân viên có ID: " + idNv + "?", "Xác nhận xóa", JOptionPane.YES_NO_OPTION);
                    if (confirm == JOptionPane.YES_OPTION) {
                        // Update the isVisible status of the employee to 0 instead of deleting
                        tkBUS.delete(idNv);
                        loadDataTable();
                    }
                }else{
                    JOptionPane.showMessageDialog(null, "Vui lòng chọn nhân viên cần xóa");
                }
            }
        });
        panel_btn.add(btn_xoa);

        JButton btn_taiLai = new JButton("Tải Lại");
        btn_taiLai.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                loadDataTable();
            }
        });
        panel_btn.add(btn_taiLai);

        jTxt_timKiem = new JTextField(20);
        panel_btn.add(jTxt_timKiem);
        JButton btn_timKiem = new JButton("Tìm Kiếm");
        btn_timKiem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                timKiemNhanVien();
            }
        });
        panel_btn.add(btn_timKiem);
        add(panel_btn, BorderLayout.NORTH);


    }
//    public void loadDataTable(){
//        ArrayList<TaiKhoanDTO> dsnv = tkDAO.selectAll();
//        tblModel.setRowCount(0);
//        for(TaiKhoanDTO tk : dsnv){
//            tblModel.addRow(new Object[]{
//                    tk.getUseName(), tk.getMatKhau(), tk.getTrangThai(), tk.getChucVu(), tk.getNHANVIEN_idNV()
//            });
//        }
//    }
//    public void timKiemNhanVien(){
//        String key = jTxt_timKiem.getText().trim();
//        if(key.isEmpty()){
//            loadDataTable();
//        }else {
//            ArrayList<TaiKhoanDTO> dsnv = tkDAO.search(key);
//            tblModel.setRowCount(0);
//            for(TaiKhoanDTO nv : dsnv) {
//                tblModel.addRow(new Object[]{
//                        nv.getUseName(), nv.getMatKhau(), nv.getTrangThai(), nv.getChucVu(), nv.getNHANVIEN_idNV()});
//            }
//        }
//    }
public void loadDataTable(){
    ArrayList<TaiKhoanDTO> dsnv = tkBUS.selectAll();
    tblModel.setRowCount(0);
    for(TaiKhoanDTO tk : dsnv){
        tblModel.addRow(new Object[]{
                tk.getUseName(), tk.getMatKhau(), tk.getTrangThai(), tk.getChucVu(), tk.getNHANVIEN_idNV()
        });
    }
}
    public void timKiemNhanVien(){
        String key = jTxt_timKiem.getText().trim();
        if(key.isEmpty()){
            loadDataTable();
        }else {
            ArrayList<TaiKhoanDTO> dsnv = tkBUS.search(key);
            tblModel.setRowCount(0);
            for(TaiKhoanDTO nv : dsnv) {
                tblModel.addRow(new Object[]{
                        nv.getUseName(), nv.getMatKhau(), nv.getTrangThai(), nv.getChucVu(), nv.getNHANVIEN_idNV()});
            }
        }
    }
}
