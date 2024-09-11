package GUI.Dialog.HoaDonDialog;

import javax.swing.JDialog;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Font;
import java.util.ArrayList;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import BUS.KhachHangBUS;
import DTO.KhachHangDTO;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class xemDSKH_Dialog extends JDialog {
    private JTextField txt_timKiemKH;
    private JTable table_KH;
    DefaultTableModel tblModel;
    private int selectedCustomerId;
    private KhachHangBUS khBUS = new KhachHangBUS();

    public int getSelectedCustomerId() {
        return selectedCustomerId;
    }

    public void loadDataTalbe() {
        ArrayList<KhachHangDTO> result = khBUS.getAll();
        tblModel.setRowCount(0);
        for (KhachHangDTO kh : result) {
            tblModel.addRow(new Object[]{kh.getIdKhachHang(), kh.getTenKhachHang(), kh.getDiaChiKhachHang(), kh.getSdtKhachHang()});
        }
    }

    public xemDSKH_Dialog() {

        String[] columnNames = {"Mã khách hàng", "Tên khách hàng", "Địa chỉ", "Số điện thoại"};
        tblModel = new DefaultTableModel(columnNames, 0);
        getContentPane().setLayout(null);
        loadDataTalbe();
        table_KH = new JTable(tblModel);
        table_KH.setRowSelectionInterval(0, 0);
        selectedCustomerId = (int) tblModel.getValueAt(0, 0);
        txt_timKiemKH = new JTextField();
        txt_timKiemKH.setBounds(325, 28, 238, 32);
        getContentPane().add(txt_timKiemKH);
        txt_timKiemKH.setColumns(10);

        JButton btn_chonKH = new JButton("Chọn khách hàng");
        btn_chonKH.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int selectedRow = table_KH.getSelectedRow();
                if (selectedRow != -1) {
                    selectedCustomerId = (int) tblModel.getValueAt(selectedRow, 0);
                    dispose();
                }
            }
        });
        btn_chonKH.setFont(new Font("Tahoma", Font.PLAIN, 16));
        btn_chonKH.setBounds(584, 28, 161, 32);
        getContentPane().add(btn_chonKH);

        JLabel lbl_timkiem = new JLabel("Tìm kiếm:");
        lbl_timkiem.setFont(new Font("Tahoma", Font.PLAIN, 18));
        lbl_timkiem.setBounds(217, 31, 98, 23);
        getContentPane().add(lbl_timkiem);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(152, 86, 666, 323);
        getContentPane().add(scrollPane);

        scrollPane.setViewportView(table_KH);
    }
}

