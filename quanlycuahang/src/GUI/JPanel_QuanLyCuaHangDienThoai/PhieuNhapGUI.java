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
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class PhieuNhapGUI extends JPanel {
	private JTable table_PN;
	private DefaultTableModel tblModel;
	private JTextField txtTimKiem;

	public PhieuNhapGUI(int idNVHienTai) {
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
					String thoiGian = new SimpleDateFormat("dd/MM/yyyy").format(phieuNhap.getThoiGian());
					tblModel.addRow(new Object[]{phieuNhap.getIdPhieuNhap(), phieuNhap.getIdNhanVien(), tongTien, thoiGian});
				}
			}
		});
		panel.add(txtTimKiem);

		JButton btn_them = new JButton("Thêm phiếu nhập");
		btn_them.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				them(idNVHienTai);
			}
		});
		btn_them.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().createImage(SanPhamGUI.class.getResource("icon_them.png"))));
		panel.add(btn_them);

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

		String[] columnNames = {"Mã phiếu nhập", "Mã nhân viên", "Tổng tiền", "Thời gian"};
		tblModel = new DefaultTableModel(columnNames, 0);

		table_PN = new JTable(tblModel);
		table_PN.getColumnModel().getColumn(0).setPreferredWidth(100);
		table_PN.getColumnModel().getColumn(1).setPreferredWidth(150);
		table_PN.getColumnModel().getColumn(2).setPreferredWidth(200);
		table_PN.getColumnModel().getColumn(3).setPreferredWidth(150);

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
			String thoiGian = new SimpleDateFormat("dd/MM/yyyy").format(phieuNhap.getThoiGian());
			tblModel.addRow(new Object[]{phieuNhap.getIdPhieuNhap(), phieuNhap.getIdNhanVien(), tongTien, thoiGian});
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

	private void them(int idNVHienTai){
		int selectedRow = table_PN.getSelectedRow();
		DefaultTableModel model = (DefaultTableModel) table_PN.getModel();
		if (selectedRow != -1) {
			int selectedIdPhieuNhap = (int) model.getValueAt(selectedRow, 0);
			int selectedIdNhanVien = (int) model.getValueAt(selectedRow, 1);

			JFrame frame = new JFrame("Bổ sung Phiếu Nhập " + selectedIdPhieuNhap);
			frame.setSize(1170, 700);
			ThemPhieuNhap themPhieuNhapPanel = new ThemPhieuNhap(selectedIdPhieuNhap, selectedIdNhanVien);
			frame.getContentPane().add(themPhieuNhapPanel);
			frame.setLocationRelativeTo(null);
			frame.setVisible(true);
		} else {
			PhieuNhapBUS phieuNhapBUS = new PhieuNhapBUS();
			JFrame frame = new JFrame("Thêm Phiếu Nhập");
			frame.setSize(1170, 700);
			ThemPhieuNhap themPhieuNhapPanel = new ThemPhieuNhap(phieuNhapBUS.getMaxIdPhieuNhap()+1, idNVHienTai);
			frame.getContentPane().add(themPhieuNhapPanel);
			frame.setLocationRelativeTo(null);
			frame.setVisible(true);
		}
	}
}