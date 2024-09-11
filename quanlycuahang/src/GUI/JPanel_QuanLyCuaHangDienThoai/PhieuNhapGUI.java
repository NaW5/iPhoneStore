package GUI.JPanel_QuanLyCuaHangDienThoai;


import BUS.PhieuNhapBUS;
import DTO.PhieuNhapDTO;
import GUI.Dialog.PhieuNhap.ChiTietPhieuNhap;
import GUI.Dialog.PhieuNhap.ThemNhanVienPN;
import GUI.Dialog.PhieuNhap.ThemPhieuNhap;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.util.ArrayList;

//import static GUI.Panel.ExcelExporter.exportToExcel;

public class PhieuNhapGUI extends JPanel {
	private JTable table_PN;
	private DefaultTableModel tblModel;
	private JTextField txtTimKiem;

	public PhieuNhapGUI() {
		setLayout(new BorderLayout());
		JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));

		txtTimKiem = new JTextField(20){
			@Override
			public void paintComponent(Graphics g) {
				super.paintComponent(g);
				if (getText().isEmpty()) {
					g.setColor(Color.gray);
					g.drawString("Tìm mã phiếu nhập", getInsets().left, (getHeight() - getInsets().top - getInsets().bottom) / 2 + getFont().getSize() / 2);
				}
			}
		};

		txtTimKiem.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				super.keyReleased(e);
				String searchText = txtTimKiem.getText().trim();
				PhieuNhapBUS phieuNhapBUS = new PhieuNhapBUS();
				ArrayList<PhieuNhapDTO> searchResult = phieuNhapBUS.search(searchText);
				tblModel.setRowCount(0);
				for (PhieuNhapDTO phieuNhap : searchResult) {
					String tongTien = String.format("%,.0f", phieuNhap.getTongTien());
					tblModel.addRow(new Object[]{phieuNhap.getIdPhieuNhap(), phieuNhap.getIdNhanVien(), tongTien});
				}
			}
		});
		panel.add(txtTimKiem);

		JButton btn_themNhanVien = new JButton("Thêm nhân viên");
		btn_themNhanVien.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().createImage(SanPhamGUI.class.getResource("icon_them.png"))));
		panel.add(btn_themNhanVien);
		btn_themNhanVien.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				themNhanVienVaoPhieu();
			}
		});
		JButton btn_them = new JButton("Thêm phiếu nhập");
		btn_them.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				them();
			}
		});
		btn_them.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().createImage(SanPhamGUI.class.getResource("icon_them.png"))));
		panel.add(btn_them);

		JButton btn_xoa = new JButton("Xóa");
		btn_xoa.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().createImage(SanPhamGUI.class.getResource("icon_xoa.png"))));
		panel.add(btn_xoa);
		btn_xoa.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				xoaPhieuNhap();
			}
		});

		JButton btn_chitiet = new JButton("Chi tiết");
		btn_chitiet.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				chiTiet();
			}
		});
		btn_chitiet.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().createImage(SanPhamGUI.class.getResource("icon_info.png"))));

		panel.add(btn_chitiet);

		JButton btnRefresh = new JButton("Tải lại");
		btnRefresh.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().createImage(SanPhamGUI.class.getResource("icon_tailai.png"))));

		panel.add(btnRefresh);
		btnRefresh.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				loadDataTalbe();
			}
		});

		JButton btn_xuatExcel = new JButton("Xuất Excel");
		btn_xuatExcel.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().createImage(SanPhamGUI.class.getResource("icon_exel.png"))));
		panel.add(btn_xuatExcel);

		btn_xuatExcel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JFileChooser jFileChooser = new JFileChooser();
				jFileChooser.setDialogTitle("Chọn nơi lưu file");
				int userSelection = jFileChooser.showSaveDialog(null);

				if (userSelection == JFileChooser.APPROVE_OPTION) {
					File fileToSave = jFileChooser.getSelectedFile();
					PhieuNhapBUS phieuNhapBUS = new PhieuNhapBUS();
					ArrayList<PhieuNhapDTO> dspn = phieuNhapBUS.loadDataFromDatabase();

					int dialogResult = JOptionPane.showConfirmDialog(null, "Bạn có muốn xuất file Excel không?", "Xác nhận", JOptionPane.YES_NO_OPTION);

					if (dialogResult == JOptionPane.YES_OPTION) {
						phieuNhapBUS.exportToExcel(dspn, fileToSave.getAbsolutePath());
						JOptionPane.showMessageDialog(null, "Xuất Excel thành công!");
					}
				}
			}
		});


		add(panel, BorderLayout.NORTH);

		String[] columnNames = {"Mã phiếu nhập", "Mã nhân viên", "Tổng tiền"};
		tblModel = new DefaultTableModel(columnNames, 0);

		table_PN = new JTable(tblModel);
		table_PN.getColumnModel().getColumn(0).setPreferredWidth(100);
		table_PN.getColumnModel().getColumn(1).setPreferredWidth(150);
		table_PN.getColumnModel().getColumn(2).setPreferredWidth(200);

		Font font = table_PN.getFont().deriveFont(Font.PLAIN, 14);
		table_PN.setFont(font);

		JScrollPane scrollPane = new JScrollPane(table_PN);
		add(scrollPane, BorderLayout.CENTER);
		loadDataTalbe();
	}
	public void loadDataTalbe() {
		ArrayList<PhieuNhapDTO> PhieuNhapList = new PhieuNhapBUS().loadDataFromDatabase();
		tblModel.setRowCount(0);
		for (PhieuNhapDTO phieuNhap : PhieuNhapList) {
			String tongTien = String.format("%,.0f", phieuNhap.getTongTien());
			tblModel.addRow(new Object[]{phieuNhap.getIdPhieuNhap(), phieuNhap.getIdNhanVien(), tongTien});
		}
	}
	private void chiTiet(){
		int selectedRow = table_PN.getSelectedRow();
		DefaultTableModel model = (DefaultTableModel) table_PN.getModel();
		if (selectedRow != -1) {
			int idPhieuNhap = (int) model.getValueAt(selectedRow, 0);
			ChiTietPhieuNhap chiTietPhieuNhap_dialog = new ChiTietPhieuNhap(idPhieuNhap);
			chiTietPhieuNhap_dialog.setLocationRelativeTo(null);
			chiTietPhieuNhap_dialog.setVisible(true);
		} else {
			JOptionPane.showMessageDialog(null, "Vui lòng chọn một phiếu nhập để xem chi tiết", "Thông báo", JOptionPane.WARNING_MESSAGE);
		}
	}
	private void them(){
		int selectedRow = table_PN.getSelectedRow();
		DefaultTableModel model = (DefaultTableModel) table_PN.getModel();
		if (selectedRow != -1) {

			int selectedIdPhieuNhap = (int) model.getValueAt(selectedRow, 0);
			int selectedIdNhanVien = (int) model.getValueAt(selectedRow, 1);

			JFrame frame = new JFrame("Thêm Phiếu Nhập");
			frame.setSize(1170, 700);
			ThemPhieuNhap themPhieuNhapPanel = new ThemPhieuNhap(selectedIdPhieuNhap, selectedIdNhanVien);
			frame.getContentPane().add(themPhieuNhapPanel);
			frame.setLocationRelativeTo(null);
			frame.setVisible(true);

		} else {
			JOptionPane.showMessageDialog(null, "Vui lòng chọn một phiếu nhập để thêm", "Thông báo", JOptionPane.WARNING_MESSAGE);
		}
	}
	private void themNhanVienVaoPhieu(){
		ThemNhanVienPN themNhanVienPN_dialog = new ThemNhanVienPN();
		themNhanVienPN_dialog.setLocationRelativeTo(null);
		themNhanVienPN_dialog.setVisible(true);
	}
	private void xoaPhieuNhap() {
		int selectedRow = table_PN.getSelectedRow();
		DefaultTableModel model = (DefaultTableModel) table_PN.getModel();
		if (selectedRow != -1) {
			int idPhieuNhap = (int) model.getValueAt(selectedRow, 0);
			try {
				String tongTienStr = model.getValueAt(selectedRow, 2).toString().replace(",", "").replace(".", "");
				double tongTien = Double.parseDouble(tongTienStr);
				if (tongTien > 0) {
					JOptionPane.showMessageDialog(null, "Không thể xóa phiếu nhập có tổng tiền lớn hơn 0", "Thông báo", JOptionPane.WARNING_MESSAGE);
				} else {
					int option = JOptionPane.showConfirmDialog(null, "Bạn có chắc chắn muốn xóa phiếu nhập này?", "Xác nhận xóa", JOptionPane.YES_NO_OPTION);
					if (option == JOptionPane.YES_OPTION) {
						PhieuNhapBUS phieuNhapBUS = new PhieuNhapBUS();
						int result = phieuNhapBUS.deletePhieuNhap(idPhieuNhap);
						if (result > 0) {
							JOptionPane.showMessageDialog(null, "Xóa phiếu nhập thành công");
							loadDataTalbe();
						} else {
							JOptionPane.showMessageDialog(null, "Xóa phiếu nhập thất bại");
						}
					}
				}
			} catch (NumberFormatException e) {
				JOptionPane.showMessageDialog(null, "Giá nhập không hợp lệ!");
			}
		} else {
			JOptionPane.showMessageDialog(null, "Vui lòng chọn một phiếu nhập để xóa", "Thông báo", JOptionPane.WARNING_MESSAGE);
		}
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> {
			JFrame frame = new JFrame("Quản lý phiếu nhập");
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.setSize(820, 400);
			PhieuNhapGUI phieuNhapGUI = new PhieuNhapGUI();
			frame.getContentPane().add(phieuNhapGUI);
			frame.setVisible(true);
		});
	}
}

