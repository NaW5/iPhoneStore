package GUI.Dialog.SanPhamDialog;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.Insets;

import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;
// Sửa phần này nha tú.ơ..................................................................................

import DAO.IMEIDAO;

import DTO.IMEIDTO;


import java.awt.Color;
import javax.swing.JButton;

import javax.swing.JPanel;

import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class xemDanhSachImeiSanPham_Dialog extends JDialog{
	private JTable table_ctsp;
	DefaultTableModel tblModel;


	public void loadDataTalbe(int idSP) {
		ArrayList<IMEIDTO> result = IMEIDAO.getInstance().selectByCondition("SANPHAM_idSP = " + idSP);
		// Sửa phần này nha tú.ơ..................................................................................

		tblModel.setRowCount(0);
		for (IMEIDTO sp : result) {
			tblModel.addRow(new Object[]{sp.getMaIMEI(), sp.getIdPhieuNhap(), sp.getIdPhieuXuat(), sp.getSANPHAM_idSP(), sp.getTrangThai()});
		}
	}

	public xemDanhSachImeiSanPham_Dialog(int idSP) {
		String[] columnNames = {"Mã IMEI", "Mã phiếu nhập", "Mã phiếu xuất", "Mã sản phẩm", "Trạng thái"};
		tblModel = new DefaultTableModel(columnNames, 0);
		loadDataTalbe(idSP);
		getContentPane().setLayout(null);
		JPanel panel_ctsp = new JPanel();
		panel_ctsp.setBounds(0, 0, 795, 547);
		getContentPane().add(panel_ctsp);

		JButton btn_huybo = new JButton("Tắt");
		btn_huybo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
		panel_ctsp.setLayout(null);


		btn_huybo.setBounds(491, 488, 63, 23);
		panel_ctsp.add(btn_huybo);

		table_ctsp = new JTable(tblModel);
		JScrollPane scrollPane = new JScrollPane(table_ctsp); // Tạo JScrollPane và đặt table_SP vào đó
		scrollPane.setBounds(62, 50, 672, 427);
		table_ctsp.getTableHeader().setBackground(Color.LIGHT_GRAY);
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.gridwidth = 7;
		gbc_scrollPane.insets = new Insets(0, 0, 0, 5);
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 0;
		gbc_scrollPane.gridy = 1;
		panel_ctsp.add(scrollPane); // Thêm JScrollPane chứa table_SP vào panel_SanPham

		JButton btn_tailai = new JButton("Tải lại");
		btn_tailai.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				loadDataTalbe(idSP);
			}
		});
		btn_tailai.setBounds(240, 488, 77, 23);
		panel_ctsp.add(btn_tailai);

		JButton btn_xoaIMEI = new JButton("Xóa IMEI");
		btn_xoaIMEI.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selectedRow = table_ctsp.getSelectedRow();
				if (selectedRow != -1) {
					DefaultTableModel model = (DefaultTableModel) table_ctsp.getModel();
					int maIMEI = (int) model.getValueAt(selectedRow, 0);
					xoaCTSP_Dialog spdialog = new xoaCTSP_Dialog(maIMEI);
					spdialog.setSize(500,300);
					spdialog.setVisible(true);
				}
				else {
					JOptionPane.showMessageDialog(null, "Vui lòng chọn IMEI cần xóa!", "Cảnh báo", JOptionPane.WARNING_MESSAGE);
				}
			}
		});
		btn_xoaIMEI.setBounds(354, 488, 95, 23);
		panel_ctsp.add(btn_xoaIMEI);

		JLabel lbl_DSImei = new JLabel("DANH SÁCH MÃ IMEI CỦA SẢN PHẨM " + String.valueOf(idSP));
		lbl_DSImei.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_DSImei.setFont(new Font("Tahoma", Font.BOLD, 14));
		lbl_DSImei.setBackground(new Color(128, 255, 255));
		lbl_DSImei.setBounds(10, 11, 775, 17);
		panel_ctsp.add(lbl_DSImei);


	}
}

