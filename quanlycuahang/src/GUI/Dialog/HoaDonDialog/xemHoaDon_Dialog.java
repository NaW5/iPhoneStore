package GUI.Dialog.HoaDonDialog;

import javax.swing.JDialog;

import BUS.HoaDonBUS;
import BUS.KhachHangBUS;
import BUS.PhieuBaoHanhBUS;
import BUS.SanPhamBUS;
import BUS.ctHoaDonBUS;
import BUS.ctSanPhamBUS;

//Sửa ở đây nha..............................................................................................
import DAO.KhuyenMaiDAO;
import DAO.PhieuBaoHanhDAO;
import DAO.SanPhamDAO;
import DAO.ctSanPhamDAO;

import DTO.KhuyenMaiDTO;
import DTO.PhieuBaoHanhDTO;
import DTO.SanPhamDTO;
import DTO.ctHoaDonDTO;
import DTO.ctSanPhamDTO;


import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.util.ArrayList;

import javax.swing.JComboBox;
import javax.swing.SwingConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;

import java.awt.event.ActionEvent;
import javax.swing.DefaultComboBoxModel;

public class xemHoaDon_Dialog extends JDialog{
	private JTable table_SP;
	DefaultTableModel tblModel;
	private JTextField txt_maSP;
	private JTextField txt_tenSP;
	private JTextField txt_rom;
	private JTextField txt_mauSac;
	private JTextField txt_maHD;
	private JTextField txt_tenNV;
	private JTextField txt_idKH;
	private JTextField txt_tongtien;
	public SanPhamBUS spBUS = new SanPhamBUS();
	public ctSanPhamBUS ctspBUS = new ctSanPhamBUS();
	public ctHoaDonBUS cthdBUS = new ctHoaDonBUS();
	public HoaDonBUS hdBUS = new HoaDonBUS();
	public KhachHangBUS khBUS = new KhachHangBUS();
	private JTextField txt_soluong;

	public void loadDataTalbe() {
		ArrayList<SanPhamDTO> result = spBUS.layDanhSachSanPham();
		tblModel.setRowCount(0);
		for (SanPhamDTO sp : result) {
			tblModel.addRow(new Object[]{sp.getIdSP(), sp.getTenSP()});
		}
	}

	public void setSelectedCustomerId(int selectedCustomerId) {
		txt_idKH.setText(String.valueOf(selectedCustomerId));
	}


	public xemHoaDon_Dialog(int idHD) {
		String[] columnNames = {"Mã sản phẩm", "Tên sản phẩm"};
		tblModel = new DefaultTableModel(columnNames, 0);
		getContentPane().setLayout(null);
		loadDataTalbe();

		table_SP = new JTable(tblModel);
		ctHoaDonDTO newcthd = cthdBUS.getCTHoaDonById(idHD);
		int newIDSP = newcthd.getSANPHAM_idSP();
		int vitri = -1;
		int rowCount = tblModel.getRowCount();
		for (int i = 0; i < rowCount; i++) {
			int idSP = (int) tblModel.getValueAt(i, 0);
			if (idSP == newIDSP) {
				vitri = i;
				break;
			}
		}

		table_SP.setRowSelectionInterval(vitri, vitri);
		JScrollPane scrollPane = new JScrollPane(table_SP);
		scrollPane.setBounds(49, 72, 372, 379);
		getContentPane().add(scrollPane);



		int selectedRow = table_SP.getSelectedRow();
		DefaultTableModel model = (DefaultTableModel) table_SP.getModel();
		int idSP = (int) model.getValueAt(selectedRow, 0);



		JLabel lbl_maSP = new JLabel("Mã sản phẩm");
		lbl_maSP.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lbl_maSP.setBounds(498, 77, 94, 14);
		getContentPane().add(lbl_maSP);

		txt_maSP = new JTextField(String.valueOf(idSP));
		txt_maSP.setEnabled(false);
		txt_maSP.setEditable(false);
		txt_maSP.setBounds(498, 102, 94, 27);
		getContentPane().add(txt_maSP);
		txt_maSP.setColumns(10);

		SanPhamDTO sp = spBUS.laySanPhamTheoId(Integer.parseInt(txt_maSP.getText()));
		ctSanPhamDTO ctsp = ctspBUS.timctSanPhamTheoId(Integer.parseInt(txt_maSP.getText()));

		JLabel lbl_tenSP = new JLabel("Tên sản phẩm");
		lbl_tenSP.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lbl_tenSP.setBounds(666, 79, 94, 14);
		getContentPane().add(lbl_tenSP);

		txt_tenSP = new JTextField(sp.getTenSP());
		txt_tenSP.setEnabled(false);
		txt_tenSP.setEditable(false);
		txt_tenSP.setColumns(10);
		txt_tenSP.setBounds(666, 105, 158, 27);
		getContentPane().add(txt_tenSP);

		JLabel lbl_rom = new JLabel("Bộ nhớ ");
		lbl_rom.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lbl_rom.setBounds(498, 165, 94, 14);
		getContentPane().add(lbl_rom);

		txt_rom = new JTextField(ctsp.getRom());
		txt_rom.setEnabled(false);
		txt_rom.setEditable(false);
		txt_rom.setColumns(10);
		txt_rom.setBounds(498, 190, 94, 27);
		getContentPane().add(txt_rom);

		JLabel lbl_mauSac = new JLabel("Màu sắc");
		lbl_mauSac.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lbl_mauSac.setBounds(666, 165, 94, 14);
		getContentPane().add(lbl_mauSac);

		txt_mauSac = new JTextField(sp.getMauSac());
		txt_mauSac.setEnabled(false);
		txt_mauSac.setEditable(false);
		txt_mauSac.setColumns(10);
		txt_mauSac.setBounds(666, 190, 158, 27);
		getContentPane().add(txt_mauSac);

		JLabel lbl_maHD = new JLabel("Mã hóa đơn");
		lbl_maHD.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lbl_maHD.setBounds(907, 77, 94, 14);
		getContentPane().add(lbl_maHD);

		txt_maHD = new JTextField(String.valueOf(idHD));
		txt_maHD.setEditable(false);
		txt_maHD.setColumns(10);
		txt_maHD.setBounds(907, 103, 94, 27);
		getContentPane().add(txt_maHD);



		JLabel lbl_NV = new JLabel("Nhân viên");
		lbl_NV.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lbl_NV.setBounds(907, 165, 94, 14);
		getContentPane().add(lbl_NV);

		//tên của người đang đăng nhập

		txt_tenNV = new JTextField(String.valueOf(hdBUS.getHoaDonById(idHD).getNHANVIEN_idNV()));
		txt_tenNV.setColumns(10);
		txt_tenNV.setBounds(907, 188, 132, 27);
		getContentPane().add(txt_tenNV);

		JLabel lbl_KH = new JLabel("Khách hàng");
		lbl_KH.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lbl_KH.setBounds(907, 250, 94, 14);
		getContentPane().add(lbl_KH);

		txt_idKH = new JTextField(String.valueOf(hdBUS.getHoaDonById(idHD).getKHACHHANG_idKH()));
		txt_idKH.setColumns(10);
		txt_idKH.setBounds(907, 280, 132, 27);
		getContentPane().add(txt_idKH);

		JLabel lbl_tongtien = new JLabel("Tổng tiền");
		lbl_tongtien.setFont(new Font("Tahoma", Font.BOLD, 16));
		lbl_tongtien.setBounds(905, 348, 107, 27);
		getContentPane().add(lbl_tongtien);


		txt_tongtien = new JTextField("0");
		txt_tongtien.setEditable(false);
		txt_tongtien.setEnabled(false);
		txt_tongtien.setBounds(907, 386, 158, 27);
		getContentPane().add(txt_tongtien);
		txt_tongtien.setColumns(10);

		txt_soluong = new JTextField(String.valueOf(newcthd.getSoLuong()));





		JButton btn_thoat = new JButton("Thoát");
		btn_thoat.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btn_thoat.setIcon(new ImageIcon(xemHoaDon_Dialog.class.getResource("/GUI/JFrame_QuanLyCuaHangDienThoai/icon_dangxuat.png")));
		btn_thoat.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btn_thoat.setBounds(498, 429, 141, 57);
		getContentPane().add(btn_thoat);

		JButton btn_xemThemNV = new JButton("...");
		btn_xemThemNV.setBounds(1041, 187, 28, 29);
		getContentPane().add(btn_xemThemNV);

		JButton btn_xemThemKH = new JButton("...");
		btn_xemThemKH.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				xemDSKH_Dialog dskhdialog = new xemDSKH_Dialog();
				dskhdialog.setSize(1000, 500);
				dskhdialog.setVisible(true);

				int selectedCustomerId = dskhdialog.getSelectedCustomerId();
				System.out.println(selectedCustomerId);
				if (selectedCustomerId != 0) {
					int idKH = khBUS.selectKh(selectedCustomerId).getIdKhachHang();
					txt_idKH.setText(String.valueOf(idKH));
				}
			}
		});
		btn_xemThemKH.setBounds(1041, 279, 28, 29);
		getContentPane().add(btn_xemThemKH);

		JLabel lbl_themHD = new JLabel("HÓA ĐƠN "+ String.valueOf(idHD));
		lbl_themHD.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_themHD.setFont(new Font("Tahoma", Font.BOLD, 20));
		lbl_themHD.setBounds(10, 0, 1099, 41);
		getContentPane().add(lbl_themHD);

		JLabel lbl_soluong = new JLabel("Số lượng");
		lbl_soluong.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lbl_soluong.setBounds(498, 252, 94, 14);
		getContentPane().add(lbl_soluong);


		txt_soluong.setColumns(10);
		txt_soluong.setBounds(498, 280, 94, 27);
		getContentPane().add(txt_soluong);
		table_SP.getColumnModel().getColumn(0).setPreferredWidth(95);

		JLabel lbl_baohanh = new JLabel("Bảo hành");
		lbl_baohanh.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lbl_baohanh.setBounds(666, 327, 94, 14);
		getContentPane().add(lbl_baohanh);



		JComboBox cbb_baohanh = new JComboBox();
		DefaultComboBoxModel<String> cbbmodel = new DefaultComboBoxModel<>();
		PhieuBaoHanhBUS phieuBaoHanhBUS = new PhieuBaoHanhBUS();
		ArrayList<PhieuBaoHanhDTO> dsPhieuBH = phieuBaoHanhBUS.getAllPhieuBaoHanh();
		for (PhieuBaoHanhDTO phieuBH : dsPhieuBH) {
			cbbmodel.addElement(phieuBH.getThoiGian());
		}
		cbb_baohanh.setModel(cbbmodel);
		cbb_baohanh.setBounds(666, 352, 94, 22);
		getContentPane().add(cbb_baohanh);
		int maBH = newcthd.getPHIEUBAOHANH_idBaoHanh();
		String baoHanhDangChon = PhieuBaoHanhDAO.getInstance().selectByIdBH(maBH).getThoiGian();

		////Sửa chỗ này///////////////////////////////////////////////////////////////////////////////////////////////
		System.out.println(maBH);
		System.out.println(baoHanhDangChon);
		cbb_baohanh.setSelectedItem(baoHanhDangChon);

		JLabel lbl_khuyenmai = new JLabel("Khuyến mãi (%)");
		lbl_khuyenmai.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lbl_khuyenmai.setBounds(666, 252, 119, 14);
		getContentPane().add(lbl_khuyenmai);

		JComboBox cbb_khuyenmai = new JComboBox();

		DefaultComboBoxModel<Integer> cbbmodelkm = new DefaultComboBoxModel<>();
		KhuyenMaiDAO kmDAO = KhuyenMaiDAO.getInstance();
		ArrayList<KhuyenMaiDTO> dskm = kmDAO.selectAll();
		for (KhuyenMaiDTO km : dskm) {
			cbbmodelkm.addElement((int) km.getPhanTram());
		}
		cbb_khuyenmai.setModel(cbbmodelkm);
		cbb_khuyenmai.setBounds(666, 282, 158, 22);
		getContentPane().add(cbb_khuyenmai);
		int maKM = newcthd.getKHUYENMAI_idKM();
		int kmDangChon = (int) KhuyenMaiDAO.getInstance().selectByIdKM(maKM).getPhanTram();
		cbb_khuyenmai.setSelectedItem(kmDangChon);
		////Sửa chỗ này///////////////////////////////////////////////////////////////////////////////////////////////


		table_SP.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				int selectedRow = table_SP.getSelectedRow(); // Lấy chỉ số hàng đã chọn
				if (selectedRow != -1) { // Kiểm tra xem có hàng nào được chọn không
					DefaultTableModel model = (DefaultTableModel) table_SP.getModel();
					int idSP = (int) model.getValueAt(selectedRow, 0);
					txt_soluong.setText("0");// Lấy idSP của hàng đã chọn
					// Tại đây, bạn có thể sử dụng idSP để cập nhật thông tin sản phẩm khác
					// Ví dụ:
					SanPhamDTO sp = SanPhamDAO.getInstance().selectById(idSP);
					ctSanPhamDTO ctsp = ctSanPhamDAO.getInstance().selectById(idSP);
					////Sửa chỗ này///////////////////////////////////////////////////////////////////////////////////////////////

					txt_maSP.setText(String.valueOf(sp.getIdSP()));
					txt_tenSP.setText(sp.getTenSP());
					txt_rom.setText(ctsp.getRom());
					txt_mauSac.setText(sp.getMauSac());
					int soluong = Integer.parseInt(txt_soluong.getText());
					int soluongton = sp.getSoLuong();
					txt_soluong.setText(String.valueOf(soluongton));
					if (soluong > soluongton) {
						JOptionPane.showMessageDialog(null, "Vui lòng nhập số lượng hợp lệ", "Lỗi", JOptionPane.ERROR_MESSAGE);
						txt_soluong.setText("0"); // Clear the invalid input
						txt_soluong.requestFocusInWindow();
					}
					Object selectedItem = cbb_khuyenmai.getSelectedItem();
					int km = Integer.parseInt(selectedItem.toString());
					float gia = sp.getGiaBan();
					int sl = Integer.parseInt(txt_soluong.getText());
					double tongTien = gia * sl - gia * sl * km / 100;
					DecimalFormat df = new DecimalFormat("#.##");
					txt_tongtien.setText(df.format(tongTien));
				}
			}
		});

		txt_soluong.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int soluong = Integer.parseInt(txt_soluong.getText());
				int maSanPhamDangHien = SanPhamDAO.getInstance().selectById(Integer.parseInt(txt_maSP.getText())).getIdSP();
				int soluongton = SanPhamDAO.getInstance().selectById(maSanPhamDangHien).getSoLuong();
				////Sửa chỗ này///////////////////////////////////////////////////////////////////////////////////////////////

				if (soluong > soluongton) {
					JOptionPane.showMessageDialog(null, "Vui lòng nhập số lượng hợp lệ", "Lỗi", JOptionPane.ERROR_MESSAGE);
					txt_soluong.setText("0"); // Clear the invalid input
					txt_soluong.requestFocusInWindow();
				}
				Object selectedItem = cbb_khuyenmai.getSelectedItem();
				int km = Integer.parseInt(selectedItem.toString());
				float gia = sp.getGiaBan();
				int sl = Integer.parseInt(txt_soluong.getText());
				double tongTien = gia * sl - gia * sl * km / 100;
				DecimalFormat df = new DecimalFormat("#.##");
				txt_tongtien.setText(df.format(tongTien));
			}
		});
	}

}


